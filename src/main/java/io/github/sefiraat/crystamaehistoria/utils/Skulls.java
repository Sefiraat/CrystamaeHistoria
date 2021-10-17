package io.github.sefiraat.crystamaehistoria.utils;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public enum Skulls {

    // TODO Replace with heads from Cheesy

    CRYSTAL_ELEMENTAL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMzYTViZmM4YTJhM2ExNTJkNjQ2YTViZWE2OTRhNDI1YWI3OWRiNjk0YjIxNGYxNTZjMzdjNzE4M2FhIn19fQ==", StoryType.ELEMENTAL),
    CRYSTAL_MECHANICAL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWRjNTYzNTVmMTFjZTUzZTE0ZDM3NGVjZjJhMGIyNTUzMDFiNzM0ZDk5YzY3NDI0MGFmYWNjNzNlMjE0NWMifX19", StoryType.MECHANICAL),
    CRYSTAL_ALCHEMICAL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDExMzliM2VmMmU0YzQ0YTRjOTgzZjExNGNiZTk0OGQ4YWI1ZDRmODc5YTVjNjY1YmI4MjBlNzM4NmFjMmYifX19", StoryType.ALCHEMICAL),
    CRYSTAL_HISTORICAL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzYwN2NmZmNkNzg2NGZmMjdjNzhiMjlhMmM1OTU1MTMxYTY3N2JlZjYzNzFmODg5ODhkM2RjYzM3ZWY4ODczIn19fQ==", StoryType.HISTORICAL),
    CRYSTAL_HUMAN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDExMzdiOWJmNDM1YzRiNmI4OGZhZWFmMmU0MWQ4ZmQwNGUxZDk2NjNkNmY2M2VkM2M2OGNjMTZmYzcyNCJ9fX0=", StoryType.HUMAN),
    CRYSTAL_ANIMAL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFiOWEwYTZkMWI5OTEyNzk0Mjg5ZWNhMWUyMjRlYWU2ZDc2YTdjYjc1MmNhNjg5ZjFiOTkxY2U5NzBhZGVlIn19fQ==", StoryType.ANIMAL),
    CRYSTAL_CELESTIAL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2FmMDM5YmVjMWZjMWZiNzUxOTYwOTJiMjZlNjMxZjM3YTg3ZGZmMTQzZmMxODI5Nzc5OGQ0N2M1ZWFhZiJ9fX0=", StoryType.CELESTIAL),
    CRYSTAL_VOID("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2UxZTVjODFmYjlkNjRiNzQ5OTZmZDE3MTQ4OWRlYWRiYjhjYjc3MmQ1MmNmOGI1NjZlM2JjMTAyMzAxMDQ0In19fQ==", StoryType.VOID),
    CRYSTAL_PHILOSOPHICAL("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzU0ZGVlZWFiM2I5NzUwYzc3NjQyZWM5NWUzN2ZlMmJkZjlhZGM1NTVlMDgyNmRlZGQ3NjliZWRkMTAifX19", StoryType.PHILOSOPHICAL);

    @Getter
    private final String base64Code;
    @Getter
    private final StoryType storyType;

    @ParametersAreNonnullByDefault
    Skulls(String base64Code, StoryType storyType) {
        this.base64Code = base64Code;
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
        return PlayerHead.getItemStack(PlayerSkin.fromBase64(base64Code));
    }

}
