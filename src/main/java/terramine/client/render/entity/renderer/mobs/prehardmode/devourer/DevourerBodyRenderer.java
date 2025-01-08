package terramine.client.render.entity.renderer.mobs.prehardmode.devourer;

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
import terramine.client.render.entity.model.mobs.prehardmode.DevourerModel;
import terramine.client.render.entity.states.TerrariaLivingEntityRenderState;
import terramine.common.entity.mobs.prehardmode.devourer.DevourerBodyEntity;
import terramine.common.init.ModModelLayers;

@Environment(value=EnvType.CLIENT)
public class DevourerBodyRenderer extends MobRenderer<DevourerBodyEntity, TerrariaLivingEntityRenderState, DevourerModel<TerrariaLivingEntityRenderState>> {

    private static final ResourceLocation TEXTURE = TerraMine.id("textures/entity/monsters/pre-hardmode/devourer/default.png");

    public DevourerBodyRenderer(EntityRendererProvider.Context context) {
        super(context, new DevourerModel<>(context.bakeLayer(ModModelLayers.DEVOURER_BODY)), 0.40F);
    }

    @Override
    public void render(@NotNull TerrariaLivingEntityRenderState entity, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, matrixStack, buffer, packedLight);
    }

    @Override
    public TerrariaLivingEntityRenderState createRenderState() {
        return new TerrariaLivingEntityRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull TerrariaLivingEntityRenderState entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(@NotNull TerrariaLivingEntityRenderState renderState, @NotNull PoseStack poseStack, float f, float g) {
        super.setupRotations(renderState, poseStack, f, g);
        poseStack.mulPose(Axis.XP.rotationDegrees(renderState.xRot));
    }
}
