package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.StackUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
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
    private Map<Long, Pair<StoryRarity, Integer>> crystalStoryMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public RealisationAltarCache(BlockMenu blockMenu) {
        super(blockMenu);
    }

    protected void process() {
        tryGrow();
        final ItemStack i = blockMenu.getItemInSlot(RealisationAltar.INPUT_SLOT);
        if (i != null && i.getType() != Material.AIR && !StoryUtils.hasRemainingStorySlots(i)) {
            rejectOverage(i);
            processItem(i);
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
            final JsonArray jsonArray = StoryUtils.getAllStories(itemStack);
            final int x = ThreadLocalRandom.current().nextInt(-3, 4);
            final int z = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block potentialBlock = blockMenu.getBlock().getRelative(x, 0, z);
            if (potentialBlock.getType() == Material.AIR) {
                JsonElement jsonElement = jsonArray.get(0);
                String s = jsonElement.toString().replace("\"", "");
                String[] storyProfile = s.split("\\|");
                int storyId = Integer.parseInt(storyProfile[0]);
                StoryRarity storyRarity = StoryRarity.valueOf(storyProfile[1]);
                potentialBlock.setType(Material.SMALL_AMETHYST_BUD);
                crystalStoryMap.put(new BlockPosition(potentialBlock.getLocation()).getPosition(), new Pair<>(storyRarity, storyId));
                if (StoryUtils.removeStory(itemStack, jsonElement) == 0) {
                    itemStack.setAmount(0);
                } else {
                    StackUtils.rebuildStoriedStack(itemStack);
                }
                summonGrowParticles(potentialBlock);
                summonConsumeParticles(blockMenu.getBlock());
            }

        }
    }

    private void saveMap() {
        final List<Integer> storyRarityIds = new ArrayList<>();
        final List<Integer> storyIds = new ArrayList<>();
        for (Pair<StoryRarity, Integer> pair : crystalStoryMap.values()) {
            storyRarityIds.add(pair.getFirstValue().getId());
            storyIds.add(pair.getSecondValue());
        }
        final long[] locations = crystalStoryMap.keySet().stream().mapToLong(l -> l).toArray();
        final int[] rarities = storyRarityIds.stream().mapToInt(i -> i).toArray();
        final int[] ids = storyIds.stream().mapToInt(i -> i).toArray();
        PersistentDataAPI.setLongArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeys().getResolutionCrystalMap(), locations);
        PersistentDataAPI.setIntArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeys().getResolutionRarityMap(), rarities);
        PersistentDataAPI.setIntArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeys().getResolutionStoryMap(), ids);
    }

    protected void loadMap() {
        final long[] locations = PersistentDataAPI.getLongArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeys().getResolutionCrystalMap(), new long[0]);
        final int[] rarities = PersistentDataAPI.getIntArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeys().getResolutionRarityMap(), new int[0]);
        final int[] ids = PersistentDataAPI.getIntArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeys().getResolutionStoryMap(), new int[0]);
        if (locations.length > 0) {
            for (int i = 0; i < locations.length - 1; i++) {
                crystalStoryMap.put(locations[i], new Pair<>(StoryRarity.getById(rarities[i]), ids[i]));
            }
        }
    }

    private void clearMap() {
        PersistentDataAPI.remove(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeys().getResolutionCrystalMap());
    }

    private void tryGrow() {
        Iterator<Long> iterator = crystalStoryMap.keySet().iterator();
        while (iterator.hasNext()) {
            long l = iterator.next();
            final BlockPosition blockPosition = new BlockPosition(blockMenu.getLocation().getWorld(), l);
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
            final Location l = location.clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1), 1, ThreadLocalRandom.current().nextDouble(0, 1.1));
            location.getWorld().spawnParticle(Particle.FLASH, l, 0, 0.2, 0, 0.2, 0);
        }
    }

    private void summonGrowParticles(Block block) {
        final Location location = block.getLocation();
        for (int i = 0; i < 5; i++) {
            final Location l = location.clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1), 0, ThreadLocalRandom.current().nextDouble(0, 1.1));
            location.getWorld().spawnParticle(Particle.CRIMSON_SPORE, l, 0, 0.5, 0, 0.5, 0);
        }
    }

    private void summonFullyGrownParticles(Block block) {
        final Location location = block.getLocation();
        for (int i = 0; i < 2; i++) {
            final Location l = location.clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1), 0, ThreadLocalRandom.current().nextDouble(0, 1.1));
            location.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 0, 0.5, 0, 0.5, 0, Material.BUDDING_AMETHYST.createBlockData());
        }
    }

    protected void kill() {
        Iterator<Long> iterator = crystalStoryMap.keySet().iterator();
        while (iterator.hasNext()) {
            long l = iterator.next();
            Block block = new BlockPosition(blockMenu.getLocation().getWorld(), l).getBlock();
            block.setType(Material.AIR);
            iterator.remove();
            summonConsumeParticles(block);
        }
        clearMap();
    }

    protected World getWorld() {
        return blockMenu.getLocation().getWorld();
    }

    protected Location getLocation() {
        return blockMenu.getLocation();
    }

}
