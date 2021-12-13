package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieVillager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

public class CurificationRitual extends Spell {

    public CurificationRitual() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(60, false, 10, true, 100, true)
            .makeInstantSpell(this::cast)
            .makeDamagingSpell(2, true, 0, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final double range = getRange(castInformation);
        final Player caster = castInformation.getCasterAsPlayer();
        final Collection<Entity> zombies = caster.getWorld().getNearbyEntities(
            caster.getLocation(),
            range,
            range,
            range
        );
        for (Entity entity : zombies) {
            if (entity instanceof ZombieVillager) {
                ParticleUtils.displayParticleEffect(entity, Particle.WAX_OFF, 2, 10);
                ZombieVillager zombieVillager = (ZombieVillager) entity;
                zombieVillager.setConversionTime(100);
                zombieVillager.setConversionPlayer(caster);
            } else if (entity instanceof Zombie) {
                Zombie zombie = (Zombie) entity;
                GeneralUtils.damageEntity(zombie, castInformation.getCaster(), getDamage(castInformation));
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.HUMAN,
            StoryType.ANIMAL,
            StoryType.CELESTIAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Cures nearby zombie villagers while",
            "damaging other zombies in the process."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "CURIFICATION_RITUAL";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.GOLDEN_APPLE;
    }
}
