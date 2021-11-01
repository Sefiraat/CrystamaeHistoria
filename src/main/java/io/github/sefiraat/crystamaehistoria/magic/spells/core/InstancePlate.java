package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Getter
public class InstancePlate {

    private final int tier;
    private final SpellType storedSpell;
    @Setter
    private int crysta;
    @Setter
    private long cooldown = 0;

    public InstancePlate(int tier, SpellType storedSpell, int crysta) {
        this.tier = tier;
        this.storedSpell = storedSpell;
        this.crysta = crysta;
    }

    public static void setPlateLore(ItemStack itemStack, InstancePlate instancePlate) {

        String magic = instancePlate != null ? ThemeType.toTitleCase(instancePlate.storedSpell.getId()) : "None";
        String crysta = instancePlate != null ? String.valueOf(instancePlate.crysta) : "0";

        final String[] lore = new String[]{
            "A magically charged plate storing magic",
            "potential.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Magic Framework : " + ThemeType.NOTICE.getColor() + magic,
            ThemeType.CLICK_INFO.getColor() + "Stored Crysta : " + ThemeType.NOTICE.getColor() + crysta
        };
        ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();

        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, ThemeType.CRAFTING.getLoreLine()));

        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(finalLore);
        itemStack.setItemMeta(itemMeta);
    }

    public CastResult tryCastSpell(CastInformation castInformation) {
        castInformation.setSpellType(storedSpell);
        Spell spell = storedSpell.getSpell();
        int crystaCost = spell.getCrystaCost(castInformation);
        if (crysta >= crystaCost) {
            if (cooldown <= System.currentTimeMillis()) {
                spell.castSpell(castInformation);
                this.crysta -= crystaCost;
                final long cdSeconds = (long) (spell.getCooldownSeconds(castInformation) * 1000);
                CrystamaeHistoria.log(Level.WARNING, String.valueOf(cdSeconds));
                this.cooldown = System.currentTimeMillis() + cdSeconds;
                return CastResult.CAST_SUCCESS;
            } else {
                return CastResult.ON_COOLDOWN;
            }
        } else {
            return CastResult.CAST_FAIL_NO_CRYSTA;
        }
    }

    public void addCrysta(int amount) {
        this.crysta += amount;
    }
}
