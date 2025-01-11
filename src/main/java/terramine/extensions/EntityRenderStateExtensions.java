package terramine.extensions;

import net.minecraft.world.entity.Entity;

public interface EntityRenderStateExtensions<T extends Entity> {

	T terrariaCraft$getLivingEntity();

	void terrariaCraft$setLivingEntity(T livingEntity);
}
