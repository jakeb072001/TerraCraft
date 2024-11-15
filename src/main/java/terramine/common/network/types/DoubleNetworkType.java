package terramine.common.network.types;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;

public record DoubleNetworkType(double double1, double double2, double double3) implements CustomPacketPayload {
    public static Type<DoubleNetworkType> typeCustom;
    public static final Type<DoubleNetworkType> TYPE = new Type<>(TerraMine.id("triple_double_type"));
    public static final StreamCodec<RegistryFriendlyByteBuf, DoubleNetworkType> CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, DoubleNetworkType::double1,
            ByteBufCodecs.DOUBLE, DoubleNetworkType::double2,
            ByteBufCodecs.DOUBLE, DoubleNetworkType::double3,
            DoubleNetworkType::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public DoubleNetworkType setCustomType(Type<DoubleNetworkType> type) {
        typeCustom = type;
        return this;
    }

    public double getDouble1() {
        return double1;
    }

    public double getDouble2() {
        return double2;
    }

    public double getDouble3() {
        return double3;
    }
}