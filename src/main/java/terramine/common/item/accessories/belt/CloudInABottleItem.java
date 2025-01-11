package terramine.common.item.accessories.belt;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import terramine.common.item.accessories.AccessoryTerrariaItem;
import terramine.common.network.ServerPacketHandler;
import terramine.common.network.types.LongNetworkType;
import terramine.extensions.LivingEntityExtensions;

public class CloudInABottleItem extends AccessoryTerrariaItem {
	public CloudInABottleItem(ResourceKey<Item> key) {
        super(key);
        ServerPlayNetworking.registerGlobalReceiver(ServerPacketHandler.C2S_DOUBLE_JUMPED_ID, CloudInABottleItem::handleDoubleJumpPacket);
	}

	private static void handleDoubleJumpPacket(LongNetworkType bufferConverter, ServerPlayNetworking.Context context) {
		context.player().server.execute(() -> {
			ServerPlayer player = context.player();
			((LivingEntityExtensions) player).terramine$doubleJump();

			// Spawn particles
			for (int i = 0; i < 20; ++i) {
				double motionX = player.getRandom().nextGaussian() * 0.02;
				double motionY = player.getRandom().nextGaussian() * 0.02 + 0.20;
				double motionZ = player.getRandom().nextGaussian() * 0.02;
				player.serverLevel().sendParticles(ParticleTypes.POOF, player.getX(), player.getY(), player.getZ(), 1, motionX, motionY, motionZ, 0.15);
			}
		});
	}

	@Override
	public SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.BOTTLE_FILL_DRAGONBREATH);
	}
}
