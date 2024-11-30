package terramine.mixin.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terramine.TerraMine;
import terramine.client.render.AccessoryFeatureRenderer;
import terramine.client.render.AccessoryRenderRegistry;
import terramine.client.render.accessory.renderer.GloveAccessoryRenderer;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.extensions.PlayerStorages;

import java.util.HashMap;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerRenderState, PlayerModel> {
	public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel entityModel, float f) {
		super(context, entityModel, f);
	}

	@Inject(at = @At("RETURN"), method = "<init>")
	public void init(EntityRendererProvider.Context context, boolean bl, CallbackInfo ci) {
		this.addLayer(new AccessoryFeatureRenderer<>((PlayerRenderer) (Object) this));
	}

	@Inject(method = "renderLeftHand", at = @At("TAIL"))
	private void renderLeftGlove(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ResourceLocation resourceLocation, boolean bl, CallbackInfo ci) {
		renderArm(poseStack, multiBufferSource, i, HumanoidArm.LEFT);
	}

	@Inject(method = "renderRightHand", at = @At("TAIL"))
	private void renderRightGlove(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ResourceLocation resourceLocation, boolean bl, CallbackInfo ci) {
		renderArm(poseStack, multiBufferSource, i, HumanoidArm.RIGHT);
	}

	@Unique
	private static void renderArm(PoseStack matrixStack, MultiBufferSource buffer, int light, HumanoidArm handSide) {
		if (!TerraMine.CONFIG.client.showFirstPersonGloves) {
			return;
		}

		AbstractClientPlayer player = Minecraft.getInstance().player;
		HashMap<Integer, ItemStack> allEquippedGloves = new HashMap<>();
		for (int i = 0; i < 7; i++) {
			allEquippedGloves.put(i + 7, ((PlayerStorages)player).getTerrariaInventory().getItem(i + 7));
			allEquippedGloves.put(i, ((PlayerStorages)player).getTerrariaInventory().getItem(i));
		}

		for (int i = 0; i < allEquippedGloves.size(); i++) {
			if (!((PlayerStorages) player).getSlotVisibility(i)) {
				continue;
			}
			ItemStack stack = allEquippedGloves.get(i);
			if (stack.getItem() instanceof AccessoryTerrariaItem accessory && accessory.isGlove()) {
				int j = i;
				if (j >= 7) {
					j -= 7;
				}
				int finalI = j;
				AccessoryRenderRegistry.getRenderer(stack.getItem()).ifPresent(renderer -> {
					if (renderer instanceof GloveAccessoryRenderer gloveRenderer) {
						gloveRenderer.renderFirstPersonArm(matrixStack, buffer, light, finalI, player, handSide, stack.hasFoil());
					}
				});
			}
		}
	}
}
