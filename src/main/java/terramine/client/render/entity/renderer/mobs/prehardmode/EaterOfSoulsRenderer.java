package terramine.client.render.entity.renderer.mobs.prehardmode;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.client.render.entity.model.mobs.prehardmode.EaterOfSoulsModel;
import terramine.client.render.entity.states.TerrariaLivingEntityRenderState;
import terramine.common.entity.mobs.prehardmode.EaterOfSoulsEntity;
import terramine.common.init.ModModelLayers;

@Environment(value=EnvType.CLIENT)
public class EaterOfSoulsRenderer extends MobRenderer<EaterOfSoulsEntity, TerrariaLivingEntityRenderState, EaterOfSoulsModel<TerrariaLivingEntityRenderState>> {

    private static final ResourceLocation TEXTURE = TerraMine.id("textures/entity/monsters/pre-hardmode/eater_of_souls/default.png");

    public EaterOfSoulsRenderer(EntityRendererProvider.Context context) {
        super(context, new EaterOfSoulsModel<>(context.bakeLayer(ModModelLayers.EATER_OF_SOULS)), 0.80F);
    }

    @Override
    public @NotNull TerrariaLivingEntityRenderState createRenderState() {
        return new TerrariaLivingEntityRenderState();
    }

    @Override
    public void render(@NotNull TerrariaLivingEntityRenderState renderState, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(renderState, matrixStack, buffer, packedLight);
    }

    @Override
    protected void setupRotations(@NotNull TerrariaLivingEntityRenderState renderState, @NotNull PoseStack poseStack, float f, float g) {
        super.setupRotations(renderState, poseStack, f, g);
        poseStack.mulPose(Axis.XP.rotationDegrees(renderState.xRot));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(TerrariaLivingEntityRenderState livingEntityRenderState) {
        return TEXTURE;
    }
}
