package terramine.common.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.ComposterBlock;
import terramine.TerraMine;
import terramine.common.item.CraftingItem;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.item.accessories.ShieldAccessoryLikeItem;
import terramine.common.item.accessories.ShieldOfCthulhuItem;
import terramine.common.item.accessories.WhoopeeCushionItem;
import terramine.common.item.accessories.back.WingsItem;
import terramine.common.item.accessories.belt.*;
import terramine.common.item.accessories.feet.*;
import terramine.common.item.accessories.hands.*;
import terramine.common.item.accessories.head.DivingGear;
import terramine.common.item.accessories.necklace.*;
import terramine.common.item.armor.*;
import terramine.common.item.armor.vanity.FamiliarVanity;
import terramine.common.item.dye.BasicDye;
import terramine.common.item.equipment.CellPhoneItem;
import terramine.common.item.equipment.MagicMirrorItem;
import terramine.common.item.equipment.TerrariaToolMaterials;
import terramine.common.item.equipment.UmbrellaItem;
import terramine.common.item.equipment.swords.CustomSoundSwordItem;
import terramine.common.item.equipment.swords.VolcanoSwordItem;
import terramine.common.item.equipment.tools.MoltenPickaxeItem;
import terramine.common.item.equipment.tools.TerrariaShaxeItem;
import terramine.common.item.magic.*;
import terramine.common.item.misc.BossSpawnItem;
import terramine.common.item.misc.DemonHeartItem;
import terramine.common.item.misc.EvilSeeds;
import terramine.common.item.misc.TreasureBagItem;
import terramine.common.item.projectiles.arrows.FlamingArrowItem;
import terramine.common.item.projectiles.arrows.JesterArrowItem;
import terramine.common.item.projectiles.arrows.UnholyArrowItem;
import terramine.common.item.projectiles.throwables.BombItem;
import terramine.common.item.projectiles.throwables.DynamiteItem;
import terramine.common.item.projectiles.throwables.GrenadeItem;

import java.util.function.Function;

@SuppressWarnings("unused")
public class ModItems {

	// Misc
	public static final Item MIMIC_SPAWN_EGG = register("mimic_spawn_egg", key -> new SpawnEggItem(ModEntities.MIMIC, 0x805113, 0x212121, new Item.Properties().setId(key)));
	public static final Item DEMON_EYE_SPAWN_EGG = register("demon_eye_spawn_egg", key -> new SpawnEggItem(ModEntities.DEMON_EYE, 0xffffff, 0xff0000, new Item.Properties().setId(key)));
	public static final Item EATER_OF_SOULS_SPAWN_EGG = register("eater_of_souls_spawn_egg", key -> new SpawnEggItem(ModEntities.EATER_OF_SOULS, 0x735c5f, 0x999190, new Item.Properties().setId(key)));
	public static final Item DEVOURER_SPAWN_EGG = register("devourer_spawn_egg", key -> new SpawnEggItem(ModEntities.DEVOURER, 0x999190, 0x735c5f, new Item.Properties().setId(key)));
	public static final Item CRIMERA_SPAWN_EGG = register("crimera_spawn_egg", key -> new SpawnEggItem(ModEntities.CRIMERA, 0x72261f, 0xac524d, new Item.Properties().setId(key)));
	public static final Item SUSPICIOUS_LOOKING_EYE = register("suspicious_looking_eye", key -> new BossSpawnItem(ModEntities.TEST_BOSS, new Item.Properties().setId(key).stacksTo(16).rarity(Rarity.RARE)));
	public static final Item UMBRELLA = register("umbrella", UmbrellaItem::new);
	public static final Item WHOOPEE_CUSHION = register("whoopee_cushion", WhoopeeCushionItem::new);
	public static final Item MAGIC_MIRROR = register("magic_mirror", MagicMirrorItem::new);
	public static final Item COBALT_SHIELD = register("cobalt_shield", key -> new ShieldAccessoryLikeItem(new Item.Properties().setId(key).repairable(Items.DIAMOND).durability(2500).fireResistant().rarity(Rarity.RARE)));
	public static final Item OBSIDIAN_SHIELD = register("obsidian_shield", key -> new ShieldAccessoryLikeItem(new Item.Properties().setId(key).repairable(Items.OBSIDIAN).durability(2500).fireResistant().rarity(Rarity.RARE)));
	public static final Item SHIELD_OF_CTHULHU = register("shield_of_cthulhu", key -> new ShieldOfCthulhuItem(new Item.Properties().setId(key).repairable(Items.ROTTEN_FLESH).durability(2500).fireResistant().rarity(Rarity.RARE)));
	public static final Item CORRUPT_SEEDS = register("corrupt_seeds", key -> new EvilSeeds(new Item.Properties().setId(key), ModBlocks.CORRUPTED_GRASS));
	public static final Item CRIMSON_SEEDS = register("crimson_seeds", key -> new EvilSeeds(new Item.Properties().setId(key), ModBlocks.CRIMSON_GRASS));

	// todo: add many more dyes, need to create a model/item for each one but its just copy paste
	// todo: also add some custom shader dyes, need to add a system to render the dyes first though
	// todo: make dye craft-able, some will also be obtainable from enemies and other things
	// todo: maybe also make work like potions, so only one item is registered and doesn't need a model per item, maybe
	// Dye Items (uses hex)
	public static final Item RED_DYE = register("red_dye", key -> new BasicDye(0xFF0000, key));
	public static final Item GREEN_DYE = register("green_dye", key -> new BasicDye(0x008000, key));
	public static final Item BLUE_DYE = register("blue_dye", key -> new BasicDye(0x0000FF, key));
	public static final Item YELLOW_DYE = register("yellow_dye", key -> new BasicDye(0xFFFF00, key));
	public static final Item ORANGE_DYE = register("orange_dye", key -> new BasicDye(0xFFA500, key));
	public static final Item PURPLE_DYE = register("purple_dye", key -> new BasicDye(0x800080, key));
	public static final Item PINK_DYE = register("pink_dye", key -> new BasicDye(0xFFC0CB, key));
	public static final Item BROWN_DYE = register("brown_dye", key -> new BasicDye(0x964B00, key));
	public static final Item GRAY_DYE = register("gray_dye", key -> new BasicDye(0x808080, key));
	public static final Item BLACK_DYE = register("black_dye", key -> new BasicDye(0x000000, key));

