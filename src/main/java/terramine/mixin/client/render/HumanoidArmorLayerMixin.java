package terramine.mixin.client.render;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentModel;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import terramine.TerraMine;
import terramine.common.item.armor.TerrariaArmor;
import terramine.common.item.armor.vanity.FamiliarVanity;
import terramine.common.item.armor.vanity.VanityArmor;
import terramine.common.item.dye.BasicDye;
import terramine.extensions.PlayerStorages;

import java.util.Map;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<S extends HumanoidRenderState, M extends HumanoidModel<S>, A extends HumanoidModel<S>> extends RenderLayer<S, M> {

	public HumanoidArmorLayerMixin(RenderLayerParent<S, M> renderLayerParent, A humanoidModel, A humanoidModel2, EquipmentLayerRenderer equipmentLayerRenderer) {
		super(renderLayerParent);
	}

	@ModifyVariable(method = "renderArmorPiece", at = @At("HEAD"), ordinal = 1)
	private ItemStack vanityArmor(PoseStack poseStack, MultiBufferSource multiBufferSource, ItemStack itemStack, EquipmentSlot equipmentSlot, int i, A humanoidModel) {
		if (Minecraft.getInstance().player instanceof Player player) {
			if (((PlayerStorages)player).getTerrariaInventory().getItem(equipmentSlot.getIndex() + 23) != ItemStack.EMPTY) {
				if (((PlayerStorages)player).getTerrariaInventory().getItem(equipmentSlot.getIndex() + 23).getItem() == Items.ELYTRA) {
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
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/EquipmentLayerRenderer;renderLayers(Lnet/minecraft/world/item/equipment/EquipmentModel$LayerType;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/model/Model;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
	)
	private void armorDyeVanity(EquipmentLayerRenderer instance, EquipmentModel.LayerType layerType, ResourceLocation resourceLocation, Model model, ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Operation<Void> original) {
		Player player = Minecraft.getInstance().player;
		ArmorItem armorItem = (ArmorItem) itemStack.getItem();
		if (!(armorItem instanceof FamiliarVanity)) {
			if (armorItem instanceof TerrariaArmor terrariaArmor && terrariaArmor.getCustomArmorModel() != null) {
				A customModel = (A) terrariaArmor.getCustomArmorModel();
				((A) model).copyPropertiesTo(customModel);
				model = customModel;
			}
			if (((PlayerStorages) player).getTerrariaInventory().getItem(player.getEquipmentSlotForItem(itemStack).getIndex() + 27).getItem() instanceof BasicDye dye) {
				ResourceLocation location = getArmorLocation(armorItem, resourceLocation);
				Vector3f colour = dye.getColour();
				VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(multiBufferSource, RenderType.armorCutoutNoCull(location), armorItem.getDefaultInstance().hasFoil());
				vertexConsumer.setColor(colour.x(), colour.y(), colour.z(), 1.0F);
				model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1);
				return;
			}
		}
		original.call(instance, layerType, resourceLocation, model, itemStack, poseStack, multiBufferSource, i);
	}

	@Unique
	private ResourceLocation getArmorLocation(ArmorItem item, ResourceLocation originalResource) {
		if (item instanceof VanityArmor vanityArmor) {
			if (vanityArmor.getCustomArmorLocation() != null) {
				return ResourceLocation.fromNamespaceAndPath(TerraMine.MOD_ID, "textures/models/vanity/" + vanityArmor.getCustomArmorLocation() + ".png");
			}
		}

		return originalResource;
	}
}