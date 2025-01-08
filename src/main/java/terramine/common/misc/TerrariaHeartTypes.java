package terramine.common.misc;

import net.minecraft.resources.ResourceLocation;
import static terramine.TerraMine.id;

public enum TerrariaHeartTypes {
    WEREWOLF(id("textures/gui/hearts/werewolf/werewolf_full"), id("textures/gui/hearts/werewolf/werewolf_full_blinking"), id("textures/gui/hearts/werewolf/werewolf_half"), id("textures/gui/hearts/werewolf/werewolf_half_blinking"), id("textures/gui/hearts/werewolf/werewolf_full_hardcore"), id("textures/gui/hearts/werewolf/werewolf_full_hardcore_blinking"), id("textures/gui/hearts/werewolf/werewolf_half_hardcore"), id("textures/gui/hearts/werewolf/werewolf_half_hardcore_blinking")),
    POISONED_WEREWOLF(id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_full"), id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_full_blinking"), id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_half"), id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_half_blinking"), id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_full_hardcore"), id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_full_hardcore_blinking"), id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_half_hardcore"), id("textures/gui/hearts/werewolf/poisoned/werewolf_poisoned_half_hardcore_blinking")),
    WITHERED_WEREWOLF(id("textures/gui/hearts/werewolf/withered/werewolf_withered_full"), id("textures/gui/hearts/werewolf/withered/werewolf_withered_full_blinking"), id("textures/gui/hearts/werewolf/withered/werewolf_withered_half"), id("textures/gui/hearts/werewolf/withered/werewolf_withered_half_blinking"), id("textures/gui/hearts/werewolf/withered/werewolf_withered_full_hardcore"), id("textures/gui/hearts/werewolf/withered/werewolf_withered_full_hardcore_blinking"), id("textures/gui/hearts/werewolf/withered/werewolf_withered_half_hardcore"), id("textures/gui/hearts/werewolf/withered/werewolf_withered_half_hardcore_blinking")),
    MERFOLK(id("textures/gui/hearts/merfolk/merfolk_full"), id("textures/gui/hearts/merfolk/merfolk_full_blinking"), id("textures/gui/hearts/merfolk/merfolk_half"), id("textures/gui/hearts/merfolk/merfolk_half_blinking"), id("textures/gui/hearts/merfolk/merfolk_full_hardcore"), id("textures/gui/hearts/merfolk/merfolk_full_hardcore_blinking"), id("textures/gui/hearts/merfolk/merfolk_half_hardcore"), id("textures/gui/hearts/merfolk/merfolk_half_hardcore_blinking")),
    POISONED_MERFOLK(id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_full"), id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_full_blinking"), id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_half"), id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_half_blinking"), id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_full_hardcore"), id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_full_hardcore_blinking"), id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_half_hardcore"), id("textures/gui/hearts/merfolk/poisoned/merfolk_poisoned_half_hardcore_blinking")),
    WITHERED_MERFOLK(id("textures/gui/hearts/merfolk/withered/merfolk_withered_full"), id("textures/gui/hearts/merfolk/withered/merfolk_withered_full_blinking"), id("textures/gui/hearts/merfolk/withered/merfolk_withered_half"), id("textures/gui/hearts/merfolk/withered/merfolk_withered_half_blinking"), id("textures/gui/hearts/merfolk/withered/merfolk_withered_full_hardcore"), id("textures/gui/hearts/merfolk/withered/merfolk_withered_full_hardcore_blinking"), id("textures/gui/hearts/merfolk/withered/merfolk_withered_half_hardcore"), id("textures/gui/hearts/merfolk/withered/merfolk_withered_half_hardcore_blinking"));

    private final ResourceLocation full;
    private final ResourceLocation fullBlinking;
    private final ResourceLocation half;
    private final ResourceLocation halfBlinking;
    private final ResourceLocation hardcoreFull;
    private final ResourceLocation hardcoreFullBlinking;
    private final ResourceLocation hardcoreHalf;
    private final ResourceLocation hardcoreHalfBlinking;

    TerrariaHeartTypes(final ResourceLocation resourceLocation, final ResourceLocation resourceLocation2, final ResourceLocation resourceLocation3, final ResourceLocation resourceLocation4, final ResourceLocation resourceLocation5, final ResourceLocation resourceLocation6, final ResourceLocation resourceLocation7, final ResourceLocation resourceLocation8) {
        this.full = resourceLocation;
        this.fullBlinking = resourceLocation2;
        this.half = resourceLocation3;
        this.halfBlinking = resourceLocation4;
        this.hardcoreFull = resourceLocation5;
        this.hardcoreFullBlinking = resourceLocation6;
        this.hardcoreHalf = resourceLocation7;
        this.hardcoreHalfBlinking = resourceLocation8;
    }

    public ResourceLocation getSprite(boolean bl, boolean bl2, boolean bl3) {
        if (!bl) {
            if (bl2) {
                return bl3 ? this.halfBlinking : this.half;
            } else {
                return bl3 ? this.fullBlinking : this.full;
            }
        } else if (bl2) {
            return bl3 ? this.hardcoreHalfBlinking : this.hardcoreHalf;
        } else {
            return bl3 ? this.hardcoreFullBlinking : this.hardcoreFull;
        }
    }
}
