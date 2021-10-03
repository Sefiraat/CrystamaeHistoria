package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
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

import java.util.UUID;

public class SpellEffectListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent event) {
        UUID projectile = event.getEntity().getUniqueId();
        if (CrystamaeHistoria.getProjectileMap().containsKey(projectile)) {
            event.setCancelled(true);
            CastInformation castInfo = CrystamaeHistoria.getSpellCastInfo(projectile);
            Entity hitEntity = event.getHitEntity();
            if (eventAllowed(castInfo, hitEntity)) {
                Location location = hitEntity.getLocation();

                castInfo.setMainTarget((LivingEntity) hitEntity);
                castInfo.setDamageLocation(location);

                // TODO Combine?
                castInfo.runPreAffectEvent();
                castInfo.runAffectEvent();
                castInfo.runPostAffectEvent();
            }
            event.getEntity().remove();
            CrystamaeHistoria.getProjectileMap().remove(projectile);
        }
    }

    private boolean eventAllowed(CastInformation castInformation, Entity hitEntity) {
        Player player = Bukkit.getPlayer(castInformation.getCaster());
        return hitEntity != null
                && !(hitEntity instanceof Projectile)
                && hitEntity.getUniqueId() != castInformation.getCaster()
                && player != null;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        LightningStrike lightningStrike = event.getLightning();
        if (CrystamaeHistoria.getProjectileMap().containsKey(lightningStrike)) {
            CastInformation castInformation = CrystamaeHistoria.getProjectileMap().get(lightningStrike).getFirstValue();

            if (castInformation != null) {
                Location location = event.getLightning().getLocation();
                castInformation.setDamageLocation(location);

                // TODO Combine?
                castInformation.runPreAffectEvent();
                castInformation.runAffectEvent();
                castInformation.runPostAffectEvent();

                event.setCancelled(true);
                CrystamaeHistoria.getProjectileMap().remove(lightningStrike);

            }
        }
    }
}
