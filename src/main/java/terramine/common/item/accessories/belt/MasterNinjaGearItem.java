package terramine.common.item.accessories.belt;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import terramine.common.init.ModItems;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.misc.AccessoriesHelper;
import terramine.common.utility.Utilities;

public class MasterNinjaGearItem extends AccessoryTerrariaItem {

    @Override
	protected void curioTick(Player player, ItemStack stack) {
		if (player.level().isClientSide) {
			if (!AccessoriesHelper.isInInventory(ModItems.SHIELD_OF_CTHULHU, player)) {
				Utilities.playerDash(player, stack.getItem());
			}
		}
	}
}
