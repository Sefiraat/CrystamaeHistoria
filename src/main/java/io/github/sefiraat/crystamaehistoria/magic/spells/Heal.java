package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Heal extends Spell {

    public Heal() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, true, 0, false, 1, true)
                .makeInstantSpell(this::cast)
                .makeHealingSpell(2, true);
        setSpellCore(spellCoreBuilder.build());
    }

    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        Player caster = spellCastInformation.getCaster();
        healEntity(caster, getHealAmount(spellCastInformation));
        displayParticleEffect(caster, Particle.HEART, 2, 10);
    }

}
