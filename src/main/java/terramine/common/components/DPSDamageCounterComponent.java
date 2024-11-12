package terramine.common.components;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.entity.C2SSelfMessagingComponent;

@SuppressWarnings("UnstableApiUsage")
public class DPSDamageCounterComponent implements C2SSelfMessagingComponent, AutoSyncedComponent {

	private float lastDamageTaken = 0;
	private final Player provider;

	public DPSDamageCounterComponent(Player provider) {
		this.provider = provider;
	}

	public void setDamageTaken(float damage) {
		lastDamageTaken += damage;
	}

	public void resetDamageTaken() {
		lastDamageTaken = 0;
	}

	public float getDamageTaken() {
		return lastDamageTaken;
	}

	@Override
	public void handleC2SMessage(RegistryFriendlyByteBuf buf) {
	}

	@Override
	public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {

	}

	@Override
	public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {

	}
}
