package terramine.mixin.item.accessories.blackbelt;

import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terramine.common.init.ModItems;
import terramine.common.misc.AccessoriesHelper;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	@Unique
	private DamageSource damageSource;

	@Unique
	private boolean dodged;

	public LivingEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(method = "hurtServer", at = @At("HEAD"))
	private void accessDamageSource(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
		this.damageSource = damageSource;
	}

	@ModifyVariable(method = "hurtServer", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private float dodgeAttack(float f) {
		LivingEntity self = (LivingEntity) (Object) this;
		if (AccessoriesHelper.isEquipped(ModItems.BLACK_BELT, self) || AccessoriesHelper.isEquipped(ModItems.MASTER_NINJA_GEAR, self)) {
			if (damageSource != this.damageSources().fall() && self.getRandom().nextInt(100) <= 10) {
				f = 0f;
				dodged = true;
				if (!self.level().isClientSide()) {
					Vec3 pos = self.position();
					Vec3i motion = self.getMotionDirection().getUnitVec3i();
					((ServerLevel) self.level()).sendParticles(ParticleTypes.POOF, pos.x(), pos.y(), pos.z(), 3, motion.getX() * -1.0D, -1.0D, motion.getZ() * -1.0D, 0.15);
				}
			}
		}
		return f;
	}

	@ModifyVariable(method = "knockback", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private double removeKnockback(double d) {
		LivingEntity self = (LivingEntity) (Object) this;
		if (AccessoriesHelper.isEquipped(ModItems.BLACK_BELT, self) || AccessoriesHelper.isEquipped(ModItems.MASTER_NINJA_GEAR, self)) {
			if (dodged) {
				d = 0f;
				dodged = false;
			}
		}
		return d;
	}
}
