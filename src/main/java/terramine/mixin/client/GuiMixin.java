package terramine.mixin.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terramine.TerraMine;
import terramine.common.init.ModComponents;
import terramine.common.init.ModMobEffects;

@Mixin(Gui.class)
public abstract class GuiMixin {

	@Unique private final ResourceLocation MANA_ICONS_TEXTURE = TerraMine.id("textures/gui/manabar.png");
	@Unique private final ResourceLocation WEREWOLF_HEARTS_TEXTURE = TerraMine.id("textures/gui/hearts/werewolf_hearts.png");
	@Unique private final ResourceLocation WEREWOLF_POISON_HEARTS_TEXTURE = TerraMine.id("textures/gui/hearts/werewolf_poison_hearts.png");
	@Unique private final ResourceLocation WEREWOLF_WITHER_HEARTS_TEXTURE = TerraMine.id("textures/gui/hearts/werewolf_wither_hearts.png");
	@Unique private final ResourceLocation MERFOLK_HEARTS_TEXTURE = TerraMine.id("textures/gui/hearts/merfolk_hearts.png");
	@Unique private final ResourceLocation MERFOLK_POISON_HEARTS_TEXTURE = TerraMine.id("textures/gui/hearts/merfolk_poison_hearts.png");
	@Unique private final ResourceLocation MERFOLK_WITHER_HEARTS_TEXTURE = TerraMine.id("textures/gui/hearts/merfolk_wither_hearts.png");

	@Shadow private int screenHeight;
	@Shadow private int screenWidth;

	@Shadow protected abstract Player getCameraPlayer();

	@Shadow @Final private RandomSource random;

	@Inject(method = "renderPlayerHealth", require = 0, at = @At(value = "TAIL"))
	private void renderManaBar(GuiGraphics guiGraphics, CallbackInfo ci) {
		Player player = this.getCameraPlayer();

		if (player != null) {
			int left = this.screenWidth - 15;
			int top = this.screenHeight -15;
			int currentMana = ModComponents.MANA_HANDLER.get(player).getCurrentMana();
			int maxMana = ModComponents.MANA_HANDLER.get(player).getMaxMana();

			int count = (int) Math.floor(currentMana / 20F);
			if (count >= maxMana) {
				count = maxMana;
			}

			for (int i = 0; i < count + 1; i++) {
				if (i == count) {
					if (currentMana <= maxMana) {
						float countFloat = currentMana / 20F + 10;
						guiGraphics.setColor(1, 1, 1, (countFloat) % ((int) (countFloat)));
					}
				}
				guiGraphics.blit(MANA_ICONS_TEXTURE, left, top - i * 10, -90, 0, 0, 10, 11, 10, 11);
				guiGraphics.setColor(1, 1, 1, 1);
			}
		}
	}

	// todo: when health is critical (hearts wiggle), these hearts do not line up
	// todo: probably find a way to replace this with HeartType, that way i don't have to use all this code i can just add a new HeartType and have that be used
	@Inject(method = "renderHearts", require = 0, at = @At(value = "TAIL"))
	private void renderCustomHearts(GuiGraphics guiGraphics, Player player, int i, int j, int k, int l, float f, int m, int n, int o, boolean bl, CallbackInfo ci) {
		ResourceLocation heartTexture = null;
		if (player.hasEffect(ModMobEffects.WEREWOLF)) {
			heartTexture = WEREWOLF_HEARTS_TEXTURE;
			if (player.hasEffect(MobEffects.POISON)) {
				heartTexture = WEREWOLF_POISON_HEARTS_TEXTURE;
			} else if (player.hasEffect(MobEffects.WITHER)) {
				heartTexture = WEREWOLF_WITHER_HEARTS_TEXTURE;
			}
		} else if (player.hasEffect(ModMobEffects.MERFOLK)) {
			heartTexture = MERFOLK_HEARTS_TEXTURE;
			if (player.hasEffect(MobEffects.POISON)) {
				heartTexture = MERFOLK_POISON_HEARTS_TEXTURE;
			} else if (player.hasEffect(MobEffects.WITHER)) {
				heartTexture = MERFOLK_WITHER_HEARTS_TEXTURE;
			}
		}

		if (heartTexture != null) {
			int p = player.level().getLevelData().isHardcore() ? 9 : 0;
			int q = Mth.ceil((double) f / 2.0);
			int r = Mth.ceil((double) o / 2.0);

			for (int t = q + r - 1; t >= 0; --t) {
				int u = t / 10;
				int v = t % 10;
				int w = i + v * 8;
				int x = j - u * k;
				if (m + o <= 4) {
					x += random.nextInt(2);
				}

				if (t < q && t == l) {
					x -= 2;
				}

				int y = t * 2;

				boolean bl4;
				if (bl && y < n) {
					bl4 = y + 1 == n;
					guiGraphics.blit(heartTexture, w, x, bl4 ? 9 : 0, p, 9, 9, 18, 18);
				}

				if (y < m) {
					bl4 = y + 1 == m;
					guiGraphics.blit(heartTexture, w, x, bl4 ? 9 : 0, p, 9, 9, 18, 18);
				}
			}
		}
	}
}
