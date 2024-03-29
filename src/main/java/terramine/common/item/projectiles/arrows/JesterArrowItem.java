package terramine.common.item.projectiles.arrows;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import terramine.common.entity.projectiles.arrows.JesterArrowEntity;

public class JesterArrowItem extends ArrowItem {
    public JesterArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level level, @NotNull ItemStack itemStack, @NotNull LivingEntity livingEntity) {
        return new JesterArrowEntity(level, livingEntity);
    }
}
