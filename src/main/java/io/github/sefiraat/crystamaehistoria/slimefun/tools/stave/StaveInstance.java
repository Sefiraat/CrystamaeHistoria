package io.github.sefiraat.crystamaehistoria.slimefun.tools.stave;

import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;

public class StaveInstance {

    private ItemStack itemStack;
    private Stave stave;
    private final EnumMap<SpellSlot, PlateStorage> spellInstanceMap = new EnumMap<>(SpellSlot.class);

    @ParametersAreNonnullByDefault
    public StaveInstance(Stave stave, ItemStack itemStack) {
        this.stave = stave;
        this.itemStack = itemStack;
    }




}
