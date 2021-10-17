package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public final class ItemGroups {

    public static final NestedItemGroup MAIN = new NestedItemGroup(Keys.CAT_MAIN, new CustomItemStack(
        new ItemStack(Material.AMETHYST_CLUSTER),
        ThemeType.MAIN.getColor() + "Crystamae Historia"
    ));
    public static final SubItemGroup MECHANISMS = new SubItemGroup(Keys.CAT_MECHANICAL, MAIN, new CustomItemStack(
        new ItemStack(Material.DEEPSLATE_TILE_SLAB),
        ThemeType.MAIN.getColor() + "Historia Mechanisms"
    ));
    public static final SubItemGroup CRYSTALS = new SubItemGroup(Keys.CAT_CRYSTAL, MAIN, new CustomItemStack(
        new ItemStack(Material.AMETHYST_CLUSTER),
        ThemeType.MAIN.getColor() + "Historia Crystals"
    ));
    public static final SubItemGroup TOOLS = new SubItemGroup(Keys.CAT_TOOLS, MAIN, new CustomItemStack(
        new ItemStack(Material.STICK),
        ThemeType.MAIN.getColor() + "Historia Staves and Tools"
    ));
    public static final SubItemGroup SPELLS = new SubItemGroup(Keys.CAT_TOOLS, MAIN, new CustomItemStack(
        new ItemStack(Material.FIRE_CHARGE),
        ThemeType.MAIN.getColor() + "Magic Spells"
    ));

    public static final DummyItemGroup DUMMY_ITEM_GROUP = new DummyItemGroup(Keys.CAT_TOOLS, new CustomItemStack(
        new ItemStack(Material.FIRE_CHARGE),
        ThemeType.MAIN.getColor() + "Dummy Crystamae Historia"
    ));
}
