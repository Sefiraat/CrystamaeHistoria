package io.github.sefiraat.crystamaehistoria.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@UtilityClass
public class GildingUtils {

    /**
     * Returns true if the has been gilded.
     *
     * @param itemStack The {@link ItemStack} to check
     * @return true if has previously been gilded at any point
     */
    @ParametersAreNonnullByDefault
    public static boolean isGilded(ItemStack itemStack) {
        return PersistentDataAPI.hasBoolean(itemStack.getItemMeta(), Keys.PDC_IS_GILDED);
    }

    /**
     * Sets the ItemStack's PDC Gilded to True. Also sets an initial story object
     *
     * @param itemStack The {@link ItemStack} whose meta will have the PDC element added to
     */
    @ParametersAreNonnullByDefault
    public static void makeGilded(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataAPI.setBoolean(itemMeta, Keys.PDC_IS_GILDED, true);
        List<String> lore = itemStack.getLore();
        lore.add("");
        lore.add("" + ChatColor.YELLOW + ChatColor.BOLD + "GILDED");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

}
