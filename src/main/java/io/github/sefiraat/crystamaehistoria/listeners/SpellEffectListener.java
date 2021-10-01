package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.weather.LightningStrikeEvent;

public class SpellEffectListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (CrystamaeHistoria.getEffectMap().containsKey(projectile)) {
            SpellCastInformation spellCastInformation = CrystamaeHistoria.getEffectMap().get(projectile).getFirstValue();
            event.setCancelled(true);
            if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {

                LivingEntity hitEntity = (LivingEntity) event.getHitEntity();
                Location location = hitEntity.getLocation();

                spellCastInformation.setMainTarget(hitEntity);
                spellCastInformation.setDamageLocation(location);

                // TODO Combine?
                spellCastInformation.runPreAffectEvent();

                spellCastInformation.runAffectEvent();

                spellCastInformation.runPostAffectEvent();

            }

            CrystamaeHistoria.getEffectMap().remove(projectile);
            event.getEntity().remove();

        }

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        LightningStrike lightningStrike = event.getLightning();
        if (CrystamaeHistoria.getEffectMap().containsKey(lightningStrike)) {
            SpellCastInformation spellCastInformation = CrystamaeHistoria.getEffectMap().get(lightningStrike).getFirstValue();
            event.setCancelled(true);

            Location location = event.getLightning().getLocation();

            spellCastInformation.setDamageLocation(location);

            // TODO Combine?
            spellCastInformation.runPreAffectEvent();

            spellCastInformation.runAffectEvent();

            spellCastInformation.runPostAffectEvent();

        }

        CrystamaeHistoria.getEffectMap().remove(lightningStrike);

    }

}
