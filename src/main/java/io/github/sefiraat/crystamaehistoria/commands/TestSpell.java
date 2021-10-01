package io.github.sefiraat.crystamaehistoria.commands;

import io.github.mooy1.infinitylib.commands.SubCommand;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import io.github.sefiraat.crystamaehistoria.magic.Spells;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

public class TestSpell extends SubCommand {

    public TestSpell() {
        super("cast", "Casts the selected spell", true);
    }

    @Override
    protected void execute(@Nonnull CommandSender sender, @Nonnull  String[] args) {
        if (sender instanceof Player) {
            if (args.length != 2) {
                return;
            }
            int power = Integer.parseInt(args[1]);
            Spells.getById(args[0]).castSpell(new SpellCastInformation((Player) sender, power, power, power));
        }
    }

    @Override
    protected void complete(@Nonnull CommandSender commandSender, @Nonnull String[] strings, @Nonnull List<String> list) {
        if (strings.length == 1) {
            for (Spells spell : Spells.values()) {
                list.add(spell.name());
            }
        }
    }

}