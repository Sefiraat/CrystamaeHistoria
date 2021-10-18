package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStoryChunkDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RealisationAltarCache extends AbstractCache {

    @Getter
    private final Map<BlockPosition, Pair<StoryRarity, String>> crystalStoryMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public RealisationAltarCache(BlockMenu blockMenu) {
        super(blockMenu);
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
            processItem(itemStack);
        }
        saveMap();
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

    @ParametersAreNonnullByDefault
    private void processItem(ItemStack itemStack) {
        if (GeneralUtils.testChance(1, 5)) {
            final List<Story> storyList = StoryUtils.getAllStories(itemStack);
            final int x = ThreadLocalRandom.current().nextInt(-3, 4);
            final int z = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block potentialBlock = blockMenu.getBlock().getRelative(x, 0, z);
            if (potentialBlock.getType() == Material.AIR) {
                Story story = storyList.get(0);
                potentialBlock.setType(Material.SMALL_AMETHYST_BUD);
                crystalStoryMap.put(new BlockPosition(potentialBlock.getLocation()), new Pair<>(story.getRarity(), story.getId()));
                if (StoryUtils.removeStory(itemStack, story) == 0) {
                    itemStack.setAmount(0);
                } else {
                    StoriesManager.rebuildStoriedStack(itemStack);
                }
                summonGrowParticles(potentialBlock);
                summonConsumeParticles(blockMenu.getBlock());
            }

        }
    }

    private void saveMap() {
        final Chunk chunk = blockMenu.getBlock().getChunk();
        final BlockPosition position = new BlockPosition(blockMenu.getLocation());
        final List<Story> stories = new ArrayList<>();
        for (Map.Entry<BlockPosition, Pair<StoryRarity, String>> entry : crystalStoryMap.entrySet()) {
            Pair<StoryRarity, String> pair = entry.getValue();
            Story story = CrystamaeHistoria.getStoriesManager().getStory(pair.getSecondValue(), pair.getFirstValue());
            story.setBlockPosition(new BlockPosition(blockMenu.getLocation().getWorld(), entry.getKey().getPosition()));
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
        final Location location = block.getLocation();
        for (int i = 0; i < 2; i++) {
            final Location l = location.clone().add(0.5, 1, 0.5);
            location.getWorld().spawnParticle(Particle.FLASH, l, 0, 0.2, 0, 0.2, 0);
        }
    }

    private void summonGrowParticles(Block block) {
        final Location location = block.getLocation();
        final Location l = location.clone().add(0.5, 0.22, 0.5);
        location.getWorld().spawnParticle(Particle.CRIMSON_SPORE, l, 0, 0.5, 0, 0.5, 0);
        location.getWorld().spawnParticle(Particle.SPORE_BLOSSOM_AIR, l, 0, 0.5, 0, 0.5, 0);
    }

    private void summonFullyGrownParticles(Block block) {
        final Location location = block.getLocation();
        final Location l = location.clone().add(0.5, 0, 0.5);
        location.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 0, 0.5, 0, 0.5, 0, Material.BUDDING_AMETHYST.createBlockData());
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
