package terramine.common.item.accessories.necklace;

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
import terramine.common.init.ModAttributes;
import terramine.common.item.accessories.AccessoryTerrariaItem;

import java.util.UUID;

public class AvengerEmblemItem extends AccessoryTerrariaItem {

	public AvengerEmblemItem(ResourceKey<Item> key) {
		super(key);
	}

	@Override
	protected Multimap<Holder<Attribute>, AttributeModifier> applyModifiers(ItemStack stack, LivingEntity entity, UUID uuid) {
		Multimap<Holder<Attribute>, AttributeModifier> result = super.applyModifiers(stack, entity, uuid);
		AttributeModifier modifier = new AttributeModifier(TerraMine.id("avenger_emblem_attack_damage"), 0.12f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
		result.put(Attributes.ATTACK_DAMAGE, modifier);
		result.put(ModAttributes.RANGER_ATTACK_DAMAGE, modifier);
		result.put(ModAttributes.MAGIC_ATTACK_DAMAGE, modifier);
		return result;
	}
}
