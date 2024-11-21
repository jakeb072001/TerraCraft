package terramine.common.components;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class SyncedIntegerComponent implements Component, AutoSyncedComponent {

	private final String name;
	protected int integer;
	protected boolean set = false;

	public SyncedIntegerComponent(String name) {
		this.name = name;
	}

	public int get() {
		return integer;
	}

	public void set(int integer) {
		this.integer = integer;
		this.set = true;
	}

	public void add(int integer) {
		this.integer += integer;
		this.set = true;
	}

	public void remove(int integer) {
		this.integer -= integer;
		this.set = true;
	}

	public boolean isSet() {
		return set;
	}

	@Override
	public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		this.integer = tag.contains(this.name) ? tag.getInt(this.name) : 0;
		this.set = tag.contains("isSet") && tag.getBoolean("isSet");
	}

	@Override
	public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		tag.putInt(this.name, this.integer);
		tag.putBoolean("isSet", this.set);
	}

	@Override
	public void writeSyncPacket(RegistryFriendlyByteBuf buf, ServerPlayer recipient) {
		buf.writeInt(this.integer);
		buf.writeBoolean(this.set);
	}

	@Override
	public void applySyncPacket(RegistryFriendlyByteBuf buf) {
		this.integer = buf.readInt();
		this.set = buf.readBoolean();
	}
}
