package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Collections;

public class GlassOfMilk extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public GlassOfMilk(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler((BlockUseHandler) this::onRightClick);
    }

    public void onRightClick(PlayerRightClickEvent e) {
        final Player player = e.getPlayer();
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    @NotNull
    @Override
    public Collection<ItemStack> getDrops() {
        return Collections.emptyList();
    }
}