	// Arrows
	// todo: make use just a single arrow item class, need to make all arrows the same entity for this, can be done much later
	public static final Item FLAMING_ARROW = register("flaming_arrow", key -> new FlamingArrowItem(new Item.Properties().setId(key)));
	public static final Item UNHOLY_ARROW = register("unholy_arrow", key -> new UnholyArrowItem(new Item.Properties().setId(key)));
	public static final Item JESTER_ARROW = register("jester_arrow", key -> new JesterArrowItem(new Item.Properties().setId(key)));

	// Treasure Bags
	public static final Item EYE_OF_CTHULHU_TREASURE_BAG = register("eye_of_cthulhu_treasure_bag", key -> new TreasureBagItem(new Item.Properties().setId(key).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ModLootTables.EYE_OF_CTHULHU, Component.translatable("terramine.ui.eye_of_cthulhu_treasure_bag")));

	// Crafting Items
	public static final Item LENS = register("lens", key -> new CraftingItem(new Item.Properties().setId(key), false));
	public static final Item BLACK_LENS = register("black_lens", key -> new CraftingItem(new Item.Properties().setId(key), false));
	public static final Item ROTTEN_CHUNK = register("rotten_chunk", key -> new CraftingItem(new Item.Properties().setId(key).stacksTo(64), true));
	public static final Item VERTEBRA = register("vertebra", key -> new CraftingItem(new Item.Properties().setId(key).stacksTo(64), false));
	public static final Item WORM_TOOTH = register("worm_tooth", key -> new CraftingItem(new Item.Properties().setId(key).stacksTo(64), false));

	// Ores etc
	public static final Item RAW_METEORITE = register("raw_meteorite", key -> new CraftingItem(new Item.Properties().setId(key), false));
	public static final Item METEORITE_INGOT = register("meteorite_ingot", key -> new CraftingItem(new Item.Properties().setId(key), true));
	public static final Item RAW_DEMONITE = register("raw_demonite", key -> new CraftingItem(new Item.Properties().setId(key), false));
	public static final Item DEMONITE_INGOT = register("demonite_ingot", key -> new CraftingItem(new Item.Properties().setId(key), true));
	public static final Item RAW_CRIMTANE = register("raw_crimtane", key -> new CraftingItem(new Item.Properties().setId(key), false));
	public static final Item CRIMTANE_INGOT = register("crimtane_ingot", key -> new CraftingItem(new Item.Properties().setId(key), false));
	public static final Item RAW_HELLSTONE = register("raw_hellstone", key -> new CraftingItem(new Item.Properties().setId(key).fireResistant(), true));
	public static final Item RAW_HELLSTONE_HARDENED = register("raw_hellstone_hardened", key -> new CraftingItem(new Item.Properties().setId(key).fireResistant(), true));
	public static final Item HELLSTONE_INGOT = register("hellstone_ingot", key -> new CraftingItem(new Item.Properties().setId(key).fireResistant(), true));

	// Magic Items
	public static final Item MAGIC_MISSILE_ITEM = register("magic_missile", key -> new MagicMissileItem(key));
	public static final Item FLAMELASH_ITEM = register("flamelash", key -> new FlamelashItem(key));
	public static final Item RAINBOW_ROD_ITEM = register("rainbow_rod", key -> new RainbowRodItem(key));
	public static final Item FAKE_FALLEN_STAR = register("fake_fallen_star", key -> new CraftingItem(new Item.Properties().setId(key), true));
	public static final Item FALLEN_STAR = register("fallen_star", key -> new CraftingItem(new Item.Properties().setId(key).stacksTo(64).rarity(Rarity.UNCOMMON), true));
	public static final Item MANA_CRYSTAL = register("mana_crystal", key -> new ManaCrystalItem(new Item.Properties().setId(key).stacksTo(64).rarity(Rarity.RARE)));
	public static final Item DEMON_HEART = register("demon_heart", key -> new DemonHeartItem(new Item.Properties().setId(key).stacksTo(16).rarity(Rarity.EPIC)));

	// Informational
	public static final Item GOLD_WATCH = register("gold_watch", AccessoryTerrariaItem::new);
	public static final Item DEPTH_METER = register("depth_meter", AccessoryTerrariaItem::new);
	public static final Item COMPASS = register("compass", AccessoryTerrariaItem::new);
	public static final Item GPS = register("gps", AccessoryTerrariaItem::new);
	public static final Item WEATHER_RADIO = register("weather_radio", AccessoryTerrariaItem::new);
	public static final Item SEXTANT = register("sextant", AccessoryTerrariaItem::new);
	public static final Item FISH_FINDER = register("fish_finder", AccessoryTerrariaItem::new);
	public static final Item METAL_DETECTOR = register("metal_detector", AccessoryTerrariaItem::new);
	public static final Item STOPWATCH = register("stopwatch", AccessoryTerrariaItem::new);
	public static final Item DPS_METER = register("dps_meter", AccessoryTerrariaItem::new);
	public static final Item GOBLIN_TECH = register("goblin_tech", AccessoryTerrariaItem::new);
	public static final Item PDA = register("pda", AccessoryTerrariaItem::new);
	public static final Item CELL_PHONE = register("cell_phone", CellPhoneItem::new);

