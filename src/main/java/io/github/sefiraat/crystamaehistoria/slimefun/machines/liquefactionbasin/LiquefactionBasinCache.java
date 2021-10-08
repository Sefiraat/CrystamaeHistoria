package io.github.sefiraat.crystamaehistoria.slimefun.machines.liquefactionbasin;

import io.github.sefiraat.crystamaehistoria.slimefun.AbstractCache;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Crystal;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.plate.Plate;
import io.github.sefiraat.crystamaehistoria.stories.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.StoryType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class LiquefactionBasinCache extends AbstractCache {

    public static final Map<StoryRarity, Integer> RARITY_VALUE_MAP = new HashMap<>();
    protected static final String CH_LEVEL_PREFIX = "ch_c_lvl:";

    static {
        RARITY_VALUE_MAP.put(StoryRarity.COMMON, 1);
        RARITY_VALUE_MAP.put(StoryRarity.UNCOMMON, 3);
        RARITY_VALUE_MAP.put(StoryRarity.RARE, 10);
        RARITY_VALUE_MAP.put(StoryRarity.EPIC, 25);
        RARITY_VALUE_MAP.put(StoryRarity.MYTHICAL, 50);
        RARITY_VALUE_MAP.put(StoryRarity.UNIQUE, 2);
    }

    @Getter
    private final Map<StoryType, Long> contentMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public LiquefactionBasinCache(BlockMenu blockMenu) {
        super(blockMenu);
    }

    @ParametersAreNonnullByDefault
    public void consumeItems() {
        Collection<Entity> entities = getWorld().getNearbyEntities(getLocation().clone().add(0.5, 0.5, 0.5), 0.3, 0.3, 0.3, entity -> entity instanceof Item);
        for (Entity entity : entities) {
            Item item = (Item) entity;
            SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());
            if (slimefunItem instanceof Crystal) {
                Crystal crystal = (Crystal) slimefunItem;
                addCrystamae(crystal.getType(), crystal.getRarity(), crystal.getAmount());
                item.remove();
                summonConsumeParticles();
            } else if (slimefunItem instanceof Plate) {
                checkCatalyst();
            } else {
                double velX = ThreadLocalRandom.current().nextDouble(0.2, 1);
                double velZ = ThreadLocalRandom.current().nextDouble(0.2, 1);
                item.setVelocity(new Vector(velX, 0.5, velZ));
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void addCrystamae(StoryType type, StoryRarity rarity, int numberInStack) {
        long amount = ((long) LiquefactionBasinCache.RARITY_VALUE_MAP.get(rarity) * numberInStack);
        if (contentMap.containsKey(type)) {
            contentMap.put(type, contentMap.get(type) + amount);
        } else {
            contentMap.put(type, amount);
        }
    }

    @ParametersAreNonnullByDefault
    private void removeCrystamae(StoryType type, long amount) {
        long volume = contentMap.get(type);
        if (volume - amount <= 0) {
            contentMap.remove(type);
            BlockStorage.addBlockInfo(blockMenu.getLocation(), CH_LEVEL_PREFIX + type, null);
        } else {
            contentMap.put(type, contentMap.get(type) - amount);
        }
    }


    public void syncBlock() {
        for (Map.Entry<StoryType, Long> e : contentMap.entrySet()) {
            BlockStorage.addBlockInfo(blockMenu.getBlock(), CH_LEVEL_PREFIX + e.getKey(), String.valueOf(e.getValue()));
        }
    }

    private void checkCatalyst() {

    }

    private void summonConsumeParticles() {
        final Location location = blockMenu.getLocation().clone().add(0.5, 1, 0.5);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 0, 0.2, 0, 0.2, 0);
    }

    private void summonGrowParticles(Block block) {
        final Location location = block.getLocation();
        for (int i = 0; i < 5; i++) {
            final Location l = location.clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1), 0, ThreadLocalRandom.current().nextDouble(0, 1.1));
            location.getWorld().spawnParticle(Particle.CRIMSON_SPORE, l, 0, 0.5, 0, 0.5, 0);
        }
    }

    private void summonFullyGrownParticles(Block block) {
        final Location location = block.getLocation();
        for (int i = 0; i < 2; i++) {
            final Location l = location.clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1), 0, ThreadLocalRandom.current().nextDouble(0, 1.1));
            location.getWorld().spawnParticle(Particle.BLOCK_CRACK, l, 0, 0.5, 0, 0.5, 0, Material.BUDDING_AMETHYST.createBlockData());
        }
    }

    protected void kill(Location location) {
        BlockStorage.clearBlockInfo(location);
    }

    protected World getWorld() {
        return blockMenu.getLocation().getWorld();
    }

    protected Location getLocation() {
        return blockMenu.getLocation();
    }

}
