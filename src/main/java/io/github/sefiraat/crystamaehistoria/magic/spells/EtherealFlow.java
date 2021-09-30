package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractPositiveTickingSpell;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractSpell;
import lombok.NonNull;
import org.bukkit.entity.Player;

public class EtherealFlow extends AbstractPositiveTickingSpell {

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
    }

    @Override
    protected int getNumberTicks() {
        return 30;
    }

    @Override
    protected int getTickInterval() {
        return 5;
    }

    @Override
    protected int getBaseHealing() {
        return 0;
    }

    @Override
    protected double getRange() {
        return 0;
    }

    @Override
    protected int getBaseCooldown() {
        return 2000;
    }

    @Override
    public void onTick(@NonNull SpellCastInformation spellCastInformation) {
        Player caster = spellCastInformation.getCaster();
        caster.getWorld().setTime(caster.getWorld().getTime() + (100L * spellCastInformation.getPowerMulti()));
    }

}
