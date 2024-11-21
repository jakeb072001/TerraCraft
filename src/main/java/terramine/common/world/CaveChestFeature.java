package terramine.common.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import terramine.TerraMine;
import terramine.common.entity.mobs.hardmode.MimicEntity;
import terramine.common.init.ModBlocks;
import terramine.common.init.ModComponents;
import terramine.common.init.ModEntities;
import terramine.common.init.ModLootTables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaveChestFeature extends Feature<NoneFeatureConfiguration> {

	public CaveChestFeature() {
		super(NoneFeatureConfiguration.CODEC);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		BlockPos origin = context.origin();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();

		List<BlockPos> positions = new ArrayList<>();
		BlockPos.betweenClosedStream(origin.offset(-1, 0, -1), origin.offset(1, 0, 1)).forEach((pos -> positions.add(pos.immutable())));
		positions.remove(origin);
		positions.removeIf(currentPos -> blockWaterCheck(currentPos, level));
		positions.removeIf(currentPos -> blockWaterCheck(currentPos.above(), level));
		positions.removeIf(currentPos -> !level.getBlockState(currentPos.below()).blocksMotion());
		if (positions.size() < 1) {
			return false;
		}
		Collections.shuffle(positions);

		generateContainer(level, positions.remove(0), random);

		return !TerraMine.CONFIG.worldgen.caveChest.disableChests;
	}

	public boolean blockWaterCheck(BlockPos pos, WorldGenLevel level) {
		if (!level.isEmptyBlock(pos)) {
			return !level.isWaterAt(pos);
		}
		return false;
	}

	public void generateContainer(WorldGenLevel level, BlockPos pos, RandomSource random) {
		BlockPos offsetPos = pos.atY(100);
		boolean frozen = false;
		boolean jungle = false;
		boolean desert = false;

		if (level.getBiome(offsetPos).value().coldEnoughToSnow(offsetPos)) {
			frozen = true;
		} else if (level.getBiome(offsetPos).is(BiomeTags.IS_JUNGLE)) {
			jungle = true;
		} else if (level.getBiome(offsetPos).is(BiomeTags.HAS_VILLAGE_DESERT)) {
			desert = true;
		}

		if (!desert && !jungle && ModComponents.HARDMODE.get(level.getLevelData()).get() && random.nextFloat() * 100 < TerraMine.CONFIG.worldgen.caveChest.mimicChance) {
			MimicEntity mimic = ModEntities.MIMIC.create(level.getLevel());
			if (mimic != null) {
				if (frozen) {
					mimic.setMimicType(3);
				} else {
					mimic.setMimicType(2);
				}
				mimic.setDormant(true);
				mimic.setFacing(Direction.Plane.HORIZONTAL.getRandomDirection(random));
				mimic.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
				level.addFreshEntity(mimic);
			}
		} else if (random.nextInt(5) == 0) {
			if (frozen) {
				this.setBlock(level, pos, ModBlocks.TRAPPED_FROZEN_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			} else if (jungle) {
				this.setBlock(level, pos, ModBlocks.TRAPPED_IVY_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			} else if (desert) {
				this.setBlock(level, pos, ModBlocks.TRAPPED_SANDSTONE_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			} else {
				this.setBlock(level, pos, ModBlocks.TRAPPED_GOLD_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			}
			if (pos.getY() >= 4) {
				this.setBlock(level, pos.below(), ModBlocks.REDSTONE_STONE.defaultBlockState());
			} else {
				this.setBlock(level, pos.below(), ModBlocks.REDSTONE_DEEPSLATE.defaultBlockState());
			}
			this.setBlock(level, pos.below().below(), ModBlocks.INSTANT_TNT.defaultBlockState());
		} else {
			if (frozen) {
				this.setBlock(level, pos, ModBlocks.FROZEN_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			} else if (jungle) {
				this.setBlock(level, pos, ModBlocks.IVY_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			} else if (desert) {
				this.setBlock(level, pos, ModBlocks.SANDSTONE_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			} else {
				this.setBlock(level, pos, ModBlocks.GOLD_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
			}
		}

		if (pos.getY() <= TerraMine.CONFIG.worldgen.caveChest.deepCaveY) {
			RandomizableContainer.setBlockEntityLootTable(level, random, pos, ModLootTables.DEEP_CAVE_CHEST);
		} else {
			if (frozen) {
				RandomizableContainer.setBlockEntityLootTable(level, random, pos, ModLootTables.FROZEN_CAVE_CHEST);
			} else if (jungle) {
				RandomizableContainer.setBlockEntityLootTable(level, random, pos, ModLootTables.IVY_CAVE_CHEST);
			} else if (desert) {
				RandomizableContainer.setBlockEntityLootTable(level, random, pos, ModLootTables.SANDSTONE_CAVE_CHEST);
			} else {
				RandomizableContainer.setBlockEntityLootTable(level, random, pos, ModLootTables.CAVE_CHEST);
			}
		}
	}
}
