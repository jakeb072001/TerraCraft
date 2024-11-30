package terramine.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import terramine.common.entity.block.InstantPrimedTNTEntity;
import terramine.common.init.ModEntities;

public class InstantTNTBlock extends TntBlock {
    public InstantTNTBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (blockState2.is(blockState.getBlock())) {
            return;
        }
        if (level.hasNeighborSignal(blockPos)) {
            InstantTNTBlock.explode(level, blockPos);
            level.removeBlock(blockPos, false);
        }
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, @Nullable Orientation orientation, boolean bl) {
        if (level.hasNeighborSignal(blockPos)) {
            InstantTNTBlock.explode(level, blockPos);
            level.removeBlock(blockPos, false);
        }
    }

    public static void explode(Level level, @NotNull BlockPos blockPos) {
        InstantTNTBlock.explode(level, blockPos, null);
    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Player player) {
        if (!level.isClientSide() && !player.isCreative() && blockState.getValue(UNSTABLE)) {
            InstantTNTBlock.explode(level, blockPos);
        }
        super.playerWillDestroy(level, blockPos, blockState, player);
        return blockState;
    }

    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity livingEntity) {
        if (level.isClientSide) {
            return;
        }
        InstantPrimedTNTEntity primedTnt = ModEntities.INSTANT_TNT.create(level, EntitySpawnReason.TRIGGERED);
        if (primedTnt != null) {
            primedTnt.setValues(level, (double) blockPos.getX() + 0.5, blockPos.getY(), (double) blockPos.getZ() + 0.5, livingEntity);
            level.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 1.0f, 1.0f);
            level.addFreshEntity(primedTnt);

            level.gameEvent(livingEntity, GameEvent.PRIME_FUSE, blockPos);
        }
    }

    @Override
    public void wasExploded(ServerLevel serverLevel, BlockPos blockPos, Explosion explosion) {
        if (serverLevel.isClientSide) {
            return;
        }
        InstantPrimedTNTEntity primedTnt = ModEntities.INSTANT_TNT.create(serverLevel, EntitySpawnReason.TRIGGERED);
        if (primedTnt != null) {
            primedTnt.setValues(serverLevel, (double) blockPos.getX() + 0.5, blockPos.getY(), (double) blockPos.getZ() + 0.5, explosion.getIndirectSourceEntity());
            serverLevel.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 1.0f, 1.0f);
            serverLevel.addFreshEntity(primedTnt);
        }
    }
}
