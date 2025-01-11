package terramine.client.render;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import terramine.TerraMine;

// todo: being registered as block models?
public class HeldItemModels {
    public static final ResourceLocation UMBRELLA_HELD_MODEL = TerraMine.id("umbrella_in_hand");
    public static final ResourceLocation MAGIC_MISSILE_HELD_MODEL = TerraMine.id("magic_missile_held");
    public static final ResourceLocation FLAMELASH_HELD_MODEL = TerraMine.id("flamelash_held");
    public static final ResourceLocation RAINBOW_ROD_HELD_MODEL = TerraMine.id("rainbow_rod_held");
    public static final ResourceLocation SPACE_GUN_HELD_MODEL = TerraMine.id("space_gun_held");
    public static final ResourceLocation GRENADE_HELD_MODEL = TerraMine.id("grenade_held");
    public static final ResourceLocation STICKY_GRENADE_HELD_MODEL = TerraMine.id("sticky_grenade_held");
    public static final ResourceLocation BOUNCY_GRENADE_HELD_MODEL = TerraMine.id("bouncy_grenade_held");
    public static final ResourceLocation BOMB_HELD_MODEL = TerraMine.id("bomb_held");
    public static final ResourceLocation STICKY_BOMB_HELD_MODEL = TerraMine.id("sticky_bomb_held");
    public static final ResourceLocation BOUNCY_BOMB_HELD_MODEL = TerraMine.id("bouncy_bomb_held");
    public static final ResourceLocation DYNAMITE_HELD_MODEL = TerraMine.id("dynamite_held");
    public static final ResourceLocation STICKY_DYNAMITE_HELD_MODEL = TerraMine.id("sticky_dynamite_held");
    public static final ResourceLocation BOUNCY_DYNAMITE_HELD_MODEL = TerraMine.id("bouncy_dynamite_held");

    public static void register() {
        ModelLoadingPlugin.register((context) -> context.addModels(
                UMBRELLA_HELD_MODEL,
                MAGIC_MISSILE_HELD_MODEL,
                FLAMELASH_HELD_MODEL,
                RAINBOW_ROD_HELD_MODEL,
                SPACE_GUN_HELD_MODEL,
                GRENADE_HELD_MODEL,
                STICKY_GRENADE_HELD_MODEL,
                BOUNCY_GRENADE_HELD_MODEL,
                BOMB_HELD_MODEL,
                STICKY_BOMB_HELD_MODEL,
                BOUNCY_BOMB_HELD_MODEL,
                DYNAMITE_HELD_MODEL,
                STICKY_DYNAMITE_HELD_MODEL,
                BOUNCY_DYNAMITE_HELD_MODEL
        ));
    }
}
