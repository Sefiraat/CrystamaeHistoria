package io.github.sefiraat.crystamaehistoria.slimefun.gadgets;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.player.BlockRank;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import io.github.sefiraat.crystamaehistoria.slimefun.materials.Trophy;
import io.github.sefiraat.crystamaehistoria.slimefun.types.Stand;
import io.github.sefiraat.crystamaehistoria.stories.BlockDefinition;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.TextUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class TrophyDisplay extends Stand {

    private Consumer<Location> locationConsumer = null;

    @ParametersAreNonnullByDefault
    public TrophyDisplay(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler((BlockUseHandler) this::onRightClick);
    }

    @Override
    public void onRightClick(PlayerRightClickEvent e) {
        final Player player = e.getPlayer();

        Optional<Block> optionalBlock = e.getClickedBlock();

        if (!optionalBlock.isPresent()) {
            return;
        }

        e.cancel();

        final Block blockClicked = optionalBlock.get();
        final UUID currentItemUuid = itemMap.get(blockClicked.getLocation());

        // Stand already has an item, we try to remove this then return;
        if (currentItemUuid != null) {
            final Item currentItem = (Item) Bukkit.getEntity(currentItemUuid);
            final ItemStack itemStack = currentItem.getItemStack();
            final HashMap<Integer, ItemStack> rejected = player.getInventory().addItem(itemStack);
            BlockStorage.addBlockInfo(blockClicked, PDC_ITEM, null);
            itemMap.remove(blockClicked.getLocation());
            if (rejected.isEmpty()) {
                currentItem.remove();
                this.locationConsumer = null;
            }
            return;
        }

        final ItemStack itemStack = e.getItem();
        final Material material = itemStack.getType();
        final BlockDefinition definition = CrystamaeHistoria.getStoriesManager().getBlockDefinitionMap().get(material);

        if (definition != null && material != Material.AIR) {
            final BlockRank blockRank = PlayerStatistics.getBlockRank(
                player.getUniqueId(),
                definition
            );
            if (blockRank == BlockRank.SME) {
                addItem(blockClicked, itemStack, TextUtils.toTitleCase(material.toString()));
                this.locationConsumer = this::defaultConsumer;
            } else {
                SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
                if (slimefunItem instanceof Trophy) {
                    Trophy trophy = (Trophy) slimefunItem;
                    this.locationConsumer = trophy.getDisplayConsumer();
                    addItem(blockClicked, itemStack, slimefunItem.getItemName());
                }
            }
        }
    }

    private void addItem(Block block, ItemStack itemStack, String name) {
        final Location location = block.getLocation().add(0.5, 1.5, 0.5);
        final String finalName = ChatColor.of("#FFD100") + name;
        Item item = GeneralUtils.spawnDisplayItem(itemStack.asQuantity(1), location, finalName);
        itemStack.setAmount(itemStack.getAmount() - 1);
        BlockStorage.addBlockInfo(block, PDC_ITEM, item.getUniqueId().toString());
        itemMap.put(block.getLocation(), item.getUniqueId());
    }

    @Override
    public void afterTick(@Nonnull Item item, @Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        if (locationConsumer != null) {
            final Location itemLocation = item.getLocation();
            locationConsumer.accept(itemLocation);
        }
    }

    public void defaultConsumer(@Nonnull Location location) {
        ParticleUtils.displayParticleEffect(location.add(0, 0.2, 0), Particle.WAX_ON, 0.2, 3);
    }
}
