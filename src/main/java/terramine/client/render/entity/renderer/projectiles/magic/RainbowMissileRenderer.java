package terramine.client.render.entity.renderer.projectiles.magic;

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
import terramine.client.render.entity.model.projectiles.magic.MagicMissileModel;
import terramine.common.entity.projectiles.RainbowMissileEntity;
import terramine.common.init.ModModelLayers;

public class RainbowMissileRenderer extends EntityRenderer<RainbowMissileEntity> {

    private static final ResourceLocation TEXTURE = TerraMine.id("textures/entity/projectiles/magic/magic_missile.png");
    protected final EntityModel<RainbowMissileEntity> model;

    public RainbowMissileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new MagicMissileModel<>(context.bakeLayer(ModModelLayers.MAGIC_MISSILE));
    }

    @Override
    public void render(@NotNull RainbowMissileEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        poseStack.pushPose();
        this.model.setupAnim(entity, 0.0f, 0.0f, partialTicks, entity.getYRot(), entity.getXRot());
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Vector3f.XN.rotationDegrees(entity.getXRot() - 180));
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(this.getTextureLocation(entity))).color(0xe0e0e0);
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 0.9f, 0.9f, 0.9f, 1.0f);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull RainbowMissileEntity entity) {
        return TEXTURE;
    }
}