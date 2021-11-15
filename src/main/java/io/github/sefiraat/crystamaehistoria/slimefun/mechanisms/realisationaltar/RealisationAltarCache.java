package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStoryChunkDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RealisationAltarCache extends AbstractCache {

    @Getter
    private final Map<BlockPosition, Pair<StoryRarity, String>> crystalStoryMap = new HashMap<>();
    private final int maxTier;

    @ParametersAreNonnullByDefault
    public RealisationAltarCache(BlockMenu blockMenu, int tier) {
        super(blockMenu);
        this.maxTier = tier + 1;

        final String activePlayerString = BlockStorage.getLocationInfo(blockMenu.getLocation(), Keys.BS_CP_ACTIVE_PLAYER);
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    protected void process() {
        tryGrow();
        final ItemStack itemStack = blockMenu.getItemInSlot(RealisationAltar.INPUT_SLOT);
        if (itemStack != null
            && itemStack.getType() != Material.AIR
            && StoryUtils.isStoried(itemStack)
            && !StoryUtils.hasRemainingStorySlots(itemStack)
        ) {
            rejectOverage(itemStack);
            if (processItem(itemStack)) {
                saveMap();
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            final ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), i2);
        }
    }

    protected void reject(@Nullable ItemStack itemStack) {
        if (itemStack != null) {
            final ItemStack rejectedSpawn = itemStack.clone();
            itemStack.setAmount(0);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), rejectedSpawn);
        }
    }

    @ParametersAreNonnullByDefault
    private boolean processItem(ItemStack itemStack) {
        final BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(itemStack.getType());
        if (definition.getTier().tier <= this.maxTier) {
            if (GeneralUtils.testChance(1, 6)) {
                final int x = ThreadLocalRandom.current().nextInt(-3, 4);
                final int z = ThreadLocalRandom.current().nextInt(-3, 4);
                final Block potentialBlock = blockMenu.getBlock().getRelative(x, 0, z);
                if (potentialBlock.isEmpty() && potentialBlock.getRelative(BlockFace.DOWN).getType().isSolid()) {
                    final List<Story> storyList = StoryUtils.getAllStories(itemStack);
                    final Story story = storyList.get(0);
                    potentialBlock.setType(Material.SMALL_AMETHYST_BUD);
                    crystalStoryMap.put(new BlockPosition(potentialBlock.getLocation()), new Pair<>(story.getRarity(), story.getId()));
                    if (StoryUtils.removeStory(itemStack, story) == 0) {
                        PlayerStatistics.addRealisation(activePlayer, definition);
                        itemStack.setAmount(0);
                    } else {
                        StoriesManager.rebuildStoriedStack(itemStack);
                    }
                    summonGrowParticles(potentialBlock);
                    summonConsumeParticles(blockMenu.getBlock());
                    return true;
                }
            }
        } else {
            reject(itemStack);
        }
        return false;
    }

    public void saveMap() {
        final Chunk chunk = blockMenu.getBlock().getChunk();
        final BlockPosition position = new BlockPosition(blockMenu.getLocation());
        final List<Story> stories = new ArrayList<>();
        for (Map.Entry<BlockPosition, Pair<StoryRarity, String>> entry : crystalStoryMap.entrySet()) {
            Pair<StoryRarity, String> pair = entry.getValue();
            Story story = CrystamaeHistoria.getStoriesManager().getStory(pair.getSecondValue(), pair.getFirstValue()).copy();
            story.setBlockPosition(entry.getKey());
            stories.add(story);
        }
        DataTypeMethods.setCustom(chunk, Keys.newKey(String.valueOf(position.getPosition())), PersistentStoryChunkDataType.TYPE, stories);
    }

    protected void loadMap() {
        final Chunk chunk = blockMenu.getBlock().getChunk();
        final BlockPosition position = new BlockPosition(blockMenu.getLocation());
        final List<Story> stories = DataTypeMethods.getCustom(chunk, Keys.newKey(String.valueOf(position.getPosition())), PersistentStoryChunkDataType.TYPE);
        if (stories != null) {
            for (Story story : stories) {
                crystalStoryMap.put(story.getBlockPosition(), new Pair<>(story.getRarity(), story.getId()));
            }
        }
    }

    private void clearMap() {
        PersistentDataAPI.remove(blockMenu.getBlock().getChunk(), Keys.RESOLUTION_CRYSTAL_MAP);
    }

    private void tryGrow() {
        Iterator<BlockPosition> iterator = crystalStoryMap.keySet().iterator();
        while (iterator.hasNext()) {
            BlockPosition blockPosition = iterator.next();
            final Block block = blockPosition.getBlock();
            final Material material = block.getType();
            switch (material) {
                case SMALL_AMETHYST_BUD:
                    if (GeneralUtils.testChance(1, 10)) {
                        block.setType(Material.MEDIUM_AMETHYST_BUD);
                        summonGrowParticles(block);
                    }
                    break;
                case MEDIUM_AMETHYST_BUD:
                    if (GeneralUtils.testChance(1, 20)) {
                        block.setType(Material.LARGE_AMETHYST_BUD);
                        summonGrowParticles(block);
                    }
                    break;
                case LARGE_AMETHYST_BUD:
                    summonFullyGrownParticles(block);
                    break;
                default:
                    iterator.remove();
            }
        }
    }

    private void summonConsumeParticles(Block block) {
        final Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.FLASH, 0.4, 2);
    }

    private void summonGrowParticles(Block block) {
        final Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.CRIMSON_SPORE, 0.4, 3);
    }

    private void summonFullyGrownParticles(Block block) {
        final Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.WAX_OFF, 0.4, 3);
    }

    protected void kill() {
        Iterator<BlockPosition> iterator = crystalStoryMap.keySet().iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next().getBlock();
            block.setType(Material.AIR);
            iterator.remove();
            summonConsumeParticles(block);
        }
        final Chunk chunk = blockMenu.getBlock().getChunk();
        final BlockPosition position = new BlockPosition(blockMenu.getLocation());
        PersistentDataAPI.remove(chunk, Keys.newKey(String.valueOf(position.getPosition())));
        clearMap();
    }

    protected World getWorld() {
        return blockMenu.getLocation().getWorld();
    }

    protected Location getLocation() {
        return blockMenu.getLocation();
    }

}
