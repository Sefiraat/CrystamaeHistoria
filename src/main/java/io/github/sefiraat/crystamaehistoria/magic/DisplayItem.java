package io.github.sefiraat.crystamaehistoria.magic;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@Getter
public class DisplayItem {

    private final Item item;
    private final Consumer<Item> onRemove;

    @ParametersAreNonnullByDefault
    public DisplayItem(ItemStack itemStack, Location location, @Nullable String name, @Nullable Consumer<Item> onRemove) {
        this.item = GeneralUtils.spawnDisplayItem(itemStack, location, name);
        this.onRemove = onRemove;
    }

    public void registerRemoval(long duration) {
        CrystamaeHistoria.getSpellMemory().getDisplayItems().put(this, System.currentTimeMillis() + duration);
    }

    @ParametersAreNonnullByDefault
    public void setVelocity(Vector vector) {
        this.item.setGravity(true);
        this.item.setVelocity(vector);
    }

    public void kill() {
        if (this.item.isValid()) {
            if (this.onRemove != null) {
                this.onRemove.accept(this.item);
            }
            this.item.remove();
        }
    }

}
