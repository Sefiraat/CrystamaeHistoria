package io.github.sefiraat.crystamaehistoria.slimefun.machines.chroniclerpanel;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.runnables.animation.FloatingHeadAnimation;
import io.github.sefiraat.crystamaehistoria.slimefun.machines.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
import io.github.sefiraat.crystamaehistoria.stories.StoriesManager;
import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import lombok.Getter;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Light;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class ChroniclerPanelCache extends AbstractCache {

    @Nullable
    private Material workingOn;
    private boolean working;
    private StoriedBlockDefinition storiedBlockDefinition;
    private FloatingHeadAnimation animation;
    private Location blockMiddle;
    private boolean lightDimming = true;

    @ParametersAreNonnullByDefault
    public ChroniclerPanelCache(BlockMenu blockMenu) {
        super(blockMenu);
        String workingOnString = BlockStorage.getLocationInfo(blockMenu.getLocation(), Keys.BS_CP_WORKING_ON);
        if (workingOnString != null) {
            setWorking(blockMenu.getBlock(), Material.valueOf(workingOnString));
        }
    }

    protected void process() {
        Block b = blockMenu.getBlock();

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
                    ArmourStandUtils.setDisplayItem(getDisplayStand(), workingOn);
                } else {
                    // Working with an item in slot while workingOn matches means we can process the item
                    animateLight();
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
            ArmourStandUtils.clearDisplayItem(getDisplayStand());
        }
    }

    @ParametersAreNonnullByDefault
    protected void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), i2);
        }
    }

    @ParametersAreNonnullByDefault
    protected void setWorking(Block block, Material m) {
        blockMiddle = block.getLocation().clone().add(0.5, 0.5, 0.5);
        workingOn = m;
        working = true;
        BlockStorage.addBlockInfo(block, Keys.BS_CP_WORKING_ON, m.toString());
        Block lightBlock = block.getRelative(BlockFace.UP);
        if (lightBlock.getType() == Material.AIR) {
            lightBlock.setType(Material.LIGHT);
        }
        startAnimation();
        storiedBlockDefinition = CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().get(m);
    }

    private void startAnimation() {
        ArmorStand armourStand = getDisplayStand();
        ArmourStandUtils.panelAnimationReset(armourStand, blockMenu.getBlock());
        animation = new FloatingHeadAnimation(armourStand);
        animation.runTaskTimer(CrystamaeHistoria.getInstance(), 0, FloatingHeadAnimation.SPEED);
    }

    @ParametersAreNonnullByDefault
    private void setNotWorking(Block block) {
        workingOn = null;
        working = false;
        BlockStorage.addBlockInfo(block, Keys.BS_CP_WORKING_ON, null);
        Block lightBlock = block.getRelative(BlockFace.UP);
        if (lightBlock.getType() == Material.LIGHT) {
            lightBlock.setType(Material.AIR);
        }
        if (animation != null) {
            animation.cancel();
        }
        ArmourStandUtils.panelAnimationReset(getDisplayStand(), block);
    }

    @ParametersAreNonnullByDefault
    private void processStack(ItemStack i) {
        summonParticles();
        // If this block isn't storied, make it storied then add the initial story set
        if (StoryUtils.hasRemainingStorySlots(i)) {
            int remaining = StoryUtils.getRemainingStoryAmount(i);
            int req = storiedBlockDefinition.getTier().chroniclingChance;
            if (GeneralUtils.testChance(req, 10000)) {
                // We can chronicle a story
                StoryUtils.requestNewStory(i);
                if (remaining == 1) {
                    // That was the last story, unlock unique
                    StoryUtils.requestUniqueStory(i);
                }
                StoriesManager.rebuildStoriedStack(i);
                blockMenu.getBlock().getWorld().strikeLightningEffect(blockMiddle);
            }
        }
    }

    private void animateLight() {
        final Block block = blockMenu.getBlock().getRelative(BlockFace.UP);
        if (block.getType() == Material.LIGHT) {
            final Light light = (Light) block.getBlockData();
            int level = light.getLevel();
            if (level >= 15) {
                light.setLevel(level - 1);
                lightDimming = true;
            } else if (level <= 5) {
                light.setLevel(level + 1);
                lightDimming = false;
            } else {
                light.setLevel(lightDimming ? level - 1 : level + 1);
            }
            block.setBlockData(light);
        }
    }

    private void summonParticles() {
        final Location location = blockMenu.getLocation();
        for (int i = 0; i < 2; i++) {
            final Location l = location.clone().add(ThreadLocalRandom.current().nextDouble(0, 1.1), 1, ThreadLocalRandom.current().nextDouble(0, 1.1));
            location.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, l, 0, 0.2, 0, -0.2, 0);
        }
    }

    protected void kill() {
        setNotWorking(blockMenu.getBlock());
        getDisplayStand().remove();
    }

    protected World getWorld() {
        return blockMenu.getLocation().getWorld();
    }

    protected Location getLocation() {
        return blockMenu.getLocation().clone();
    }

    protected Location getLocation(boolean centered) {
        if (centered) {
            return getLocation().add(0.5, 0.5, 0.5);
        } else {
            return getLocation();
        }
    }

    @ParametersAreNonnullByDefault
    private ArmorStand getDisplayStand() {
        Block block = blockMenu.getBlock();
        String uuidString = BlockStorage.getLocationInfo(getLocation(), "ch_display_stand");
        if (uuidString != null) {
            UUID uuid = UUID.fromString(uuidString);
            return (ArmorStand) Bukkit.getEntity(uuid);
        } else {
            final ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(getLocation().add(0.5, -0.6, 0.5), EntityType.ARMOR_STAND);
            ArmourStandUtils.setDisplay(armorStand);
            BlockStorage.addBlockInfo(block.getLocation(), "ch_display_stand", armorStand.getUniqueId().toString());
            return armorStand;
        }
    }

}
