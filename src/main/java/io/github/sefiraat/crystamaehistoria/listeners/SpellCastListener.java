package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.magic.spells.core.InstanceStave;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.sefiraat.crystamaehistoria.utils.theme.ThemeType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpellCastListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        final ItemStack stack = player.getInventory().getItemInMainHand();
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(stack);
        if (slimefunItem instanceof Stave) {
            Stave stave = (Stave) slimefunItem;
            InstanceStave staveInstance = new InstanceStave(stack);
            SpellSlot slot = SpellSlot.getByPlayerAndAction(player, e.getAction());
            if (slot == null) {
                return;
            }
            CastInformation castInformation = new CastInformation(player, stave.getLevel());
            CastResult castResult = staveInstance.tryCastSpell(slot, castInformation);
            if (castResult == CastResult.CAST_SUCCESS) {
                ItemMeta itemMeta = stack.getItemMeta();
                DataTypeMethods.setCustom(
                    itemMeta,
                    Keys.PDC_STAVE_STORAGE,
                    PersistentStaveDataType.TYPE,
                    staveInstance.getSpellInstanceMap()
                );
                stack.setItemMeta(itemMeta);
                staveInstance.buildLore();
                player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                    ThemeType.SUCCESS.getColor() + "Casting spell: " + castInformation.getSpellType().getId()
                ));
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                    ThemeType.WARNING.getColor() + "Cast failed: " + castResult.getMessage())
                );
            }
        }
    }
}