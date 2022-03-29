package io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets;

import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Light;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlowingTicker extends SlimefunItem {

    private final Map<Location, TickState> tickMap = new HashMap<>();
    private final int requiredTicks;

    @ParametersAreNonnullByDefault
    public GlowingTicker(ItemGroup category,
                         SlimefunItemStack item,
                         RecipeType recipeType,
                         ItemStack[] recipe,
                         int tickFrequency
    ) {
        super(category, item, recipeType, recipe);
        this.requiredTicks = tickFrequency;
    }

    @Override
    public void preRegister() {
        addItemHandler(
            onTick(),
            onBreak()
        );
    }

    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
                final Block block = event.getBlock();
                final Location location = block.getLocation();
                final Block blockAbove = block.getRelative(BlockFace.UP);
                final Material material = block.getType();
                final Material materialAbove = blockAbove.getType();

                if (materialAbove == Material.LIGHT) {
                    blockAbove.setType(Material.AIR);
                }

                final ItemStack itemToDrop = GlowingTicker.this.getItem().clone();
                final ItemMeta itemMeta = itemToDrop.getItemMeta();

                itemToDrop.setType(material);
                itemMeta.setDisplayName(ThemeType.GADGET.getColor() + "Glowing " + ThemeType.toTitleCase(material.name()));
                itemToDrop.setItemMeta(itemMeta);
                location.getWorld().dropItem(location.clone().add(0.5, 0.5, 0.5), itemToDrop);
                event.setDropItems(false);
            }
        };
    }

    private BlockTicker onTick() {
        return new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                if (block.isEmpty()) {
                    tickMap.remove(block.getLocation());
                    BlockStorage.clearBlockInfo(block.getLocation());
                    return;
                }

                final Block blockAbove = block.getRelative(BlockFace.UP);
                final Material material = blockAbove.getType();

                if (material == Material.AIR) {
                    blockAbove.setType(Material.LIGHT);
                    return;
                }

                if (material == Material.LIGHT) {
                    final TickState tickState = tickMap.getOrDefault(block.getLocation(), new TickState());

                    if (tickState.tick(GlowingTicker.this.requiredTicks)) {
                        final Light light = (Light) blockAbove.getBlockData();
                        if (tickState.isIncreasing()) {
                            light.setLevel(Math.min(light.getLevel() + 1, light.getMaximumLevel()));
                            if (light.getLevel() == light.getMaximumLevel()) {
                                tickState.toggleDirection();
                            }
                        } else {
                            light.setLevel(Math.max(light.getLevel() - 1, 0));
                            if (light.getLevel() == 0) {
                                tickState.toggleDirection();
                            }
                        }
                        blockAbove.setBlockData(light, false);
                    }
                    tickMap.put(block.getLocation(), tickState);
                }
            }
        };
    }

    private static class TickState {
        private int ticks = 0;
        private boolean increasing = false;

        public int getTicks() {
            return ticks;
        }

        public void setTicks(int ticks) {
            this.ticks = ticks;
        }

        public boolean tick(int requiredTicks) {
            if (this.ticks >= requiredTicks) {
                this.ticks = 0;
                return true;
            } else {
                this.ticks++;
                return false;
            }
        }

        public boolean isIncreasing() {
            return increasing;
        }

        public void setIncreasing(boolean increasing) {
            this.increasing = increasing;
        }

        public void toggleDirection() {
            this.increasing = !this.increasing;
        }
    }

}
