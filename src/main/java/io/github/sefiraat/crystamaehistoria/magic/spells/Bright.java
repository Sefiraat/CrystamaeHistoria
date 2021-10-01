package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Bright extends Spell {

    public Bright() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(2000, true, 0, false, 10, false)
                .makeInstantSpell(this::castSpell);
        setSpellCore(spellCoreBuilder.build());
    }

    public void castSpell(@NonNull SpellCastInformation spellCastInformation) {
        Player caster = spellCastInformation.getCaster();
        caster.getWorld().setThundering(false);
        caster.getWorld().setStorm(false);
        displayParticleEffect(caster, Particle.FALLING_NECTAR, 2, 30);
        caster.getWorld().playEffect(caster.getLocation(), Effect.BONE_MEAL_USE, 1);

    }

}