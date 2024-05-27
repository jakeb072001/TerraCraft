package terramine.common.item.accessories.necklace;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import terramine.common.init.ModAttributes;
import terramine.common.item.accessories.AccessoryTerrariaItem;

import java.util.UUID;

public class SorcererEmblemItem extends AccessoryTerrariaItem {

	@Override
	protected Multimap<Attribute, AttributeModifier> applyModifiers(ItemStack stack, LivingEntity entity, UUID uuid) {
		Multimap<Attribute, AttributeModifier> result = super.applyModifiers(stack, entity, uuid);
		AttributeModifier modifier = new AttributeModifier(uuid, "sorcerer_emblem_magic_attack_damage", 0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
		result.put(ModAttributes.MAGIC_ATTACK_DAMAGE.value(), modifier);
		return result;
	}
}
