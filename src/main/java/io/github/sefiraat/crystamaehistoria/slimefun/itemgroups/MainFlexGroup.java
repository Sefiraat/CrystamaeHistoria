package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * @noinspection deprecation
 */
public class MainFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;

    private static final int MECHANISMS = 9;
    private static final int CRYSTALS = 10;
    private static final int TOOLS = 11;
    private static final int GADGETS = 12;
    private static final int PAINTBRUSHES = 13;
    private static final int MATERIALS = 14;
    private static final int GUIDE = 15;
    private static final int STORY = 16;
    private static final int SPELL = 17;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    public MainFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    public boolean isVisible(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(ThemeType.MAIN.getColor() + "Crystamae Magic Compendium");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        chestMenu.setEmptySlotsClickable(false);
        setupPage(p, profile, mode, chestMenu);
        chestMenu.open(p);
    }


    private void setupPage(@Nonnull Player player, @Nonnull PlayerProfile profile, @Nonnull SlimefunGuideMode mode, @Nonnull ChestMenu menu) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ((player1, i, itemStack, clickAction) -> false));
        }

        // Back
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(player, Slimefun.getLocalization().getMessage("guide.back.guide")));
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openMainMenu(profile, mode, 1);
            return false;
        });

        // Mechanisms
        menu.replaceExistingItem(MECHANISMS, ItemGroups.MECHANISMS.getItem(player));
        menu.addMenuClickHandler(MECHANISMS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.MECHANISMS, mode, 1));

        // Crystals
        menu.replaceExistingItem(CRYSTALS, ItemGroups.CRYSTALS.getItem(player));
        menu.addMenuClickHandler(CRYSTALS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.CRYSTALS, mode, 1));

        // Tools
        menu.replaceExistingItem(TOOLS, ItemGroups.TOOLS.getItem(player));
        menu.addMenuClickHandler(TOOLS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.TOOLS, mode, 1));

        // Gadgets
        menu.replaceExistingItem(GADGETS, ItemGroups.GADGETS.getItem(player));
        menu.addMenuClickHandler(GADGETS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.GADGETS, mode, 1));

        // Paint Brushes
        menu.replaceExistingItem(PAINTBRUSHES, ItemGroups.PAINTBRUSHES.getItem(player));
        menu.addMenuClickHandler(PAINTBRUSHES, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.PAINTBRUSHES, mode, 1));

        // Materials
        menu.replaceExistingItem(MATERIALS, ItemGroups.MATERIALS.getItem(player));
        menu.addMenuClickHandler(MATERIALS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.MATERIALS, mode, 1));

        // Story
        menu.replaceExistingItem(STORY, ItemGroups.STORY_COLLECTION.getItem(player));
        menu.addMenuClickHandler(STORY, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.STORY_COLLECTION, mode, 1));

        // Spell
        menu.replaceExistingItem(SPELL, ItemGroups.SPELL_COLLECTION.getItem(player));
        menu.addMenuClickHandler(SPELL, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.SPELL_COLLECTION, mode, 1));

        // Guide
        menu.replaceExistingItem(GUIDE, ItemGroups.GUIDE.getItem(player));
        menu.addMenuClickHandler(GUIDE, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.GUIDE, mode, 1));
    }

    public boolean openPage(PlayerProfile profile, ItemGroup itemGroup, SlimefunGuideMode mode, int page) {
        profile.getGuideHistory().add(this, 1);
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, page);
        return false;
    }
}
