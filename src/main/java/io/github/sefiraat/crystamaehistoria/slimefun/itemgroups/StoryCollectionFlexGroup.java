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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @noinspection deprecation
 */
public class StoryCollectionFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;

    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    private static final int CHRONICLING_SLOT = 20;
    private static final int TIER_SLOT = 22;
    private static final int UNIQUE_SLOT = 40;

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
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(ThemeType.MAIN.getColor() + "Crystamae Magic Compendium");

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

    private void setupPage(@Nonnull Player p, @Nonnull PlayerProfile profile, @Nonnull SlimefunGuideMode mode, @Nonnull ChestMenu menu, int page) {
        final int numberOfBlocks = CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().size();
        final int totalPages = (int) Math.ceil(numberOfBlocks / (double) PAGE_SIZE);
        final List<StoriedBlockDefinition> blockDefinitions = new ArrayList<>(CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().values());
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, blockDefinitions.size());
        final List<StoriedBlockDefinition> blockDefinitionSubList = blockDefinitions.subList(start, end);

        reapplyFooter(p, profile, mode, menu, page, totalPages);

        // Back
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(p, Slimefun.getLocalization().getMessage("guide.back.guide")));
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(profile, ItemGroups.MAIN, mode, 1);
            return false;
        });

        blockDefinitionSubList.sort(Comparator.comparing(definition -> definition.getMaterial().name()));

        for (int i = 0; i < 36; i++) {
            final int slot = i + 9;
            // TODO [WalshyBoi] Visible when unlocked or GuideMode == CHEAT
            if (i + 1 > blockDefinitionSubList.size()) {
                menu.replaceExistingItem(slot, null);
                menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
            } else {
                StoriedBlockDefinition definition = blockDefinitionSubList.get(i);
                menu.replaceExistingItem(slot, new ItemStack(definition.getMaterial()));
                menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> {
                    displayDefinition(player1, profile, mode, menu, page, definition);
                    return false;
                });
            }
        }
    }

    private void displayDefinition(@Nonnull Player p, @Nonnull PlayerProfile profile, @Nonnull SlimefunGuideMode mode, @Nonnull ChestMenu menu, int returnPage, @Nonnull StoriedBlockDefinition definition) {
        // Back Button
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(p, Slimefun.getLocalization().getMessage("guide.back.guide")));
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            setupPage(player1, profile, mode, menu, returnPage);
            return false;
        });

        clearDisplay(menu);

        for (int slot : DIVIDER) {
            menu.replaceExistingItem(slot, GuiElements.MENU_DIVIDER);
        }
        for (int slot : CRYSTAMAE) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
        }

        menu.replaceExistingItem(CHRONICLING_SLOT, getPoolsItemStack(definition));
        menu.addMenuClickHandler(CHRONICLING_SLOT, (player, i, itemStack, clickAction) -> false);

        menu.replaceExistingItem(TIER_SLOT, getTierItemStack(definition));
        menu.addMenuClickHandler(TIER_SLOT, (player, i, itemStack, clickAction) -> false);

        menu.replaceExistingItem(UNIQUE_SLOT, getUniqueStoryItemStack(definition));
        menu.addMenuClickHandler(UNIQUE_SLOT, (player, i, itemStack, clickAction) -> false);

        for (Map.Entry<StoryType, Integer> entry : definition.getUnique().getStoryShardProfile().shardMap.entrySet()) {
            int amount = entry.getValue();
            if (amount > 0) {
                StoryType type = entry.getKey();
                ItemStack itemStack = CrystamaeHistoria.getStructure().getMaterials().getTypeItemMap().get(type).getItem().clone();
                itemStack.setAmount(entry.getValue());
                menu.replaceExistingItem(CRYSTAMAE[type.getId() - 1], itemStack);
            }
        }

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

    private ItemStack getPoolsItemStack(@Nonnull StoriedBlockDefinition definition) {
        final List<StoryType> storyTypes = definition.getPools();
        final List<String> lore = Arrays.stream(new String[]{
            "When chronicling this item, you",
            "can draw latent stories from the",
            "following story pools.",
            ""
        }).map(s -> ThemeType.PASSIVE.getColor() + s).collect(Collectors.toList());

        for (StoryType storyType : storyTypes) {
            lore.add(ThemeType.CLICK_INFO.getColor() + ThemeType.toTitleCase(storyType.toString()));
        }
        return new CustomItemStack(
            Material.DEEPSLATE_BRICK_SLAB,
            ThemeType.MAIN.getColor() + "Chronicling Results",
            lore
        );
    }

    private ItemStack getUniqueStoryItemStack(@Nonnull StoriedBlockDefinition definition) {
        return new CustomItemStack(
            definition.getMaterial(),
            ThemeType.MAIN.getColor() + definition.getUnique().getId(),
            definition.getUnique().getStoryLore()
        );
    }

    private ItemStack getTierItemStack(@Nonnull StoriedBlockDefinition definition) {
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
