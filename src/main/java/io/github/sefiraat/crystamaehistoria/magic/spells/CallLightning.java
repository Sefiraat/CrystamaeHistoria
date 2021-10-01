package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.utils.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class CallLightning extends Spell {

    public CallLightning() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(50, true, 100, false, 5, true)
                .makeDamagingSpell(2, true, 0, false)
                .makeProjectileSpell(this::fireProjectiles, this::projectileHit, 2, false, 0.5, true)
                .addBeforeProjectileHitEvent(this::beforeProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    public void fireProjectiles(@Nonnull SpellCastInformation spellCastInformation) {
        Player player = Bukkit.getPlayer(spellCastInformation.getCaster());
        if (player != null) {
            Block block = player.getTargetBlockExact((int) getRange(spellCastInformation));
            if (block != null) {
                Location location = block.getLocation();
                LightningStrike lightningStrike = location.getWorld().strikeLightning(location);
                registerProjectile(lightningStrike, spellCastInformation);
            }
        }
    }

    public void beforeProjectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, getProjectileAoe(spellCastInformation), true)) {
            livingEntity.setFireTicks(40);
        }
    }

    public void projectileHit(@Nonnull SpellCastInformation spellCastInformation) {
        for (LivingEntity livingEntity : getTargets(spellCastInformation, getProjectileAoe(spellCastInformation), true)) {
            damageEntity(livingEntity, spellCastInformation.getCaster(), getDamage(spellCastInformation), spellCastInformation.getDamageLocation(), getKnockback(spellCastInformation));
        }
    }
}
