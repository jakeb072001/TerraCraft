package terramine.mixin.client.render;

import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.world.item.equipment.EquipmentModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EquipmentLayerRenderer.LayerTextureKey.class)
public interface LayerTextureKeyInvoker {
    @Invoker("<init>")
    static EquipmentLayerRenderer.LayerTextureKey create(EquipmentModel.LayerType layerType, EquipmentModel.Layer layer) {
        throw new AssertionError();
    }
}
