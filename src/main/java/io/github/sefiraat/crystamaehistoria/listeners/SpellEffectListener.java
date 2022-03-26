package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicFallingBlock;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicProjectile;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentUUIDDataType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
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

        // We don't want magic projectiles to hit their own passengers.
        List<Entity> passengers = magicProjectile.getProjectile().getPassengers();
        if (event.getHitEntity() != null && !passengers.isEmpty()) {
            for (Entity entity : passengers) {
                if (entity.getUniqueId() == event.getHitEntity().getUniqueId()) {
                    return;
                }
            }
        }

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

        magicProjectile.kill();
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFallingBlockLands(EntityChangeBlockEvent event) {
        final Entity entity = event.getEntity();

        if (entity instanceof FallingBlock) {
            final Optional<MagicFallingBlock> optionalMagicFallingBlock = CrystamaeHistoria.getFallingBlockMap().keySet()
                .stream()
                .filter(magicFallingBlock -> magicFallingBlock.matches((FallingBlock) entity))
                .findFirst();

            if (!optionalMagicFallingBlock.isPresent()) {
                return;
            }

            final MagicFallingBlock magicFallingBlock = optionalMagicFallingBlock.get();
            final CastInformation castInfo = CrystamaeHistoria.getFallingBlockCastInfo(magicFallingBlock);

            event.setCancelled(true);
            castInfo.setProjectileLocation(magicFallingBlock.getLocation());
            castInfo.setHitBlock(event.getBlock());
            castInfo.runProjectileHitBlockEvent();
            magicFallingBlock.kill();
        }
    }

    private boolean entityHitAllowed(CastInformation castInformation, Entity hitEntity) {
        final Player player = Bukkit.getPlayer(castInformation.getCaster());
        final Interaction interaction = hitEntity instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
        if (player != null && GeneralUtils.hasPermission(player, hitEntity.getLocation(), interaction)) {
            return hitEntity instanceof LivingEntity
                && hitEntity.getUniqueId() != castInformation.getCaster();
        }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLightningStrikeHit(LightningStrikeEvent event) {
        final LightningStrike lightningStrike = event.getLightning();
        final UUID uuid = lightningStrike.getUniqueId();
        if (CrystamaeHistoria.getStrikeMap().containsKey(uuid)) {
            CastInformation castInformation = CrystamaeHistoria.getStrikeCastInfo(uuid);

            final Location location = event.getLightning().getLocation();
            castInformation.setDamageLocation(location);

            castInformation.runPreAffectEvent();
            castInformation.runAffectEvent();
            castInformation.runPostAffectEvent();

            event.setCancelled(true);
            CrystamaeHistoria.getStrikeMap().remove(uuid);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInvulnerablePlayerDamaged(EntityDamageEvent event) {
        NamespacedKey key = Keys.PDC_IS_INVULNERABLE;
        EntityDamageEvent.DamageCause cause = event.getCause();
        if (event.getEntity() instanceof LivingEntity
            && PersistentDataAPI.hasLong(event.getEntity(), key)
        ) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
            long expiry = PersistentDataAPI.getLong(livingEntity, key);
            if (expiry >= System.currentTimeMillis()) {
                if (cause != EntityDamageEvent.DamageCause.CUSTOM
                    && cause != EntityDamageEvent.DamageCause.SUICIDE
                ) {
                    event.setCancelled(true);
                }
            } else {
                PersistentDataAPI.remove(livingEntity, key);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onWitherWeatherDeath(EntityDeathEvent event) {
        NamespacedKey key = Keys.PDC_IS_WEATHER_WITHER;
        if (event.getEntity() instanceof WitherSkeleton
            && PersistentDataAPI.getBoolean(event.getEntity(), key)
        ) {
            List<ItemStack> itemStackList = event.getDrops();
            for (ItemStack itemStack : itemStackList) {
                if (itemStack.getType() == Material.WITHER_SKELETON_SKULL) {
                    return;
                }
            }
            itemStackList.add(new ItemStack(Material.WITHER_SKELETON_SKULL));
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMagicSummonDeath(EntityDeathEvent event) {
        NamespacedKey key = Keys.PDC_IS_SPAWN_OWNER;
        if (DataTypeMethods.hasCustom(event.getEntity(), key, PersistentUUIDDataType.TYPE)) {
            event.setCancelled(true);
            event.getEntity().remove();
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onRideRavager(EntityChangeBlockEvent event) {
        NamespacedKey key = Keys.PDC_IS_SPAWN_OWNER;
        if (DataTypeMethods.hasCustom(event.getEntity(), key, PersistentUUIDDataType.TYPE)) {
            event.setCancelled(true);
        }
    }

//    // TODO Will not work until you can teleport entities with passengers
//    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
//    public void onRideRavager(PlayerInteractEntityEvent event) {
//        NamespacedKey key = Keys.PDC_IS_SPAWN_OWNER;
//        UUID uuid = DataTypeMethods.getCustom(event.getRightClicked(), key, PersistentUUIDDataType.LIQUEFACTION_CRAFTING);
//        if (uuid != null && uuid.equals(event.getPlayer().getUniqueId()) && event.getRightClicked().getType() == EntityType.RAVAGER) {
//            event.getRightClicked().addPassenger(event.getPlayer());
//        }
//    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        CrystamaeHistoria.getSpellMemory().removeFlight(event.getPlayer());
    }


}
