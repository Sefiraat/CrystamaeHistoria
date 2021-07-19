package io.github.sefiraat.crystamaehistoria.slimefun.machines;

import io.github.mooy1.infinitylib.slimefun.AbstractTickingContainer;
import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.animation.FloatingHeadAnimation;
import io.github.sefiraat.crystamaehistoria.stories.BlockTier;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.theme.GUIElements;
import io.github.sefiraat.crystamaehistoria.utils.AnimateUtils;
import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class ChroniclerPanel extends AbstractTickingContainer {

    private static final int[] BACKGROUND_SLOTS = {
            0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,
            9 ,10,11,         15,16,17,
            18,19,20,         24,25,26,
            27,28,29,         33,34,35,
            36,37,38,39,40,41,42,43,44
    };
    private static final int[] BACKGROUND_INPUT = {
            12,13,14,
            21,   23,
            30,31,32
    };
    private static final int INPUT_SLOT = 22;

    private BlockMenu menu;
    private ArmorStand armourStand;
    @Nullable
    private Material workingOn;
    private boolean working;
    private StoriedBlockDefinition storiedBlockDefinition;
    private String armourStandName;
    private FloatingHeadAnimation animation;

    public ChroniclerPanel(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void tick(@NotNull BlockMenu blockMenu, @NotNull Block block) {

        // Set up armor stand if empty (after restart)
        if (armourStand == null) {
            armourStand = setupArmourStand(block);
            startAnimation();
        }

        ItemStack i = blockMenu.getItemInSlot(INPUT_SLOT);

        if (i != null && i.getType() != Material.AIR && StoryUtils.canBeStoried(i)) {
            rejectOverage(i);
            if (!StoryUtils.isStoried(i)) {
                StoryUtils.makeStoried(i);
            }
            if (StoryUtils.getRemainingStoryAmount(i) > 0) {
                // A block is in the Input slot. Does the current item being worked on match the input?
                Material m = i.getType();
                if (!working || workingOn != m) {
                    // Either not working or workingOn has changed
                    setWorking(block, m);
                    setDisplayItem(false);
                } else {
                    // Working with an item in slot while workingOn matches means we can process the item
                    processStack(i);
                }
            } else {
                shutdown();
            }
        } else {
            shutdown();
        }
    }

    private void shutdown() {
        if (working) {
            setNotWorking(menu.getBlock());
            setDisplayItem(true);
        }
    }

    private void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            menu.getBlock().getWorld().dropItemNaturally(menu.getLocation(), i2);
        }
    }

    private void setWorking(Block block, Material m) {
        workingOn = m;
        working = true;
        BlockStorage.addBlockInfo(block, KeyHolder.BS_CP_WORKING_ON, m.toString());
        Block lightBlock = block.getRelative(BlockFace.UP);
        if (lightBlock.getType() == Material.AIR) {
            lightBlock.setType(Material.LIGHT);
        }
        if (armourStand != null) {
            startAnimation();
        }
        storiedBlockDefinition = StoriesManager.getSTORIED_BLOCK_MAP().get(m);
    }

    private void startAnimation() {
        AnimateUtils.panelAnimationReset(armourStand, menu.getBlock());
        animation = new FloatingHeadAnimation(armourStand);
        animation.runTaskTimer(CrystamaeHistoria.inst(), 0, 2);
    }

    private void setNotWorking(Block block) {
        workingOn = null;
        working = false;
        BlockStorage.addBlockInfo(block, KeyHolder.BS_CP_WORKING_ON, null);
        Block lightBlock = block.getRelative(BlockFace.UP);
        if (lightBlock.getType() == Material.LIGHT) {
            lightBlock.setType(Material.AIR);
        }
        if (animation != null) {
            animation.cancel();
        }
        AnimateUtils.panelAnimationReset(armourStand, block);
    }

    private void processStack(ItemStack i) {
        summonParticles();
        // If this block isn't storied, make it storied then add the initial story set
        if (StoryUtils.getRemainingStoryAmount(i) > 0) {
            CrystamaeHistoria.inst().getLogger().info("has stories");
            int rnd = ThreadLocalRandom.current().nextInt(1, 1001);
            int req = storiedBlockDefinition.getTier().chroniclingChance;
            if (rnd <= req) {
                // We can chronicle a story
                menu.getBlock().getWorld().playEffect(menu.getLocation(), Effect.END_PORTAL_CREATED_IN_OVERWORLD, 1);
                StoryUtils.incrementStoryAmount(i);
            }
        }
    }

    private void summonParticles() {
        Location l1 = menu.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1),2,ThreadLocalRandom.current().nextDouble(0, 1.1));
        Location l2 = menu.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1),2,ThreadLocalRandom.current().nextDouble(0, 1.1));
        Location l3 = menu.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1),2,ThreadLocalRandom.current().nextDouble(0, 1.1));
        int r1 = ThreadLocalRandom.current().nextInt(150,250);
        int r2 = ThreadLocalRandom.current().nextInt(150,250);
        int r3 = ThreadLocalRandom.current().nextInt(150,250);
        World w = l1.getWorld();
        w.spawnParticle(Particle.ENCHANTMENT_TABLE, l1, 0,0.2,0,-0.2, 0);
        w.spawnParticle(Particle.ENCHANTMENT_TABLE, l2, 0,0.2,0,-0.2, 0);
        w.spawnParticle(Particle.ENCHANTMENT_TABLE, l3, 0,0.2,0,-0.2, 0);
    }

    @Override
    protected void setupMenu(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(GUIElements.menuBackground(), BACKGROUND_SLOTS);
        blockMenuPreset.drawBackground(GUIElements.menuBackgroundInput(), BACKGROUND_INPUT);
    }

    @Override
    protected int @NotNull [] getTransportSlots(@NotNull DirtyChestMenu dirtyChestMenu, @NotNull ItemTransportFlow itemTransportFlow, ItemStack itemStack) {
        return new int[0];
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent event, @Nonnull BlockMenu blockMenu, @Nonnull Location location) {
        super.onBreak(event, blockMenu, location);
        setNotWorking(blockMenu.getBlock());
        CrystamaeHistoria.inst().getLogger().info(armourStand.getCustomName());
        armourStand.remove();
        blockMenu.dropItems(location, INPUT_SLOT);
    }

    @Override
    protected void onPlace(@NotNull BlockPlaceEvent e, @NotNull Block b) {
        super.onPlace(e, b);
        armourStandName = generateStandName(b);
        armourStand = setupArmourStand(b);
    }

    @Override
    protected void onNewInstance(@Nonnull BlockMenu blockMenu, @Nonnull Block b) {
        super.onNewInstance(blockMenu, b);
        this.menu = blockMenu;
        String working = BlockStorage.getLocationInfo(b.getLocation(), KeyHolder.BS_CP_WORKING_ON);
        armourStandName = generateStandName(b);
        if (working != null) {
            setWorking(b, Material.valueOf(working));
        }
    }

    private String generateStandName(Block block) {
        return KeyHolder.PANEL_STAND_PREFIX + block.getX() + "|" + block.getY() + "|" + block.getZ();
    }

    private ArmorStand setupArmourStand(Block block) {
        // Check for an existing stand
        for (ArmorStand e : block.getLocation().getNearbyEntitiesByType(ArmorStand.class, 2)) {
            String name = e.getCustomName();
            CrystamaeHistoria.inst().getLogger().info(name);
            CrystamaeHistoria.inst().getLogger().info(armourStandName);
            CrystamaeHistoria.inst().getLogger().info(String.valueOf(name != null && name.equals(armourStandName)));
            if (name != null && name.equals(armourStandName)) {
                // Found the sucker!
                return e;
            }
        }
        // None found, spawn in a new one
        ArmorStand a = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().clone().add(0.5, -0.6, 0.5), EntityType.ARMOR_STAND);
        a.setVisible(false);
        a.setGravity(false);
        a.setBasePlate(false);
        a.setCustomNameVisible(false);
        a.setRemoveWhenFarAway(false);
        a.setCollidable(false);
        a.setCustomName(generateStandName(block));
        return a;
    }

    private void setDisplayItem(boolean clear) {
        if (clear) {
            armourStand.getEquipment().setHelmet(null);
        } else {
            armourStand.getEquipment().setHelmet(new ItemStack(workingOn));
        }
    }

    @Override
    protected boolean synchronised() {
        return true;
    }
}
