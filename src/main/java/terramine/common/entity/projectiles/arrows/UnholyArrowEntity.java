package terramine.common.entity.projectiles.arrows;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModEntities;
import terramine.common.init.ModItems;

public class UnholyArrowEntity extends AbstractArrow {
    private final float knockbackModifier;

    public UnholyArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);

        // todo: make piercing public
        this.setPierceLevel((byte) 4);
        this.setBaseDamage(4);
        knockbackModifier = 3;
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
    protected void doKnockback(LivingEntity livingEntity, DamageSource damageSource) {
        float var10000;
        label18: {
            if (this.firedFromWeapon != null) {
                Level var6 = this.level();
                if (var6 instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)var6;
                    var10000 = EnchantmentHelper.modifyKnockback(serverLevel, this.firedFromWeapon, livingEntity, damageSource, 0.0F) + knockbackModifier;
                    break label18;
                }
            }

            var10000 = knockbackModifier;
        }

        double d = var10000;
        if (d > 0.0) {
            double e = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(d * 0.6 * e);
            if (vec3.lengthSqr() > 0.0) {
                livingEntity.push(vec3.x, 0.1, vec3.z);
            }
        }
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return ModItems.UNHOLY_ARROW.getDefaultInstance();
    }
}
