package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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

            if (spellCastInformation != null && eventAllowed(event, spellCastInformation)) {
                LivingEntity hitEntity = (LivingEntity) event.getHitEntity();
                Location location = hitEntity.getLocation();

                spellCastInformation.setMainTarget(hitEntity);
                spellCastInformation.setDamageLocation(location);

                // TODO Combine?
                spellCastInformation.runPreAffectEvent();
                spellCastInformation.runAffectEvent();
                spellCastInformation.runPostAffectEvent();
            }
            // Cancel the event regardless of the event being allowed later
            event.setCancelled(true);
            CrystamaeHistoria.getEffectMap().remove(projectile);
            event.getEntity().remove();
        }

    }

    private boolean eventAllowed(ProjectileHitEvent hitEvent, SpellCastInformation spellCastInformation) {
        Entity hitEntity = hitEvent.getHitEntity();
        Player player = Bukkit.getPlayer(spellCastInformation.getCaster());
        return hitEntity != null
                && !(hitEvent.getHitEntity() instanceof Projectile)
                && hitEntity.getUniqueId() != spellCastInformation.getCaster()
                && player != null;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        LightningStrike lightningStrike = event.getLightning();
        if (CrystamaeHistoria.getEffectMap().containsKey(lightningStrike)) {

            SpellCastInformation spellCastInformation = CrystamaeHistoria.getEffectMap().get(lightningStrike).getFirstValue();

            if (spellCastInformation != null) {
                Location location = event.getLightning().getLocation();
                spellCastInformation.setDamageLocation(location);

                // TODO Combine?
                spellCastInformation.runPreAffectEvent();

                spellCastInformation.runAffectEvent();

                spellCastInformation.runPostAffectEvent();

                event.setCancelled(true);
                CrystamaeHistoria.getEffectMap().remove(lightningStrike);

            }
        }
    }
}
