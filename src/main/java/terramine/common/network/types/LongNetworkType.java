package terramine.common.network.types;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;

import java.util.UUID;

public record LongNetworkType(Long savedLong) implements CustomPacketPayload {
    public static Type<LongNetworkType> typeCustom;
    public static final Type<LongNetworkType> TYPE = new Type<>(TerraMine.id("packet_converter"));
    public static final StreamCodec<RegistryFriendlyByteBuf, LongNetworkType> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG, LongNetworkType::savedLong,
            LongNetworkType::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public LongNetworkType setCustomType(Type<LongNetworkType> type) {
        typeCustom = type;
        return this;
    }

    public Long getLong() {
        return savedLong;
    }
}