	// Necklace
	public static final Item CROSS_NECKLACE = register("cross_necklace", CrossNecklaceItem::new);
	public static final Item PANIC_NECKLACE = register("panic_necklace", PanicNecklaceItem::new);
	public static final Item RANGER_EMBLEM = register("ranger_emblem", RangerEmblemItem::new);
	public static final Item WARRIOR_EMBLEM = register("warrior_emblem", WarriorEmblemItem::new);
	public static final Item SORCERER_EMBLEM = register("sorcerer_emblem", SorcererEmblemItem::new);
	public static final Item AVENGER_EMBLEM = register("avenger_emblem", AvengerEmblemItem::new);
	public static final Item NEPTUNE_SHELL = register("neptune_shell", key -> new CelestialShell(true, false, false, false, key));
	public static final Item MOON_CHARM = register("moon_charm", key -> new CelestialShell(false, true, false, false, key));
	public static final Item MOON_SHELL = register("moon_shell", key -> new CelestialShell(true, true, false, false, key));
	public static final Item MOON_STONE = register("moon_stone", key -> new CelestialShell(false, false, false, true, key));
	public static final Item SUN_STONE = register("sun_stone", key -> new CelestialShell(false, false, true, false, key));
	public static final Item CELESTIAL_STONE = register("celestial_stone", key -> new CelestialShell(false, false, true, true, key));
	public static final Item CELESTIAL_SHELL = register("celestial_shell", key -> new CelestialShell(true, true, true, true, key));

	// Belt
	public static final Item SHACKLE = register("shackle", ShackleItem::new);
	public static final Item OBSIDIAN_ROSE = register("obsidian_rose", AccessoryTerrariaItem::new);
	public static final Item MAGMA_STONE = register("magma_stone", AccessoryTerrariaItem::new);
	public static final Item OBSIDIAN_SKULL = register("obsidian_skull", ObsidianSkullItem::new);
	public static final Item MAGMA_SKULL = register("magma_skull", ObsidianSkullItem::new);
	public static final Item OBSIDIAN_SKULL_ROSE = register("obsidian_skull_rose", ObsidianSkullItem::new);
	public static final Item MOLTEN_SKULL_ROSE = register("molten_skull_rose", ObsidianSkullItem::new);
	public static final Item LAVA_CHARM = register("lava_charm", AccessoryTerrariaItem::new);
	public static final Item MOLTEN_CHARM = register("molten_charm", AccessoryTerrariaItem::new);
	public static final Item LUCKY_HORSESHOE = register("lucky_horseshoe", AccessoryTerrariaItem::new);
	public static final Item OBSIDIAN_HORSESHOE = register("obsidian_horseshoe", AccessoryTerrariaItem::new);
	public static final Item CLOUD_IN_A_BOTTLE = register("cloud_in_a_bottle", CloudInABottleItem::new);
	public static final Item SHINY_RED_BALLOON = register("shiny_red_balloon", ShinyRedBalloonItem::new);
	public static final Item CLOUD_IN_A_BALLOON = register("cloud_in_a_balloon", CloudInABalloonItem::new);
	public static final Item BUNDLE_OF_BALLOONS = register("bundle_of_balloons", BundleOfBalloonsItem::new);
	public static final Item BLUE_HORSESHOE_BALLOON = register("blue_horseshoe_balloon", BlueHorseshoeBalloonItem::new);
	public static final Item TOOLBELT = register("toolbelt", ToolbeltItem::new);
	public static final Item TOOLBOX = register("toolbox", ToolboxItem::new);
	public static final Item EXTENDO_GRIP = register("extendo_grip", ExtendoGripItem::new);
	public static final Item ANCIENT_CHISEL = register("ancient_chisel", AccessoryTerrariaItem::new);
	public static final Item TREASURE_MAGNET = register("treasure_magnet", UniversalAttractorItem::new);

	// Hands
	public static final Item SHOE_SPIKES = register("shoe_spikes", AccessoryTerrariaItem::new);
	public static final Item CLIMBING_CLAWS = register("climbing_claws", AccessoryTerrariaItem::new);
	public static final Item TIGER_CLIMBING_GEAR = register("tiger_climbing_gear", AccessoryTerrariaItem::new);
	public static final Item TABI = register("tabi", TabiItem::new);
	public static final Item BLACK_BELT = register("black_belt", AccessoryTerrariaItem::new);
	public static final Item MASTER_NINJA_GEAR = register("master_ninja_gear", MasterNinjaGearItem::new);
	public static final Item FERAL_CLAWS = register("feral_claws", FeralClawsItem::new);
	public static final Item TITAN_GLOVE = register("titan_glove", TitanGloveItem::new);
	public static final Item POWER_GLOVE = register("power_glove", PowerGloveItem::new);
	public static final Item MECHANICAL_GLOVE = register("mechanical_glove", MechanicalGloveItem::new);
	public static final Item FIRE_GAUNTLET = register("fire_gauntlet", FireGauntletItem::new);
	public static final Item BAND_OF_REGENERATION = register("band_of_regeneration", BandOfRegenerationItem::new);
	public static final Item PHILOSOPHERS_STONE = register("philosophers_stone", PhilosophersStoneItem::new);
	public static final Item CHARM_OF_MYTHS = register("charm_of_myths", CharmOfMythsItem::new);
	public static final Item BAND_OF_STARPOWER = register("band_of_starpower", BandOfStarpowerItem::new);
	public static final Item MANA_REGENERATION_BAND = register("mana_regeneration_band", ManaRegenerationBandItem::new);
	public static final Item MAGIC_CUFFS = register("magic_cuffs", MagicCuffsItem::new);

	// Head
	public static final Item DIVING_HELMET = register("diving_helmet", DivingGear::new);
	public static final Item DIVING_GEAR = register("diving_gear", DivingGear::new);

