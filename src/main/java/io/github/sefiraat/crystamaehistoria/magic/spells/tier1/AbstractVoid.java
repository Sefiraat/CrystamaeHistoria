package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.GeneralUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AbstractVoid extends Spell {

    public AbstractVoid() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(25, false, 7, true, 10, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    private void cast(CastInformation castInformation) {
        final Location castLocation = castInformation.getCastLocation();
        final double range = getRange(castInformation);
        final List<Location> locationList = new ArrayList<>();
        final List<Entity> entities = new ArrayList<>();

        for (Entity entity : castLocation.getWorld().getNearbyEntities(castLocation, range, range, range)) {
            if (entity instanceof LivingEntity
                && GeneralUtils.hasPermission(castInformation.getCaster(), entity.getLocation(), Interaction.INTERACT_ENTITY)
            ) {
                locationList.add(entity.getLocation());
                entities.add(entity);
            }
        }

        for (Entity entity : entities) {
            if (!locationList.isEmpty()) {
                final Location location = locationList.get(ThreadLocalRandom.current().nextInt(locationList.size()));
                entity.teleport(location);
                locationList.remove(location);
            }
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.VOID,
            StoryType.PHILOSOPHICAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Swaps everything around!"
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "ABSTRACT_VOID";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.GREEN_DYE;
    }
}
