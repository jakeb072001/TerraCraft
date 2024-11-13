package terramine.common.network.types;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;

public record ParticleNetworkType(ParticleOptions particleOptions) implements CustomPacketPayload {
    public static Type<ParticleNetworkType> typeCustom;
    public static final Type<ParticleNetworkType> TYPE = new Type<>(TerraMine.id("packet_converter"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ParticleNetworkType> CODEC = StreamCodec.composite(
            ParticleTypes.STREAM_CODEC, ParticleNetworkType::particleOptions,
            ParticleNetworkType::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public ParticleNetworkType setCustomType(Type<ParticleNetworkType> type) {
        typeCustom = type;
        return this;
    }

    public ParticleOptions getParticleOption() {
        return particleOptions;
    }
}