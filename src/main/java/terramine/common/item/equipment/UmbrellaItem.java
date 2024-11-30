package terramine.common.item.equipment;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModItems;
import terramine.common.item.TerrariaItem;

public class UmbrellaItem extends TerrariaItem {

	public UmbrellaItem() {
		super(new Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant(), false);
		//DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
	}

    /* TODO: wait for fapi/lib
    @Override
    public boolean isShield(ItemStack stack, LivingEntity entity) {
        return true;
    }*/

	public static HeldStatus getHeldStatusForHand(LivingEntity entity, InteractionHand hand) {
		if (entity.getItemInHand(hand).getItem() != ModItems.UMBRELLA) {
			return HeldStatus.NONE;
		}

		if (entity.isUsingItem() && entity.getUsedItemHand() == hand && !entity.getUseItem().isEmpty()
				&& entity.getUseItem().getUseAnimation() == ItemUseAnimation.BLOCK) {
			return HeldStatus.BLOCKING;
		}

		return HeldStatus.HELD_UP;
	}

	public static boolean isHeldUpInEitherHand(LivingEntity entity) {
		for (InteractionHand hand : InteractionHand.values()) {
			if (getHeldStatusForHand(entity, hand) == HeldStatus.HELD_UP) {
				return true;
			}
		}

		return false;
	}

	@Override
	public @NotNull ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
		return ItemUseAnimation.BLOCK;
	}

	@Override
	public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
		return 72000;
	}

	@Override
	public InteractionResult use(@NotNull Level world, Player player, @NotNull InteractionHand hand) {
		player.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}

	public enum HeldStatus {
		NONE,
		HELD_UP,
		BLOCKING
	}
}
