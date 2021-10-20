package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public enum Skulls {

    // TODO Replace with heads from Cheesy

    CRYSTAL_ELEMENTAL("533a5bfc8a2a3a152d646a5bea694a425ab79db694b214f156c37c7183aa", StoryType.ELEMENTAL),
    CRYSTAL_MECHANICAL("adc56355f11ce53e14d374ecf2a0b255301b734d99c674240afacc73e2145c", StoryType.MECHANICAL),
    CRYSTAL_ALCHEMICAL("41139b3ef2e4c44a4c983f114cbe948d8ab5d4f879a5c665bb820e7386ac2f", StoryType.ALCHEMICAL),
    CRYSTAL_HISTORICAL("c607cffcd7864ff27c78b29a2c5955131a677bef6371f88988d3dcc37ef8873", StoryType.HISTORICAL),
    CRYSTAL_HUMAN("85484f4b6367b95bb16288398f1c8dd6c61de988f3a8356d4c3ae73ea38a42", StoryType.HUMAN),
    CRYSTAL_ANIMAL("cb9b2a4d59781d1bec2d8278f23985e749c881b72d7876c979e71fda5bd3c", StoryType.ANIMAL),
    CRYSTAL_CELESTIAL("caf039bec1fc1fb75196092b26e631f37a87dff143fc18297798d47c5eaaf", StoryType.CELESTIAL),
    CRYSTAL_VOID("da3d7a709717d1ffe2402448d867b1a7f32fb8955621cc8977b61f9a3cbc95", StoryType.VOID),
    CRYSTAL_PHILOSOPHICAL("a1b9a0a6d1b9912794289eca1e224eae6d76a7cb752ca689f1b991ce970adee", StoryType.PHILOSOPHICAL),
    GUI_TIER_NUMBER_1("d2a6f0e84daefc8b21aa99415b16ed5fdaa6d8dc0c3cd591f49ca832b575", null),
    GUI_TIER_NUMBER_2("96fab991d083993cb83e4bcf44a0b6cefac647d4189ee9cb823e9cc1571e38", null),
    GUI_TIER_NUMBER_3("cd319b9343f17a35636bcbc26b819625a9333de3736111f2e932827c8e749", null),
    GUI_TIER_NUMBER_4("d198d56216156114265973c258f57fc79d246bb65e3c77bbe8312ee35db6", null),
    GUI_TIER_NUMBER_5("7fb91bb97749d6a6eed4449d23aea284dc4de6c3818eea5c7e149ddda6f7c9", null);

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
