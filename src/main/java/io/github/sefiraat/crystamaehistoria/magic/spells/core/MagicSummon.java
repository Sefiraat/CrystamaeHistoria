package io.github.sefiraat.crystamaehistoria.magic.spells.core;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Consumer;

public class MagicSummon {

    @Getter
    public final UUID mobUUID;
    @Getter
    @Setter
    public Consumer<MagicSummon> tickConsumer;

    public MagicSummon(Mob mob) {
        this.mobUUID = mob.getUniqueId();
    }

    @Nullable
    public Mob getMob() {
        return (Mob) Bukkit.getEntity(mobUUID);
    }

    public void kill() {
        CrystamaeHistoria.getSummonedEntityMap().remove(this);
        Mob mob = (Mob) Bukkit.getEntity(mobUUID);
        if (mob != null) {
            mob.remove();
        }
    }

    public void run() {
        if (tickConsumer != null) {
            tickConsumer.accept(this);
        }
    }
}

