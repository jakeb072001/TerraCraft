package terramine.common.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import terramine.TerraMine;
import terramine.common.misc.ConfigurableRandomChance;

public class ModLootConditions {

    public static final LootItemConditionType CONFIGURABLE_ACCESSORY_CHANCE = new LootItemConditionType(ConfigurableRandomChance.CODEC);

    public static void register() {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, TerraMine.id("configurable_random_chance"), CONFIGURABLE_ACCESSORY_CHANCE);
    }
}
