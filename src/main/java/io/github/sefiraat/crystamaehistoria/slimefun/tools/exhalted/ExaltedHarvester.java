package io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted;

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

    public ExaltedHarvester(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (int i = 0; i < 5; i++) {
            final int x = ThreadLocalRandom.current().nextInt(-4, 5);
            final int z = ThreadLocalRandom.current().nextInt(-4, 5);
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
