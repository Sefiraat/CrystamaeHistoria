package io.github.sefiraat.crystamaehistoria.slimefun;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class AbstractCache {

    protected final BlockMenu blockMenu;

    @ParametersAreNonnullByDefault
    protected AbstractCache(BlockMenu blockMenu) {
        this.blockMenu = blockMenu;
    }

}
