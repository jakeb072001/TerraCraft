package terramine.common.item.accessories.hands;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import terramine.TerraMine;
import terramine.common.item.accessories.AccessoryTerrariaItem;

import java.util.UUID;

public class ShackleItem extends AccessoryTerrariaItem {

	public ShackleItem() {
		canShowTooltip(false);
	}

	@Override
	protected Multimap<Holder<Attribute>, AttributeModifier> applyModifiers(ItemStack stack, LivingEntity entity, UUID uuid) {
		Multimap<Holder<Attribute>, AttributeModifier> result = super.applyModifiers(stack, entity, uuid);
		AttributeModifier modifier = new AttributeModifier(TerraMine.id("shackle_defence"),
				1, AttributeModifier.Operation.ADD_VALUE);
		result.put(Attributes.ARMOR, modifier);
		return result;
	}

	@Override
	public boolean isBothHands() {
		return true;
	}

	@Override
	public boolean isGlove() {
		return true;
	}

	@Override
	public SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON.value());
	}
}
