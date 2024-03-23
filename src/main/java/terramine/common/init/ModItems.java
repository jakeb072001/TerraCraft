package terramine.common.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.ComposterBlock;
import terramine.TerraMine;
import terramine.common.item.CraftingItem;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.item.accessories.ShieldAccessoryItem;
import terramine.common.item.accessories.WhoopeeCushionItem;
import terramine.common.item.accessories.back.WingsItem;
import terramine.common.item.accessories.belt.*;
import terramine.common.item.accessories.feet.*;
import terramine.common.item.accessories.hands.*;
import terramine.common.item.accessories.necklace.*;
import terramine.common.item.armor.*;
import terramine.common.item.armor.vanity.FamiliarVanity;
import terramine.common.item.dye.BasicDye;
import terramine.common.item.equipment.*;
import terramine.common.item.equipment.swords.CustomSoundSwordItem;
import terramine.common.item.equipment.swords.VolcanoSwordItem;
import terramine.common.item.magic.*;
import terramine.common.item.misc.BossSpawnItem;
import terramine.common.item.misc.DemonHeartItem;
import terramine.common.item.misc.TreasureBagItem;
import terramine.common.item.throwables.BombItem;
import terramine.common.item.throwables.DynamiteItem;
import terramine.common.item.throwables.GrenadeItem;

@SuppressWarnings("unused")
public class ModItems {

