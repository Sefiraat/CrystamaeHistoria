package io.github.sefiraat.crystamaehistoria.slimefun.tools.satchel;

import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryType;
import scala.Int;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;

public class SatchelInstance {

    private long id;
    private int tier;
    private String lastUser = "Unknown";
    private final Map<StoryRarity, int[]> amounts = new EnumMap<>(StoryRarity.class);

    public SatchelInstance(long id, int tier) {
        this.tier = tier;
        this.id = id;
        amounts.put(StoryRarity.COMMON, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        amounts.put(StoryRarity.UNCOMMON, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        amounts.put(StoryRarity.RARE, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        amounts.put(StoryRarity.EPIC, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        amounts.put(StoryRarity.MYTHICAL, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        amounts.put(StoryRarity.UNIQUE, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    public int getAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type) {
        return amounts.get(rarity)[type.getId() - 1];
    }

    public void setAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type, int amount) {
        final int[] values = this.amounts.get(rarity);
        values[type.getId() - 1] = (long) amount >= Integer.MAX_VALUE ? Integer.MAX_VALUE : amount;
        this.amounts.put(rarity, values);
    }

    public void setAmounts(@Nonnull StoryRarity rarity, int[] amounts) {
        this.amounts.put(rarity, amounts);
    }

    public void addAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type, int amount) {
        final int[] values = this.amounts.get(rarity);
        final int oldAmount = values[type.getId() - 1];
        final long newAmount = (long) oldAmount + (long) amount;

        values[type.getId() - 1] = newAmount >= Integer.MAX_VALUE ? Integer.MAX_VALUE : oldAmount + amount;
        this.amounts.put(rarity, values);
    }

    public void removeAmount(@Nonnull StoryRarity rarity, @Nonnull StoryType type, int amount) {
        final int[] values = this.amounts.get(rarity);
        final int oldAmount = values[type.getId() - 1];
        values[type.getId() - 1] = oldAmount - amount;
        this.amounts.put(rarity, values);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public Map<StoryRarity, int[]> getAmounts() {
        return amounts;
    }
}
