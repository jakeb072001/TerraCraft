package terramine.common.network;

import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
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
import terramine.common.network.packet.BufferConverter;
import terramine.common.network.packet.UpdateInputPacket;
import terramine.extensions.PlayerStorages;

import java.util.ArrayList;
import java.util.UUID;

// todo: fucking redo networking i guess, very good job mojang, very cool: https://fabricmc.net/2024/04/19/1205.html
// options: either create a converter for old bytebuf to type OR create a packet for what is needed, such as transferring long for the bone meal packet or for rocket boots sounds, etc, probably easier especially since friendlybytebuf cant store a lot of things anymore...
public class ServerPacketHandler {
    // Server
    public static final CustomPacketPayload.Type<BufferConverter> BONE_MEAL_PACKET_ID = registerType(TerraMine.id("bone_meal"), false);
    public static final CustomPacketPayload.Type<BufferConverter> FALL_DISTANCE_PACKET_ID = registerType(TerraMine.id("fall_distance"), false);
    public static final CustomPacketPayload.Type<BufferConverter> WALL_JUMP_PACKET_ID = registerType(TerraMine.id("wall_jump"), false);
    public static final CustomPacketPayload.Type<BufferConverter> DASH_PACKET_ID = registerType(TerraMine.id("dash"), false);
    public static final CustomPacketPayload.Type<BufferConverter> CONTROLS_PACKET_ID = registerType(TerraMine.id("controls_packet"), false);
    public static final CustomPacketPayload.Type<BufferConverter> PLAYER_MOVEMENT_PACKET_ID = registerType(TerraMine.id("player_movement"), false);
    public static final CustomPacketPayload.Type<BufferConverter> ROCKET_BOOTS_SOUND_PACKET_ID = registerType(TerraMine.id("rocket_boots_sound"), false);
    public static final CustomPacketPayload.Type<BufferConverter> ROCKET_BOOTS_PARTICLE_PACKET_ID = registerType(TerraMine.id("rocket_boots_particles"), false);
    public static final CustomPacketPayload.Type<BufferConverter> OPEN_INVENTORY_PACKET_ID = registerType(TerraMine.id("open_inventory"), false);
    public static final CustomPacketPayload.Type<BufferConverter> UPDATE_TEAM_PACKET_ID = registerType(TerraMine.id("update_team"), false);
    public static final CustomPacketPayload.Type<BufferConverter> C2S_DOUBLE_JUMPED_ID = registerType(TerraMine.id("c2s_double_jumped"), false);
    public static final CustomPacketPayload.Type<BufferConverter> C2S_QUADRUPLE_JUMPED_ID = registerType(TerraMine.id("c2s_quadruple_jumped"), false);

    // Client
    public static final CustomPacketPayload.Type<BufferConverter> SETUP_INVENTORY_PACKET_ID = registerType(TerraMine.id("setup_inventory"), true);
    public static final CustomPacketPayload.Type<BufferConverter> UPDATE_INVENTORY_PACKET_ID = registerType(TerraMine.id("update_inventory"), true);
    public static final CustomPacketPayload.Type<BufferConverter> UPDATE_BIOME_PACKET_ID = registerType(TerraMine.id("update_biome"), true);

    // Both
    public static final CustomPacketPayload.Type<BufferConverter> UPDATE_ACCESSORY_VISIBILITY_PACKET_ID = registerType(TerraMine.id("update_accessory_visibility"));

