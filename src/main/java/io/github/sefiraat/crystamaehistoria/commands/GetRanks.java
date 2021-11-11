package io.github.sefiraat.crystamaehistoria.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import io.github.sefiraat.crystamaehistoria.player.PlayerStatistics;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class GetRanks extends SubCommand {

    public GetRanks() {
        super("rank", "Displays your Crystamae ranks", false);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(PlayerStatistics.getSpellRankString(player.getUniqueId()));
            player.sendMessage(PlayerStatistics.getStoryRankString(player.getUniqueId()));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void complete(CommandSender commandSender, String[] strings, List<String> list) {
        // Not required
    }
}