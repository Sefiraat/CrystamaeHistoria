package io.github.sefiraat.crystamaehistoria.utils.datatypes;

import io.github.sefiraat.crystamaehistoria.CrystamaeHistoria;
import io.github.sefiraat.crystamaehistoria.stories.Story;
import io.github.sefiraat.crystamaehistoria.stories.definition.StoryRarity;
import io.github.sefiraat.crystamaehistoria.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A {@link PersistentDataType} for {@link Story}s which uses an
 * {@link Integer} array for storage purposes.
 * Creatively thieved from {@see <a href="https://github.com/baked-libs/dough/blob/main/dough-data/src/main/java/io/github/bakedlibs/dough/data/persistent/PersistentUUIDDataType.java">PersistentUUIDDataType}
 *
 * @author Sfiguz7
 * @author Walshy
 */

public class PersistentStoryChunkDataType implements PersistentDataType<PersistentDataContainer[], List<Story>> {

    public static final PersistentDataType<PersistentDataContainer[], List<Story>> TYPE = new PersistentStoryChunkDataType();

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
    public PersistentDataContainer[] toPrimitive(@Nonnull List<Story> complex, @Nonnull PersistentDataAdapterContext context) {
        Keys keys = CrystamaeHistoria.getKeys();
        PersistentDataContainer[] containers = new PersistentDataContainer[complex.size()];
        int i = 0;

        for (Story story : complex) {
            PersistentDataContainer container = context.newPersistentDataContainer();
            container.set(Keys.STORY_ID, PersistentDataType.STRING, story.getId());
            container.set(Keys.STORY_RARITY, PersistentDataType.INTEGER, story.getRarity().getId());
            container.set(Keys.RESOLUTION_STORY_LOCATION, PersistentDataType.LONG, story.getBlockPosition().getPosition());
            container.set(Keys.RESOLUTION_STORY_WORLD, PersistentUUIDDataType.TYPE, story.getBlockPosition().getWorld().getUID());
            containers[i] = container;
            i++;
        }

        return containers;
    }

    @Override
    @Nonnull
    public List<Story> fromPrimitive(@Nonnull PersistentDataContainer[] primitive, @Nonnull PersistentDataAdapterContext context) {
        final Keys keys = CrystamaeHistoria.getKeys();
        List<Story> list = new ArrayList<>();
        for (PersistentDataContainer container : primitive) {
            final String id = container.get(Keys.STORY_ID, PersistentDataType.STRING);
            final StoryRarity rarity = StoryRarity.getById(container.get(Keys.STORY_RARITY, PersistentDataType.INTEGER));
            final long locationLong = container.get(Keys.RESOLUTION_STORY_LOCATION, PersistentDataType.LONG);
            final UUID worldUuid = container.get(Keys.RESOLUTION_STORY_WORLD, PersistentUUIDDataType.TYPE);
            final World world = Bukkit.getWorld(worldUuid);
            final BlockPosition position = new BlockPosition(world, locationLong);
            final Story story = CrystamaeHistoria.getStoriesManager().getStory(id, rarity);
            story.setBlockPosition(position);
            list.add(story);
        }

        return list;
    }
}
