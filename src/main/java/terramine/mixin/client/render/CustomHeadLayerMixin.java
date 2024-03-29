package terramine.mixin.client.render;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import terramine.common.item.dye.BasicDye;
import terramine.extensions.PlayerStorages;

@Mixin(CustomHeadLayer.class)
public abstract class CustomHeadLayerMixin<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
    @Unique
    private BasicDye dyeItem;

    public CustomHeadLayerMixin(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @ModifyVariable(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("STORE"), ordinal = 0)
    private ItemStack vanityArmor(ItemStack itemStack, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        if (livingEntity instanceof Player player) {
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

    @WrapOperation(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;renderSkull(Lnet/minecraft/core/Direction;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/SkullModelBase;Lnet/minecraft/client/renderer/RenderType;)V")
    )
    private void headSkullDye(Direction direction, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, SkullModelBase skullModelBase, RenderType renderType, Operation<Void> original) {
        if (dyeItem != null) {
            poseStack.pushPose();
            Vector3f colour = dyeItem.getColour();
            poseStack.translate(0.5F, 0.0F, 0.5F);
            poseStack.scale(-1.0F, -1.0F, 1.0F);
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(renderType);
            skullModelBase.setupAnim(g, f, 0.0F);
            skullModelBase.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, colour.x(), colour.y(), colour.z(), 1.0F);
            poseStack.popPose();
            return;
        }
        original.call(direction, f, g, poseStack, multiBufferSource, i, skullModelBase, renderType);
    }

    // todo: add dye support to items displayed on head, probably needs a lot of work
    /**
    @WrapOperation(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
    )
    private void headBlockDye(ItemInHandRenderer instance, LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext itemDisplayContext, boolean bl, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Operation<Void> original) {
        if (dyeItem != null) {
            Vector3f colour = dyeItem.getColour();
            return;
        }
        original.call(instance, livingEntity, itemStack, itemDisplayContext, bl, poseStack, multiBufferSource, i);
    }
    **/
}
