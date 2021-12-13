package io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ExaltedFertilityPharo extends ExaltedItem {

    public ExaltedFertilityPharo(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        final List<Animals> animals = location.getNearbyEntitiesByType(Animals.class, 10, 10).stream().toList();
        if (!animals.isEmpty()) {
            final int rnd = ThreadLocalRandom.current().nextInt(0, animals.size());
            animals.get(rnd).setLoveModeTicks(100);
        }
    }
}
