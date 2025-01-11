package terramine.mixin.client.render;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.equipment.EquipmentModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import terramine.common.item.armor.vanity.FamiliarVanity;
import terramine.common.item.dye.BasicDye;
import terramine.common.utility.Utilities;
import terramine.extensions.EntityRenderStateExtensions;
import terramine.extensions.PlayerStorages;

@Mixin(WingsLayer.class)
public abstract class ElytraLayerMixin<S extends HumanoidRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {
    @Unique
    private BasicDye dyeItem;

    public ElytraLayerMixin(RenderLayerParent<S, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @ModifyVariable(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V", at = @At("STORE"), ordinal = 0)
    private ItemStack hideElytra(ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, S humanoidRenderState, float f, float g) {
        if (((EntityRenderStateExtensions)humanoidRenderState).terrariaCraft$getLivingEntity() instanceof Player player) {
            if ((((PlayerStorages) player).getTerrariaInventory().getItem(25).getItem() instanceof FamiliarVanity)) {
                return ItemStack.EMPTY;
            }
            if (((PlayerStorages) player).getTerrariaInventory().getItem(29).getItem() instanceof BasicDye dye) {
                this.dyeItem = dye;
            } else {
                this.dyeItem = null;
            }
            if ((((PlayerStorages) player).getTerrariaInventory().getItem(25).getItem().components().has(DataComponents.GLIDER))) {
                return ((PlayerStorages) player).getTerrariaInventory().getItem(25);
            }
        }

        return itemStack;
    }

    // todo: unsure if will work, using vanilla dye system
    @WrapOperation(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/EquipmentLayerRenderer;renderLayers(Lnet/minecraft/world/item/equipment/EquipmentModel$LayerType;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/model/Model;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/resources/ResourceLocation;)V")
    )
    private void elytraDye(EquipmentLayerRenderer instance, EquipmentModel.LayerType layerType, ResourceLocation resourceLocation, Model model, ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ResourceLocation resourceLocation2, Operation<Void> original) {
        if (dyeItem != null) {
            itemStack.set(DataComponents.DYED_COLOR, new DyedItemColor(Utilities.intFromColor(dyeItem), false));
            original.call(instance, layerType, resourceLocation, model, itemStack, poseStack, multiBufferSource, i, resourceLocation2);
            return;
        }
        original.call(instance, layerType, resourceLocation, model, itemStack, poseStack, multiBufferSource, i, resourceLocation2);
    }
}