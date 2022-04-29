package terracraft.mixin.item.cloudinabottle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terracraft.common.init.ModComponents;
import terracraft.common.init.ModItems;
import terracraft.common.init.ModSoundEvents;
import terracraft.common.item.curio.belt.CloudInABottleItem;
import terracraft.common.trinkets.TrinketsHelper;
import terracraft.extensions.LivingEntityExtensions;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityExtensions {

	@Shadow
	protected boolean jumping;
	// Is entity double jumping in this tick
	@Unique
	private boolean isDoubleJumping = false;
	// Has entity released jump key since last jump
	@Unique
	private boolean jumpWasReleased = false;
	// Has entity double jumped during current airtime
	@Unique
	private boolean hasDoubleJumped = false;

	@Unique
	private int quadJumped = 0;

	public LivingEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Shadow
	protected abstract void jumpFromGround();

	@Shadow
	public abstract boolean onClimbable();

	@Unique
	@Override
	public void terracraft$doubleJump() {
		// Call the vanilla jump method
		// We modify the behaviour of this method in multiple places if terracraft$isDoubleJumping is true
		this.isDoubleJumping = true;
		this.jumpFromGround();

		// Play jump sound
		LivingEntity self = (LivingEntity) (Object) this;
		SoundEvent jumpSound = TrinketsHelper.isEquipped(ModItems.WHOOPEE_CUSHION, self) ?
				ModSoundEvents.FART : ModSoundEvents.DOUBLE_JUMP;
		this.playSound(jumpSound, 1, 0.9F + self.getRandom().nextFloat() * 0.2F);

		// Reset fall distance for fall damage
		this.fallDistance = 0;

		// Send double jump packet to server if we're on the client
		if (this.level.isClientSide) {
			sendDoubleJumpPacket();
		}

		this.isDoubleJumping = false;

		if (self instanceof Player player) {
			if (TrinketsHelper.isEquipped(ModItems.BUNDLE_OF_BALLOONS, player)) {
				quadJumped++;
				if (quadJumped == 3) {
					ModComponents.MOVEMENT_ORDER.get(player).setCloudFinished(true);
					quadJumped = 0;
				}
			} else {
				ModComponents.MOVEMENT_ORDER.get(player).setCloudFinished(true);
			}
		}
	}

	// This code is extracted because the mixin fails to apply with the usage of client-side only classes
	@Unique
	@Environment(EnvType.CLIENT)
	private static void sendDoubleJumpPacket() {
		ClientPlayNetworking.send(CloudInABottleItem.C2S_DOUBLE_JUMPED_ID, PacketByteBufs.empty());
	}

	@ModifyVariable(method = "causeFallDamage", ordinal = 0, at = @At("HEAD"))
	private float reduceFallDistance(float fallDistance) {
		// FIXME: this probably also works if we didn't double jump, intended?
		if (TrinketsHelper.isEquipped(ModItems.CLOUD_IN_A_BOTTLE, (LivingEntity) (Object) this)) {
			fallDistance = Math.max(0, fallDistance - 3);
		}

		return fallDistance;
	}

	@SuppressWarnings("ConstantConditions")
	@Inject(method = "aiStep", at = @At("HEAD"))
	private void invokeDoubleJump(CallbackInfo info) {
		LivingEntity self = (LivingEntity) (Object) this;
		if (self instanceof Player player) {
			jumpWasReleased |= !this.jumping;

			if ((this.isOnGround() || this.onClimbable()) && !this.isInWater()) {
				quadJumped = 0;
				this.hasDoubleJumped = false;
				ModComponents.MOVEMENT_ORDER.get(player).setCloudFinished(false);
			}

			boolean flying = player.getAbilities().flying;
			if (this.jumping && this.jumpWasReleased && !this.isInWater() && !this.isOnGround() && !this.isPassenger() // jumping is only client side, find alternative to fix rocket boot particles
					&& !this.hasDoubleJumped && !flying && TrinketsHelper.isEquipped(ModItems.CLOUD_IN_A_BOTTLE, player)
					&& !TrinketsHelper.isEquipped(ModItems.BUNDLE_OF_BALLOONS, player)) {
				this.terracraft$doubleJump();
				this.hasDoubleJumped = true;
			}
		}
	}

	@Inject(method = "jumpFromGround", at = @At("RETURN"))
	private void setJumpReleased(CallbackInfo info) {
		this.jumpWasReleased = false;
	}

	@Inject(method = "getJumpPower", cancellable = true, at = @At("HEAD"))
	private void increaseBaseDoubleJumpVelocity(CallbackInfoReturnable<Float> info) {
		if (this.isDoubleJumping) {
			info.setReturnValue(0.5f);
		}
	}
}
