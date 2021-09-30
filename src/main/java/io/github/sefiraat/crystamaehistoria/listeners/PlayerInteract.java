package io.github.sefiraat.crystamaehistoria.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(@Nonnull PlayerInteractEvent e) {
        // Todo remove this, debugging only
//        SpellCastInformation castDefinition = new SpellCastInformation(e.getPlayer(), 3,3,3);
//        if (e.getPlayer().isSneaking()) {
//            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
//                Spell.RAIN_OF_FIRE.cast(castDefinition);
//            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
//                Spell.QUAKE.cast(castDefinition);
//            }
//        } else {
//            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
//                Spell.TEMPEST.cast(castDefinition);
//            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
//                Spell.POISON_NOVA.cast(castDefinition);
//            }
//        }
    }

}