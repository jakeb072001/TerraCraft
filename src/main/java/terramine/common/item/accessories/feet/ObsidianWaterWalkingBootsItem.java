package terramine.common.item.accessories.feet;

import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FluidState;
import terramine.common.init.ModComponents;
import terramine.common.init.ModItems;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.misc.AccessoriesHelper;

public class ObsidianWaterWalkingBootsItem extends AccessoryTerrariaItem {

	public ObsidianWaterWalkingBootsItem(ResourceKey<Item> key) {
        super(key);
        //noinspection UnstableApiUsage
        LivingFluidCollisionCallback.EVENT.register(ObsidianWaterWalkingBootsItem::onFluidCollision);
	}

	@Override
	public void tick(ItemStack stack, Player player) {
		ModComponents.SWIM_ABILITIES.maybeGet(player).ifPresent(swimAbilities -> {
			if (player.isInWater()) {
				swimAbilities.setWet(true);
			} else if (player.onGround() || player.getAbilities().flying) {
				swimAbilities.setWet(false);
			}
		});

		super.tick(stack, player);
	}

	private static boolean onFluidCollision(LivingEntity entity, FluidState fluidState) {
		if (AccessoriesHelper.isEquipped(ModItems.OBSIDIAN_WATER_WALKING_BOOTS, entity) && !entity.isCrouching()) {
			entity.resetFallDistance();
			return !fluidState.is(FluidTags.LAVA);
		}

		return false;
	}
}
