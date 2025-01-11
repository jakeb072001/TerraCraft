package terramine.mixin.player;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terramine.extensions.EntityRenderStateExtensions;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {

    @Inject(at = @At("RETURN"), method = "extractRenderState")
    public void extractLivingEntity(T entity, S entityRenderState, float f, CallbackInfo ci) {
        ((EntityRenderStateExtensions<T>)entityRenderState).terrariaCraft$setLivingEntity(entity);
    }
}
