package terramine.common.item.accessories.necklace;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import terramine.TerraMine;
import terramine.common.init.ModAttributes;
import terramine.common.item.accessories.AccessoryTerrariaItem;

import java.util.UUID;

public class SorcererEmblemItem extends AccessoryTerrariaItem {

	public SorcererEmblemItem(ResourceKey<Item> key) {
		super(key);
	}

	@Override
	protected Multimap<Holder<Attribute>, AttributeModifier> applyModifiers(ItemStack stack, LivingEntity entity, UUID uuid) {
		Multimap<Holder<Attribute>, AttributeModifier> result = super.applyModifiers(stack, entity, uuid);
		AttributeModifier modifier = new AttributeModifier(TerraMine.id("sorcerer_emblem_magic_attack_damage"), 0.15f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
		result.put(ModAttributes.MAGIC_ATTACK_DAMAGE, modifier);
		return result;
	}
}
