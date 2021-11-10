package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum Skulls {

    CRYSTAL_ELEMENTAL("3b16a4c438f90e2193ae9877c4ec5d2a13c2bc679c113131ac98684e326c402f", StoryType.ELEMENTAL),
    CRYSTAL_MECHANICAL("789f76df5f41dc45df1c1f2aa4acd286a7842fff774c073c27c3f2dda5fe2aa0", StoryType.MECHANICAL),
    CRYSTAL_ALCHEMICAL("9caa807ee0b46a5031877438bc4ee5bcc202177b2b2e6fb0e3416438097d9266", StoryType.ALCHEMICAL),
    CRYSTAL_HISTORICAL("bfbabc6d29df6ae46706f4a3b7087df3e99cf94bfcfbd147f0bf5d902d82b72a", StoryType.HISTORICAL),
    CRYSTAL_HUMAN("edfc9f9546d04e2444899570909b9c5f6cbf7a6095692fcc4555a915b9337c0c", StoryType.HUMAN),
    CRYSTAL_ANIMAL("beb1e63fb346afb11e6acc1951a0d3ffdbf72f949d4216f443a6af4500b93564", StoryType.ANIMAL),
    CRYSTAL_CELESTIAL("64323e0487b2dabe760b4557fd5bb1f3f99de9287441aa33ba6a26bf557bfdba", StoryType.CELESTIAL),
    CRYSTAL_VOID("e754711a50023f5dbc6ed8483d1e760526dd5260a2bb1e559d3658a5163b575f", StoryType.VOID),
    CRYSTAL_PHILOSOPHICAL("a3cf04c05d997b85cbfaadd514671a9a28a06df0a584f9e2d4dccb2c35e48b24", StoryType.PHILOSOPHICAL),
    GUI_TIER_NUMBER_1("d2a6f0e84daefc8b21aa99415b16ed5fdaa6d8dc0c3cd591f49ca832b575", null),
    GUI_TIER_NUMBER_2("96fab991d083993cb83e4bcf44a0b6cefac647d4189ee9cb823e9cc1571e38", null),
    GUI_TIER_NUMBER_3("cd319b9343f17a35636bcbc26b819625a9333de3736111f2e932827c8e749", null),
    GUI_TIER_NUMBER_4("d198d56216156114265973c258f57fc79d246bb65e3c77bbe8312ee35db6", null),
    GUI_TIER_NUMBER_5("7fb91bb97749d6a6eed4449d23aea284dc4de6c3818eea5c7e149ddda6f7c9", null);

    @Getter
    protected static final Skulls[] cachedValues = values();

    @Getter
    private final String hash;
    @Getter
    @Nullable
    private final StoryType storyType;

    @ParametersAreNonnullByDefault
    Skulls(String hash, @Nullable StoryType storyType) {
        this.hash = hash;
        this.storyType = storyType;
    }

    public static Skulls getByType(StoryType type) {
        for (Skulls skull : values()) {
            if (skull.storyType == type) {
                return skull;
            }
        }
        return null;
    }

    public ItemStack getPlayerHead() {
        return PlayerHead.getItemStack(PlayerSkin.fromHashCode(hash));
    }

}
