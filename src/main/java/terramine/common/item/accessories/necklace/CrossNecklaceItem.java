package terramine.common.item.accessories.necklace;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import terramine.common.item.accessories.AccessoryTerrariaItem;

public class CrossNecklaceItem extends AccessoryTerrariaItem {

	public static final double HURT_RESISTANCE_MULTIPLIER = 3; // Hurt invuln is multiplied by this factor

	public CrossNecklaceItem(ResourceKey<Item> key) {
		super(key);
	}

	@Override
	public SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND.value());
	}
}
