package terramine.common.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;
import terramine.TerraMine;
import terramine.client.render.gui.menu.TerrariaInventoryContainerMenu;
import terramine.common.init.ModComponents;
import terramine.common.misc.TeamColours;
import terramine.common.network.packet.BoneMealPacket;
import terramine.common.network.packet.UpdateInputPacket;
import terramine.common.network.types.*;
import terramine.extensions.PlayerStorages;

import java.util.List;
import java.util.UUID;

public class ServerPacketHandler {
    // Server
    public static final CustomPacketPayload.Type<LongNetworkType> BONE_MEAL_PACKET_ID = registerType(TerraMine.id("bone_meal"), false, LongNetworkType.CODEC);
    public static final CustomPacketPayload.Type<FloatSoundNetworkType> FALL_DISTANCE_PACKET_ID = registerType(TerraMine.id("fall_distance"), false, FloatSoundNetworkType.CODEC);
    public static final CustomPacketPayload.Type<IntBoolUUIDNetworkType> WALL_JUMP_PACKET_ID = registerType(TerraMine.id("wall_jump"), false, IntBoolUUIDNetworkType.CODEC);
    public static final CustomPacketPayload.Type<ItemNetworkType> DASH_PACKET_ID = registerType(TerraMine.id("dash"), false, ItemNetworkType.CODEC);
    public static final CustomPacketPayload.Type<InputNetworkType> CONTROLS_PACKET_ID = registerType(TerraMine.id("controls_packet"), false, InputNetworkType.CODEC);
    public static final CustomPacketPayload.Type<DoubleNetworkType> PLAYER_MOVEMENT_PACKET_ID = registerType(TerraMine.id("player_movement"), false, DoubleNetworkType.CODEC);
    public static final CustomPacketPayload.Type<FloatSoundNetworkType> ROCKET_BOOTS_SOUND_PACKET_ID = registerType(TerraMine.id("rocket_boots_sound"), false, FloatSoundNetworkType.CODEC);
    public static final CustomPacketPayload.Type<ParticleNetworkType> ROCKET_BOOTS_PARTICLE_PACKET_ID = registerType(TerraMine.id("rocket_boots_particles"), false, ParticleNetworkType.CODEC);
    public static final CustomPacketPayload.Type<LongNetworkType> OPEN_INVENTORY_PACKET_ID = registerType(TerraMine.id("open_inventory"), false, LongNetworkType.CODEC);
    public static final CustomPacketPayload.Type<IntBoolUUIDNetworkType> UPDATE_TEAM_PACKET_ID = registerType(TerraMine.id("update_team"), false, IntBoolUUIDNetworkType.CODEC);
    public static final CustomPacketPayload.Type<LongNetworkType> C2S_DOUBLE_JUMPED_ID = registerType(TerraMine.id("c2s_double_jumped"), false, LongNetworkType.CODEC);
    public static final CustomPacketPayload.Type<LongNetworkType> C2S_QUADRUPLE_JUMPED_ID = registerType(TerraMine.id("c2s_quadruple_jumped"), false, LongNetworkType.CODEC);

    // Client
    public static final CustomPacketPayload.Type<ItemNetworkType> SETUP_INVENTORY_PACKET_ID = registerType(TerraMine.id("setup_inventory"), true, ItemNetworkType.CODEC);
    public static final CustomPacketPayload.Type<ItemNetworkType> UPDATE_INVENTORY_PACKET_ID = registerType(TerraMine.id("update_inventory"), true, ItemNetworkType.CODEC);
    public static final CustomPacketPayload.Type<IntBoolUUIDNetworkType> UPDATE_BIOME_PACKET_ID = registerType(TerraMine.id("update_biome"), true, IntBoolUUIDNetworkType.CODEC);

    // Both
    public static final CustomPacketPayload.Type<IntBoolUUIDNetworkType> UPDATE_ACCESSORY_VISIBILITY_PACKET_ID = registerType(TerraMine.id("update_accessory_visibility"), IntBoolUUIDNetworkType.CODEC);

