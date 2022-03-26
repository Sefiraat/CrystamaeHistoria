package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class ImbuedStand extends SlimefunItem {

    public static final NamespacedKey KEY = Keys.newKey("imbued_stand");

    public ImbuedStand(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(onItemUse());
    }

    private ItemUseHandler onItemUse() {
        return e -> {
            final Optional<Block> optionalBlock = e.getClickedBlock();

            if (optionalBlock.isPresent()) {
                final Block block = optionalBlock.get();
                final Location location = block.getRelative(e.getClickedFace()).getLocation().add(0.5, 0.5, 0.5);
                final Entity entity = location.getWorld().spawnEntity(
                    location,
                    EntityType.ARMOR_STAND,
                    CreatureSpawnEvent.SpawnReason.CUSTOM
                );

                PersistentDataAPI.setBoolean(entity, KEY, true);
                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    e.getItem().setAmount(e.getItem().getAmount() - 1);
                }
            }
        };
    }
}
