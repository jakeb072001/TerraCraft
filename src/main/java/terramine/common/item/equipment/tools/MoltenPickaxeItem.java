package terramine.common.item.equipment.tools;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class MoltenPickaxeItem extends PickaxeItem {
    public MoltenPickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemStack, @NotNull LivingEntity livingEntity, @NotNull LivingEntity livingEntity2) {
        if (livingEntity.getRandom().nextFloat() <= 0.10) {
            livingEntity.setRemainingFireTicks(6);
        }
        return super.hurtEnemy(itemStack, livingEntity, livingEntity2);
    }
}
