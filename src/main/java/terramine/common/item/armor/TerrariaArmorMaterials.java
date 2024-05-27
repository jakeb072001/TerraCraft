package terramine.common.item.armor;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import terramine.TerraMine;
import terramine.common.init.ModItems;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class TerrariaArmorMaterials {

    public static final Holder<ArmorMaterial> VANITY;
    public static final Holder<ArmorMaterial> SHADOW;
    public static final Holder<ArmorMaterial> ANCIENT_SHADOW;
    public static final Holder<ArmorMaterial> CRIMSON;
    public static final Holder<ArmorMaterial> METEOR;
    public static final Holder<ArmorMaterial> MOLTEN;

    public TerrariaArmorMaterials() {
    }

    private static Holder<ArmorMaterial> register(String string, EnumMap<ArmorItem.Type, Integer> enumMap, int i, Holder<SoundEvent> holder, float f, float g, Supplier<Ingredient> supplier) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(new ResourceLocation(string)));
        return register(string, enumMap, i, holder, f, g, supplier, list);
    }

    private static Holder<ArmorMaterial> register(String string, EnumMap<ArmorItem.Type, Integer> enumMap, int i, Holder<SoundEvent> holder, float f, float g, Supplier<Ingredient> supplier, List<ArmorMaterial.Layer> list) {
        EnumMap<ArmorItem.Type, Integer> enumMap2 = new EnumMap<>(ArmorItem.Type.class);
        ArmorItem.Type[] var9 = ArmorItem.Type.values();
        int var10 = var9.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            ArmorItem.Type type = var9[var11];
            enumMap2.put(type, enumMap.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(string), new ArmorMaterial(enumMap2, i, holder, supplier, list, f, g));
    }

    static {
        VANITY = register("vanity", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 0);
            enumMap.put(ArmorItem.Type.LEGGINGS, 0);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 0);
            enumMap.put(ArmorItem.Type.HELMET, 0);
            enumMap.put(ArmorItem.Type.BODY, 0);
        }), 0, SoundEvents.ARMOR_EQUIP_LEATHER, 0, 0, () -> Ingredient.of(ItemStack.EMPTY));
        SHADOW = register("shadow", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 2);
            enumMap.put(ArmorItem.Type.LEGGINGS, 5);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 7);
            enumMap.put(ArmorItem.Type.HELMET, 3);
            enumMap.put(ArmorItem.Type.BODY, 5);
        }), 10, SoundEvents.ARMOR_EQUIP_IRON, 1F, 0, () -> Ingredient.of(ModItems.DEMONITE_INGOT));
        ANCIENT_SHADOW = register("ancient_shadow", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 2);
            enumMap.put(ArmorItem.Type.LEGGINGS, 5);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 7);
            enumMap.put(ArmorItem.Type.HELMET, 3);
            enumMap.put(ArmorItem.Type.BODY, 5);
        }), 10, SoundEvents.ARMOR_EQUIP_IRON, 1F, 0F, () -> Ingredient.of(ModItems.DEMONITE_INGOT));
        CRIMSON = register("crimson", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 2);
            enumMap.put(ArmorItem.Type.LEGGINGS, 5);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 7);
            enumMap.put(ArmorItem.Type.HELMET, 3);
            enumMap.put(ArmorItem.Type.BODY, 5);
        }), 10, SoundEvents.ARMOR_EQUIP_GOLD, 1F, 0F, () -> Ingredient.of(ModItems.CRIMTANE_INGOT));
        METEOR = register("meteor", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 3);
            enumMap.put(ArmorItem.Type.LEGGINGS, 6);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 8);
            enumMap.put(ArmorItem.Type.HELMET, 3);
            enumMap.put(ArmorItem.Type.BODY, 6);
        }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.1F, () -> Ingredient.of(ModItems.METEORITE_INGOT));
        MOLTEN = register("molten", Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
            enumMap.put(ArmorItem.Type.BOOTS, 4);
            enumMap.put(ArmorItem.Type.LEGGINGS, 8);
            enumMap.put(ArmorItem.Type.CHESTPLATE, 8);
            enumMap.put(ArmorItem.Type.HELMET, 4);
            enumMap.put(ArmorItem.Type.BODY, 7);
        }), 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(ModItems.HELLSTONE_INGOT));
    }
}
