package terramine.common.item.curio;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.common.events.PlayHurtSoundCallback;
import terramine.common.item.TerrariaItem;
import terramine.common.trinkets.TrinketsHelper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrinketTerrariaItem extends TerrariaItem implements Trinket {
	public boolean effectEnabled = true;

	public TrinketTerrariaItem() {
		initialise();
	}

	public TrinketTerrariaItem(Properties settings) {
		super(settings, false);
		initialise();
	}

	private void initialise() {
		// DispenserBlock.registerBehavior(this, TrinketItem.TRINKET_DISPENSER_BEHAVIOR); TODO: bug, missing in trinkets rewrite
		PlayHurtSoundCallback.EVENT.register(this::playExtraHurtSound);
		TrinketsApi.registerTrinket(this, this);
	}

	@Override
	public boolean overrideOtherStackedOnMe(ItemStack slotStack, ItemStack holdingStack, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
		// Toggle accessory status when right-clicked in inventory without a stack
		if (clickAction == ClickAction.SECONDARY && holdingStack.isEmpty()) {
			CompoundTag tag = slotStack.getOrCreateTagElement("terramine");
			tag.putByte("Status", (byte) terramineStatus.nextIndex(tag.getByte("Status")));
			slotStack.addTagElement("terramine", tag);
			effectEnabled = terramineStatus.values()[tag.getByte("Status")].hasEffects();
			return true;
		}
		return false;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
		ItemStack stack = user.getItemInHand(hand);
		if (TrinketItem.equipItem(user, stack)) {
			// Play right click equip sound
			SoundInfo sound = this.getEquipSoundInfo();
			user.playSound(sound.soundEvent(), sound.volume(), sound.pitch());

			return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
		}

		return super.use(level, user, hand);
	}

	@Override
	public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
		if (TrinketsHelper.areEffectsEnabled(stack, entity) && entity instanceof Player) {
			curioTick(entity, stack);
		}
	}

	protected void curioTick(LivingEntity livingEntity, ItemStack stack) {
	}

	@Override
	public final Multimap<Attribute, AttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
		Multimap<Attribute, AttributeModifier> modifiers = Trinket.super.getModifiers(stack, slot, entity, uuid);
		if (TrinketsHelper.areEffectsEnabled(stack, entity) && entity instanceof Player) {
			Multimap<Attribute, AttributeModifier> accessoryModifiers = this.applyModifiers(stack, slot, entity, uuid);
			modifiers.putAll(accessoryModifiers);
		}
		return modifiers;
	}

	protected Multimap<Attribute, AttributeModifier> applyModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
		return HashMultimap.create();
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flags) {
		super.appendHoverText(stack, world, tooltip, flags);
		getEffectsEnabledLanguageKey(stack).ifPresent(key -> {
			MutableComponent enabled = new TranslatableComponent(key).withStyle(ChatFormatting.GOLD);
			Component toggletooltip = new TranslatableComponent(TerraMine.MOD_ID + ".status.toggletooltip").withStyle(ChatFormatting.GRAY);
			tooltip.add(enabled.append(" ").append(toggletooltip));
		});
	}

	/**
	 * @return The {@link SoundInfo} to play when the accessory is right-click equipped
	 */
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC);
	}

	/**
	 * @return An extra {@link SoundEvent} to play when an entity wearing this accessory is hurt
	 */
	protected SoundEvent getExtraHurtSound() {
		return null;
	}

	/**
	 * Used to give a Trinket a permanent status effect while wearing it.
	 * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
	 *
	 * @return The {@link MobEffectInstance} to be applied while wearing this accessory
	 */
	public MobEffectInstance getPermanentEffect() {
		return null;
	}

	private void playExtraHurtSound(LivingEntity entity, float volume, float pitch) { // keeping for now, may use for moon charm and Neptune's shell
		SoundEvent hurtSound = getExtraHurtSound();

		if (hurtSound != null && TrinketsHelper.isEquipped(this, entity, true)) {
			entity.playSound(hurtSound, volume, pitch);
		}
	}

	public static void addModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (!instance.hasModifier(modifier)) {
			instance.addTransientModifier(modifier);
		}
	}

	public static void removeModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (instance.hasModifier(modifier)) {
			instance.removeModifier(modifier);
		}
	}

	private static Optional<String> getEffectsEnabledLanguageKey(ItemStack stack) {
		return getTerraMineStatus(stack).map(status -> switch (status) {
			case ALL_ENABLED -> "terramine.status.allenabled";
			case COSMETIC_ONLY -> "terramine.status.cosmeticonly";
			case EFFECTS_ONLY -> "terramine.status.effectsonly";
		});
	}

	public static Optional<terramineStatus> getTerraMineStatus(ItemStack stack) {
		if (!(stack.getItem() instanceof TrinketTerrariaItem)) {
			return Optional.empty();
		}

		CompoundTag tag = stack.getTagElement("terramine");
		if (tag == null || !tag.contains("Status", 1)) {
			return Optional.of(terramineStatus.ALL_ENABLED);
		}

		return Optional.ofNullable(terramineStatus.values()[tag.getByte("Status")]);
	}

	public enum terramineStatus {
		ALL_ENABLED(true, true),
		COSMETIC_ONLY(false, true),
		EFFECTS_ONLY(true, false);

		private final boolean hasEffects;
		private final boolean hasCosmetics;

		terramineStatus(boolean hasEffects, boolean hasCosmetics) {
			this.hasEffects = hasEffects;
			this.hasCosmetics = hasCosmetics;
		}

		public boolean hasEffects() {
			return hasEffects;
		}

		public boolean hasCosmetics() {
			return hasCosmetics;
		}

		public static int nextIndex(int index) {
			return index >= values().length - 1 ? 0 : index + 1;
		}
	}

	protected record SoundInfo(SoundEvent soundEvent, float volume, float pitch) {

		// Changes access modifier to public
		@SuppressWarnings({"RedundantRecordConstructor", "RedundantSuppression"})
		public SoundInfo {}

		public SoundInfo(SoundEvent soundEvent) {
			this(soundEvent, 1f, 1f);
		}
	}
}
