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

	public static final List<ResourceLocation> INJECT_TABLE_IDS = Arrays.asList(
			new ResourceLocation("chests/village/village_armorer"),
			new ResourceLocation("chests/village/village_butcher"),
			new ResourceLocation("chests/village/village_tannery"),
			new ResourceLocation("chests/village/village_temple"),
			new ResourceLocation("chests/village/village_toolsmith"),
			new ResourceLocation("chests/village/village_weaponsmith"),
			new ResourceLocation("chests/village/village_desert_house"),
			new ResourceLocation("chests/village/village_plains_house"),
			new ResourceLocation("chests/village/village_savanna_house"),
			new ResourceLocation("chests/village/village_snowy_house"),
			new ResourceLocation("chests/village/village_taiga_house"),
			new ResourceLocation("chests/abandoned_mineshaft"),
			new ResourceLocation("chests/bastion_hoglin_stable"),
			new ResourceLocation("chests/bastion_treasure"),
			new ResourceLocation("chests/buried_treasure"),
			new ResourceLocation("chests/desert_pyramid"),
			new ResourceLocation("chests/end_city_treasure"),
			new ResourceLocation("chests/jungle_temple"),
			new ResourceLocation("chests/nether_bridge"),
			new ResourceLocation("chests/pillager_outpost"),
			new ResourceLocation("chests/ruined_portal"),
			new ResourceLocation("chests/shipwreck_treasure"),
			new ResourceLocation("chests/spawn_bonus_chest"),
			new ResourceLocation("chests/stronghold_corridor"),
			new ResourceLocation("chests/underwater_ruin_big"),
			new ResourceLocation("chests/woodland_mansion"),
			new ResourceLocation("entities/bat"),
			new ResourceLocation("entities/blaze"),
			new ResourceLocation("entities/ghast"),
			new ResourceLocation("entities/zombie")
	);

	public static void onLootTableLoad(ResourceKey<LootTable> id, FabricLootTableBuilder supplier) {
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
