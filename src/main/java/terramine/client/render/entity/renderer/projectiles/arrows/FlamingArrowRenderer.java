package terramine.client.render.entity.renderer.projectiles.arrows;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.common.entity.projectiles.arrows.FlamingArrowEntity;

public class FlamingArrowRenderer extends ArrowRenderer<FlamingArrowEntity, ArrowRenderState> {
    private static final ResourceLocation TEXTURE = TerraMine.id("textures/item/weapons/arrows/flaming_arrow.png");

    public FlamingArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ArrowRenderState entity) {
        return TEXTURE;
    }
}
