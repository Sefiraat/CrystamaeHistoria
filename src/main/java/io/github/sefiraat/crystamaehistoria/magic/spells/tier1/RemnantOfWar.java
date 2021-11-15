package io.github.sefiraat.crystamaehistoria.magic.spells.tier1;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.Spell;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.SpellCoreBuilder;
import io.github.sefiraat.crystamaehistoria.magic.spells.spellobjects.MagicSummon;
import io.github.sefiraat.crystamaehistoria.slimefun.mechanisms.liquefactionbasin.RecipeSpell;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.sefiraat.crystamaehistoria.utils.SpellUtils;
import io.github.sefiraat.crystamaehistoria.utils.mobgoals.BoringGoal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RemnantOfWar extends Spell {

    public RemnantOfWar() {
        SpellCoreBuilder spellCoreBuilder = new SpellCoreBuilder(5, true, 0, false, 50, true)
            .makeInstantSpell(this::cast);
        setSpellCore(spellCoreBuilder.build());
    }

    @ParametersAreNonnullByDefault
    public void cast(CastInformation castInformation) {
        UUID caster = castInformation.getCaster();
        Location location = castInformation.getCastLocation();
        Location spawnLocation = location.clone().add(
            ThreadLocalRandom.current().nextDouble(-3, 3),
            0,
            ThreadLocalRandom.current().nextDouble(-3, 3)
        );
        MagicSummon magicSummon = SpellUtils.summonTemporaryMob(
            EntityType.ZOMBIE,
            caster,
            spawnLocation,
            new BoringGoal(caster),
            180,
            this::onTick
        );
        Zombie zombie = (Zombie) magicSummon.getMob();
        gearZombie(zombie, castInformation.getStaveLevel());
    }

    public void gearZombie(Zombie zombie, int tier) {
        ItemStack helmet;
        ItemStack chestplate;
        ItemStack leggings;
        ItemStack boots;
        ItemStack sword;
        switch (tier) {
            case 1:
                helmet = new ItemStack(Material.LEATHER_HELMET);
                chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                boots = new ItemStack(Material.LEATHER_BOOTS);
                sword = new ItemStack(Material.WOODEN_SWORD);
                break;
            case 2:
                helmet = new ItemStack(Material.CHAINMAIL_HELMET);
                chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                boots = new ItemStack(Material.CHAINMAIL_BOOTS);
                sword = new ItemStack(Material.STONE_SWORD);
                break;
            case 3:
                helmet = new ItemStack(Material.IRON_HELMET);
                chestplate = new ItemStack(Material.IRON_CHESTPLATE);
                leggings = new ItemStack(Material.IRON_LEGGINGS);
                boots = new ItemStack(Material.IRON_BOOTS);
                sword = new ItemStack(Material.IRON_SWORD);
                break;
            case 4:
                helmet = new ItemStack(Material.DIAMOND_HELMET);
                chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                boots = new ItemStack(Material.DIAMOND_BOOTS);
                sword = new ItemStack(Material.DIAMOND_SWORD);
                break;
            case 5:
                helmet = new ItemStack(Material.NETHERITE_HELMET);
                chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
                leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
                boots = new ItemStack(Material.NETHERITE_BOOTS);
                sword = new ItemStack(Material.NETHERITE_SWORD);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tier);
        }
        EntityEquipment entityEquipment = zombie.getEquipment();
        entityEquipment.setHelmet(helmet);
        entityEquipment.setChestplate(chestplate);
        entityEquipment.setLeggings(leggings);
        entityEquipment.setBoots(boots);
        entityEquipment.setItemInMainHand(sword);
    }

    public void onTick(MagicSummon magicSummon) {
        ParticleUtils.displayParticleEffect(magicSummon.getMob(), Particle.SOUL, 1, 1);
    }

    @Nonnull
    @Override
    public String getId() {
        return "REMNANT_OF_WAR";
    }

    @Nonnull
    @Override
    public String[] getLore() {
        return new String[]{
            "Summons a remnant of war from the dead",
            "to your side. Their gear scales with tier."
        };
    }

    @Nonnull
    @Override
    public Material getMaterial() {
        return Material.ZOMBIE_SPAWN_EGG;
    }

    @Nonnull
    @Override
    public RecipeSpell getRecipe() {
        return new RecipeSpell(
            1,
            StoryType.HISTORICAL,
            StoryType.HUMAN,
            StoryType.VOID
        );
    }
}
