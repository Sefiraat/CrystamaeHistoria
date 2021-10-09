package io.github.sefiraat.crystamaehistoria.slimefun.categories;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemGroup {

    public final NestedItemGroup main = new NestedItemGroup(CrystamaeHistoria.getKeys().getCatMain(), new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getChatColor() + "Crystamae Historia"
    ));
    public final SubItemGroup mechanisms = new SubItemGroup(CrystamaeHistoria.getKeys().getCatMechanical(), main, new CustomItemStack(
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeType.MAIN.getChatColor() + "Historia Mechanisms"
    ));
    public final SubItemGroup crystals = new SubItemGroup(CrystamaeHistoria.getKeys().getCatCrystal(), main, new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getChatColor() + "Historia Crystals"
    ));
    public final SubItemGroup tools = new SubItemGroup(CrystamaeHistoria.getKeys().getCatTools(), main, new CustomItemStack(
            new ItemStack(Material.STICK),
            ThemeType.MAIN.getChatColor() + "Historia Staves and Tools"
    ));
    private final CrystamaeHistoria plugin;

    @ParametersAreNonnullByDefault
    public ItemGroup(CrystamaeHistoria plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        main.register(plugin);
        mechanisms.register(plugin);
        crystals.register(plugin);
    }

}
