package io.github.sefiraat.crystamaehistoria.slimefun.machines.chroniclerpanel;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.animation.FloatingHeadAnimation;
import io.github.sefiraat.crystamaehistoria.slimefun.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.AnimateUtils;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import io.github.sefiraat.crystamaehistoria.utils.StackUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.WorldUtils;
import lombok.Getter;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class ChroniclerPanelCache extends AbstractCache {

    @Getter @Setter private ArmorStand armourStand;
    @Nullable @Getter @Setter private Material workingOn;
    @Getter @Setter private boolean working;
    @Getter @Setter private StoriedBlockDefinition storiedBlockDefinition;
    @Getter @Setter private String armourStandName;
    @Getter @Setter private FloatingHeadAnimation animation;
    @Getter @Setter private Location blockMiddle;

    public ChroniclerPanelCache(BlockMenu blockMenu) {
        super(blockMenu);
        String working = BlockStorage.getLocationInfo(blockMenu.getLocation(), KeyHolder.BS_CP_WORKING_ON);
        if (working != null) {
            setWorking(blockMenu.getBlock(), Material.valueOf(working));
        }
        armourStandName = generateStandName(blockMenu.getBlock());
        armourStand = getArmourStand(blockMenu.getBlock());
    }

    protected void process() {

        Block b = blockMenu.getBlock();

        // Set up armor stand if empty (after restart)
        if (armourStand == null) {
            armourStand = getArmourStand(b);
        }

        ItemStack i = blockMenu.getItemInSlot(ChroniclerPanel.INPUT_SLOT);

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
                    setWorking(b, m);
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

    protected void shutdown() {
        if (working) {
            setNotWorking(blockMenu.getBlock());
            setDisplayItem(true);
        }
    }

    protected void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), i2);
        }
    }

    protected void setWorking(Block block, Material m) {
        blockMiddle = block.getLocation().clone().add(0.5, 0.5, 0.5);
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
        storiedBlockDefinition = CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().get(m);
    }

    private void startAnimation() {
        AnimateUtils.panelAnimationReset(armourStand, blockMenu.getBlock());
        animation = new FloatingHeadAnimation(armourStand);
        animation.runTaskTimer(CrystamaeHistoria.inst(), 0, FloatingHeadAnimation.SPEED);
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
        if (StoryUtils.hasRemainingStorySlots(i)) {
            int rnd = ThreadLocalRandom.current().nextInt(1, 1001);
            int req = storiedBlockDefinition.getTier().chroniclingChance;
            if (rnd <= req) {
                // We can chronicle a story
                StoryUtils.requestNewStory(i);
                StackUtils.rebuildStoriedStack(i);
                blockMenu.getBlock().getWorld().strikeLightningEffect(blockMiddle);
            }
        }
    }

    private void summonParticles() {
        Location l1 = blockMenu.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1),2,ThreadLocalRandom.current().nextDouble(0, 1.1));
        Location l2 = blockMenu.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1),2,ThreadLocalRandom.current().nextDouble(0, 1.1));
        Location l3 = blockMenu.getLocation().clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1),2,ThreadLocalRandom.current().nextDouble(0, 1.1));
        int r1 = ThreadLocalRandom.current().nextInt(150,250);
        int r2 = ThreadLocalRandom.current().nextInt(150,250);
        int r3 = ThreadLocalRandom.current().nextInt(150,250);
        World w = l1.getWorld();
        w.spawnParticle(Particle.ENCHANTMENT_TABLE, l1, 0,0.2,0,-0.2, 0);
        w.spawnParticle(Particle.ENCHANTMENT_TABLE, l2, 0,0.2,0,-0.2, 0);
        w.spawnParticle(Particle.ENCHANTMENT_TABLE, l3, 0,0.2,0,-0.2, 0);
    }

    protected void kill(@Nonnull Location location) {
        setNotWorking(blockMenu.getBlock());
        armourStand.remove();
    }

    String generateStandName(Block block) {
        return KeyHolder.PANEL_STAND_PREFIX + block.getX() + "|" + block.getY() + "|" + block.getZ();
    }

    private ArmorStand getArmourStand(Block block) {
        // Check for an existing stand
        for (Entity e : WorldUtils.getNearbyEntities(block, 2, 2, 2)) {
            if (!(e instanceof ArmorStand)) continue;
            ArmorStand a = (ArmorStand) e;
            String name = EntityUtils.getArmourStandInternal(a);
            if (name != null && name.equals(armourStandName)) {
                // Found the sucker!
                return a;
            }
        }
        // None found, spawn in a new one
        ArmorStand a = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().clone().add(0.5, -0.6, 0.5), EntityType.ARMOR_STAND);
        EntityUtils.setArmourStandInternal(a, generateStandName(block));
        EntityUtils.setDisplayOnlyArmourStand(a);
        return a;
    }


    private void setDisplayItem(boolean clear) {
        if (clear) {
            armourStand.getEquipment().setHelmet(null);
        } else {
            armourStand.getEquipment().setHelmet(new ItemStack(workingOn));
        }
    }

}
