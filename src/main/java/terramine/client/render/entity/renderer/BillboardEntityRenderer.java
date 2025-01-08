package terramine.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import terramine.client.render.entity.states.TerrariaEntityRenderState;

public abstract class BillboardEntityRenderer<T extends Entity, M extends TerrariaEntityRenderState> extends EntityRenderer<T, M> {
    private final RenderType RENDER_TYPE;

    public BillboardEntityRenderer(EntityRendererProvider.Context context, ResourceLocation texture) {
        super(context);
        RENDER_TYPE = RenderType.entityCutoutNoCull(texture);
    }

    @Override
    public @NotNull M createRenderState() {
        return (M) new TerrariaEntityRenderState();
    }

    @Override
    public void render(M entity, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(1F, 1F, 1F);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        VertexConsumer vertexConsumer = buffer.getBuffer(RENDER_TYPE);
        vertex(vertexConsumer, matrix4f, pose, packedLight, 0.0F, 0, 0, 1);
        vertex(vertexConsumer, matrix4f, pose, packedLight, 1.0F, 0, 1, 1);
        vertex(vertexConsumer, matrix4f, pose, packedLight, 1.0F, 1, 1, 0);
        vertex(vertexConsumer, matrix4f, pose, packedLight, 0.0F, 1, 0, 0);
        poseStack.popPose();
        super.render(entity, poseStack, buffer, packedLight);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, PoseStack.Pose pose, int i, float f, int j, int k, int l) {
        vertexConsumer.addVertex(matrix4f, f - 0.5F, (float)j - 0.25F, 0.0F).setColor(255, 255, 255, 255).setUv((float)k, (float)l).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(i, i).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}
