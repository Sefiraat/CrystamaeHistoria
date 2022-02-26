package io.github.sefiraat.crystamaehistoria.slimefun.tools;

import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BalmySponge extends SlimefunItem {

    private static final NamespacedKey KEY = Keys.newKey("is_saturated");
    private static final String DISPLAY_NAME_SUFFIX = " (Saturated)";

    private final int range;

    @ParametersAreNonnullByDefault
    public BalmySponge(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(itemGroup, item, recipeType, recipe);
        this.range = range;
    }

    @Override
    public void preRegister() {
        addItemHandler(
            getPlaceHandler(),
            getBreakHandler()
        );
    }

    @Nonnull
    public BlockPlaceHandler getPlaceHandler() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                final Player player = e.getPlayer();
                final ItemStack itemStack = e.getItemInHand();
                final ItemMeta itemMeta = itemStack.getItemMeta();
                final Block block = e.getBlock();

                if (isSaturated(itemMeta)) {
                    // Is saturated, check for water and clean if possible
                    boolean isCleaned = false;

                    for (BlockFace face : BlockFace.values()) {
                        final Block checkBlock = block.getRelative(face);
                        if (checkBlock.getType() == Material.WATER && GeneralUtils.hasPermission(player, checkBlock, Interaction.BREAK_BLOCK)) {
                            final BlockData blockData = checkBlock.getBlockData();

                            if (blockData instanceof Levelled){
                                Levelled levelled = (Levelled) blockData;
                                if (levelled.getLevel() == 0) {
                                    checkBlock.setType(Material.OBSIDIAN);
                                    isCleaned = true;
                                }
                            }
                        }
                    }
                    if (isCleaned) {
                        changeSaturation(block, false);
                        block.setType(Material.DEAD_FIRE_CORAL_BLOCK);
                    } else {
                        changeSaturation(block, true);
                        block.setType(Material.FIRE_CORAL_BLOCK);
                    }
                } else {
                    // Is not saturated, check for lava and sponge if possible.
                    boolean hasAbsorbed = false;

                    for (Location possibleLocation : getPossibleLocations(block.getLocation(), BalmySponge.this.range)) {
                        final Block checkBlock = possibleLocation.getBlock();
                        if (checkBlock.getType() == Material.LAVA && GeneralUtils.hasPermission(player, checkBlock, Interaction.BREAK_BLOCK)) {
                            checkBlock.setType(Material.AIR);
                            hasAbsorbed = true;
                        }
                    }

                    if (hasAbsorbed) {
                        changeSaturation(block, true);
                        block.setType(Material.FIRE_CORAL_BLOCK);
                    } else {
                        changeSaturation(block, false);
                        block.setType(Material.DEAD_FIRE_CORAL_BLOCK);
                    }
                }
            }
        };
    }

    @Nonnull
    public BlockBreakHandler getBreakHandler() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                if (isSaturated(e.getBlock())) {
                    final ItemStack itemStack = BalmySponge.this.getItem().clone();
                    final ItemMeta itemMeta = itemStack.getItemMeta();

                    changeSaturation(itemMeta, true);
                    itemMeta.setDisplayName(itemMeta.getDisplayName() + DISPLAY_NAME_SUFFIX);
                    itemStack.setType(Material.FIRE_CORAL_BLOCK);
                    itemStack.setItemMeta(itemMeta);
                    drops.add(itemStack);
                } else {
                    drops.add(getItem().clone());
                }
            }
        };
    }

    @NotNull
    @Override
    public Collection<ItemStack> getDrops() {
        return Collections.emptyList();
    }

    private static void changeSaturation(@Nonnull Block block, boolean saturated) {
        BlockStorage.addBlockInfo(block, KEY.value(), Boolean.toString(saturated));
    }

    private static void changeSaturation(@Nonnull ItemMeta itemMeta, boolean saturated) {
        PersistentDataAPI.setBoolean(itemMeta, KEY, saturated);
    }

    private static boolean isSaturated(@Nonnull Block block) {
        String string = BlockStorage.getLocationInfo(block.getLocation(), KEY.value());
        return Boolean.parseBoolean(string);
    }

    private static boolean isSaturated(@Nonnull ItemMeta itemMeta) {
        return PersistentDataAPI.getBoolean(itemMeta, KEY);
    }

    // https://www.spigotmc.org/threads/how-to-get-all-blocks-within-a-sphere.233306/
    public static List<Location> getPossibleLocations(@Nonnull Location spongeLocation, int range) {
        final List<Location> circleBlocks = new ArrayList<>();

        int bx = spongeLocation.getBlockX();
        int by = spongeLocation.getBlockY();
        int bz = spongeLocation.getBlockZ();

        for(int x = bx - range; x <= bx + range; x++) {
            for(int y = by - range; y <= by + range; y++) {
                for(int z = bz - range; z <= bz + range; z++) {
                    final double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));
                    if(distance < range * range) {
                        final Location l = new Location(spongeLocation.getWorld(), x, y, z);
                        circleBlocks.add(l);
                    }
                }
            }
        }
        return circleBlocks;
    }
}
