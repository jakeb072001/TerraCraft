package terramine.common.entity.projectiles.arrows;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModEntities;
import terramine.common.init.ModItems;

public class UnholyArrowEntity extends AbstractArrow {

    public UnholyArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);

        this.setPierceLevel((byte) 4);
        this.setBaseDamage(4);
        this.setKnockback(3);
    }

    public UnholyArrowEntity(Level level, LivingEntity livingEntity) {
        this(ModEntities.UNHOLY_ARROW, level);
        this.setPos(livingEntity.getX(), livingEntity.getEyeY() - 0.10000000149011612, livingEntity.getZ());
        this.setOwner(livingEntity);
        if (livingEntity instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }
    }

    public void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        this.setBaseDamage(this.getBaseDamage() * 0.95d);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return ModItems.UNHOLY_ARROW.getDefaultInstance();
    }
}
