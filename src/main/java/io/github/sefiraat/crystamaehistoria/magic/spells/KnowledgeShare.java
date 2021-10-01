package io.github.sefiraat.crystamaehistoria.magic.spells;

import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class KnowledgeShare extends Spell {

    private static final float BASE_EXP = 50;

    public KnowledgeShare() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(360, true, 15, false, 10, true)
                .makeTickingSpell(this::onTick, 5, false, 5, false);
        setSpellCore(spellCoreBuilder.build());
    }

    public void onTick(@NonNull SpellCastInformation spellCastInformation) {
        // TODO SHIT SHIT SHIT
        Player caster = Bukkit.getPlayer(spellCastInformation.getCaster());

        float exp = Math.max(caster.getExp(), BASE_EXP * spellCastInformation.getPowerMulti());
        exp = exp - (exp % 10);
        int waves = (int) (exp / 10);

        for (int i = 0; i < waves; i++) {
            double xOffset = ThreadLocalRandom.current().nextDouble();
            double zOffset = ThreadLocalRandom.current().nextDouble();
            Location location = caster.getLocation().clone().add(xOffset, 20, zOffset);
            ExperienceOrb experienceOrb = (ExperienceOrb) caster.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
            experienceOrb.setExperience(10);
            location.getWorld().playEffect(location, Effect.DRIPPING_DRIPSTONE, 1);

        }

    }
}
