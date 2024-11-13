package terramine.common.network.types;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;

public record FloatSoundNetworkType(float float1, float float2, SoundEvent soundEvent) implements CustomPacketPayload {
    public static Type<FloatSoundNetworkType> typeCustom;
    public static final Type<FloatSoundNetworkType> TYPE = new Type<>(TerraMine.id("packet_converter"));
    public static final StreamCodec<RegistryFriendlyByteBuf, FloatSoundNetworkType> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, FloatSoundNetworkType::float1,
            ByteBufCodecs.FLOAT, FloatSoundNetworkType::float2,
            SoundEvent.DIRECT_STREAM_CODEC, FloatSoundNetworkType::soundEvent,
            FloatSoundNetworkType::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public FloatSoundNetworkType setCustomType(Type<FloatSoundNetworkType> type) {
        typeCustom = type;
        return this;
    }

    public float getFloat1() {
        return float1;
    }

    public float getFloat2() {
        return float2;
    }

    public SoundEvent getSoundEvent() {
        return soundEvent;
    }
}