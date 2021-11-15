package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.CrystaTag;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.material.Colorable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Bobulate extends Spell {

    public Bobulate() {
        final SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, true, 0, false, 10, false)
            .makeProjectileSpell(this::fireProjectile, 5, true, 0, false)
            .makeProjectileVsBlockSpell(this::projectileHit)
            .makeProjectileVsEntitySpell(this::projectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectile(CastInformation castInformation) {
        final Location location = castInformation.getCastLocation();
        final Location aimLocation = location.clone().add(0, 1.5, 0).add(location.getDirection().multiply(2));
        final MagicProjectile magicProjectile = SpellUtils.summonMagicProjectile(castInformation, EntityType.SPLASH_POTION, aimLocation);
        magicProjectile.setVelocity(location.getDirection(), 1.5);
        magicProjectile.disableGravity();
    }

    public void projectileHit(CastInformation castInformation) {
        final UUID caster = castInformation.getCaster();
        final Location location = castInformation.getProjectileLocation();
        final double range = getProjectileAoe(castInformation);
        final int density = 50;

        for (double height = 0; height <= Math.PI; height += Math.PI / density) {
            for (int i = 0; i < range; i++) {
                final double r = i * Math.sin(height);
                final double y = i * Math.cos(height);
                for (double a = 0; a < Math.PI * 2; a += Math.PI / density) {
                    final double x = Math.cos(a) * r;
                    final double z = Math.sin(a) * r;
                    final Location pointLocation = location.clone().add(x, y, z);
                    final Block block = pointLocation.getBlock();
                    final Material material = block.getType();

                    if (Tag.WOOL.isTagged(material)) {
                        processBlock(caster, block, Tag.WOOL);
                    } else if (SlimefunTag.TERRACOTTA.isTagged(material)) {
                        processBlock(caster, block, SlimefunTag.TERRACOTTA);
                    } else if (CrystaTag.GLAZED_TERRACOTTA.isTagged(material)) {
                        processBlock(caster, block, CrystaTag.GLAZED_TERRACOTTA);
                    } else if (SlimefunTag.CONCRETE_POWDERS.isTagged(material)) {
                        processBlock(caster, block, SlimefunTag.CONCRETE_POWDERS);
                    } else if (CrystaTag.CONCRETE_BLOCKS.isTagged(material)) {
                        processBlock(caster, block, CrystaTag.CONCRETE_BLOCKS);
                    } else if (Tag.CARPETS.isTagged(material)) {
                        processBlock(caster, block, Tag.CARPETS);
                    }
                }
            }
        }
        processEntities(location, caster);
    }

    private void processEntities(Location location, UUID caster) {
        final Collection<Entity> entities = location.getWorld().getNearbyEntities(
            location,
            0.5,
            0.5,
            0.5
        );

        for (Entity entity : entities) {
            if (entity instanceof Colorable
                && GeneralUtils.hasPermission(caster, entity.getLocation(), Interaction.INTERACT_ENTITY)
            ) {
                final int randomValue = ThreadLocalRandom.current().nextInt(
                    0,
                    (int) Arrays.stream(DyeColor.values()).count()
                );
                ((Colorable) entity).setColor(DyeColor.values()[randomValue]);
            }
        }
    }

    private void processBlock(UUID caster, Block block, Tag<Material> tag) {
        if (GeneralUtils.hasPermission(caster, block, Interaction.PLACE_BLOCK)) {
            final List<Material> list = tag.getValues().stream().toList();
            final int randomValue = ThreadLocalRandom.current().nextInt(0, list.size());
            block.setType(list.get(randomValue));
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "BOBULATE";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Turns colorful things into other colorful",
            "things."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.CYAN_WOOL;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.ALCHEMICAL,
            StoryType.PHILOSOPHICAL
        );
    }
}
