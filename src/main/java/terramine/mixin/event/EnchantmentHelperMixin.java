package terramine.mixin.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terramine.common.events.LivingEntityAttackedCallback;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

	@Inject(method = "doPostAttackEffects", at = @At("HEAD"))
	private static void onUserAttacked(ServerLevel serverLevel, Entity entity, DamageSource damageSource, CallbackInfo ci) {
		if (entity instanceof LivingEntity livingEntity) {
			LivingEntityAttackedCallback.EVENT.invoker().attack(livingEntity, damageSource.getEntity(), livingEntity.getRandom());
		}
	}
}
