package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.BatteringRamGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

public class BatteringRam extends Spell {

    public BatteringRam() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 3, true, 50, true)
            .makeInstantSpell(this::cast)
            .makeEffectingSpell(true, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        final UUID caster = castInformation.getCaster();
        final Location location = castInformation.getCastLocation();
        final Vector direction = location.getDirection().clone();
        final int range = (int) getRange(castInformation);
        direction.setY(0);
        final Location spawnLocation = location.clone().add(location.getDirection().clone().add(new Vector(0, 1, 0)));
        final MagicSummon magicSummon = SpellUtils.summonTemporaryMob(
            EntityType.GOAT,
            caster,
            spawnLocation,
            new BatteringRamGoal(caster),
            range
        );
        Mob mob = magicSummon.getMob();
        mob.setGravity(false);
        mob.setVelocity(location.getDirection().multiply(2));
        mob.setInvulnerable(true);
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.ELEMENTAL,
            StoryType.ALCHEMICAL,
            StoryType.ANIMAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a battering ram to decimate",
            "all in your way."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "BATTERING_RAM";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.GOAT_SPAWN_EGG;
    }

}
