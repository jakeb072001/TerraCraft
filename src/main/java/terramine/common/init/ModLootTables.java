package terramine.common.init;

import net.fabricmc.fabric.api.loot.v2.FabricLootTableBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import terramine.TerraMine;

import java.util.Arrays;
import java.util.List;

public class ModLootTables {

	// Mobs
	public static final ResourceKey<LootTable> MIMIC = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("entities/mimic"));
	public static final ResourceKey<LootTable> DEMON_EYE = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("entities/demon_eye"));
	public static final ResourceKey<LootTable> EATER_OF_SOULS = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("entities/eater_of_souls"));
	public static final ResourceKey<LootTable> DEVOURER = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("entities/devourer"));
	public static final ResourceKey<LootTable> CRIMERA = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("entities/crimera"));

	// Treasure Bags
	public static final ResourceKey<LootTable> EYE_OF_CTHULHU_CORRUPTION = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("items/treasure_bag/eye_of_cthulhu_corruption"));
	public static final ResourceKey<LootTable> EYE_OF_CTHULHU_CRIMSON = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("items/treasure_bag/eye_of_cthulhu_crimson"));
	public static final ResourceKey<LootTable> EYE_OF_CTHULHU = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("items/treasure_bag/eye_of_cthulhu"));

	// Chests
	public static final ResourceKey<LootTable> SURFACE_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/surface_chest"));
	public static final ResourceKey<LootTable> OCEAN_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/ocean_chest"));
	public static final ResourceKey<LootTable> CAVE_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/cave_chest"));
	public static final ResourceKey<LootTable> DEEP_CAVE_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/deep_cave_chest"));
	public static final ResourceKey<LootTable> FROZEN_CAVE_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/frozen_cave_chest"));
	public static final ResourceKey<LootTable> IVY_CAVE_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/ivy_cave_chest"));
	public static final ResourceKey<LootTable> SANDSTONE_CAVE_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/sandstone_cave_chest"));
	public static final ResourceKey<LootTable> SHADOW_CHEST = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("chests/shadow_chest"));

	// Repair Items

	public static final List<ResourceLocation> INJECT_TABLE_IDS = Arrays.asList(
			ResourceLocation.withDefaultNamespace("chests/spawn_bonus_chest"),
			ResourceLocation.withDefaultNamespace("entities/bat"),
			ResourceLocation.withDefaultNamespace("entities/blaze"),
			ResourceLocation.withDefaultNamespace("entities/ghast"),
			ResourceLocation.withDefaultNamespace("entities/zombie")
	);

	public static void onLootTableLoad(ResourceKey<LootTable> id, LootTable.Builder supplier) {
		if (INJECT_TABLE_IDS.contains(id.location())) {
			supplier.pool(LootPool.lootPool().add(getInjectEntry(id.location().getPath())).build());
		}
	}

	private static LootPoolEntryContainer.Builder<?> getInjectEntry(String name) {
		ResourceKey<LootTable> resourceKey = ResourceKey.create(Registries.LOOT_TABLE, TerraMine.id("inject/" + name));
		return NestedLootTable.lootTableReference(resourceKey).setWeight(1);
	}

	private ModLootTables() {
	}
}
