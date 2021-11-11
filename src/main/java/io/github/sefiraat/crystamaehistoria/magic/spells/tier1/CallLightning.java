package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class CallLightning extends Spell {

    public CallLightning() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(50, true, 100, false, 5, true)
            .makeDamagingSpell(2, true, 0, false)
            .makeProjectileSpell(this::fireProjectiles, 2, false, 0.5, true)
            .makeProjectileVsEntitySpell(this::projectileHit)
            .addBeforeProjectileHitEntityEvent(this::beforeProjectileHit);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void fireProjectiles(CastInformation castInformation) {
        Player player = Bukkit.getPlayer(castInformation.getCaster());
        if (player != null) {
            Block block = player.getTargetBlockExact((int) getRange(castInformation));
            if (block != null) {
                Location location = block.getLocation();
                LightningStrike lightningStrike = location.getWorld().strikeLightning(location);
                registerLightningStrike(lightningStrike, castInformation);
            }
        }
    }

    @ParametersAreNonnullByDefault
    public void beforeProjectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            livingEntity.setFireTicks(40);
        }
    }

    @ParametersAreNonnullByDefault
    public void projectileHit(CastInformation castInformation) {
        for (LivingEntity livingEntity : getTargets(castInformation, getProjectileAoe(castInformation), true)) {
            GeneralUtils.damageEntity(livingEntity, castInformation.getCaster(), getDamage(castInformation), castInformation.getDamageLocation(), getKnockback(castInformation));
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "CALL_LIGHTNING";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Calls a lightning bolt down where you are looking"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.LIGHTNING_ROD;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.MECHANICAL,
            StoryType.HISTORICAL
        );
    }
}
