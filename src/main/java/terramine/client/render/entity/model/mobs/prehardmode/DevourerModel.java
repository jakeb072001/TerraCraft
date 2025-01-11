package terramine.client.render.entity.model.mobs.prehardmode;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import terramine.client.render.entity.states.TerrariaLivingEntityRenderState;

public class DevourerModel<T extends TerrariaLivingEntityRenderState> extends EntityModel<T> {
    protected final ModelPart root;
    protected ModelPart devourer_head;
    protected ModelPart head_front;
    protected ModelPart head_body;
    protected ModelPart head_eyes;
    protected ModelPart cube_r1;
    protected ModelPart head_left_claw;
    protected ModelPart cube_r2;
    protected ModelPart head_right_claw;
    protected ModelPart cube_r3;
    protected ModelPart head_inner_teeth;
    protected ModelPart head_tendrils;
    protected ModelPart cube_r4;
    protected ModelPart cube_r5;
    protected ModelPart head_back;
    protected ModelPart devourer_body;
    protected ModelPart body_front;
    protected ModelPart body_back;
    protected ModelPart devourer_tail;
    protected ModelPart tail_front;
    protected ModelPart tail_back;


    public DevourerModel(ModelPart part) {
        super(part);
        root = part;

        if (part.hasChild("devourer_head")) {
            devourer_head = part.getChild("devourer_head");
            head_front = devourer_head.getChild("head_front");
            head_body = head_front.getChild("head_body");
            head_eyes = head_front.getChild("head_eyes");
            cube_r1 = head_eyes.getChild("cube_r1");
            head_left_claw = head_front.getChild("head_left_claw");
            cube_r2 = head_left_claw.getChild("cube_r2");
            head_right_claw = head_front.getChild("head_right_claw");
            cube_r3 = head_right_claw.getChild("cube_r3");
            head_inner_teeth = head_front.getChild("head_inner_teeth");
            head_tendrils = head_front.getChild("head_tendrils");
            cube_r4 = head_tendrils.getChild("cube_r4");
            cube_r5 = head_tendrils.getChild("cube_r5");
            head_back = devourer_head.getChild("head_back");
        }

        if (part.hasChild("devourer_body")) {
            devourer_body = part.getChild("devourer_body");
            body_front = devourer_body.getChild("body_front");
            body_back = devourer_body.getChild("body_back");
        }

        if (part.hasChild("devourer_tail")) {
            devourer_tail = part.getChild("devourer_tail");
            tail_front = devourer_tail.getChild("tail_front");
            tail_back = devourer_tail.getChild("tail_back");
        }
    }

    public static LayerDefinition createHeadLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition devourer_head = partDefinition.addOrReplaceChild("devourer_head", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0));

        PartDefinition head_front = devourer_head.addOrReplaceChild("head_front", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 18.0F));

        head_front.addOrReplaceChild("head_body", CubeListBuilder.create().texOffs(5, 32).addBox(-6.0F, -2.0F, -21.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(29, 9).addBox(-4.0F, -6.0F, -16.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -7.0F, -24.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(4.0F, -4.0F, -12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 34).addBox(-5.0F, -3.0F, -12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 30).addBox(5.0F, -5.0F, -20.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head_eyes = head_front.addOrReplaceChild("head_eyes", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, -8.5F, -23.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head_eyes.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 44).addBox(-2.0F, -3.5F, -5.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -18.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition head_left_claw = head_front.addOrReplaceChild("head_left_claw", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -3.0F, -23.0F, 0.0F, 0.0873F, 0.0F));

        head_left_claw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 30).addBox(8.0F, -1.5F, -9.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 30).addBox(4.0F, -0.5F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(15, 36).addBox(5.0F, -2.5F, -15.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 1.0F, 8.0F, 0.0F, 0.0873F, 0.0F));

        PartDefinition head_right_claw = head_front.addOrReplaceChild("head_right_claw", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -2.0F, -23.0F, 0.0F, -0.0873F, 0.0F));

        head_right_claw.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 21).addBox(-9.0F, -2.5F, -9.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 20).addBox(-5.0F, -1.5F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(-8.0F, -3.5F, -15.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 1.0F, 8.0F, 0.0F, -0.0873F, 0.0F));

        head_front.addOrReplaceChild("head_inner_teeth", CubeListBuilder.create().texOffs(0, 4).addBox(1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 30).addBox(-2.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 21).addBox(1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -25.0F));

        PartDefinition head_tendrils = head_front.addOrReplaceChild("head_tendrils", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.0F, -7.0F, 0.0F, -2.7053F, 0.0F));

        head_tendrils.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 16).addBox(4.5F, 0.0F, 4.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(4.0F, 0.5F, 4.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.1916F, -1.0F, 10.3962F, 0.0F, -0.4363F, 0.0F));

        head_tendrils.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(2, 16).addBox(-9.0F, 0.5F, 11.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-8.5F, 0.0F, 11.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8084F, -1.0F, 8.3962F, 0.0F, -0.4363F, 0.0F));

        devourer_head.addOrReplaceChild("head_back", CubeListBuilder.create().texOffs(28, 0).addBox(-3.0F, -5.0F, 3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 0).addBox(3.0F, -4.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 16).addBox(-4.0F, -4.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 6.0F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition devourer_body = partDefinition.addOrReplaceChild("devourer_body", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, -4.0F));

        devourer_body.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(24, 22).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(15, 36).addBox(-5.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 36).addBox(4.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 4.0F));

        devourer_body.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(30, 36).addBox(-3.0F, -5.0F, 3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(15, 33).addBox(3.0F, -4.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 32).addBox(-4.0F, -4.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 4.0F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createTailLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition devourer_tail = partDefinition.addOrReplaceChild("devourer_tail", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0));

        devourer_tail.addOrReplaceChild("tail_front", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(3, 36).addBox(-5.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 35).addBox(4.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 4.0F));

        devourer_tail.addOrReplaceChild("tail_back", CubeListBuilder.create().texOffs(39, 44).addBox(-3.0F, -5.0F, 15.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(46, 36).addBox(-2.0F, -4.0F, 18.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-1.0F, -3.5F, 20.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -8.0F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
