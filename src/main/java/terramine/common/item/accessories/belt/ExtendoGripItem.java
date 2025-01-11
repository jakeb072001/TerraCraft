package terramine.common.item.accessories.belt;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import terramine.TerraMine;
import terramine.common.item.accessories.AccessoryTerrariaItem;

import java.util.UUID;

public class ExtendoGripItem extends AccessoryTerrariaItem {
	public ExtendoGripItem(ResourceKey<Item> key) {
		super(key);
	}

	@Override
	protected Multimap<Holder<Attribute>, AttributeModifier> applyModifiers(ItemStack stack, LivingEntity entity, UUID uuid) {
		Multimap<Holder<Attribute>, AttributeModifier> result = super.applyModifiers(stack, entity, uuid);
		AttributeModifier modifier = new AttributeModifier(TerraMine.id("extendo_grip_range"),
				3, AttributeModifier.Operation.ADD_VALUE);
		result.put(Attributes.BLOCK_INTERACTION_RANGE, modifier);
		return result;
	}
}
