package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractSpell;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Bright extends AbstractSpell {

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);

        Player caster = spellCastInformation.getCaster();
        caster.getWorld().setThundering(false);
        caster.getWorld().setStorm(false);

    }

    @Override
    protected int getBaseCooldown() {
        return 2000;
    }

}
