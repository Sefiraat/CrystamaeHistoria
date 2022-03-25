package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ExaltedFertilityPharo extends ExaltedItem {

    private final int range;

    public ExaltedFertilityPharo(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int range) {
        super(itemGroup, item, recipeType, recipe);
        this.range = range;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        final List<Animals> animals = location.getNearbyEntitiesByType(Animals.class, this.range, this.range).stream().toList();
        if (!animals.isEmpty()) {
            final int rnd = ThreadLocalRandom.current().nextInt(0, animals.size());
            animals.get(rnd).setLoveModeTicks(100);
        }
    }
}
