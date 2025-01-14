package terramine.mixin.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import terramine.common.item.accessories.ShieldAccessoryLikeItem;
import terramine.extensions.EntityRenderStateExtensions;
import terramine.extensions.PlayerStorages;

@Mixin(PlayerItemInHandLayer.class)
public class PlayerItemInHandLayerMixin {

    @Shadow @Final private ItemRenderer itemRenderer;

    // todo: vanity shield is technically not being used so in TerraMineClient, ItemProperties.register for shields won't function, either trick the game into using item (can have issues), set the shield to blocking here, or have a dummy boolean in custom shield that ItemProperties.register reads
    @ModifyVariable(method = "renderArmWithItem(Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), argsOnly = true)
    private ItemStack renderCustomShield(ItemStack itemStack, PlayerRenderState playerRenderState, BakedModel bakedModel, ItemStack itemStack2, ItemDisplayContext itemDisplayContext, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (((EntityRenderStateExtensions)playerRenderState).terrariaCraft$getLivingEntity() instanceof Player player && humanoidArm.equals(HumanoidArm.LEFT) && (itemStack.getItem() instanceof ShieldItem || itemStack.getItem() instanceof ShieldAccessoryLikeItem)) {
            if (((PlayerStorages)player).getTerrariaInventory().getItem(21) != ItemStack.EMPTY) {
                return ((PlayerStorages)player).getTerrariaInventory().getItem(21);
            }
        }
        return itemStack;
    }

    @ModifyVariable(method = "renderArmWithItem(Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), argsOnly = true)
    private BakedModel renderCustomShield(BakedModel bakedModel, PlayerRenderState playerRenderState, BakedModel bakedModel2, ItemStack itemStack, ItemDisplayContext itemDisplayContext, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (((EntityRenderStateExtensions)playerRenderState).terrariaCraft$getLivingEntity() instanceof Player player && humanoidArm.equals(HumanoidArm.LEFT) && (itemStack.getItem() instanceof ShieldItem || itemStack.getItem() instanceof ShieldAccessoryLikeItem)) {
            if (((PlayerStorages)player).getTerrariaInventory().getItem(21) != ItemStack.EMPTY) {
                return this.itemRenderer.getModel(itemStack, player.level(), player, 0);
            }
        }
        return bakedModel;
    }
}
