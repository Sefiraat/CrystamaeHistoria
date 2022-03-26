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

    CRYSTAL_CLEAR("76869f9c95fc847be697a13c897a3e384372b0bcd17e217f52f963235cd419bf", null),
    CRYSTAL_POLYCHROME("39be0d270bc9bc439a73c699197801dd490f2fbfb2cbd25a2606cb2969470676", null),
    CRYSTAL_KALEIDOSCOPIC("dba479b02dc2e3731358f76bd767edcff552821ea981261ac739ac1a193dffd9", null),
    CRYSTAL_MOTLEY("33f81076bcfded0b512aa2ad4c9726f9af1ac8c02cbf3118434925971596000c", null),
    CRYSTAL_PRISMATIC("491a6e3ca73ed8a05544d25eaa3cce8c5449d680ed0f1b661374fb75dfa3e7f4", null),

    RUNE_A("2b5ec1f698de0e98623abe6e1f26adb6140cba5c9ef43fc7cfdc0e7e6ca4fd8", null),
    RUNE_B("58bb35c180c504bedd7281d120b36c094b7931a0c63203b2911432855230185e", null),
    RUNE_C("dcd519483dd041b34ea006ee668592cea6d1ecafa6aeb82f7c73281ea339cbcd", null),
    RUNE_D("1bf28ea4a46c5c1169c6c12757955fc6ad3eeaadd4aab958a40b3da50465dd0f", null),
    RUNE_E("4bdfc33f23d65a3ce0daa129954ed8a3544647134b30e936bfdd2fa3f5fe49fe", null),
    RUNE_F("d8a7fd7ab963aa314d283d26bd5bdae504e414b147c263cddc22f9e92e3fbf20", null),
    RUNE_G("5cdec5390ec0dc3138c08cf070e32e56ce13261c5d6a733b8e2e5087880e5df0", null),
    RUNE_H("5ec2b8c9317f460f3b3f913a9244ccc9694951a00828defc667347686fa95187", null),
    RUNE_I("645a0099faf3f0729c1d1c6b184347be7a717f8c639a3c80c04161eb474d7605", null),
    RUNE_J("3591e03a102eea3f2c400dd0dbcc144d63cd54e9f22e7f8bbdcb26eff85a6bbc", null),
    RUNE_K("42d6643a7e127cf1609800e442fd0b8afa31cd8eb7e2192a5ad0bc892b8d3bd6", null),
    RUNE_L("dd5ebf10c768dda2b343dc1dc443f7b3e751f64d94b82e85ac1d0bddbb314ba8", null),
    RUNE_M("c3960ecec7a2f2c1678f02148d1eb964964840dc3e99de58dd2cebbd376e0233", null),
    RUNE_N("ce61df6ab2f3c8a6485f459dc961c84d46b77dbd980ef1bc084b7caef4de23cd", null),
    RUNE_O("a3eb6191181b41988471983880424401ad53c532333f42ad287000442b92d471", null),
    RUNE_P("f1bd3374ee846e2339feb2b790d45ec0e03dd292034cb9e5314306858f5fbb99", null),
    RUNE_Q("c28460cc98aeb6045af1efec753a7f6dcc8707a6dcd28a812b8ed53cc7cf1f00", null),
    RUNE_R("b5fb5137a9c75bff73ffa5e0af854c4fc00b2d316bf394bc44412b71c889a164", null),
    RUNE_S("82f81a26007c18e2a26a10ac8aa6b9a643e3c0a893b054a5451a441c5c32509e", null),
    RUNE_T("ec46804e6ecb40701c0bb3bc341941422a3a1af4e94d757ad462a277f28bd34", null),
    RUNE_U("cf48f30c303476a50425b1d08ba37eb4da72506c0afd3a93f9043a020cb6a650", null),
    RUNE_V("754a923d7adabb08f0e677ffee4afdb733d01a3ea0e49bb780432f5c76705736", null),
    RUNE_W("806f62c7209e9f2bc35252a4386d0c67a4a7103b403ddfc9d9433c43ab4fc7c6", null),
    RUNE_X("326d8520de0bb448694b6298b7c4b3eeb3dbe712e86c6cd3d2dce35172e30a00", null),
    RUNE_Y("4386be633ecb034b39186c133e919a9b4fcf1680e4c65db6449e7d49c464427b", null),
    RUNE_Z("310fbb74095f99a6e729818270ec8f5a1d4f3fccc5f4da129c5204a41a2de79c", null),

    GUI_TIER_NUMBER_1("d2a6f0e84daefc8b21aa99415b16ed5fdaa6d8dc0c3cd591f49ca832b575", null),
    GUI_TIER_NUMBER_2("96fab991d083993cb83e4bcf44a0b6cefac647d4189ee9cb823e9cc1571e38", null),
    GUI_TIER_NUMBER_3("cd319b9343f17a35636bcbc26b819625a9333de3736111f2e932827c8e749", null),
    GUI_TIER_NUMBER_4("d198d56216156114265973c258f57fc79d246bb65e3c77bbe8312ee35db6", null),
    GUI_TIER_NUMBER_5("7fb91bb97749d6a6eed4449d23aea284dc4de6c3818eea5c7e149ddda6f7c9", null),

    ITEM_MILK("bc68c3e9d2ee9390a61a666f77949ec44cb72c3cea43301cd0c41510472a7d74", null);

    @Getter
    private static final Skulls[] cachedValues = values();

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

    @Nullable
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
