package terramine.common.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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

public class ModBlocks {
    // Chests
    public static final Block GOLD_CHEST = register("gold_chest", new GoldChestBlock(FabricBlockSettings.create().mapColor(MapColor.GOLD).strength(3.0f, 6.0f).sounds(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.GOLD_CHEST));
    public static final Block TRAPPED_GOLD_CHEST = register("trapped_gold_chest", new GoldChestBlock(FabricBlockSettings.create().mapColor(MapColor.GOLD).strength(3.0f, 6.0f).sounds(SoundType.METAL).requiresCorrectToolForDrops(), true, () -> ModBlockEntityType.GOLD_CHEST));
    public static final Block FROZEN_CHEST = register("frozen_chest", new FrozenChestBlock(FabricBlockSettings.create().mapColor(MapColor.ICE).strength(2.0f, 5.0f).sounds(SoundType.METAL).friction(0.98f).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.FROZEN_CHEST));
    public static final Block TRAPPED_FROZEN_CHEST = register("trapped_frozen_chest", new FrozenChestBlock(FabricBlockSettings.create().mapColor(MapColor.ICE).strength(2.0f, 5.0f).sounds(SoundType.METAL).friction(0.98f).requiresCorrectToolForDrops(), true, () -> ModBlockEntityType.FROZEN_CHEST));
    public static final Block IVY_CHEST = register("ivy_chest", new IvyChestBlock(FabricBlockSettings.create().mapColor(MapColor.WOOD).strength(2.5f).sounds(SoundType.WOOD), false, () -> ModBlockEntityType.IVY_CHEST));
    public static final Block TRAPPED_IVY_CHEST = register("trapped_ivy_chest", new IvyChestBlock(FabricBlockSettings.create().mapColor(MapColor.WOOD).strength(2.5f).sounds(SoundType.WOOD), true, () -> ModBlockEntityType.IVY_CHEST));
    public static final Block SANDSTONE_CHEST = register("sandstone_chest", new SandstoneChestBlock(FabricBlockSettings.create().mapColor(MapColor.SAND).strength(3.0f).sounds(SoundType.STONE).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.SANDSTONE_CHEST));
    public static final Block TRAPPED_SANDSTONE_CHEST = register("trapped_sandstone_chest", new SandstoneChestBlock(FabricBlockSettings.create().mapColor(MapColor.SAND).strength(3.0f).sounds(SoundType.STONE).requiresCorrectToolForDrops(), true, () -> ModBlockEntityType.SANDSTONE_CHEST));
    public static final Block WATER_CHEST = register("water_chest", new WaterChestBlock(FabricBlockSettings.create().mapColor(MapColor.WATER).strength(3.0f, 6.0f).sounds(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.WATER_CHEST));
    public static final Block SKYWARE_CHEST = register("skyware_chest", new SkywareChestBlock(FabricBlockSettings.create().mapColor(MapColor.LAPIS).strength(3.0f, 6.0f).sounds(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.SKYWARE_CHEST));
    public static final Block SHADOW_CHEST = register("shadow_chest", new ShadowChestBlock(FabricBlockSettings.create().mapColor(MapColor.COLOR_PURPLE).strength(3.0f, 6.0f).sounds(SoundType.METAL).requiresCorrectToolForDrops(), false, () -> ModBlockEntityType.SHADOW_CHEST));
    public static final Block PIGGY_BANK = register("piggy_bank", new PiggyBankBlock(Properties.ofFullCopy(Blocks.TERRACOTTA), () -> ModBlockEntityType.PIGGY_BANK));
    public static final Block SAFE = register("safe", new SafeBlock(Properties.ofFullCopy(Blocks.IRON_BLOCK), () -> ModBlockEntityType.SAFE));

    // Metals
    public static final Block METEORITE_ORE = register("meteorite_ore", new HotFloorBlock(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).strength(5f)));
    public static final Block RAW_METEORITE_BLOCK = register("raw_meteorite_block", new Block(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).strength(8f)));
    public static final Block METEORITE_BLOCK = register("meteorite_block", new Block(Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));
    public static final Block DEMONITE_ORE = register("demonite_ore", new Block(Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final Block DEEPSLATE_DEMONITE_ORE = register("deepslate_demonite_ore", new Block(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final Block RAW_DEMONITE_BLOCK = register("raw_demonite_block", new Block(Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));
    public static final Block DEMONITE_BLOCK = register("demonite_block", new Block(Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Block CRIMTANE_ORE = register("crimtane_ore", new Block(Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final Block DEEPSLATE_CRIMTANE_ORE = register("deepslate_crimtane_ore", new Block(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final Block RAW_CRIMTANE_BLOCK = register("raw_crimtane_block", new Block(Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));
    public static final Block CRIMTANE_BLOCK = register("crimtane_block", new Block(Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Block HELLSTONE_ORE = register("hellstone_ore", new HellstoneBlock(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).sound(SoundType.NETHER_GOLD_ORE).strength(7f)));
    public static final Block RAW_HELLSTONE_BLOCK = register("raw_hellstone_block", new HotFloorBlock(Properties.ofFullCopy(Blocks.MAGMA_BLOCK).destroyTime(10f).explosionResistance(1200f), false));
    public static final Block HELLSTONE_BLOCK = register("hellstone_block", new Block(Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));

    // Misc
    public static final Block REDSTONE_STONE = register("redstone_stone", new RedStoneStoneBlock(Properties.ofFullCopy(Blocks.STONE).strength(1.5f, 1200.0f)));
    public static final Block REDSTONE_DEEPSLATE = register("redstone_deepslate", new RedStoneDeepslateBlock(Properties.ofFullCopy(Blocks.DEEPSLATE).strength(3.0f, 1200.0f)));
    public static final Block INSTANT_TNT = register("instant_tnt", new InstantTNTBlock(Properties.ofFullCopy(Blocks.TNT)));
    public static final Block TINKERER_TABLE = register("tinkerer_workshop", new Block(Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));

    // Building
    public static final Block SUNPLATE_BLOCK = register("sunplate_block", new Block(Properties.ofFullCopy(Blocks.GOLD_BLOCK)));
    public static final Block CLOUD = register("cloud", new Block(Properties.of().mapColor(MapColor.SNOW).strength(0.2f).sound(SoundType.SNOW).noOcclusion()));
    public static final Block RAIN_CLOUD = register("rain_cloud", new RainCloudBlock(Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(0.2f).sound(SoundType.SNOW).noOcclusion()));
    public static final Block BLUE_BRICKS = register("blue_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block CRACKED_BLUE_BRICKS = register("cracked_blue_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block FANCY_BLUE_BRICKS = register("fancy_blue_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block GREEN_BRICKS = register("green_brick", new DungeonBlock(Properties.of().requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN).strength(1.5f, 1200.0F)));
    public static final Block CRACKED_GREEN_BRICKS = register("cracked_green_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block FANCY_GREEN_BRICKS = register("fancy_green_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_GREEN).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block PURPLE_BRICKS = register("purple_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block CRACKED_PURPLE_BRICKS = register("cracked_purple_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));
    public static final Block FANCY_PURPLE_BRICKS = register("fancy_purple_brick", new DungeonBlock(Properties.of().mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5f, 1200.0F)));

    // Vegetation
    public static final Block VILE_MUSHROOM = register("vile_mushroom", new EvilMushroom(true, Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(blockState -> 1).hasPostProcess(ModBlocks::always)));
    public static final Block POTTED_VILE_MUSHROOM = register("potted_vile_mushroom", new FlowerPotBlock(VILE_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).instabreak().noOcclusion()));
    public static final Block VICIOUS_MUSHROOM = register("vicious_mushroom", new EvilMushroom(false, Properties.of().mapColor(MapColor.COLOR_RED).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(blockState -> 1).hasPostProcess(ModBlocks::always)));
    public static final Block POTTED_VICIOUS_MUSHROOM = register("potted_vicious_mushroom", new FlowerPotBlock(VICIOUS_MUSHROOM, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).pushReaction(PushReaction.DESTROY).instabreak().noOcclusion()));

    // Corruption
    public static final Block CORRUPTED_GRASS = register("corrupted_grass", new CorruptedGrass(Properties.ofFullCopy(Blocks.GRASS_BLOCK).randomTicks()));
    public static final Block CORRUPTED_GRAVEL = register("corrupted_gravel", new CorruptedFallingBlock(Properties.ofFullCopy(Blocks.GRAVEL).randomTicks()));
    public static final Block CORRUPTED_SAND = register("corrupted_sand", new CorruptedFallingBlock(Properties.ofFullCopy(Blocks.SAND).randomTicks()));
    public static final Block CORRUPTED_GLASS = register("corrupted_glass", new CorruptedTransparentBlock(Properties.ofFullCopy(Blocks.GLASS).randomTicks()));
    public static final Block CORRUPTED_SANDSTONE = register("corrupted_sandstone", new CorruptedBlock(Properties.ofFullCopy(Blocks.SANDSTONE).randomTicks()));
    public static final Block CORRUPTED_ANDESITE = register("corrupted_andesite", new CorruptedBlock(Properties.ofFullCopy(Blocks.ANDESITE).randomTicks()));
    public static final Block CORRUPTED_DIORITE = register("corrupted_diorite", new CorruptedBlock(Properties.ofFullCopy(Blocks.DIORITE).randomTicks()));
    public static final Block CORRUPTED_GRANITE = register("corrupted_granite", new CorruptedBlock(Properties.ofFullCopy(Blocks.GRANITE).randomTicks()));
    public static final Block CORRUPTED_STONE = register("corrupted_stone", new CorruptedBlock(Properties.ofFullCopy(Blocks.STONE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE = register("corrupted_deepslate", new CorruptedRotatableBlock(Properties.ofFullCopy(Blocks.DEEPSLATE).randomTicks()));
    public static final Block CORRUPTED_COBBLESTONE = register("corrupted_cobblestone", new CorruptedBlock(Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks()));
    public static final Block CORRUPTED_COBBLED_DEEPSLATE = register("corrupted_cobbled_deepslate", new CorruptedBlock(Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).randomTicks()));
    public static final Block CORRUPTED_COAL_ORE = register("corrupted_coal_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.COAL_ORE).randomTicks()));
    public static final Block CORRUPTED_IRON_ORE = register("corrupted_iron_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.IRON_ORE).randomTicks()));
    public static final Block CORRUPTED_COPPER_ORE = register("corrupted_copper_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.COPPER_ORE).randomTicks()));
    public static final Block CORRUPTED_GOLD_ORE = register("corrupted_gold_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.GOLD_ORE).randomTicks()));
    public static final Block CORRUPTED_LAPIS_ORE = register("corrupted_lapis_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.LAPIS_ORE).randomTicks()));
    public static final Block CORRUPTED_REDSTONE_ORE = register("corrupted_redstone_ore", new CorruptedRedstoneOreBlock(Properties.ofFullCopy(Blocks.REDSTONE_ORE).randomTicks()));
    public static final Block CORRUPTED_DIAMOND_ORE = register("corrupted_diamond_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DIAMOND_ORE).randomTicks()));
    public static final Block CORRUPTED_EMERALD_ORE = register("corrupted_emerald_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.EMERALD_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_COAL_ORE = register("corrupted_deepslate_coal_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COAL_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_IRON_ORE = register("corrupted_deepslate_iron_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_COPPER_ORE = register("corrupted_deepslate_copper_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_GOLD_ORE = register("corrupted_deepslate_gold_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_LAPIS_ORE = register("corrupted_deepslate_lapis_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_LAPIS_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_REDSTONE_ORE = register("corrupted_deepslate_redstone_ore", new CorruptedRedstoneOreBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_DIAMOND_ORE = register("corrupted_deepslate_diamond_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).randomTicks()));
    public static final Block CORRUPTED_DEEPSLATE_EMERALD_ORE = register("corrupted_deepslate_emerald_ore", new CorruptedBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).randomTicks()));
    public static final Block CORRUPTED_SNOW_LAYER = register("corrupted_snow_layer", new CorruptedSnowLayer(Properties.ofFullCopy(Blocks.SNOW).randomTicks()));
    public static final Block CORRUPTED_SNOW = register("corrupted_snow", new CorruptedBlock(Properties.ofFullCopy(Blocks.SNOW_BLOCK).randomTicks()));
    public static final Block CORRUPTED_ICE = register("corrupted_ice", new CorruptedBlock(Properties.ofFullCopy(Blocks.ICE).randomTicks()));
    public static final Block CORRUPTED_PACKED_ICE = register("corrupted_packed_ice", new CorruptedBlock(Properties.ofFullCopy(Blocks.PACKED_ICE).randomTicks()));
    public static final Block CORRUPTED_BLUE_ICE = register("corrupted_blue_ice", new CorruptedBlock(Properties.ofFullCopy(Blocks.BLUE_ICE).randomTicks()));

    // Crimson
    public static final Block CRIMSON_GRASS = register("crimson_grass", new CrimsonGrass(Properties.ofFullCopy(Blocks.GRASS_BLOCK).randomTicks()));
    public static final Block CRIMSON_GRAVEL = register("crimson_gravel", new CrimsonFallingBlock(Properties.ofFullCopy(Blocks.GRAVEL).randomTicks()));
    public static final Block CRIMSON_SAND = register("crimson_sand", new CrimsonFallingBlock(Properties.ofFullCopy(Blocks.SAND).randomTicks()));
    public static final Block CRIMSON_GLASS = register("crimson_glass", new CrimsonTransparentBlock(Properties.ofFullCopy(Blocks.GLASS).randomTicks()));
    public static final Block CRIMSON_SANDSTONE = register("crimson_sandstone", new CrimsonBlock(Properties.ofFullCopy(Blocks.SANDSTONE).randomTicks()));
    public static final Block CRIMSON_ANDESITE = register("crimson_andesite", new CrimsonBlock(Properties.ofFullCopy(Blocks.ANDESITE).randomTicks()));
    public static final Block CRIMSON_DIORITE = register("crimson_diorite", new CrimsonBlock(Properties.ofFullCopy(Blocks.DIORITE).randomTicks()));
    public static final Block CRIMSON_GRANITE = register("crimson_granite", new CrimsonBlock(Properties.ofFullCopy(Blocks.GRANITE).randomTicks()));
    public static final Block CRIMSON_STONE = register("crimson_stone", new CrimsonBlock(Properties.ofFullCopy(Blocks.STONE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE = register("crimson_deepslate", new CrimsonRotatableBlock(Properties.ofFullCopy(Blocks.DEEPSLATE).randomTicks()));
    public static final Block CRIMSON_COBBLESTONE = register("crimson_cobblestone", new CrimsonBlock(Properties.ofFullCopy(Blocks.COBBLESTONE).randomTicks()));
    public static final Block CRIMSON_COBBLED_DEEPSLATE = register("crimson_cobbled_deepslate", new CrimsonBlock(Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).randomTicks()));
    public static final Block CRIMSON_COAL_ORE = register("crimson_coal_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.COAL_ORE).randomTicks()));
    public static final Block CRIMSON_IRON_ORE = register("crimson_iron_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.IRON_ORE).randomTicks()));
    public static final Block CRIMSON_COPPER_ORE = register("crimson_copper_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.COPPER_ORE).randomTicks()));
    public static final Block CRIMSON_GOLD_ORE = register("crimson_gold_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.GOLD_ORE).randomTicks()));
    public static final Block CRIMSON_LAPIS_ORE = register("crimson_lapis_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.LAPIS_ORE).randomTicks()));
    public static final Block CRIMSON_REDSTONE_ORE = register("crimson_redstone_ore", new CrimsonRedstoneOreBlock(Properties.ofFullCopy(Blocks.REDSTONE_ORE).randomTicks()));
    public static final Block CRIMSON_DIAMOND_ORE = register("crimson_diamond_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DIAMOND_ORE).randomTicks()));
    public static final Block CRIMSON_EMERALD_ORE = register("crimson_emerald_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.EMERALD_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_COAL_ORE = register("crimson_deepslate_coal_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COAL_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_IRON_ORE = register("crimson_deepslate_iron_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_COPPER_ORE = register("crimson_deepslate_copper_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_GOLD_ORE = register("crimson_deepslate_gold_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_LAPIS_ORE = register("crimson_deepslate_lapis_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_LAPIS_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_REDSTONE_ORE = register("crimson_deepslate_redstone_ore", new CrimsonRedstoneOreBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_REDSTONE_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_DIAMOND_ORE = register("crimson_deepslate_diamond_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).randomTicks()));
    public static final Block CRIMSON_DEEPSLATE_EMERALD_ORE = register("crimson_deepslate_emerald_ore", new CrimsonBlock(Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).randomTicks()));
    public static final Block CRIMSON_SNOW_LAYER = register("crimson_snow_layer", new CrimsonSnowLayer(Properties.ofFullCopy(Blocks.SNOW).randomTicks()));
    public static final Block CRIMSON_SNOW = register("crimson_snow", new CrimsonBlock(Properties.ofFullCopy(Blocks.SNOW_BLOCK).randomTicks()));
    public static final Block CRIMSON_ICE = register("crimson_ice", new CrimsonBlock(Properties.ofFullCopy(Blocks.ICE).randomTicks()));
    public static final Block CRIMSON_PACKED_ICE = register("crimson_packed_ice", new CrimsonBlock(Properties.ofFullCopy(Blocks.PACKED_ICE).randomTicks()));
    public static final Block CRIMSON_BLUE_ICE = register("crimson_blue_ice", new CrimsonBlock(Properties.ofFullCopy(Blocks.BLUE_ICE).randomTicks()));

    private static Block register(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, TerraMine.id(name), block);
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
