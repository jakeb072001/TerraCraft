package terramine.common.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
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
import terramine.common.entity.projectiles.arrows.FlamingArrowEntity;
import terramine.common.entity.projectiles.arrows.JesterArrowEntity;
import terramine.common.entity.projectiles.arrows.UnholyArrowEntity;
import terramine.common.entity.throwables.BombEntity;
import terramine.common.entity.throwables.DynamiteEntity;
import terramine.common.entity.throwables.GrenadeEntity;
import terramine.common.entity.misc.ClientItemEntity;

public class ModEntities {

	public static final EntityType<MimicEntity> MIMIC = register("mimic", FabricEntityType.Builder
			.createMob(MimicEntity::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(MimicEntity::createMobAttributes))
			.sized(14 / 16F, 14 / 16F)
			.clientTrackingRange(64)
			.build());

	public static final EntityType<DemonEyeEntity> DEMON_EYE = register("demon_eye", FabricEntityType.Builder
			.createMob(DemonEyeEntity::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(DemonEyeEntity::createMobAttributes).spawnRestriction(SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DemonEyeEntity::checkMobSpawnRules))
			.sized(0.53f, 0.53f)
			.build());

	public static final EntityType<EaterOfSoulsEntity> EATER_OF_SOULS = register("eater_of_souls", FabricEntityType.Builder
			.createMob(EaterOfSoulsEntity::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(EaterOfSoulsEntity::createMobAttributes).spawnRestriction(SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EaterOfSoulsEntity::checkMobSpawnRules))
			.sized(1f, 0.6f)
			.build());

	public static final EntityType<DevourerEntity> DEVOURER = register("devourer", FabricEntityType.Builder
			.createMob(DevourerEntity::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(DevourerEntity::createMobAttributes).spawnRestriction(SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DevourerEntity::checkMobSpawnRules))
			.sized(0.8f, 0.4f)
			.build());

	public static final EntityType<DevourerBodyEntity> DEVOURER_BODY = register("devourer_body", FabricEntityType.Builder
			.createMob(DevourerBodyEntity::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(DevourerBodyEntity::createMobAttributes))
			.sized(0.8f, 0.4f)
			.noSummon()
			.build());

	public static final EntityType<DevourerTailEntity> DEVOURER_TAIL = register("devourer_tail", FabricEntityType.Builder
			.createMob(DevourerTailEntity::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(DevourerTailEntity::createMobAttributes))
			.sized(0.8f, 0.4f)
			.noSummon()
			.build());

	public static final EntityType<CrimeraEntity> CRIMERA = register("crimera", FabricEntityType.Builder
			.createMob(CrimeraEntity::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(CrimeraEntity::createMobAttributes).spawnRestriction(SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrimeraEntity::checkMobSpawnRules))
			.sized(0.85f, 0.5f)
			.build());

	/**
	 * Testing, remove later
	 */
	public static final EntityType<TestBoss> TEST_BOSS = register("test_boss", FabricEntityType.Builder
			.createMob(TestBoss::new, MobCategory.MONSTER, (builder) ->
					builder.defaultAttributes(TestBoss::createMobAttributes).spawnRestriction(SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TestBoss::checkMobSpawnRules))
			.sized(1f, 2f)
			.build());

	public static final EntityType<ClientItemEntity> CLIENT_ITEM = register("client_item", EntityType.Builder
			.of(ClientItemEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F)
			.noSummon()
			.build());

	public static final EntityType<FallingStarEntity> FALLING_STAR = register("falling_star", EntityType.Builder
			.of(FallingStarEntity::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.build());

	public static final EntityType<FallingMeteoriteEntity> METEORITE = register("meteorite", EntityType.Builder
			.of(FallingMeteoriteEntity::new, MobCategory.MISC)
			.sized(1f, 1f)
			.build());

	public static final EntityType<MagicMissileEntity> MAGIC_MISSILE = register("magic_missile", EntityType.Builder
			.of(MagicMissileEntity::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.noSummon()
			.build());

	public static final EntityType<FlamelashMissileEntity> FLAMELASH_MISSILE = register("flamelash_missile", EntityType.Builder
			.of(FlamelashMissileEntity::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.noSummon()
			.build());

	public static final EntityType<RainbowMissileEntity> RAINBOW_MISSILE = register("rainbow_missile", EntityType.Builder
			.of(RainbowMissileEntity::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.noSummon()
			.build());

	public static final EntityType<LaserEntity> LASER = register("laser", EntityType.Builder
			.of(LaserEntity::new, MobCategory.MISC)
			.sized(0.60f, 0.10f)
			.noSummon()
			.build());

	public static final EntityType<DynamiteEntity> DYNAMITE = register("dynamite", EntityType.Builder
			.of(DynamiteEntity::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.noSummon()
			.build());

	public static final EntityType<GrenadeEntity> GRENADE = register("grenade", EntityType.Builder
			.of(GrenadeEntity::new, MobCategory.MISC)
			.sized(0.25f, 0.25f)
			.noSummon()
			.build());

	public static final EntityType<BombEntity> BOMB = register("bomb", EntityType.Builder
			.of(BombEntity::new, MobCategory.MISC)
			.sized(0.5f, 0.5f)
			.noSummon()
			.build());

	public static final EntityType<InstantPrimedTNTEntity> INSTANT_TNT = register("instant_tnt", EntityType.Builder
			.of(InstantPrimedTNTEntity::new, MobCategory.MISC)
			.fireImmune()
			.sized(0.98f, 0.98f)
			.clientTrackingRange(10)
			.updateInterval(10)
			.build());

	public static final EntityType<FlamingArrowEntity> FLAMING_ARROW = register("flaming_arrow", createArrow(FlamingArrowEntity::new));
	public static final EntityType<UnholyArrowEntity> UNHOLY_ARROW = register("unholy_arrow", createArrow(UnholyArrowEntity::new));
	public static final EntityType<JesterArrowEntity> JESTER_ARROW = register("jester_arrow", createArrow(JesterArrowEntity::new));

	private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entType) {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, TerraMine.id(name), entType);
	}

	private static <T extends Entity> EntityType<T> createArrow(EntityType.EntityFactory<T> factory) {
		return FabricEntityTypeBuilder.create(MobCategory.MISC, factory).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackedUpdateRate(20).build();
	}

	public static void addToSpawn() {
		// todo: increase Demon Eye spawn weight to 100 when full moon, cant use Minecraft class otherwise server wont work
		naturalSpawn(DEMON_EYE, MobCategory.MONSTER, 110, 2, 6);
	}

	public static <T extends Entity> void naturalSpawn(EntityType<T> entType, MobCategory category, int weight, int minGroup, int maxGroup) {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.ZOMBIE).and(BiomeSelectors.foundInOverworld()), category, entType, weight, minGroup, maxGroup);
	}
}
