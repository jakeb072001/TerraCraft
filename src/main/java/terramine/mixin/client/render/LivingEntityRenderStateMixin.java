package terramine.mixin.client.render;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import terramine.extensions.LivingEntityRenderStateExtensions;

@Mixin(LivingEntityRenderState.class)
public abstract class LivingEntityRenderStateMixin extends EntityRenderState implements LivingEntityRenderStateExtensions {
    @Unique
    public LivingEntity livingEntity;

    @Override
    public LivingEntity terrariaCraft$getLivingEntity() {
        return livingEntity;
    }

    @Override
    public void terrariaCraft$setLivingEntity(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }
}
