package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LovePotion extends Spell {

    public LovePotion() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(20, true, 10, true, 5, true)
                .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    public void cast(@NonNull SpellCastInformation spellCastInformation) {
        Player caster = spellCastInformation.getCaster();
        Location casterLocation = caster.getLocation();
        double range = getRange(spellCastInformation);
        for (Entity entity : caster.getWorld().getNearbyEntities(casterLocation, range, range, range, entity -> entity instanceof Breedable)) {
            Animals animals = (Animals) entity;
            if (animals.isAdult() && animals.canBreed()) {
                animals.setLoveModeTicks(120);
                displayParticleEffect(entity, Particle.HEART, 1, 5);
            }
        }
    }
}
