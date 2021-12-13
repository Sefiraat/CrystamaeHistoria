package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.RefactingLens;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExpCollector extends TickingMenuBlock {

    protected static final String ID_UUID = "CH_UUID";
    protected static final String ID_VOLUME = "EXP_VOLUME";

    protected static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27, 28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };

    @Getter
    private final int maxVolume;
    @Getter
    private final int range;
    @Getter
    private final Map<Location, UUID> blockOwnerMap = new HashMap<>();
    @Getter
    private final Map<Location, Integer> volumeMap = new HashMap<>();

    @ParametersAreNonnullByDefault
    public ExpCollector(ItemGroup category,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe,
                        int maxVolume,
                        int range
    ) {
        super(category, item, recipeType, recipe);
        this.maxVolume = maxVolume;
        this.range = range;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        final Location location = block.getLocation().add(0.5, 0.5, 0.5);
        final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(20, 230, 5), 1);
        final Collection<Entity> entities = location.getWorld().getNearbyEntities(
            location,
            range,
            range,
            range,
            ExperienceOrb.class::isInstance
        );
        int amount = volumeMap.get(block.getLocation());
        boolean hasUpdated = false;
        for (Entity entity : entities) {
            ExperienceOrb experienceOrb = (ExperienceOrb) entity;
            int newAmount = amount + experienceOrb.getExperience();
            if (newAmount <= this.maxVolume) {
                amount = newAmount;
                experienceOrb.remove();
                hasUpdated = true;
                ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 1, 0.5), 0.7, 3, dustOptions);
            }
        }
        if (hasUpdated) {
            volumeMap.put(block.getLocation(), amount);
            syncValue(block);
        }
    }

    private void syncValue(Block block) {
        BlockStorage.addBlockInfo(block, ID_VOLUME, String.valueOf(volumeMap.get(block.getLocation())));
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
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
    protected void onNewInstance(BlockMenu menu, Block block) {
        Location location = block.getLocation();
        String owner = BlockStorage.getLocationInfo(location, ID_UUID);
        if (owner != null) {
            blockOwnerMap.put(location, UUID.fromString(owner));
        }
        String volumeString = BlockStorage.getLocationInfo(location, ID_VOLUME);
        if (volumeString != null) {
            volumeMap.put(location, Integer.parseInt(volumeString));
        }

        menu.addMenuOpeningHandler(player -> {
            player.closeInventory();
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
            if (!(slimefunItem instanceof RefactingLens)) {
                final UUID uuid = blockOwnerMap.get(location);
                if (player.getUniqueId().equals(uuid)) {
                    int amount = volumeMap.get(location);
                    if (amount > 0) {
                        volumeMap.put(location, 0);
                        player.giveExp(amount);
                        ParticleUtils.displayParticleEffect(block.getLocation().add(0.5, 0.5, 0.5), Particle.WAX_ON, 1.5, Math.min(20, amount / 10));
                        syncValue(block);
                    }
                }
            }
        });
    }

    @Override
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        BlockStorage.clearBlockInfo(e.getBlock());
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onPlace(BlockPlaceEvent e, Block b) {
        final UUID uuid = e.getPlayer().getUniqueId();
        BlockStorage.addBlockInfo(b, ID_UUID, uuid.toString());
        blockOwnerMap.put(b.getLocation(), uuid);
        BlockStorage.addBlockInfo(b, ID_VOLUME, String.valueOf(0));
        volumeMap.put(b.getLocation(), 0);
    }
}
