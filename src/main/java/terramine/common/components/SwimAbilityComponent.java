package terramine.common.components;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.entity.C2SSelfMessagingComponent;

@SuppressWarnings("UnstableApiUsage")
public class SwimAbilityComponent implements C2SSelfMessagingComponent, AutoSyncedComponent {

	private boolean shouldSwim;
	private boolean shouldSink;
	private boolean hasTouchedWater;
	private int swimTime;
	private final Player provider;

	public SwimAbilityComponent(Player provider) {
		this.provider = provider;
	}

	public boolean isSwimming() {
		return shouldSwim;
	}

	public boolean isSinking() {
		return shouldSink;
	}

	public boolean isWet() {
		return hasTouchedWater;
	}

	public int getSwimTime() {
		return swimTime;
	}

	public void setSwimming(boolean shouldSwim) {
		this.shouldSwim = shouldSwim;
	}

	public void setSinking(boolean shouldSink) {
		this.shouldSink = shouldSink;
	}

	public void setWet(boolean hasTouchedWater) {
		this.hasTouchedWater = hasTouchedWater;
	}

	public void setSwimTime(int swimTime) {
		this.swimTime = swimTime;
	}

	@Override
	public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		this.setSwimming(tag.getBoolean("ShouldSwim"));
		this.setSinking(tag.getBoolean("ShouldSink"));
		this.setWet(tag.getBoolean("IsWet"));
		this.setSwimTime(tag.getInt("SwimTime"));
	}

	@Override
	public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		tag.putBoolean("ShouldSwim", this.isSwimming());
		tag.putBoolean("ShouldSink", this.isSinking());
		tag.putBoolean("IsWet", this.isWet());
		tag.putInt("SwimTime", this.getSwimTime());
	}

	@Override
	public boolean shouldSyncWith(ServerPlayer player) {
		return player == provider;
	}

	@Override
	public void writeSyncPacket(RegistryFriendlyByteBuf buf, ServerPlayer recipient) {
		buf.writeBoolean(this.isSwimming());
		buf.writeBoolean(this.isSinking());
		buf.writeBoolean(this.isWet());
		buf.writeInt(this.getSwimTime());
	}

	@Override
	public void applySyncPacket(RegistryFriendlyByteBuf buf) {
		this.setSwimming(buf.readBoolean());
		this.setSinking(buf.readBoolean());
		this.setWet(buf.readBoolean());
		this.setSwimTime(buf.readInt());
	}

	@Override
	public void handleC2SMessage(RegistryFriendlyByteBuf buf) {
	}
}
