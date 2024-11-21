package terramine.common.network.types;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;

import java.util.List;
import java.util.UUID;

public record ItemNetworkType(List<ItemStack> itemStack, int integer, UUID uuid) implements CustomPacketPayload {
    public static CustomPacketPayload.Type<ItemNetworkType> typeCustom;
    public static final CustomPacketPayload.Type<ItemNetworkType> TYPE = new CustomPacketPayload.Type<>(TerraMine.id("item_int_uuid_type"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemNetworkType> CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list()), ItemNetworkType::itemStack,
            ByteBufCodecs.INT, ItemNetworkType::integer,
            UUIDUtil.STREAM_CODEC, ItemNetworkType::uuid,
            ItemNetworkType::new);

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        if (typeCustom != null) {
            return typeCustom;
        }
        return TYPE;
    }

    public ItemNetworkType setCustomType(Type<ItemNetworkType> type) {
        typeCustom = type;
        return this;
    }

    public List<ItemStack> getItemStacks() {
        return itemStack;
    }

    public int getInteger() {
        return integer;
    }

    public UUID getUUID() {
        return uuid;
    }
}