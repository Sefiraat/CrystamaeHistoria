package io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin;

import de.slikey.effectlib.effect.SphereEffect;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstancePlate;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.DisplayStandHolder;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.MagicalPlate;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentPlateDataType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Getter
public class LiquefactionBasinCache extends DisplayStandHolder {

    public static final double LOWEST_LEVEL = -1.7;
    public static final double HIGHEST_LEVEL = -1;
    protected static final String CH_LEVEL_PREFIX = "ch_c_lvl:";
    private static final Map<SpellType, RecipeSpell> RECIPES_SPELL = new HashMap<>();
    private static final Map<SlimefunItem, RecipeItem> RECIPES_ITEMS = new HashMap<>();

    private final double maxVolume;
    private final Map<StoryType, Integer> contentMap = new EnumMap<>(StoryType.class);

    @ParametersAreNonnullByDefault
    public LiquefactionBasinCache(BlockMenu blockMenu, double maxVolume) {
        super(blockMenu);
        this.maxVolume = maxVolume;

        final String activePlayerString = BlockStorage.getLocationInfo(blockMenu.getLocation(), Keys.BS_CP_ACTIVE_PLAYER);
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    public static void addSpellRecipe(SpellType spellType, RecipeSpell recipeSpell) {
        RECIPES_SPELL.put(spellType, recipeSpell);
    }

    public static void addCraftingRecipe(SlimefunItem slimefunItem, RecipeItem recipeItem) {
        RECIPES_ITEMS.put(slimefunItem, recipeItem);
    }

    @ParametersAreNonnullByDefault
    public void consumeItems() {
        final Collection<Entity> entities = getWorld().getNearbyEntities(
            getLocation().clone().add(0.5, 0.5, 0.5),
            0.3,
            0.3,
            0.3,
            Item.class::isInstance
        );

        for (Entity entity : entities) {
            final Item item = (Item) entity;
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());
            if (slimefunItem instanceof Crystal) {
                final Crystal crystal = (Crystal) slimefunItem;
                addCrystamae(crystal.getType(), crystal.getRarity(), item);
            } else if (slimefunItem instanceof BlankPlate) {
                processBlankPlate(item, (BlankPlate) slimefunItem);
            } else if (slimefunItem instanceof ChargedPlate) {
                processChargedPlate(item, (ChargedPlate) slimefunItem);
            } else {
                if (!processOtherItem(item)) {
                    rejectItem(item);
                }
            }
            syncBlock();
        }
        if (getFillLevel() > 0 && GeneralUtils.testChance(1, 5)) {
            summonBoilingParticles();
        }
    }

    @ParametersAreNonnullByDefault
    private void rejectItem(Item item) {
        final double velX = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        final double velZ = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        item.setVelocity(new Vector(velX, 0.5, velZ));
    }

    @ParametersAreNonnullByDefault
    private void addCrystamae(StoryType type, StoryRarity rarity, Item item) {
        final int numberInStack = item.getItemStack().getAmount();
        final int amount = Crystal.getRarityValueMap().get(rarity) * numberInStack;
        if (getFillLevel() + amount > maxVolume) {
            rejectItem(item);
        } else {
            if (contentMap.containsKey(type)) {
                contentMap.put(type, contentMap.get(type) + amount);
            } else {
                contentMap.put(type, amount);
            }
            updateDisplay();
            item.remove();
            summonConsumeParticles();
        }
    }

