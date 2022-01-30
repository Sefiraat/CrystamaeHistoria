package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link PersistentDataType} for {@link Story}s which uses an
 * {@link Integer} array for storage purposes.
 * Creatively thieved from {@see <a href="https://github.com/baked-libs/dough/blob/main/dough-data/src/main/java/io/github/bakedlibs/dough/data/persistent/PersistentUUIDDataType.java">PersistentUUIDDataType}
 *
 * @author Sfiguz7
 * @author Walshy
 */

public class PersistentStoriesDataType implements PersistentDataType<PersistentDataContainer[], List<Story>> {

    public static final PersistentDataType<PersistentDataContainer[], List<Story>> TYPE = new PersistentStoriesDataType();

    @Override
    @Nonnull
    public Class<PersistentDataContainer[]> getPrimitiveType() {
        return PersistentDataContainer[].class;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public Class<List<Story>> getComplexType() {
        return (Class<List<Story>>) (Class<?>) List.class;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public PersistentDataContainer[] toPrimitive(List<Story> complex, PersistentDataAdapterContext context) {
        PersistentDataContainer[] containers = new PersistentDataContainer[complex.size()];
        int i = 0;

        for (Story story : complex) {
            PersistentDataContainer container = context.newPersistentDataContainer();
            container.set(Keys.STORY_ID, PersistentDataType.STRING, story.getId());
            container.set(Keys.STORY_RARITY, PersistentDataType.INTEGER, story.getRarity().getId());
            containers[i] = container;
            i++;
        }
        return containers;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public List<Story> fromPrimitive(PersistentDataContainer[] primitive, PersistentDataAdapterContext context) {
        List<Story> list = new ArrayList<>();
        for (PersistentDataContainer container : primitive) {
            String id = container.get(Keys.STORY_ID, PersistentDataType.STRING);
            StoryRarity rarity = StoryRarity.getById(container.get(Keys.STORY_RARITY, PersistentDataType.INTEGER));
            list.add(CrystamaeHistoria.getStoriesManager().getStory(id, rarity));
        }
        return list;
    }

}
