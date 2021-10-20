package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.DummyLiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class Spells {

    private final CrystamaeHistoria plugin;

    @ParametersAreNonnullByDefault
    public Spells(CrystamaeHistoria p) {
        this.plugin = p;
    }

    public void setup() {
        for (SpellType spellType : SpellType.getCachedValues()) {
            getSlimefunItem(spellType).register(plugin);
        }
    }

    private SlimefunItem getSlimefunItem(SpellType spellType) {
        final Materials materials = CrystamaeHistoria.getStructure().getMaterials();
        final ItemStack one = materials.getTypeItemMap().get(spellType.getRecipeStory(0)).getItem();
        final ItemStack two = materials.getTypeItemMap().get(spellType.getRecipeStory(1)).getItem();
        final ItemStack three = materials.getTypeItemMap().get(spellType.getRecipeStory(2)).getItem();

        return new SlimefunItem(
            ItemGroups.SPELLS,
            spellType.getSpell().getThemedStack(),
            DummyLiquefactionBasin.TYPE,
            new ItemStack[]{
                null, null, null, one, two, three, null, null, null
            }
        );
    }

}
