package terramine.common.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;
import terramine.TerraMine;
import terramine.common.entity.block.InstantPrimedTNTEntity;
import terramine.common.entity.mobs.bosses.TestBoss;
import terramine.common.entity.mobs.prehardmode.CrimeraEntity;
import terramine.common.entity.mobs.prehardmode.DemonEyeEntity;
import terramine.common.entity.mobs.prehardmode.EaterOfSoulsEntity;
import terramine.common.entity.mobs.hardmode.MimicEntity;
import terramine.common.entity.mobs.prehardmode.devourer.DevourerBodyEntity;
import terramine.common.entity.mobs.prehardmode.devourer.DevourerEntity;
import terramine.common.entity.mobs.prehardmode.devourer.DevourerTailEntity;
import terramine.common.entity.projectiles.*;
import terramine.common.entity.throwables.BombEntity;
import terramine.common.entity.throwables.DynamiteEntity;
import terramine.common.entity.throwables.GrenadeEntity;

public class ModEntities {

	public static final EntityType<MimicEntity> MIMIC = register("mimic", FabricEntityTypeBuilder
			.<MimicEntity>createMob()
			.entityFactory(MimicEntity::new)
			.dimensions(EntityDimensions.fixed(14 / 16F, 14 / 16F))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(MimicEntity::createMobAttributes)
			.trackRangeBlocks(64)
			.build());

	public static final EntityType<DemonEyeEntity> DEMON_EYE = register("demon_eye", FabricEntityTypeBuilder
			.<DemonEyeEntity>createMob()
			.entityFactory(DemonEyeEntity::new)
			.dimensions(EntityDimensions.fixed(0.53f, 0.53f))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(DemonEyeEntity::createMobAttributes)
			.spawnRestriction(SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DemonEyeEntity::checkMobSpawnRules)
			.build());

	public static final EntityType<EaterOfSoulsEntity> EATER_OF_SOULS = register("eater_of_souls", FabricEntityTypeBuilder
			.<EaterOfSoulsEntity>createMob()
			.entityFactory(EaterOfSoulsEntity::new)
			.dimensions(EntityDimensions.fixed(1f, 0.6f))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(EaterOfSoulsEntity::createMobAttributes)
			.spawnRestriction(SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EaterOfSoulsEntity::checkMobSpawnRules)
			.build());

	public static final EntityType<DevourerEntity> DEVOURER = register("devourer", FabricEntityTypeBuilder
			.<DevourerEntity>createMob()
			.entityFactory(DevourerEntity::new)
			.dimensions(EntityDimensions.fixed(0.8f, 0.4f))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(DevourerEntity::createMobAttributes)
			.spawnRestriction(SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DevourerEntity::checkMobSpawnRules)
			.build());

	public static final EntityType<DevourerBodyEntity> DEVOURER_BODY = register("devourer_body", FabricEntityTypeBuilder
			.<DevourerBodyEntity>createMob()
			.entityFactory(DevourerBodyEntity::new)
			.dimensions(EntityDimensions.fixed(0.8f, 0.4f))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(DevourerBodyEntity::createMobAttributes)
			.disableSummon()
			.build());

	public static final EntityType<DevourerTailEntity> DEVOURER_TAIL = register("devourer_tail", FabricEntityTypeBuilder
			.<DevourerTailEntity>createMob()
			.entityFactory(DevourerTailEntity::new)
			.dimensions(EntityDimensions.fixed(0.8f, 0.4f))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(DevourerTailEntity::createMobAttributes)
			.disableSummon()
			.build());

	public static final EntityType<CrimeraEntity> CRIMERA = register("crimera", FabricEntityTypeBuilder
			.<CrimeraEntity>createMob()
			.entityFactory(CrimeraEntity::new)
			.dimensions(EntityDimensions.fixed(0.85f, 0.5f))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(CrimeraEntity::createMobAttributes)
			.spawnRestriction(SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrimeraEntity::checkMobSpawnRules)
			.build());

	/**
	 * Testing, remove later
	 */
	public static final EntityType<TestBoss> TEST_BOSS = register("test_boss", FabricEntityTypeBuilder
			.<TestBoss>createMob()
			.entityFactory(TestBoss::new)
			.dimensions(EntityDimensions.fixed(1f, 2f))
			.spawnGroup(MobCategory.MONSTER)
			.defaultAttributes(TestBoss::createMobAttributes)
			.spawnRestriction(SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TestBoss::checkMobSpawnRules)
			.build());

	public static final EntityType<FallingStarEntity> FALLING_STAR = register("falling_star", FabricEntityTypeBuilder
			.create(MobCategory.MISC, FallingStarEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.build());

	public static final EntityType<FallingMeteoriteEntity> METEORITE = register("meteorite", FabricEntityTypeBuilder
			.create(MobCategory.MISC, FallingMeteoriteEntity::new)
			.dimensions(EntityDimensions.fixed(1f, 1f))
			.build());

	public static final EntityType<MagicMissileEntity> MAGIC_MISSILE = register("magic_missile", FabricEntityTypeBuilder
			.create(MobCategory.MISC, MagicMissileEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.disableSummon()
			.build());

	public static final EntityType<FlamelashMissileEntity> FLAMELASH_MISSILE = register("flamelash_missile", FabricEntityTypeBuilder
			.create(MobCategory.MISC, FlamelashMissileEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.disableSummon()
			.build());

	public static final EntityType<RainbowMissileEntity> RAINBOW_MISSILE = register("rainbow_missile", FabricEntityTypeBuilder
			.create(MobCategory.MISC, RainbowMissileEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.disableSummon()
			.build());

	public static final EntityType<LaserEntity> LASER = register("laser", FabricEntityTypeBuilder
			.create(MobCategory.MISC, LaserEntity::new)
			.dimensions(EntityDimensions.fixed(0.60f, 0.10f))
			.disableSummon()
			.build());

	public static final EntityType<DynamiteEntity> DYNAMITE = register("dynamite", FabricEntityTypeBuilder
			.create(MobCategory.MISC, DynamiteEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.disableSummon()
			.build());

	public static final EntityType<GrenadeEntity> GRENADE = register("grenade", FabricEntityTypeBuilder
			.create(MobCategory.MISC, GrenadeEntity::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.disableSummon()
			.build());

	public static final EntityType<BombEntity> BOMB = register("bomb", FabricEntityTypeBuilder
			.create(MobCategory.MISC, BombEntity::new)
			.dimensions(EntityDimensions.fixed(0.5f, 0.5f))
			.disableSummon()
			.build());

	public static final EntityType<InstantPrimedTNTEntity> INSTANT_TNT = register("instant_tnt", FabricEntityTypeBuilder
			.create(MobCategory.MISC, InstantPrimedTNTEntity::new)
			.fireImmune()
			.dimensions(EntityDimensions.fixed(0.98f, 0.98f))
			.trackRangeBlocks(10)
			.trackedUpdateRate(10)
			.build());

	private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entType) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, TerraMine.id(name), entType);
	}

	public static void addToSpawn() {
		// todo: increase Demon Eye spawn weight to 100 when full moon, cant use Minecraft class otherwise server wont work
		naturalSpawn(DEMON_EYE, MobCategory.MONSTER, 110, 2, 6);
	}

	public static <T extends Entity> void naturalSpawn(EntityType<T> entType, MobCategory category, int weight, int minGroup, int maxGroup) {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.ZOMBIE).and(BiomeSelectors.foundInOverworld()), category, entType, weight, minGroup, maxGroup);
	}
}
