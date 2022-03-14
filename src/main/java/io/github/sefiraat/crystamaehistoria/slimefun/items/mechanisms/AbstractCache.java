package io.github.sefiraat.crystamaehistoria.slimefun.items.mechanisms;

import io.github.sefiraat.crystamaehistoria.utils.Keys;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

public abstract class AbstractCache {

    protected final BlockMenu blockMenu;
    @Nullable
    @Getter
    protected UUID activePlayer;

    @ParametersAreNonnullByDefault
    protected AbstractCache(BlockMenu blockMenu) {
        this.blockMenu = blockMenu;
    }

    @OverridingMethodsMustInvokeSuper
    public void kill(Location location) {
        BlockStorage.clearBlockInfo(location);
    }

    public void setActivePlayer(@Nonnull Player player) {
        this.activePlayer = player.getUniqueId();
        BlockStorage.addBlockInfo(this.blockMenu.getBlock(), Keys.BS_CP_ACTIVE_PLAYER, player.getUniqueId().toString());
    }

}
