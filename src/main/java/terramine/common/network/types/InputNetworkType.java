package terramine.common.network.types;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;

import java.util.List;

public record InputNetworkType(List<Boolean> booleanList) implements CustomPacketPayload {
    public static Type<InputNetworkType> typeCustom;
    public static final Type<InputNetworkType> TYPE = new Type<>(TerraMine.id("packet_converter"));
    public static final StreamCodec<RegistryFriendlyByteBuf, InputNetworkType> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL.apply(ByteBufCodecs.list()), InputNetworkType::booleanList,
            InputNetworkType::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public InputNetworkType setCustomType(Type<InputNetworkType> type) {
        typeCustom = type;
        return this;
    }

    public List<Boolean> getBooleans() {
        return booleanList;
    }
}