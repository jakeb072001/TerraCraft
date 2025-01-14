package terramine.common.item.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import terramine.common.entity.mobs.BossEntityAI;
import terramine.common.init.ModComponents;
import terramine.common.init.ModSoundEvents;
import terramine.common.item.TerrariaItemConfigurable;

// todo: add boss spawn limit like in Terraria
public class BossSpawnItem extends TerrariaItemConfigurable {
    private final EntityType<?> defaultType;

    public BossSpawnItem(EntityType<? extends Mob> entityType, Properties properties) {
        super(properties);
        this.defaultType = entityType;
    }

    @Override
    public ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity entity) {
        Player player = (Player) entity;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, itemStack);
        }

        if (!level.isClientSide) {
            if (level.isNight()) {
                Entity bossEntity = defaultType.spawn((ServerLevel) level, itemStack, player, entity.blockPosition().offset(entity.getRandom().nextInt(-10, 10), entity.getRandom().nextInt(10, 20), entity.getRandom().nextInt(-10, 10)), EntitySpawnReason.MOB_SUMMONED, false, false);
                if (bossEntity instanceof BossEntityAI boss) {
                    boss.setTargetTeam(ModComponents.TEAMS.get(player).getTeamColour());
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, boss.position());
                    level.playSound(null, player.blockPosition(), ModSoundEvents.BOSS_ROAR_0, SoundSource.PLAYERS, 0.50f, 1f);
                    player.awardStat(Stats.ITEM_USED.get(this));
                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                }
            } else {
                player.displayClientMessage(Component.translatable("text.boss_summon.fail").withStyle(ChatFormatting.RED), false);
            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }

    @Override
    public @NotNull ItemUseAnimation getUseAnimation(@NotNull ItemStack stack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public InteractionResult use(@NotNull Level world, Player user, @NotNull InteractionHand hand) {
        user.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 15;
    }
}
