package io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExaltedWeather extends ExaltedItem {

    private final WeatherType weatherType;

    public ExaltedWeather(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, WeatherType weatherType) {
        super(itemGroup, item, recipeType, recipe);
        this.weatherType = weatherType;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (Player player : location.getNearbyEntitiesByType(Player.class, 25, 25)) {
            player.setPlayerWeather(this.weatherType);
            CrystamaeHistoria.getSpellMemory().getPlayersWithFrozenWeather().put(player.getUniqueId(), System.currentTimeMillis() + 2000L);
        }
    }
}