    public static void register() {
        // todo: probably have a register class that auto does this for each resource location above (also convert to Type like in BONE_MEAL_PACKET_ID)
        ServerPlayNetworking.registerGlobalReceiver(BONE_MEAL_PACKET_ID, BoneMealPacket::receive);

        ServerPlayNetworking.registerGlobalReceiver(CONTROLS_PACKET_ID, (buf, context) ->
                UpdateInputPacket.onMessage(UpdateInputPacket.read(buf.getBooleans()), context.player().server, context.player()));

        ServerPlayNetworking.registerGlobalReceiver(DASH_PACKET_ID, (buf, context) -> {
            ItemStack gear = buf.getItemStacks().getFirst();
            int cooldown = 10;

            context.player().server.execute(() -> {
                ServerPlayer player = context.player();
                for (int i = 0; i < 20; ++i) {
                    double d0 = player.level().random.nextGaussian() * 0.02D;
                    double d1 = player.level().random.nextGaussian() * 0.02D;
                    double d2 = player.level().random.nextGaussian() * 0.02D;
                    float random = (player.getRandom().nextFloat() - 0.5F) * 0.1F;
                    player.getServer().getLevel(player.level().dimension()).sendParticles(ParticleTypes.POOF, player.getX() + (double) (player.level().random.nextFloat() * player.getBbWidth() * 2.0F) - (double) player.getBbWidth() - d0 * 10.0D, player.getY() + (double) (player.level().random.nextFloat() * player.getBbHeight()) - d1 * 10.0D, player.getZ() + (double) (player.level().random.nextFloat() * player.getBbWidth() * 2.0F) - (double) player.getBbWidth() - d2 * 10.0D, 1, 0, -0.2D, 0, random);
                }

                player.level().playSound(null, player.blockPosition(), SoundEvents.PHANTOM_FLAP, SoundSource.PLAYERS, 1.0F, 2.0F);
                player.getCooldowns().addCooldown(gear.getItem(), cooldown);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(FALL_DISTANCE_PACKET_ID, (buf, context) -> {
            float fallDistance = buf.getFloat1();
            context.player().server.execute(() -> {
                context.player().fallDistance = fallDistance;
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(WALL_JUMP_PACKET_ID, (buf, context) -> {
            boolean wallJumped = buf.getBoolean();
            context.player().server.execute(() -> {
                ModComponents.MOVEMENT_ORDER.get(context.player()).setWallJumped(wallJumped);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(PLAYER_MOVEMENT_PACKET_ID, (buf, context) -> {
            double x = buf.getDouble1();
            double y = buf.getDouble2();
            double z = buf.getDouble3();
            context.player().server.execute(() -> {
                context.player().setDeltaMovement(x, y, z);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(ROCKET_BOOTS_SOUND_PACKET_ID, (buf, context) -> {
            SoundEvent sound = buf.getSoundEvent();
            float soundVolume = buf.getFloat1();
            float soundPitch = buf.getFloat2();
            context.player().server.execute(() -> {
                if (sound != null) {
                    context.player().level().playSound(null, context.player().blockPosition(), sound, SoundSource.PLAYERS, soundVolume, soundPitch);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(ROCKET_BOOTS_PARTICLE_PACKET_ID, (buf, context) -> {
            ServerPlayer player = context.player();
            SimpleParticleType particle1 = (SimpleParticleType) buf.getParticleOption().getType();
            //SimpleParticleType particle2 = (SimpleParticleType) buf.getParticleOption().getType();
            Vec3 vLeft = new Vec3(-0.15, -1.5, 0).xRot(0).yRot(player.yBodyRot * -0.017453292F);
            Vec3 vRight = new Vec3(0.15, -1.5, 0).xRot(0).yRot(player.yBodyRot * -0.017453292F);
            Vec3 playerPos = player.getPosition(0).add(0, 1.5, 0);
            float random = (player.getRandom().nextFloat() - 0.5F) * 0.1F;

            context.player().server.execute(() -> {
                Vec3 v = playerPos.add(vLeft);
                player.serverLevel().sendParticles(particle1, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
                //player.serverLevel().sendParticles(particle2, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
                v = playerPos.add(vRight);
                player.serverLevel().sendParticles(particle1, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
                //player.serverLevel().sendParticles(particle2, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(OPEN_INVENTORY_PACKET_ID, (buf, context) ->
                context.player().server.execute(() -> context.player().openMenu(new SimpleMenuProvider((id, inventory, player2) -> new TerrariaInventoryContainerMenu(context.player()), Component.empty()))));

        ServerPlayNetworking.registerGlobalReceiver(UPDATE_ACCESSORY_VISIBILITY_PACKET_ID, (buf, context) -> {
                int slot = buf.getInteger1();
                boolean isVisible = buf.getBoolean();
            context.player().server.execute(() -> ((PlayerStorages) context.player()).setSlotVisibility(slot, isVisible));
        });

        ServerPlayNetworking.registerGlobalReceiver(UPDATE_TEAM_PACKET_ID, (buf, context) -> {
            int slot = buf.getInteger1();
            context.player().server.execute(() -> {
                ModComponents.TEAMS.get(context.player()).setTeamColour(TeamColours.getTeam(slot));
                ModComponents.TEAMS.sync(context.player());
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(SETUP_INVENTORY_PACKET_ID, (buf, context) -> {
            UUID uuid = buf.getUUID();
            List<ItemStack> items = buf.getItemStacks();
            context.client().execute(() -> {
                if (context.client().level.getPlayerByUUID(uuid) != null) {
                    for (int i = 0; i < items.size(); i++) {
                        ((PlayerStorages) context.client().level.getPlayerByUUID(uuid)).getTerrariaInventory().setItem(i, items.get(i));
                    }
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(UPDATE_INVENTORY_PACKET_ID, (buf, context) -> {
            int slot = buf.getInteger();
            ItemStack itemStack = buf.getItemStacks().getFirst();
            UUID uuid = buf.getUUID();
            context.client().execute(() -> {
                if (context.client().level.getPlayerByUUID(uuid) != null) {
                    ((PlayerStorages) context.client().level.getPlayerByUUID(uuid)).getTerrariaInventory().setItem(slot, itemStack);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(UPDATE_ACCESSORY_VISIBILITY_PACKET_ID, (buf, context) -> {
            int slot = buf.integer1();
            boolean isVisible = buf.getBoolean();
            UUID uuid = buf.getUUID();
            context.client().execute(() -> {
                if (context.client().level.getPlayerByUUID(uuid) != null) {
                    ((PlayerStorages) context.client().level.getPlayerByUUID(uuid)).setSlotVisibility(slot, isVisible);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(UPDATE_BIOME_PACKET_ID, (buf, context) -> {
            int chunkX = buf.getInteger1();
            int chunkZ = buf.getInteger2();
            if (context.player() != null) {
                ((ClientLevel) context.player().level()).onChunkLoaded(new ChunkPos(chunkX, chunkZ));
            }
        });
    }

    public static <T extends CustomPacketPayload> CustomPacketPayload.Type<T> registerType(ResourceLocation resourceLocation, boolean S2C, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        if (S2C) {
            return PayloadTypeRegistry.playS2C().register(new CustomPacketPayload.Type<>(resourceLocation), streamCodec).type();
        }
        return PayloadTypeRegistry.playC2S().register(new CustomPacketPayload.Type<>(resourceLocation), streamCodec).type();
    }

    public static <T extends CustomPacketPayload> CustomPacketPayload.Type<T> registerType(ResourceLocation resourceLocation, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        CustomPacketPayload.Type<T> type = new CustomPacketPayload.Type<>(resourceLocation);
        PayloadTypeRegistry.playS2C().register(type, streamCodec);
        PayloadTypeRegistry.playC2S().register(type, streamCodec);
        return type;
    }
}
