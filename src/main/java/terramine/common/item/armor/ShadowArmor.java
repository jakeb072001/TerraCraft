package terramine.common.item.armor;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class ShadowArmor extends TerrariaArmor {
    private static final AttributeModifier MOVEMENT_SPEED_BONUS = new AttributeModifier(UUID.fromString("d42cc1da-db67-462a-9024-ef1ad231409b"),
            "shadow_armor_set_bonus", 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    public ShadowArmor(String armorType, Holder<ArmorMaterial> holder, Type type, Properties properties) {
        super(armorType, holder, type, properties);

        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        //builder.put(Attributes.ARMOR, new AttributeModifier(uUID, "Armor modifier", this.defense, AttributeModifier.Operation.ADD_VALUE));
        //builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uUID, "Armor toughness", this.toughness, AttributeModifier.Operation.ADD_VALUE));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uUID, "Shadow Attack Speed", 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attributeModifiers = builder.build();
    }

    @Override
    public void setBonusEffect(LivingEntity livingEntity, Level level) {
        AttributeInstance movementSpeed = livingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeed != null) {
            addModifier(movementSpeed, MOVEMENT_SPEED_BONUS);
        }
    }

    @Override
    public void removeBonusEffect(LivingEntity livingEntity, Level level) {
        AttributeInstance movementSpeed = livingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeed != null) {
            removeModifier(movementSpeed, MOVEMENT_SPEED_BONUS);
        }
    }
}
