package terramine.mixin.client.render;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import terramine.common.item.dye.BasicDye;
import terramine.extensions.EntityRenderStateExtensions;
import terramine.extensions.PlayerStorages;

@Mixin(CustomHeadLayer.class)
public abstract class CustomHeadLayerMixin<S extends LivingEntityRenderState, M extends EntityModel<S> & HeadedModel> extends RenderLayer<S, M> {
    @Unique
    private BasicDye dyeItem;

    @Final
    @Shadow
    private ItemRenderer itemRenderer;

    public CustomHeadLayerMixin(RenderLayerParent<S, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @ModifyVariable(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V", at = @At("STORE"), ordinal = 0)
    private ItemStack vanityArmor(ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, S livingEntityRenderState, float f, float g) {
        if (((EntityRenderStateExtensions)livingEntityRenderState).terrariaCraft$getLivingEntity() instanceof Player player) {
            if (((PlayerStorages) player).getTerrariaInventory().getItem(30).getItem() instanceof BasicDye dye) {
                this.dyeItem = dye;
            } else {
                this.dyeItem = null;
            }

            if (((PlayerStorages)player).getTerrariaInventory().getItem(26) != ItemStack.EMPTY) {
                return ((PlayerStorages)player).getTerrariaInventory().getItem(26);
            }
        }
        return itemStack;
    }

    @ModifyVariable(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V", at = @At("STORE"), ordinal = 0)
    private BakedModel vanityArmor(BakedModel bakedModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, S livingEntityRenderState, float f, float g) {
        if (((EntityRenderStateExtensions)livingEntityRenderState).terrariaCraft$getLivingEntity() instanceof Player player) {
            if (((PlayerStorages)player).getTerrariaInventory().getItem(26) != ItemStack.EMPTY) {
                return itemRenderer.getModel(((PlayerStorages)player).getTerrariaInventory().getItem(26), player.level(), player, 0);
            }
        }
        return bakedModel;
    }

    @WrapOperation(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;renderSkull(Lnet/minecraft/core/Direction;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/SkullModelBase;Lnet/minecraft/client/renderer/RenderType;)V")
    )
    private void headSkullDye(Direction direction, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, SkullModelBase skullModelBase, RenderType renderType, Operation<Void> original) {
        if (dyeItem != null) {
            poseStack.pushPose();
            poseStack.translate(0.5F, 0.0F, 0.5F);
            poseStack.scale(-1.0F, -1.0F, 1.0F);
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(renderType);
            skullModelBase.setupAnim(g, f, 0.0F);
            skullModelBase.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, dyeItem.getColourInt());
            poseStack.popPose();
            return;
        }
        original.call(direction, f, g, poseStack, multiBufferSource, i, skullModelBase, renderType);
    }

    /**
    // todo: add dye support to items displayed on head, probably needs a lot of work if possible
    @WrapOperation(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V")
    )
    private void headBlockDye(ItemRenderer instance, ItemStack itemStack, ItemDisplayContext itemDisplayContext, boolean bl, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, BakedModel bakedModel, Operation<Void> original) {
        if (dyeItem != null) {
            Vector3f colour = dyeItem.getColour();
            return;
        }
        original.call(instance, itemStack, itemDisplayContext, bl, poseStack, multiBufferSource, i, j, bakedModel);
    }
    **/
}
