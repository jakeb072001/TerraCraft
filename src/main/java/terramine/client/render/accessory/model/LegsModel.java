package terramine.client.render.accessory.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.LivingEntity;

public class LegsModel extends HumanoidModel<HumanoidRenderState> {

    public LegsModel(ModelPart part) {
        super(part, RenderType::entityCutoutNoCull);
    }

    public static MeshDefinition createLegs(float delta, CubeListBuilder leftLeg, CubeListBuilder rightLeg) {
        CubeDeformation deformation = new CubeDeformation(delta);
        MeshDefinition mesh = createMesh(CubeDeformation.NONE, 0);

        mesh.getRoot().addOrReplaceChild(
                "left_leg",
                leftLeg.texOffs(0, 0)
                        .addBox(-2, 0, -2, 4, 12, 4, deformation),
                PartPose.offset(1.9F, 12, 0)
        );
        mesh.getRoot().addOrReplaceChild(
                "right_leg",
                rightLeg.texOffs(16, 0)
                        .addBox(-2, 0, -2, 4, 12, 4, deformation),
                PartPose.offset(-1.9F, 12, 0)
        );

        return mesh;
    }

    public static MeshDefinition createSleevedLegs(float delta, CubeListBuilder leftLeg, CubeListBuilder rightLeg) {
        CubeDeformation deformation = new CubeDeformation(delta + 0.25F);

        // sleeves
        leftLeg.texOffs(0, 16);
        leftLeg.addBox(-2, 0, -2, 4, 12, 4, deformation);
        rightLeg.texOffs(16, 16);
        rightLeg.addBox(-2, 0, -2, 4, 12, 4, deformation);

        return createLegs(delta, leftLeg, rightLeg);
    }

    public static MeshDefinition createShoes(float delta, CubeListBuilder leftLeg, CubeListBuilder rightLeg) {
        CubeDeformation deformation = new CubeDeformation(delta, delta / 4, delta / 4);

        // shoe tips
        leftLeg.texOffs(0, 16);
        leftLeg.addBox(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, deformation);
        rightLeg.texOffs(16, 16);
        rightLeg.addBox(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, deformation);

        return createLegs(delta, leftLeg, rightLeg);
    }

    public static MeshDefinition createSlippers(CubeListBuilder leftLeg, CubeListBuilder rightLeg) {
        leftLeg.texOffs(32, 0);
        leftLeg.addBox(-2.5F, 8.51F, -7.01F, 5, 4, 5);
        rightLeg.texOffs(32, 16);
        rightLeg.addBox(-2.5F, 8.51F, -7, 5, 4, 5);

        return createSleevedLegs(0.51F, leftLeg, rightLeg);
    }

    public static MeshDefinition createAquaDashers() {
        CubeListBuilder leftLeg = CubeListBuilder.create();
        CubeListBuilder rightLeg = CubeListBuilder.create();

        float delta = 1.25F;
        CubeDeformation deformation = new CubeDeformation(0, delta, delta);

        // wings
        leftLeg.texOffs(0, 16);
        leftLeg.addBox(2 + delta, 0, -2 + 3 + delta * 3 / 2, 0, 12, 4, deformation);
        rightLeg.texOffs(16, 16);
        rightLeg.addBox(-2 - delta, 0, -2 + 3 + delta * 3 / 2, 0, 12, 4, deformation);

        return createShoes(delta, leftLeg, rightLeg);
    }

    public static MeshDefinition createFlippers() {
        CubeListBuilder leftLeg = CubeListBuilder.create();
        CubeListBuilder rightLeg = CubeListBuilder.create();

        leftLeg.texOffs(0, 16);
        leftLeg.addBox(-2, 11.5F, -16, 9, 0, 20);
        rightLeg.texOffs(0, 36);
        rightLeg.addBox(-7, 11.5F, -16, 9, 0, 20);

        return createLegs(0.5F, leftLeg, rightLeg);
    }

    public static MeshDefinition createRunningShoes() {
        return createShoes(0.5F, CubeListBuilder.create(), CubeListBuilder.create());
    }
}
