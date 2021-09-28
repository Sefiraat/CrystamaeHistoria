package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import io.github.sefiraat.crystamaehistoria.magic.spells.interfaces.CastableProjectile;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;

public class CallLightning implements CastableProjectile {

    private static final int DAMAGE = 10;
    private static final int COOLDOWN = 200;
    private static final double KNOCK_BACK_FORCE = 5;
    private static final double AOE_RANGE = 5;

    @Override
    public void cast(@Nonnull SpellDefinition spellDefinition) {

        spellDefinition.setCastInformation(DAMAGE, AOE_RANGE, KNOCK_BACK_FORCE, COOLDOWN);

        Block block = spellDefinition.getCaster().getTargetBlockExact(100);
        if (block != null) {
            Location location = block.getLocation();
            LightningStrike lightningStrike = location.getWorld().strikeLightning(location);

            registerProjectile(lightningStrike, spellDefinition);
        }

    }

    @Override
    public void beforeAffect(@Nonnull SpellDefinition spellDefinition) {
        for (LivingEntity livingEntity : spellDefinition.getAllTargets()) {
            livingEntity.setFireTicks(40);
        }
    }

    @Override
    public void affect(@Nonnull SpellDefinition spellDefinition) {
        for (LivingEntity livingEntity : spellDefinition.getAllTargets()) {
            EntityUtils.damageEntity(livingEntity, spellDefinition.getCaster(), spellDefinition.getDamage(), spellDefinition.getDamageLocation(), spellDefinition.getKnockbackForce());
        }
    }

}
