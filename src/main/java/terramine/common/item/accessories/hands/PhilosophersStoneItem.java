package terramine.common.item.accessories.hands;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import terramine.common.events.LivingEntityPotionEffectCallback;
import terramine.common.init.ModItems;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.misc.AccessoriesHelper;
import terramine.extensions.MobEffectInstanceExtensions;

public class PhilosophersStoneItem extends AccessoryTerrariaItem {

	public PhilosophersStoneItem(ResourceKey<Item> key) {
        super(key);
        LivingEntityPotionEffectCallback.EVENT.register(PhilosophersStoneItem::onPotionStart);
	}

	// todo: maybe have a way to decrease duration of effect instead of just removing it
	/** Removes all beneficial effects when accessory is removed */
	@Override
	public void onUnequip(ItemStack stack, Player player) {
		for (MobEffectInstance effectInstance : player.getActiveEffects()) {
			if (effectInstance.getEffect().value().isBeneficial()) {
				player.removeEffect(effectInstance.getEffect());
			}
		}
	}

	/** Called when the potion effects start to apply this effect */
	private static void onPotionStart(LivingEntity living, MobEffectInstance newEffect, MobEffectInstance oldEffect, @Nullable Entity source) {
		if (newEffect.getEffect().value().isBeneficial() && !newEffect.isInfiniteDuration()) {
			if (AccessoriesHelper.isEquipped(ModItems.PHILOSOPHERS_STONE, living) && !AccessoriesHelper.isEquipped(ModItems.CHARM_OF_MYTHS, living)) {
				int duration = (int) (newEffect.getDuration() * 1.25f);
				((MobEffectInstanceExtensions) newEffect).terramine$setDuration(duration);
			}
		}
	}
}
