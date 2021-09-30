package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.superclasses.AbstractSpell;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class LovePotion extends AbstractSpell {

    private static final int RANGE = 10;

    @Override
    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        super.cast(spellCastInformation);

        Player caster = spellCastInformation.getCaster();
        Location casterLocation = caster.getLocation();

        for (Entity entity : caster.getWorld().getNearbyEntities(casterLocation, RANGE, RANGE, RANGE, entity -> entity instanceof Breedable)) {
            if (((Ageable) entity).isAdult()) {
                ((Breedable) entity).setBreed(true);
            }
        }

    }

    @Override
    protected int getBaseCooldown() {
        return 20;
    }

}