	// Feet
	public static final Item FLIPPERS = register("flippers", AccessoryTerrariaItem::new);
	public static final Item AGLET = register("aglet", AgletItem::new);
	public static final Item ANKLET = register("anklet", AnkletItem::new);
	public static final Item WATER_WALKING_BOOTS = register("water_walking_boots", WaterWalkingBootsItem::new);
	public static final Item OBSIDIAN_WATER_WALKING_BOOTS = register("obsidian_water_walking_boots", ObsidianWaterWalkingBootsItem::new);
	public static final Item LAVA_WADERS = register("lava_waders", LavaWadersItem::new);
	public static final Item ICE_SKATES = register("ice_skates", AccessoryTerrariaItem::new);
	public static final Item HERMES_BOOTS = register("hermes_boots", AccessoryTerrariaItem::new);
	public static final Item ROCKET_BOOTS = register("rocket_boots", RocketBootsItem::new);
	public static final Item SPECTRE_BOOTS = register("spectre_boots", SpectreBootsItem::new);
	public static final Item LIGHTNING_BOOTS = register("lightning_boots", LightningBootsItem::new);
	public static final Item FROSTSPARK_BOOTS = register("frostspark_boots", FrostsparkBootsItem::new);
	public static final Item TERRASPARK_BOOTS = register("terraspark_boots", TerrasparkBootsItem::new);
	public static final Item FLOWER_BOOTS = register("flower_boots", FlowerBootsItem::new);
	public static final Item FAIRY_BOOTS = register("fairy_boots", FairyBootsItem::new);

	// Back
	public static final Item FLEDGLING_WINGS = register("fledgling_wings", key -> new WingsItem(0.5D, 0.025D, 20, 7, ModSoundEvents.WINGS_FLAP, key));
	public static final Item ANGEL_WINGS = register("angel_wings", key -> new WingsItem(0.5D, 0.05D, 80, 8, ModSoundEvents.WINGS_FLAP, key));
	public static final Item DEMON_WINGS = register("demon_wings", key -> new WingsItem(0.5D, 0.05D, 80, 9, ModSoundEvents.WINGS_FLAP, key));
	public static final Item LEAF_WINGS = register("leaf_wings", key -> new WingsItem(0.5D, 0.05D, 80, 10, ModSoundEvents.WINGS_FLAP, key));

	// Tools
	public static final Item DEMONITE_PICKAXE = register("demonite_pickaxe", key -> new PickaxeItem(TerrariaToolMaterials.DEMONITE, 1F, -2.8F, new Item.Properties().setId(key)));
	public static final Item DEMONITE_AXE = register("demonite_axe", key -> new AxeItem(TerrariaToolMaterials.DEMONITE, 6F, -3.1F, new Item.Properties().setId(key)));
	public static final Item DEMONITE_SHOVEL = register("demonite_shovel", key -> new ShovelItem(TerrariaToolMaterials.DEMONITE, 1.5F, -3F, new Item.Properties().setId(key)));
	public static final Item DEMONITE_HOE = register("demonite_hoe", key -> new HoeItem(TerrariaToolMaterials.DEMONITE, -2F, -1F, new Item.Properties().setId(key)));
	public static final Item CRIMTANE_PICKAXE = register("crimtane_pickaxe", key -> new PickaxeItem(TerrariaToolMaterials.CRIMTANE, 1F, -2.8F, new Item.Properties().setId(key)));
	public static final Item CRIMTANE_AXE = register("crimtane_axe", key -> new AxeItem(TerrariaToolMaterials.CRIMTANE, 6.5F, -3.1F, new Item.Properties().setId(key)));
	public static final Item CRIMTANE_SHOVEL = register("crimtane_shovel", key -> new ShovelItem(TerrariaToolMaterials.CRIMTANE, 1.5F, -3F, new Item.Properties().setId(key)));
	public static final Item CRIMTANE_HOE = register("crimtane_hoe", key -> new HoeItem(TerrariaToolMaterials.CRIMTANE, -2, -1F, new Item.Properties().setId(key)));
	public static final Item METEOR_SHAXE = register("meteor_shaxe", key -> new TerrariaShaxeItem(TerrariaToolMaterials.METEOR, 7F, -3.1F, new Item.Properties().setId(key)));
	public static final Item MOLTEN_PICKAXE = register("molten_pickaxe", key -> new MoltenPickaxeItem(TerrariaToolMaterials.MOLTEN, 1F, -2.8F, new Item.Properties().setId(key).fireResistant()));
	public static final Item MOLTEN_SHAXE = register("molten_shaxe", key -> new TerrariaShaxeItem(TerrariaToolMaterials.MOLTEN, true, 7.5F, -3.1F, new Item.Properties().setId(key).fireResistant()));

	// Weapons
	public static final Item DEMONITE_SWORD = register("demonite_sword", key -> new SwordItem(TerrariaToolMaterials.DEMONITE, 3F, -2.4F, new Item.Properties().setId(key)));
	public static final Item CRIMTANE_SWORD = register("crimtane_sword", key -> new SwordItem(TerrariaToolMaterials.CRIMTANE, 3F, -2.4F, new Item.Properties().setId(key)));
	public static final Item PHASEBLADE_WHITE = register("phaseblade_white", key -> new CustomSoundSwordItem(TerrariaToolMaterials.METEOR, 3F, -1F, ModSoundEvents.PHASEBLADE_SWING, new Item.Properties().setId(key)));
	public static final Item PHASEBLADE_GREEN = register("phaseblade_green", key -> new CustomSoundSwordItem(TerrariaToolMaterials.METEOR, 3F, -1F, ModSoundEvents.PHASEBLADE_SWING, new Item.Properties().setId(key)));
	public static final Item VOLCANO_SWORD = register("volcano_sword", key -> new VolcanoSwordItem(TerrariaToolMaterials.MOLTEN, 4F, -2.4F, new Item.Properties().setId(key).fireResistant()));

	// Ranged
	public static final Item SPACE_GUN = register("space_gun", SpaceGunItem::new);

