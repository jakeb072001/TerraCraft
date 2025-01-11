package terramine.mixin.item.accessories.flippers;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import terramine.common.init.ModItems;
import terramine.common.misc.AccessoriesHelper;
import terramine.extensions.LivingEntityExtensions;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityExtensions {

	@ModifyArg(method = "jumpInLiquid", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;"))
	private double increaseSwimUpSpeed(double y) {
		return terramine$getIncreasedSwimSpeed(y);
	}

	@ModifyArg(method = "travelInFluid", allow = 1,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V"),
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWater()Z"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V")
			)
	)
	private float increaseSwimSpeed(float speed) {
		return (float) terramine$getIncreasedSwimSpeed(speed);
	}

	@Unique
	@Override
	public double terramine$getIncreasedSwimSpeed(double speed) {
		LivingEntity entity = (LivingEntity) (Object) this;
		if (AccessoriesHelper.isEquipped(ModItems.FLIPPERS, entity) || AccessoriesHelper.isEquipped(ModItems.DIVING_GEAR, entity)
				|| AccessoriesHelper.isEquipped(ModItems.NEPTUNE_SHELL, entity) || AccessoriesHelper.isEquipped(ModItems.MOON_SHELL, entity)
				|| AccessoriesHelper.isEquipped(ModItems.CELESTIAL_SHELL, entity)) {
			return speed * 2;
		} else {
			return speed;
		}
	}
}
