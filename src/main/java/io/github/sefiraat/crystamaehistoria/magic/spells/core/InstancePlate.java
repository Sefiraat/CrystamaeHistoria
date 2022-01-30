package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@Getter
public class InstancePlate {

    private final int tier;
    private final SpellType storedSpell;
    @Setter
    private int crysta;
    @Setter
    private long cooldown = 0;

    @ParametersAreNonnullByDefault
    public InstancePlate(int tier, SpellType storedSpell, int crysta) {
        this.tier = tier;
        this.storedSpell = storedSpell;
        this.crysta = crysta;
    }

    @ParametersAreNonnullByDefault
    public static void setPlateLore(ItemStack itemStack, @Nullable InstancePlate instancePlate) {
        final String magic = instancePlate != null ? ThemeType.toTitleCase(instancePlate.storedSpell.getId()) : "None";
        final String crysta = instancePlate != null ? String.valueOf(instancePlate.crysta) : "0";
        final String[] lore = new String[]{
            "A magically charged plate storing magic",
            "potential.",
            "",
            ThemeType.CLICK_INFO.getColor() + "Magic Framework : " + ThemeType.NOTICE.getColor() + magic,
            ThemeType.CLICK_INFO.getColor() + "Stored Crysta : " + ThemeType.NOTICE.getColor() + crysta
        };
        final ChatColor passiveColor = ThemeType.PASSIVE.getColor();
        final List<String> finalLore = new ArrayList<>();
        final ItemMeta itemMeta = itemStack.getItemMeta();

        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(ThemeType.applyThemeToString(ThemeType.CLICK_INFO, ThemeType.CRAFTING.getLoreLine()));

        itemMeta.setLore(finalLore);
        itemStack.setItemMeta(itemMeta);
    }

    @ParametersAreNonnullByDefault
    public CastResult tryCastSpell(CastInformation castInformation) {
        final Spell spell = storedSpell.getSpell();
        final int crystaCost = spell.getCrystaCost(castInformation);

        // Is the spell disabled in spells.yml?
        if (!CrystamaeHistoria.getConfigManager().spellEnabled(spell)) {
            return CastResult.SPELL_DISABLED;
        }

        // Is enough crysta currently stored in the plate?
        if (crysta < crystaCost) {
            return CastResult.CAST_FAIL_NO_CRYSTA;
        }

        // Is the spell still on cooldown?
        if (cooldown > System.currentTimeMillis()) {
            return CastResult.ON_COOLDOWN;
        }

        castInformation.setSpellType(storedSpell);
        spell.castSpell(castInformation);
        this.crysta -= crystaCost;
        final long cdSeconds = (long) (spell.getCooldownSeconds(castInformation) * 1000);
        this.cooldown = System.currentTimeMillis() + cdSeconds;
        PlayerStatistics.addUsage(castInformation.getCaster(), storedSpell);
        return CastResult.CAST_SUCCESS;
    }

    public void addCrysta(int amount) {
        this.crysta += amount;
    }

    public void removeCrysta(int amount) {
        this.crysta -= amount;
    }
}
