package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.AngelBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.CursedEarth;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.EnderInhibitor;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.ExpCollector;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.GreenHouseGlass;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.MobCandle;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.MobFan;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.MobLamp;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.MobMat;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.MobTrap;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.MysteriousTicker;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.TrophyDisplay;
import io.github.sefiraat.crystamaehistoria.slimefun.gadgets.Waystone;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
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
    private static MobFan inversionVacuum;
    @Getter
    private static MobFan antipodalVacuum;
    @Getter
    private static CursedEarth cursedEarth;
    @Getter
    private static CursedEarth dreadfulDirt;
    @Getter
    private static MobMat searingPlate;
    @Getter
    private static MobMat doomedPlate;
    @Getter
    private static MobMat evisceratingPlate;
    @Getter
    private static MobTrap trapPlate;
    @Getter
    private static ExpCollector basicExpCollector;
    @Getter
    private static ExpCollector infusedExpCollector;
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
    private static MysteriousTicker mysteriousPottedPlant;
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
    private static TrophyDisplay trophyDisplay;
    @Getter
    private static Waystone waystone;
    @Getter
    private static AngelBlock angelBlock;

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack uniqueVoid = Materials.CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.VOID).getItem();
        final ItemStack amalgamateDustRare = Materials.getAmalgamateDustRare().getItem();
        final ItemStack amalgamateIngotRare = Materials.getAmalgamateIngotRare().getItem();
        final ItemStack amalgamateDustEpic = Materials.getAmalgamateDustEpic().getItem();

        // Abstraction Lamp
        RecipeItem abstractionLampRecipe = new RecipeItem(
            new ItemStack(Material.LANTERN),
            StoryType.ALCHEMICAL, 50,
            StoryType.HUMAN, 75,
            StoryType.PHILOSOPHICAL, 75
        );
        abstractionLamp = new MobLamp(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_LAMP_1",
                new ItemStack(Material.LANTERN),
                ThemeType.GADGET,
                "Abstraction Lamp",
                "The abstraction lamp will push all",
                "nearby mobs away from it.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "5 Blocks",
                ThemeType.CLICK_INFO.getColor() + "Force: " + ThemeType.PASSIVE.getColor() + "3 CrystaPow™"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            abstractionLampRecipe.getDisplayRecipe(),
            5,
            0.3
        );

        // Dispersion Lamp
        RecipeItem dispersionLampRecipe = new RecipeItem(
            abstractionLamp.getItem(),
            StoryType.ALCHEMICAL, 250,
            StoryType.HUMAN, 150,
            StoryType.PHILOSOPHICAL, 300
        );
        dispersionLamp = new MobLamp(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_LAMP_2",
                new ItemStack(Material.SOUL_LANTERN),
                ThemeType.GADGET,
                "Dispersion Lamp",
                "The dispersion lamp will push all",
                "nearby mobs away from it.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "7 Blocks",
                ThemeType.CLICK_INFO.getColor() + "Force: " + ThemeType.PASSIVE.getColor() + "5 CrystaPow™"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            abstractionLampRecipe.getDisplayRecipe(),
            7,
            0.5
        );

        // Inversion Vacuum
        RecipeItem inversionVacuumRecipe = new RecipeItem(
            abstractionLamp.getItem(),
            StoryType.HISTORICAL, 200,
            StoryType.VOID, 200,
            StoryType.PHILOSOPHICAL, 180
        );
        inversionVacuum = new MobFan(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_FAN_1",
                new ItemStack(Material.REDSTONE_LAMP),
                ThemeType.GADGET,
                "Inversion Vacuum",
                "Creates a magically induced vacuum",
                "that pulls entities away from the",
                "mechanism.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Push Distance: " + ThemeType.PASSIVE.getColor() + "5 Blocks"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            inversionVacuumRecipe.getDisplayRecipe(),
            5
        );

        // Antipodal Vacuum
        RecipeItem antipodalVacuumRecipe = new RecipeItem(
            dispersionLamp.getItem(),
            StoryType.HISTORICAL, 400,
            StoryType.VOID, 400,
            StoryType.PHILOSOPHICAL, 360
        );
        antipodalVacuum = new MobFan(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_FAN_2",
                new ItemStack(Material.NOTE_BLOCK),
                ThemeType.GADGET,
                "Antipodal Vacuum",
                "Creates a magically induced vacuum",
                "that pulls entities away from the",
                "mechanism.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Push Distance: " + ThemeType.PASSIVE.getColor() + "10 Blocks"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            antipodalVacuumRecipe.getDisplayRecipe(),
            10
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
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_DIRT_1",
                new ItemStack(Material.BROWN_WOOL),
                ThemeType.GADGET,
                "Cursed Earth",
                "Dark magics seep from this dirt giving",
                "a very ominous vibe!",
                "",
                ThemeType.CLICK_INFO.getColor() + "Tick Rate: " + ThemeType.PASSIVE.getColor() + "20",
                ThemeType.CLICK_INFO.getColor() + "Light Level: " + ThemeType.PASSIVE.getColor() + "7",
                ThemeType.CLICK_INFO.getColor() + "Spawns: " + ThemeType.PASSIVE.getColor() + "Basic"
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                uniqueVoid, uniqueVoid, uniqueVoid,
                new ItemStack(Material.DIRT), new ItemStack(Material.DIRT), new ItemStack(Material.DIRT),
                amalgamateDustRare, amalgamateDustRare, amalgamateDustRare
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
            cursedEarth.getItem(),
            StoryType.VOID, 700,
            StoryType.ANIMAL, 200,
            StoryType.HISTORICAL, 100
        );
        dreadfulDirt = new CursedEarth(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_DIRT_2",
                new ItemStack(Material.BLACK_WOOL),
                ThemeType.GADGET,
                "Dreadful Dirt",
                "Dark magics seep from this dirt giving",
                "a very ominous vibe!",
                "",
                ThemeType.CLICK_INFO.getColor() + "Tick Rate: " + ThemeType.PASSIVE.getColor() + "10",
                ThemeType.CLICK_INFO.getColor() + "Light Level: " + ThemeType.PASSIVE.getColor() + "15",
                ThemeType.CLICK_INFO.getColor() + "Spawns: " + ThemeType.PASSIVE.getColor() + "Advanced"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            dreadfulDirtRecipe.getDisplayRecipe(),
            10,
            15,
            dreadfulDirtSpawns,
            Color.BLACK
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
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_PLATE_1",
                new ItemStack(Material.CRIMSON_PRESSURE_PLATE),
                ThemeType.GADGET,
                "Searing Plate",
                "A plate that is magically super-heated.",
                "Anything standing on this plate gets",
                "damaged.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Damage: " + ThemeType.PASSIVE.getColor() + "1",
                ThemeType.CLICK_INFO.getColor() + "Player Drops: " + ThemeType.PASSIVE.getColor() + "No"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            searingPlateRecipe.getDisplayRecipe(),
            1,
            false
        );

        // Doomed Plate
        RecipeItem doomedPlateRecipe = new RecipeItem(
            searingPlate.getItem(),
            StoryType.ALCHEMICAL, 240,
            StoryType.VOID, 440,
            StoryType.MECHANICAL, 360
        );
        doomedPlate = new MobMat(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_PLATE_2",
                new ItemStack(Material.WARPED_PRESSURE_PLATE),
                ThemeType.GADGET,
                "Doomed Plate",
                "A plate that is magically super-heated.",
                "Anything standing on this plate gets",
                "damaged.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Damage: " + ThemeType.PASSIVE.getColor() + "1",
                ThemeType.CLICK_INFO.getColor() + "Player Drops: " + ThemeType.PASSIVE.getColor() + "Yes"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            doomedPlateRecipe.getDisplayRecipe(),
            1,
            true
        );

        // Eviscerating Plate
        RecipeItem evisceratingPlateRecipe = new RecipeItem(
            doomedPlate.getItem(),
            StoryType.ALCHEMICAL, 480,
            StoryType.VOID, 880,
            StoryType.MECHANICAL, 720
        );
        evisceratingPlate = new MobMat(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_PLATE_3",
                new ItemStack(Material.POLISHED_BLACKSTONE_PRESSURE_PLATE),
                ThemeType.GADGET,
                "Eviscerating Plate",
                "A plate that is magically super-heated.",
                "Anything standing on this plate gets",
                "damaged.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Damage: " + ThemeType.PASSIVE.getColor() + "2",
                ThemeType.CLICK_INFO.getColor() + "Player Drops: " + ThemeType.PASSIVE.getColor() + "Yes"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            evisceratingPlateRecipe.getDisplayRecipe(),
            2,
            true
        );

        // Trap Plate
        RecipeItem trapPlateRecipe = new RecipeItem(
            evisceratingPlate.getItem(),
            StoryType.ALCHEMICAL, 400,
            StoryType.CELESTIAL, 100,
            StoryType.MECHANICAL, 50
        );
        trapPlate = new MobTrap(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_PLATE_TRAP",
                new ItemStack(Material.DARK_OAK_PRESSURE_PLATE),
                ThemeType.GADGET,
                "Trap Plate",
                "This plate no longer deals",
                "damage but now applies potion",
                "effects. Right click with",
                "a potion to assign."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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
            ThemeType.themedSlimefunItemStack(
                "CRY_EXP_COLLECTOR_1",
                new ItemStack(Material.LIGHTNING_ROD),
                ThemeType.GADGET,
                "Basic Exp Collector",
                "Infusing the Exp Collector with",
                "magic now allows it to work",
                "without electricity and flasks.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "4",
                ThemeType.CLICK_INFO.getColor() + "Capacity: " + ThemeType.PASSIVE.getColor() + "2500"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            basicExpCollectorRecipe.getDisplayRecipe(),
            2500,
            4
        );

        // Infused Exp Collector Plate
        RecipeItem infusedExpCollectorRecipe = new RecipeItem(
            basicExpCollector.getItem(),
            StoryType.MECHANICAL, 740,
            StoryType.HUMAN, 560,
            StoryType.ANIMAL, 885
        );
        infusedExpCollector = new ExpCollector(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_EXP_COLLECTOR_2",
                new ItemStack(Material.LIGHTNING_ROD),
                ThemeType.GADGET,
                "Infused Exp Collector",
                "Further infusion has made the",
                "collector even more powerful.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "8",
                ThemeType.CLICK_INFO.getColor() + "Capacity: " + ThemeType.PASSIVE.getColor() + "10000"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            infusedExpCollectorRecipe.getDisplayRecipe(),
            10000,
            8
        );

        // Basic Ender Inhibitor
        basicEnderInhibitor = new EnderInhibitor(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_ENDER_INHIBITOR_1",
                new ItemStack(Material.REDSTONE_TORCH),
                ThemeType.GADGET,
                "Basic Ender Inhibitor",
                "By using an Enderman's own resonance",
                "against it, we can stop theme teleporting",
                "for a brief time.",
                "Duration is extended if still in range.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "4",
                ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "2 seconds"
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                Materials.getUncannyPearl().getItem(), Materials.getUncannyPearl().getItem(), Materials.getUncannyPearl().getItem(),
                Materials.getUncannyPearl().getItem(), SlimefunItems.COOLER, Materials.getUncannyPearl().getItem(),
                Materials.getUncannyPearl().getItem(), Materials.getUncannyPearl().getItem(), Materials.getUncannyPearl().getItem()
            },
            4
        );

        // Advanced Ender Inhibitor
        advancedEnderInhibitor = new EnderInhibitor(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_ENDER_INHIBITOR_2",
                new ItemStack(Material.SOUL_TORCH),
                ThemeType.GADGET,
                "Advanced Ender Inhibitor",
                "By using an Enderman's own resonance",
                "against it, we can stop theme teleporting",
                "for a brief time.",
                "Duration is extended if still in range.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "8",
                ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "2 seconds"
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                Materials.getGildedPearl().getItem(), Materials.getGildedPearl().getItem(), Materials.getGildedPearl().getItem(),
                Materials.getGildedPearl().getItem(), basicEnderInhibitor.getItem(), Materials.getGildedPearl().getItem(),
                Materials.getGildedPearl().getItem(), Materials.getGildedPearl().getItem(), Materials.getGildedPearl().getItem()
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
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_CANDLE_1",
                new ItemStack(Material.BLACK_CANDLE),
                ThemeType.GADGET,
                "Dim Verache Candle",
                "This candle stops mobs spawning",
                "in a radius around it.",
                "Doesn't last forever.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "16",
                ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "2 hour (real time)"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            dimMobCandleRecipe.getDisplayRecipe(),
            16,
            7200
        );

        // Bright Mob Candle
        RecipeItem brightMobCandleRecipe = new RecipeItem(
            dimMobCandle.getItem(),
            StoryType.HISTORICAL, 100,
            StoryType.CELESTIAL, 100,
            StoryType.ANIMAL, 100
        );
        brightMobCandle = new MobCandle(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_CANDLE_2",
                new ItemStack(Material.BROWN_CANDLE),
                ThemeType.GADGET,
                "Bright Verache Candle",
                "This candle stops mobs spawning",
                "in a radius around it.",
                "Doesn't last forever.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "32",
                ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "24 hours (real time)"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            brightMobCandleRecipe.getDisplayRecipe(),
            32,
            86_400
        );

        // Scintillating Mob Candle
        RecipeItem scintillatingMobCandleRecipe = new RecipeItem(
            brightMobCandle.getItem(),
            StoryType.HISTORICAL, 200,
            StoryType.CELESTIAL, 200,
            StoryType.ANIMAL, 200
        );
        scintillatingMobCandle = new MobCandle(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MOB_CANDLE_3",
                new ItemStack(Material.WHITE_CANDLE),
                ThemeType.GADGET,
                "Scintillating Verache Candle",
                "This candle stops mobs spawning",
                "in a radius around it.",
                "Doesn't last forever.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Range: " + ThemeType.PASSIVE.getColor() + "64",
                ThemeType.CLICK_INFO.getColor() + "Duration: " + ThemeType.PASSIVE.getColor() + "48 Hours (real time)"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            scintillatingMobCandleRecipe.getDisplayRecipe(),
            64,
            172_800
        );

        // Mysterious Potted Plant
        mysteriousPottedPlant = new MysteriousTicker(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MYSTERIOUS_POTTED_PLANT",
                new ItemStack(Material.FLOWER_POT),
                ThemeType.GADGET,
                "Mysterious Potted Plant",
                "Just a pinch of magic can make",
                "wonderful things happen..."
            ),
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, Materials.getAmalgamateDustRare().getItem(), null,
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
            mysteriousPottedPlant.getItem(),
            StoryType.ELEMENTAL, 25,
            StoryType.ALCHEMICAL, 25,
            StoryType.VOID, 25
        );
        mysteriousPlant = new MysteriousTicker(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MYSTERIOUS_PLANT",
                new ItemStack(Material.OXEYE_DAISY),
                ThemeType.GADGET,
                "Mysterious Plant",
                "Removing it from that pot took work."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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
            ThemeType.themedSlimefunItemStack(
                "CRY_MYSTERIOUS_GLASS",
                new ItemStack(Material.ORANGE_STAINED_GLASS),
                ThemeType.GADGET,
                "Mysterious Glass",
                "Like rainbow glass but far",
                "more random."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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
            ThemeType.themedSlimefunItemStack(
                "CRY_MYSTERIOUS_WOOL",
                new ItemStack(Material.ORANGE_WOOL),
                ThemeType.GADGET,
                "Mysterious Wool",
                "Like rainbow wool but far",
                "more random."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            mysteriousWoolRecipe.getDisplayRecipe(),
            SlimefunTag.WOOL.getValues(),
            15
        );

        // Mysterious Wool
        RecipeItem mysteriousTerracottaRecipe = new RecipeItem(
            new ItemStack(Material.TERRACOTTA),
            StoryType.MECHANICAL, 5,
            StoryType.ALCHEMICAL, 5,
            StoryType.PHILOSOPHICAL, 5
        );
        mysteriousTerracotta = new MysteriousTicker(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_MYSTERIOUS_TERRACOTTA",
                new ItemStack(Material.ORANGE_TERRACOTTA),
                ThemeType.GADGET,
                "Mysterious Terracotta",
                "Like rainbow terracotta but far",
                "more random."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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
            ThemeType.themedSlimefunItemStack(
                "CRY_MYSTERIOUS_GLAZED_TERRACOTTA",
                new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA),
                ThemeType.GADGET,
                "Mysterious Glazed Terracotta",
                "Like rainbow terracotta but far",
                "more random."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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
            ThemeType.themedSlimefunItemStack(
                "CRY_MYSTERIOUS_CONCRETE",
                new ItemStack(Material.ORANGE_CONCRETE),
                ThemeType.GADGET,
                "Mysterious Concrete",
                "Like rainbow concrete but far",
                "more random."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            mysteriousConcreteRecipe.getDisplayRecipe(),
            CrystaTag.CONCRETE_BLOCKS.getValues(),
            15
        );

        // Green House Glass
        greenHouseGlass = new GreenHouseGlass(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_CROP_GLASS_1",
                new ItemStack(Material.GLASS),
                ThemeType.GADGET,
                "Greenhouse Glass",
                "Crops under this glass will grow faster.",
                "Works during the day in light worlds only.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Rate: " + ThemeType.PASSIVE.getColor() + "5"
            ),
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                amalgamateDustEpic, new ItemStack(Material.GLASS), amalgamateDustEpic,
                new ItemStack(Material.GLASS), SlimefunItems.POWER_CRYSTAL, new ItemStack(Material.GLASS),
                amalgamateDustEpic, new ItemStack(Material.GLASS), amalgamateDustEpic,
            },
            5
        );

        // Focused Green House Glass
        RecipeItem focusedGreenHouseGlassRecipe = new RecipeItem(
            mysteriousPottedPlant.getItem(),
            StoryType.ALCHEMICAL, 15,
            StoryType.ANIMAL, 40,
            StoryType.PHILOSOPHICAL, 30
        );
        focusedGreenHouseGlass = new GreenHouseGlass(
            ItemGroups.GADGETS,
            ThemeType.themedSlimefunItemStack(
                "CRY_CROP_GLASS_2",
                new ItemStack(Material.YELLOW_STAINED_GLASS),
                ThemeType.GADGET,
                "Focused Greenhouse Glass",
                "Crops under this glass will grow faster.",
                "Works during the day in light worlds only.",
                "",
                ThemeType.CLICK_INFO.getColor() + "Rate: " + ThemeType.PASSIVE.getColor() + "10"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            focusedGreenHouseGlassRecipe.getDisplayRecipe(),
            10
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
            ThemeType.themedSlimefunItemStack(
                "CRY_TROPHY_DISPLAY_1",
                new ItemStack(Material.POLISHED_BLACKSTONE_BRICK_WALL),
                ThemeType.GADGET,
                "Trophy Display",
                "Used to place your trophies on to show",
                "off to the world.",
                "",
                "Currently allowable trophies include:",
                ThemeType.CLICK_INFO.getColor() + "A block in which you have a S.M.E. rank"
            ),
            DummyLiquefactionBasinCrafting.TYPE,
            trophyDisplayRecipe.getDisplayRecipe()
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
            ThemeType.themedSlimefunItemStack(
                "CRY_WAYSTONE",
                new ItemStack(Material.END_STONE_BRICK_WALL),
                ThemeType.GADGET,
                "Diverging Waystone",
                "Can be used as a marker to recall",
                "back to."
            ),
            DummyLiquefactionBasinCrafting.TYPE,
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
        SlimefunItemStack angelBlockStack = ThemeType.themedSlimefunItemStack(
            "CRY_ANGEL_BLOCK",
            new ItemStack(Material.GLASS),
            ThemeType.GADGET,
            "Angel Block",
            "Can be placed anywhere, even in",
            "the air."
        );
        angelBlock = new AngelBlock(
            ItemGroups.GADGETS,
            angelBlockStack,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS),
                new ItemStack(Material.GLASS), amalgamateIngotRare, new ItemStack(Material.GLASS),
                new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS)
            },
            angelBlockStack.asQuantity(8)
        );

        // Slimefun Registry
        abstractionLamp.register(plugin);
        dispersionLamp.register(plugin);
        inversionVacuum.register(plugin);
        antipodalVacuum.register(plugin);
        cursedEarth.register(plugin);
        dreadfulDirt.register(plugin);
        searingPlate.register(plugin);
        doomedPlate.register(plugin);
        evisceratingPlate.register(plugin);
        trapPlate.register(plugin);
        basicExpCollector.register(plugin);
        infusedExpCollector.register(plugin);
        basicEnderInhibitor.register(plugin);
        advancedEnderInhibitor.register(plugin);
        dimMobCandle.register(plugin);
        brightMobCandle.register(plugin);
        scintillatingMobCandle.register(plugin);
        mysteriousPottedPlant.register(plugin);
        mysteriousPlant.register(plugin);
        mysteriousGlass.register(plugin);
        mysteriousWool.register(plugin);
        mysteriousTerracotta.register(plugin);
        mysteriousGlazedTerracotta.register(plugin);
        mysteriousConcrete.register(plugin);
        greenHouseGlass.register(plugin);
        focusedGreenHouseGlass.register(plugin);
        trophyDisplay.register(plugin);
        waystone.register(plugin);
        angelBlock.register(plugin);

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

        LiquefactionBasinCache.addCraftingRecipe(waystone, waystoneRecipe);
    }

}
