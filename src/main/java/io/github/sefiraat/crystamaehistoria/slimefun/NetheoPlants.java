package io.github.sefiraat.crystamaehistoria.slimefun;

import dev.sefiraat.netheopoiesis.api.items.HarvestableSeed;
import dev.sefiraat.netheopoiesis.api.plant.Growth;
import dev.sefiraat.netheopoiesis.api.plant.GrowthStages;
import dev.sefiraat.netheopoiesis.api.plant.Placements;
import dev.sefiraat.netheopoiesis.implementation.Stacks;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NetheoPlants {

    // Stacks
    public static final SlimefunItemStack ELEMENTAL_COMMON_SEED = Theme.themedSeed(
        "CRY_ELEMENTAL_COMMON_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Common Elemental Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Elemental Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack MECHANICAL_COMMON_SEED = Theme.themedSeed(
        "CRY_MECHANICAL_COMMON_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Common Mechanical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Mechanical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ALCHEMICAL_COMMON_SEED = Theme.themedSeed(
        "CRY_ALCHEMICAL_COMMON_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Common Alchemical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Alchemical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HISTORICAL_COMMON_SEED = Theme.themedSeed(
        "CRY_HISTORICAL_COMMON_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Common Historical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Historical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HUMAN_COMMON_SEED = Theme.themedSeed(
        "CRY_HUMAN_COMMON_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Common Human Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Human Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ANIMAL_COMMON_SEED = Theme.themedSeed(
        "CRY_ANIMAL_COMMON_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Common Animal Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Animal Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack CELESTIAL_COMMON_SEED = Theme.themedSeed(
        "CRY_CELESTIAL_COMMON_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Common Celestial Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Celestial Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack VOID_COMMON_SEED = Theme.themedSeed(
        "CRY_VOID_COMMON_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Common Void Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Void Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack PHILOSOPHICAL_COMMON_SEED = Theme.themedSeed(
        "CRY_PHILOSOPHICAL_COMMON_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Common Philosophical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Philosophical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );
    
    public static final SlimefunItemStack ELEMENTAL_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_ELEMENTAL_UNCOMMON_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Uncommon Elemental Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Elemental Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack MECHANICAL_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_MECHANICAL_UNCOMMON_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Uncommon Mechanical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Mechanical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ALCHEMICAL_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_ALCHEMICAL_UNCOMMON_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Uncommon Alchemical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Alchemical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HISTORICAL_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_HISTORICAL_UNCOMMON_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Uncommon Historical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Historical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HUMAN_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_HUMAN_UNCOMMON_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Uncommon Human Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Human Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ANIMAL_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_ANIMAL_UNCOMMON_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Uncommon Animal Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Animal Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack CELESTIAL_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_CELESTIAL_UNCOMMON_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Uncommon Celestial Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Celestial Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack VOID_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_VOID_UNCOMMON_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Uncommon Void Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Void Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack PHILOSOPHICAL_UNCOMMON_SEED = Theme.themedSeed(
        "CRY_PHILOSOPHICAL_UNCOMMON_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Uncommon Philosophical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Philosophical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );
    
    public static final SlimefunItemStack ELEMENTAL_RARE_SEED = Theme.themedSeed(
        "CRY_ELEMENTAL_RARE_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Rare Elemental Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Elemental Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack MECHANICAL_RARE_SEED = Theme.themedSeed(
        "CRY_MECHANICAL_RARE_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Rare Mechanical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Mechanical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ALCHEMICAL_RARE_SEED = Theme.themedSeed(
        "CRY_ALCHEMICAL_RARE_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Rare Alchemical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Alchemical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HISTORICAL_RARE_SEED = Theme.themedSeed(
        "CRY_HISTORICAL_RARE_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Rare Historical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Historical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HUMAN_RARE_SEED = Theme.themedSeed(
        "CRY_HUMAN_RARE_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Rare Human Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Human Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ANIMAL_RARE_SEED = Theme.themedSeed(
        "CRY_ANIMAL_RARE_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Rare Animal Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Animal Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack CELESTIAL_RARE_SEED = Theme.themedSeed(
        "CRY_CELESTIAL_RARE_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Rare Celestial Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Celestial Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack VOID_RARE_SEED = Theme.themedSeed(
        "CRY_VOID_RARE_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Rare Void Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Void Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack PHILOSOPHICAL_RARE_SEED = Theme.themedSeed(
        "CRY_PHILOSOPHICAL_RARE_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Rare Philosophical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Philosophical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );
    
    public static final SlimefunItemStack ELEMENTAL_EPIC_SEED = Theme.themedSeed(
        "CRY_ELEMENTAL_EPIC_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Epic Elemental Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Elemental Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack MECHANICAL_EPIC_SEED = Theme.themedSeed(
        "CRY_MECHANICAL_EPIC_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Epic Mechanical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Mechanical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ALCHEMICAL_EPIC_SEED = Theme.themedSeed(
        "CRY_ALCHEMICAL_EPIC_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Epic Alchemical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Alchemical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HISTORICAL_EPIC_SEED = Theme.themedSeed(
        "CRY_HISTORICAL_EPIC_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Epic Historical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Historical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HUMAN_EPIC_SEED = Theme.themedSeed(
        "CRY_HUMAN_EPIC_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Epic Human Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Human Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ANIMAL_EPIC_SEED = Theme.themedSeed(
        "CRY_ANIMAL_EPIC_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Epic Animal Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Animal Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack CELESTIAL_EPIC_SEED = Theme.themedSeed(
        "CRY_CELESTIAL_EPIC_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Epic Celestial Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Celestial Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack VOID_EPIC_SEED = Theme.themedSeed(
        "CRY_VOID_EPIC_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Epic Void Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Void Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack PHILOSOPHICAL_EPIC_SEED = Theme.themedSeed(
        "CRY_PHILOSOPHICAL_EPIC_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Epic Philosophical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Philosophical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );
    
    public static final SlimefunItemStack ELEMENTAL_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_ELEMENTAL_MYTHICAL_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Mythical Elemental Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Elemental Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack MECHANICAL_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_MECHANICAL_MYTHICAL_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Mythical Mechanical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Mechanical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ALCHEMICAL_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_ALCHEMICAL_MYTHICAL_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Mythical Alchemical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Alchemical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HISTORICAL_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_HISTORICAL_MYTHICAL_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Mythical Historical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Historical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack HUMAN_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_HUMAN_MYTHICAL_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Mythical Human Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Human Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack ANIMAL_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_ANIMAL_MYTHICAL_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Mythical Animal Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Animal Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack CELESTIAL_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_CELESTIAL_MYTHICAL_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Mythical Celestial Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Celestial Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack VOID_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_VOID_MYTHICAL_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Mythical Void Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Void Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static final SlimefunItemStack PHILOSOPHICAL_MYTHICAL_SEED = Theme.themedSeed(
        "CRY_PHILOSOPHICAL_MYTHICAL_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Mythical Philosophical Crysta Seed",
        new String[]{"This seed, when fully grown, will", "provide Philosophical Crysta when", "harvested."},
        Stacks.getCanBePlacedOnLore("Nether Grass (or better)")
    );

    public static void setup() {

        final CrystamaeHistoria plugin = CrystamaeHistoria.getInstance();

        new HarvestableSeed(ELEMENTAL_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.ELEMENTAL).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_RED, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(5, 2, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(MECHANICAL_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.MECHANICAL).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_ORANGE, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(2, 5, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(ALCHEMICAL_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.ALCHEMICAL).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_YELLOW, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(2, 2, 5, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HISTORICAL_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.HISTORICAL).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_GREEN, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(2, 2, 2, 5, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HUMAN_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.HUMAN).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(2, 2, 2, 2, 5)
            .tryRegister(plugin);

        new HarvestableSeed(ANIMAL_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.ANIMAL).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_CYAN, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(4, 4, 3, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(CELESTIAL_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.CELESTIAL).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_YELLOW, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(2, 4, 4, 3, 2)
            .tryRegister(plugin);

        new HarvestableSeed(VOID_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.VOID).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_PURPLE, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(2, 2, 4, 4, 3)
            .tryRegister(plugin);

        new HarvestableSeed(PHILOSOPHICAL_COMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.COMMON).get(StoryType.PHILOSOPHICAL).getItem())
            .setGrowth(new Growth(GrowthStages.VINEY_PURPLE, Placements.NETHER_GRASS_AND_UP, 5, 0.05))
            .addBreedingPair(Stacks.CRYSTALLINE_SEED.getItemId(), Stacks.CRYSTALLINE_SEED.getItemId(), 0.1, 0)
            .addFlavourProfile(3, 2, 2, 4, 4)
            .tryRegister(plugin);

        new HarvestableSeed(ELEMENTAL_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.ELEMENTAL).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_RED, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(ELEMENTAL_COMMON_SEED.getItemId(), ELEMENTAL_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(10, 2, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(MECHANICAL_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.MECHANICAL).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_ORANGE, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(MECHANICAL_COMMON_SEED.getItemId(), MECHANICAL_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(2, 10, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(ALCHEMICAL_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.ALCHEMICAL).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_YELLOW, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(ALCHEMICAL_COMMON_SEED.getItemId(), ALCHEMICAL_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(2, 2, 10, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HISTORICAL_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.HISTORICAL).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_GREEN, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(HISTORICAL_COMMON_SEED.getItemId(), HISTORICAL_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(2, 2, 2, 10, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HUMAN_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.HUMAN).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_BLUE, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(HUMAN_COMMON_SEED.getItemId(), HUMAN_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(2, 2, 2, 2, 10)
            .tryRegister(plugin);

        new HarvestableSeed(ANIMAL_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.ANIMAL).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_CYAN, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(ANIMAL_COMMON_SEED.getItemId(), ANIMAL_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(6, 6, 4, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(CELESTIAL_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.CELESTIAL).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_YELLOW, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(CELESTIAL_COMMON_SEED.getItemId(), CELESTIAL_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(2, 6, 6, 4, 2)
            .tryRegister(plugin);

        new HarvestableSeed(VOID_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.VOID).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_PURPLE, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(VOID_COMMON_SEED.getItemId(), VOID_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(2, 2, 6, 6, 4)
            .tryRegister(plugin);

        new HarvestableSeed(PHILOSOPHICAL_UNCOMMON_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.UNCOMMON).get(StoryType.PHILOSOPHICAL).getItem())
            .setGrowth(new Growth(GrowthStages.FUNGAL_PURPLE, Placements.NETHER_GRASS_AND_UP, 10, 0.01))
            .addBreedingPair(PHILOSOPHICAL_COMMON_SEED.getItemId(), PHILOSOPHICAL_COMMON_SEED.getItemId(), 0.05, 0.01)
            .addFlavourProfile(4, 2, 2, 6, 6)
            .tryRegister(plugin);

        new HarvestableSeed(ELEMENTAL_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.ELEMENTAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_RED, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(ELEMENTAL_UNCOMMON_SEED.getItemId(), ELEMENTAL_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(20, 2, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(MECHANICAL_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.MECHANICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_ORANGE, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(MECHANICAL_UNCOMMON_SEED.getItemId(), MECHANICAL_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(2, 20, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(ALCHEMICAL_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.ALCHEMICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_YELLOW, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(ALCHEMICAL_UNCOMMON_SEED.getItemId(), ALCHEMICAL_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(2, 2, 20, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HISTORICAL_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.HISTORICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_GREEN, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(HISTORICAL_UNCOMMON_SEED.getItemId(), HISTORICAL_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(2, 2, 2, 20, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HUMAN_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.HUMAN).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_BLUE, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(HUMAN_UNCOMMON_SEED.getItemId(), HUMAN_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(2, 2, 2, 2, 20)
            .tryRegister(plugin);

        new HarvestableSeed(ANIMAL_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.ANIMAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_CYAN, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(ANIMAL_UNCOMMON_SEED.getItemId(), ANIMAL_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(10, 10, 6, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(CELESTIAL_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.CELESTIAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_YELLOW, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(CELESTIAL_UNCOMMON_SEED.getItemId(), CELESTIAL_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(2, 10, 10, 6, 2)
            .tryRegister(plugin);

        new HarvestableSeed(VOID_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.VOID).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_PURPLE, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(VOID_UNCOMMON_SEED.getItemId(), VOID_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(2, 2, 10, 10, 6)
            .tryRegister(plugin);

        new HarvestableSeed(PHILOSOPHICAL_RARE_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.RARE).get(StoryType.PHILOSOPHICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_PURPLE, Placements.NETHER_GRASS_AND_UP, 10, 0.005))
            .addBreedingPair(PHILOSOPHICAL_UNCOMMON_SEED.getItemId(), PHILOSOPHICAL_UNCOMMON_SEED.getItemId(), 0.025, 0.005)
            .addFlavourProfile(6, 2, 2, 10, 10)
            .tryRegister(plugin);

        new HarvestableSeed(ELEMENTAL_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.ELEMENTAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_RED, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(ELEMENTAL_RARE_SEED.getItemId(), ELEMENTAL_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(20, 2, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(MECHANICAL_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.MECHANICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_ORANGE, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(MECHANICAL_RARE_SEED.getItemId(), MECHANICAL_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 20, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(ALCHEMICAL_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.ALCHEMICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_YELLOW, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(ALCHEMICAL_RARE_SEED.getItemId(), ALCHEMICAL_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 20, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HISTORICAL_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.HISTORICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_GREEN, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(HISTORICAL_RARE_SEED.getItemId(), HISTORICAL_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 2, 20, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HUMAN_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.HUMAN).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_BLUE, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(HUMAN_RARE_SEED.getItemId(), HUMAN_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 2, 2, 20)
            .tryRegister(plugin);

        new HarvestableSeed(ANIMAL_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.ANIMAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_CYAN, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(ANIMAL_RARE_SEED.getItemId(), ANIMAL_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(20, 20, 6, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(CELESTIAL_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.CELESTIAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_YELLOW, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(CELESTIAL_RARE_SEED.getItemId(), CELESTIAL_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 20, 20, 6, 2)
            .tryRegister(plugin);

        new HarvestableSeed(VOID_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.VOID).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_PURPLE, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(VOID_RARE_SEED.getItemId(), VOID_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 20, 20, 6)
            .tryRegister(plugin);

        new HarvestableSeed(PHILOSOPHICAL_EPIC_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.EPIC).get(StoryType.PHILOSOPHICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPIKEY_PURPLE, Placements.NETHER_GRASS_AND_UP, 20, 0.0025))
            .addBreedingPair(PHILOSOPHICAL_RARE_SEED.getItemId(), PHILOSOPHICAL_RARE_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(6, 2, 2, 20, 20)
            .tryRegister(plugin);

        new HarvestableSeed(ELEMENTAL_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.ELEMENTAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_RED, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(ELEMENTAL_EPIC_SEED.getItemId(), ELEMENTAL_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(30, 2, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(MECHANICAL_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.MECHANICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_ORANGE, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(MECHANICAL_EPIC_SEED.getItemId(), MECHANICAL_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 30, 2, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(ALCHEMICAL_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.ALCHEMICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_YELLOW, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(ALCHEMICAL_EPIC_SEED.getItemId(), ALCHEMICAL_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 30, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HISTORICAL_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.HISTORICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_GREEN, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(HISTORICAL_EPIC_SEED.getItemId(), HISTORICAL_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 2, 30, 2)
            .tryRegister(plugin);

        new HarvestableSeed(HUMAN_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.HUMAN).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_BLUE, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(HUMAN_EPIC_SEED.getItemId(), HUMAN_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 2, 2, 30)
            .tryRegister(plugin);

        new HarvestableSeed(ANIMAL_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.ANIMAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_CYAN, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(ANIMAL_EPIC_SEED.getItemId(), ANIMAL_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(30, 30, 6, 2, 2)
            .tryRegister(plugin);

        new HarvestableSeed(CELESTIAL_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.CELESTIAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_YELLOW, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(CELESTIAL_EPIC_SEED.getItemId(), CELESTIAL_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 30, 30, 6, 2)
            .tryRegister(plugin);

        new HarvestableSeed(VOID_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.VOID).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_PURPLE, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(VOID_EPIC_SEED.getItemId(), VOID_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(2, 2, 30, 30, 6)
            .tryRegister(plugin);

        new HarvestableSeed(PHILOSOPHICAL_MYTHICAL_SEED)
            .setHarvestingResult(Materials.CRYSTAL_MAP.get(StoryRarity.MYTHICAL).get(StoryType.PHILOSOPHICAL).getItem())
            .setGrowth(new Growth(GrowthStages.SPAWNING_PURPLE, Placements.NETHER_GRASS_AND_UP, 30, 0.0025))
            .addBreedingPair(PHILOSOPHICAL_EPIC_SEED.getItemId(), PHILOSOPHICAL_EPIC_SEED.getItemId(), 0.001, 0.0025)
            .addFlavourProfile(6, 2, 2, 30, 30)
            .tryRegister(plugin);
    }
}
