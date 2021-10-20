package io.github.sefiraat.crystamaehistoria.slimefun.itemgroups;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
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
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @noinspection deprecation
 */
public class StoryCollectionFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;
    private static final int CHRONICLING_SLOT = 20;
    private static final int TIER_SLOT = 22;
    private static final int UNIQUE_SLOT = 40;
    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };
    private static final int[] DIVIDER = new int[]{
        36, 37, 38, 39, 41, 42, 43, 44
    };
    private static final int[] CRYSTAMAE = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    protected StoryCollectionFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    public boolean isVisible(@Nonnull Player player, @Nonnull PlayerProfile playerProfile, @Nonnull SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    public void open(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        final ChestMenu chestMenu = new ChestMenu(ThemeType.MAIN.getColor() + "Crystamae Magic Compendium");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        chestMenu.setEmptySlotsClickable(false);

        // Back
        chestMenu.replaceExistingItem(1, ChestMenuUtils.getBackButton(player, Slimefun.getLocalization().getMessage("guide.back.guide")));
        chestMenu.addMenuClickHandler(1, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(playerProfile, ItemGroups.MAIN, guideMode, 1);
            return false;
        });

        setupPage(chestMenu, player, 1);

        chestMenu.open(player);
    }

    private void setupPage(@Nonnull ChestMenu chestMenu, @Nonnull Player player, int page) {
        final int numberOfBlocks = CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().size();
        final int totalPages = (int) Math.ceil(numberOfBlocks / (double) PAGE_SIZE);
        final List<StoriedBlockDefinition> blockDefinitions = new ArrayList<>(CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().values());
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, blockDefinitions.size());
        final List<StoriedBlockDefinition> blockDefinitionSubList = blockDefinitions.subList(start, end);

        blockDefinitionSubList.sort(Comparator.comparing(definition -> definition.getMaterial().name()));

        reapplyFooter(chestMenu, player, page, totalPages);

        for (int i = 0; i < 36; i++) {
            final int slot = i + 9;
            // TODO Visible when unlocked or GuideMode == CHEAT
            if (i + 1 > blockDefinitionSubList.size()) {
                chestMenu.replaceExistingItem(slot, null);
                chestMenu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
            } else {
                StoriedBlockDefinition definition = blockDefinitionSubList.get(i);
                // TODO Open Story Info
                chestMenu.replaceExistingItem(slot, new ItemStack(definition.getMaterial()));
                chestMenu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> {
                    displayDefinition(chestMenu, player1, page, definition);
                    return false;
                });
            }
        }
    }

    private void displayDefinition(@Nonnull ChestMenu chestMenu, @Nonnull Player player, int returnPage, @Nonnull StoriedBlockDefinition definition) {
        // Back Button
        chestMenu.replaceExistingItem(1, ChestMenuUtils.getBackButton(player, Slimefun.getLocalization().getMessage("guide.back.guide")));
        chestMenu.addMenuClickHandler(1, (player1, slot, itemStack, clickAction) -> {
            setupPage(chestMenu, player1, returnPage);
            return false;
        });

        clearDisplay(chestMenu);

        for (int slot : DIVIDER) {
            chestMenu.replaceExistingItem(slot, GuiElements.MENU_DIVIDER);
        }
        for (int slot : CRYSTAMAE) {
            chestMenu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
        }
        chestMenu.replaceExistingItem(CHRONICLING_SLOT, getPoolsItemStack(definition));
        chestMenu.replaceExistingItem(TIER_SLOT, getTierItemStack(definition));
        chestMenu.replaceExistingItem(UNIQUE_SLOT, getUniqueStoryItemStack(definition));

        for (Map.Entry<StoryType, Integer> entry : definition.getUnique().getStoryShardProfile().shardMap.entrySet()) {
            int amount = entry.getValue();
            if (amount > 0) {
                StoryType type = entry.getKey();
                ItemStack itemStack = CrystamaeHistoria.getStructure().getMaterials().getTypeItemMap().get(type).getItem().clone();
                itemStack.setAmount(entry.getValue());
                chestMenu.replaceExistingItem(CRYSTAMAE[type.getId() - 1], itemStack);
            }
        }

    }

    private void clearDisplay(@Nonnull ChestMenu chestMenu) {
        for (int i = 0; i < 45; i++) {
            final int slot = i + 9;
            chestMenu.replaceExistingItem(slot, null);
            chestMenu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
        }
    }

    private void reapplyFooter(@Nonnull ChestMenu chestMenu, @Nonnull Player player, int page, int totalPages) {
        for (int i = 45; i < 54; i++) {
            chestMenu.replaceExistingItem(i, ChestMenuUtils.getBackground());
        }

        chestMenu.replaceExistingItem(46, ChestMenuUtils.getPreviousButton(player, page, totalPages));
        chestMenu.addMenuClickHandler(46, (player1, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage != page && previousPage >= 1) {
                setupPage(chestMenu, player1, previousPage);
            }
            return false;
        });

        chestMenu.replaceExistingItem(52, ChestMenuUtils.getNextButton(player, page, totalPages));
        chestMenu.addMenuClickHandler(52, (player1, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage != page && nextPage <= totalPages) {
                setupPage(chestMenu, player1, nextPage);
            }
            return false;
        });
    }

    private ItemStack getPoolsItemStack(StoriedBlockDefinition definition) {
        final List<StoryType> storyTypes = definition.getPools();
        final List<String> lore = new ArrayList<>();
        for (String s : Arrays.asList(
            "When chronicling this item, you",
            "can draw latent stories from the",
            "following story pools.",
            "")) {
            lore.add(ThemeType.PASSIVE.getColor() + s);
        }
        for (StoryType storyType : storyTypes) {
            lore.add(ThemeType.CLICK_INFO.getColor() + ThemeType.toTitleCase(storyType.toString()));
        }
        return new CustomItemStack(
            Material.DEEPSLATE_BRICK_SLAB,
            ThemeType.MAIN.getColor() + "Chronicling Results",
            lore
        );
    }

    private ItemStack getUniqueStoryItemStack(StoriedBlockDefinition definition) {
        return new CustomItemStack(
            definition.getMaterial(),
            ThemeType.MAIN.getColor() + definition.getUnique().getId(),
            definition.getUnique().getStoryLore()
        );
    }

    private ItemStack getTierItemStack(StoriedBlockDefinition definition) {
        switch (definition.getTier().tier) {
            case 1:
                return GuiElements.TIER_INDICATOR_1;
            case 2:
                return GuiElements.TIER_INDICATOR_2;
            case 3:
                return GuiElements.TIER_INDICATOR_3;
            case 4:
                return GuiElements.TIER_INDICATOR_4;
            case 5:
                return GuiElements.TIER_INDICATOR_5;
            default:
                throw new IllegalStateException("Inapplicable tier provided: " + definition.getTier().tier);
        }
    }
}
