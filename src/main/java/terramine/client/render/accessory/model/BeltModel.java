package terramine.client.render.accessory.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import terramine.client.render.accessory.AccessoryRenderers;
import terramine.common.init.ModModelLayers;

import java.util.function.Function;

public class BeltModel extends HumanoidModel<LivingEntity> {

    protected final ModelPart charm = body.getChild("charm");

    private final float xOffset;
    private final float zOffset;
    private final float rotation;

    public BeltModel(ModelPart part, Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
        super(part, renderType);
        this.xOffset = xOffset;
        this.zOffset = zOffset;
        this.rotation = rotation;
    }

    public BeltModel(ModelPart part, float xOffset, float zOffset, float rotation) {
        this(part, RenderType::entityCutoutNoCull, xOffset, zOffset, rotation);
    }

    public void setCharmPosition(int slot) {
        float xOffset = slot % 2 == 0 ? this.xOffset : -this.xOffset;
        float zOffset = slot % 4 < 2 ? this.zOffset : -this.zOffset;
        charm.setPos(xOffset, 9, zOffset);

        float rotation = slot % 4 < 2 ? 0 : (float) -Math.PI;
        rotation += slot % 2 == 0 ^ slot % 4 >= 2 ? this.rotation : -this.rotation;
        charm.yRot = rotation;
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(body);
    }

    public static BeltModel createCloudInABottleModel() {
        return new BeltModel(AccessoryRenderers.bakeLayer(ModModelLayers.CLOUD_IN_A_BOTTLE), RenderType::entityTranslucent, 3, -3, -0.5F) {
            private final ModelPart cloud = charm.getChild("cloud");

            @Override
            public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                cloud.yRot = (ageInTicks) / 50;
                cloud.y = Mth.cos((ageInTicks) / 30) / 2;
            }
        };
    }

    public static BeltModel createObsidianSkullModel() {
        return new BeltModel(AccessoryRenderers.bakeLayer(ModModelLayers.OBSIDIAN_SKULL), 4.5F, -4F, -0.5F);
    }

    public static BeltModel createUniversalAttractorModel() {
        return new BeltModel(AccessoryRenderers.bakeLayer(ModModelLayers.UNIVERSAL_ATTRACTOR), 2.5F, -3, 0);
    }

    private static MeshDefinition createBelt(CubeListBuilder charm) {
        CubeDeformation deformation = new CubeDeformation(0.5F);
        MeshDefinition mesh = createMesh(CubeDeformation.NONE, 0);

        mesh.getRoot().addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4, 0, -2, 8, 12, 4, deformation),
                PartPose.ZERO
        );

        mesh.getRoot().getChild("body").addOrReplaceChild(
                "charm",
                charm,
                PartPose.ZERO
        );

        return mesh;
    }

    public static MeshDefinition createCloudInABottle() {
        CubeListBuilder charm = CubeListBuilder.create();

        // jar
        charm.texOffs(0, 16);
        charm.addBox(-2, 0, -2, 4, 5, 4);

        // lid
        charm.texOffs(0, 25);
        charm.addBox(-1, -1, -1, 2, 1, 2);

        MeshDefinition mesh = createBelt(charm);

        mesh.getRoot().getChild("body").getChild("charm").addOrReplaceChild(
                "cloud",
                CubeListBuilder.create()
                        .texOffs(8, 25) // cloud
                        .addBox(-1, 1.5F, -1, 2, 2, 2),
                PartPose.ZERO
        );

        return mesh;
    }

    public static MeshDefinition createObsidianSkull() {
        CubeListBuilder charm = CubeListBuilder.create();

        // cranium
        charm.texOffs(0, 16);
        charm.addBox(-2.5F, 0, 0, 5, 3, 4);

        // teeth
        charm.texOffs(18, 16);
        charm.addBox(-1.5F, 3, 0, 1, 1, 2);
        charm.texOffs(18, 19);
        charm.addBox(0.5F, 3, 0, 1, 1, 2);

        return createBelt(charm);
    }

    public static MeshDefinition createUniversalAttractor() {
        CubeListBuilder charm = CubeListBuilder.create();

        charm.texOffs(0, 16);
        charm.addBox(-2.5F, 0, 0, 5, 2, 1);
        charm.texOffs(0, 19);
        charm.addBox(-2.5F, 2, 0, 2, 4, 1);
        charm.texOffs(6, 19);
        charm.addBox(0.5F, 2, 0, 2, 4, 1);

        return createBelt(charm);
    }
}
