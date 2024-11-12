package terramine.extensions;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import terramine.common.utility.AttributeComponent;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static terramine.common.init.ModDataComponents.ATTRIBUTE_MODIFIER_COMPONENT;

public interface Accessories {
    default void tick(ItemStack stack, Player player) {
    }

    default void onEquip(ItemStack stack, Player player) {
    }

    default void onUnequip(ItemStack stack, Player player) {
    }

    default Multimap<Holder<Attribute>, AttributeModifier> getModifiers(ItemStack stack, Player player, UUID uuid) {
        Multimap<Holder<Attribute>, AttributeModifier> map = Multimaps.newMultimap(Maps.newLinkedHashMap(), ArrayList::new);

        if (stack.has(ATTRIBUTE_MODIFIER_COMPONENT)) {
            for (AttributeComponent.Entry entry : stack.getOrDefault(ATTRIBUTE_MODIFIER_COMPONENT, AttributeComponent.DEFAULT).modifiers()) {
                map.put(entry.attribute(), entry.modifier());
            }
        }
        return map;
    }
}
