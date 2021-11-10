package io.github.sefiraat.crystamaehistoria.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import io.github.sefiraat.crystamaehistoria.slimefun.ItemGroups;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

public class OpenStoryCompendium extends SubCommand {

    public OpenStoryCompendium() {
        super("stories", "Opens the story compendium", false);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getUniqueId());
            Optional<PlayerProfile> playerProfile = PlayerProfile.find(offlinePlayer);
            playerProfile.ifPresent(profile -> ItemGroups.STORY_COLLECTION.open(player, profile, SlimefunGuideMode.SURVIVAL_MODE));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void complete(CommandSender commandSender, String[] strings, List<String> list) {
        // Not required
    }
}