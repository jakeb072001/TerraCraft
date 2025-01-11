package terramine.common.item.equipment;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModSoundEvents;
import terramine.common.item.accessories.AccessoryTerrariaItem;

import java.util.Optional;

public class CellPhoneItem extends AccessoryTerrariaItem {

	public CellPhoneItem(ResourceKey<Item> key) {
		super(key);
	}

	@Override
	public ItemStack finishUsingItem(@NotNull ItemStack stack, Level level, @NotNull LivingEntity entity) {
		Player player = (Player)entity;
		if (!level.isClientSide) {
			ServerPlayer serverPlayer = (ServerPlayer)player;
			ServerLevel serverLevel = serverPlayer.server.getLevel(serverPlayer.getRespawnDimension());
			if (serverLevel != null) {
				BlockPos spawnpoint = serverPlayer.getRespawnPosition();
				if (spawnpoint != null) {
					Vec3 spawnVec = serverPlayer.findRespawnPositionAndUseSpawnBlock(false, (entity2) ->{}).position();

					//Player Spawn
					serverLevel.playSound(null, serverPlayer.blockPosition(), ModSoundEvents.MAGIC_MIRROR_USE, SoundSource.PLAYERS, 0.4f, 1f);
					serverPlayer.teleportTo(serverLevel, spawnVec.x(), spawnVec.y(), spawnVec.z(), Relative.ALL, serverPlayer.getRespawnAngle(), 0.5F, true);
					serverLevel.playSound(null, spawnpoint, ModSoundEvents.MAGIC_MIRROR_USE, SoundSource.PLAYERS, 0.4f, 1f);
				} else {
					worldSpawn(serverPlayer, serverLevel);
				}
			} else {
				((ServerPlayer) player).sendSystemMessage(Component.translatable("text.magic_mirror.fail").withStyle(ChatFormatting.RED));
				level.playSound(null, player.blockPosition(), net.minecraft.sounds.SoundEvents.SHULKER_BULLET_HURT, SoundSource.BLOCKS, 1f, 1f);
			}
		}
		player.getCooldowns().addCooldown(this.getDefaultInstance(), 20);
		player.awardStat(Stats.ITEM_USED.get(this));
		return super.finishUsingItem(stack, level, entity);
	}

	@Override
	public ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
		return ItemUseAnimation.BOW;
	}

	@Override
	public InteractionResult use(@NotNull Level world, Player user, @NotNull InteractionHand hand) {
		user.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}

	@Override
	public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
		return 28;
	}

	public void worldSpawn(ServerPlayer serverPlayer, ServerLevel serverLevel) {
		BlockPos spawnpoint = serverLevel.getSharedSpawnPos();
		serverLevel.playSound(null, serverPlayer.blockPosition(), ModSoundEvents.MAGIC_MIRROR_USE, SoundSource.PLAYERS, 0.4f, 1f);
		serverPlayer.teleportTo(serverLevel, spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ(), Relative.ALL, serverPlayer.getRespawnAngle(), 0.5F, true);
		while (!serverLevel.isEmptyBlock(serverPlayer.blockPosition())) {
			serverPlayer.teleportTo(serverPlayer.getX(), serverPlayer.getY() + 1.0D, serverPlayer.getZ());
		}
		serverLevel.playSound(null, spawnpoint, ModSoundEvents.MAGIC_MIRROR_USE, SoundSource.PLAYERS, 0.4f, 1f);
	}
}
