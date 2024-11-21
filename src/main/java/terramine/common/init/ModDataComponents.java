package terramine.common.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import terramine.TerraMine;
import terramine.common.utility.AttributeComponent;

public class ModDataComponents {
    public static final DataComponentType<AttributeComponent> ATTRIBUTE_MODIFIER_COMPONENT = register(TerraMine.id("attribute_modifier_component"), AttributeComponent.CODEC, AttributeComponent.PACKET_CODEC);

    private static <T> DataComponentType<T> register(ResourceLocation resourceLocation, Codec<T> codec) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, resourceLocation, DataComponentType.<T>builder().persistent(codec).build());
    }

    private static <T> DataComponentType<T> register(ResourceLocation resourceLocation, Codec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, resourceLocation, DataComponentType.<T>builder().persistent(codec).networkSynchronized(streamCodec).build());
    }
}
