package terramine.common.potions.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class TerrariaEffect extends MobEffect {
    private final boolean instant;

    public TerrariaEffect(MobEffectCategory type, int color, boolean isInstant) {
        super(type, color);
        this.instant = isInstant;
    }

    @Override
    public boolean isInstantenous() {
        return instant;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int remainingTicks, int level) {
        if (isInstantenous()) {
            return true;
        }
        return canApplyEffect(remainingTicks, level);
    }

    protected boolean canApplyEffect(int remainingTicks, int level) {
        if (!isInstantenous()) {
            Thread.dumpStack();
        }
        return false;
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, @NotNull LivingEntity entity, int amplifier) {
        if (isInstantenous()) {
            applyInstantenousEffect(serverLevel, null, null, entity, amplifier, 1.0d);
        }
        return true;
    }
}