    private void updateDisplay() {
        if (contentMap.isEmpty()) {
            return;
        }

        ArmorStand armorStand = getDisplayStand();
        int amount = 0;
        int red = 0;
        int green = 0;
        int blue = 0;

        for (Map.Entry<StoryType, Integer> entry : contentMap.entrySet()) {
            final Color color = ThemeType.getByType(entry.getKey()).getColor().getColor();
            final int additionalAmount = entry.getValue();
            amount += additionalAmount;
            red += color.getRed() * additionalAmount;
            green += color.getGreen() * additionalAmount;
            blue += color.getBlue() * additionalAmount;
        }

        if (amount > 0) {
            red /= amount;
            green /= amount;
            blue /= amount;

            final ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET);
            final LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            leatherArmorMeta.setColor(org.bukkit.Color.fromRGB(red, green, blue));
            itemStack.setItemMeta(leatherArmorMeta);
            ArmourStandUtils.setDisplayItem(armorStand, itemStack);
            setFillHeight(armorStand);
        }
    }

    public void emptyBasin() {
        contentMap.clear();
        clearBlockStorage();
        ArmorStand armorStand = getDisplayStand();
        ArmourStandUtils.clearDisplayItem(armorStand);
        setFillHeight(armorStand);
    }

    private void clearBlockStorage() {
        final Config c = BlockStorage.getLocationInfo(blockMenu.getLocation());
        final List<String> keys = new ArrayList<>();
        for (String key : c.getKeys()) {
            if (key.startsWith(CH_LEVEL_PREFIX)) {
                keys.add(key);
            }
        }
        for (String key : keys) {
            BlockStorage.addBlockInfo(blockMenu.getLocation(), key, null);
        }
    }

    @ParametersAreNonnullByDefault
    private void setFillHeight(ArmorStand armorStand) {
        final double diff = HIGHEST_LEVEL - LOWEST_LEVEL;
        final double incrementAmount = diff / maxVolume;
        final double amount = incrementAmount * getFillLevel();
        final Location location = blockMenu.getLocation().clone().add(0.5, -1.7 + amount, 0.5);
        armorStand.teleport(location);
    }

    public void syncBlock() {
        for (Map.Entry<StoryType, Integer> e : contentMap.entrySet()) {
            BlockStorage.addBlockInfo(blockMenu.getBlock(), CH_LEVEL_PREFIX + e.getKey(), String.valueOf(e.getValue()));
        }
    }

    @ParametersAreNonnullByDefault
    private void processBlankPlate(Item item, BlankPlate plate) {
        final Set<StoryType> set = contentMap.entrySet().stream()
            .sorted(Map.Entry.<StoryType, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());

        final ItemStack itemStack = item.getItemStack();
        if (set.size() == 3) {
            SlimefunItem.getByItem(itemStack);
            final SpellType spellType = getMatchingRecipe(set, plate);
            if (spellType != null) {
                item.getWorld().dropItem(item.getLocation(), ChargedPlate.getChargedPlate(plate.getTier(), spellType, getFillLevel()));
                if (itemStack.getAmount() > 1) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                } else {
                    item.remove();
                }
                summonCatalystParticles();
                if (activePlayer != null
                    && !PlayerStatistics.hasUnlockedSpell(activePlayer, spellType)
                ) {
                    PlayerStatistics.unlockSpell(activePlayer, spellType);
                }
            }
            emptyBasin();
        } else {
            rejectItem(item);
        }
    }

    private void processChargedPlate(Item item, ChargedPlate plate) {
        final ItemStack itemStack = item.getItemStack();
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final InstancePlate instancePlate = DataTypeMethods.getCustom(
            itemMeta,
            Keys.PDC_PLATE_STORAGE,
            PersistentPlateDataType.TYPE
        );

        if (instancePlate == null) {
            CrystamaeHistoria.getInstance().getLogger().warning(
                "The charged plate used has not been configured correctly. /sf cheat charged plates will not " +
                    "work in the Liquefaction Basin. If this is not the case, please raises an issue."
            );
            item.remove();
            return;
        }

        final SpellType currentSpellType = instancePlate.getStoredSpell();
        final Set<StoryType> set = contentMap.entrySet().stream()
            .sorted(Map.Entry.<StoryType, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());

        if (set.size() == 3) {
            SpellType spellType = getMatchingRecipe(set, plate);
            if (spellType != null && spellType == currentSpellType) {
                instancePlate.addCrysta(getFillLevel());
                DataTypeMethods.setCustom(itemMeta, Keys.PDC_PLATE_STORAGE, PersistentPlateDataType.TYPE, instancePlate);
                itemStack.setItemMeta(itemMeta);
                InstancePlate.setPlateLore(itemStack, instancePlate);
                summonCatalystParticles();
            }
            emptyBasin();
        } else {
            rejectItem(item);
        }
    }

    @ParametersAreNonnullByDefault
    private boolean processOtherItem(Item item) {
        final ItemStack itemStack = item.getItemStack();

        final List<StoryType> typeList = contentMap.entrySet().stream()
            .sorted(Map.Entry.<StoryType, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        final List<Integer> amountList = contentMap.entrySet().stream()
            .sorted(Map.Entry.<StoryType, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());

        if (typeList.size() == 3) {
            SlimefunItem.getByItem(itemStack);
            SlimefunItem slimefunItem = getMatchingRecipe(typeList, amountList, itemStack);
            if (slimefunItem != null
                && !slimefunItem.isDisabled()
            ) {
                item.getWorld().dropItem(item.getLocation(), slimefunItem.getRecipeOutput().clone());
                if (itemStack.getAmount() > 1) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                } else {
                    item.remove();
                }
                summonCatalystParticles();
                emptyBasin();
                return true;
            }
        }
        return false;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public SpellType getMatchingRecipe(Set<StoryType> set, MagicalPlate magicalPlate) {
        SpellType spellType = null;
        for (Map.Entry<SpellType, RecipeSpell> recipeEntry : RECIPES_SPELL.entrySet()) {
            if (recipeEntry.getValue().recipeMatches(set, magicalPlate.getTier())) {
                spellType = recipeEntry.getKey();
                break;
            }
        }
        return spellType;
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public SlimefunItem getMatchingRecipe(List<StoryType> types, List<Integer> amounts, ItemStack itemStack) {
        SlimefunItem slimefunItem = null;
        for (Map.Entry<SlimefunItem, RecipeItem> recipeEntry : RECIPES_ITEMS.entrySet()) {
            if (recipeEntry.getValue().recipeMatches(types, amounts, itemStack)) {
                slimefunItem = recipeEntry.getKey();
                break;
            }
        }
        return slimefunItem;
    }

    public int getFillLevel() {
        int amount = 0;
        for (Map.Entry<StoryType, Integer> entry : contentMap.entrySet()) {
            amount += entry.getValue();
        }
        return amount;
    }

    private void summonConsumeParticles() {
        final Location location = getLocation(true).add(0, 0.8, 0);
        location.getWorld().spawnParticle(
            Particle.CAMPFIRE_COSY_SMOKE,
            location,
            0,
            0.2,
            0,
            0.2,
            0
        );
    }

    private void summonBoilingParticles() {
        final Location location = getLocation(true).add(0, 0.8, 0);
        location.getWorld().spawnParticle(
            Particle.SMOKE_NORMAL,
            location,
            0,
            0.2,
            0,
            0.5,
            0
        );
    }

    private void summonCatalystParticles() {
        SphereEffect sphereEffect = new SphereEffect(CrystamaeHistoria.getEffectManager());
        sphereEffect.particle = Particle.REDSTONE;
        sphereEffect.color = org.bukkit.Color.TEAL;
        sphereEffect.setLocation(getLocation(true));
        sphereEffect.radius = 1;
        sphereEffect.iterations = 2;
        sphereEffect.start();
    }
}
