package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractPositiveSpell;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractSpell;
import lombok.NonNull;
import org.bukkit.entity.Player;

public class Heal extends AbstractPositiveSpell {

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);
        healEntity(spellCastInformation.getCaster(), getHealAmount(spellCastInformation));
    }

    @Override
    protected int getBaseHealing() {
        return 2;
    }

    @Override
    protected double getRange() {
        return 0;
    }

    @Override
    protected int getBaseCooldown() {
        return 10;
    }

}
