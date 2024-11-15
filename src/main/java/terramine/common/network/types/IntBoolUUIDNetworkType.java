package terramine.common.network.types;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;

import java.util.UUID;

public record IntBoolUUIDNetworkType(int integer1, int integer2, boolean bool, UUID uuid) implements CustomPacketPayload {
    public static Type<IntBoolUUIDNetworkType> typeCustom;
    public static final Type<IntBoolUUIDNetworkType> TYPE = new Type<>(TerraMine.id("int_bool_uuid_type"));
    public static final StreamCodec<RegistryFriendlyByteBuf, IntBoolUUIDNetworkType> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, IntBoolUUIDNetworkType::integer1,
            ByteBufCodecs.INT, IntBoolUUIDNetworkType::integer2,
            ByteBufCodecs.BOOL, IntBoolUUIDNetworkType::bool,
            UUIDUtil.STREAM_CODEC, IntBoolUUIDNetworkType::uuid,
            IntBoolUUIDNetworkType::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public IntBoolUUIDNetworkType setCustomType(Type<IntBoolUUIDNetworkType> type) {
        typeCustom = type;
        return this;
    }

    public int getInteger1() {
        return integer1;
    }

    public int getInteger2() {
        return integer2;
    }

    public boolean getBoolean() {
        return bool;
    }

    public UUID getUUID() {
        return uuid;
    }
}