package terramine.common.item.accessories.feet;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import terramine.TerraMine;
import terramine.common.init.ModParticles;
import terramine.common.init.ModSoundEvents;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.utility.RocketBootHelper;

import java.util.UUID;

public class LightningBootsItem extends AccessoryTerrariaItem {

	public static final AttributeModifier SPEED_BOOST_MODIFIER_WALK = new AttributeModifier(TerraMine.id("lightning_boots_movement_speed_walk"), 0.08, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	public RocketBootHelper rocketHelper = new RocketBootHelper();
	public double speed = 0.4D;

	public LightningBootsItem() {
		rocketHelper.setSoundSettings(ModSoundEvents.SPECTRE_BOOTS, 1f, 1f);
		rocketHelper.setParticleSettings(ModParticles.BLUE_POOF, ParticleTypes.POOF);
	}

	@Override
	public void curioTick(Player player, ItemStack stack) {
		rocketHelper.rocketFly(speed, 4, player);
	}

	@Override
	public void onEquip(ItemStack stack, Player player) {
		AttributeInstance movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);

		if (movementSpeed == null) {
			TerraMine.LOGGER.debug("Entity {} missing entity attribute(s)", this);
			return;
		}

		addModifier(movementSpeed, SPEED_BOOST_MODIFIER_WALK);
	}

	@Override
	public void onUnequip(ItemStack stack, Player player) {
		AttributeInstance movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);

		if (movementSpeed == null) {
			TerraMine.LOGGER.debug("Entity {} missing entity attribute(s)", this);
			return;
		}
		removeModifier(movementSpeed, SPEED_BOOST_MODIFIER_WALK);
	}
}

