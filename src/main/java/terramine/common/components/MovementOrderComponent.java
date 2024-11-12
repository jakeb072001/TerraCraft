package terramine.common.components;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.entity.C2SSelfMessagingComponent;

@SuppressWarnings("UnstableApiUsage")
public class MovementOrderComponent implements C2SSelfMessagingComponent, AutoSyncedComponent {
    private final Player provider;
    private boolean cloud;
    private boolean wings;
    private boolean wallJump;

    public MovementOrderComponent(Player provider) {
        this.provider = provider;
    }

    public void setCloudFinished(boolean cloud) {
        this.cloud = cloud;
    }

    public void setWingsFinished(boolean wings) {
        this.wings = wings;
    }

    public boolean getCloudFinished() {
        return cloud;
    }

    public boolean getWingsFinished() {
        return wings;
    }

    public void setWallJumped(boolean wallJump) {
        this.wallJump = wallJump;
    }

    public boolean getWallJumped() {
        return wallJump;
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
    }

    @Override
    public void handleC2SMessage(RegistryFriendlyByteBuf buf) {
    }
}
