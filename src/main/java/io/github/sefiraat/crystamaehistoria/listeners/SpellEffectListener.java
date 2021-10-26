package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.MagicProjectile;
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

import java.util.Optional;
import java.util.UUID;

public class SpellEffectListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent event) {
        final Projectile projectile = event.getEntity();
        final Optional<MagicProjectile> optionalMagicProjectile = CrystamaeHistoria.getProjectileMap().keySet()
            .stream()
            .filter(magicProjectile1 -> magicProjectile1.matches(projectile))
            .findFirst();

        if (!optionalMagicProjectile.isPresent()) {
            return;
        }

        final MagicProjectile magicProjectile = optionalMagicProjectile.get();
        final CastInformation castInfo = CrystamaeHistoria.getProjectileCastInfo(magicProjectile);
        final Entity hitEntity = event.getHitEntity();

        event.setCancelled(true);
        castInfo.setProjectileLocation(magicProjectile.getLocation());

        if (entityHitAllowed(castInfo, hitEntity)) {
            castInfo.setMainTarget((LivingEntity) hitEntity);
            castInfo.setDamageLocation(hitEntity.getLocation());
            castInfo.runPreAffectEvent();
            castInfo.runAffectEvent();
            castInfo.runPostAffectEvent();
        }

        if (event.getHitBlock() != null) {
            castInfo.setHitBlock(event.getHitBlock());
            castInfo.setDamageLocation(event.getHitBlock().getLocation());
            castInfo.runProjectileHitBlockEvent();
        }

        CrystamaeHistoria.getActiveStorage().removeProjectile(magicProjectile);
        magicProjectile.kill();
    }

    private boolean entityHitAllowed(CastInformation castInformation, Entity hitEntity) {
        final Player player = Bukkit.getPlayer(castInformation.getCaster());
        return hitEntity instanceof LivingEntity
            && hitEntity.getUniqueId() != castInformation.getCaster()
            && player != null;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        final LightningStrike lightningStrike = event.getLightning();
        final UUID uuid = lightningStrike.getUniqueId();
        if (CrystamaeHistoria.getStrikeMap().containsKey(uuid)) {
            CastInformation castInformation = CrystamaeHistoria.getStrikeCastInfo(uuid);

            final Location location = event.getLightning().getLocation();
            castInformation.setDamageLocation(location);

            // TODO Combine?
            castInformation.runPreAffectEvent();
            castInformation.runAffectEvent();
            castInformation.runPostAffectEvent();

            event.setCancelled(true);
            CrystamaeHistoria.getStrikeMap().remove(uuid);
        }
    }
}