	// Misc
	public static final Item MIMIC_SPAWN_EGG = register("mimic_spawn_egg", new SpawnEggItem(ModEntities.MIMIC, 0x805113, 0x212121, new Item.Properties()));
	public static final Item DEMON_EYE_SPAWN_EGG = register("demon_eye_spawn_egg", new SpawnEggItem(ModEntities.DEMON_EYE, 0xffffff, 0xff0000, new Item.Properties()));
	public static final Item EATER_OF_SOULS_SPAWN_EGG = register("eater_of_souls_spawn_egg", new SpawnEggItem(ModEntities.EATER_OF_SOULS, 0x735c5f, 0x999190, new Item.Properties()));
	public static final Item DEVOURER_SPAWN_EGG = register("devourer_spawn_egg", new SpawnEggItem(ModEntities.DEVOURER, 0x999190, 0x735c5f, new Item.Properties()));
	public static final Item CRIMERA_SPAWN_EGG = register("crimera_spawn_egg", new SpawnEggItem(ModEntities.CRIMERA, 0x72261f, 0xac524d, new Item.Properties()));
	public static final Item SUSPICIOUS_LOOKING_EYE = register("suspicious_looking_eye", new BossSpawnItem(ModEntities.TEST_BOSS, new FabricItemSettings().stacksTo(16).rarity(Rarity.RARE)));
	public static final Item UMBRELLA = register("umbrella", new UmbrellaItem());
	public static final Item WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionItem());
	public static final Item MAGIC_MIRROR = register("magic_mirror", new MagicMirrorItem());
	public static final Item COBALT_SHIELD = register("cobalt_shield", new ShieldAccessoryItem(new FabricItemSettings().maxDamage(2500).fireproof().rarity(Rarity.RARE), Items.DIAMOND));
	public static final Item OBSIDIAN_SHIELD = register("obsidian_shield", new ShieldAccessoryItem(new FabricItemSettings().maxDamage(2500).fireproof().rarity(Rarity.RARE), Items.OBSIDIAN));

	// todo: add many more dyes, need to create a model/item for each one but its just copy paste
	// todo: also add some custom shader dyes, need to add a system to render the dyes first though
	// todo: make dye craft-able, some will also be obtainable from enemies and other things
	// todo: maybe also make work like potions, so only one item is registered and doesn't need a model per item, maybe
	// Dye Items (uses hex)
	public static final Item RED_DYE = register("red_dye", new BasicDye(0xFF0000));
	public static final Item GREEN_DYE = register("green_dye", new BasicDye(0x008000));
	public static final Item BLUE_DYE = register("blue_dye", new BasicDye(0x0000FF));
	public static final Item YELLOW_DYE = register("yellow_dye", new BasicDye(0xFFFF00));
	public static final Item ORANGE_DYE = register("orange_dye", new BasicDye(0xFFA500));
	public static final Item PURPLE_DYE = register("purple_dye", new BasicDye(0x800080));
	public static final Item PINK_DYE = register("pink_dye", new BasicDye(0xFFC0CB));
	public static final Item BROWN_DYE = register("brown_dye", new BasicDye(0x964B00));
	public static final Item GRAY_DYE = register("gray_dye", new BasicDye(0x808080));
	public static final Item BLACK_DYE = register("black_dye", new BasicDye(0x000000));

	// Treasure Bags
	public static final Item EYE_OF_CTHULHU_TREASURE_BAG = register("eye_of_cthulhu_treasure_bag", new TreasureBagItem(new FabricItemSettings().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ModLootTables.EYE_OF_CTHULHU));

	// Crafting Items
	public static final Item LENS = register("lens", new CraftingItem(new FabricItemSettings(), false));
	public static final Item BLACK_LENS = register("black_lens", new CraftingItem(new FabricItemSettings(), false));
	public static final Item ROTTEN_CHUNK = register("rotten_chunk", new CraftingItem(new FabricItemSettings().stacksTo(64), true));
	public static final Item VERTEBRA = register("vertebra", new CraftingItem(new FabricItemSettings().stacksTo(64), false));

	// Ores etc
	public static final Item RAW_METEORITE = register("raw_meteorite", new CraftingItem(new FabricItemSettings(), false));
	public static final Item METEORITE_INGOT = register("meteorite_ingot", new CraftingItem(new FabricItemSettings(), true));
	public static final Item RAW_DEMONITE = register("raw_demonite", new CraftingItem(new FabricItemSettings(), false));
	public static final Item DEMONITE_INGOT = register("demonite_ingot", new CraftingItem(new FabricItemSettings(), true));
	public static final Item RAW_CRIMTANE = register("raw_crimtane", new CraftingItem(new FabricItemSettings(), false));
	public static final Item CRIMTANE_INGOT = register("crimtane_ingot", new CraftingItem(new FabricItemSettings(), false));
	public static final Item RAW_HELLSTONE = register("raw_hellstone", new CraftingItem(new FabricItemSettings().fireproof(), true));
	public static final Item RAW_HELLSTONE_HARDENED = register("raw_hellstone_hardened", new CraftingItem(new FabricItemSettings().fireproof(), true));
	public static final Item HELLSTONE_INGOT = register("hellstone_ingot", new CraftingItem(new FabricItemSettings().fireproof(), true));

	// Magic Items
	public static final Item MAGIC_MISSILE_ITEM = register("magic_missile", new MagicMissileItem());
	public static final Item FLAMELASH_ITEM = register("flamelash", new FlamelashItem());
	public static final Item RAINBOW_ROD_ITEM = register("rainbow_rod", new RainbowRodItem());
	public static final Item FAKE_FALLEN_STAR = register("fake_fallen_star", new CraftingItem(new FabricItemSettings(), true));
	public static final Item FALLEN_STAR = register("fallen_star", new CraftingItem(new FabricItemSettings().stacksTo(64).rarity(Rarity.UNCOMMON), true));
	public static final Item MANA_CRYSTAL = register("mana_crystal", new ManaCrystalItem(new FabricItemSettings().stacksTo(64).rarity(Rarity.RARE)));
	public static final Item DEMON_HEART = register("demon_heart", new DemonHeartItem(new FabricItemSettings().stacksTo(16).rarity(Rarity.EPIC)));

	// Informational
	public static final Item GOLD_WATCH = register("gold_watch", new AccessoryTerrariaItem());
	public static final Item DEPTH_METER = register("depth_meter", new AccessoryTerrariaItem());
	public static final Item COMPASS = register("compass", new AccessoryTerrariaItem());
	public static final Item GPS = register("gps", new AccessoryTerrariaItem());
	public static final Item WEATHER_RADIO = register("weather_radio", new AccessoryTerrariaItem());
	public static final Item SEXTANT = register("sextant", new AccessoryTerrariaItem());
	public static final Item FISH_FINDER = register("fish_finder", new AccessoryTerrariaItem());
	public static final Item METAL_DETECTOR = register("metal_detector", new AccessoryTerrariaItem());
	public static final Item STOPWATCH = register("stopwatch", new AccessoryTerrariaItem());
	public static final Item DPS_METER = register("dps_meter", new AccessoryTerrariaItem());
	public static final Item GOBLIN_TECH = register("goblin_tech", new AccessoryTerrariaItem());
	public static final Item PDA = register("pda", new AccessoryTerrariaItem());
	public static final Item CELL_PHONE = register("cell_phone", new CellPhoneItem());

	// Necklace
	public static final Item CROSS_NECKLACE = register("cross_necklace", new CrossNecklaceItem());
	public static final Item PANIC_NECKLACE = register("panic_necklace", new PanicNecklaceItem());
	public static final Item RANGER_EMBLEM = register("ranger_emblem", new RangerEmblemItem());
	public static final Item WARRIOR_EMBLEM = register("warrior_emblem", new WarriorEmblemItem());
	public static final Item SORCERER_EMBLEM = register("sorcerer_emblem", new SorcererEmblemItem());
	public static final Item AVENGER_EMBLEM = register("avenger_emblem", new AvengerEmblemItem());
	public static final Item NEPTUNE_SHELL = register("neptune_shell", new CelestialShell(true, false, false, false));
	public static final Item MOON_CHARM = register("moon_charm", new CelestialShell(false, true, false, false));
	public static final Item MOON_SHELL = register("moon_shell", new CelestialShell(true, true, false, false));
	public static final Item MOON_STONE = register("moon_stone", new CelestialShell(false, false, false, true));
	public static final Item SUN_STONE = register("sun_stone", new CelestialShell(false, false, true, false));
	public static final Item CELESTIAL_STONE = register("celestial_stone", new CelestialShell(false, false, true, true));
	public static final Item CELESTIAL_SHELL = register("celestial_shell", new CelestialShell(true, true, true, true));

	// Belt
	public static final Item SHACKLE = register("shackle", new ShackleItem());
	public static final Item OBSIDIAN_ROSE = register("obsidian_rose", new AccessoryTerrariaItem());
	public static final Item MAGMA_STONE = register("magma_stone", new AccessoryTerrariaItem());
	public static final Item OBSIDIAN_SKULL = register("obsidian_skull", new ObsidianSkullItem());
	public static final Item MAGMA_SKULL = register("magma_skull", new ObsidianSkullItem());
	public static final Item OBSIDIAN_SKULL_ROSE = register("obsidian_skull_rose", new ObsidianSkullItem());
	public static final Item MOLTEN_SKULL_ROSE = register("molten_skull_rose", new ObsidianSkullItem());
	public static final Item LAVA_CHARM = register("lava_charm", new AccessoryTerrariaItem());
	public static final Item MOLTEN_CHARM = register("molten_charm", new AccessoryTerrariaItem());
	public static final Item LUCKY_HORSESHOE = register("lucky_horseshoe", new AccessoryTerrariaItem());
	public static final Item OBSIDIAN_HORSESHOE = register("obsidian_horseshoe", new AccessoryTerrariaItem());
	public static final Item CLOUD_IN_A_BOTTLE = register("cloud_in_a_bottle", new CloudInABottleItem());
	public static final Item SHINY_RED_BALLOON = register("shiny_red_balloon", new ShinyRedBalloonItem());
	public static final Item CLOUD_IN_A_BALLOON = register("cloud_in_a_balloon", new CloudInABalloonItem());
	public static final Item BUNDLE_OF_BALLOONS = register("bundle_of_balloons", new BundleOfBalloonsItem());
	public static final Item BLUE_HORSESHOE_BALLOON = register("blue_horseshoe_balloon", new BlueHorseshoeBalloonItem());
	public static final Item TOOLBELT = register("toolbelt", new ToolbeltItem());
	public static final Item TOOLBOX = register("toolbox", new ToolboxItem());
	public static final Item EXTENDO_GRIP = register("extendo_grip", new ExtendoGripItem());
	public static final Item ANCIENT_CHISEL = register("ancient_chisel", new AccessoryTerrariaItem());
	public static final Item TREASURE_MAGNET = register("treasure_magnet", new UniversalAttractorItem());

	// Hands
	public static final Item SHOE_SPIKES = register("shoe_spikes", new AccessoryTerrariaItem());
	public static final Item CLIMBING_CLAWS = register("climbing_claws", new AccessoryTerrariaItem());
	public static final Item TIGER_CLIMBING_GEAR = register("tiger_climbing_gear", new AccessoryTerrariaItem());
	public static final Item TABI = register("tabi", new TabiItem());
	public static final Item BLACK_BELT = register("black_belt", new AccessoryTerrariaItem());
	public static final Item MASTER_NINJA_GEAR = register("master_ninja_gear", new MasterNinjaGearItem());
	public static final Item FERAL_CLAWS = register("feral_claws", new FeralClawsItem());
	public static final Item TITAN_GLOVE = register("titan_glove", new TitanGloveItem());
	public static final Item POWER_GLOVE = register("power_glove", new PowerGloveItem());
	public static final Item MECHANICAL_GLOVE = register("mechanical_glove", new MechanicalGloveItem());
	public static final Item FIRE_GAUNTLET = register("fire_gauntlet", new FireGauntletItem());
	public static final Item BAND_OF_REGENERATION = register("band_of_regeneration", new BandOfRegenerationItem());
	public static final Item PHILOSOPHERS_STONE = register("philosophers_stone", new PhilosophersStoneItem());
	public static final Item CHARM_OF_MYTHS = register("charm_of_myths", new CharmOfMythsItem());
	public static final Item BAND_OF_STARPOWER = register("band_of_starpower", new BandOfStarpowerItem());
	public static final Item MANA_REGENERATION_BAND = register("mana_regeneration_band", new ManaRegenerationBandItem());
	public static final Item MAGIC_CUFFS = register("magic_cuffs", new MagicCuffsItem());

	// Head
	public static final Item DIVING_HELMET = register("diving_helmet", new AccessoryTerrariaItem());
	public static final Item DIVING_GEAR = register("diving_gear", new AccessoryTerrariaItem());

	// Feet
	public static final Item FLIPPERS = register("flippers", new AccessoryTerrariaItem());
	public static final Item AGLET = register("aglet", new AgletItem());
	public static final Item ANKLET = register("anklet", new AnkletItem());
	public static final Item WATER_WALKING_BOOTS = register("water_walking_boots", new WaterWalkingBootsItem());
	public static final Item OBSIDIAN_WATER_WALKING_BOOTS = register("obsidian_water_walking_boots", new ObsidianWaterWalkingBootsItem());
	public static final Item LAVA_WADERS = register("lava_waders", new LavaWadersItem());
	public static final Item ICE_SKATES = register("ice_skates", new AccessoryTerrariaItem());
	public static final Item HERMES_BOOTS = register("hermes_boots", new AccessoryTerrariaItem());
	public static final Item ROCKET_BOOTS = register("rocket_boots", new RocketBootsItem());
	public static final Item SPECTRE_BOOTS = register("spectre_boots", new SpectreBootsItem());
	public static final Item LIGHTNING_BOOTS = register("lightning_boots", new LightningBootsItem());
	public static final Item FROSTSPARK_BOOTS = register("frostspark_boots", new FrostsparkBootsItem());
	public static final Item TERRASPARK_BOOTS = register("terraspark_boots", new TerrasparkBootsItem());
	public static final Item FLOWER_BOOTS = register("flower_boots", new FlowerBootsItem());
	public static final Item FAIRY_BOOTS = register("fairy_boots", new FairyBootsItem());

	// Back
	public static final Item FLEDGLING_WINGS = register("fledgling_wings", new WingsItem(0.5D, 0.025D, 20, 7, ModSoundEvents.WINGS_FLAP));
	public static final Item ANGEL_WINGS = register("angel_wings", new WingsItem(0.5D, 0.05D, 80, 8, ModSoundEvents.WINGS_FLAP));
	public static final Item DEMON_WINGS = register("demon_wings", new WingsItem(0.5D, 0.05D, 80, 9, ModSoundEvents.WINGS_FLAP));
	public static final Item LEAF_WINGS = register("leaf_wings", new WingsItem(0.5D, 0.05D, 80, 10, ModSoundEvents.WINGS_FLAP));

	// Tools
	public static final Item DEMONITE_PICKAXE = register("demonite_pickaxe", new PickaxeItem(TerrariaToolTiers.DEMONITE, 1, -2.8f, new FabricItemSettings()));
	public static final Item DEMONITE_AXE = register("demonite_axe", new AxeItem(TerrariaToolTiers.DEMONITE, 6.0f, -3.1f, new FabricItemSettings()));
	public static final Item DEMONITE_SHOVEL = register("demonite_shovel", new ShovelItem(TerrariaToolTiers.DEMONITE, 1.5f, -3.0f, new FabricItemSettings()));
	public static final Item DEMONITE_HOE = register("demonite_hoe", new HoeItem(TerrariaToolTiers.DEMONITE, -2, -1.0f, new FabricItemSettings()));
	public static final Item CRIMTANE_PICKAXE = register("crimtane_pickaxe", new PickaxeItem(TerrariaToolTiers.CRIMTANE, 1, -2.8f, new FabricItemSettings()));
	public static final Item CRIMTANE_AXE = register("crimtane_axe", new AxeItem(TerrariaToolTiers.CRIMTANE, 6.5f, -3.1f, new FabricItemSettings()));
	public static final Item CRIMTANE_SHOVEL = register("crimtane_shovel", new ShovelItem(TerrariaToolTiers.CRIMTANE, 1.5f, -3.0f, new FabricItemSettings()));
	public static final Item CRIMTANE_HOE = register("crimtane_hoe", new HoeItem(TerrariaToolTiers.CRIMTANE, -2, -1.0f, new FabricItemSettings()));
	public static final Item METEOR_SHAXE = register("meteor_shaxe", new TerrariaShaxeItem(TerrariaToolTiers.METEOR, 7, -3.1f, new FabricItemSettings()));
	public static final Item MOLTEN_PICKAXE = register("molten_pickaxe", new MoltenPickaxeItem(TerrariaToolTiers.MOLTEN, 1, -2.8f, new FabricItemSettings()));
	public static final Item MOLTEN_SHAXE = register("molten_shaxe", new TerrariaShaxeItem(TerrariaToolTiers.MOLTEN, 7.5f, -3.1f, true, new FabricItemSettings()));

	// Weapons
	public static final Item DEMONITE_SWORD = register("demonite_sword", new SwordItem(TerrariaToolTiers.DEMONITE, 3, -2.4f, new FabricItemSettings()));
	public static final Item CRIMTANE_SWORD = register("crimtane_sword", new SwordItem(TerrariaToolTiers.CRIMTANE, 3, -2.4f, new FabricItemSettings()));
	public static final Item PHASEBLADE_WHITE = register("phaseblade_white", new CustomSoundSwordItem(TerrariaToolTiers.METEOR, 3, -1f, ModSoundEvents.PHASEBLADE_SWING, new FabricItemSettings()));
	public static final Item PHASEBLADE_GREEN = register("phaseblade_green", new CustomSoundSwordItem(TerrariaToolTiers.METEOR, 3, -1f, ModSoundEvents.PHASEBLADE_SWING, new FabricItemSettings()));
	public static final Item VOLCANO_SWORD = register("volcano_sword", new VolcanoSwordItem(TerrariaToolTiers.MOLTEN, 4, -2.4f, new FabricItemSettings()));

	// Ranged
	public static final Item SPACE_GUN = register("space_gun", new SpaceGunItem());

	// Throwables
	// Grenades
	public static final Item GRENADE = register("grenade", new GrenadeItem(new FabricItemSettings(), false, false));
	public static final Item STICKY_GRENADE = register("sticky_grenade", new GrenadeItem(new FabricItemSettings(), true, false));
	public static final Item BOUNCY_GRENADE = register("bouncy_grenade", new GrenadeItem(new FabricItemSettings(), false, true));
	// Bombs
	public static final Item BOMB = register("bomb", new BombItem(new FabricItemSettings(), false, false));
	public static final Item STICKY_BOMB = register("sticky_bomb", new BombItem(new FabricItemSettings(), true, false));
	public static final Item BOUNCY_BOMB = register("bouncy_bomb", new BombItem(new FabricItemSettings(), false, true));
	// Dynamite
	public static final Item DYNAMITE = register("dynamite", new DynamiteItem(new FabricItemSettings(), false, false));
	public static final Item STICKY_DYNAMITE = register("sticky_dynamite", new DynamiteItem(new FabricItemSettings(), true, false));
	public static final Item BOUNCY_DYNAMITE = register("bouncy_dynamite", new DynamiteItem(new FabricItemSettings(), false, true));

	// Armours
	// todo: make another register method that registers a full set of armor instead of registering per piece, don't know the best way to do this since the item is different (ShadowArmor, CrimsonArmor)
	public static final Item SHADOW_HELMET = register("shadow_helmet", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item SHADOW_CHESTPLATE = register("shadow_chestplate", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item SHADOW_LEGGINGS = register("shadow_leggings", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item SHADOW_BOOTS = register("shadow_boots", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorItem.Type.BOOTS, new FabricItemSettings()));
	public static final Item ANCIENT_SHADOW_HELMET = register("ancient_shadow_helmet", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item ANCIENT_SHADOW_CHESTPLATE = register("ancient_shadow_chestplate", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item ANCIENT_SHADOW_LEGGINGS = register("ancient_shadow_leggings", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item ANCIENT_SHADOW_BOOTS = register("ancient_shadow_boots", new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorItem.Type.BOOTS, new FabricItemSettings()));
	public static final Item CRIMSON_HELMET = register("crimson_helmet", new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item CRIMSON_CHESTPLATE = register("crimson_chestplate", new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item CRIMSON_LEGGINGS = register("crimson_leggings", new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item CRIMSON_BOOTS = register("crimson_boots", new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorItem.Type.BOOTS, new FabricItemSettings()));
	public static final Item METEOR_HELMET = register("meteor_helmet", new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item METEOR_CHESTPLATE = register("meteor_chestplate", new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item METEOR_LEGGINGS = register("meteor_leggings", new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item METEOR_BOOTS = register("meteor_boots", new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorItem.Type.BOOTS, new FabricItemSettings()));
	public static final Item MOLTEN_HELMET = register("molten_helmet", new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item MOLTEN_CHESTPLATE = register("molten_chestplate", new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item MOLTEN_LEGGINGS = register("molten_leggings", new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item MOLTEN_BOOTS = register("molten_boots", new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorItem.Type.BOOTS, new FabricItemSettings()));

	// Vanity Armours
	public static final Item FAMILIAR_WIG = register("familiar_wig", new FamiliarVanity("familiar_wig", TerrariaArmorMaterials.VANITY, ArmorItem.Type.HELMET, new FabricItemSettings()));
	public static final Item FAMILIAR_SHIRT = register("familiar_shirt", new FamiliarVanity("familiar_shirt", TerrariaArmorMaterials.VANITY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	public static final Item FAMILIAR_PANTS = register("familiar_pants", new FamiliarVanity("familiar_pants", TerrariaArmorMaterials.VANITY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	public static final Item FAMILIAR_SHOES = register("familiar_shoes", new FamiliarVanity("familiar_shoes", TerrariaArmorMaterials.VANITY, ArmorItem.Type.BOOTS, new FabricItemSettings()));
	//public static final Item TOP_HAT = register("top_hat", new TopHatVanity("top_hat", TerrariaArmorMaterials.VANITY, ArmorItem.Type.HELMET, new FabricItemSettings()));



	///////////////// Blocks /////////////////

	// Chests
	public static final Item GOLD_CHEST = register("gold_chest", new BlockItem(ModBlocks.GOLD_CHEST, new FabricItemSettings()));
	public static final Item TRAPPED_GOLD_CHEST = register("trapped_gold_chest", new BlockItem(ModBlocks.TRAPPED_GOLD_CHEST, new FabricItemSettings()));
	public static final Item FROZEN_CHEST = register("frozen_chest", new BlockItem(ModBlocks.FROZEN_CHEST, new FabricItemSettings()));
	public static final Item TRAPPED_FROZEN_CHEST = register("trapped_frozen_chest", new BlockItem(ModBlocks.TRAPPED_FROZEN_CHEST, new FabricItemSettings()));
	public static final Item IVY_CHEST = register("ivy_chest", new BlockItem(ModBlocks.IVY_CHEST, new FabricItemSettings()));
	public static final Item TRAPPED_IVY_CHEST = register("trapped_ivy_chest", new BlockItem(ModBlocks.TRAPPED_IVY_CHEST, new FabricItemSettings()));
	public static final Item SANDSTONE_CHEST = register("sandstone_chest", new BlockItem(ModBlocks.SANDSTONE_CHEST, new FabricItemSettings()));
	public static final Item TRAPPED_SANDSTONE_CHEST = register("trapped_sandstone_chest", new BlockItem(ModBlocks.TRAPPED_SANDSTONE_CHEST, new FabricItemSettings()));
	public static final Item WATER_CHEST = register("water_chest", new BlockItem(ModBlocks.WATER_CHEST, new FabricItemSettings()));
	public static final Item SKYWARE_CHEST = register("skyware_chest", new BlockItem(ModBlocks.SKYWARE_CHEST, new FabricItemSettings()));
	public static final Item SHADOW_CHEST = register("shadow_chest", new BlockItem(ModBlocks.SHADOW_CHEST, new FabricItemSettings()));
	public static final Item PIGGY_BANK = register("piggy_bank", new BlockItem(ModBlocks.PIGGY_BANK, new FabricItemSettings()));
	public static final Item SAFE = register("safe", new BlockItem(ModBlocks.SAFE, new FabricItemSettings()));

	// Metals
	public static final Item METEORITE_ORE = register("meteorite_ore", new BlockItem(ModBlocks.METEORITE_ORE, new FabricItemSettings()));
	public static final Item RAW_METEORITE_BLOCK = register("raw_meteorite_block", new BlockItem(ModBlocks.RAW_METEORITE_BLOCK, new FabricItemSettings()));
	public static final Item METEORITE_BLOCK = register("meteorite_block", new BlockItem(ModBlocks.METEORITE_BLOCK, new FabricItemSettings()));
	public static final Item DEMONITE_ORE = register("demonite_ore", new BlockItem(ModBlocks.DEMONITE_ORE, new FabricItemSettings()));
	public static final Item DEEPSLATE_DEMONITE_ORE = register("deepslate_demonite_ore", new BlockItem(ModBlocks.DEEPSLATE_DEMONITE_ORE, new FabricItemSettings()));
	public static final Item RAW_DEMONITE_BLOCK = register("raw_demonite_block", new BlockItem(ModBlocks.RAW_DEMONITE_BLOCK, new FabricItemSettings()));
	public static final Item DEMONITE_BLOCK = register("demonite_block", new BlockItem(ModBlocks.DEMONITE_BLOCK, new FabricItemSettings()));
	public static final Item CRIMTANE_ORE = register("crimtane_ore", new BlockItem(ModBlocks.CRIMTANE_ORE, new FabricItemSettings()));
	public static final Item DEEPSLATE_CRIMTANE_ORE = register("deepslate_crimtane_ore", new BlockItem(ModBlocks.DEEPSLATE_CRIMTANE_ORE, new FabricItemSettings()));
	public static final Item RAW_CRIMTANE_BLOCK = register("raw_crimtane_block", new BlockItem(ModBlocks.RAW_CRIMTANE_BLOCK, new FabricItemSettings()));
	public static final Item CRIMTANE_BLOCK = register("crimtane_block", new BlockItem(ModBlocks.CRIMTANE_BLOCK, new FabricItemSettings()));
	public static final Item HELLSTONE_ORE = register("hellstone_ore", new BlockItem(ModBlocks.HELLSTONE_ORE, new FabricItemSettings().fireproof()));
	public static final Item RAW_HELLSTONE_BLOCK = register("raw_hellstone_block", new BlockItem(ModBlocks.RAW_HELLSTONE_BLOCK, new FabricItemSettings().fireproof()));
	public static final Item HELLSTONE_BLOCK = register("hellstone_block", new BlockItem(ModBlocks.HELLSTONE_BLOCK, new FabricItemSettings().fireproof()));

	// Misc
	public static final Item REDSTONE_STONE = register("redstone_stone", new BlockItem(ModBlocks.REDSTONE_STONE, new FabricItemSettings()));
	public static final Item REDSTONE_DEEPSLATE = register("redstone_deepslate", new BlockItem(ModBlocks.REDSTONE_DEEPSLATE, new FabricItemSettings()));
	public static final Item INSTANT_TNT = register("instant_tnt", new BlockItem(ModBlocks.INSTANT_TNT, new FabricItemSettings()));
	public static final Item TINKERER_TABLE = register("tinkerer_workshop", new BlockItem(ModBlocks.TINKERER_TABLE, new FabricItemSettings()));

	// Building
	public static final Item SUNPLATE_BLOCK = register("sunplate_block", new BlockItem(ModBlocks.SUNPLATE_BLOCK, new FabricItemSettings()));
	public static final Item CLOUD = register("cloud", new BlockItem(ModBlocks.CLOUD, new FabricItemSettings()));
	public static final Item RAIN_CLOUD = register("rain_cloud", new BlockItem(ModBlocks.RAIN_CLOUD, new FabricItemSettings()));
	public static final Item BLUE_BRICKS = register("blue_brick", new BlockItem(ModBlocks.BLUE_BRICKS, new FabricItemSettings()));
	public static final Item CRACKED_BLUE_BRICKS = register("cracked_blue_brick", new BlockItem(ModBlocks.CRACKED_BLUE_BRICKS, new FabricItemSettings()));
	public static final Item FANCY_BLUE_BRICKS = register("fancy_blue_brick", new BlockItem(ModBlocks.FANCY_BLUE_BRICKS, new FabricItemSettings()));
	public static final Item GREEN_BRICKS = register("green_brick", new BlockItem(ModBlocks.GREEN_BRICKS, new FabricItemSettings()));
	public static final Item CRACKED_GREEN_BRICKS = register("cracked_green_brick", new BlockItem(ModBlocks.CRACKED_GREEN_BRICKS, new FabricItemSettings()));
	public static final Item FANCY_GREEN_BRICKS = register("fancy_green_brick", new BlockItem(ModBlocks.FANCY_GREEN_BRICKS, new FabricItemSettings()));
	public static final Item PURPLE_BRICKS = register("purple_brick", new BlockItem(ModBlocks.PURPLE_BRICKS, new FabricItemSettings()));
	public static final Item CRACKED_PURPLE_BRICKS = register("cracked_purple_brick", new BlockItem(ModBlocks.CRACKED_PURPLE_BRICKS, new FabricItemSettings()));
	public static final Item FANCY_PURPLE_BRICKS = register("fancy_purple_brick", new BlockItem(ModBlocks.FANCY_PURPLE_BRICKS, new FabricItemSettings()));

	// Vegetation
	public static final Item VILE_MUSHROOM = registerPlant("vile_mushroom", 0.65f, new BlockItem(ModBlocks.VILE_MUSHROOM, new FabricItemSettings()));
	public static final Item VICIOUS_MUSHROOM = registerPlant("vicious_mushroom", 0.65f, new BlockItem(ModBlocks.VICIOUS_MUSHROOM, new FabricItemSettings()));

	// Corruption
	public static final Item CORRUPTED_GRASS_BLOCK = register("corrupted_grass", new BlockItem(ModBlocks.CORRUPTED_GRASS, new FabricItemSettings()));
	public static final Item CORRUPTED_GRAVEL = register("corrupted_gravel", new BlockItem(ModBlocks.CORRUPTED_GRAVEL, new FabricItemSettings()));
	public static final Item CORRUPTED_SAND = register("corrupted_sand", new BlockItem(ModBlocks.CORRUPTED_SAND, new FabricItemSettings()));
	public static final Item CORRUPTED_GLASS = register("corrupted_glass", new BlockItem(ModBlocks.CORRUPTED_GLASS, new FabricItemSettings()));
	public static final Item CORRUPTED_SANDSTONE = register("corrupted_sandstone", new BlockItem(ModBlocks.CORRUPTED_SANDSTONE, new FabricItemSettings()));
	public static final Item CORRUPTED_ANDESITE = register("corrupted_andesite", new BlockItem(ModBlocks.CORRUPTED_ANDESITE, new FabricItemSettings()));
	public static final Item CORRUPTED_DIORITE = register("corrupted_diorite", new BlockItem(ModBlocks.CORRUPTED_DIORITE, new FabricItemSettings()));
	public static final Item CORRUPTED_GRANITE = register("corrupted_granite", new BlockItem(ModBlocks.CORRUPTED_GRANITE, new FabricItemSettings()));
	public static final Item CORRUPTED_STONE = register("corrupted_stone", new BlockItem(ModBlocks.CORRUPTED_STONE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE = register("corrupted_deepslate", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE, new FabricItemSettings()));
	public static final Item CORRUPTED_COBBLESTONE = register("corrupted_cobblestone", new BlockItem(ModBlocks.CORRUPTED_COBBLESTONE, new FabricItemSettings()));
	public static final Item CORRUPTED_COBBLED_DEEPSLATE = register("corrupted_cobbled_deepslate", new BlockItem(ModBlocks.CORRUPTED_COBBLED_DEEPSLATE, new FabricItemSettings()));
	public static final Item CORRUPTED_COAL_ORE = register("corrupted_coal_ore", new BlockItem(ModBlocks.CORRUPTED_COAL_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_IRON_ORE = register("corrupted_iron_ore", new BlockItem(ModBlocks.CORRUPTED_IRON_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_COPPER_ORE = register("corrupted_copper_ore", new BlockItem(ModBlocks.CORRUPTED_COPPER_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_GOLD_ORE = register("corrupted_gold_ore", new BlockItem(ModBlocks.CORRUPTED_GOLD_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_LAPIS_ORE = register("corrupted_lapis_ore", new BlockItem(ModBlocks.CORRUPTED_LAPIS_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_REDSTONE_ORE = register("corrupted_redstone_ore", new BlockItem(ModBlocks.CORRUPTED_REDSTONE_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DIAMOND_ORE = register("corrupted_diamond_ore", new BlockItem(ModBlocks.CORRUPTED_DIAMOND_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_EMERALD_ORE = register("corrupted_emerald_ore", new BlockItem(ModBlocks.CORRUPTED_EMERALD_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_COAL_ORE = register("corrupted_deepslate_coal_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_COAL_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_IRON_ORE = register("corrupted_deepslate_iron_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_IRON_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_COPPER_ORE = register("corrupted_deepslate_copper_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_COPPER_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_GOLD_ORE = register("corrupted_deepslate_gold_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_GOLD_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_LAPIS_ORE = register("corrupted_deepslate_lapis_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_LAPIS_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_REDSTONE_ORE = register("corrupted_deepslate_redstone_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_REDSTONE_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_DIAMOND_ORE = register("corrupted_deepslate_diamond_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_DIAMOND_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_DEEPSLATE_EMERALD_ORE = register("corrupted_deepslate_emerald_ore", new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_EMERALD_ORE, new FabricItemSettings()));
	public static final Item CORRUPTED_SNOW_LAYER = register("corrupted_snow_layer", new BlockItem(ModBlocks.CORRUPTED_SNOW_LAYER, new FabricItemSettings()));
	public static final Item CORRUPTED_SNOW_BLOCK = register("corrupted_snow", new BlockItem(ModBlocks.CORRUPTED_SNOW, new FabricItemSettings()));
	public static final Item CORRUPTED_ICE = register("corrupted_ice", new BlockItem(ModBlocks.CORRUPTED_ICE, new FabricItemSettings()));
	public static final Item CORRUPTED_PACKED_ICE = register("corrupted_packed_ice", new BlockItem(ModBlocks.CORRUPTED_PACKED_ICE, new FabricItemSettings()));
	public static final Item CORRUPTED_BLUE_ICE = register("corrupted_blue_ice", new BlockItem(ModBlocks.CORRUPTED_BLUE_ICE, new FabricItemSettings()));

	// Crimson
	public static final Item CRIMSON_GRASS_BLOCK = register("crimson_grass", new BlockItem(ModBlocks.CRIMSON_GRASS, new FabricItemSettings()));
	public static final Item CRIMSON_GRAVEL = register("crimson_gravel", new BlockItem(ModBlocks.CRIMSON_GRAVEL, new FabricItemSettings()));
	public static final Item CRIMSON_SAND = register("crimson_sand", new BlockItem(ModBlocks.CRIMSON_SAND, new FabricItemSettings()));
	public static final Item CRIMSON_GLASS = register("crimson_glass", new BlockItem(ModBlocks.CRIMSON_GLASS, new FabricItemSettings()));
	public static final Item CRIMSON_SANDSTONE = register("crimson_sandstone", new BlockItem(ModBlocks.CRIMSON_SANDSTONE, new FabricItemSettings()));
	public static final Item CRIMSON_ANDESITE = register("crimson_andesite", new BlockItem(ModBlocks.CRIMSON_ANDESITE, new FabricItemSettings()));
	public static final Item CRIMSON_DIORITE = register("crimson_diorite", new BlockItem(ModBlocks.CRIMSON_DIORITE, new FabricItemSettings()));
	public static final Item CRIMSON_GRANITE = register("crimson_granite", new BlockItem(ModBlocks.CRIMSON_GRANITE, new FabricItemSettings()));
	public static final Item CRIMSON_STONE = register("crimson_stone", new BlockItem(ModBlocks.CRIMSON_STONE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE = register("crimson_deepslate", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE, new FabricItemSettings()));
	public static final Item CRIMSON_COBBLESTONE = register("crimson_cobblestone", new BlockItem(ModBlocks.CRIMSON_COBBLESTONE, new FabricItemSettings()));
	public static final Item CRIMSON_COBBLED_DEEPSLATE = register("crimson_cobbled_deepslate", new BlockItem(ModBlocks.CRIMSON_COBBLED_DEEPSLATE, new FabricItemSettings()));
	public static final Item CRIMSON_COAL_ORE = register("crimson_coal_ore", new BlockItem(ModBlocks.CRIMSON_COAL_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_IRON_ORE = register("crimson_iron_ore", new BlockItem(ModBlocks.CRIMSON_IRON_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_COPPER_ORE = register("crimson_copper_ore", new BlockItem(ModBlocks.CRIMSON_COPPER_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_GOLD_ORE = register("crimson_gold_ore", new BlockItem(ModBlocks.CRIMSON_GOLD_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_LAPIS_ORE = register("crimson_lapis_ore", new BlockItem(ModBlocks.CRIMSON_LAPIS_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_REDSTONE_ORE = register("crimson_redstone_ore", new BlockItem(ModBlocks.CRIMSON_REDSTONE_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DIAMOND_ORE = register("crimson_diamond_ore", new BlockItem(ModBlocks.CRIMSON_DIAMOND_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_EMERALD_ORE = register("crimson_emerald_ore", new BlockItem(ModBlocks.CRIMSON_EMERALD_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_COAL_ORE = register("crimson_deepslate_coal_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_COAL_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_IRON_ORE = register("crimson_deepslate_iron_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_IRON_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_COPPER_ORE = register("crimson_deepslate_copper_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_COPPER_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_GOLD_ORE = register("crimson_deepslate_gold_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_GOLD_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_LAPIS_ORE = register("crimson_deepslate_lapis_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_LAPIS_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_REDSTONE_ORE = register("crimson_deepslate_redstone_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_REDSTONE_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_DIAMOND_ORE = register("crimson_deepslate_diamond_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_DIAMOND_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_DEEPSLATE_EMERALD_ORE = register("crimson_deepslate_emerald_ore", new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_EMERALD_ORE, new FabricItemSettings()));
	public static final Item CRIMSON_SNOW_LAYER = register("crimson_snow_layer", new BlockItem(ModBlocks.CRIMSON_SNOW_LAYER, new FabricItemSettings()));
	public static final Item CRIMSON_SNOW_BLOCK = register("crimson_snow", new BlockItem(ModBlocks.CRIMSON_SNOW, new FabricItemSettings()));
	public static final Item CRIMSON_ICE = register("crimson_ice", new BlockItem(ModBlocks.CRIMSON_ICE, new FabricItemSettings()));
	public static final Item CRIMSON_PACKED_ICE = register("crimson_packed_ice", new BlockItem(ModBlocks.CRIMSON_PACKED_ICE, new FabricItemSettings()));
	public static final Item CRIMSON_BLUE_ICE = register("crimson_blue_ice", new BlockItem(ModBlocks.CRIMSON_BLUE_ICE, new FabricItemSettings()));

	private static Item register(String name, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, TerraMine.id(name), item);
	}

	private static Item registerPlant(String name, float chance, Item item) {
		ComposterBlock.COMPOSTABLES.put(item, chance);
		return Registry.register(BuiltInRegistries.ITEM, TerraMine.id(name), item);
	}
}
