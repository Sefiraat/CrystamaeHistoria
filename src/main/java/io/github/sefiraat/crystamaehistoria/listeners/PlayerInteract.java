package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.magic.Spell;
import io.github.sefiraat.crystamaehistoria.magic.SpellCastInformation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(@Nonnull PlayerInteractEvent e) {
        // Todo remove this, debugging only
        SpellCastInformation castDefinition = new SpellCastInformation(e.getPlayer(), 3,3,3);
        if (e.getPlayer().isSneaking()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Spell.SQUALL.cast(castDefinition);
            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                Spell.BRIGHT.cast(castDefinition);
            }
        } else {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Spell.ETHEREAL_FLOW.cast(castDefinition);
            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                Spell.POISON_NOVA.cast(castDefinition);
            }
        }
    }

}