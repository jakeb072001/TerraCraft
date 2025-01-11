package terramine.common.item.accessories.hands;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import terramine.common.init.ModComponents;
import terramine.common.item.accessories.AccessoryTerrariaItem;

public class BandOfStarpowerItem extends AccessoryTerrariaItem {

	public BandOfStarpowerItem(ResourceKey<Item> key) {
		super(key);
	}

	@Override
	public void onEquip(ItemStack stack, Player player) {
		ModComponents.MANA_HANDLER.get(player).addMaxMana(20);
	}

    @Override
	public void onUnequip(ItemStack stack, Player player) {
		ModComponents.MANA_HANDLER.get(player).addMaxMana(-20);
		ModComponents.MANA_HANDLER.get(player).addCurrentMana(-20);
	}

	@Override
	public boolean isGlove() {
		return true;
	}
}
