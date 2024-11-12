package terramine.common.components;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import terramine.TerraMine;
import terramine.common.misc.TeamColours;

@SuppressWarnings("UnstableApiUsage")
public class TeamsComponent implements Component, AutoSyncedComponent {
    private final Player provider;
    private TeamColours teamColour;

    public TeamsComponent(Player provider) {
        this.provider = provider;
        teamColour = TeamColours.NONE;
    }

    public void setTeamColour(TeamColours teamColour) {
        this.teamColour = teamColour;
    }

    public TeamColours getTeamColour() {
        return teamColour;
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        if (tag.contains("team") && TerraMine.CONFIG.client.rememberTeam) {
            teamColour = TeamColours.getTeam(tag.getInt("team"));
        }
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        if (teamColour != null && TerraMine.CONFIG.client.rememberTeam) {
            tag.putInt("team", teamColour.getIndex());
        }
    }

    @Override
    public boolean shouldSyncWith(ServerPlayer player) {
        return player == provider;
    }

    @Override
    public void writeSyncPacket(RegistryFriendlyByteBuf buf, ServerPlayer recipient) {
        if (teamColour != null) {
            buf.writeInt(teamColour.getIndex());
        } else {
            buf.writeInt(0);
        }
    }

    @Override
    public void applySyncPacket(RegistryFriendlyByteBuf buf) {
        teamColour = TeamColours.getTeam(buf.readInt());
    }
}