	// Throwables
	// Grenades
	public static final Item GRENADE = register("grenade", key -> new GrenadeItem(new Item.Properties().setId(key), false, false));
	public static final Item STICKY_GRENADE = register("sticky_grenade", key -> new GrenadeItem(new Item.Properties().setId(key), true, false));
	public static final Item BOUNCY_GRENADE = register("bouncy_grenade", key -> new GrenadeItem(new Item.Properties().setId(key), false, true));
	// Bombs
	public static final Item BOMB = register("bomb", key -> new BombItem(new Item.Properties().setId(key), false, false));
	public static final Item STICKY_BOMB = register("sticky_bomb", key -> new BombItem(new Item.Properties().setId(key), true, false));
	public static final Item BOUNCY_BOMB = register("bouncy_bomb", key -> new BombItem(new Item.Properties().setId(key), false, true));
	// Dynamite
	public static final Item DYNAMITE = register("dynamite", key -> new DynamiteItem(new Item.Properties().setId(key), false, false));
	public static final Item STICKY_DYNAMITE = register("sticky_dynamite", key -> new DynamiteItem(new Item.Properties().setId(key), true, false));
	public static final Item BOUNCY_DYNAMITE = register("bouncy_dynamite", key -> new DynamiteItem(new Item.Properties().setId(key), false, true));

