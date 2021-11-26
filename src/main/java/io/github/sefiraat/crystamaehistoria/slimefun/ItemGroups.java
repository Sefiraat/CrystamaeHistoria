package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.DummyGuideOnly;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.DummyItemGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.MainFlexGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.SpellCollectionFlexGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.itemgroups.StoryCollectionFlexGroup;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.magicpaintbrush.MagicPaintbrush;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.experimental.UtilityClass;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public final class ItemGroups {

    public static final DummyItemGroup DUMMY_ITEM_GROUP = new DummyItemGroup(
        Keys.newKey("dummy"),
        new CustomItemStack(
            new ItemStack(Material.FIRE_CHARGE),
            ThemeType.MAIN.getColor() + "Dummy Crystamae Historia"
        )
    );
    public static final MainFlexGroup MAIN = new MainFlexGroup(
        Keys.newKey("main"),
        new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getColor() + "Crystamae Historia"
        )
    );
    public static final DummyItemGroup MECHANISMS = new DummyItemGroup(
        Keys.newKey("mechanisms"),
        new CustomItemStack(
            new ItemStack(Material.DEEPSLATE_TILE_SLAB),
            ThemeType.MAIN.getColor() + "Historia Mechanisms"
        )
    );
    public static final DummyItemGroup CRYSTALS = new DummyItemGroup(
        Keys.newKey("crystals"),
        new CustomItemStack(
            new ItemStack(Material.AMETHYST_CLUSTER),
            ThemeType.MAIN.getColor() + "Historia Crystals"
        )
    );
    public static final DummyItemGroup TOOLS = new DummyItemGroup(
        Keys.newKey("tools"),
        new CustomItemStack(
            new ItemStack(Material.STICK),
            ThemeType.MAIN.getColor() + "Historia Staves and Tool"
        )
    );
    public static final DummyItemGroup PAINTBRUSHES = new DummyItemGroup(
        Keys.newKey("paintbrushes"),
        new CustomItemStack(
            MagicPaintbrush.getTippedBrush(DyeColor.WHITE, true),
            ThemeType.MAIN.getColor() + "Crystamae Magic Paintbrushes"
        )
    );
    public static final DummyItemGroup GADGETS = new DummyItemGroup(
        Keys.newKey("gadgets"),
        new CustomItemStack(
            new ItemStack(Material.REDSTONE_LAMP),
            ThemeType.MAIN.getColor() + "Magical Tech and Gadgets"
        )
    );
    public static final DummyItemGroup MATERIALS = new DummyItemGroup(
        Keys.newKey("materials"),
        new CustomItemStack(
            new ItemStack(Material.GOLD_INGOT),
            ThemeType.MAIN.getColor() + "Crystamae Raw Materials"
        )
    );
    public static final DummyItemGroup GUIDE = new DummyItemGroup(
        Keys.newKey("guide"),
        new CustomItemStack(
            new ItemStack(Material.BOOK),
            ThemeType.MAIN.getColor() + "Crystamae Misc Guides"
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


    public static void setup() {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        /*
        These items are for the misc guide which serves to inform players
        how the more abstract mechanics work where a true recipe doesn't
        suit or would be detrimental to the process.
         */

        // Chronicler
        SlimefunItem guideChronicler = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_CHRONICLER",
                new ItemStack(Material.DEEPSLATE_TILE_SLAB),
                ThemeType.GUIDE,
                "Chronicler Panel Guide",
                "The chronicler panel will take a block",
                "and 'learn' the stories it has. The type of",
                "stories is determined by the block and the",
                "amount is random but based on tier.",
                "",
                "A chronicler can only work on blocks of",
                "a matching tier + 1.",
                "",
                "A block is finished when it's unique",
                "story has been chronicled and the block",
                "stops floating."
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Realisation
        SlimefunItem guideRealisation = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_REALISATION",
                new ItemStack(Material.CHISELED_DEEPSLATE),
                ThemeType.GUIDE,
                "Realisation Altar Guide",
                "Place a fully chronicled block onto",
                "the Realisation Altar and it will start",
                "to consume it's stories and turn them",
                "into a crystalline structure on the ground",
                "nearby.",
                "",
                "These crystal will slowly grow",
                "and, when fully formed, can be broken",
                "into Crystamae."
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Liquefaction
        SlimefunItem guideLiquefaction = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_LIQUEFACTION",
                new ItemStack(Material.CAULDRON),
                ThemeType.GUIDE,
                "Liquefaction Basin Guide",
                "The Liquefaction Basin is where you",
                "can finally make cool things.",
                "",
                "Any Crystamae crystal you throw in",
                "will be melted down into a liquid.",
                "When you throw in an item that is",
                "not a crystal, it will use that to try",
                "to make an item.",
                "",
                "Get it wrong and you lose your Crysta."
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Stave Configurator
        SlimefunItem guideStave = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_STAVE_CONFIGURATOR",
                new ItemStack(Material.CAULDRON),
                ThemeType.GUIDE,
                "Stave Configurator",
                "Put a stave into the input slot of the",
                "configurator. Add charged spell plates",
                "into the four spell slots then click",
                "'Add Plates'. Click 'Remove' to remove",
                "them again.",
                "",
                "Spells can be changed freely without cost."
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Make a Spell
        SlimefunItem guideMakeSpell = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_MAKE_SPELL",
                new ItemStack(Material.PAPER),
                ThemeType.GUIDE,
                "How To: Make a spell",
                "To make a spell, first craft a Basic",
                "Spell Plate. The in the Liquefaction",
                "Basin, fill with at least 3 different",
                "types of Crystamae - then throw in the",
                "Plate"
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Recharge a Spell
        SlimefunItem guideChargeSpell = new SlimefunItem(
            ItemGroups.GUIDE,
            ThemeType.themedSlimefunItemStack(
                "CRY_GUIDE_CHARGE_SPELL",
                new ItemStack(Material.PAPER),
                ThemeType.GUIDE,
                "How To: Recharge a spell",
                "To refill a plate with crysta first",
                "take it off of the stave using the",
                "configurator. then in the Liquefaction",
                "Basin, make the spell recipe and throw",
                "the charged plate in. It will refill",
                "the Crysta inside."
            ),
            DummyGuideOnly.TYPE,
            new ItemStack[]{}
        );

        // Slimefun Registry
        ItemGroups.MAIN.register(plugin);
        //ItemGroups.STORY_COLLECTION.register(plugin);
        //ItemGroups.SPELL_COLLECTION.register(plugin);

        guideChronicler.register(plugin);
        guideRealisation.register(plugin);
        guideLiquefaction.register(plugin);
        guideStave.register(plugin);
        guideMakeSpell.register(plugin);
        guideChargeSpell.register(plugin);
    }
}
