package terramine.common.item.armor;

import com.google.common.base.Suppliers;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import terramine.TerraMine;
import terramine.common.utility.equipmentchecks.ArmorSetCheck;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

// todo: custom models: https://github.com/Luke100000/ImmersiveArmors/blob/1.19.2/common/src/main/java/immersive_armors/mixin/MixinArmorFeatureRenderer.java ?
// todo: trail effect, like in terraria when the full set is equipped, both these todos go for all armor
public class TerrariaArmor extends ArmorItem {
    private final String armorType;
    protected final int defense;
    protected final float toughness;
    private final Supplier<ItemAttributeModifiers> defaultModifiers;
    protected Multimap<Holder<Attribute>, AttributeModifier> attributeModifiers;
    public static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(Type.class), (enumMap) -> {
        enumMap.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        enumMap.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        enumMap.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        enumMap.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
        enumMap.put(ArmorItem.Type.BODY, UUID.fromString("C1C72771-8B8E-BA4A-ACE0-81A93C8928B2"));
    });

    public TerrariaArmor(String armorType, Holder<ArmorMaterial> holder, Type type, Properties properties) {
        super(holder, type, properties);
        this.armorType = armorType;
        this.defense = holder.value().getDefense(type);
        this.toughness = holder.value().toughness();

        this.defaultModifiers = Suppliers.memoize(() -> {
            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
            EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
            UUID uUID = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
            builder.add(Attributes.ARMOR, new AttributeModifier(uUID, "Armor modifier", defense, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uUID, "Armor toughness", toughness, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            float g = holder.value().knockbackResistance();
            if (g > 0.0F) {
                builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uUID, "Armor knockback resistance", g, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            }

            attributeModifiers.asMap().forEach((attribute, attributeModifiers) -> {
                for (AttributeModifier attributeModifier : attributeModifiers) {
                    builder.add(attribute, attributeModifier, equipmentSlotGroup);
                }
            });

            return builder.build();
        });
    }

    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.defaultModifiers.get();
    }

    public static void addModifier(AttributeInstance instance, AttributeModifier modifier) {
        if (!instance.hasModifier(modifier)) {
            instance.addTransientModifier(modifier);
        }
    }

    public static void removeModifier(AttributeInstance instance, AttributeModifier modifier) {
        if (instance.hasModifier(modifier)) {
            instance.removeModifier(modifier);
        }
    }

    public String getArmorType() {
        return armorType;
    }


    @Environment(EnvType.CLIENT)
    public HumanoidModel<LivingEntity> getCustomArmorModel() {
        return null;
    }

    @Environment(EnvType.CLIENT)
    public static ModelPart bakeLayer(ModelLayerLocation layerLocation) {
        return Minecraft.getInstance().getEntityModels().bakeLayer(layerLocation);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, level, entity, i, bl);

        boolean isEquipped;
        if (entity instanceof LivingEntity livingEntity) {
            ItemStack equippedStack = livingEntity.getItemBySlot(getEquipmentSlot());
            if (equippedStack == itemStack) {
                isEquipped = ArmorSetCheck.isSetEquipped(livingEntity, this.getArmorType());

                if (isEquipped) {
                    if (itemStack.getItem() instanceof TerrariaArmor armor && armor.getEquipmentSlot() == EquipmentSlot.HEAD) { // do this so the set bonus only happens once and not per armor (4 times the buff)
                        setBonusEffect(livingEntity, level);
                    }
                } else {
                    removeBonusEffect(livingEntity, level);
                }
            }
        }
    }

    public void setBonusEffect(LivingEntity livingEntity, Level level) {
    }

    public void removeBonusEffect(LivingEntity livingEntity, Level level) {
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
        if (TerraMine.CONFIG.client.showTooltips) {
            appendTooltipDescription(tooltip, this.getDescriptionId() + ".tooltip");

            // Checks if the player is wearing a full set of one type of armor, then display the set bonus
            boolean isEquipped = false;
            if (Minecraft.getInstance().player != null) {
                isEquipped = ArmorSetCheck.isSetEquipped(Minecraft.getInstance().player, this.getArmorType());
            }
            if (isEquipped) {
                appendTooltipDescription(tooltip, "item." + TerraMine.MOD_ID + "." + armorType + ".setbonus");
            }
        }
    }

    public String[] getREITooltip() {
        return Language.getInstance().getOrDefault(this.getDescriptionId() + ".tooltip").replace("%%", "%").split("\n");
    }

    public String[] getREISetBonusTooltip() {
        return Language.getInstance().getOrDefault("item." + TerraMine.MOD_ID + "." + armorType + ".setbonus").replace("%%", "%").split("\n");
    }

    protected void appendTooltipDescription(List<Component> tooltip, String translKey) {
        String[] lines = Language.getInstance().getOrDefault(translKey).split("\n");

        for (String line : lines) {
            tooltip.add(Component.literal(line).withStyle(ChatFormatting.GRAY));
        }
    }
}
