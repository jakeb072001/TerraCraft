package terramine.common.init;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import terramine.TerraMine;
import terramine.common.potions.effects.BlankEffect;
import terramine.common.potions.effects.mana.InstantManaEffect;
import terramine.common.potions.effects.mana.ManaRegenEffect;
import terramine.common.potions.effects.misc.IronSkinEffect;

public class ModMobEffects {
    //Timed
    public static final Holder<MobEffect> MANA_REGEN = register("mana_regen", new ManaRegenEffect(MobEffectCategory.BENEFICIAL, 0xe13b9e, false));
    public static final Holder<MobEffect> MINING_SPEED = register("mining_speed", new BlankEffect(MobEffectCategory.BENEFICIAL, 0x567f85, false));
    public static final Holder<MobEffect> IRONSKIN = register("ironskin", new IronSkinEffect(MobEffectCategory.BENEFICIAL, 0xf5ff3a, false));

    //Instant
    public static final Holder<MobEffect> INSTANT_MANA = register("instant_mana", new InstantManaEffect(MobEffectCategory.BENEFICIAL, 0x262db3, true));

    //Visual
    public static final Holder<MobEffect> MERFOLK = register("merfolk", new BlankEffect(MobEffectCategory.BENEFICIAL, 0x479c68, false));
    public static final Holder<MobEffect> WEREWOLF = register("werewolf", new BlankEffect(MobEffectCategory.BENEFICIAL, 0x7e5d3f, false));

    public static Holder<MobEffect> register(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, TerraMine.id(name), effect);
    }
}
