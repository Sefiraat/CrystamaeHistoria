package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class EtherealFlow extends Spell {

    public EtherealFlow() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(720, true, 0, false, 10, false)
                .makeTickingSpell(this::onTick, 30, true, 2, false);
        setSpellCore(spellCoreBuilder.build());

    }

    public void onTick(@NonNull SpellCastInformation spellCastInformation) {
        Player caster = spellCastInformation.getCaster();
        caster.getWorld().setTime(caster.getWorld().getTime() + (50L * spellCastInformation.getPowerMulti()));
        caster.getWorld().playEffect(caster.getLocation(), Effect.ENDER_SIGNAL, 1);
    }

}
