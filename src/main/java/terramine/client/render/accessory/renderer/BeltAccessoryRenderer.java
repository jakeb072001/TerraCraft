package terramine.client.render.accessory.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;
import terramine.TerraMine;
import terramine.client.render.AccessoryRenderer;
import terramine.client.render.accessory.model.BeltModel;
import terramine.common.item.dye.BasicDye;
import terramine.extensions.PlayerStorages;

public class BeltAccessoryRenderer implements AccessoryRenderer {

    private final ResourceLocation texture;
    private final BeltModel model;

    public BeltAccessoryRenderer(String texturePath, BeltModel model) {
        this(TerraMine.id(String.format("textures/entity/accessory/%s.png", texturePath)), model);
    }

    public BeltAccessoryRenderer(ResourceLocation texture, BeltModel model) {
        this.texture = texture;
        this.model = model;
    }

    protected ResourceLocation getTexture() {
        return texture;
    }

    protected BeltModel getModel() {
        return model;
    }

    @Override
    public final void render(ItemStack stack, int dyeSlot, int realSlot, EntityModel<? extends HumanoidRenderState> contextModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, Player player, float f, float g) {
        if (!((PlayerStorages) player).getSlotVisibility(realSlot)) {
            return;
        }
        BeltModel model = getModel();

        model.setupAnim(new PlayerRenderState());
        //model.prepareMobModel(player, limbSwing, limbSwingAmount, partialTicks);
        model.setCharmPosition(1); // change later so that 1 is the slot number for the item
        AccessoryRenderer.followBodyRotations(player, model);
        render(poseStack, multiBufferSource, player, dyeSlot, light, stack.hasFoil());
    }

    protected void render(PoseStack matrixStack, MultiBufferSource buffer, Player player, int slot, int light, boolean hasFoil) {
        RenderType renderType = model.renderType(getTexture());
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        if (((PlayerStorages)player).getTerrariaInventory().getItem(slot + 14).getItem() instanceof BasicDye dye) {
            model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, dye.getColourInt());
            return;
        }
        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, -1);
    }
}
