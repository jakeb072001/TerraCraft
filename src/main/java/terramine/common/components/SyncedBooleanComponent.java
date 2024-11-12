package terramine.common.components;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.LevelData;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class SyncedBooleanComponent implements Component, AutoSyncedComponent {

	private final String name;
	protected boolean bool;
	private static LevelData levelData;

	public SyncedBooleanComponent(String name) {
		this.name = name;
	}

	public boolean get() {
		return bool;
	}

	public void set(boolean bool) {
		this.bool = bool;
	}

	public static void setLevelData(LevelData mcLevelData) {
		levelData = mcLevelData;
	}

	@NotNull
	public static LevelData getLevelData() {
		if (levelData != null) {
			return levelData;
		}
		throw new UnsupportedOperationException("Accessed server level too early!");
	}

	@Override
	public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		this.bool = tag.contains(this.name) && tag.getBoolean(this.name);
	}

	@Override
	public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		tag.putBoolean(this.name, this.bool);
	}

	@Override
	public void writeSyncPacket(RegistryFriendlyByteBuf buf, ServerPlayer recipient) {
		buf.writeBoolean(this.bool);
	}

	@Override
	public void applySyncPacket(RegistryFriendlyByteBuf buf) {
		this.bool = buf.readBoolean();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof SyncedBooleanComponent) {
			SyncedBooleanComponent other = (SyncedBooleanComponent) obj;
			return this.get() == other.get() && this.name.equals(other.name);
		}
		return false;
	}
}
