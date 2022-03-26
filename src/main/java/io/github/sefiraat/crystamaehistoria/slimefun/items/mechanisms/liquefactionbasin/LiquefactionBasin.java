package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.liquefactionbasin;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class LiquefactionBasin extends TickingMenuBlock {

    protected static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };
    protected static final int INPUT_SLOT = 22;
    public final int maxVolume;
    @Getter
    protected final Map<Location, LiquefactionBasinCache> cacheMap = new HashMap<>();
    private final Color color;

    @ParametersAreNonnullByDefault
    public LiquefactionBasin(ItemGroup itemGroup,
                             SlimefunItemStack item,
                             RecipeType recipeType,
                             ItemStack[] recipe,
                             int maxVolume,
                             Color color
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.maxVolume = maxVolume;
        this.color = color;
    }

    @Override
    public void preRegister() {
        addItemHandler(onBlockPlace());
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                final Location location = event.getBlockPlaced().getLocation();
                final LiquefactionBasinCache cache = new LiquefactionBasinCache(BlockStorage.getInventory(location), maxVolume);
                cache.setActivePlayer(event.getPlayer());
                cacheMap.put(location, cache);
            }
        };
    }


    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        LiquefactionBasinCache cache = LiquefactionBasin.this.cacheMap.get(block.getLocation());
        if (cache != null) {
            cache.consumeItems();
            Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1);
            ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), 1, 4, dustOptions);
        }
        final Material material = block.getType();
        if (material == Material.LAVA_CAULDRON || material == Material.WATER_CAULDRON) {
            block.setType(Material.CAULDRON);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.addMenuOpeningHandler(HumanEntity::closeInventory);
        blockMenuPreset.drawBackground(BACKGROUND_SLOTS);
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent event, BlockMenu blockMenu) {
        super.onBreak(event, blockMenu);

        final Location location = blockMenu.getLocation();
        final LiquefactionBasinCache liquefactionBasinCache = cacheMap.remove(location);

        boolean punish = false;

        if (liquefactionBasinCache != null) {
            liquefactionBasinCache.kill(location);
            punish = liquefactionBasinCache.getFillLevel() > 0;
        }
        blockMenu.dropItems(location, INPUT_SLOT);
        if (punish) {
            blockMenu.getLocation().getWorld().createExplosion(
                event.getBlock().getLocation(),
                2,
                true,
                false
            );
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        if (!cacheMap.containsKey(blockMenu.getLocation())) {
            LiquefactionBasinCache cache = new LiquefactionBasinCache(blockMenu, this.maxVolume);
            Config c = BlockStorage.getLocationInfo(blockMenu.getLocation());

            for (String key : c.getKeys()) {
                if (key.startsWith(LiquefactionBasinCache.CH_LEVEL_PREFIX)) {
                    String id = key.replace(LiquefactionBasinCache.CH_LEVEL_PREFIX, "");
                    int amount = Integer.parseInt(c.getString(key));
                    cache.getContentMap().put(StoryType.valueOf(id), amount);
                }
            }

            cacheMap.put(blockMenu.getLocation(), cache);
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

}
