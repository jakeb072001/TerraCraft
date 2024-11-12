package terramine.common.network.packet;

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

public record BufferConverter(FriendlyByteBuf buf, ItemStack itemStack, ParticleOptions particleOptions, SoundEvent soundEvent) implements CustomPacketPayload {
    public static CustomPacketPayload.Type<BufferConverter> typeCustom;
    public static final CustomPacketPayload.Type<BufferConverter> TYPE = new CustomPacketPayload.Type<>(TerraMine.id("packet_converter"));
    // todo: get to send FriendlyByteBuf, if need be reconstruct the buf but see if can easily send without reconstruction.
    public static final StreamCodec<RegistryFriendlyByteBuf, BufferConverter> CODEC = StreamCodec.composite(
            BufferCodec.FRIENDLYBYTEBUF, BufferConverter::buf,
            ItemStack.OPTIONAL_STREAM_CODEC, BufferConverter::itemStack,
            ParticleTypes.STREAM_CODEC, BufferConverter::particleOptions,
            SoundEvent.DIRECT_STREAM_CODEC, BufferConverter::soundEvent,
            BufferConverter::new);

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public BufferConverter setCustomType(Type<BufferConverter> type) {
        typeCustom = type;
        return this;
    }

    public FriendlyByteBuf getFriendlybyteBuf() {
        return buf;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public SoundEvent getSoundEvent() {
        return soundEvent;
    }

    public ParticleOptions getParticleOption() {
        return particleOptions;
    }
}