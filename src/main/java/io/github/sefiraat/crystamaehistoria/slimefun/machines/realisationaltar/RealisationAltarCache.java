package io.github.sefiraat.crystamaehistoria.slimefun.machines.realisationaltar;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.animation.DisplayStand;
import io.github.sefiraat.crystamaehistoria.runnables.animation.FloatingHeadAnimation;
import io.github.sefiraat.crystamaehistoria.slimefun.AbstractCache;
import io.github.sefiraat.crystamaehistoria.stories.StoriedBlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.AnimateUtils;
import io.github.sefiraat.crystamaehistoria.utils.KeyHolder;
import io.github.sefiraat.crystamaehistoria.utils.StackUtils;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import lombok.Getter;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class RealisationAltarCache extends AbstractCache {

    @Getter @Setter private DisplayStand displayStand;
    @Nullable @Getter @Setter private Material workingOn;
    @Getter @Setter private boolean working;
    @Getter @Setter private StoriedBlockDefinition storiedBlockDefinition;
    @Getter @Setter private String armourStandName;
    @Getter @Setter private FloatingHeadAnimation animation;
    @Getter @Setter private Location blockMiddle;

    @ParametersAreNonnullByDefault
    public RealisationAltarCache(BlockMenu blockMenu) {
        super(blockMenu);
        String working = BlockStorage.getLocationInfo(blockMenu.getLocation(), KeyHolder.BS_CP_WORKING_ON);
        if (working != null) {
            setWorking(blockMenu.getBlock(), Material.valueOf(working));
        }
        armourStandName = generateStandName(blockMenu.getBlock());
        displayStand = getDisplayStand(blockMenu.getBlock());
    }

    protected void process() {
        Block b = blockMenu.getBlock();

        // Set up armor stand if empty (after restart)
        if (displayStand == null) {
            displayStand = getDisplayStand(b);
        }

        ItemStack i = blockMenu.getItemInSlot(RealisationAltar.INPUT_SLOT);

        if (i != null && i.getType() != Material.AIR && StoryUtils.canBeStoried(i)) {
            rejectOverage(i);
            if (!StoryUtils.isStoried(i)) {
                StoryUtils.makeStoried(i);
            }
            // A block is in the Input slot. Does the current item being worked on match the input?
            Material m = i.getType();
            if (!working || workingOn != m) {
                // Either not working or workingOn has changed
                setWorking(b, m);
                displayStand.setDisplayItem(workingOn);
            } else {
                // Working with an item in slot while workingOn matches means we can process the item
                processStack(i);
            }
        } else {
            shutdown();
        }
    }

    protected void shutdown() {
        if (working) {
            setNotWorking(blockMenu.getBlock());
            displayStand.clearDisplayItem();
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
        BlockStorage.addBlockInfo(block, KeyHolder.BS_CP_WORKING_ON, m.toString());
        Block lightBlock = block.getRelative(BlockFace.UP);
        if (lightBlock.getType() == Material.AIR) {
            lightBlock.setType(Material.LIGHT);
        }
        if (displayStand != null) {
            startAnimation();
        }
        storiedBlockDefinition = CrystamaeHistoria.getStoriesManager().getStoriedBlockDefinitionMap().get(m);
    }

    private void startAnimation() {
    }

    @ParametersAreNonnullByDefault
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
        AnimateUtils.panelAnimationReset(displayStand.getArmorStand(), block);
    }

    @ParametersAreNonnullByDefault
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

    }

    protected void kill() {
        setNotWorking(blockMenu.getBlock());
        displayStand.kill();
    }

    @ParametersAreNonnullByDefault
    String generateStandName(Block block) {
        return KeyHolder.PANEL_STAND_PREFIX + block.getX() + "|" + block.getY() + "|" + block.getZ();
    }

    @ParametersAreNonnullByDefault
    private DisplayStand getDisplayStand(Block block) {
        DisplayStand displayStand = DisplayStand.get(block);
        if (displayStand == null) {
            displayStand = new DisplayStand(block);
        }
        return displayStand;
    }

}
