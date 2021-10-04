package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import org.bukkit.Effect;
import org.bukkit.Location;

import javax.annotation.ParametersAreNonnullByDefault;

public class EtherealFlow extends Spell {

    public EtherealFlow() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(720, true, 0, false, 10, false)
                .makeTickingSpell(this::onTick, 30, true, 1, false);
        setSpellCore(spellCoreBuilder.build());

    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        Location location = castInformation.getCastLocation();
        location.getWorld().setTime(location.getWorld().getTime() + (50L * castInformation.getStaveLevel()));
        location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 1);
    }

}
