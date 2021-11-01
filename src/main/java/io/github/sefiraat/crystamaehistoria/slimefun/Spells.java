package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin.DummyLiquefactionBasinSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

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
        final Map<StoryType, SlimefunItem> map = Materials.DUMMY_CRYSTAL_MAP;

        final StoryType type1 = spellType.getSpell().getRecipe().getInput(0);
        final StoryType type2 = spellType.getSpell().getRecipe().getInput(1);
        final StoryType type3 = spellType.getSpell().getRecipe().getInput(2);

        final ItemStack stack1 = map.get(type1).getItem();
        final ItemStack stack2 = map.get(type2).getItem();
        final ItemStack stack3 = map.get(type3).getItem();

        return new SlimefunItem(
            ItemGroups.SPELLS,
            spellType.getSpell().getThemedStack(),
            DummyLiquefactionBasinSpell.TYPE,
            new ItemStack[]{
                null, null, null,
                stack1, stack2, stack3,
                null, null, null
            }
        );
    }

}
