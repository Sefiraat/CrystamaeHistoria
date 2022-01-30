package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.SupportedPluginManager;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.ConnectingCompass;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.Displacer;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.LuminescenceScoop;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.RecallingCrystaLattice;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.SpiritualSilken;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.ThaumaturgicSalt;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.covers.BlockVeil;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.crafting.EphemeralCraftingTable;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.crafting.EphemeralWorkBench;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.sefiraat.networks.slimefun.NetworksSlimefunItemStacks;
import io.github.sefiraat.networks.slimefun.network.NetworkBridge;
import io.github.sefiraat.networks.slimefun.network.NetworkMonitor;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.cargo.CargoConnectorNode;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.EnergyConnector;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

@UtilityClass
public class Tools {

    @Getter
    private static SlimefunItem inertPlate;
    @Getter
    private static SlimefunItem chargedPlate;
    @Getter
    private static Stave staveBasic;
    @Getter
    private static Stave staveAdvanced;
    @Getter
    private static RefactingLens refractingLens;
    @Getter
    private static ThaumaturgicSalt thaumaturgicSalts;
    @Getter
    private static RecallingCrystaLattice crystaRecallLattice;
    @Getter
    private static EphemeralCraftingTable ephemeralCraftingTable;
    @Getter
    private static EphemeralWorkBench ephemeralWorkBench;
    @Getter
    private static LuminescenceScoop luminescenceScoop;
    @Getter
    private static LuminescenceScoop brillianceScoop;
    @Getter
    private static LuminescenceScoop lustreScoop;
    @Getter
    private static ConnectingCompass connectingCompass;
    @Getter
    private static SpiritualSilken spiritualSilken;
    @Getter
    private static Displacer simpleDisplacer;
    @Getter
    private static Displacer arcaneDisplacer;
    @Getter
    private static BlockVeil cargoCover;
    @Getter
    private static BlockVeil energyNetCover;
    @Getter
    private static BlockVeil networkNodeCover;


    public static void setup() {
        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack elementalUniqueCrystal = Materials.CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.ELEMENTAL).getItem();
        final ItemStack commonIngot = Materials.getAmalgamateIngotCommon().getItem();
        final ItemStack commonDust = Materials.getAmalgamateDustCommon().getItem();
        final ItemStack epicIngot = Materials.getAmalgamateIngotEpic().getItem();

        // Inert Plate
        RecipeItem inertPlateRecipe = new RecipeItem(
            SlimefunItems.REINFORCED_PLATE.clone(),
            StoryType.ELEMENTAL, 10,
            StoryType.HUMAN, 10,
            StoryType.PHILOSOPHICAL, 10
        );
        inertPlate = new BlankPlate(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_SPELL_PLATE_1",
                new ItemStack(Material.PAPER),
                ThemeType.TOOL,
                "Basic Spell Plate",
                "A blank plate that has the potential to",
                "store magical energy"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            inertPlateRecipe.getDisplayRecipe(),
            1
        );


        // Charged Plate
        chargedPlate = new ChargedPlate(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_CHARGED_PLATE_1",
                new ItemStack(Material.PAPER),
                ThemeType.TOOL,
                "Charged Basic Spell Plate",
                "A magically charged plate storing magic",
                "potential."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            new ItemStack[]{null, null, null, null, new ItemStack(Material.AMETHYST_CLUSTER), null, null, null, null},
            1
        );

