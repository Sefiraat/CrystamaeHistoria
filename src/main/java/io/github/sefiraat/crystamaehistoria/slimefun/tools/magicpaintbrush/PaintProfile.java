package io.github.sefiraat.crystamaehistoria.slimefun.tools.magicpaintbrush;

import lombok.Getter;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;

import java.awt.Color;

public enum PaintProfile {

    BLACK(
        Material.BLACK_WOOL,
        Material.BLACK_TERRACOTTA,
        Material.BLACK_GLAZED_TERRACOTTA,
        Material.BLACK_CONCRETE_POWDER,
        Material.BLACK_CONCRETE,
        Material.BLACK_CARPET,
        DyeColor.BLACK
    ),
    BLUE(
        Material.BLUE_WOOL,
        Material.BLUE_TERRACOTTA,
        Material.BLUE_GLAZED_TERRACOTTA,
        Material.BLUE_CONCRETE_POWDER,
        Material.BLUE_CONCRETE,
        Material.BLUE_CARPET,
        DyeColor.BLUE
    ),
    BROWN(
        Material.BROWN_WOOL,
        Material.BROWN_TERRACOTTA,
        Material.BROWN_GLAZED_TERRACOTTA,
        Material.BROWN_CONCRETE_POWDER,
        Material.BROWN_CONCRETE,
        Material.BROWN_CARPET,
        DyeColor.BROWN
    ),
    CYAN(
        Material.CYAN_WOOL,
        Material.CYAN_TERRACOTTA,
        Material.CYAN_GLAZED_TERRACOTTA,
        Material.CYAN_CONCRETE_POWDER,
        Material.CYAN_CONCRETE,
        Material.CYAN_CARPET,
        DyeColor.CYAN
    ),
    GRAY(
        Material.GRAY_WOOL,
        Material.GRAY_TERRACOTTA,
        Material.GRAY_GLAZED_TERRACOTTA,
        Material.GRAY_CONCRETE_POWDER,
        Material.GRAY_CONCRETE,
        Material.GRAY_CARPET,
        DyeColor.GRAY
    ),
    GREEN(
        Material.GREEN_WOOL,
        Material.GREEN_TERRACOTTA,
        Material.GREEN_GLAZED_TERRACOTTA,
        Material.GREEN_CONCRETE_POWDER,
        Material.GREEN_CONCRETE,
        Material.GREEN_CARPET,
        DyeColor.GREEN
    ),
    LIGHT_BLUE(
        Material.LIGHT_BLUE_WOOL,
        Material.LIGHT_BLUE_TERRACOTTA,
        Material.LIGHT_BLUE_GLAZED_TERRACOTTA,
        Material.LIGHT_BLUE_CONCRETE_POWDER,
        Material.LIGHT_BLUE_CONCRETE,
        Material.LIGHT_BLUE_CARPET,
        DyeColor.LIGHT_BLUE
    ),
    LIGHT_GRAY(
        Material.LIGHT_GRAY_WOOL,
        Material.LIGHT_GRAY_TERRACOTTA,
        Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
        Material.LIGHT_GRAY_CONCRETE_POWDER,
        Material.LIGHT_GRAY_CONCRETE,
        Material.LIGHT_GRAY_CARPET,
        DyeColor.LIGHT_GRAY
    ),
    LIME(
        Material.LIME_WOOL,
        Material.LIME_TERRACOTTA,
        Material.LIME_GLAZED_TERRACOTTA,
        Material.LIME_CONCRETE_POWDER,
        Material.LIME_CONCRETE,
        Material.LIME_CARPET,
        DyeColor.LIME
    ),
    MAGENTA(

        Material.MAGENTA_WOOL,
        Material.MAGENTA_TERRACOTTA,
        Material.MAGENTA_GLAZED_TERRACOTTA,
        Material.MAGENTA_CONCRETE_POWDER,
        Material.MAGENTA_CONCRETE,
        Material.MAGENTA_CARPET,
        DyeColor.MAGENTA
    ),
    ORANGE(
        Material.ORANGE_WOOL,
        Material.ORANGE_TERRACOTTA,
        Material.ORANGE_GLAZED_TERRACOTTA,
        Material.ORANGE_CONCRETE_POWDER,
        Material.ORANGE_CONCRETE,
        Material.ORANGE_CARPET,
        DyeColor.ORANGE
    ),
    PINK(
        Material.PINK_WOOL,
        Material.PINK_TERRACOTTA,
        Material.PINK_GLAZED_TERRACOTTA,
        Material.PINK_CONCRETE_POWDER,
        Material.PINK_CONCRETE,
        Material.PINK_CARPET,
        DyeColor.PINK
    ),
    PURPLE(
        Material.PURPLE_WOOL,
        Material.PURPLE_TERRACOTTA,
        Material.PURPLE_GLAZED_TERRACOTTA,
        Material.PURPLE_CONCRETE_POWDER,
        Material.PURPLE_CONCRETE,
        Material.PURPLE_CARPET,
        DyeColor.PURPLE
    ),
    RED(
        Material.RED_WOOL,
        Material.RED_TERRACOTTA,
        Material.RED_GLAZED_TERRACOTTA,
        Material.RED_CONCRETE_POWDER,
        Material.RED_CONCRETE,
        Material.RED_CARPET,
        DyeColor.RED
    ),
    WHITE(
        Material.WHITE_WOOL,
        Material.WHITE_TERRACOTTA,
        Material.WHITE_GLAZED_TERRACOTTA,
        Material.WHITE_CONCRETE_POWDER,
        Material.WHITE_CONCRETE,
        Material.WHITE_CARPET,
        DyeColor.WHITE
    ),
    YELLOW(
        Material.YELLOW_WOOL,
        Material.YELLOW_TERRACOTTA,
        Material.YELLOW_GLAZED_TERRACOTTA,
        Material.YELLOW_CONCRETE_POWDER,
        Material.YELLOW_CONCRETE,
        Material.YELLOW_CARPET,
        DyeColor.YELLOW
    );

    @Getter
    protected static final PaintProfile[] cachedValues = values();

    @Getter
    private final Material materialWool;
    @Getter
    private final Material materialTerracotta;
    @Getter
    private final Material materialGlazedTerracotta;
    @Getter
    private final Material materialConcretePowder;
    @Getter
    private final Material materialConcrete;
    @Getter
    private final Material materialCarpet;
    @Getter
    private final DyeColor dyeColor;

    PaintProfile(Material materialWool,
                 Material materialTerracotta,
                 Material materialGlazedTerracotta,
                 Material materialConcretePowder,
                 Material materialConcrete,
                 Material materialCarpet,
                 DyeColor dyeColor
    ) {
        this.materialWool = materialWool;
        this.materialTerracotta = materialTerracotta;
        this.materialGlazedTerracotta = materialGlazedTerracotta;
        this.materialConcretePowder = materialConcretePowder;
        this.materialConcrete = materialConcrete;
        this.materialCarpet = materialCarpet;
        this.dyeColor = dyeColor;
    }

    public ChatColor getChatColor() {
        Color color = new Color(
            dyeColor.getColor().getRed(),
            dyeColor.getColor().getGreen(),
            dyeColor.getColor().getBlue()
        );
        return ChatColor.of(color);
    }

    public TextColor getTextColor() {
        return TextColor.color(
            dyeColor.getColor().getRed(),
            dyeColor.getColor().getGreen(),
            dyeColor.getColor().getBlue()
        );
    }
}
