package io.github.sefiraat.crystamaehistoria.slimefun.machines;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

public abstract class AbstractCache {

    protected final BlockMenu blockMenu;

    @ParametersAreNonnullByDefault
    protected AbstractCache(BlockMenu blockMenu) {
        this.blockMenu = blockMenu;
    }

    @OverridingMethodsMustInvokeSuper
    public void kill(Location location) {
        BlockStorage.clearBlockInfo(location);
    }

}
