package terramine.client.render.entity.renderer.projectiles.arrows;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.common.entity.projectiles.arrows.JesterArrowEntity;

public class JesterArrowRenderer extends ArrowRenderer<JesterArrowEntity> {
    private static final ResourceLocation TEXTURE = TerraMine.id("textures/item/weapons/arrows/jester_arrow.png");

    public JesterArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull JesterArrowEntity entity) {
        return TEXTURE;
    }
}
