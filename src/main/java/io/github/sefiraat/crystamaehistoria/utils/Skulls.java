package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum Skulls {

    CRYSTAL_ELEMENTAL("f8368a0d1b95432f14de4473cbbc6314ed9dcf1cc55621510e6339995b176add", StoryType.ELEMENTAL),
    CRYSTAL_MECHANICAL("3cc99b4f2ab0dd952b8bb5518fb254401f1479f4d647251381acfed8fc0ca475", StoryType.MECHANICAL),
    CRYSTAL_ALCHEMICAL("c28c9d7aa926ff2f013c706ce6886b48620fe4a9db732c6f35c2ae5e6e950c27", StoryType.ALCHEMICAL),
    CRYSTAL_HISTORICAL("41806240fa2f4c3c674ae468dd4c78e1f87ab70fdc73b94bd889968375847bca", StoryType.HISTORICAL),
    CRYSTAL_HUMAN("8be82c96e659a9f61d32bf8954cbb2079399c6b28040c3f0b23ca97526f56a2d", StoryType.HUMAN),
    CRYSTAL_ANIMAL("9ae52bc1f1733eb6bb47d68401b0064e8a3f1f7fd72b8b96da6c6afb5c744731", StoryType.ANIMAL),
    CRYSTAL_CELESTIAL("17e55dd3057ebb1e9c32b5d0cffcf7ea20c7dc3f13667bc35c9de4794533f73d", StoryType.CELESTIAL),
    CRYSTAL_VOID("90576e18ea045943b66e0de9b2ee470eae26165a95b7b60a7bf8d485a7d771d", StoryType.VOID),
    CRYSTAL_PHILOSOPHICAL("5dc2f9505091b32b80a689469cbe1b15c26d2fb7c6e4537040daff4255a193fc", StoryType.PHILOSOPHICAL),

//    CRYSTAL_ELEMENTAL("3b16a4c438f90e2193ae9877c4ec5d2a13c2bc679c113131ac98684e326c402f", StoryType.ELEMENTAL),
//    CRYSTAL_MECHANICAL("789f76df5f41dc45df1c1f2aa4acd286a7842fff774c073c27c3f2dda5fe2aa0", StoryType.MECHANICAL),
//    CRYSTAL_ALCHEMICAL("9caa807ee0b46a5031877438bc4ee5bcc202177b2b2e6fb0e3416438097d9266", StoryType.ALCHEMICAL),
//    CRYSTAL_HISTORICAL("bfbabc6d29df6ae46706f4a3b7087df3e99cf94bfcfbd147f0bf5d902d82b72a", StoryType.HISTORICAL),
//    CRYSTAL_HUMAN("edfc9f9546d04e2444899570909b9c5f6cbf7a6095692fcc4555a915b9337c0c", StoryType.HUMAN),
//    CRYSTAL_ANIMAL("beb1e63fb346afb11e6acc1951a0d3ffdbf72f949d4216f443a6af4500b93564", StoryType.ANIMAL),
//    CRYSTAL_CELESTIAL("64323e0487b2dabe760b4557fd5bb1f3f99de9287441aa33ba6a26bf557bfdba", StoryType.CELESTIAL),
//    CRYSTAL_VOID("e754711a50023f5dbc6ed8483d1e760526dd5260a2bb1e559d3658a5163b575f", StoryType.VOID),
//    CRYSTAL_PHILOSOPHICAL("a3cf04c05d997b85cbfaadd514671a9a28a06df0a584f9e2d4dccb2c35e48b24", StoryType.PHILOSOPHICAL),

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