    public static void register() {
        // todo: probably have a register class that auto does this for each resource location above (also convert to Type like in BONE_MEAL_PACKET_ID)
        PayloadTypeRegistry.playS2C().register(BONE_MEAL_PACKET_ID, BufferConverter.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(BONE_MEAL_PACKET_ID, BoneMealPacket::receive);

        ServerPlayNetworking.registerGlobalReceiver(CONTROLS_PACKET_ID, (buf, context) ->
                UpdateInputPacket.onMessage(UpdateInputPacket.read(buf.getFriendlybyteBuf()), context.player().server, context.player()));

        ServerPlayNetworking.registerGlobalReceiver(DASH_PACKET_ID, (buf, context) -> {
            ItemStack gear = buf.getItemStack();
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
            float fallDistance = buf.getFriendlybyteBuf().readFloat();
            context.player().server.execute(() -> {
                context.player().fallDistance = fallDistance;
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(WALL_JUMP_PACKET_ID, (buf, context) -> {
            boolean wallJumped = buf.getFriendlybyteBuf().readBoolean();
            context.player().server.execute(() -> {
                ModComponents.MOVEMENT_ORDER.get(context.player()).setWallJumped(wallJumped);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(PLAYER_MOVEMENT_PACKET_ID, (buf, context) -> {
            double x = buf.getFriendlybyteBuf().readDouble();
            double y = buf.getFriendlybyteBuf().readDouble();
            double z = buf.getFriendlybyteBuf().readDouble();
            context.player().server.execute(() -> {
                context.player().setDeltaMovement(x, y, z);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(ROCKET_BOOTS_SOUND_PACKET_ID, (buf, context) -> {
            SoundEvent sound = buf.getSoundEvent();
            float soundVolume = buf.getFriendlybyteBuf().readFloat();
            float soundPitch = buf.getFriendlybyteBuf().readFloat();
            context.player().server.execute(() -> {
                if (sound != null) {
                    context.player().level().playSound(null, context.player().blockPosition(), sound, SoundSource.PLAYERS, soundVolume, soundPitch);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(ROCKET_BOOTS_PARTICLE_PACKET_ID, (buf, context) -> {
            ServerPlayer player = context.player();
            SimpleParticleType particle1 = (SimpleParticleType) buf.getParticleOption().getType();
            SimpleParticleType particle2 = (SimpleParticleType) buf.getParticleOption().getType();
            Vec3 vLeft = new Vec3(-0.15, -1.5, 0).xRot(0).yRot(player.yBodyRot * -0.017453292F);
            Vec3 vRight = new Vec3(0.15, -1.5, 0).xRot(0).yRot(player.yBodyRot * -0.017453292F);
            Vec3 playerPos = player.getPosition(0).add(0, 1.5, 0);
            float random = (player.getRandom().nextFloat() - 0.5F) * 0.1F;

            context.player().server.execute(() -> {
                Vec3 v = playerPos.add(vLeft);
                player.serverLevel().sendParticles(particle1, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
                player.serverLevel().sendParticles(particle2, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
                v = playerPos.add(vRight);
                player.serverLevel().sendParticles(particle1, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
                player.serverLevel().sendParticles(particle2, v.x, v.y, v.z, 1, 0, -0.2D, 0, random);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(OPEN_INVENTORY_PACKET_ID, (buf, context) ->
                context.player().server.execute(() -> context.player().openMenu(new SimpleMenuProvider((id, inventory, player2) -> new TerrariaInventoryContainerMenu(context.player()), Component.empty()))));

        ServerPlayNetworking.registerGlobalReceiver(UPDATE_ACCESSORY_VISIBILITY_PACKET_ID, (buf, context) -> {
                int slot = buf.getFriendlybyteBuf().readInt();
                boolean isVisible = buf.getFriendlybyteBuf().readBoolean();
            context.player().server.execute(() -> ((PlayerStorages) context.player()).setSlotVisibility(slot, isVisible));
        });

        ServerPlayNetworking.registerGlobalReceiver(UPDATE_TEAM_PACKET_ID, (buf, context) -> {
            int slot = buf.getFriendlybyteBuf().readInt();
            context.player().server.execute(() -> {
                ModComponents.TEAMS.get(context.player()).setTeamColour(TeamColours.getTeam(slot));
                ModComponents.TEAMS.sync(context.player());
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(SETUP_INVENTORY_PACKET_ID, (buf, context) -> {
            UUID uuid = buf.getFriendlybyteBuf().readUUID();
            ArrayList<ItemStack> items = Lists.newArrayList();
            for (int i = 0; i < 35; i++) {
                items.add(buf.getItemStack());
            }
            context.client().execute(() -> {
                if (context.client().level.getPlayerByUUID(uuid) != null) {
                    for (int i = 0; i < items.size(); i++) {
                        ((PlayerStorages) context.client().level.getPlayerByUUID(uuid)).getTerrariaInventory().setItem(i, items.get(i));
                    }
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(UPDATE_INVENTORY_PACKET_ID, (buf, context) -> {
            int slot = buf.getFriendlybyteBuf().readInt();
            ItemStack itemStack = buf.getItemStack();
            UUID uuid = buf.getFriendlybyteBuf().readUUID();
            context.client().execute(() -> {
                if (context.client().level.getPlayerByUUID(uuid) != null) {
                    ((PlayerStorages) context.client().level.getPlayerByUUID(uuid)).getTerrariaInventory().setItem(slot, itemStack);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(UPDATE_ACCESSORY_VISIBILITY_PACKET_ID, (buf, context) -> {
            int slot = buf.getFriendlybyteBuf().readInt();
            boolean isVisible = buf.getFriendlybyteBuf().readBoolean();
            UUID uuid = buf.getFriendlybyteBuf().readUUID();
            context.client().execute(() -> {
                if (context.client().level.getPlayerByUUID(uuid) != null) {
                    ((PlayerStorages) context.client().level.getPlayerByUUID(uuid)).setSlotVisibility(slot, isVisible);
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(UPDATE_BIOME_PACKET_ID, (buf, context) -> {
            int chunkX = buf.getFriendlybyteBuf().readInt();
            int chunkZ = buf.getFriendlybyteBuf().readInt();
            if (context.player() != null) {
                ((ClientLevel) context.player().level()).onChunkLoaded(new ChunkPos(chunkX, chunkZ));
            }
        });
    }

    public static CustomPacketPayload.Type<BufferConverter> registerType(ResourceLocation resourceLocation, boolean S2C) {
        if (S2C) {
            return PayloadTypeRegistry.playS2C().register(new CustomPacketPayload.Type<>(resourceLocation), BufferConverter.CODEC).type();
        }
        return PayloadTypeRegistry.playC2S().register(new CustomPacketPayload.Type<>(resourceLocation), BufferConverter.CODEC).type();
    }

    public static CustomPacketPayload.Type<BufferConverter> registerType(ResourceLocation resourceLocation) {
        CustomPacketPayload.Type<BufferConverter> type = new CustomPacketPayload.Type<>(resourceLocation);
        PayloadTypeRegistry.playS2C().register(type, BufferConverter.CODEC);
        PayloadTypeRegistry.playC2S().register(type, BufferConverter.CODEC);
        return type;
    }
}
