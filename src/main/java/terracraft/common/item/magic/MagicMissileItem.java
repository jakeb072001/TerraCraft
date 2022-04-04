package terracraft.common.item.magic;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import terracraft.common.entity.MagicMissileEntity;
import terracraft.common.init.ModEntities;
import terracraft.common.init.ModSoundEvents;
import terracraft.common.item.MagicTerrariaItem;

public class MagicMissileItem extends MagicTerrariaItem {

    public MagicMissileItem() {
        this.setVars(5, 14);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        Player player = (Player)entity;
        if (canUse(player)) {
            MagicMissileEntity missile = ModEntities.MAGIC_MISSILE.create(world);
            if (missile != null) {
                missile.setPos(player.position().x(), player.position().y() + 2, player.position().z());
                missile.setOwner(player);
                missile.setCooldownItem(this);
                missile.setParticleType(0);
                missile.setSpeed(0.8f);
                missile.setDamage(2.7f);
                missile.liquidCollision(true, false);
                world.addFreshEntity(missile);
                world.playSound(null, player.blockPosition(), ModSoundEvents.MAGIC_MISSILE_SHOOT, SoundSource.PLAYERS, 0.50f, 1f);
            }
        }
        return super.finishUsingItem(stack, world, entity);
    }
}