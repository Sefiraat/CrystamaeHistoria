package io.github.sefiraat.crystamaehistoria.utils;

import org.bukkit.Material;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BlocksConfigGenerator {

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("src/main/resources/new_blocks.yml")))) {
            writeLine(writer, "blocks:");

            for (Material material : Arrays.stream(Material.values()).sorted(Comparator.comparing(Material::toString)).collect(Collectors.toList())) {
                if (!material.isItem() || material.isLegacy()) continue;
                writeLine(writer, "  - material: " + material.name());
                writeLine(writer, "    tier: 1");
                writeLine(writer, "    elements:");
                writeLine(writer, "      - ");
                writeLine(writer, "    story:");
                writeLine(writer, "      name: ");
                writeLine(writer, "      type: ");
                writeLine(writer, "      lore: ");
                writeLine(writer, "        - ");
                writeLine(writer, "      shards: ");
                writeLine(writer, "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }
}
