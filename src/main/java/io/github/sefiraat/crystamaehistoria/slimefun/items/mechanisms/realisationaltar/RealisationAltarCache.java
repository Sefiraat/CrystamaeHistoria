package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.realisationaltar;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.GildingUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStoryChunkDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RealisationAltarCache extends AbstractCache {

    @Getter
    private final Map<BlockPosition, RealisedCrystalState> crystalStoryMap = new HashMap<>();
    private final int tier;

    @ParametersAreNonnullByDefault
    public RealisationAltarCache(BlockMenu blockMenu, int tier) {
        super(blockMenu);
        this.tier = tier;

        final String activePlayerString = BlockStorage.getLocationInfo(blockMenu.getLocation(), Keys.BS_CP_ACTIVE_PLAYER);
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    protected void process() {
        tryGrow();
        final ItemStack inputItem = blockMenu.getItemInSlot(RealisationAltar.INPUT_SLOT);

        // No item inserted, try to pick up (T5 +) or shutdown
        if (inputItem == null || inputItem.getType() == Material.AIR) {
            if (this.tier >= 5) {
                tryInsertItem();
            }
            return;
        }

        if (inputItem.getType() != Material.AIR && StoryUtils.isStoried(inputItem) && !StoryUtils.hasRemainingStorySlots(inputItem)) {
            rejectOverage(inputItem);
            if (processItem(inputItem)) {
                saveMap();
            }
        }
    }

    private void tryInsertItem() {
        final Collection<Entity> entities = getWorld().getNearbyEntities(
            getLocation().clone().add(0.5, 1, 0.5),
            0.3,
            0.3,
            0.3,
            Item.class::isInstance
        );

        if (!entities.isEmpty()) {
            final Item item = (Item) entities.stream().findFirst().orElse(null);
            final ItemStack itemStack = item.getItemStack();
            final ItemStack clone = itemStack.asQuantity(1);

            this.blockMenu.replaceExistingItem(RealisationAltar.INPUT_SLOT, clone);

            final int amount = CrystamaeHistoria.getSupportedPluginManager().getStackAmount(item);

            if (amount == 1) {
                item.remove();
            } else {
                CrystamaeHistoria.getSupportedPluginManager().setStackAmount(item, amount - 1);
            }
        }
    }

    private void tryGrow() {
        final Iterator<Map.Entry<BlockPosition, RealisedCrystalState>> iterator = crystalStoryMap.entrySet().iterator();

        while (iterator.hasNext()) {
            final Map.Entry<BlockPosition, RealisedCrystalState> entry = iterator.next();
            final Block block = entry.getKey().getBlock();
            final Material material = block.getType();

            if (entry.getValue().isGilded()) {
                summonGildedParticles(block);
            }

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
    private boolean processItem(ItemStack itemStack) {
        final BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(itemStack.getType());
        if (definition.getBlockTier().tier <= this.tier + 1) {
            if (GeneralUtils.testChance(1, 6)) {
                final int x = ThreadLocalRandom.current().nextInt(-3, 4);
                final int z = ThreadLocalRandom.current().nextInt(-3, 4);
                final Block potentialBlock = blockMenu.getBlock().getRelative(x, 0, z);
                if (potentialBlock.isEmpty() && potentialBlock.getRelative(BlockFace.DOWN).getType().isSolid()) {
                    final List<Story> storyList = StoryUtils.getAllStories(itemStack);
                    final Story story = storyList.get(0);
                    final boolean isGilded = GildingUtils.isGilded(itemStack);

                    potentialBlock.setType(Material.SMALL_AMETHYST_BUD);
                    crystalStoryMap.put(
                        new BlockPosition(potentialBlock.getLocation()),
                        new RealisedCrystalState(story.getRarity(), story.getId(), isGilded)
                    );
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
        for (Map.Entry<BlockPosition, RealisedCrystalState> entry : crystalStoryMap.entrySet()) {
            RealisedCrystalState state = entry.getValue();
            Story story = CrystamaeHistoria.getStoriesManager().getStory(state.storyId, state.storyRarity).copy();
            story.setBlockPosition(entry.getKey());
            story.setGilded(state.gilded);
            stories.add(story);
        }
        DataTypeMethods.setCustom(chunk, Keys.newKey(String.valueOf(position.getPosition())), PersistentStoryChunkDataType.TYPE, stories);
    }

    private void summonGrowParticles(@Nonnull Block block) {
        final Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.CRIMSON_SPORE, 0.4, 3);
    }

    private void summonFullyGrownParticles(@Nonnull Block block) {
        final Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.WAX_OFF, 0.4, 3);
    }

    private void summonGildedParticles(@Nonnull Block block) {
        final Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, 0.4, 3, new Particle.DustOptions(Color.YELLOW, 2));
    }

    private void summonConsumeParticles(@Nonnull Block block) {
        final Location location = block.getLocation().add(0.5, 0.2, 0.5);
        ParticleUtils.displayParticleEffect(location, Particle.FLASH, 0.4, 2);
    }

    protected void reject(@Nullable ItemStack itemStack) {
        if (itemStack != null) {
            final ItemStack rejectedSpawn = itemStack.clone();
            itemStack.setAmount(0);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), rejectedSpawn);
        }
    }

    protected void loadMap() {
        final Chunk chunk = blockMenu.getBlock().getChunk();
        final BlockPosition position = new BlockPosition(blockMenu.getLocation());
        final List<Story> stories = DataTypeMethods.getCustom(
            chunk,
            Keys.newKey(String.valueOf(position.getPosition())),
            PersistentStoryChunkDataType.TYPE
        );
        if (stories != null) {
            for (Story story : stories) {
                crystalStoryMap.put(
                    story.getBlockPosition(),
                    new RealisedCrystalState(story.getRarity(), story.getId(), story.isGilded())
                );
            }
        }
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

    private void clearMap() {
        PersistentDataAPI.remove(blockMenu.getBlock().getChunk(), Keys.RESOLUTION_CRYSTAL_MAP);
    }

    protected World getWorld() {
        return blockMenu.getLocation().getWorld();
    }

    protected Location getLocation() {
        return blockMenu.getLocation();
    }

    public class RealisedCrystalState {
        private final StoryRarity storyRarity;
        private final String storyId;
        private final boolean gilded;

        private RealisedCrystalState(StoryRarity storyRarity, String storyId, boolean gilded) {
            this.storyRarity = storyRarity;
            this.storyId = storyId;
            this.gilded = gilded;
        }

        public StoryRarity getStoryRarity() {
            return storyRarity;
        }

        public String getStoryId() {
            return storyId;
        }

        public boolean isGilded() {
            return gilded;
        }
    }

}
