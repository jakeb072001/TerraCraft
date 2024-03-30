package terramine.common.entity.projectiles.arrows;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModEntities;

public class JesterArrowEntity extends AbstractArrow {

    public JesterArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);

        // todo: make piercing have no limit
        this.setPierceLevel((byte) 127);
        this.setBaseDamage(3.5d);
        this.setKnockback(4);
        this.setNoGravity(true);
    }

    public JesterArrowEntity(Level level, LivingEntity livingEntity) {
        this(ModEntities.JESTER_ARROW, level);
        this.setPos(livingEntity.getX(), livingEntity.getEyeY() - 0.10000000149011612, livingEntity.getZ());
        this.setOwner(livingEntity);
        if (livingEntity instanceof Player) {
            this.pickup = Pickup.DISALLOWED;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.tickDespawn();
    }

    public void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        this.setBaseDamage(this.getBaseDamage() * 0.90d);
    }

    public void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);

        if (this.level().isClientSide()) {
            // todo: play better particle
            this.level().addParticle(ParticleTypes.CLOUD, this.getRandomX(0.5), this.getRandomY() - 0.2, this.getRandomZ(0.5), (16 & 0xFF) / 255.0, (8 & 0xFF) / 255.0, (0) / 255.0);
        } else {
            this.level().broadcastEntityEvent(this, (byte)0);
        }

        this.remove(RemovalReason.DISCARDED);
    }

    public float getWaterInertia() {
        return 1;
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}
