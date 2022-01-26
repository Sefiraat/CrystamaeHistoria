package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.mooy1.infinitylib.machines.TickingMenuBlock;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.theme.GuiElements;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;

public class MobFan extends TickingMenuBlock {

    protected static final String ID_DIRECTION = "CH_DIRECTION";
    protected static final String ID_UUID = "CH_UUID";

    protected static final int[] BACKGROUND_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 16, 17, 18, 19, 20, 21, 23, 25, 26, 27, 28, 30, 31, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44
    };
    protected static final int SET_UP = 11;
    protected static final int SET_DOWN = 29;
    protected static final int SET_NORTH = 14;
    protected static final int SET_WEST = 22;
    protected static final int SET_SOUTH = 32;
    protected static final int SET_EAST = 24;

    @Getter
    private final double range;

    @ParametersAreNonnullByDefault
    public MobFan(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double range) {
        super(category, item, recipeType, recipe);
        this.range = range;
    }

    @Override
    public void preRegister() {
        addItemHandler(onBlockPlace(), onBlockBreak());
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            @ParametersAreNonnullByDefault
            public void onPlayerPlace(BlockPlaceEvent event) {
                BlockStorage.addBlockInfo(event.getBlock(), ID_UUID, event.getPlayer().getUniqueId().toString());
                BlockStorage.addBlockInfo(event.getBlock(), ID_DIRECTION, BlockFace.SELF.name());
            }
        };
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            @ParametersAreNonnullByDefault
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                BlockStorage.clearBlockInfo(blockBreakEvent.getBlock());
            }
        };
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu blockMenu) {
        final BlockFace direction = BlockFace.valueOf(BlockStorage.getLocationInfo(block.getLocation(), ID_DIRECTION));
        final Vector facingVector = direction.getDirection();
        final UUID owner = UUID.fromString(BlockStorage.getLocationInfo(block.getLocation(), ID_UUID));

        if (direction == BlockFace.SELF) {
            return;
        }

        final Location location = block.getLocation();
        final RayTraceResult result = location.getWorld().rayTraceBlocks(
            location.add(facingVector),
            facingVector,
            range,
            FluidCollisionMode.ALWAYS,
            false
        );

        double finalRange = range;

        if (result != null && result.getHitBlock() != null) {
            finalRange = result.getHitBlock().getLocation().distance(block.getLocation());
        }

        for (int i = 0; i < finalRange + 0.5; i++) {
            Location offsetLocation = location.clone().add(direction.getDirection().clone().multiply(i));
            for (Entity entity : block.getWorld().getNearbyEntities(offsetLocation, 0.5, 0.5, 0.5)) {
                if (entity instanceof Player) {
                    Player player = (Player) entity;
                    if (player.getGameMode() != GameMode.SURVIVAL) {
                        return;
                    }
                }
                GeneralUtils.pushEntity(
                    owner,
                    facingVector,
                    entity,
                    1
                );
            }
        }
    }

    @Override
    protected boolean synchronous() {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(BACKGROUND_SLOTS);

        blockMenuPreset.addItem(SET_NORTH, GuiElements.getDirectionalSlotPane(BlockFace.NORTH, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(SET_SOUTH, GuiElements.getDirectionalSlotPane(BlockFace.SOUTH, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(SET_EAST, GuiElements.getDirectionalSlotPane(BlockFace.EAST, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(SET_WEST, GuiElements.getDirectionalSlotPane(BlockFace.WEST, false), (player, i, itemStack, clickAction) -> false);

        blockMenuPreset.addItem(SET_UP, GuiElements.getDirectionalSlotPane(BlockFace.UP, false), (player, i, itemStack, clickAction) -> false);
        blockMenuPreset.addItem(SET_DOWN, GuiElements.getDirectionalSlotPane(BlockFace.DOWN, false), (player, i, itemStack, clickAction) -> false);
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
    protected void onNewInstance(BlockMenu menu, Block b) {
        BlockFace direction = BlockFace.valueOf(BlockStorage.getLocationInfo(b.getLocation(), ID_DIRECTION));
        setDirection(menu, direction);

        menu.addMenuClickHandler(SET_NORTH, (player, i, itemStack, clickAction) -> setDirection(menu, BlockFace.NORTH));
        menu.addMenuClickHandler(SET_SOUTH, (player, i, itemStack, clickAction) -> setDirection(menu, BlockFace.SOUTH));
        menu.addMenuClickHandler(SET_EAST, (player, i, itemStack, clickAction) -> setDirection(menu, BlockFace.EAST));
        menu.addMenuClickHandler(SET_WEST, (player, i, itemStack, clickAction) -> setDirection(menu, BlockFace.WEST));
        menu.addMenuClickHandler(SET_UP, (player, i, itemStack, clickAction) -> setDirection(menu, BlockFace.UP));
        menu.addMenuClickHandler(SET_DOWN, (player, i, itemStack, clickAction) -> setDirection(menu, BlockFace.DOWN));

    }

    @ParametersAreNonnullByDefault
    private boolean setDirection(BlockMenu blockMenu, BlockFace blockFace) {
        BlockStorage.addBlockInfo(blockMenu.getBlock(), ID_DIRECTION, blockFace.name());

        blockMenu.replaceExistingItem(SET_UP, GuiElements.getDirectionalSlotPane(BlockFace.UP, false));
        blockMenu.replaceExistingItem(SET_DOWN, GuiElements.getDirectionalSlotPane(BlockFace.DOWN, false));
        blockMenu.replaceExistingItem(SET_NORTH, GuiElements.getDirectionalSlotPane(BlockFace.NORTH, false));
        blockMenu.replaceExistingItem(SET_SOUTH, GuiElements.getDirectionalSlotPane(BlockFace.SOUTH, false));
        blockMenu.replaceExistingItem(SET_EAST, GuiElements.getDirectionalSlotPane(BlockFace.EAST, false));
        blockMenu.replaceExistingItem(SET_WEST, GuiElements.getDirectionalSlotPane(BlockFace.WEST, false));

        switch (blockFace) {
            case UP: {
                blockMenu.replaceExistingItem(SET_UP, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case DOWN: {
                blockMenu.replaceExistingItem(SET_DOWN, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case NORTH: {
                blockMenu.replaceExistingItem(SET_NORTH, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case SOUTH: {
                blockMenu.replaceExistingItem(SET_SOUTH, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case EAST: {
                blockMenu.replaceExistingItem(SET_EAST, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case WEST: {
                blockMenu.replaceExistingItem(SET_WEST, GuiElements.getDirectionalSlotPane(blockFace, true));
                break;
            }
            case SELF: {
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + blockFace);
        }

        return false;
    }
}
