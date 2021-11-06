package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.DummyItemGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.SpellCollectionFlexGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.StoryCollectionFlexGroup;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ItemGroups {

    public static final NestedItemGroup MAIN = new NestedItemGroup(
        Keys.newKey("main"),
        new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getColor() + "Crystamae Historia"
        )
    );

    public static final SubItemGroup MECHANISMS = new SubItemGroup(
        Keys.newKey("mechanisms"),
        MAIN,
        new CustomItemStack(
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeType.MAIN.getColor() + "Historia Mechanisms"
        )
    );

    public static final SubItemGroup CRYSTALS = new SubItemGroup(
        Keys.newKey("crystals"),
        MAIN,
        new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getColor() + "Historia Crystals"
        )
    );

    public static final SubItemGroup TOOLS = new SubItemGroup(
        Keys.newKey("tools"),
        MAIN,
        new CustomItemStack(
            new ItemStack(Material.STICK),
            ThemeType.MAIN.getColor() + "Historia Staves and Materials"
        )
    );

    public static final SubItemGroup GADGETS = new SubItemGroup(
        Keys.newKey("gadgets"),
        MAIN,
        new CustomItemStack(
            new ItemStack(Material.REDSTONE_LAMP),
            ThemeType.MAIN.getColor() + "Magical Tech and Gadgets"
        )
    );

    public static final SubItemGroup MATERIALS = new SubItemGroup(
        Keys.newKey("materials"),
        MAIN,
        new CustomItemStack(
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.MAIN.getColor() + "Crystamae Raw Materials"
        )
    );

    public static final StoryCollectionFlexGroup STORY_COLLECTION = new StoryCollectionFlexGroup(
        Keys.newKey("story_collection"),
        new CustomItemStack(
            new ItemStack(Material.KNOWLEDGE_BOOK),
            ThemeType.MAIN.getColor() + "Story Collection"
        )
    );

    public static final SpellCollectionFlexGroup SPELL_COLLECTION = new SpellCollectionFlexGroup(
        Keys.newKey("spell_collection"),
        new CustomItemStack(
            new ItemStack(Material.KNOWLEDGE_BOOK),
            ThemeType.MAIN.getColor() + "Spell Collection"
        )
    );

    public static final DummyItemGroup DUMMY_ITEM_GROUP = new DummyItemGroup(
        Keys.newKey("dummy"),
        new CustomItemStack(
            new ItemStack(Material.FIRE_CHARGE),
            ThemeType.MAIN.getColor() + "Dummy Crystamae Historia"
        )
    );

    public static void setup() {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        // Slimefun Registry
        ItemGroups.STORY_COLLECTION.register(plugin);
        ItemGroups.SPELL_COLLECTION.register(plugin);
    }
}
