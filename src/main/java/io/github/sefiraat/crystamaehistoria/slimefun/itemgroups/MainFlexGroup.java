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
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @noinspection deprecation
 */
public class MainFlexGroup extends FlexItemGroup {

    private static final ItemStack DOCS_ITEM_STACK = ThemeType.themedItemStack(
        Material.BOOK,
        ThemeType.GUIDE,
        "Documentation Wiki",
        "Click to get the link to the",
        "documentation Wiki for Crystamae",
        "and other Sefiraat addons."
    );

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;

    private static final int DOCS = 9;
    private static final int MECHANISMS = 10;
    private static final int CRYSTALS = 11;
    private static final int TOOLS = 12;
    private static final int GADGETS = 13;
    private static final int PAINTBRUSHES = 14;
    private static final int EXALTED = 15;
    private static final int UNIQUES = 16;
    private static final int RUNES = 17;
    private static final int MATERIALS = 18;
    private static final int GUIDE = 19;
    private static final int STORY = 20;
    private static final int SPELL = 21;
    private static final int GILDING = 22;

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
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
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

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ((player1, i, itemStack, clickAction) -> false));
        }

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                player,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openMainMenu(profile, mode, 1);
            return false;
        });

        // Docs
        menu.replaceExistingItem(DOCS, DOCS_ITEM_STACK);
        menu.addMenuClickHandler(DOCS, (player1, i1, itemStack1, clickAction) -> {
            final TextComponent link = Component.text()
                .content("To access the documentation Wiki, please click here")
                .color(TextColor.color(175, 200, 60))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://sefiraat.dev/"))
                .build();
            player1.sendMessage(link);
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
        menu.replaceExistingItem(PAINTBRUSHES, ItemGroups.ARTISTIC.getItem(player));
        menu.addMenuClickHandler(PAINTBRUSHES, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.ARTISTIC, mode, 1));

        // Exalted
        menu.replaceExistingItem(EXALTED, ItemGroups.EXALTED.getItem(player));
        menu.addMenuClickHandler(EXALTED, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.EXALTED, mode, 1));

        // Uniques
        menu.replaceExistingItem(UNIQUES, ItemGroups.UNIQUES.getItem(player));
        menu.addMenuClickHandler(UNIQUES, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.UNIQUES, mode, 1));

        // Runes
        menu.replaceExistingItem(RUNES, ItemGroups.RUNES.getItem(player));
        menu.addMenuClickHandler(RUNES, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.RUNES, mode, 1));

        // Materials
        menu.replaceExistingItem(MATERIALS, ItemGroups.MATERIALS.getItem(player));
        menu.addMenuClickHandler(MATERIALS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.MATERIALS, mode, 1));

        // Guide
        menu.replaceExistingItem(GUIDE, ItemGroups.GUIDE.getItem(player));
        menu.addMenuClickHandler(GUIDE, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.GUIDE, mode, 1));

        // Story
        menu.replaceExistingItem(STORY, ItemGroups.STORY_COLLECTION.getItem(player));
        menu.addMenuClickHandler(STORY, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.STORY_COLLECTION, mode, 1));

        // Spell
        menu.replaceExistingItem(SPELL, ItemGroups.SPELL_COLLECTION.getItem(player));
        menu.addMenuClickHandler(SPELL, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.SPELL_COLLECTION, mode, 1));

        // Gilding
        menu.replaceExistingItem(GILDING, ItemGroups.GILDING_COLLECTION.getItem(player));
        menu.addMenuClickHandler(GILDING, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, ItemGroups.GILDING_COLLECTION, mode, 1));
    }

    @ParametersAreNonnullByDefault
    public boolean openPage(PlayerProfile profile, ItemGroup itemGroup, SlimefunGuideMode mode, int page) {
        profile.getGuideHistory().add(this, 1);
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, page);
        return false;
    }
}
