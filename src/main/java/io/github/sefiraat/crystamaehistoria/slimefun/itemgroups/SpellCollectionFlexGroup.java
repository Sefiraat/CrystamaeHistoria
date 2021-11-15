package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCore;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.player.SpellRank;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.slimefun.Materials;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @noinspection deprecation
 */
public class SpellCollectionFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;
    private static final int GUIDE_STATS = 7;

    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    private static final int SPELL = 19;
    private static final int[] RECIPE = new int[]{
        21, 22, 23
    };
    private static final int MECHANISM = 25;
    private static final int CRYSTA_COST = 37;
    private static final int CAST_TYPE = 38;
    private static final int VALUES = 39;
    private static final int RANGE = 40;
    private static final int KNOCK_BACK = 41;
    private static final int PROJECTILE_INFO = 42;
    private static final int EFFECTS = 43;

    public SpellCollectionFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    public boolean isVisible(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(ThemeType.MAIN.getColor() + "Spell Compendium");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        chestMenu.setEmptySlotsClickable(false);
        setupPage(p, profile, mode, chestMenu, 1);
        chestMenu.open(p);
    }

    private void setupPage(@Nonnull Player player, @Nonnull PlayerProfile profile, @Nonnull SlimefunGuideMode mode, @Nonnull ChestMenu menu, int page) {
        final List<SpellType> spellTypes = Arrays.asList(SpellType.getEnabledSpells());
        final int numberOfBlocks = spellTypes.size();
        final int totalPages = (int) Math.ceil(numberOfBlocks / (double) PAGE_SIZE);
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, spellTypes.size());

        spellTypes.sort(Comparator.comparing(spellType -> spellType.getSpell().getId()));

        final List<SpellType> spellTypeSubList = spellTypes.subList(start, end);

        reapplyFooter(player, profile, mode, menu, page, totalPages);

        // Back
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(player, Slimefun.getLocalization().getMessage("guide.back.guide")));
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(profile, ItemGroups.MAIN, mode, 1);
            return false;
        });

        // Stats
        menu.replaceExistingItem(GUIDE_STATS, getStatsStack(player));
        menu.addMenuClickHandler(GUIDE_STATS, (player1, slot, itemStack, clickAction) -> false);

        for (int i = 0; i < 36; i++) {
            final int slot = i + 9;

            if (i + 1 <= spellTypeSubList.size()) {
                final SpellType spellType = spellTypeSubList.get(i);
                final boolean researched = PlayerStatistics.hasUnlockedSpell(player, spellType);

                if (mode == SlimefunGuideMode.CHEAT_MODE || researched) {
                    menu.replaceExistingItem(slot, new ItemStack(spellType.getSpell().getThemedStack()));
                    menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> {
                        displayDefinition(player1, profile, mode, menu, page, spellType);
                        return false;
                    });
                } else {
                    menu.replaceExistingItem(slot, GuiElements.getSpellNotUnlockedIcon(spellType.getId()));
                    menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
                }
            } else {
                menu.replaceExistingItem(slot, null);
                menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
            }
        }
    }

    private void displayDefinition(@Nonnull Player p, @Nonnull PlayerProfile profile, @Nonnull SlimefunGuideMode mode, @Nonnull ChestMenu menu, int returnPage, @Nonnull SpellType spellType) {
        final Spell spell = spellType.getSpell();
        // Back Button
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(p, Slimefun.getLocalization().getMessage("guide.back.guide")));
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            setupPage(player1, profile, mode, menu, returnPage);
            return false;
        });

        clearDisplay(menu);

        menu.replaceExistingItem(SPELL, spellType.getSpell().getThemedStack());
        menu.addMenuClickHandler(SPELL, ((player, i, itemStack, clickAction) -> false));

        for (int i = 0; i < RECIPE.length; i++) {
            int slot = RECIPE[i];
            StoryType storyType = spellType.getSpell().getRecipe().getInput(i);
            ItemStack stack = Materials.getDummyCrystalMap().get(storyType).getItem();
            menu.replaceExistingItem(slot, stack);
            menu.addMenuClickHandler(slot, ((player, slot2, itemStack, clickAction) -> false));
        }

        menu.replaceExistingItem(MECHANISM, getMechanismStack());
        menu.addMenuClickHandler(MECHANISM, ((player, i, itemStack, clickAction) -> false));

        menu.replaceExistingItem(CRYSTA_COST, getBasicStack(spell));
        menu.addMenuClickHandler(CRYSTA_COST, ((player, i, itemStack, clickAction) -> false));

        menu.replaceExistingItem(VALUES, getValuesStack(spell));
        menu.addMenuClickHandler(VALUES, ((player, i, itemStack, clickAction) -> false));

        menu.replaceExistingItem(CAST_TYPE, getCastTypeStack(spell));
        menu.addMenuClickHandler(CAST_TYPE, ((player, i, itemStack, clickAction) -> false));

        menu.replaceExistingItem(RANGE, getRangeStack(spell));
        menu.addMenuClickHandler(RANGE, ((player, i, itemStack, clickAction) -> false));

        menu.replaceExistingItem(KNOCK_BACK, getKnockBackStack(spell));
        menu.addMenuClickHandler(KNOCK_BACK, ((player, i, itemStack, clickAction) -> false));

        menu.replaceExistingItem(PROJECTILE_INFO, getProjectileStack(spell));
        menu.addMenuClickHandler(PROJECTILE_INFO, ((player, i, itemStack, clickAction) -> false));

        menu.replaceExistingItem(EFFECTS, getEffectsStack(spell));
        menu.addMenuClickHandler(EFFECTS, ((player, i, itemStack, clickAction) -> false));

    }

    private void clearDisplay(@Nonnull ChestMenu menu) {
        for (int i = 0; i < 45; i++) {
            final int slot = i + 9;
            menu.replaceExistingItem(slot, null);
            menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
        }
    }

    private void reapplyFooter(@Nonnull Player p, @Nonnull PlayerProfile profile, SlimefunGuideMode mode, @Nonnull ChestMenu menu, int page, int totalPages) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ((player, i, itemStack, clickAction) -> false));
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_PREVIOUS, (player1, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage != page && previousPage >= 1) {
                setupPage(player1, profile, mode, menu, previousPage);
            }
            return false;
        });

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_NEXT, (player1, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage != page && nextPage <= totalPages) {
                setupPage(player1, profile, mode, menu, nextPage);
            }
            return false;
        });
    }

    private ItemStack getMechanismStack() {
        final List<String> lore = Arrays.stream(
            new String[]{
                "Spells are created by combining",
                "liquid crystamae in a Liquefaction",
                "Basin.",
                "The highest 3 amounts of liquid",
                "determine the spell created (left).",
                "",
                "Throw in a Spell Plate when ready",
                "to create your spell."
            }
        ).map(s -> ThemeType.PASSIVE.getColor() + s)
            .collect(Collectors.toList());

        return new CustomItemStack(
            Material.CAULDRON,
            ThemeType.MAIN.getColor() + "Liquefaction Basin",
            lore
        );
    }

    private ItemStack getBasicStack(Spell spell) {
        final SpellCore spellCore = spell.getSpellCore();
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();

        final String crysta = MessageFormat.format("{0}Crysta Cost per Cast: {1}{2}", color, passive, spellCore.getCrystaCost());
        final String crystaMulti = MessageFormat.format("{0}Crysta cost {1} with stave tier", color, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");
        final String cooldown = MessageFormat.format("{0}Cooldown (sec) on use: {1}{2}", color, passive, spell.getSpellCore().getCooldownSeconds());
        final String cooldownDivided = MessageFormat.format("{0}Cooldown {1} with stave tier", color, spellCore.isDamageMultiplied() ? "isn't reduced" : "is reduced");


        return new CustomItemStack(
            Material.GLOW_BERRIES,
            ThemeType.MAIN.getColor() + "Basic Details",
            crysta,
            crystaMulti,
            cooldown,
            cooldownDivided
        );
    }

    private ItemStack getValuesStack(Spell spell) {
        final SpellCore spellCore = spell.getSpellCore();
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();
        final List<String> lore = new ArrayList<>();

        final String damageMessage = MessageFormat.format("{0}Damage: {1}{2}", color, passive, spellCore.getDamageAmount());
        final String damageMulti = MessageFormat.format("{0}Damage {1} with stave tier", color, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");

        final String healMessage = MessageFormat.format("{0}Heal Amount: {1}{2}", color, passive, spellCore.getDamageAmount());
        final String healMulti = MessageFormat.format("{0}Heal Amount {1} with stave tier", color, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");

        if (spellCore.isDamagingSpell()) {
            lore.add(damageMessage);
            lore.add(damageMulti);
        } else {
            lore.add(passive + "This spell does not damage.");
        }

        if (spellCore.isHealingSpell()) {
            lore.add(healMessage);
            lore.add(healMulti);
        } else {
            lore.add(passive + "This spell does not heal.");
        }

        return new CustomItemStack(
            Material.MAP,
            ThemeType.MAIN.getColor() + "Spell Values",
            lore
        );
    }

    private ItemStack getCastTypeStack(Spell spell) {
        final SpellCore spellCore = spell.getSpellCore();
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();
        final ChatColor notice = ThemeType.NOTICE.getColor();
        final List<String> lore = new ArrayList<>();

        final int ticks = spellCore.getNumberOfTicks();

        final String instantCast = MessageFormat.format("{0}Instant: {1}Fires immediately when cast", color, passive);
        final String damagingSpell = MessageFormat.format("{0}Damaging: {1}Will cause damage and/or debuff", color, passive);
        final String healingSpell = MessageFormat.format("{0}Healing: {1}Will heal and/or buff", color, passive);
        final String effectingSpell = MessageFormat.format("{0}Effecting: {1}Will apply potion effects", color, passive);
        final String tickingSpell1 = MessageFormat.format("{0}Ticking: {1}Effects of this spell will", color, passive);
        final String tickingSpell2 = MessageFormat.format("{0}fire ({1}{2}{3}) times", passive, notice, ticks, passive);
        final String tickingSpell3 = MessageFormat.format("{0}Tick number {1} with stave tier", passive, spellCore.isDamageMultiplied() ? "increases" : "doesn't increase");

        final String projectileSpell1 = MessageFormat.format("{0}Projectile: {1}This spell fires a projectile", color, passive);
        final String projectileSpell2 = MessageFormat.format("{0}which, when it hits, may induce", passive);
        final String projectileSpell3 = MessageFormat.format("{0}further effects", passive);

        if (spellCore.isInstantCast()) {
            lore.add(instantCast);
        }
        if (spellCore.isDamagingSpell()) {
            lore.add(damagingSpell);
        }
        if (spellCore.isHealingSpell()) {
            lore.add(healingSpell);
        }
        if (spellCore.isEffectingSpell()) {
            lore.add(effectingSpell);
        }
        if (spellCore.isTickingSpell()) {
            lore.add(tickingSpell1);
            lore.add(tickingSpell2);
            lore.add(tickingSpell3);
        }
        if (spellCore.isProjectileSpell()) {
            lore.add(projectileSpell1);
            lore.add(projectileSpell2);
            lore.add(projectileSpell3);
        }
        return new CustomItemStack(
            Material.NAME_TAG,
            ThemeType.MAIN.getColor() + "Spell Cast Type(s)",
            lore
        );
    }

    private ItemStack getRangeStack(Spell spell) {
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();
        final List<String> lore = new ArrayList<>();

        final String message = MessageFormat.format("{0}Range: {1}{2}", color, passive, spell.getSpellCore().getRange());
        final String multiMessage = MessageFormat.format("{0}Range {1} with stave tier", passive, spell.getSpellCore().isRangeMultiplied() ? "increases" : "doesn't increase");
        final String noRange = passive + "Not effected by range";

        if (spell.getSpellCore().getKnockbackAmount() > 0) {
            lore.add(message);
            lore.add(multiMessage);
        } else {
            lore.add(noRange);
        }

        return new CustomItemStack(
            Material.TARGET,
            ThemeType.MAIN.getColor() + "Range",
            lore
        );
    }

    private ItemStack getKnockBackStack(Spell spell) {
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();
        final List<String> lore = new ArrayList<>();

        final String message = MessageFormat.format("{0}Knockback: {1}{2}", color, passive, spell.getSpellCore().getKnockbackAmount());
        final String multiMessage = MessageFormat.format("{0}Amount {1} with stave tier", passive, spell.getSpellCore().isKnockbackMultiplied() ? "increases" : "doesn't increase");
        final String noKnockback = passive + "No direct knockback";

        if (spell.getSpellCore().getKnockbackAmount() > 0) {
            lore.add(message);
            lore.add(multiMessage);
        } else {
            lore.add(noKnockback);
        }

        return new CustomItemStack(
            Material.SLIME_BLOCK,
            ThemeType.MAIN.getColor() + "Knockback",
            lore
        );
    }

    private ItemStack getProjectileStack(Spell spell) {
        final SpellCore spellCore = spell.getSpellCore();
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();

        final String aoeMessage = MessageFormat.format("{0}Projectile AoE: {1}{2}", color, passive, spellCore.getProjectileAoeRange());
        final String aoeMultiMessage = MessageFormat.format("{0}AoE {1} with stave tier", passive, spellCore.isProjectileAoeMultiplied() ? "increases" : "doesn't increase");
        final String knockbackMessage = MessageFormat.format("{0}Projectile Knockback: {1}{2}", color, passive, spellCore.getKnockbackAmount());
        final String knockbackMultiMessage = MessageFormat.format("{0}Knockback {1} with stave tier", passive, spellCore.isKnockbackMultiplied() ? "increases" : "doesn't increase");

        final List<String> lore = new ArrayList<>();

        if (spellCore.isProjectileSpell()) {
            lore.add(aoeMessage);
            lore.add(aoeMultiMessage);
            lore.add("");
            lore.add(knockbackMessage);
            lore.add(knockbackMultiMessage);
        } else {
            lore.add(passive + "Not a projectile spell");
        }

        return new CustomItemStack(
            Material.FIRE_CHARGE,
            ThemeType.MAIN.getColor() + "Projectile Information",
            lore
        );
    }

    private ItemStack getEffectsStack(Spell spell) {
        final SpellCore spellCore = spell.getSpellCore();
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();
        final List<String> lore = new ArrayList<>();

        if (spellCore.isEffectingSpell()) {
            final String effectAmplification = MessageFormat.format(
                "{0}Effect power {1} with stave tier",
                passive,
                spellCore.isAmplificationMultiplied() ? "increases" : "doesn't increase"
            );
            final String effectDuration = MessageFormat.format(
                "{0}Effect duration {1} with stave tier",
                passive,
                spellCore.isEffectDurationMultiplied() ? "increases" : "doesn't increase"
            );

            if (spellCore.getNegativeEffectPairMap().size() > 0) {
                lore.add(color + "Negative Effects:");
                spellCore.getNegativeEffectPairMap().forEach(
                    (type, pair) -> {
                        final String negativeEffectMessage = MessageFormat.format(
                            "{0}{1}: {2}Power ({3}) - Duration ({4})",
                            color,
                            ThemeType.toTitleCase(type.getName()),
                            passive,
                            pair.getFirstValue(),
                            pair.getSecondValue()
                        );
                        lore.add(negativeEffectMessage);
                    }
                );
            }

            if (spellCore.getPositiveEffectPairMap().size() > 0) {
                lore.add(color + "Positive Effects:");
                spellCore.getPositiveEffectPairMap().forEach(
                    (type, pair) -> {
                        final String positiveEffectMessage = MessageFormat.format(
                            "{0}{1}: {2}Power ({3}) - Duration ({4})",
                            color,
                            ThemeType.toTitleCase(type.getName()),
                            passive,
                            pair.getFirstValue(),
                            pair.getSecondValue()
                        );
                        lore.add(positiveEffectMessage);
                    }
                );
            }

            lore.add("");
            lore.add(effectAmplification);
            lore.add(effectDuration);
        } else {
            lore.add(passive + "Spell has no effects");
        }

        return new CustomItemStack(
            Material.BREWING_STAND,
            ThemeType.MAIN.getColor() + "Effects",
            lore
        );
    }

    private ItemStack getStatsStack(Player player) {
        final ChatColor color = ThemeType.CLICK_INFO.getColor();
        final ChatColor passive = ThemeType.PASSIVE.getColor();
        final List<String> lore = new ArrayList<>();
        final SpellRank spellRank = PlayerStatistics.getSpellRank(player.getUniqueId());

        lore.add(MessageFormat.format("{0}Spells Unlocked: {1}{2}", color, passive, PlayerStatistics.getSpellsUnlocked(player.getUniqueId())));
        lore.add(MessageFormat.format("{0}Rank: {1}{2}", color, spellRank.getTheme().getColor(), spellRank.getTheme().getLoreLine()));

        return new CustomItemStack(
            Material.TARGET,
            ThemeType.MAIN.getColor() + "Spell Statistics",
            lore
        );
    }
}
