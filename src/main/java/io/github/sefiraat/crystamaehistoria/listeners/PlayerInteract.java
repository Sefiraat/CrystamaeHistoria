package io.github.sefiraat.crystamaehistoria.listeners;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.magic.CastInformation;
import io.github.sefiraat.crystamaehistoria.magic.CastResult;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.SpellSlot;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.Stave;
import io.github.sefiraat.crystamaehistoria.slimefun.tools.stave.StaveStorage;
import io.github.sefiraat.crystamaehistoria.theme.ThemeType;
import io.github.sefiraat.crystamaehistoria.utils.StoryUtils;
import io.github.sefiraat.crystamaehistoria.utils.datatypes.PersistentStaveDataType;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack stave = player.getInventory().getItemInMainHand();
        SlimefunItem slimefunItem = SlimefunItem.getByItem(stave);
        if (slimefunItem instanceof Stave) {
            Stave sfStave = (Stave) slimefunItem;
            StaveStorage staveStorage = new StaveStorage(stave);
            SpellSlot slot = SpellSlot.getByPlayerAndAction(player, e.getAction());
            if (slot == null) {
                return;
            }
            CastInformation castInformation = new CastInformation(player, sfStave.getLevel());
            CastResult castResult = staveStorage.tryCastSpell(slot, castInformation);
            if (castResult == CastResult.CAST_SUCCESS) {
                ItemMeta itemMeta = stave.getItemMeta();
                StoryUtils.setCustom(
                    itemMeta,
                    CrystamaeHistoria.getKeys().getPdcStaveStorage(),
                    PersistentStaveDataType.TYPE,
                    staveStorage.getSpellInstanceMap()
                );
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                    ThemeType.SUCCESS.getColor() + "Casting spell : " + castInformation.getSpellType().getId()
                ));
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                    ThemeType.WARNING.getColor() + "Casting failed : " + castResult.getMessage())
                );
            }
        }
    }
}