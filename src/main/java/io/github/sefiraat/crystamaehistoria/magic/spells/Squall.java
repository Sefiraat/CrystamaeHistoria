package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractSpell;
import lombok.NonNull;
import org.bukkit.entity.Player;

public class Squall extends AbstractSpell {

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);

        Player caster = spellCastInformation.getCaster();
        caster.getWorld().setThundering(true);
        caster.getWorld().setStorm(true);

    }

    @Override
    protected int getBaseCooldown() {
        return 2000;
    }

}
