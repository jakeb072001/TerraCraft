package terramine.mixin.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terramine.common.events.LivingEntityDamagedCallback;
import terramine.common.events.LivingEntityHurtCallback;

@Mixin(value = {LivingEntity.class, Player.class})
public abstract class LivingEntitiesMixin extends Entity {

	public LivingEntitiesMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Inject(method = "actuallyHurt", at = @At("HEAD"))
	private void onEntityHurt(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfo ci) {
		if (!this.isInvulnerableToBase(damageSource)) {
			LivingEntityHurtCallback.EVENT.invoker().hurt((LivingEntity) (Object) this, damageSource, f);
		}
	}

	@Inject(method = "actuallyHurt", allow = 1, at = @At(value = "JUMP", opcode = Opcodes.IFNE))
	private void onEntityDamaged(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfo ci) {
		if (!this.isInvulnerableToBase(damageSource)) {
			LivingEntityDamagedCallback.EVENT.invoker().damage((LivingEntity) (Object) this, damageSource, f);
		}
	}
}
