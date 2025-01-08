package terramine.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import terramine.common.utility.CorruptionHelper;

public class CorruptedFallingBlock extends CorruptionHelper {
    public CorruptedFallingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState2, boolean bl) {
        level.scheduleTick(blockPos, this, this.getDelayAfterPlace());
    }

    @Override
    public @NotNull BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
        scheduledTickAccess.scheduleTick(blockPos, this, this.getDelayAfterPlace());
        return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
    }

    @Override
    public void tick(@NotNull BlockState blockState, @NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (!FallingBlock.isFree(serverLevel.getBlockState(blockPos.below())) || blockPos.getY() < serverLevel.getMinY()) {
            return;
        }
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(serverLevel, blockPos, blockState);
        this.falling(fallingBlockEntity);
    }

    protected void falling(FallingBlockEntity fallingBlockEntity) {
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    public static boolean isFree(BlockState blockState) {
        return blockState.isAir() || blockState.is(BlockTags.FIRE) || blockState.liquid() || blockState.canBeReplaced();
    }

    @Override
    public void animateTick(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, RandomSource random) {
        if (random.nextInt(16) == 0 && FallingBlock.isFree(level.getBlockState(blockPos.below()))) {
            double d = (double)blockPos.getX() + random.nextDouble();
            double e = (double)blockPos.getY() - 0.05;
            double f = (double)blockPos.getZ() + random.nextDouble();
            level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, blockState), d, e, f, 0.0, 0.0, 0.0);
        }
    }
}