	// Armours
	// todo: make another register method that registers a full set of armor instead of registering per piece, don't know the best way to do this since the item is different (ShadowArmor, CrimsonArmor)
	public static final Item SHADOW_HELMET = register("shadow_helmet", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorType.HELMET, new Item.Properties().setId(key)));
	public static final Item SHADOW_CHESTPLATE = register("shadow_chestplate", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorType.CHESTPLATE, new Item.Properties().setId(key)));
	public static final Item SHADOW_LEGGINGS = register("shadow_leggings", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorType.LEGGINGS, new Item.Properties().setId(key)));
	public static final Item SHADOW_BOOTS = register("shadow_boots", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.SHADOW, ArmorType.BOOTS, new Item.Properties().setId(key)));
	public static final Item ANCIENT_SHADOW_HELMET = register("ancient_shadow_helmet", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorType.HELMET, new Item.Properties().setId(key)));
	public static final Item ANCIENT_SHADOW_CHESTPLATE = register("ancient_shadow_chestplate", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorType.CHESTPLATE, new Item.Properties().setId(key)));
	public static final Item ANCIENT_SHADOW_LEGGINGS = register("ancient_shadow_leggings", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorType.LEGGINGS, new Item.Properties().setId(key)));
	public static final Item ANCIENT_SHADOW_BOOTS = register("ancient_shadow_boots", key -> new ShadowArmor("shadow_armor", TerrariaArmorMaterials.ANCIENT_SHADOW, ArmorType.BOOTS, new Item.Properties().setId(key)));
	public static final Item CRIMSON_HELMET = register("crimson_helmet", key -> new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorType.HELMET, new Item.Properties().setId(key)));
	public static final Item CRIMSON_CHESTPLATE = register("crimson_chestplate", key -> new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorType.CHESTPLATE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_LEGGINGS = register("crimson_leggings", key -> new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorType.LEGGINGS, new Item.Properties().setId(key)));
	public static final Item CRIMSON_BOOTS = register("crimson_boots", key -> new CrimsonArmor("crimson_armor", TerrariaArmorMaterials.CRIMSON, ArmorType.BOOTS, new Item.Properties().setId(key)));
	public static final Item METEOR_HELMET = register("meteor_helmet", key -> new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorType.HELMET, new Item.Properties().setId(key)));
	public static final Item METEOR_CHESTPLATE = register("meteor_chestplate", key -> new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorType.CHESTPLATE, new Item.Properties().setId(key)));
	public static final Item METEOR_LEGGINGS = register("meteor_leggings", key -> new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorType.LEGGINGS, new Item.Properties().setId(key)));
	public static final Item METEOR_BOOTS = register("meteor_boots", key -> new MeteorArmor("meteor_armor", TerrariaArmorMaterials.METEOR, ArmorType.BOOTS, new Item.Properties().setId(key)));
	public static final Item MOLTEN_HELMET = register("molten_helmet", key -> new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorType.HELMET, new Item.Properties().setId(key)));
	public static final Item MOLTEN_CHESTPLATE = register("molten_chestplate", key -> new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorType.CHESTPLATE, new Item.Properties().setId(key)));
	public static final Item MOLTEN_LEGGINGS = register("molten_leggings", key -> new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorType.LEGGINGS, new Item.Properties().setId(key)));
	public static final Item MOLTEN_BOOTS = register("molten_boots", key -> new MoltenArmor("molten_armor", TerrariaArmorMaterials.MOLTEN, ArmorType.BOOTS, new Item.Properties().setId(key)));

	// Vanity Armours
	public static final Item FAMILIAR_WIG = register("familiar_wig", key -> new FamiliarVanity("familiar_wig", TerrariaArmorMaterials.VANITY, ArmorType.HELMET, new Item.Properties().setId(key)));
	public static final Item FAMILIAR_SHIRT = register("familiar_shirt", key -> new FamiliarVanity("familiar_shirt", TerrariaArmorMaterials.VANITY, ArmorType.CHESTPLATE, new Item.Properties().setId(key)));
	public static final Item FAMILIAR_PANTS = register("familiar_pants", key -> new FamiliarVanity("familiar_pants", TerrariaArmorMaterials.VANITY, ArmorType.LEGGINGS, new Item.Properties().setId(key)));
	public static final Item FAMILIAR_SHOES = register("familiar_shoes", key -> new FamiliarVanity("familiar_shoes", TerrariaArmorMaterials.VANITY, ArmorType.BOOTS, new Item.Properties().setId(key)));
	//public static final Item TOP_HAT = register("top_hat", key -> new TopHatVanity("top_hat", TerrariaArmorMaterials.VANITY, ArmorType.HELMET, new FabricItemSettings()));
	// todo: make Eye of Cthulhu mask and add it to Treasure Bag loot table (replace familiar wig)



	///////////////// Blocks /////////////////

	// Chests
	public static final Item GOLD_CHEST = register("gold_chest", key -> new BlockItem(ModBlocks.GOLD_CHEST, new Item.Properties().setId(key)));
	public static final Item TRAPPED_GOLD_CHEST = register("trapped_gold_chest", key -> new BlockItem(ModBlocks.TRAPPED_GOLD_CHEST, new Item.Properties().setId(key)));
	public static final Item FROZEN_CHEST = register("frozen_chest", key -> new BlockItem(ModBlocks.FROZEN_CHEST, new Item.Properties().setId(key)));
	public static final Item TRAPPED_FROZEN_CHEST = register("trapped_frozen_chest", key -> new BlockItem(ModBlocks.TRAPPED_FROZEN_CHEST, new Item.Properties().setId(key)));
	public static final Item IVY_CHEST = register("ivy_chest", key -> new BlockItem(ModBlocks.IVY_CHEST, new Item.Properties().setId(key)));
	public static final Item TRAPPED_IVY_CHEST = register("trapped_ivy_chest", key -> new BlockItem(ModBlocks.TRAPPED_IVY_CHEST, new Item.Properties().setId(key)));
	public static final Item SANDSTONE_CHEST = register("sandstone_chest", key -> new BlockItem(ModBlocks.SANDSTONE_CHEST, new Item.Properties().setId(key)));
	public static final Item TRAPPED_SANDSTONE_CHEST = register("trapped_sandstone_chest", key -> new BlockItem(ModBlocks.TRAPPED_SANDSTONE_CHEST, new Item.Properties().setId(key)));
	public static final Item WATER_CHEST = register("water_chest", key -> new BlockItem(ModBlocks.WATER_CHEST, new Item.Properties().setId(key)));
	public static final Item SKYWARE_CHEST = register("skyware_chest", key -> new BlockItem(ModBlocks.SKYWARE_CHEST, new Item.Properties().setId(key)));
	public static final Item SHADOW_CHEST = register("shadow_chest", key -> new BlockItem(ModBlocks.SHADOW_CHEST, new Item.Properties().setId(key)));
	public static final Item PIGGY_BANK = register("piggy_bank", key -> new BlockItem(ModBlocks.PIGGY_BANK, new Item.Properties().setId(key)));
	public static final Item SAFE = register("safe", key -> new BlockItem(ModBlocks.SAFE, new Item.Properties().setId(key)));

	// Metals
	public static final Item METEORITE_ORE = register("meteorite_ore", key -> new BlockItem(ModBlocks.METEORITE_ORE, new Item.Properties().setId(key)));
	public static final Item RAW_METEORITE_BLOCK = register("raw_meteorite_block", key -> new BlockItem(ModBlocks.RAW_METEORITE_BLOCK, new Item.Properties().setId(key)));
	public static final Item METEORITE_BLOCK = register("meteorite_block", key -> new BlockItem(ModBlocks.METEORITE_BLOCK, new Item.Properties().setId(key)));
	public static final Item DEMONITE_ORE = register("demonite_ore", key -> new BlockItem(ModBlocks.DEMONITE_ORE, new Item.Properties().setId(key)));
	public static final Item DEEPSLATE_DEMONITE_ORE = register("deepslate_demonite_ore", key -> new BlockItem(ModBlocks.DEEPSLATE_DEMONITE_ORE, new Item.Properties().setId(key)));
	public static final Item RAW_DEMONITE_BLOCK = register("raw_demonite_block", key -> new BlockItem(ModBlocks.RAW_DEMONITE_BLOCK, new Item.Properties().setId(key)));
	public static final Item DEMONITE_BLOCK = register("demonite_block", key -> new BlockItem(ModBlocks.DEMONITE_BLOCK, new Item.Properties().setId(key)));
	public static final Item CRIMTANE_ORE = register("crimtane_ore", key -> new BlockItem(ModBlocks.CRIMTANE_ORE, new Item.Properties().setId(key)));
	public static final Item DEEPSLATE_CRIMTANE_ORE = register("deepslate_crimtane_ore", key -> new BlockItem(ModBlocks.DEEPSLATE_CRIMTANE_ORE, new Item.Properties().setId(key)));
	public static final Item RAW_CRIMTANE_BLOCK = register("raw_crimtane_block", key -> new BlockItem(ModBlocks.RAW_CRIMTANE_BLOCK, new Item.Properties().setId(key)));
	public static final Item CRIMTANE_BLOCK = register("crimtane_block", key -> new BlockItem(ModBlocks.CRIMTANE_BLOCK, new Item.Properties().setId(key)));
	public static final Item HELLSTONE_ORE = register("hellstone_ore", key -> new BlockItem(ModBlocks.HELLSTONE_ORE, new Item.Properties().setId(key).fireResistant()));
	public static final Item RAW_HELLSTONE_BLOCK = register("raw_hellstone_block", key -> new BlockItem(ModBlocks.RAW_HELLSTONE_BLOCK, new Item.Properties().setId(key).fireResistant()));
	public static final Item HELLSTONE_BLOCK = register("hellstone_block", key -> new BlockItem(ModBlocks.HELLSTONE_BLOCK, new Item.Properties().setId(key).fireResistant()));

	// Misc
	public static final Item REDSTONE_STONE = register("redstone_stone", key -> new BlockItem(ModBlocks.REDSTONE_STONE, new Item.Properties().setId(key)));
	public static final Item REDSTONE_DEEPSLATE = register("redstone_deepslate", key -> new BlockItem(ModBlocks.REDSTONE_DEEPSLATE, new Item.Properties().setId(key)));
	public static final Item INSTANT_TNT = register("instant_tnt", key -> new BlockItem(ModBlocks.INSTANT_TNT, new Item.Properties().setId(key)));
	public static final Item TINKERER_TABLE = register("tinkerer_workshop", key -> new BlockItem(ModBlocks.TINKERER_TABLE, new Item.Properties().setId(key)));

	// Building
	public static final Item SUNPLATE_BLOCK = register("sunplate_block", key -> new BlockItem(ModBlocks.SUNPLATE_BLOCK, new Item.Properties().setId(key)));
	public static final Item CLOUD = register("cloud", key -> new BlockItem(ModBlocks.CLOUD, new Item.Properties().setId(key)));
	public static final Item RAIN_CLOUD = register("rain_cloud", key -> new BlockItem(ModBlocks.RAIN_CLOUD, new Item.Properties().setId(key)));
	public static final Item BLUE_BRICKS = register("blue_brick", key -> new BlockItem(ModBlocks.BLUE_BRICKS, new Item.Properties().setId(key)));
	public static final Item CRACKED_BLUE_BRICKS = register("cracked_blue_brick", key -> new BlockItem(ModBlocks.CRACKED_BLUE_BRICKS, new Item.Properties().setId(key)));
	public static final Item FANCY_BLUE_BRICKS = register("fancy_blue_brick", key -> new BlockItem(ModBlocks.FANCY_BLUE_BRICKS, new Item.Properties().setId(key)));
	public static final Item GREEN_BRICKS = register("green_brick", key -> new BlockItem(ModBlocks.GREEN_BRICKS, new Item.Properties().setId(key)));
	public static final Item CRACKED_GREEN_BRICKS = register("cracked_green_brick", key -> new BlockItem(ModBlocks.CRACKED_GREEN_BRICKS, new Item.Properties().setId(key)));
	public static final Item FANCY_GREEN_BRICKS = register("fancy_green_brick", key -> new BlockItem(ModBlocks.FANCY_GREEN_BRICKS, new Item.Properties().setId(key)));
	public static final Item PURPLE_BRICKS = register("purple_brick", key -> new BlockItem(ModBlocks.PURPLE_BRICKS, new Item.Properties().setId(key)));
	public static final Item CRACKED_PURPLE_BRICKS = register("cracked_purple_brick", key -> new BlockItem(ModBlocks.CRACKED_PURPLE_BRICKS, new Item.Properties().setId(key)));
	public static final Item FANCY_PURPLE_BRICKS = register("fancy_purple_brick", key -> new BlockItem(ModBlocks.FANCY_PURPLE_BRICKS, new Item.Properties().setId(key)));

	// Vegetation
	public static final Item VILE_MUSHROOM = registerPlant("vile_mushroom", 0.65f, key -> new BlockItem(ModBlocks.VILE_MUSHROOM, new Item.Properties().setId(key)));
	public static final Item VICIOUS_MUSHROOM = registerPlant("vicious_mushroom", 0.65f, key -> new BlockItem(ModBlocks.VICIOUS_MUSHROOM, new Item.Properties().setId(key)));

	// Corruption
	public static final Item CORRUPTED_GRASS_BLOCK = register("corrupted_grass", key -> new BlockItem(ModBlocks.CORRUPTED_GRASS, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_GRAVEL = register("corrupted_gravel", key -> new BlockItem(ModBlocks.CORRUPTED_GRAVEL, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_SAND = register("corrupted_sand", key -> new BlockItem(ModBlocks.CORRUPTED_SAND, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_GLASS = register("corrupted_glass", key -> new BlockItem(ModBlocks.CORRUPTED_GLASS, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_SANDSTONE = register("corrupted_sandstone", key -> new BlockItem(ModBlocks.CORRUPTED_SANDSTONE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_ANDESITE = register("corrupted_andesite", key -> new BlockItem(ModBlocks.CORRUPTED_ANDESITE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DIORITE = register("corrupted_diorite", key -> new BlockItem(ModBlocks.CORRUPTED_DIORITE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_GRANITE = register("corrupted_granite", key -> new BlockItem(ModBlocks.CORRUPTED_GRANITE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_STONE = register("corrupted_stone", key -> new BlockItem(ModBlocks.CORRUPTED_STONE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE = register("corrupted_deepslate", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_COBBLESTONE = register("corrupted_cobblestone", key -> new BlockItem(ModBlocks.CORRUPTED_COBBLESTONE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_COBBLED_DEEPSLATE = register("corrupted_cobbled_deepslate", key -> new BlockItem(ModBlocks.CORRUPTED_COBBLED_DEEPSLATE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_COAL_ORE = register("corrupted_coal_ore", key -> new BlockItem(ModBlocks.CORRUPTED_COAL_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_IRON_ORE = register("corrupted_iron_ore", key -> new BlockItem(ModBlocks.CORRUPTED_IRON_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_COPPER_ORE = register("corrupted_copper_ore", key -> new BlockItem(ModBlocks.CORRUPTED_COPPER_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_GOLD_ORE = register("corrupted_gold_ore", key -> new BlockItem(ModBlocks.CORRUPTED_GOLD_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_LAPIS_ORE = register("corrupted_lapis_ore", key -> new BlockItem(ModBlocks.CORRUPTED_LAPIS_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_REDSTONE_ORE = register("corrupted_redstone_ore", key -> new BlockItem(ModBlocks.CORRUPTED_REDSTONE_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DIAMOND_ORE = register("corrupted_diamond_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DIAMOND_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_EMERALD_ORE = register("corrupted_emerald_ore", key -> new BlockItem(ModBlocks.CORRUPTED_EMERALD_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_COAL_ORE = register("corrupted_deepslate_coal_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_COAL_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_IRON_ORE = register("corrupted_deepslate_iron_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_IRON_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_COPPER_ORE = register("corrupted_deepslate_copper_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_COPPER_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_GOLD_ORE = register("corrupted_deepslate_gold_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_GOLD_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_LAPIS_ORE = register("corrupted_deepslate_lapis_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_LAPIS_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_REDSTONE_ORE = register("corrupted_deepslate_redstone_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_REDSTONE_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_DIAMOND_ORE = register("corrupted_deepslate_diamond_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_DIAMOND_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_DEEPSLATE_EMERALD_ORE = register("corrupted_deepslate_emerald_ore", key -> new BlockItem(ModBlocks.CORRUPTED_DEEPSLATE_EMERALD_ORE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_SNOW_LAYER = register("corrupted_snow_layer", key -> new BlockItem(ModBlocks.CORRUPTED_SNOW_LAYER, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_SNOW_BLOCK = register("corrupted_snow", key -> new BlockItem(ModBlocks.CORRUPTED_SNOW, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_ICE = register("corrupted_ice", key -> new BlockItem(ModBlocks.CORRUPTED_ICE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_PACKED_ICE = register("corrupted_packed_ice", key -> new BlockItem(ModBlocks.CORRUPTED_PACKED_ICE, new Item.Properties().setId(key)));
	public static final Item CORRUPTED_BLUE_ICE = register("corrupted_blue_ice", key -> new BlockItem(ModBlocks.CORRUPTED_BLUE_ICE, new Item.Properties().setId(key)));

	// Crimson
	public static final Item CRIMSON_GRASS_BLOCK = register("crimson_grass", key -> new BlockItem(ModBlocks.CRIMSON_GRASS, new Item.Properties().setId(key)));
	public static final Item CRIMSON_GRAVEL = register("crimson_gravel", key -> new BlockItem(ModBlocks.CRIMSON_GRAVEL, new Item.Properties().setId(key)));
	public static final Item CRIMSON_SAND = register("crimson_sand", key -> new BlockItem(ModBlocks.CRIMSON_SAND, new Item.Properties().setId(key)));
	public static final Item CRIMSON_GLASS = register("crimson_glass", key -> new BlockItem(ModBlocks.CRIMSON_GLASS, new Item.Properties().setId(key)));
	public static final Item CRIMSON_SANDSTONE = register("crimson_sandstone", key -> new BlockItem(ModBlocks.CRIMSON_SANDSTONE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_ANDESITE = register("crimson_andesite", key -> new BlockItem(ModBlocks.CRIMSON_ANDESITE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DIORITE = register("crimson_diorite", key -> new BlockItem(ModBlocks.CRIMSON_DIORITE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_GRANITE = register("crimson_granite", key -> new BlockItem(ModBlocks.CRIMSON_GRANITE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_STONE = register("crimson_stone", key -> new BlockItem(ModBlocks.CRIMSON_STONE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE = register("crimson_deepslate", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_COBBLESTONE = register("crimson_cobblestone", key -> new BlockItem(ModBlocks.CRIMSON_COBBLESTONE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_COBBLED_DEEPSLATE = register("crimson_cobbled_deepslate", key -> new BlockItem(ModBlocks.CRIMSON_COBBLED_DEEPSLATE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_COAL_ORE = register("crimson_coal_ore", key -> new BlockItem(ModBlocks.CRIMSON_COAL_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_IRON_ORE = register("crimson_iron_ore", key -> new BlockItem(ModBlocks.CRIMSON_IRON_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_COPPER_ORE = register("crimson_copper_ore", key -> new BlockItem(ModBlocks.CRIMSON_COPPER_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_GOLD_ORE = register("crimson_gold_ore", key -> new BlockItem(ModBlocks.CRIMSON_GOLD_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_LAPIS_ORE = register("crimson_lapis_ore", key -> new BlockItem(ModBlocks.CRIMSON_LAPIS_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_REDSTONE_ORE = register("crimson_redstone_ore", key -> new BlockItem(ModBlocks.CRIMSON_REDSTONE_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DIAMOND_ORE = register("crimson_diamond_ore", key -> new BlockItem(ModBlocks.CRIMSON_DIAMOND_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_EMERALD_ORE = register("crimson_emerald_ore", key -> new BlockItem(ModBlocks.CRIMSON_EMERALD_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_COAL_ORE = register("crimson_deepslate_coal_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_COAL_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_IRON_ORE = register("crimson_deepslate_iron_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_IRON_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_COPPER_ORE = register("crimson_deepslate_copper_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_COPPER_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_GOLD_ORE = register("crimson_deepslate_gold_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_GOLD_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_LAPIS_ORE = register("crimson_deepslate_lapis_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_LAPIS_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_REDSTONE_ORE = register("crimson_deepslate_redstone_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_REDSTONE_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_DIAMOND_ORE = register("crimson_deepslate_diamond_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_DIAMOND_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_DEEPSLATE_EMERALD_ORE = register("crimson_deepslate_emerald_ore", key -> new BlockItem(ModBlocks.CRIMSON_DEEPSLATE_EMERALD_ORE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_SNOW_LAYER = register("crimson_snow_layer", key -> new BlockItem(ModBlocks.CRIMSON_SNOW_LAYER, new Item.Properties().setId(key)));
	public static final Item CRIMSON_SNOW_BLOCK = register("crimson_snow", key -> new BlockItem(ModBlocks.CRIMSON_SNOW, new Item.Properties().setId(key)));
	public static final Item CRIMSON_ICE = register("crimson_ice", key -> new BlockItem(ModBlocks.CRIMSON_ICE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_PACKED_ICE = register("crimson_packed_ice", key -> new BlockItem(ModBlocks.CRIMSON_PACKED_ICE, new Item.Properties().setId(key)));
	public static final Item CRIMSON_BLUE_ICE = register("crimson_blue_ice", key -> new BlockItem(ModBlocks.CRIMSON_BLUE_ICE, new Item.Properties().setId(key)));

	private static Item register(String name, Function<ResourceKey<Item>, Item> itemFactory) {
		ResourceLocation resourceLocation = TerraMine.id(name);
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, resourceLocation);
		Item item = itemFactory.apply(key);

		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	private static Item registerPlant(String name, float chance, Function<ResourceKey<Item>, Item> itemFactory) {
		ResourceLocation resourceLocation = TerraMine.id(name);
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, resourceLocation);
		Item item = itemFactory.apply(key);

		ComposterBlock.COMPOSTABLES.put(item, chance);
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}
}
