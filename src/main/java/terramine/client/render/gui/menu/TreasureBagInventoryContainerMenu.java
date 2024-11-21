package terramine.client.render.gui.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModScreenHandlerType;
import terramine.common.misc.TreasureBagInventory;

public class TreasureBagInventoryContainerMenu extends AbstractContainerMenu {
    TreasureBagInventory treasureBagInventory;

    public TreasureBagInventoryContainerMenu(final Player player) {
        super(ModScreenHandlerType.TREASURE_BAG_CONTAINER, 11);
        addSlots(player, new TreasureBagInventory(player.getMainHandItem(), player, null));
    }

    public TreasureBagInventoryContainerMenu(final Player player, final TreasureBagInventory container) {
        super(ModScreenHandlerType.TREASURE_BAG_CONTAINER, 11);
        addSlots(player, container);
    }

    private void addSlots(Player player, TreasureBagInventory container) {
        Inventory inventory = player.getInventory();
        treasureBagInventory = container;

        int i;
        int j;
        for (i = 0; i < 5; ++i) {
            this.addSlot(new Slot(inventory, i, -1000, -1000));
        }

        // Inventory
        for(i = 0; i < 3; ++i) {
            for(j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + (i + 1) * 9, 8 + j * 18, 67 + i * 18));
            }
        }

        // Hotbar
        for(i = 0; i < 9; ++i) {
            int finalI = i;
            this.addSlot(new Slot(inventory, finalI, 8 + i * 18, 125) {
                public boolean mayPickup(Player player) {
                    return this.getItem() != treasureBagInventory.getContainerItem();
                }
            });
        }

        if (container == null) { return; }
        // Bag
        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(container, i, 8 + i * 18, 30) {
                public boolean mayPlace(@NotNull ItemStack itemStack) {
                    return false;
                }
            });
        }
    }

    public void removed(@NotNull Player player) {
        treasureBagInventory.stopOpen(player);
        super.removed(player);
    }

    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            if (i >= 1 && i < 5) {
                if (!this.moveItemStackTo(itemStack2, 9, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i >= 5 && i < 9) {
                if (!this.moveItemStackTo(itemStack2, 9, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i >= 9 && i < 36) {
                if (!this.moveItemStackTo(itemStack2, 36, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i >= 36 && i < 41) {
                if (!this.moveItemStackTo(itemStack2, 9, 36, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 9, 41, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack2);
            if (i == 0) {
                player.drop(itemStack2, false);
            }
        }

        return itemStack;
    }

    public int getSize() {
        return 15; // what do this do?
    }
}
