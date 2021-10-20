package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Shroud extends Spell {

    private static final int BLIND_DURATION = 15;
    private static final int WITHER_DURATION = 5;

    public Shroud() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(40, true, 10, true, 5, true)
            .makeDamagingSpell(0, false, 0, false)
            .makeInstantSpell(this::cast);
        spellCoreBuilder.addNegativeEffect(PotionEffectType.BLINDNESS, 0, BLIND_DURATION);
        spellCoreBuilder.addNegativeEffect(PotionEffectType.WITHER, 0, WITHER_DURATION);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        double range = getRange(castInformation);
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
            if (entity instanceof LivingEntity && entity.getUniqueId() != castInformation.getCaster()) {
                LivingEntity livingEntity = (LivingEntity) entity;
                applyNegativeEffects(livingEntity);
                displayParticleEffect(livingEntity, Particle.SLIME, 2, 2);
            }
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "SHROUD";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a dark miasma around the player",
            "causing light damage and blinding those",
            "affected."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.BLACK_CANDLE;
    }
}
