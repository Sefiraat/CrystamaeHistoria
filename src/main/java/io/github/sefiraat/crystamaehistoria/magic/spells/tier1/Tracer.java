package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Boss;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.WaterMob;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tracer extends Spell {

    protected static final Map<String, Color> COLOR_MAP = new LinkedHashMap<>();

    static {
        COLOR_MAP.put("player", Color.fromRGB(255, 255, 255));
        COLOR_MAP.put("npc", Color.fromRGB(240, 180, 40));
        COLOR_MAP.put("boss", Color.fromRGB(255, 25, 30));
        COLOR_MAP.put("monster", Color.fromRGB(190, 25, 30));
        COLOR_MAP.put("water", Color.fromRGB(45, 45, 220));
        COLOR_MAP.put("animal", Color.fromRGB(50, 230, 30));
        COLOR_MAP.put("ambient", Color.fromRGB(150, 150, 150));
    }

    public Tracer() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(10, false, 40, true, 10, true)
            .makeTickingSpell(this::onTick, 10, true, 20, false);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void onTick(CastInformation castInformation) {
        final Player player = castInformation.getCasterAsPlayer();
        final Location location = player.getLocation().add(0, 1.5, 0);
        final Collection<LivingEntity> entityList = location.getNearbyLivingEntities(getRange(castInformation));
        for (LivingEntity livingEntity : entityList) {
            if (livingEntity.getUniqueId() == castInformation.getCaster()) {
                continue;
            }

            Particle.DustOptions dustOptions = null;
            if (livingEntity instanceof Player) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("player"), 1);
            } else if (livingEntity instanceof NPC) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("npc"), 1);
            } else if (livingEntity instanceof Boss) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("boss"), 1);
            } else if (livingEntity instanceof Monster) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("monster"), 1);
            } else if (livingEntity instanceof WaterMob) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("water"), 1);
            } else if (livingEntity instanceof Animals) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("animal"), 1);
            } else if (livingEntity instanceof Ambient) {
                dustOptions = new Particle.DustOptions(COLOR_MAP.get("ambient"), 1);
            } else {
                continue;
            }

            Validate.notNull(
                dustOptions,
                "Dust Options is null, something be bad: " + livingEntity.getType().name()
            );

            ParticleUtils.drawLine(
                dustOptions,
                location,
                livingEntity.getLocation(),
                1
            );
        }
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.MECHANICAL,
            StoryType.HISTORICAL,
            StoryType.ANIMAL
        );
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Shows you all nearby living things",
            "color coded by type."
        };
    }

    @Nonnull
    @Override
    public String getId() {
        return "TRACER";
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.LEAD;
    }
}
