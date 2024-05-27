package terramine.common.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import terramine.TerraMine;
import terramine.client.render.gui.menu.ChestBlockContainerMenu;

public class ChestEntity extends ChestBlockEntity {
    String name;
    MenuType<ChestBlockContainerMenu> menu;
    boolean trapped;
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter(){
        @Override
        protected void onOpen(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
            playSound(level, blockPos, blockState, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
            playSound(level, blockPos, blockState, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, int i, int j) {
            signalOpenCount(level, blockPos, blockState, i, j);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestBlockContainerMenu) {
                Container container = ((ChestBlockContainerMenu)player.containerMenu).getContainer();
                return container == ChestEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(ChestEntity.this);
            }
            return false;
        }
    };


    public ChestEntity(String name, MenuType<ChestBlockContainerMenu> menu, BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.name = name;
        this.menu = menu;
        this.setItems(NonNullList.withSize(40, ItemStack.EMPTY));
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return new ChestBlockContainerMenu(40, menu, i, inventory, ContainerLevelAccess.create(level, getBlockPos()));
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("entity." + TerraMine.MOD_ID + "." + name);
    }

    private final ChestLidController chestLidController = new ChestLidController();

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, ChestEntity chestBlockEntity) {
        chestBlockEntity.chestLidController.tickLid();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void loadAdditional(@NotNull CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        this.setTrapped(compoundTag.getBoolean("trapped"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putBoolean("trapped", trapped);
    }

    @Override
    public void startOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            openersCounter.incrementOpeners(player, getLevel(), getBlockPos(), getBlockState());
        }
    }

    @Override
    public void stopOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            openersCounter.decrementOpeners(player, getLevel(), getBlockPos(), getBlockState());
        }
    }

    @Override
    public float getOpenNess(float f) {
        return this.chestLidController.getOpenness(f);
    }

    private void playSound(Level level, BlockPos blockPos, BlockState blockState, SoundEvent soundEvent) {
        ChestType chestType = blockState.getValue(ChestBlock.TYPE);
        if (chestType == ChestType.LEFT) {
            return;
        }
        double d = (double)blockPos.getX() + 0.5;
        double e = (double)blockPos.getY() + 0.5;
        double f = (double)blockPos.getZ() + 0.5;
        if (chestType == ChestType.RIGHT) {
            Direction direction = ChestBlock.getConnectedDirection(blockState);
            d += (double)direction.getStepX() * 0.5;
            f += (double)direction.getStepZ() * 0.5;
        }
        level.playSound(null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5f, level.random.nextFloat() * 0.1f + 0.9f);
    }

    @Override
    public boolean triggerEvent(int i, int j) {
        if (i == 1) {
            this.chestLidController.shouldBeOpen(j > 0);
            return true;
        }
        return super.triggerEvent(i, j);
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public static int getOpenCount(BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        BlockEntity blockEntity;
        BlockState blockState = blockGetter.getBlockState(blockPos);
        if (blockState.hasBlockEntity() && (blockEntity = blockGetter.getBlockEntity(blockPos)) instanceof ChestEntity) {
            return ((ChestEntity)blockEntity).openersCounter.getOpenerCount();
        }
        return 0;
    }

    @Override
    protected void signalOpenCount(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, int i, int j) {
        super.signalOpenCount(level, blockPos, blockState, i, j);
        if (i != j && trapped) {
            Block block = blockState.getBlock();
            level.updateNeighborsAt(blockPos, block);
            level.updateNeighborsAt(blockPos.below(), block);
        }
    }

    @Override
    public int getContainerSize() {
        return 40;
    }
}
