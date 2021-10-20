package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class Bright extends Spell {

    public Bright() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(2000, true, 0, false, 10, false)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        Player caster = Bukkit.getPlayer(castInformation.getCaster());
        if (caster != null) {
            caster.getWorld().setThundering(false);
            caster.getWorld().setStorm(false);
            displayParticleEffect(caster, Particle.FALLING_NECTAR, 2, 30);
            caster.getWorld().playEffect(caster.getLocation(), Effect.BONE_MEAL_USE, 1);
        }
    }

    @Nonnull
    @Override
    public String getId() {
        return "BRIGHT";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Turns any day into a pleasant sunny one!"
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.SUNFLOWER;
    }
}
