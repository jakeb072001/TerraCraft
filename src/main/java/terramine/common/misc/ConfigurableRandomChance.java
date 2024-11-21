package terramine.common.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.common.init.ModLootConditions;

// todo: look at https://github.com/ochotonida/artifacts/blob/1.20.x/common/src/main/java/artifacts/loot/ConfigValueChance.java#L59 for help fixing?
public record ConfigurableRandomChance(float defaultProbability) implements LootItemCondition {

    // new test
    public static final MapCodec<ConfigurableRandomChance> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.FLOAT.fieldOf("default_probability").forGetter(ConfigurableRandomChance::defaultProbability))
                    .apply(instance, ConfigurableRandomChance::new)
    );
    // end new test

    public @NotNull LootItemConditionType getType() {
        return ModLootConditions.CONFIGURABLE_ACCESSORY_CHANCE;
    }

    public boolean test(LootContext context) {
        if (TerraMine.CONFIG.worldgen.accessoryRarity >= 9999) {
            return false;
        }
        float c = TerraMine.CONFIG.worldgen.accessoryRarity;
        float p = defaultProbability;
        return context.getRandom().nextFloat() < p / (p + c - c * p);
    }

    public static LootItemCondition.Builder configurableRandomChance(float probability) {
        return () -> new ConfigurableRandomChance(probability);
    }
}
