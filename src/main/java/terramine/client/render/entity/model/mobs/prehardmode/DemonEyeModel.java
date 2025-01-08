package terramine.client.render.entity.model.mobs.prehardmode;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;
import terramine.client.render.entity.states.TerrariaLivingEntityRenderState;
import terramine.common.entity.mobs.prehardmode.DemonEyeEntity;

public class DemonEyeModel<T extends TerrariaLivingEntityRenderState> extends EntityModel<T> {

    protected final ModelPart root;
    protected final ModelPart eye;
    protected final ModelPart veins;


    public DemonEyeModel(ModelPart part) {
        super(part);
        root = part;
        eye = part.getChild("eye");
        veins = part.getChild("veins");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();

        mesh.getRoot().addOrReplaceChild(
                "eye",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, -4.0F, 0.0F, 4.0F, 4.0F, 4.0F)
                        .texOffs(9, 9)
                        .addBox(-1.5F, -3.5F, -0.25F, 3.0F, 3.0F, 3.0F),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );
        mesh.getRoot().addOrReplaceChild(
                "veins",
                CubeListBuilder.create()
                        .texOffs(8, 11)
                        .addBox(1.0F, -4.0F, 4.0F, 0.0F, 4.0F, 4.0F)
                        .texOffs(0, 8)
                        .addBox(-1.0F, -4.0F, 4.0F, 0.0F, 4.0F, 4.0F)
                        .texOffs(8, 0)
                        .addBox(-2.0F, -3.0F, 4.0F, 4.0F, 0.0F, 4.0F)
                        .texOffs(0, 8)
                        .addBox(-2.0F, -1.0F, 4.0F, 4.0F, 0.0F, 4.0F),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 32, 32);
    }
}
