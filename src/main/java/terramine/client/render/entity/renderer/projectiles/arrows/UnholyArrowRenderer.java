package terramine.client.render.entity.renderer.projectiles.arrows;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.common.entity.projectiles.arrows.UnholyArrowEntity;

public class UnholyArrowRenderer extends ArrowRenderer<UnholyArrowEntity> {
    private static final ResourceLocation TEXTURE = TerraMine.id("textures/item/weapons/arrows/unholy_arrow.png");

    public UnholyArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull UnholyArrowEntity entity) {
        return TEXTURE;
    }
}
