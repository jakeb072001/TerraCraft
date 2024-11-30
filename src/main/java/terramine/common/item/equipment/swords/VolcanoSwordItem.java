package terramine.common.item.equipment.swords;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import org.jetbrains.annotations.NotNull;

public class VolcanoSwordItem extends SwordItem {
    public VolcanoSwordItem(ToolMaterial tier, float f, float g, Properties properties) {
        super(tier, f, g, properties);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemStack, @NotNull LivingEntity livingEntity, @NotNull LivingEntity livingEntity2) {
        if (livingEntity.getRandom().nextFloat() <= 0.50) {
            livingEntity.setRemainingFireTicks(6);
        }
        return super.hurtEnemy(itemStack, livingEntity, livingEntity2);
    }
}
