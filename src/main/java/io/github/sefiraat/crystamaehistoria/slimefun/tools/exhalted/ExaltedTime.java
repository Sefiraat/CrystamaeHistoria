package io.github.sefiraat.crystamaehistoria.slimefun.tools.exhalted;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExaltedTime extends ExaltedItem {

    private final int time;

    public ExaltedTime(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int time) {
        super(itemGroup, item, recipeType, recipe);
        this.time = time;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (Player player : location.getNearbyEntitiesByType(Player.class, 25, 25)) {
            player.setPlayerTime(this.time, false);
            CrystamaeHistoria.getSpellMemory().getPlayersWithFrozenTime().put(player.getUniqueId(), System.currentTimeMillis() + 2000L);
        }
    }
}
