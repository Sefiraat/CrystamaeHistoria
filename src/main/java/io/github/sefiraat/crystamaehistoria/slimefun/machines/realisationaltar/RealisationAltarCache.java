package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.AbstractCache;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.StackUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RealisationAltarCache extends AbstractCache {

    private List<Long> crystals;

    @ParametersAreNonnullByDefault
    public RealisationAltarCache(BlockMenu blockMenu) {
        super(blockMenu);
    }

    protected void process() {
        tryGrow();
        ItemStack i = blockMenu.getItemInSlot(RealisationAltar.INPUT_SLOT);
        if (i != null && i.getType() != Material.AIR && !StoryUtils.hasRemainingStorySlots(i)) {
            rejectOverage(i);
            processItem(i);
        }
        saveMap();
    }

    @ParametersAreNonnullByDefault
    private void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), i2);
        }
    }

    @ParametersAreNonnullByDefault
    private void processItem(ItemStack itemStack) {
        JsonArray jsonArray = StoryUtils.getAllStories(itemStack);
        for (JsonElement jsonElement : jsonArray) {
            int x = ThreadLocalRandom.current().nextInt(-3, 4);
            int z = ThreadLocalRandom.current().nextInt(-3, 4);
            Block potential = blockMenu.getBlock().getRelative(x, 0 , z);
            if (potential.getType() == Material.AIR) {
                potential.setType(Material.SMALL_AMETHYST_BUD);
                crystals.add(new BlockPosition(potential.getLocation()).getPosition());
                if (StoryUtils.removeStory(itemStack, jsonElement) == 0) {
                    itemStack.setAmount(0);
                } else {
                    StackUtils.rebuildStoriedStack(itemStack);
                }
            }
        }
    }

    private void saveMap() {
        long[] longs = crystals.stream().mapToLong(l -> l).toArray();
        PersistentDataAPI.setLongArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeyHolder().getResolutionCrystalMap(), longs);
    }

    protected void loadMap() {
        long[] longs = PersistentDataAPI.getLongArray(blockMenu.getBlock().getChunk(), CrystamaeHistoria.getKeyHolder().getResolutionCrystalMap(), new long[0]);
        crystals = Arrays.stream(longs).boxed().collect(Collectors.toList());
    }

    private void tryGrow() {
        for (int i = crystals.size() - 1; i >= 0 ; --i) {
            long l = crystals.get(i);
            if (GeneralUtils.testChance(1, 20)) {
                BlockPosition blockPosition = new BlockPosition(blockMenu.getLocation().getWorld(), l);
                Block block = blockPosition.getBlock();
                Material material = block.getType();
                switch (material) {
                    case SMALL_AMETHYST_BUD:
                        block.setType(Material.MEDIUM_AMETHYST_BUD);
                        break;
                    case MEDIUM_AMETHYST_BUD:
                        block.setType(Material.LARGE_AMETHYST_BUD);
                        break;
                    case LARGE_AMETHYST_BUD:
                        break;
                    default:
                        crystals.remove(l);
                }
            }
        }
    }

    private void summonParticles() {

    }

    protected void kill() {
    }

}
