package io.github.sefiraat.crystamaehistoria.slimefun;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.chroniclerpanel.ChroniclerPanel;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets.CursedEarth;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets.MobFan;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets.MobLamp;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.gadgets.MobMat;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.DummyLiquefactionBasinCrafting;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasin;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.LiquefactionBasinCache;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeItem;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.realisationaltar.RealisationAltar;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.staveconfigurator.StaveConfigurator;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        final ItemStack uniqueVoid = Materials.CRYSTAL_MAP.get(StoryRarity.UNIQUE).get(StoryType.VOID).getItem();
        final ItemStack amalgamateDustRare = Materials.getAmalgamateDustRare().getItem();

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
                ThemeType.MECHANISM,
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
                ThemeType.MECHANISM,
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
                ThemeType.MECHANISM,
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
                ThemeType.MECHANISM,
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
                ThemeType.MECHANISM,
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
                ThemeType.MECHANISM,
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
                ThemeType.MECHANISM,
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
                ThemeType.MECHANISM,
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

        // Doomed Plate
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
                ThemeType.MECHANISM,
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

        // Liquefaction Recipes
        LiquefactionBasinCache.addCraftingRecipe(abstractionLamp, abstractionLampRecipe);
        LiquefactionBasinCache.addCraftingRecipe(dispersionLamp, dispersionLampRecipe);

        LiquefactionBasinCache.addCraftingRecipe(inversionVacuum, inversionVacuumRecipe);
        LiquefactionBasinCache.addCraftingRecipe(antipodalVacuum, antipodalVacuumRecipe);

        LiquefactionBasinCache.addCraftingRecipe(dreadfulDirt, dreadfulDirtRecipe);

        LiquefactionBasinCache.addCraftingRecipe(searingPlate, searingPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(doomedPlate, doomedPlateRecipe);
        LiquefactionBasinCache.addCraftingRecipe(evisceratingPlate, evisceratingPlateRecipe);
    }
}
