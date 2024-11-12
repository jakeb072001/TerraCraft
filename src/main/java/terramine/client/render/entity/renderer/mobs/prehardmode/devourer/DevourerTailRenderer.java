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
import terramine.common.entity.mobs.prehardmode.devourer.DevourerTailEntity;
import terramine.common.init.ModModelLayers;

@Environment(value=EnvType.CLIENT)
public class DevourerTailRenderer extends MobRenderer<DevourerTailEntity, DevourerModel<DevourerTailEntity>> {

    private static final ResourceLocation TEXTURE = TerraMine.id("textures/entity/monsters/pre-hardmode/devourer/default.png");

    public DevourerTailRenderer(EntityRendererProvider.Context context) {
        super(context, new DevourerModel<>(context.bakeLayer(ModModelLayers.DEVOURER)), 0.40F);
    }

    @Override
    public void render(@NotNull DevourerTailEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull DevourerTailEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(@NotNull DevourerTailEntity entity, @NotNull PoseStack poseStack, float f, float g, float h, float i) {
        super.setupRotations(entity, poseStack, f, g, h, i);
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));
    }
}
