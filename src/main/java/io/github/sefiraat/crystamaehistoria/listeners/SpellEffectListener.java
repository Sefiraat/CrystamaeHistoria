package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastDefinition;
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
            CastDefinition castDefinition = CrystamaeHistoria.getEffectMap().get(projectile).getFirstValue();
            event.setCancelled(true);
            if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {

                // Magic projectile hit a valid entity - let the magic begin
                double range = castDefinition.getAoeRange();

                LivingEntity hitEntity = (LivingEntity) event.getHitEntity();
                Location location = hitEntity.getLocation();

                castDefinition.setMainTarget(hitEntity);
                castDefinition.setDamageLocation(location);

                if (range > 0) {
                    for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
                        if (entity instanceof LivingEntity && entity != castDefinition.getCaster() && entity.getUniqueId() != hitEntity.getUniqueId()) {
                            castDefinition.getAdditionalTargets().add((LivingEntity) entity);
                        }
                    }
                }

                // TODO Combine?
                castDefinition.runPreAffectEvent();

                castDefinition.runAffectEvent();

                castDefinition.runPostAffectEvent();

            }

            CrystamaeHistoria.getEffectMap().remove(projectile);
            event.getEntity().remove();

        }

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        LightningStrike lightningStrike = event.getLightning();
        if (CrystamaeHistoria.getEffectMap().containsKey(lightningStrike)) {
            CastDefinition castDefinition = CrystamaeHistoria.getEffectMap().get(lightningStrike).getFirstValue();
            event.setCancelled(true);

            double range = castDefinition.getAoeRange();
            Location location = event.getLightning().getLocation();

            castDefinition.setDamageLocation(location);

            if (range > 0) {
                for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
                    if (entity instanceof LivingEntity && entity != castDefinition.getCaster()) {
                        castDefinition.getAdditionalTargets().add((LivingEntity) entity);
                    }
                }
            }

            // TODO Combine?
            castDefinition.runPreAffectEvent();

            castDefinition.runAffectEvent();

            castDefinition.runPostAffectEvent();

        }

        CrystamaeHistoria.getEffectMap().remove(lightningStrike);

    }

}
