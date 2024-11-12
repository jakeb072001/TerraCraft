package terramine.mixin.client.render;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terramine.TerraMine;
import terramine.common.item.armor.TerrariaArmor;
import terramine.common.item.armor.vanity.FamiliarVanity;
import terramine.common.item.armor.vanity.VanityArmor;
import terramine.common.item.dye.BasicDye;
import terramine.extensions.PlayerStorages;

import java.util.Map;

// todo: needs to be redone, thanks mojang, very cool
@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
	@Shadow
	@Final
	private static Map<String, ResourceLocation> ARMOR_LOCATION_CACHE;

	@Unique
	private Player retrievedPlayer;

	@Shadow protected abstract ResourceLocation getArmorLocation(ArmorItem armorItem, boolean bl, @Nullable String string);

	public HumanoidArmorLayerMixin(RenderLayerParent<T, M> renderLayerParent) {
		super(renderLayerParent);
	}

	@ModifyVariable(method = "renderArmorPiece", at = @At("STORE"), ordinal = 0)
	private ItemStack vanityArmor(ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int i, A humanoidModel) {
		if (livingEntity instanceof Player player) {
			this.retrievedPlayer = player;
			if (((PlayerStorages)player).getTerrariaInventory().getItem(equipmentSlot.getIndex() + 23) != ItemStack.EMPTY) {
				if (((PlayerStorages)player).getTerrariaInventory().getItem(equipmentSlot.getIndex() + 23).getItem() instanceof ElytraItem) {
					return itemStack;
				}
				return ((PlayerStorages)player).getTerrariaInventory().getItem(equipmentSlot.getIndex() + 23);
			}
		}
		return itemStack;
	}

	// todo: look more into FancyDyes for special shaders, once working for armor implement into the accessory renderers
	@WrapOperation(
			method = "renderArmorPiece",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/HumanoidModel;FFFLnet/minecraft/resources/ResourceLocation;)V")
	)
	private void armorDyeVanity(HumanoidArmorLayer<T, M, A> instance, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, A humanoidModel, float f, float g, float h, ResourceLocation resourceLocation, Operation<Void> original) {
		ItemStack armorItem = livingEntity.getItemBySlot(equipmentSlot);
		if (!(armorItem instanceof FamiliarVanity)) {
			if (armorItem instanceof TerrariaArmor terrariaArmor && terrariaArmor.getCustomArmorModel() != null) {
				A customModel = (A) terrariaArmor.getCustomArmorModel();
				humanoidModel.copyPropertiesTo(customModel);
				humanoidModel = customModel;
			}
			if (this.retrievedPlayer != null) {
				if (((PlayerStorages) this.retrievedPlayer).getTerrariaInventory().getItem(armorItem.getEquipmentSlot().getIndex() + 27).getItem() instanceof BasicDye dye) {
					ResourceLocation location = this.getArmorLocation(armorItem, bl, string);
					Vector3f colour = dye.getColour();
					VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(multiBufferSource, RenderType.armorCutoutNoCull(location), false, bl);
					humanoidModel.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, colour.x(), colour.y(), colour.z(), 1.0F);
					return;
				}
			}
			original.call(instance, poseStack, multiBufferSource, i, humanoidModel, f, g, h, resourceLocation);
		}
	}

	@Inject(method = "getArmorLocation", at = @At(value = "HEAD"), cancellable = true)
	private void getArmorTexture(ArmorItem item, boolean secondLayer, String overlay, CallbackInfoReturnable<ResourceLocation> cir) {
		if (item instanceof VanityArmor vanityArmor) {
			if (vanityArmor.getCustomArmorLocation() != null) {
				cir.setReturnValue(ARMOR_LOCATION_CACHE.computeIfAbsent(TerraMine.MOD_ID + ":textures/models/vanity/" + vanityArmor.getCustomArmorLocation() + ".png", ResourceLocation::new));
			}
		}
	}
}