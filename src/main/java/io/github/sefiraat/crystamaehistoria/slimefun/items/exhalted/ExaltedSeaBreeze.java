package io.github.sefiraat.crystamaehistoria.slimefun.items.exhalted;

import io.github.sefiraat.crystamaehistoria.utils.ParticleUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ExaltedSeaBreeze extends ExaltedItem {

    private static final Map<Material, Material> MATERIAL_MAP = new EnumMap<>(Material.class);

    public ExaltedSeaBreeze(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        MATERIAL_MAP.put(Material.COPPER_BLOCK, Material.EXPOSED_COPPER);
        MATERIAL_MAP.put(Material.EXPOSED_COPPER, Material.WEATHERED_COPPER);
        MATERIAL_MAP.put(Material.WEATHERED_COPPER, Material.OXIDIZED_COPPER);

        MATERIAL_MAP.put(Material.CUT_COPPER, Material.EXPOSED_CUT_COPPER);
        MATERIAL_MAP.put(Material.EXPOSED_CUT_COPPER, Material.WEATHERED_CUT_COPPER);
        MATERIAL_MAP.put(Material.WEATHERED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER);

        MATERIAL_MAP.put(Material.CUT_COPPER_STAIRS, Material.EXPOSED_CUT_COPPER_STAIRS);
        MATERIAL_MAP.put(Material.EXPOSED_CUT_COPPER_STAIRS, Material.WEATHERED_CUT_COPPER_STAIRS);
        MATERIAL_MAP.put(Material.WEATHERED_CUT_COPPER_STAIRS, Material.OXIDIZED_CUT_COPPER_STAIRS);

        MATERIAL_MAP.put(Material.CUT_COPPER_SLAB, Material.EXPOSED_CUT_COPPER_SLAB);
        MATERIAL_MAP.put(Material.EXPOSED_CUT_COPPER_SLAB, Material.WEATHERED_CUT_COPPER_SLAB);
        MATERIAL_MAP.put(Material.WEATHERED_CUT_COPPER_SLAB, Material.OXIDIZED_CUT_COPPER_SLAB);
    }

    @Override
    public void onExalt(ExaltedItem slimefunItem, Location location) {
        for (int i = 0; i < 4; i++) {
            final int x = ThreadLocalRandom.current().nextInt(-5, 6);
            final int z = ThreadLocalRandom.current().nextInt(-5, 6);
            Block block = location.add(x, -1.5, z).getBlock();
            Material material = MATERIAL_MAP.get(block.getType());
            if (material != null) {
                block.setType(material);
                ParticleUtils.displayParticleEffect(block.getLocation().clone().add(0.5, 0.5, 0.5), Particle.WAX_OFF, 1.5, 5);
            }
        }
    }
}
