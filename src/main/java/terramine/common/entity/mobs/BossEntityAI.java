package terramine.common.entity.mobs;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import terramine.common.entity.misc.ClientItemEntity;
import terramine.common.init.ModComponents;
import terramine.common.init.ModEntities;
import terramine.common.init.ModItems;
import terramine.common.misc.TeamColours;

// TODO: Boss Stuff
/**
 * Heavy WIP, needs to:
 * Display a boss health bar (that looks like it's from Terraria),
 * Boss music,
 * Track the bosses total health (eg, devourer and twins aren't one entity),
 * Drop loot bag for players that where targeted by boss (which will be a custom entity that's client sided, once picked up is a normal item though and is a storage container you can open and take items from but not put items in),
 * Limit quantity of Bosses that can spawn within an area of a specified size,
 * Make the boss leave once it is day or there are no nearby players that the boss can target.
 * <p>
 * First, make a team system, by default it will just use preset colours that each player can select just like in Terraria. But if FTB Teams or similar is installed then allow for proper team creation (best for bigger servers).
 */
public class BossEntityAI extends PathfinderMob implements Enemy {
    private final ServerBossEvent bossEvent; // TODO: make custom class that has terraria looking boss bar

    protected BossEntityAI(EntityType<? extends BossEntityAI> entityType, Level level) {
        super(entityType, level);
        this.bossEvent = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);
        //level.playLocalSound(this.blockPosition(), ModSoundEvents.BOSS_MUSIC_1, SoundSource.HOSTILE, 1f, 1f, false); // TODO: sound doesn't loop, sound replays when entity reloads, sound doesn't fade out when boss dies
        this.xpReward = 5;
    }

    public @NotNull SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    public void aiStep() {
        this.updateSwingTime();
        this.updateNoActionTime();
        super.aiStep();
    }

    protected void customServerAiStep(ServerLevel serverLevel) {
        super.customServerAiStep(serverLevel);
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public void die(DamageSource damageSource) {
        super.die(damageSource);
        this.bossEvent.setProgress(0);
    }

    protected void updateNoActionTime() {
        float f = this.getLightLevelDependentMagicValue();
        if (f > 0.5F) {
            this.noActionTime += 2;
        }

    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    protected @NotNull SoundEvent getSwimSound() {
        return SoundEvents.HOSTILE_SWIM;
    }

    protected @NotNull SoundEvent getSwimSplashSound() {
        return SoundEvents.HOSTILE_SPLASH;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return SoundEvents.HOSTILE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.HOSTILE_DEATH;
    }

    public LivingEntity.@NotNull Fallsounds getFallSounds() {
        return new LivingEntity.Fallsounds(SoundEvents.HOSTILE_SMALL_FALL, SoundEvents.HOSTILE_BIG_FALL);
    }

    public float getWalkTargetValue(@NotNull BlockPos blockPos, LevelReader levelReader) {
        return -levelReader.getPathfindingCostFromLightLevels(blockPos);
    }

    public void startSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        if (checkPlayerTeam(serverPlayer)) {
            this.bossEvent.addPlayer(serverPlayer);
        }
    }

    public void stopSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
    }

    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        this.bossEvent.setName(this.getDisplayName());
    }

    public boolean shouldDropExperience() {
        return true;
    }

    public boolean canHoldItem(@NotNull ItemStack itemStack) {
        return false;
    }

    public boolean wantsToPickUp(@NotNull ItemStack itemStack) {
        return false;
    }

    public void checkDespawn() {}

    protected boolean canRide(@NotNull Entity entity) {
        return false;
    }

    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public boolean checkSpawnRules(@NotNull LevelAccessor world, @NotNull EntitySpawnReason spawnReason) {
        return true;
    }

    /**
     * Team related stuff past this point
     */
    private TeamColours targetTeam;
    private boolean naturalSpawning = true;

    public void setTargetTeam(TeamColours targetTeam) {
        this.targetTeam = targetTeam;
        naturalSpawning = false;
    }

    private boolean checkPlayerTeam(Player player) {
        return ModComponents.TEAMS.get(player).getTeamColour() == targetTeam || naturalSpawning;
    }

    // Drop Loot Bags per player
    // todo: test if this works correctly, the target condition only targets in combat players which may exclude some players that shouldn't be excluded, also maybe need to change the inflate value
    protected void dropAllDeathLoot(ServerLevel serverLevel, @NotNull DamageSource damageSource) {
        for (Player player : serverLevel.getNearbyPlayers(TargetingConditions.DEFAULT, this, new AABB(this.position(), this.position()).inflate(32)) ) {
            if (checkPlayerTeam(player)) {
                ClientItemEntity clientItemEntity = new ClientItemEntity(ModEntities.CLIENT_ITEM, this.level());
                clientItemEntity.setValues(treasureBagItem().getDefaultInstance(), this.getX(), this.getY(), this.getZ());
                clientItemEntity.setClientPlayer(player.getUUID());
                this.level().addFreshEntity(clientItemEntity);
            }
        }
        this.dropExperience((ServerLevel) this.level(), this.level().getNearestPlayer(this, 10));
    }

    protected Item treasureBagItem() {
        return ModItems.EYE_OF_CTHULHU_TREASURE_BAG;
    }

    // Boss can only attack players on the same team as the player that summoned the boss
    public void playerTouch(@NotNull Player player) {
        if (checkPlayerTeam(player)) {
            super.playerTouch(player);

            if (this.isAlive()) {
                player.hurt(damageSources().mobAttack(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
            }
        }
    }

    // Only players on the same team as the player that summoned the boss can attack it
    public boolean hurtServer(ServerLevel serverLevel, @NotNull DamageSource damageSource, float f) {
        if (damageSource.getEntity() instanceof Player player) {
            if (checkPlayerTeam(player)) {
                if (!super.hurtServer(serverLevel, damageSource, f)) {
                    return false;
                } else if (!(this.level() instanceof ServerLevel)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
