package terramine.common.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import terramine.TerraMine;
import terramine.common.block.*;
import terramine.common.block.chests.*;
import terramine.common.block.plants.EvilMushroom;

import java.util.function.Function;

public class ModBlocks {
    // Chests
    public static final Block GOLD_CHEST = register("gold_chest", key -> new GoldChestBlock(Properties.of().setId(key).mapColor(MapColor.GOLD).strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.GOLD_CHEST));
    public static final Block TRAPPED_GOLD_CHEST = register("trapped_gold_chest", key -> new GoldChestBlock(Properties.of().setId(key).mapColor(MapColor.GOLD).strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops(), true, () -> ModBlockEntityType.GOLD_CHEST));
    public static final Block FROZEN_CHEST = register("frozen_chest", key -> new FrozenChestBlock(Properties.of().setId(key).mapColor(MapColor.ICE).strength(2.0f, 5.0f).sound(SoundType.METAL).friction(0.98f).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.FROZEN_CHEST));
    public static final Block TRAPPED_FROZEN_CHEST = register("trapped_frozen_chest", key -> new FrozenChestBlock(Properties.of().setId(key).mapColor(MapColor.ICE).strength(2.0f, 5.0f).sound(SoundType.METAL).friction(0.98f).requiresCorrectToolForDrops(), true, () -> ModBlockEntityType.FROZEN_CHEST));
    public static final Block IVY_CHEST = register("ivy_chest", key -> new IvyChestBlock(Properties.of().setId(key).mapColor(MapColor.WOOD).strength(2.5f).sound(SoundType.WOOD), false, () -> ModBlockEntityType.IVY_CHEST));
    public static final Block TRAPPED_IVY_CHEST = register("trapped_ivy_chest", key -> new IvyChestBlock(Properties.of().setId(key).mapColor(MapColor.WOOD).strength(2.5f).sound(SoundType.WOOD), true, () -> ModBlockEntityType.IVY_CHEST));
    public static final Block SANDSTONE_CHEST = register("sandstone_chest", key -> new SandstoneChestBlock(Properties.of().setId(key).mapColor(MapColor.SAND).strength(3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.SANDSTONE_CHEST));
    public static final Block TRAPPED_SANDSTONE_CHEST = register("trapped_sandstone_chest", key -> new SandstoneChestBlock(Properties.of().setId(key).mapColor(MapColor.SAND).strength(3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops(), true, () -> ModBlockEntityType.SANDSTONE_CHEST));
    public static final Block WATER_CHEST = register("water_chest", key -> new WaterChestBlock(Properties.of().setId(key).mapColor(MapColor.WATER).strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.WATER_CHEST));
    public static final Block SKYWARE_CHEST = register("skyware_chest", key -> new SkywareChestBlock(Properties.of().setId(key).mapColor(MapColor.LAPIS).strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.SKYWARE_CHEST));
    public static final Block SHADOW_CHEST = register("shadow_chest", key -> new ShadowChestBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_PURPLE).strength(3.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.SHADOW_CHEST));
    public static final Block PIGGY_BANK = register("piggy_bank", key -> new PiggyBankBlock(Properties.ofFullCopy(Blocks.TERRACOTTA).setId(key), () -> ModBlockEntityType.PIGGY_BANK));
    public static final Block SAFE = register("safe", key -> new SafeBlock(Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(key), () -> ModBlockEntityType.SAFE));

    // Metals
    public static final Block METEORITE_ORE = register("meteorite_ore", key -> new HotFloorBlock(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).setId(key).strength(5f)));
    public static final Block RAW_METEORITE_BLOCK = register("raw_meteorite_block", key -> new Block(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).setId(key).strength(8f)));
    public static final Block METEORITE_BLOCK = register("meteorite_block", key -> new Block(Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).setId(key)));
    public static final Block DEMONITE_ORE = register("demonite_ore", key -> new Block(Properties.ofFullCopy(Blocks.IRON_ORE).setId(key)));
    public static final Block DEEPSLATE_DEMONITE_ORE = register("deepslate_demonite_ore", key -> new Block(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).setId(key)));
    public static final Block RAW_DEMONITE_BLOCK = register("raw_demonite_block", key -> new Block(Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).setId(key)));
    public static final Block DEMONITE_BLOCK = register("demonite_block", key -> new Block(Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(key)));
    public static final Block CRIMTANE_ORE = register("crimtane_ore", key -> new Block(Properties.ofFullCopy(Blocks.IRON_ORE).setId(key)));
    public static final Block DEEPSLATE_CRIMTANE_ORE = register("deepslate_crimtane_ore", key -> new Block(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).setId(key)));
    public static final Block RAW_CRIMTANE_BLOCK = register("raw_crimtane_block", key -> new Block(Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).setId(key)));
    public static final Block CRIMTANE_BLOCK = register("crimtane_block", key -> new Block(Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(key)));
    public static final Block HELLSTONE_ORE = register("hellstone_ore", key -> new HellstoneBlock(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).setId(key).sound(SoundType.NETHER_GOLD_ORE).strength(7f)));
    public static final Block RAW_HELLSTONE_BLOCK = register("raw_hellstone_block", key -> new HotFloorBlock(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).setId(key).destroyTime(10f).explosionResistance(1200f), false));
    public static final Block HELLSTONE_BLOCK = register("hellstone_block", key -> new Block(Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).setId(key)));

    // Misc
    public static final Block REDSTONE_STONE = register("redstone_stone", key -> new RedStoneStoneBlock(Properties.ofFullCopy(Blocks.STONE).setId(key).strength(1.5f, 1200.0f)));
    public static final Block REDSTONE_DEEPSLATE = register("redstone_deepslate", key -> new RedStoneDeepslateBlock(Properties.ofFullCopy(Blocks.DEEPSLATE).setId(key).strength(3.0f, 1200.0f)));
    public static final Block INSTANT_TNT = register("instant_tnt", key -> new InstantTNTBlock(Properties.ofFullCopy(Blocks.TNT).setId(key)));
    public static final Block TINKERER_TABLE = register("tinkerer_workshop", key -> new Block(Properties.ofFullCopy(Blocks.CRAFTING_TABLE).setId(key)));

    // Building
    public static final Block SUNPLATE_BLOCK = register("sunplate_block", key -> new Block(Properties.ofFullCopy(Blocks.GOLD_BLOCK).setId(key)));
    public static final Block CLOUD = register("cloud", key -> new Block(Properties.of().setId(key).mapColor(MapColor.SNOW).strength(0.2f).sound(SoundType.SNOW).noOcclusion()));
    public static final Block RAIN_CLOUD = register("rain_cloud", key -> new RainCloudBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(0.2f).sound(SoundType.SNOW).noOcclusion()));
    public static final Block BLUE_BRICKS = register("blue_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block CRACKED_BLUE_BRICKS = register("cracked_blue_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block FANCY_BLUE_BRICKS = register("fancy_blue_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block GREEN_BRICKS = register("green_brick", key -> new DungeonBlock(Properties.of().setId(key).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN).strength(1.5f, 1200.0F)));
    public static final Block CRACKED_GREEN_BRICKS = register("cracked_green_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block FANCY_GREEN_BRICKS = register("fancy_green_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block PURPLE_BRICKS = register("purple_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block CRACKED_PURPLE_BRICKS = register("cracked_purple_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block FANCY_PURPLE_BRICKS = register("fancy_purple_brick", key -> new DungeonBlock(Properties.of().setId(key).mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));

    // Vegetation
    public static final Block VILE_MUSHROOM = register("vile_mushroom", key -> new EvilMushroom(true, Properties.of().setId(key).mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(blockState -> 1).hasPostProcess(ModBlocks::always)));
    public static final Block POTTED_VILE_MUSHROOM = register("potted_vile_mushroom", key -> new FlowerPotBlock(VILE_MUSHROOM, BlockBehaviour.Properties.of().setId(key).mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).instabreak().noOcclusion()));
    public static final Block VICIOUS_MUSHROOM = register("vicious_mushroom", key -> new EvilMushroom(false, Properties.of().setId(key).mapColor(MapColor.COLOR_RED).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(blockState -> 1).hasPostProcess(ModBlocks::always)));
    public static final Block POTTED_VICIOUS_MUSHROOM = register("potted_vicious_mushroom", key -> new FlowerPotBlock(VICIOUS_MUSHROOM, BlockBehaviour.Properties.of().setId(key).mapColor(MapColor.COLOR_RED).pushReaction(PushReaction.DESTROY).instabreak().noOcclusion()));

    // Corruption
    public static final Block CORRUPTED_GRASS = register("corrupted_grass", key -> new CorruptedGrass(Properties.ofFullCopy(Blocks.GRASS_BLOCK).setId(key).randomTicks()));
    public static final Block CORRUPTED_GRAVEL = register("corrupted_gravel", key -> new CorruptedFallingBlock(Properties.ofFullCopy(Blocks.GRAVEL).setId(key).randomTicks()));
    public static final Block CORRUPTED_SAND = register("corrupted_sand", key -> new CorruptedFallingBlock(Properties.ofFullCopy(Blocks.SAND).setId(key).randomTicks()));
    public static final Block CORRUPTED_GLASS = register("corrupted_glass", key -> new CorruptedTransparentBlock(Properties.ofFullCopy(Blocks.GLASS).setId(key).randomTicks()));
    public static final Block CORRUPTED_SANDSTONE = register("corrupted_sandstone", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.SANDSTONE).setId(key).randomTicks()));
    public static final Block CORRUPTED_ANDESITE = register("corrupted_andesite", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.ANDESITE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DIORITE = register("corrupted_diorite", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DIORITE).setId(key).randomTicks()));
    public static final Block CORRUPTED_GRANITE = register("corrupted_granite", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.GRANITE).setId(key).randomTicks()));
    public static final Block CORRUPTED_STONE = register("corrupted_stone", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.STONE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE = register("corrupted_deepslate", key -> new CorruptedRotatableBlock(Properties.ofFullCopy(Blocks.DEEPSLATE).setId(key).randomTicks()));
    public static final Block CORRUPTED_COBBLESTONE = register("corrupted_cobblestone", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.COBBLESTONE).setId(key).randomTicks()));
    public static final Block CORRUPTED_COBBLED_DEEPSLATE = register("corrupted_cobbled_deepslate", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).setId(key).randomTicks()));
    public static final Block CORRUPTED_COAL_ORE = register("corrupted_coal_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.COAL_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_IRON_ORE = register("corrupted_iron_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.IRON_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_COPPER_ORE = register("corrupted_copper_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.COPPER_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_GOLD_ORE = register("corrupted_gold_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.GOLD_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_LAPIS_ORE = register("corrupted_lapis_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.LAPIS_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_REDSTONE_ORE = register("corrupted_redstone_ore", key -> new CorruptedRedstoneOreBlock(Properties.ofFullCopy(Blocks.REDSTONE_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DIAMOND_ORE = register("corrupted_diamond_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DIAMOND_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_EMERALD_ORE = register("corrupted_emerald_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.EMERALD_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_COAL_ORE = register("corrupted_deepslate_coal_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COAL_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_IRON_ORE = register("corrupted_deepslate_iron_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_COPPER_ORE = register("corrupted_deepslate_copper_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_GOLD_ORE = register("corrupted_deepslate_gold_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_LAPIS_ORE = register("corrupted_deepslate_lapis_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_LAPIS_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_REDSTONE_ORE = register("corrupted_deepslate_redstone_ore", key -> new CorruptedRedstoneOreBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_DIAMOND_ORE = register("corrupted_deepslate_diamond_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_EMERALD_ORE = register("corrupted_deepslate_emerald_ore", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).setId(key).randomTicks()));
    public static final Block CORRUPTED_SNOW_LAYER = register("corrupted_snow_layer", key -> new CorruptedSnowLayer(Properties.ofFullCopy(Blocks.SNOW).setId(key).randomTicks()));
    public static final Block CORRUPTED_SNOW = register("corrupted_snow", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.SNOW_BLOCK).setId(key).randomTicks()));
    public static final Block CORRUPTED_ICE = register("corrupted_ice", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.ICE).setId(key).randomTicks()));
    public static final Block CORRUPTED_PACKED_ICE = register("corrupted_packed_ice", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.PACKED_ICE).setId(key).randomTicks()));
    public static final Block CORRUPTED_BLUE_ICE = register("corrupted_blue_ice", key -> new CorruptedBlock(Properties.ofFullCopy(Blocks.BLUE_ICE).setId(key).randomTicks()));

    // Crimson
    public static final Block CRIMSON_GRASS = register("crimson_grass", key -> new CrimsonGrass(Properties.ofFullCopy(Blocks.GRASS_BLOCK).setId(key).randomTicks()));
    public static final Block CRIMSON_GRAVEL = register("crimson_gravel", key -> new CrimsonFallingBlock(Properties.ofFullCopy(Blocks.GRAVEL).setId(key).randomTicks()));
    public static final Block CRIMSON_SAND = register("crimson_sand", key -> new CrimsonFallingBlock(Properties.ofFullCopy(Blocks.SAND).setId(key).randomTicks()));
    public static final Block CRIMSON_GLASS = register("crimson_glass", key -> new CrimsonTransparentBlock(Properties.ofFullCopy(Blocks.GLASS).setId(key).randomTicks()));
    public static final Block CRIMSON_SANDSTONE = register("crimson_sandstone", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.SANDSTONE).setId(key).randomTicks()));
    public static final Block CRIMSON_ANDESITE = register("crimson_andesite", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.ANDESITE).setId(key).randomTicks()));
    public static final Block CRIMSON_DIORITE = register("crimson_diorite", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DIORITE).setId(key).randomTicks()));
    public static final Block CRIMSON_GRANITE = register("crimson_granite", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.GRANITE).setId(key).randomTicks()));
    public static final Block CRIMSON_STONE = register("crimson_stone", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.STONE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE = register("crimson_deepslate", key -> new CrimsonRotatableBlock(Properties.ofFullCopy(Blocks.DEEPSLATE).setId(key).randomTicks()));
    public static final Block CRIMSON_COBBLESTONE = register("crimson_cobblestone", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.COBBLESTONE).setId(key).randomTicks()));
    public static final Block CRIMSON_COBBLED_DEEPSLATE = register("crimson_cobbled_deepslate", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).setId(key).randomTicks()));
    public static final Block CRIMSON_COAL_ORE = register("crimson_coal_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.COAL_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_IRON_ORE = register("crimson_iron_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.IRON_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_COPPER_ORE = register("crimson_copper_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.COPPER_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_GOLD_ORE = register("crimson_gold_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.GOLD_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_LAPIS_ORE = register("crimson_lapis_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.LAPIS_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_REDSTONE_ORE = register("crimson_redstone_ore", key -> new CrimsonRedstoneOreBlock(Properties.ofFullCopy(Blocks.REDSTONE_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DIAMOND_ORE = register("crimson_diamond_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DIAMOND_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_EMERALD_ORE = register("crimson_emerald_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.EMERALD_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_COAL_ORE = register("crimson_deepslate_coal_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COAL_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_IRON_ORE = register("crimson_deepslate_iron_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_COPPER_ORE = register("crimson_deepslate_copper_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_GOLD_ORE = register("crimson_deepslate_gold_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_LAPIS_ORE = register("crimson_deepslate_lapis_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_LAPIS_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_REDSTONE_ORE = register("crimson_deepslate_redstone_ore", key -> new CrimsonRedstoneOreBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_DIAMOND_ORE = register("crimson_deepslate_diamond_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_EMERALD_ORE = register("crimson_deepslate_emerald_ore", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).setId(key).randomTicks()));
    public static final Block CRIMSON_SNOW_LAYER = register("crimson_snow_layer", key -> new CrimsonSnowLayer(Properties.ofFullCopy(Blocks.SNOW).setId(key).randomTicks()));
    public static final Block CRIMSON_SNOW = register("crimson_snow", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.SNOW_BLOCK).setId(key).randomTicks()));
    public static final Block CRIMSON_ICE = register("crimson_ice", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.ICE).setId(key).randomTicks()));
    public static final Block CRIMSON_PACKED_ICE = register("crimson_packed_ice", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.PACKED_ICE).setId(key).randomTicks()));
    public static final Block CRIMSON_BLUE_ICE = register("crimson_blue_ice", key -> new CrimsonBlock(Properties.ofFullCopy(Blocks.BLUE_ICE).setId(key).randomTicks()));

    private static Block register(String name, Function<ResourceKey<Block>, Block> blockFactory) {
        ResourceLocation resourceLocation = TerraMine.id(name);
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, resourceLocation);
        Block block = blockFactory.apply(key);

        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
