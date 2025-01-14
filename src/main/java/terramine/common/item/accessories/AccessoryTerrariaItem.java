package terramine.common.item.accessories;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import terramine.common.events.PlayHurtSoundCallback;
import terramine.common.init.ModComponents;
import terramine.common.item.TerrariaItem;
import terramine.common.misc.AccessoriesHelper;
import terramine.common.misc.TerrariaInventory;
import terramine.extensions.Accessories;
import terramine.extensions.PlayerStorages;

import java.util.UUID;

public class AccessoryTerrariaItem extends TerrariaItem implements Accessories {
	public AccessoryTerrariaItem(ResourceKey<Item> key) {
		super(key);
		initialise();
	}

	public AccessoryTerrariaItem(Properties settings) {
		super(settings, false);
		initialise();
	}

	private void initialise() {
		PlayHurtSoundCallback.EVENT.register(this::playExtraHurtSound);
	}

	public static boolean equipItem(Player player, ItemStack stack) {
		TerrariaInventory inventory = ((PlayerStorages)player).getTerrariaInventory();
			for (int i = 0; i < 5 + ModComponents.ACCESSORY_SLOTS_ADDER.get(player).get(); i++) {
				if (inventory.getItem(i).isEmpty()) {
					ItemStack newStack = stack.copy();
					inventory.setItem(i, newStack);
					Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
					SoundEvent soundEvent = equippable != null && equippable.swappable() ? equippable.equipSound().value() : null;
					if (!stack.isEmpty() && soundEvent != null) {
						player.gameEvent(GameEvent.EQUIP);
						player.playSound(soundEvent, 1.0F, 1.0F);
					}
					stack.setCount(0);
					return true;
				}
			}
		return false;
	}

	@Override
	public InteractionResult use(@NotNull Level level, Player user, @NotNull InteractionHand hand) {
		ItemStack stack = user.getItemInHand(hand);
		if (equipItem(user, stack)) {
			// Play right click equip sound
			SoundInfo sound = this.getEquipSoundInfo();
			user.playSound(sound.soundEvent(), sound.volume(), sound.pitch());

			return InteractionResult.SUCCESS_SERVER;
		}

		return super.use(level, user, hand);
	}

	@Override
	public void tick(ItemStack stack, Player player) {
		curioTick(player, stack);
	}

	protected void curioTick(Player player, ItemStack stack) {
	}

	public boolean isGlove() {
		return false;
	}

	public boolean isBothHands() {
		return false;
	}

	@Override
	public final Multimap<Holder<Attribute>, AttributeModifier> getModifiers(ItemStack stack, Player player, UUID uuid) {
		Multimap<Holder<Attribute>, AttributeModifier> modifiers = Accessories.super.getModifiers(stack, player, uuid);
		Multimap<Holder<Attribute>, AttributeModifier> accessoryModifiers = this.applyModifiers(stack, player, uuid);
		modifiers.putAll(accessoryModifiers);
		return modifiers;
	}

	protected Multimap<Holder<Attribute>, AttributeModifier> applyModifiers(ItemStack stack, LivingEntity entity, UUID uuid) {
		return HashMultimap.create();
	}

	/**
	 * @return The {@link SoundInfo} to play when the accessory is right-click equipped
	 */
	public SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC.value());
	}

	/**
	 * @return An extra {@link SoundEvent} to play when an entity wearing this accessory is hurt
	 */
	protected SoundEvent getExtraHurtSound() {
		return null;
	}

	private void playExtraHurtSound(Player player, float volume, float pitch) { // keeping for now, may use for moon charm and Neptune's shell
		SoundEvent hurtSound = getExtraHurtSound();

		if (hurtSound != null && AccessoriesHelper.isEquipped(this, player, true)) {
			player.playSound(hurtSound, volume, pitch);
		}
	}

	public static void addModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (!instance.hasModifier(modifier.id())) {
			instance.addTransientModifier(modifier);
		}
	}

	public static void removeModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (instance.hasModifier(modifier.id())) {
			instance.removeModifier(modifier);
		}
	}

	public record SoundInfo(SoundEvent soundEvent, float volume, float pitch) {

		// Changes access modifier to public
		@SuppressWarnings({"RedundantRecordConstructor", "RedundantSuppression"})
		public SoundInfo {}

		public SoundInfo(SoundEvent soundEvent) {
			this(soundEvent, 1f, 1f);
		}
	}
}
