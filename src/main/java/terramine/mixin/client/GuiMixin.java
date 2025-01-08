package terramine.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terramine.TerraMine;
import terramine.common.init.ModComponents;
import terramine.common.init.ModMobEffects;
import terramine.common.misc.TerrariaHeartTypes;

@Mixin(Gui.class)
public abstract class GuiMixin {

	@Unique private final ResourceLocation MANA_ICONS_TEXTURE = TerraMine.id("textures/gui/manabar.png");
	@Shadow protected abstract Player getCameraPlayer();

	@Inject(method = "renderPlayerHealth", require = 0, at = @At(value = "TAIL"))
	private void renderManaBar(GuiGraphics guiGraphics, CallbackInfo ci) {
		Player player = this.getCameraPlayer();

		if (player != null) {
			int left = guiGraphics.guiWidth() - 15;
			int top = guiGraphics.guiHeight() -15;
			int currentMana = ModComponents.MANA_HANDLER.get(player).getCurrentMana();
			int maxMana = ModComponents.MANA_HANDLER.get(player).getMaxMana();

			int count = (int) Math.floor(currentMana / 20F);
			if (count >= maxMana) {
				count = maxMana;
			}

			for (int i = 0; i < count + 1; i++) {
				VertexConsumer vertexConsumer = guiGraphics.bufferSource.getBuffer(RenderType.guiTextured(MANA_ICONS_TEXTURE));
				if (i == count) {
					if (currentMana <= maxMana) {
						float countFloat = currentMana / 20F + 10;
						vertexConsumer.setColor(1, 1, 1, (countFloat) % ((int) (countFloat)));
					}
				}
				guiGraphics.blit(RenderType::guiTextured, MANA_ICONS_TEXTURE, left, top - i * 10, -90, 0, 0, 10, 11, 10, 11);
				vertexConsumer.setColor(1, 1, 1, 1);
			}
		}
	}

	// todo: when health is critical (hearts wiggle), these hearts do not line up
	// todo: probably find a way to replace this with HeartType, that way i don't have to use all this code i can just add a new HeartType and have that be used
	@WrapMethod(method = "renderHeart")
	private void renderCustomHearts(GuiGraphics guiGraphics, Gui.HeartType heartType, int i, int j, boolean bl, boolean bl2, boolean bl3, Operation<Void> original) {
		if (getCameraPlayer().hasEffect(ModMobEffects.MERFOLK)) {
			if (getCameraPlayer().hasEffect(MobEffects.WITHER)) {
				guiGraphics.blitSprite(RenderType::guiTextured, TerrariaHeartTypes.WITHERED_MERFOLK.getSprite(bl, bl3, bl2), i, j, 9, 9);
				return;
			}

			if (getCameraPlayer().hasEffect(MobEffects.POISON)) {
				guiGraphics.blitSprite(RenderType::guiTextured, TerrariaHeartTypes.POISONED_MERFOLK.getSprite(bl, bl3, bl2), i, j, 9, 9);
				return;
			}

			guiGraphics.blitSprite(RenderType::guiTextured, TerrariaHeartTypes.MERFOLK.getSprite(bl, bl3, bl2), i, j, 9, 9);
			return;
		}
		if (getCameraPlayer().hasEffect(ModMobEffects.WEREWOLF)) {
			if (getCameraPlayer().hasEffect(MobEffects.WITHER)) {
				guiGraphics.blitSprite(RenderType::guiTextured, TerrariaHeartTypes.WITHERED_WEREWOLF.getSprite(bl, bl3, bl2), i, j, 9, 9);
				return;
			}

			if (getCameraPlayer().hasEffect(MobEffects.POISON)) {
				guiGraphics.blitSprite(RenderType::guiTextured, TerrariaHeartTypes.POISONED_WEREWOLF.getSprite(bl, bl3, bl2), i, j, 9, 9);
				return;
			}

			guiGraphics.blitSprite(RenderType::guiTextured, TerrariaHeartTypes.WEREWOLF.getSprite(bl, bl3, bl2), i, j, 9, 9);
			return;
		}

		original.call(guiGraphics, heartType, i, j, bl, bl2, bl3);
	}
}
