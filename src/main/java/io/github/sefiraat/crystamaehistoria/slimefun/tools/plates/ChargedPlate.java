package io.github.sefiraat.crystamaehistoria.slimefun.tools.plates;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Getter
public class ChargedPlate extends UnplaceableBlock {

    private final int tier;

    @ParametersAreNonnullByDefault
    public ChargedPlate(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int tier) {
        super(itemGroup, item, recipeType, recipe);
        this.tier = tier;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack getChargedPlate(int tier, SpellType spellType, int crysta) {
        PlateStorage plateStorage = new PlateStorage(tier, spellType, crysta);
        return getChargedPlate(plateStorage);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack getChargedPlate(PlateStorage plateStorage) {
        ItemStack newPlate = Materials.CHARGED_PLATE_T_1.getItem().clone();
        PlateStorage.setPlateLore(newPlate, plateStorage);
        ItemMeta itemMeta = newPlate.getItemMeta();
        DataTypeMethods.setCustom(
            itemMeta,
            Keys.PDC_PLATE_STORAGE,
            PersistentPlateDataType.TYPE,
            plateStorage
        );
        newPlate.setItemMeta(itemMeta);
        return newPlate;
    }
}
