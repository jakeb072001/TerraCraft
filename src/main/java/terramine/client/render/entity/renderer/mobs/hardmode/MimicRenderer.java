package terramine.client.render.entity.renderer.mobs.hardmode;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.client.render.entity.model.mobs.hardmode.MimicModel;
import terramine.client.render.entity.states.TerrariaLivingEntityRenderState;
import terramine.common.entity.mobs.hardmode.MimicEntity;
import terramine.common.init.ModModelLayers;

public class MimicRenderer extends MobRenderer<MimicEntity, TerrariaLivingEntityRenderState, MimicModel> {

    private static final ResourceLocation TEXTURE = TerraMine.id("textures/entity/mimic.png");

    public MimicRenderer(EntityRendererProvider.Context context) {
        super(context, new MimicModel(context.bakeLayer(ModModelLayers.MIMIC)), 0.45F);
        addLayer(new MimicChestLayer(this, context.getModelSet()));
    }

    @Override
    public void render(@NotNull TerrariaLivingEntityRenderState mimic, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(mimic, matrixStack, buffer, packedLight);
    }

    @Override
    public @NotNull TerrariaLivingEntityRenderState createRenderState() {
        return new TerrariaLivingEntityRenderState();
    }

    @Override
    public void extractRenderState(MimicEntity mimicEntity, TerrariaLivingEntityRenderState terrariaLivingEntityRenderState, float f) {
        super.extractRenderState(mimicEntity, terrariaLivingEntityRenderState, f);
        terrariaLivingEntityRenderState.entity = mimicEntity;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull TerrariaLivingEntityRenderState entity) {
        return TEXTURE;
    }
}