        // Basic Stave
        staveBasic = new Stave(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_STAVE_1",
                new ItemStack(Material.STICK),
                ThemeType.STAVE,
                "Basic Stave",
                "A stave with the ability to hold",
                "magically charged plates."
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, null, elementalUniqueCrystal,
                null, new ItemStack(Material.STICK), null,
                new ItemStack(Material.STICK), null, null
            },
            1
        );

        // Advanced Stave
        staveAdvanced = new Stave(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_STAVE_2",
                new ItemStack(Material.STICK),
                ThemeType.STAVE,
                "Advanced Stave",
                "A stave with the ability to hold",
                "magically charged plates."
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                commonIngot, commonIngot, commonIngot,
                commonIngot, staveBasic.getItem().clone(), commonIngot,
                commonIngot, commonIngot, commonIngot
            },
            2
        );

        // Refracting Lens
        refractingLens = new RefactingLens(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_REFRACTING_LENS",
                new ItemStack(Material.SPYGLASS),
                ThemeType.TOOL,
                "Refracting Lens",
                "This magical lens has glass that can",
                "split the light of Crysta into its",
                "individual elements.",
                "",
                "Right click on a Crystamae Block for",
                "details if available.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Works with:",
                ChatColor.DARK_BLUE + "Liquefaction Basin",
                ChatColor.DARK_BLUE + "Exp Collector"
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, Materials.getImbuedGlass().getItem(), null,
                null, new ItemStack(Material.SPYGLASS), null,
                null, commonIngot, null
            }
        );

        // Thaumaturgic Salt
        thaumaturgicSalts = new ThaumaturgicSalt(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_THAUMATURGIC_SALT",
                new ItemStack(Material.REDSTONE),
                ThemeType.TOOL,
                "Thaumaturgic Salts",
                "A special formulation of salts",
                "that can absorb unwanted crysta",
                "from a Liquefaction Basin.",
                "",
                "Right click on a Liquefaction Basin",
                "to empty it."
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                commonDust, commonDust, commonDust,
                commonDust, SlimefunItems.SALT, commonDust,
                commonDust, commonDust, commonDust
            }
        );

        // Recall Lattice
        crystaRecallLattice = new RecallingCrystaLattice(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_RECALL_LATTICE",
                new ItemStack(Material.NETHER_STAR),
                ThemeType.TOOL,
                "Crystamae Recall Lattice",
                "Right click to recall to a linked",
                "Waystone.",
                "",
                "Shift Right Click on a Waystone",
                "to link"
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                new ItemStack(Material.AMETHYST_SHARD), epicIngot, new ItemStack(Material.AMETHYST_SHARD),
                epicIngot, new ItemStack(Material.NETHER_STAR), epicIngot,
                new ItemStack(Material.AMETHYST_SHARD), epicIngot, new ItemStack(Material.AMETHYST_SHARD)
            }
        );

        // Ephemeral Crafting Table
        RecipeItem ephemeralCraftingTableRecipe = new RecipeItem(
            new ItemStack(Material.CRAFTING_TABLE),
            StoryType.HUMAN, 50,
            StoryType.HISTORICAL, 25,
            StoryType.PHILOSOPHICAL, 50
        );
        ephemeralCraftingTable = new EphemeralCraftingTable(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_EPHEMERAL_CRAFT_TABLE",
                new ItemStack(Material.CRAFTING_TABLE),
                ThemeType.TOOL,
                "Ephemeral Crafting Table",
                "Right click to be able to craft",
                "from anywhere.",
                "Vanilla Only"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            ephemeralCraftingTableRecipe.getDisplayRecipe()
        );

        // Ephemeral Workbench
        RecipeItem ephemeralWorkBenchRecipe = new RecipeItem(
            ephemeralCraftingTable.getItem(),
            StoryType.HUMAN, 250,
            StoryType.HISTORICAL, 100,
            StoryType.PHILOSOPHICAL, 250
        );
        ephemeralWorkBench = new EphemeralWorkBench(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_EPHEMERAL_WORKBENCH",
                new ItemStack(Material.CRAFTING_TABLE),
                ThemeType.TOOL,
                "Ephemeral Work Bench",
                "Right click to be able to craft",
                "from anywhere.",
                "Vanilla + Enhanced Crafting Table."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            ephemeralWorkBenchRecipe.getDisplayRecipe()
        );

        // Luminesence Scoop
        RecipeItem luminescenceScoopRecipe = new RecipeItem(
            new ItemStack(Material.LANTERN),
            StoryType.CELESTIAL, 70,
            StoryType.ALCHEMICAL, 20,
            StoryType.HUMAN, 15
        );
        luminescenceScoop = new LuminescenceScoop(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_LUMINESCENCE_SCOOP",
                new ItemStack(Material.LANTERN),
                ThemeType.TOOL,
                "Luminescence Scoop",
                "Right click to place a magical",
                "light source.",
                "Shift Right click to scoop back.",
                "",
                ChatColor.YELLOW + "25 Uses " + ChatColor.GRAY + "left"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            luminescenceScoopRecipe.getDisplayRecipe(),
            25
        );

        // Brilliance Scoop
        RecipeItem brillianceScoopRecipe = new RecipeItem(
            luminescenceScoop.getItem(),
            StoryType.CELESTIAL, 140,
            StoryType.ALCHEMICAL, 40,
            StoryType.HUMAN, 30
        );
        brillianceScoop = new LuminescenceScoop(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_BRILLIANCE_SCOOP",
                new ItemStack(Material.LANTERN),
                ThemeType.TOOL,
                "Brilliance Scoop",
                "Right click to place a magical",
                "light source.",
                "Shift Right click to scoop back.",
                "",
                ChatColor.YELLOW + "75 Uses " + ChatColor.GRAY + "left"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            brillianceScoopRecipe.getDisplayRecipe(),
            75
        );

        // Lustre Scoop
        RecipeItem lustreScoopRecipe = new RecipeItem(
            brillianceScoop.getItem(),
            StoryType.CELESTIAL, 280,
            StoryType.ALCHEMICAL, 80,
            StoryType.HUMAN, 60
        );
        lustreScoop = new LuminescenceScoop(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_LUSTRE_SCOOP",
                new ItemStack(Material.SOUL_LANTERN),
                ThemeType.TOOL,
                "Lustre Scoop",
                "Right click to place a magical",
                "light source.",
                "Shift Right click to scoop back.",
                "",
                ChatColor.YELLOW + "250 Uses " + ChatColor.GRAY + "left"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            lustreScoopRecipe.getDisplayRecipe(),
            250
        );

        // Connecting Compass
        RecipeItem connectingCompassRecipe = new RecipeItem(
            new ItemStack(Material.COMPASS),
            StoryType.MECHANICAL, 5,
            StoryType.HISTORICAL, 10,
            StoryType.HUMAN, 5
        );
        connectingCompass = new ConnectingCompass(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_CONNECTING_COMPASS",
                new ItemStack(Material.COMPASS),
                ThemeType.TOOL,
                "Connecting Compass",
                "Allows you to save a location",
                "to find your way back to it at",
                "a later time.",
                "",
                MessageFormat.format("{0}Right Click: {1}Display Stored Location", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor()),
                MessageFormat.format("{0}Shift Right Click: {1}Store Location", ThemeType.CLICK_INFO.getColor(), ThemeType.PASSIVE.getColor())
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            connectingCompassRecipe.getDisplayRecipe()
        );

        // Spiritual Silken
        RecipeItem spiritualSilkenRecipe = new RecipeItem(
            new ItemStack(Material.BONE),
            StoryType.MECHANICAL, 250,
            StoryType.HUMAN, 250,
            StoryType.CELESTIAL, 250
        );
        spiritualSilken = new SpiritualSilken(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_SPIRITUAL_SILKEN",
                new ItemStack(Material.BONE),
                ThemeType.TOOL,
                "Spiritual Silken",
                "This pick has been imbued with",
                "spiritual energies that allow it",
                "to keep certain items whole.",
                ChatColor.RED + "Only works on blocks that do",
                ChatColor.RED + "not normally drop themselves",
                ChatColor.RED + "both with and without silk.",
                "",
                ChatColor.YELLOW + "50 Uses " + ChatColor.GRAY + "left"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            spiritualSilkenRecipe.getDisplayRecipe(),
            50
        );

        // Simple Displacer
        RecipeItem simpleDisplacerRecipe = new RecipeItem(
            Materials.getPowderedEssence().getItem(),
            StoryType.ALCHEMICAL, 120,
            StoryType.ANIMAL, 70,
            StoryType.HUMAN, 60
        );
        simpleDisplacer = new Displacer(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_SIMPLE_DISPLACER",
                new ItemStack(Material.TORCH),
                ThemeType.TOOL,
                "Simple Displacer",
                "The displacer can shift some",
                "things out of this world and",
                "replace it with something from",
                "another dimension.",
                ChatColor.YELLOW + "50 Uses " + ChatColor.GRAY + "left"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            simpleDisplacerRecipe.getDisplayRecipe(),
            50
        );

        // Arcane Displacer
        RecipeItem arcaneDisplacerRecipe = new RecipeItem(
            simpleDisplacer.getItem(),
            StoryType.ALCHEMICAL, 240,
            StoryType.ANIMAL, 140,
            StoryType.HUMAN, 120
        );
        arcaneDisplacer = new Displacer(
            ItemGroups.TOOLS,
            ThemeType.themedSlimefunItemStack(
                "CRY_ARCANE_DISPLACER",
                new ItemStack(Material.REDSTONE_TORCH),
                ThemeType.TOOL,
                "Arcane Displacer",
                "The displacer can shift some",
                "things out of this world and",
                "replace it with something from",
                "another dimension.",
                ChatColor.YELLOW + "500 Uses " + ChatColor.GRAY + "left"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            arcaneDisplacerRecipe.getDisplayRecipe(),
            500
        );

        // Slimefun Registry
        chargedPlate.register(CrystamaeHistoria.getInstance());
        inertPlate.register(CrystamaeHistoria.getInstance());
        staveBasic.register(plugin);
        staveAdvanced.register(plugin);
        refractingLens.register(plugin);
        thaumaturgicSalts.register(plugin);
        crystaRecallLattice.register(plugin);
        ephemeralCraftingTable.register(plugin);
        ephemeralWorkBench.register(plugin);
        luminescenceScoop.register(plugin);
        brillianceScoop.register(plugin);
        lustreScoop.register(plugin);
        connectingCompass.register(plugin);
        spiritualSilken.register(plugin);
        simpleDisplacer.register(plugin);
        arcaneDisplacer.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(inertPlate, inertPlateRecipe);

        LiquefactionBasinCache.addCraftingRecipe(ephemeralCraftingTable, ephemeralCraftingTableRecipe);
        LiquefactionBasinCache.addCraftingRecipe(ephemeralWorkBench, ephemeralWorkBenchRecipe);

        LiquefactionBasinCache.addCraftingRecipe(luminescenceScoop, luminescenceScoopRecipe);
        LiquefactionBasinCache.addCraftingRecipe(brillianceScoop, brillianceScoopRecipe);
        LiquefactionBasinCache.addCraftingRecipe(lustreScoop, lustreScoopRecipe);
          
        LiquefactionBasinCache.addCraftingRecipe(connectingCompass, connectingCompassRecipe);

        LiquefactionBasinCache.addCraftingRecipe(spiritualSilken, spiritualSilkenRecipe);

        LiquefactionBasinCache.addCraftingRecipe(simpleDisplacer, simpleDisplacerRecipe);
        LiquefactionBasinCache.addCraftingRecipe(arcaneDisplacer, arcaneDisplacerRecipe);

        /*
        Covers 'hide' items from HL - until the tile entity check
        is swapped out to extend to all SlimefunItems we don't want
        to allow the items to exist here.
         */
        if (!SupportedPluginManager.isHeadLimiter()) {
            // Cargo Cover
            SlimefunItemStack cargoCoverStack = ThemeType.themedSlimefunItemStack(
                "CRY_CARGO_COVER",
                new ItemStack(Material.PAPER),
                ThemeType.TOOL,
                "Block Veil - Cargo",
                "Right click to place a magical",
                "block veil over a cargo node.",
                "The cover will mimic the block",
                "in your offhand.",
                "One time use per item."
            );
            RecipeItem cargoCoverRecipe = new RecipeItem(
                SlimefunItems.CARGO_INPUT_NODE,
                StoryType.MECHANICAL, 10,
                StoryType.HUMAN, 10,
                StoryType.VOID, 10
            );
            cargoCover = new BlockVeil(
                ItemGroups.TOOLS,
                cargoCoverStack,
                DummyLiquefactionBasinCrafting.TYPE,
                cargoCoverRecipe.getDisplayRecipe(),
                cargoCoverStack.asQuantity(8),
                CargoConnectorNode.class
            );

            // Energy Net Cover
            SlimefunItemStack energyNetCoverStack = ThemeType.themedSlimefunItemStack(
                "CRY_ENERGY_NET_COVER",
                new ItemStack(Material.PAPER),
                ThemeType.TOOL,
                "Block Veil - Energy Net",
                "Right click to place a magical",
                "block veil over a cargo node.",
                "The cover will mimic the block",
                "in your offhand.",
                "One time use per item."
            );
            RecipeItem energyNetCoverRecipe = new RecipeItem(
                SlimefunItems.ENERGY_CONNECTOR,
                StoryType.MECHANICAL, 10,
                StoryType.HUMAN, 10,
                StoryType.VOID, 10
            );
            energyNetCover = new BlockVeil(
                ItemGroups.TOOLS,
                energyNetCoverStack,
                DummyLiquefactionBasinCrafting.TYPE,
                energyNetCoverRecipe.getDisplayRecipe(),
                energyNetCoverStack.asQuantity(8),
                EnergyConnector.class
            );


            cargoCover.register(plugin);
            energyNetCover.register(plugin);

            LiquefactionBasinCache.addCraftingRecipe(cargoCover, cargoCoverRecipe);
            LiquefactionBasinCache.addCraftingRecipe(energyNetCover, energyNetCoverRecipe);
        }

        if (SupportedPluginManager.isNetworks()) {

            // Networks Cover
            SlimefunItemStack networksCoverStack = ThemeType.themedSlimefunItemStack(
                "CRY_NETWORK_COVER",
                new ItemStack(Material.PAPER),
                ThemeType.TOOL,
                "Block Veil - Networks",
                "Right click to place a magical",
                "block veil over a Networks node.",
                "The cover will mimic the block",
                "in your offhand.",
                "One time use per item."
            );
            RecipeItem networksCoverRecipe = new RecipeItem(
                NetworksSlimefunItemStacks.NETWORK_BRIDGE,
                StoryType.MECHANICAL, 10,
                StoryType.HUMAN, 10,
                StoryType.VOID, 10
            );
            networkNodeCover = new BlockVeil(
                ItemGroups.TOOLS,
                networksCoverStack,
                DummyLiquefactionBasinCrafting.TYPE,
                networksCoverRecipe.getDisplayRecipe(),
                networksCoverStack.asQuantity(8),
                NetworkBridge.class,
                NetworkMonitor.class
            );

            networkNodeCover.register(plugin);

            LiquefactionBasinCache.addCraftingRecipe(networkNodeCover, networksCoverRecipe);
        }
    }
}
