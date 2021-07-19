package io.github.sefiraat.crystamaehistoria.slimefun.categories;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun4.core.categories.MultiCategory;
import io.github.thebusybiscuit.slimefun4.core.categories.SubCategory;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Categories {

    private final CrystamaeHistoria plugin;

    public final MultiCategory MAIN = new MultiCategory(CrystamaeHistoria.inst().getKeyHolder().getCatMain(), new CustomItem(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeUtils.getThemeColor(ThemeType.MAIN) + "Crystamae Historia"
    ));
    public final SubCategory MECHANISMS_T1 = new SubCategory(CrystamaeHistoria.inst().getKeyHolder().getCatMech1(), MAIN, new CustomItem(
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeUtils.getThemeColor(ThemeType.MAIN) + "Historia Mechanisms (Stage 1)"
    ));

    public Categories(CrystamaeHistoria plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        MAIN.register(plugin);
        MECHANISMS_T1.register(plugin);
    }

}
