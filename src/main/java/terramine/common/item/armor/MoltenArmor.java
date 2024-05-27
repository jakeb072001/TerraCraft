package terramine.common.item.armor;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class MoltenArmor extends TerrariaArmor {
    private static final AttributeModifier ATTACK_DAMAGE_BONUS = new AttributeModifier(UUID.fromString("42be22eb-afa1-4f64-819a-28846c1759f3"),
            "molten_armor_set_bonus", 0.10, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    public MoltenArmor(String armorType, Holder<ArmorMaterial> holder, Type type, Properties properties) {
        super(armorType, holder, type, properties);

        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        //builder.put(Attributes.ARMOR, new AttributeModifier(uUID, "Armor modifier", this.defense, AttributeModifier.Operation.ADD_VALUE));
        //builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uUID, "Armor toughness", this.toughness, AttributeModifier.Operation.ADD_VALUE));
        if (type == ArmorItem.Type.HELMET) {
            builder.put(Attributes.LUCK, new AttributeModifier(uUID, "Molten Attack Crit Chance", 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
        if (type == ArmorItem.Type.CHESTPLATE) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uUID, "Molten Attack Damage", 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
        if (type == ArmorItem.Type.LEGGINGS) {
            builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(uUID, "Molten Attack Knockback", 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
        if (type == ArmorItem.Type.BOOTS) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uUID, "Molten Attack Speed", 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        }
        attributeModifiers = builder.build();
    }

    @Override
    public void setBonusEffect(LivingEntity livingEntity, Level level) {
        AttributeInstance attackDamage = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamage != null) {
            addModifier(attackDamage, ATTACK_DAMAGE_BONUS);
        }
    }

    @Override
    public void removeBonusEffect(LivingEntity livingEntity, Level level) {
        AttributeInstance attackDamage = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamage != null) {
            removeModifier(attackDamage, ATTACK_DAMAGE_BONUS);
        }
    }
}
