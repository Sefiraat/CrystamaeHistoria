package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
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
            SpellDefinition spellDefinition = CrystamaeHistoria.getEffectMap().get(projectile).getFirstValue();
            event.setCancelled(true);
            if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {

                // Magic projectile hit a valid entity - let the magic begin
                double range = spellDefinition.getAoeRange();

                LivingEntity hitEntity = (LivingEntity) event.getHitEntity();
                Location location = hitEntity.getLocation();

                spellDefinition.setMainTarget(hitEntity);
                spellDefinition.setDamageLocation(location);

                if (range > 0) {
                    for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
                        if (entity instanceof LivingEntity && entity != spellDefinition.getCaster() && entity.getUniqueId() != hitEntity.getUniqueId()) {
                            spellDefinition.getAdditionalTargets().add((LivingEntity) entity);
                        }
                    }
                }

                // TODO Combine?
                spellDefinition.runPreAffectEvent();

                spellDefinition.runAffectEvent();

                spellDefinition.runPostAffectEvent();

            }

            CrystamaeHistoria.getEffectMap().remove(projectile);
            event.getEntity().remove();

        }

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        LightningStrike lightningStrike = event.getLightning();
        if (CrystamaeHistoria.getEffectMap().containsKey(lightningStrike)) {
            SpellDefinition spellDefinition = CrystamaeHistoria.getEffectMap().get(lightningStrike).getFirstValue();
            event.setCancelled(true);

            double range = spellDefinition.getAoeRange();
            Location location = event.getLightning().getLocation();

            spellDefinition.setDamageLocation(location);

            if (range > 0) {
                for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
                    if (entity instanceof LivingEntity && entity != spellDefinition.getCaster()) {
                        spellDefinition.getAdditionalTargets().add((LivingEntity) entity);
                    }
                }
            }

            // TODO Combine?
            spellDefinition.runPreAffectEvent();

            spellDefinition.runAffectEvent();

            spellDefinition.runPostAffectEvent();

        }

        CrystamaeHistoria.getEffectMap().remove(lightningStrike);

    }

}
