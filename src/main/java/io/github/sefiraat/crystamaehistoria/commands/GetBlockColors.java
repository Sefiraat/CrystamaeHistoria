//package io.github.sefiraat.crystamaehistoria.commands;
//
//import io.github.mooy1.infinitylib.commands.SubCommand;
//import org.bukkit.Color;
//import org.bukkit.Material;
//import org.bukkit.block.Block;
//import org.bukkit.command.CommandSender;
// import org.bukkit.craftbukkit.v1_17_R1.block.CraftBlock;
//import org.bukkit.entity.Player;
//
//import javax.annotation.ParametersAreNonnullByDefault;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//public class GetBlockColors extends SubCommand {
//
//    public GetBlockColors() {
//        super("block_color", "Generates block colors", true);
//    }
//
//    @Override
//    @ParametersAreNonnullByDefault
//    protected void execute(CommandSender sender, String[] args) {
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//            Block block = player.getLocation().getBlock();
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("block_colors.yml")))) {
//                for (Material material : Material.values()) {
//                    if (material.isBlock()) {
//                        block.setType(material);
//                        Color color = Color.fromRGB(((CraftBlock) block).getNMS().getBlock().s().al);
//                        writeLine(writer,
//                            material.name() + ": [" +
//                            + color.getRed() + ", "
//                            + color.getGreen() + ", "
//                            + color.getBlue() + "]"
//                        );
//                    }
//                }
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
//
//    private static void writeLine(BufferedWriter writer, String line) throws IOException {
//        writer.write(line);
//        writer.newLine();
//    }
//
//    @Override
//    protected void complete(CommandSender commandSender, String[] strings, List<String> list) {
//
//    }
//
//}