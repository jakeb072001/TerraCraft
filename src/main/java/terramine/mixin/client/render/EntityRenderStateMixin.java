package terramine.mixin.client.render;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import terramine.extensions.EntityRenderStateExtensions;

@Mixin(EntityRenderState.class)
public abstract class EntityRenderStateMixin<T extends Entity> implements EntityRenderStateExtensions<T> {
    @Unique
    public T entity;

    @Override
    public T terrariaCraft$getLivingEntity() {
        return entity;
    }

    @Override
    public void terrariaCraft$setLivingEntity(T entity) {
        this.entity = entity;
    }
}
