package terramine.common.item.equipment.swords;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class VolcanoSwordItem extends SwordItem {
    public VolcanoSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemStack, @NotNull LivingEntity livingEntity, @NotNull LivingEntity livingEntity2) {
        if (livingEntity.getRandom().nextFloat() <= 0.50) {
            livingEntity.setRemainingFireTicks(6);
        }
        return super.hurtEnemy(itemStack, livingEntity, livingEntity2);
    }
}
