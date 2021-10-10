package io.github.sefiraat.crystamaehistoria.slimefun.categories;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemGroups {

    public static final NestedItemGroup MAIN = new NestedItemGroup(CrystamaeHistoria.getKeys().getCatMain(), new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getChatColor() + "Crystamae Historia"
    ));
    public static final SubItemGroup MECHANISMS = new SubItemGroup(CrystamaeHistoria.getKeys().getCatMechanical(), MAIN, new CustomItemStack(
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeType.MAIN.getChatColor() + "Historia Mechanisms"
    ));
    public static final SubItemGroup CRYSTALS = new SubItemGroup(CrystamaeHistoria.getKeys().getCatCrystal(), MAIN, new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getChatColor() + "Historia Crystals"
    ));
    public static final SubItemGroup TOOLS = new SubItemGroup(CrystamaeHistoria.getKeys().getCatTools(), MAIN, new CustomItemStack(
            new ItemStack(Material.STICK),
            ThemeType.MAIN.getChatColor() + "Historia Staves and Tools"
    ));

    public static void setup() {
        MAIN.register(CrystamaeHistoria.inst());
        MECHANISMS.register(CrystamaeHistoria.inst());
        CRYSTALS.register(CrystamaeHistoria.inst());
        TOOLS.register(CrystamaeHistoria.inst());
    }

}
