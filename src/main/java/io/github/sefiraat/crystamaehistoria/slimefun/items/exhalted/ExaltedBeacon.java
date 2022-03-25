package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class ExaltedBeacon extends ExaltedItem {

    private static final Set<PotionEffectType> EFFECT_TYPES = new HashSet<>();

    static {
        EFFECT_TYPES.add(PotionEffectType.SPEED);
        EFFECT_TYPES.add(PotionEffectType.ABSORPTION);
        EFFECT_TYPES.add(PotionEffectType.FAST_DIGGING);
        EFFECT_TYPES.add(PotionEffectType.SATURATION);
        EFFECT_TYPES.add(PotionEffectType.DAMAGE_RESISTANCE);
        EFFECT_TYPES.add(PotionEffectType.REGENERATION);
    }

    private final int amplification;

    public ExaltedBeacon(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int amplification) {
        super(itemGroup, item, recipeType, recipe);
        this.amplification = amplification;
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (Player player : location.getNearbyEntitiesByType(Player.class, 25, 25)) {
            for (PotionEffectType effectType : EFFECT_TYPES) {
                player.addPotionEffect(effectType.createEffect(40, amplification));
            }
        }
    }
}
