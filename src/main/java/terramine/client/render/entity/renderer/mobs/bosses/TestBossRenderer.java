package terramine.client.render.entity.renderer.mobs.bosses;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.client.render.entity.model.mobs.bosses.TestBossModel;
import terramine.common.entity.mobs.bosses.TestBoss;
import terramine.common.init.ModModelLayers;

/**
 * Testing, remove later
 */
@Environment(value= EnvType.CLIENT)
public class TestBossRenderer extends MobRenderer<TestBoss, ZombieRenderState, TestBossModel<ZombieRenderState>> {

    private static final ResourceLocation TEXTURE = TerraMine.id("textures/entity/monsters/pre-hardmode/crimera/default.png");

    public TestBossRenderer(EntityRendererProvider.Context context) {
        super(context, new TestBossModel<>(context.bakeLayer(ModModelLayers.TEST_BOSS)), 0.70F);
    }

    @Override
    public @NotNull ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ZombieRenderState entity) {
        return TEXTURE;
    }

    @Override
    public void render(@NotNull ZombieRenderState entity, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight) {
        super.render(entity, matrixStack, buffer, packedLight);
    }
}
