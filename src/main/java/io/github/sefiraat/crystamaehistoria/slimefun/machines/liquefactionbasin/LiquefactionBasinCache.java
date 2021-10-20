package io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin;

import de.slikey.effectlib.effect.SphereEffect;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellType;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.DisplayStandHolder;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.BlankPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.ChargedPlate;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plates.PlateStorage;
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
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Getter
public class LiquefactionBasinCache extends DisplayStandHolder {

    public static final double LOWEST_LEVEL = -1.7;
    public static final double HIGHEST_LEVEL = -1;
    public static final double MAX_VOLUME = 1000;
    protected static final Map<StoryRarity, Integer> RARITY_VALUE_MAP = new EnumMap<>(StoryRarity.class);
    protected static final String CH_LEVEL_PREFIX = "ch_c_lvl:";
    private static final Map<SpellType, SpellRecipe> RECIPES_SPELL = new HashMap<>();

    static {
        RARITY_VALUE_MAP.put(StoryRarity.COMMON, 1);
        RARITY_VALUE_MAP.put(StoryRarity.UNCOMMON, 3);
        RARITY_VALUE_MAP.put(StoryRarity.RARE, 10);
        RARITY_VALUE_MAP.put(StoryRarity.EPIC, 25);
        RARITY_VALUE_MAP.put(StoryRarity.MYTHICAL, 50);
        RARITY_VALUE_MAP.put(StoryRarity.UNIQUE, 2);
    }

    private final Map<StoryType, Integer> contentMap = new EnumMap<>(StoryType.class);

    @ParametersAreNonnullByDefault
    public LiquefactionBasinCache(BlockMenu blockMenu) {
        super(blockMenu);
    }

    public static void addSpellRecipe(SpellType spellType, SpellRecipe spellRecipe) {
        RECIPES_SPELL.put(spellType, spellRecipe);
    }

    @ParametersAreNonnullByDefault
    public void consumeItems() {
        Collection<Entity> entities = getWorld().getNearbyEntities(getLocation().clone().add(0.5, 0.5, 0.5), 0.3, 0.3, 0.3, Item.class::isInstance);
        for (Entity entity : entities) {
            Item item = (Item) entity;
            SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());
            if (slimefunItem instanceof Crystal) {
                Crystal crystal = (Crystal) slimefunItem;
                addCrystamae(crystal.getType(), crystal.getRarity(), item);
            } else if (slimefunItem instanceof BlankPlate) {
                processBlankPlate(item, (BlankPlate) slimefunItem);
            } else if (slimefunItem instanceof ChargedPlate) {
                processChargedPlate(item, (ChargedPlate) slimefunItem);
            } else {
                rejectItem(item, true);
            }
        }
        if (getFillLevel() > 0 && GeneralUtils.testChance(1, 5)) {
            summonBoilingParticles();
        }
    }

    @ParametersAreNonnullByDefault
    private void rejectItem(Item item, boolean punish) {
        double velX = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        double velZ = ThreadLocalRandom.current().nextDouble(-0.9, 1.1);
        item.setVelocity(new Vector(velX, 0.5, velZ));
        // TODO Punishment for incorrect usage
    }

    @ParametersAreNonnullByDefault
    private void addCrystamae(StoryType type, StoryRarity rarity, Item item) {
        int numberInStack = item.getItemStack().getAmount();
        int amount = LiquefactionBasinCache.RARITY_VALUE_MAP.get(rarity) * numberInStack;
        if (getFillLevel() + amount > MAX_VOLUME) {
            rejectItem(item, false);
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

    private void emptyBasin() {
        contentMap.clear();
        clearBlockStorage();
        ArmorStand armorStand = getDisplayStand();
        ArmourStandUtils.clearDisplayItem(armorStand);
        setFillHeight(armorStand);
    }

    private void clearBlockStorage() {
        Config c = BlockStorage.getLocationInfo(blockMenu.getLocation());
        List<String> keys = new ArrayList<>();
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
        final double incrementAmount = diff / MAX_VOLUME;
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
        Set<StoryType> set = contentMap.entrySet().stream().sorted(Map.Entry.<StoryType, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toSet());
        ItemStack itemStack = item.getItemStack();
        if (set.size() == 3) {
            SlimefunItem.getByItem(itemStack);
            SpellType spellType = getMatchingRecipe(set, plate);
            if (spellType != null) {
                // TODO Plate tier checks
                item.getWorld().dropItem(item.getLocation(), ChargedPlate.getChargedPlate(plate.getTier(), spellType, getFillLevel()));
                if (itemStack.getAmount() > 1) {
                    itemStack.setAmount(itemStack.getAmount() - 1);
                } else {
                    item.remove();
                }
                summonCatalystParticles();
            }
            emptyBasin();
        } else {
            rejectItem(item, true);
        }
    }

    private void processChargedPlate(Item item, ChargedPlate plate) {
        final ItemStack itemStack = item.getItemStack();
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final PlateStorage plateStorage = DataTypeMethods.getCustom(itemMeta, Keys.PDC_PLATE_STORAGE, PersistentPlateDataType.TYPE);
        final SpellType currentSpellType = plateStorage.getStoredSpell();

        final Set<StoryType> set = contentMap.entrySet().stream().sorted(Map.Entry.<StoryType, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toSet());
        if (set.size() == 3) {
            SpellType spellType = getMatchingRecipe(set, plate);
            if (spellType == currentSpellType) {
                plateStorage.addCrysta(getFillLevel());
                itemStack.setItemMeta(itemMeta);
                summonCatalystParticles();
            }
            emptyBasin();
        } else {
            rejectItem(item, false);
        }

    }

    @Nullable
    @ParametersAreNonnullByDefault
    public SpellType getMatchingRecipe(Set<StoryType> set, SlimefunItem slimefunItem) {
        SpellType spellType = null;
        for (Map.Entry<SpellType, SpellRecipe> recipeEntry : RECIPES_SPELL.entrySet()) {
            if (recipeEntry.getValue().recipeMatches(set, slimefunItem)) {
                spellType = recipeEntry.getKey();
                break;
            }
        }
        return spellType;
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
        location.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 0, 0.2, 0, 0.2, 0);
    }

    private void summonBoilingParticles() {
        final Location location = getLocation(true).add(0, 0.8, 0);
        location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 0, 0.2, 0, 0.5, 0);
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
