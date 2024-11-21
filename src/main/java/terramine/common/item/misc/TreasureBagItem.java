package terramine.common.item.misc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import terramine.client.render.gui.menu.TreasureBagInventoryContainerMenu;
import terramine.common.item.TerrariaItemConfigurable;
import terramine.common.misc.TreasureBagInventory;

import java.util.List;

public class TreasureBagItem extends TerrariaItemConfigurable {
    public TreasureBagInventory treasureBagInventory;
    protected ResourceKey<LootTable> lootTable;
    private final Component title;

    public TreasureBagItem(Properties properties, ResourceKey<LootTable> lootTable, Component title) {
        super(properties);
        this.lootTable = lootTable;
        this.title = title;
    }

    @Override
    public UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        TreasureBagInventory treasureBagInventory = new TreasureBagInventory(itemStack, player, lootTable);
        this.treasureBagInventory = treasureBagInventory;
        player.startUsingItem(hand);

        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, itemStack);
        }

        if (!level.isClientSide) {
            TreasureBagInventoryContainerMenu menu = new TreasureBagInventoryContainerMenu(player, treasureBagInventory);
            player.openMenu(new SimpleMenuProvider((id, inventory, player2) -> menu, title));
        }

        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 1;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
    }
}
