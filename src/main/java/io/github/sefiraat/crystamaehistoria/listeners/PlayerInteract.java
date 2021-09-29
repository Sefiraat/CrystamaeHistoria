package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.magic.Spell;
import io.github.sefiraat.crystamaehistoria.magic.SpellDefinition;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(@Nonnull PlayerInteractEvent e) {
        // Todo remove this, debugging only
        SpellDefinition spellDefinition = new SpellDefinition(e.getPlayer(), 3,3,3);
        if (e.getPlayer().isSneaking()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Spell.RAIN_OF_FIRE.cast(spellDefinition);
            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                Spell.LIGHTNING_CALL.cast(spellDefinition);
            }
        } else {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Spell.TEMPEST.cast(spellDefinition);
            } else if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                Spell.POISON_NOVA.cast(spellDefinition);
            }
        }
    }

}