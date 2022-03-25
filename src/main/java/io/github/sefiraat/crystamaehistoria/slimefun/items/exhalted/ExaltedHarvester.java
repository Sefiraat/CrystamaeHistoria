package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class ExaltedHarvester extends ExaltedItem {

    private final int range;

    public ExaltedHarvester(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(itemGroup, item, recipeType, recipe);
        this.range = range;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (int i = 0; i < this.range; i++) {
            final int x = ThreadLocalRandom.current().nextInt(-this.range, this.range + 1);
            final int z = ThreadLocalRandom.current().nextInt(-this.range, this.range + 1);
            Block block = location.add(x, -1.5, z).getBlock();
            if (block.getBlockData() instanceof Ageable) {
                Ageable ageable = (Ageable) block.getBlockData();
                Material material = block.getType();
                if (ageable.getAge() == ageable.getMaximumAge()) {
                    block.breakNaturally();
                    block.setType(material);
                }
            }
        }
    }
}
