package terramine.client.render.accessory.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import terramine.TerraMine;
import terramine.client.render.RenderTypes;
import terramine.client.render.accessory.model.ArmsModel;

public class GlowingGloveCurioRenderer extends GloveCurioRenderer {

    private final ResourceLocation defaultGlowTexture;
    private final ResourceLocation slimGlowTexture;

    public GlowingGloveCurioRenderer(String name, ArmsModel defaultModel, ArmsModel slimModel) {
        super(name, defaultModel, slimModel);
        defaultGlowTexture = TerraMine.id(String.format("textures/entity/accessory/glove/%s/%s_default_glow.png", name, name));
        slimGlowTexture = TerraMine.id(String.format("textures/entity/accessory/glove/%s/%s_slim_glow.png", name, name));
    }

    private ResourceLocation getGlowTexture(boolean hasSlimArms) {
        return hasSlimArms ? slimGlowTexture : defaultGlowTexture;
    }

    @Override
    protected void renderArm(ArmsModel model, PoseStack poseStack, MultiBufferSource multiBufferSource, HumanoidArm armSide, int light, boolean hasSlimArms, boolean hasFoil) {
        super.renderArm(model, poseStack, multiBufferSource, armSide, light, hasSlimArms, hasFoil);
        RenderType renderType = RenderTypes.unlit(getGlowTexture(hasSlimArms));
        VertexConsumer builder = ItemRenderer.getFoilBuffer(multiBufferSource, renderType, false, hasFoil);
        model.renderArm(armSide, poseStack, builder, LightTexture.pack(15, 15), OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

    @Override
    protected void renderFirstPersonArm(ArmsModel model, ModelPart arm, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, boolean hasSlimArms, boolean hasFoil) {
        super.renderFirstPersonArm(model, arm, poseStack, multiBufferSource, light, hasSlimArms, hasFoil);
        VertexConsumer builder = ItemRenderer.getFoilBuffer(multiBufferSource, RenderTypes.unlit(getGlowTexture(hasSlimArms)), false, hasFoil);
        arm.render(poseStack, builder, LightTexture.pack(15, 15), OverlayTexture.NO_OVERLAY);
    }
}
