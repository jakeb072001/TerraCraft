package terramine.common.item.accessories.hands;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import terramine.TerraMine;
import terramine.common.item.accessories.AccessoryTerrariaItem;

import java.util.UUID;

import static terramine.common.utility.Utilities.autoSwing;

public class FireGauntletItem extends AccessoryTerrariaItem {

	@Override
	protected Multimap<Holder<Attribute>, AttributeModifier> applyModifiers(ItemStack stack, LivingEntity entity, UUID uuid) {
		Multimap<Holder<Attribute>, AttributeModifier> result = super.applyModifiers(stack, entity, uuid);
		AttributeModifier attackModifier = new AttributeModifier(uuid,
				TerraMine.id("fire_gauntlet_attack").toString(),
				0.12, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
		AttributeModifier reachModifier = new AttributeModifier(uuid,
				TerraMine.id("fire_gauntlet_range").toString(),
				0.5, AttributeModifier.Operation.ADD_VALUE);
		AttributeModifier attackRangeModifier = new AttributeModifier(uuid,
				TerraMine.id("fire_gauntlet_attack_range").toString(),
				3, AttributeModifier.Operation.ADD_VALUE);
		result.put(Attributes.ATTACK_SPEED, attackModifier);
		result.put(Attributes.ATTACK_DAMAGE, attackModifier);
		result.put(Attributes.BLOCK_INTERACTION_RANGE, reachModifier);
		result.put(Attributes.ENTITY_INTERACTION_RANGE, attackRangeModifier);
		return result;
	}

	@Override
	public void curioTick(Player player, ItemStack stack) {
		if (player.isLocalPlayer()) {
			autoSwing();
		}
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
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_NETHERITE.value());
	}
}
