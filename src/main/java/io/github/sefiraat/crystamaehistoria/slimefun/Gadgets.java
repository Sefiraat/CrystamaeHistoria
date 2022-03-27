package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.AngelBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.CursedEarth;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.EnderInhibitor;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.ExaltationStand;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.ExpCollector;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.FragmentedVoid;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.GlassOfMilk;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.GreenHouseGlass;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobCandle;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobFan;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobLamp;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobMat;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MobTrap;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MysteriousTicker;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.MysteriousTickerNoInteraction;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.PhilosophersSpray;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.TrophyDisplay;
import io.github.sefiraat.crystamaehistoria.slimefun.items.gadgets.Waystone;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class Gadgets {

    @Getter
    private static MobLamp abstractionLamp;
    @Getter
    private static MobLamp dispersionLamp;
    @Getter
    private static MobLamp exodusLamp;
    @Getter
    private static MobFan inversionVacuum;
    @Getter
    private static MobFan antipodalVacuum;
    @Getter
    private static MobFan counterpoleVacuum;
    @Getter
    private static CursedEarth cursedEarth;
    @Getter
    private static CursedEarth dreadfulDirt;
    @Getter
    private static CursedEarth soulfilledSoil;
    @Getter
    private static MobMat searingPlate;
    @Getter
    private static MobMat doomedPlate;
    @Getter
    private static MobMat evisceratingPlate;
    @Getter
    private static MobMat shreddingPlate;
    @Getter
    private static MobTrap trapPlate;
    @Getter
    private static ExpCollector basicExpCollector;
    @Getter
    private static ExpCollector infusedExpCollector;
    @Getter
    private static ExpCollector arcaneExpCollector;
    @Getter
    private static EnderInhibitor basicEnderInhibitor;
    @Getter
    private static EnderInhibitor advancedEnderInhibitor;
    @Getter
    private static MobCandle dimMobCandle;
    @Getter
    private static MobCandle brightMobCandle;
    @Getter
    private static MobCandle scintillatingMobCandle;
    @Getter
    private static MobCandle coruscatingMobCandle;
    @Getter
    private static MysteriousTickerNoInteraction mysteriousPottedPlant;
    @Getter
    private static MysteriousTicker mysteriousPlant;
    @Getter
    private static MysteriousTicker mysteriousGlass;
    @Getter
    private static MysteriousTicker mysteriousWool;
    @Getter
    private static MysteriousTicker mysteriousTerracotta;
    @Getter
    private static MysteriousTicker mysteriousGlazedTerracotta;
    @Getter
    private static MysteriousTicker mysteriousConcrete;
    @Getter
    private static GreenHouseGlass greenHouseGlass;
    @Getter
    private static GreenHouseGlass focusedGreenHouseGlass;
    @Getter
    private static GreenHouseGlass magnifyingGreenHouseGlass;
    @Getter
    private static TrophyDisplay trophyDisplay;
    @Getter
    private static ExaltationStand exaltationStand;
    @Getter
    private static Waystone waystone;
    @Getter
    private static AngelBlock angelBlock;
    @Getter
    private static PhilosophersSpray philosophersSpray;
    @Getter
    private static GlassOfMilk glassOfMilk;
    @Getter
    private static FragmentedVoid fragmentedVoid;
    @Getter
    private static FragmentedVoid shatteredVoid;

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack uniqueVoid = Materials.CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.VOID).getItem();
        final ItemStack glass = new ItemStack(Material.GLASS);

        // Abstraction Lamp
        RecipeItem abstractionLampRecipe = new RecipeItem(
            new ItemStack(Material.LANTERN),
            StoryType.ALCHEMICAL, 50,
            StoryType.HUMAN, 75,
            StoryType.PHILOSOPHICAL, 75
        );
        abstractionLamp = new MobLamp(
            ItemGroups.GADGETS,
            CrystaStacks.ABSTRACTION_LAMP,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            abstractionLampRecipe.getDisplayRecipe(),
            5,
            0.3
        );

        // Dispersion Lamp
        RecipeItem dispersionLampRecipe = new RecipeItem(
            CrystaStacks.ABSTRACTION_LAMP,
            StoryType.ALCHEMICAL, 250,
            StoryType.HUMAN, 150,
            StoryType.PHILOSOPHICAL, 300
        );
        dispersionLamp = new MobLamp(
            ItemGroups.GADGETS,
            CrystaStacks.DISPERSION_LAMP,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            dispersionLampRecipe.getDisplayRecipe(),
            7,
            0.5
        );

        // Exodus Lamp
        exodusLamp = new MobLamp(
            ItemGroups.GADGETS,
            CrystaStacks.EXODUS_LAMP,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_BEGINNING, null,
                CrystaStacks.RUNE_NIGHT, CrystaStacks.DISPERSION_LAMP, CrystaStacks.RUNE_TWILIGHT,
                null, CrystaStacks.RUNE_BRIGHT_SHIELD, null
            },
            10,
            1
        );

        // Inversion Vacuum
        RecipeItem inversionVacuumRecipe = new RecipeItem(
            CrystaStacks.ABSTRACTION_LAMP,
            StoryType.HISTORICAL, 200,
            StoryType.VOID, 200,
            StoryType.PHILOSOPHICAL, 180
        );
        inversionVacuum = new MobFan(
            ItemGroups.GADGETS,
            CrystaStacks.INVERSION_VACUUM,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            inversionVacuumRecipe.getDisplayRecipe(),
            5
        );

        // Antipodal Vacuum
        RecipeItem antipodalVacuumRecipe = new RecipeItem(
            CrystaStacks.DISPERSION_LAMP,
            StoryType.HISTORICAL, 400,
            StoryType.VOID, 400,
            StoryType.PHILOSOPHICAL, 360
        );
        antipodalVacuum = new MobFan(
            ItemGroups.GADGETS,
            CrystaStacks.ANTIPODAL_VACUUM,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            antipodalVacuumRecipe.getDisplayRecipe(),
            10
        );

        // Counterpole Vacuum
        counterpoleVacuum = new MobFan(
            ItemGroups.GADGETS,
            CrystaStacks.COUNTERPOLE_VACUUM,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_GATE, null,
                CrystaStacks.RUNE_TRUE_HOLY, CrystaStacks.DISPERSION_LAMP, CrystaStacks.RUNE_SUN,
                null, CrystaStacks.RUNE_TRUE_LIGHTNING, null
            },
            15
        );

        // Cursed Earth
        final List<EntityType> cursedEarthSpawns = new ArrayList<>();

        cursedEarthSpawns.add(EntityType.SKELETON);
        cursedEarthSpawns.add(EntityType.ZOMBIE);
        cursedEarthSpawns.add(EntityType.ENDERMAN);
        cursedEarthSpawns.add(EntityType.CREEPER);
        cursedEarthSpawns.add(EntityType.CAVE_SPIDER);
        cursedEarthSpawns.add(EntityType.SILVERFISH);

        cursedEarth = new CursedEarth(
            ItemGroups.GADGETS,
            CrystaStacks.CURSED_EARTH,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                uniqueVoid, uniqueVoid, uniqueVoid,
                new ItemStack(Material.DIRT), new ItemStack(Material.DIRT), new ItemStack(Material.DIRT),
                CrystaStacks.AMALGAMATE_DUST_RARE, CrystaStacks.AMALGAMATE_DUST_RARE, CrystaStacks.AMALGAMATE_DUST_RARE
            },
            20,
            7,
            cursedEarthSpawns,
            Color.fromRGB(95, 50, 15)
        );

        // Dreadful Dirt
        final List<EntityType> dreadfulDirtSpawns = new ArrayList<>(cursedEarthSpawns);

        dreadfulDirtSpawns.add(EntityType.WITHER_SKELETON);
        dreadfulDirtSpawns.add(EntityType.HUSK);
        dreadfulDirtSpawns.add(EntityType.STRAY);
        dreadfulDirtSpawns.add(EntityType.BLAZE);
        dreadfulDirtSpawns.add(EntityType.ZOMBIE_VILLAGER);

        RecipeItem dreadfulDirtRecipe = new RecipeItem(
            CrystaStacks.CURSED_EARTH,
            StoryType.VOID, 700,
            StoryType.ANIMAL, 200,
            StoryType.HISTORICAL, 100
        );
        dreadfulDirt = new CursedEarth(
            ItemGroups.GADGETS,
            CrystaStacks.DREADFUL_DIRT,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            dreadfulDirtRecipe.getDisplayRecipe(),
            10,
            15,
            dreadfulDirtSpawns,
            Color.BLACK
        );

        // Soulless Soil
        final List<EntityType> soulfilledSoilSpawns = new ArrayList<>();

        dreadfulDirtSpawns.add(EntityType.COW);
        dreadfulDirtSpawns.add(EntityType.MUSHROOM_COW);
        dreadfulDirtSpawns.add(EntityType.CHICKEN);
        dreadfulDirtSpawns.add(EntityType.PIG);
        dreadfulDirtSpawns.add(EntityType.SHEEP);
        dreadfulDirtSpawns.add(EntityType.GOAT);
        dreadfulDirtSpawns.add(EntityType.AXOLOTL);
        dreadfulDirtSpawns.add(EntityType.DOLPHIN);
        dreadfulDirtSpawns.add(EntityType.TURTLE);
        dreadfulDirtSpawns.add(EntityType.CAT);
        dreadfulDirtSpawns.add(EntityType.WOLF);
        dreadfulDirtSpawns.add(EntityType.LLAMA);
        dreadfulDirtSpawns.add(EntityType.HORSE);

        soulfilledSoil = new CursedEarth(
            ItemGroups.GADGETS,
            CrystaStacks.SOULFILLED_SOIL,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_TRUE_EARTH, null,
                CrystaStacks.RUNE_TRUE_WATER, CrystaStacks.DREADFUL_DIRT, CrystaStacks.RUNE_CIRCLE,
                null, CrystaStacks.RUNE_SOUL, null
            },
            10,
            15,
            soulfilledSoilSpawns,
            Color.WHITE
        );

        // Searing Plate
        RecipeItem searingPlateRecipe = new RecipeItem(
            new ItemStack(Material.STONE_PRESSURE_PLATE),
            StoryType.ALCHEMICAL, 120,
            StoryType.VOID, 220,
            StoryType.MECHANICAL, 180
        );
        searingPlate = new MobMat(
            ItemGroups.GADGETS,
            CrystaStacks.SEARING_PLATE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            searingPlateRecipe.getDisplayRecipe(),
            1,
            false
        );

        // Doomed Plate
        RecipeItem doomedPlateRecipe = new RecipeItem(
            CrystaStacks.SEARING_PLATE,
            StoryType.ALCHEMICAL, 240,
            StoryType.VOID, 440,
            StoryType.MECHANICAL, 360
        );
        doomedPlate = new MobMat(
            ItemGroups.GADGETS,
            CrystaStacks.DOOMED_PLATE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            doomedPlateRecipe.getDisplayRecipe(),
            1,
            true
        );

        // Eviscerating Plate
        RecipeItem evisceratingPlateRecipe = new RecipeItem(
            CrystaStacks.DOOMED_PLATE,
            StoryType.ALCHEMICAL, 480,
            StoryType.VOID, 880,
            StoryType.MECHANICAL, 720
        );
        evisceratingPlate = new MobMat(
            ItemGroups.GADGETS,
            CrystaStacks.EVISCERATING_PLATE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            evisceratingPlateRecipe.getDisplayRecipe(),
            2,
            true
        );

        // Shredding Plate
        shreddingPlate = new MobMat(
            ItemGroups.GADGETS,
            CrystaStacks.SHREDDING_PLATE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_MOON, null,
                CrystaStacks.RUNE_SOVEREIGN, CrystaStacks.EVISCERATING_PLATE, CrystaStacks.RUNE_BLINKING,
                null, CrystaStacks.RUNE_CHARM, null
            },
            3,
            true
        );

        // Trap Plate
        RecipeItem trapPlateRecipe = new RecipeItem(
            CrystaStacks.EVISCERATING_PLATE,
            StoryType.ALCHEMICAL, 400,
            StoryType.CELESTIAL, 100,
            StoryType.MECHANICAL, 50
        );
        trapPlate = new MobTrap(
            ItemGroups.GADGETS,
            CrystaStacks.TRAP_PLATE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            trapPlateRecipe.getDisplayRecipe()
        );

        // Basic Exp Collector
        RecipeItem basicExpCollectorRecipe = new RecipeItem(
            SlimefunItems.EXP_COLLECTOR,
            StoryType.MECHANICAL, 150,
            StoryType.HUMAN, 200,
            StoryType.ANIMAL, 250
        );
        basicExpCollector = new ExpCollector(
            ItemGroups.GADGETS,
            CrystaStacks.EXP_COLLECTOR_BASIC,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            basicExpCollectorRecipe.getDisplayRecipe(),
            2500,
            4
        );

        // Infused Exp Collector
        RecipeItem infusedExpCollectorRecipe = new RecipeItem(
            CrystaStacks.EXP_COLLECTOR_BASIC,
            StoryType.MECHANICAL, 740,
            StoryType.HUMAN, 560,
            StoryType.ANIMAL, 885
        );
        infusedExpCollector = new ExpCollector(
            ItemGroups.GADGETS,
            CrystaStacks.EXP_COLLECTOR_INFUSED,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            infusedExpCollectorRecipe.getDisplayRecipe(),
            10000,
            8
        );

        // Infused Exp Collector
        arcaneExpCollector = new ExpCollector(
            ItemGroups.GADGETS,
            CrystaStacks.EXP_COLLECTOR_ARCANE,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_CHANGE, null,
                CrystaStacks.RUNE_DRAGON, CrystaStacks.EXP_COLLECTOR_INFUSED, CrystaStacks.RUNE_TRUE_FIRE,
                null, CrystaStacks.RUNE_TRUE_WIND, null
            },
            999999,
            8
        );

        // Basic Ender Inhibitor
        basicEnderInhibitor = new EnderInhibitor(
            ItemGroups.GADGETS,
            CrystaStacks.ENDER_INHIBITOR_BASIC,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CrystaStacks.UNCANNY_PEARL, CrystaStacks.UNCANNY_PEARL, CrystaStacks.UNCANNY_PEARL,
                CrystaStacks.UNCANNY_PEARL, SlimefunItems.COOLER, CrystaStacks.UNCANNY_PEARL,
                CrystaStacks.UNCANNY_PEARL, CrystaStacks.UNCANNY_PEARL, CrystaStacks.UNCANNY_PEARL
            },
            4
        );

        // Advanced Ender Inhibitor
        advancedEnderInhibitor = new EnderInhibitor(
            ItemGroups.GADGETS,
            CrystaStacks.ENDER_INHIBITOR_ADVANCED,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CrystaStacks.GILDED_PEARL, CrystaStacks.GILDED_PEARL, CrystaStacks.GILDED_PEARL,
                CrystaStacks.GILDED_PEARL, CrystaStacks.ENDER_INHIBITOR_BASIC, CrystaStacks.GILDED_PEARL,
                CrystaStacks.GILDED_PEARL, CrystaStacks.GILDED_PEARL, CrystaStacks.GILDED_PEARL
            },
            8
        );

        // Dim Mob Candle
        RecipeItem dimMobCandleRecipe = new RecipeItem(
            new ItemStack(Material.BLACK_CANDLE),
            StoryType.HISTORICAL, 50,
            StoryType.CELESTIAL, 50,
            StoryType.ANIMAL, 50
        );
        dimMobCandle = new MobCandle(
            ItemGroups.GADGETS,
            CrystaStacks.MOB_CANDLE_DIM,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            dimMobCandleRecipe.getDisplayRecipe(),
            16,
            7200
        );

        // Bright Mob Candle
        RecipeItem brightMobCandleRecipe = new RecipeItem(
            CrystaStacks.MOB_CANDLE_DIM,
            StoryType.HISTORICAL, 100,
            StoryType.CELESTIAL, 100,
            StoryType.ANIMAL, 100
        );
        brightMobCandle = new MobCandle(
            ItemGroups.GADGETS,
            CrystaStacks.MOB_CANDLE_BRIGHT,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            brightMobCandleRecipe.getDisplayRecipe(),
            32,
            86_400
        );

        // Scintillating Mob Candle
        RecipeItem scintillatingMobCandleRecipe = new RecipeItem(
            CrystaStacks.MOB_CANDLE_BRIGHT,
            StoryType.HISTORICAL, 200,
            StoryType.CELESTIAL, 200,
            StoryType.ANIMAL, 200
        );
        scintillatingMobCandle = new MobCandle(
            ItemGroups.GADGETS,
            CrystaStacks.MOB_CANDLE_SCINTILLATING,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            scintillatingMobCandleRecipe.getDisplayRecipe(),
            64,
            172_800
        );

        // Coruscating Mob Candle
        coruscatingMobCandle = new MobCandle(
            ItemGroups.GADGETS,
            CrystaStacks.MOB_CANDLE_CORUSCATING,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_BEAST, null,
                CrystaStacks.RUNE_BLACK, CrystaStacks.MOB_CANDLE_SCINTILLATING, CrystaStacks.RUNE_DAWN,
                null, CrystaStacks.RUNE_EIGHTFOLD, null
            },
            64,
            1_209_600
        );

        // Mysterious Potted Plant
        mysteriousPottedPlant = new MysteriousTickerNoInteraction(
            ItemGroups.GADGETS,
            CrystaStacks.MYSTERIOUS_POTTED_PLANT,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, CrystaStacks.AMALGAMATE_DUST_RARE, null,
                null, new ItemStack(Material.FLOWER_POT), null,
                null, null, null
            },
            Tag.FLOWER_POTS.getValues(),
            15,
            block -> ParticleUtils.displayParticleEffect(
                block.getLocation().add(0.5, 0.5, 0.5),
                Particle.WAX_OFF,
                0.3,
                2
            )
        );

        // Mysterious Plant
        RecipeItem mysteriousPlantRecipe = new RecipeItem(
            CrystaStacks.MYSTERIOUS_POTTED_PLANT,
            StoryType.ELEMENTAL, 25,
            StoryType.ALCHEMICAL, 25,
            StoryType.VOID, 25
        );
        mysteriousPlant = new MysteriousTicker(
            ItemGroups.GADGETS,
            CrystaStacks.MYSTERIOUS_PLANT,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            mysteriousPlantRecipe.getDisplayRecipe(),
            Tag.SMALL_FLOWERS.getValues(),
            15,
            block -> ParticleUtils.displayParticleEffect(
                block.getLocation().add(0.5, 0.5, 0.5),
                Particle.WATER_SPLASH,
                0.5,
                3
            )
        );

        // Mysterious Glass
        RecipeItem mysteriousGlassRecipe = new RecipeItem(
            new ItemStack(Material.GLASS),
            StoryType.MECHANICAL, 5,
            StoryType.ALCHEMICAL, 5,
            StoryType.PHILOSOPHICAL, 5
        );
        mysteriousGlass = new MysteriousTicker(
            ItemGroups.GADGETS,
            CrystaStacks.MYSTERIOUS_GLASS,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            mysteriousGlassRecipe.getDisplayRecipe(),
            SlimefunTag.GLASS_BLOCKS.getValues(),
            15
        );

        // Mysterious Wool
        RecipeItem mysteriousWoolRecipe = new RecipeItem(
            new ItemStack(Material.WHITE_WOOL),
            StoryType.MECHANICAL, 5,
            StoryType.ALCHEMICAL, 5,
            StoryType.PHILOSOPHICAL, 5
        );
        mysteriousWool = new MysteriousTicker(
            ItemGroups.GADGETS,
            CrystaStacks.MYSTERIOUS_WOOL,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            mysteriousWoolRecipe.getDisplayRecipe(),
            SlimefunTag.WOOL.getValues(),
            15
        );

        // Mysterious Terracotta
        RecipeItem mysteriousTerracottaRecipe = new RecipeItem(
            new ItemStack(Material.TERRACOTTA),
            StoryType.MECHANICAL, 5,
            StoryType.ALCHEMICAL, 5,
            StoryType.PHILOSOPHICAL, 5
        );
        mysteriousTerracotta = new MysteriousTicker(
            ItemGroups.GADGETS,
            CrystaStacks.MYSTERIOUS_TERRACOTTA,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            mysteriousTerracottaRecipe.getDisplayRecipe(),
            SlimefunTag.TERRACOTTA.getValues(),
            15
        );

        // Mysterious Glazed Terracotta
        RecipeItem mysteriousGlazedTerracottaRecipe = new RecipeItem(
            new ItemStack(Material.WHITE_GLAZED_TERRACOTTA),
            StoryType.MECHANICAL, 5,
            StoryType.ALCHEMICAL, 5,
            StoryType.PHILOSOPHICAL, 5
        );
        mysteriousGlazedTerracotta = new MysteriousTicker(
            ItemGroups.GADGETS,
            CrystaStacks.MYSTERIOUS_GLAZED_TERRACOTTA,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            mysteriousGlazedTerracottaRecipe.getDisplayRecipe(),
            CrystaTag.GLAZED_TERRACOTTA.getValues(),
            15
        );

        // Mysterious Concrete
        RecipeItem mysteriousConcreteRecipe = new RecipeItem(
            new ItemStack(Material.WHITE_CONCRETE),
            StoryType.MECHANICAL, 5,
            StoryType.ALCHEMICAL, 5,
            StoryType.PHILOSOPHICAL, 5
        );
        mysteriousConcrete = new MysteriousTicker(
            ItemGroups.GADGETS,
            CrystaStacks.MYSTERIOUS_CONCRETE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            mysteriousConcreteRecipe.getDisplayRecipe(),
            CrystaTag.CONCRETE_BLOCKS.getValues(),
            15
        );

        // Green House Glass
        greenHouseGlass = new GreenHouseGlass(
            ItemGroups.GADGETS,
            CrystaStacks.GREEN_HOUSE_GLASS,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_DUST_EPIC, new ItemStack(Material.GLASS), CrystaStacks.AMALGAMATE_DUST_EPIC,
                new ItemStack(Material.GLASS), SlimefunItems.POWER_CRYSTAL, new ItemStack(Material.GLASS),
                CrystaStacks.AMALGAMATE_DUST_EPIC, new ItemStack(Material.GLASS), CrystaStacks.AMALGAMATE_DUST_EPIC,
            },
            5
        );

        // Focused Green House Glass
        RecipeItem focusedGreenHouseGlassRecipe = new RecipeItem(
            CrystaStacks.GREEN_HOUSE_GLASS,
            StoryType.ALCHEMICAL, 15,
            StoryType.ANIMAL, 40,
            StoryType.PHILOSOPHICAL, 30
        );
        focusedGreenHouseGlass = new GreenHouseGlass(
            ItemGroups.GADGETS,
            CrystaStacks.GREEN_HOUSE_GLASS_FOCUSED,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            focusedGreenHouseGlassRecipe.getDisplayRecipe(),
            10
        );

        // Magnifying Green House Glass
        magnifyingGreenHouseGlass = new GreenHouseGlass(
            ItemGroups.GADGETS,
            CrystaStacks.GREEN_HOUSE_GLASS_MAGNIFYING,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                null, CrystaStacks.RUNE_BEGINNING, null,
                CrystaStacks.RUNE_NIGHT, CrystaStacks.GREEN_HOUSE_GLASS_FOCUSED, CrystaStacks.RUNE_PUNISHMENT,
                null, CrystaStacks.RUNE_BLACK_SWORD, null
            },
            20
        );

        // Trophy Stand
        RecipeItem trophyDisplayRecipe = new RecipeItem(
            new ItemStack(Material.POLISHED_BLACKSTONE_BRICK_WALL),
            StoryType.MECHANICAL, 10,
            StoryType.HUMAN, 10,
            StoryType.PHILOSOPHICAL, 10
        );
        trophyDisplay = new TrophyDisplay(
            ItemGroups.GADGETS,
            CrystaStacks.TROPHY_STAND,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            trophyDisplayRecipe.getDisplayRecipe()
        );

        // Exaltation Stand
        RecipeItem exaltationStandRecipe = new RecipeItem(
            new ItemStack(Material.PRISMARINE_WALL),
            StoryType.MECHANICAL, 50,
            StoryType.HUMAN, 50,
            StoryType.PHILOSOPHICAL, 50
        );
        exaltationStand = new ExaltationStand(
            ItemGroups.GADGETS,
            CrystaStacks.EXALTATION_STAND,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            exaltationStandRecipe.getDisplayRecipe()
        );

        // Waystone
        final Set<Material> waystoneMaterials = new HashSet<>();
        waystoneMaterials.add(Material.RED_NETHER_BRICK_WALL);
        waystoneMaterials.add(Material.END_STONE_BRICK_WALL);
        waystoneMaterials.add(Material.DEEPSLATE_BRICK_WALL);
        waystoneMaterials.add(Material.POLISHED_BLACKSTONE_BRICK_WALL);
        waystoneMaterials.add(Material.STONE_BRICK_WALL);
        waystoneMaterials.add(Material.NETHER_BRICK_WALL);
        RecipeItem waystoneRecipe = new RecipeItem(
            new ItemStack(Material.LODESTONE),
            StoryType.HISTORICAL, 50,
            StoryType.HUMAN, 50,
            StoryType.CELESTIAL, 50
        );
        waystone = new Waystone(
            ItemGroups.GADGETS,
            CrystaStacks.WAYSTONE,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            waystoneRecipe.getDisplayRecipe(),
            waystoneMaterials,
            5,
            block -> ParticleUtils.displayParticleEffect(
                block.getLocation().add(0.5, 0.5, 0.5),
                Particle.PORTAL,
                0.5,
                3
            )
        );

        // AngelBlock
        angelBlock = new AngelBlock(
            ItemGroups.GADGETS,
            CrystaStacks.ANGEL_BLOCK,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS),
                new ItemStack(Material.GLASS), CrystaStacks.AMALGAMATE_INGOT_RARE, new ItemStack(Material.GLASS),
                new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS)
            },
            CrystaStacks.ANGEL_BLOCK.asQuantity(8)
        );

        // Philosophers Spray
        philosophersSpray = new PhilosophersSpray(
            ItemGroups.GADGETS,
            CrystaStacks.PHILOSOPHERS_SPRAY,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                CrystaStacks.AMALGAMATE_INGOT_RARE, CrystaStacks.AMALGAMATE_INGOT_RARE, CrystaStacks.AMALGAMATE_INGOT_RARE,
                CrystaStacks.AMALGAMATE_INGOT_RARE, new ItemStack(Material.DISPENSER), CrystaStacks.AMALGAMATE_INGOT_RARE,
                CrystaStacks.AMALGAMATE_INGOT_RARE, CrystaStacks.ARCANE_DISPLACER, CrystaStacks.AMALGAMATE_INGOT_RARE
            }
        );

        // Glass of Milk
        glassOfMilk = new GlassOfMilk(
            ItemGroups.GADGETS,
            CrystaStacks.GLASS_OF_MILK,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                glass, null, glass,
                glass, CrystaStacks.GLASS_OF_MILK, glass,
                glass, glass, glass
            }
        );

        // Fragmented Void
        RecipeItem fragmentedVoidRecipe = new RecipeItem(
            SlimefunItems.INFUSED_HOPPER,
            StoryType.VOID, 120,
            StoryType.MECHANICAL, 100,
            StoryType.CELESTIAL, 80
        );
        fragmentedVoid = new FragmentedVoid(
            ItemGroups.GADGETS,
            CrystaStacks.FRAGMENTED_VOID,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            fragmentedVoidRecipe.getDisplayRecipe(),
            5
        );

        // Shattered Void
        RecipeItem shatteredVoidRecipe = new RecipeItem(
            CrystaStacks.FRAGMENTED_VOID,
            StoryType.VOID, 120,
            StoryType.MECHANICAL, 100,
            StoryType.CELESTIAL, 80
        );
        shatteredVoid = new FragmentedVoid(
            ItemGroups.GADGETS,
            CrystaStacks.SHATTERED_VOID,
            CrystaRecipeTypes.LIQUEFACTION_CRAFTING,
            shatteredVoidRecipe.getDisplayRecipe(),
            7
        );

        // Slimefun Registry
        abstractionLamp.register(plugin);
        dispersionLamp.register(plugin);
        exodusLamp.register(plugin);
        inversionVacuum.register(plugin);
        antipodalVacuum.register(plugin);
        counterpoleVacuum.register(plugin);
        cursedEarth.register(plugin);
        dreadfulDirt.register(plugin);
        soulfilledSoil.register(plugin);
        searingPlate.register(plugin);
        doomedPlate.register(plugin);
        evisceratingPlate.register(plugin);
        shreddingPlate.register(plugin);
        trapPlate.register(plugin);
        basicExpCollector.register(plugin);
        infusedExpCollector.register(plugin);
        arcaneExpCollector.register(plugin);
        basicEnderInhibitor.register(plugin);
        advancedEnderInhibitor.register(plugin);
        dimMobCandle.register(plugin);
        brightMobCandle.register(plugin);
        scintillatingMobCandle.register(plugin);
        coruscatingMobCandle.register(plugin);
        mysteriousPottedPlant.register(plugin);
        mysteriousPlant.register(plugin);
        mysteriousGlass.register(plugin);
        mysteriousWool.register(plugin);
        mysteriousTerracotta.register(plugin);
        mysteriousGlazedTerracotta.register(plugin);
        mysteriousConcrete.register(plugin);
        greenHouseGlass.register(plugin);
        focusedGreenHouseGlass.register(plugin);
        magnifyingGreenHouseGlass.register(plugin);
        trophyDisplay.register(plugin);
        exaltationStand.register(plugin);
        waystone.register(plugin);
        angelBlock.register(plugin);
        philosophersSpray.register(plugin);
        glassOfMilk.register(plugin);
        fragmentedVoid.register(plugin);
        shatteredVoid.register(plugin);

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(abstractionLamp, abstractionLampRecipe);
        LiquefactionBasinCache.addCraftingRecipe(dispersionLamp, dispersionLampRecipe);

        LiquefactionBasinCache.addCraftingRecipe(inversionVacuum, inversionVacuumRecipe);
        LiquefactionBasinCache.addCraftingRecipe(antipodalVacuum, antipodalVacuumRecipe);

        LiquefactionBasinCache.addCraftingRecipe(dreadfulDirt, dreadfulDirtRecipe);

        LiquefactionBasinCache.addCraftingRecipe(searingPlate, searingPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(doomedPlate, doomedPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(evisceratingPlate, evisceratingPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(trapPlate, trapPlateRecipe);

        LiquefactionBasinCache.addCraftingRecipe(basicExpCollector, basicExpCollectorRecipe);
        LiquefactionBasinCache.addCraftingRecipe(infusedExpCollector, infusedExpCollectorRecipe);

        LiquefactionBasinCache.addCraftingRecipe(dimMobCandle, dimMobCandleRecipe);
        LiquefactionBasinCache.addCraftingRecipe(brightMobCandle, brightMobCandleRecipe);
        LiquefactionBasinCache.addCraftingRecipe(scintillatingMobCandle, scintillatingMobCandleRecipe);

        LiquefactionBasinCache.addCraftingRecipe(mysteriousPlant, mysteriousPlantRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousGlass, mysteriousPlantRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousWool, mysteriousPlantRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousTerracotta, mysteriousPlantRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousGlazedTerracotta, mysteriousPlantRecipe);
        LiquefactionBasinCache.addCraftingRecipe(mysteriousConcrete, mysteriousPlantRecipe);

        LiquefactionBasinCache.addCraftingRecipe(focusedGreenHouseGlass, focusedGreenHouseGlassRecipe);

        LiquefactionBasinCache.addCraftingRecipe(trophyDisplay, trophyDisplayRecipe);
        LiquefactionBasinCache.addCraftingRecipe(exaltationStand, exaltationStandRecipe);

        LiquefactionBasinCache.addCraftingRecipe(waystone, waystoneRecipe);

        LiquefactionBasinCache.addCraftingRecipe(fragmentedVoid, fragmentedVoidRecipe);
        LiquefactionBasinCache.addCraftingRecipe(shatteredVoid, shatteredVoidRecipe);
    }
}
