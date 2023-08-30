package terramine.mixin.statuseffect.client;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.misc.AccessoriesHelper;

import java.util.Optional;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

	/**
	 * Cancels the night vision fading effect when wearing an accessory that adds night vision as a permanent effect
	 */
	@Inject(method = "getNightVisionScale", at = @At("RETURN"), cancellable = true)
	private static void cancelNightVisionFadeEffect(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> info) {
		if (info.getReturnValueF() != 1f && entity instanceof Player player) {
			AccessoriesHelper.getAllEquipped(player).stream()
					.map(stack -> Optional.ofNullable(((AccessoryTerrariaItem) stack.getItem()).getPermanentEffect()))
					.filter(effect -> effect.isPresent() && effect.get().getEffect() == MobEffects.NIGHT_VISION)
					.findAny()
					.ifPresent(effect -> info.setReturnValue(1.0f));
		}
	}
}
