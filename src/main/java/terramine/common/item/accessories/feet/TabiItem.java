package terramine.common.item.accessories.feet;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import terramine.common.init.ModItems;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.misc.AccessoriesHelper;
import terramine.common.utility.Utilities;

public class TabiItem extends AccessoryTerrariaItem {

	public TabiItem(ResourceKey<Item> key) {
		super(key);
	}

	@Override
	protected void curioTick(Player player, ItemStack stack) {
		if (player.level().isClientSide) {
			if (!AccessoriesHelper.isEquipped(ModItems.MASTER_NINJA_GEAR, player) && !AccessoriesHelper.isInInventory(ModItems.SHIELD_OF_CTHULHU, player)) {
				Utilities.playerDash(player, stack.getItem());
			}
		}
	}
}
