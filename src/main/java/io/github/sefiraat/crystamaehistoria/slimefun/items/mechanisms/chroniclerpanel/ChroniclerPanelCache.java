package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.chroniclerpanel;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.managers.StoriesManager;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.runnables.spells.FloatingHeadAnimation;
import io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.ArmourStandUtils;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import lombok.Getter;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class ChroniclerPanelCache extends AbstractCache {

    private final int tier;
    @Nullable
    private Material workingOn;
    private boolean working;
    private BlockDefinition blockDefinition;
    private FloatingHeadAnimation animation;
    private Location blockMiddle;
    private boolean lightDimming = true;
    private UUID armorStandUUID;

    @ParametersAreNonnullByDefault
    public ChroniclerPanelCache(BlockMenu blockMenu, int tier) {
        super(blockMenu);
        this.tier = tier;

        final String workingOnString = BlockStorage.getLocationInfo(blockMenu.getLocation(), Keys.BS_CP_WORKING_ON);
        if (workingOnString != null) {
            setWorking(blockMenu.getBlock(), Material.valueOf(workingOnString));
        }

        final String activePlayerString = BlockStorage.getLocationInfo(blockMenu.getLocation(), Keys.BS_CP_ACTIVE_PLAYER);
        if (activePlayerString != null) {
            this.activePlayer = UUID.fromString(activePlayerString);
        }
    }

    @ParametersAreNonnullByDefault
    protected void setWorking(Block block, Material material) {
        final Block lightBlock = block.getRelative(BlockFace.UP);

        blockMiddle = block.getLocation().clone().add(0.5, 0.5, 0.5);
        workingOn = material;
        working = true;

        BlockStorage.addBlockInfo(block, Keys.BS_CP_WORKING_ON, material.toString());
        if (lightBlock.getType() == Material.AIR) {
            lightBlock.setType(Material.LIGHT);
        }
        startAnimation();
        blockDefinition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);
    }

    private void startAnimation() {
        final ArmorStand armourStand = getDisplayStand();

        ArmourStandUtils.panelAnimationReset(armourStand, blockMenu.getBlock());
        animation = new FloatingHeadAnimation(armourStand);
        animation.runTaskTimer(CrystamaeHistoria.getInstance(), 0, FloatingHeadAnimation.SPEED);
    }

    @ParametersAreNonnullByDefault
    private ArmorStand getDisplayStand() {
        if (armorStandUUID == null) {
            final String uuidString = BlockStorage.getLocationInfo(getLocation(), "ch_display_stand");
            if (uuidString != null) {
                armorStandUUID = UUID.fromString(uuidString);
            } else {
                final Block block = blockMenu.getBlock();
                final ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(getLocation().add(0.5, -0.6, 0.5), EntityType.ARMOR_STAND);
                ArmourStandUtils.setDisplay(armorStand);
                BlockStorage.addBlockInfo(block.getLocation(), "ch_display_stand", armorStand.getUniqueId().toString());
                armorStandUUID = armorStand.getUniqueId();
                return armorStand;
            }
        }
        return (ArmorStand) Bukkit.getEntity(armorStandUUID);
    }

    protected Location getLocation() {
        return blockMenu.getLocation().clone();
    }

    protected void process() {
        final Block block = blockMenu.getBlock();
        final ItemStack inputItem = blockMenu.getItemInSlot(ChroniclerPanel.INPUT_SLOT);

        // No item inserted, try to pick up (T5 +) or shutdown
        if (inputItem == null || inputItem.getType() == Material.AIR) {
            if (this.tier >= 5) {
                tryInsertItem();
            }
            return;
        }

        if (!StoryUtils.canBeStoried(inputItem, this.tier + 1)) {
            reject(inputItem);
            shutdown();
            return;
        }

        rejectOverage(inputItem);

        if (!StoryUtils.isStoried(inputItem)) {
            StoryUtils.makeStoried(inputItem);
        }

        if (!StoryUtils.hasRemainingStorySlots(inputItem)) {
            if (this.tier >= 5) {
                pushOutItem();
            }
            shutdown();
            return;
        }

        // A block is in the Input slot. Does the current item being worked on match the input?
        final Material inputItemType = inputItem.getType();
        if (!working || workingOn != inputItemType) {
            // Either not working or workingOn has changed
            setWorking(block, inputItemType);
            ArmourStandUtils.setDisplayItem(getDisplayStand(), workingOn);
        } else {
            // Working with an item in slot while workingOn matches means we can process the item
            animateLight();
            processStack(inputItem);
        }
    }

    private void tryInsertItem() {
        final Collection<Entity> entities = getWorld().getNearbyEntities(
            getLocation().clone().add(0.5, 0.5, 0.5),
            0.3,
            0.3,
            0.3,
            Item.class::isInstance
        );

        if (entities.isEmpty()) {
            shutdown();
        } else {
            final Item item = (Item) entities.stream().findFirst().orElse(null);
            final ItemStack itemStack = item.getItemStack();
            final ItemStack clone = itemStack.asQuantity(1);

            this.blockMenu.replaceExistingItem(ChroniclerPanel.INPUT_SLOT, clone);

            final int amount = CrystamaeHistoria.getSupportedPluginManager().getStackAmount(item);

            if (amount == 1) {
                item.remove();
            } else {
                CrystamaeHistoria.getSupportedPluginManager().setStackAmount(item, amount - 1);
            }
        }
    }

    private void pushOutItem() {
        final Location pushLocation = this.blockMiddle.clone().subtract(0, 1, 0);
        pushLocation.getWorld().dropItem(pushLocation, this.blockMenu.getItemInSlot(ChroniclerPanel.INPUT_SLOT).clone());
        this.blockMenu.replaceExistingItem(ChroniclerPanel.INPUT_SLOT, null);
    }

    public void shutdown() {
        if (working) {
            setNotWorking(blockMenu.getBlock());
            ArmourStandUtils.clearDisplayItem(getDisplayStand());
        }
    }

    @ParametersAreNonnullByDefault
    protected void rejectOverage(ItemStack i) {
        if (i.getAmount() > 1) {
            final ItemStack i2 = i.clone();
            i.setAmount(1);
            i2.setAmount(i2.getAmount() - 1);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), i2);
        }
    }

    protected void reject(@Nullable ItemStack itemStack) {
        if (itemStack != null) {
            final ItemStack rejectedSpawn = itemStack.clone();
            itemStack.setAmount(0);
            blockMenu.getBlock().getWorld().dropItemNaturally(blockMenu.getLocation(), rejectedSpawn);
        }
    }

    @ParametersAreNonnullByDefault
    private void setNotWorking(Block block) {
        final Block lightBlock = block.getRelative(BlockFace.UP);

        workingOn = null;
        working = false;
        BlockStorage.addBlockInfo(block, Keys.BS_CP_WORKING_ON, null);

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
            final int remaining = StoryUtils.getRemainingStoryAmount(i);
            final int req = blockDefinition.getBlockTier().chroniclingChance;
            if (GeneralUtils.testChance(req, 10000)) {
                // We can chronicle a story
                StoryUtils.requestNewStory(i);
                if (remaining == 1) {
                    // That was the last story, unlock unique and set research
                    StoryUtils.requestUniqueStory(i);
                    if (activePlayer != null) {
                        if (!PlayerStatistics.hasUnlockedUniqueStory(activePlayer, blockDefinition)) {
                            PlayerStatistics.unlockUniqueStory(activePlayer, blockDefinition);
                        }
                        PlayerStatistics.addChronicle(activePlayer, blockDefinition);
                    }
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

    protected Location getLocation(boolean centered) {
        if (centered) {
            return getLocation().add(0.5, 0.5, 0.5);
        } else {
            return getLocation();
        }
    }

}
