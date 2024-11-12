package terramine.common.utility;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.List;
import java.util.Optional;

public record AttributeComponent(List<Entry> modifiers) {
    public static final AttributeComponent DEFAULT = new AttributeComponent(List.of());

    public static final Codec<AttributeComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Entry.CODEC.listOf().fieldOf("modifiers").forGetter(AttributeComponent::modifiers)
    ).apply(builder, AttributeComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AttributeComponent> PACKET_CODEC = StreamCodec.composite(
            Entry.PACKET_CODEC.apply(ByteBufCodecs.list()),
            AttributeComponent::modifiers,
            AttributeComponent::new);

    public record Entry(Holder<Attribute> attribute, AttributeModifier modifier, Optional<String> slot) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                BuiltInRegistries.ATTRIBUTE.holderByNameCodec().fieldOf("type").forGetter(Entry::attribute),
                AttributeModifier.MAP_CODEC.forGetter(Entry::modifier),
                Codec.STRING.optionalFieldOf("slot").forGetter(Entry::slot)
        ).apply(instance, Entry::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, Entry> PACKET_CODEC = StreamCodec.composite(
                ByteBufCodecs.holderRegistry(Registries.ATTRIBUTE),
                Entry::attribute,
                AttributeModifier.STREAM_CODEC,
                Entry::modifier,
                ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8),
                Entry::slot,
                Entry::new);
    }
}
