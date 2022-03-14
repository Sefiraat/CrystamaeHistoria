package io.github.sefiraat.crystamaehistoria.slimefun.items.artistic;

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
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

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

    default boolean tryPaint(PlayerRightClickEvent event, PaintProfile profile, boolean allowEntities) {
        final Block block = event.getPlayer().getTargetBlockExact(100);
        final Entity entity = event.getPlayer().getTargetEntity(100);

        if (allowEntities && entity != null && GeneralUtils.hasPermission(event.getPlayer(), entity.getLocation(), Interaction.INTERACT_ENTITY)) {
            return tryPaintEntity(profile, entity);
        }

        if (block != null && GeneralUtils.hasPermission(event.getPlayer(), block, Interaction.PLACE_BLOCK)) {
            return tryPaintBlock(profile, block);
        }

        return false;
    }

    default boolean tryPaintEntity(PaintProfile profile, Entity entity) {
        final EntityType entityType = entity.getType();

        if (entityType == EntityType.SHULKER) {
            Shulker shulker = (Shulker) entity;
            if (shulker.getColor() != profile.getDyeColor()) {
                shulker.setColor(profile.getDyeColor());
                return true;
            }
        } else if (entityType == EntityType.SHEEP) {
            Sheep sheep = (Sheep) entity;
            if (sheep.getColor() != profile.getDyeColor()) {
                sheep.setColor(profile.getDyeColor());
                return true;
            }
        } else if (entityType == EntityType.PARROT && profile.getParrotVariant() != null) {
            Parrot parrot = (Parrot) entity;
            if (parrot.getVariant() != profile.getParrotVariant()) {
                parrot.setVariant(profile.getParrotVariant());
                return true;
            }
        } else if (entityType == EntityType.AXOLOTL && profile.getAxolotlVariant() != null) {
            Axolotl axolotl = (Axolotl) entity;
            if (axolotl.getVariant() != profile.getAxolotlVariant()) {
                axolotl.setVariant(profile.getAxolotlVariant());
                return true;
            }
        }

        return false;
    }

    default boolean tryPaintBlock(PaintProfile profile, Block block) {
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
        } else if (SlimefunTag.GLASS_BLOCKS.isTagged(material)
            && block.getType() != profile.getMaterialGlass()
        ) {
            block.setType(profile.getMaterialGlass());
            return true;
        } else if (SlimefunTag.GLASS_PANES.isTagged(material)
            && block.getType() != profile.getMaterialGlassPane()
        ) {
            block.setType(profile.getMaterialGlassPane());
            return true;
        } else if (Tag.SHULKER_BOXES.isTagged(material)
            && block.getType() != profile.getMaterialShulker()
        ) {
            block.setType(profile.getMaterialShulker());
            return true;
        }

        return false;
    }
}
