package terramine.mixin.client.render;

import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentModel;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EquipmentLayerRenderer.TrimSpriteKey.class)
public interface TrimSpriteKeyInvoker {
    @Invoker("<init>")
    static EquipmentLayerRenderer.TrimSpriteKey create(ArmorTrim armorTrim, EquipmentModel.LayerType layerType, ResourceLocation resourceLocation) {
        throw new AssertionError();
    }
}
