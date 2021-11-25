package io.github.sefiraat.crystamaehistoria.slimefun.tools.magicpaintbrush;

import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.Optional;

public interface MagicPaintbrush {

    static ItemStack getTippedBrush(DyeColor dyeColor) {
        return getTippedBrush(dyeColor, false);
    }

    static ItemStack getTippedBrush(DyeColor dyeColor, boolean enchanted) {
        ItemStack itemStack = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.setColor(dyeColor.getColor());
        if (enchanted) {
            potionMeta.addEnchant(Enchantment.LUCK, 1, true);
        }
        potionMeta.addItemFlags(
            ItemFlag.HIDE_ENCHANTS,
            ItemFlag.HIDE_ATTRIBUTES,
            ItemFlag.HIDE_POTION_EFFECTS
        );
        itemStack.setItemMeta(potionMeta);
        return itemStack;
    }

    default boolean tryPaint(PlayerRightClickEvent event, PaintProfile profile) {
        final Optional<Block> optionalBlock = event.getClickedBlock();

        if (!optionalBlock.isPresent()) {
            return false;
        }

        final Block block = optionalBlock.get();

        if (!GeneralUtils.hasPermission(event.getPlayer(), block, Interaction.PLACE_BLOCK)) {
            return false;
        }

        final Material material = block.getType();

        if (Tag.WOOL.isTagged(material)
            && block.getType() != profile.getMaterialWool()
        ) {
            block.setType(profile.getMaterialWool());
            return true;
        } else if (SlimefunTag.TERRACOTTA.isTagged(material)
            && block.getType() != profile.getMaterialTerracotta()
        ) {
            block.setType(profile.getMaterialTerracotta());
            return true;
        } else if (CrystaTag.GLAZED_TERRACOTTA.isTagged(material)
            && block.getType() != profile.getMaterialGlazedTerracotta()
        ) {
            block.setType(profile.getMaterialGlazedTerracotta());
            return true;
        } else if (SlimefunTag.CONCRETE_POWDERS.isTagged(material)
            && block.getType() != profile.getMaterialConcretePowder()
        ) {
            block.setType(profile.getMaterialConcretePowder());
            return true;
        } else if (CrystaTag.CONCRETE_BLOCKS.isTagged(material)
            && block.getType() != profile.getMaterialConcrete()
        ) {
            block.setType(profile.getMaterialConcrete());
            return true;
        } else if (Tag.CARPETS.isTagged(material)
            && block.getType() != profile.getMaterialCarpet()
        ) {
            block.setType(profile.getMaterialCarpet());
            return true;
        }

        return false;
    }
}
