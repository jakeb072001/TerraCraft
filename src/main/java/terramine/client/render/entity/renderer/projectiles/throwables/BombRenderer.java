package terramine.client.render.entity.renderer.projectiles.throwables;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.client.render.entity.model.projectiles.throwables.BombModel;
import terramine.common.entity.throwables.BombEntity;
import terramine.common.init.ModModelLayers;

public class BombRenderer extends EntityRenderer<BombEntity> {

    private static final ResourceLocation TEXTURE = TerraMine.id("textures/item/weapons/throwables/bomb/bomb_lit.png");
    private static final ResourceLocation STICKY_TEXTURE = TerraMine.id("textures/item/weapons/throwables/bomb/sticky_bomb_lit.png");
    private static final ResourceLocation BOUNCY_TEXTURE = TerraMine.id("textures/item/weapons/throwables/bomb/bouncy_bomb_lit.png");
    protected final EntityModel<BombEntity> model;

    public BombRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BombModel(context.bakeLayer(ModModelLayers.BOMB));
    }

    @Override
    public void render(@NotNull BombEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        poseStack.pushPose();
        this.model.setupAnim(entity, 0.0f, 0.0f, 0.0f, entity.getYRot(), entity.getXRot());
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Vector3f.XN.rotationDegrees(entity.getXRot() - 90));
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(this.getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull BombEntity entity) {
        if (entity.isSticky()) {
            return STICKY_TEXTURE;
        }
        if (entity.isBouncy()) {
            return BOUNCY_TEXTURE;
        }
        return TEXTURE;
    }
}
