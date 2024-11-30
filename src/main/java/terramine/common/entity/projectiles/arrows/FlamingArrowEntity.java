package terramine.common.entity.projectiles.arrows;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModEntities;
import terramine.common.init.ModItems;

public class FlamingArrowEntity extends AbstractArrow {

    public FlamingArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);

        this.setBaseDamage(3);
    }

    public FlamingArrowEntity(Level level, LivingEntity livingEntity) {
        this(ModEntities.FLAMING_ARROW, level);
        this.setPos(livingEntity.getX(), livingEntity.getEyeY() - 0.10000000149011612, livingEntity.getZ());
        this.setOwner(livingEntity);
        if (livingEntity instanceof Player) {
            this.pickup = Pickup.ALLOWED;
        }
    }


    public void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (random.nextInt(3) == 0) {
            entityHitResult.getEntity().setRemainingFireTicks(3);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            if (this.isInGround()) {
                if (this.inGroundTime % 30 == 0) { // todo: rewrite to use random, also make entity turn into regular arrow entity 1/3 of the time it hits anything
                    this.spawnParticles();
                }
            } else {
                this.spawnParticles();
            }
        } else if (this.isInGround() && this.inGroundTime != 0 && this.inGroundTime >= 600) {
            this.level().broadcastEntityEvent(this, (byte)0);
        }
    }

    private void spawnParticles() {
        this.level().addParticle(ParticleTypes.SMALL_FLAME, this.getRandomX(0.5), this.getRandomY() - 0.2, this.getRandomZ(0.5), (16 & 0xFF) / 255.0, (8 & 0xFF) / 255.0, (0) / 255.0);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        if (random.nextInt(3) == 0) {
            return ModItems.FLAMING_ARROW.getDefaultInstance();
        }
        return Items.ARROW.getDefaultInstance();
    }
}
