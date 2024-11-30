package terramine.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import terramine.TerraMine;

public final class ModTags {
    // Block Tags
    public static final TagKey<Block> METEORITE_REPLACE_BLOCKS = createBlockTag("meteorite_replace_blocks");
    public static final TagKey<Block> MINEABLE_WITH_SHAXE = createBlockTag("mineable_with_shaxe");
    public static final TagKey<Block> CORRUPTION_MUSHROOM_GROW_BLOCKS = createBlockTag("corruption_mushroom_grow_blocks");
    public static final TagKey<Block> CRIMSON_MUSHROOM_GROW_BLOCKS = createBlockTag("crimson_mushroom_grow_blocks");

    // Item Tags
    public static final TagKey<Item> REPAIRS_SHADOW_ARMOR = createItemTag("repairs_shadow_armor");
    public static final TagKey<Item> REPAIRS_CRIMSON_ARMOR = createItemTag("repairs_crimson_armor");
    public static final TagKey<Item> REPAIRS_METEOR_ARMOR = createItemTag("repairs_meteor_armor");
    public static final TagKey<Item> REPAIRS_MOLTEN_ARMOR = createItemTag("repairs_molten_armor");

    private static TagKey<Block> createBlockTag(String string) {
        return TagKey.create(Registries.BLOCK, TerraMine.id(string));
    }

    private static TagKey<Item> createItemTag(String string) {
        return TagKey.create(Registries.ITEM, TerraMine.id(string));
    }
}
