package io.github.sefiraat.crystamaehistoria.slimefun.categories;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemGroup {

    public final NestedItemGroup main = new NestedItemGroup(CrystamaeHistoria.getKeyHolder().getCatMain(), new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeUtils.getThemeColor(ThemeType.MAIN) + "Crystamae Historia"
    ));
    public final SubItemGroup mechanisms = new SubItemGroup(CrystamaeHistoria.getKeyHolder().getCatMechanical(), main, new CustomItemStack(
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeUtils.getThemeColor(ThemeType.MAIN) + "Historia Mechanisms"
    ));
    public final SubItemGroup crystals = new SubItemGroup(CrystamaeHistoria.getKeyHolder().getCatCrystal(), main, new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeUtils.getThemeColor(ThemeType.MAIN) + "Historia Crystals"
    ));
    public final SubItemGroup tools = new SubItemGroup(CrystamaeHistoria.getKeyHolder().getCatTools(), main, new CustomItemStack(
            new ItemStack(Material.STICK),
            ThemeUtils.getThemeColor(ThemeType.MAIN) + "Historia Staves and Tools"
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
