package terramine.common.item.accessories;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import terramine.common.init.ModItems;
import terramine.common.misc.AccessoriesHelper;
import terramine.common.utility.Utilities;

public class ShieldOfCthulhuItem extends ShieldAccessoryLikeItem {
    public ShieldOfCthulhuItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Entity entity, int i, boolean bl) {
        if (entity instanceof Player player && (!player.isCreative() && !player.isSpectator())) {
            if (AccessoriesHelper.isEquipped(ModItems.SHIELD_OF_CTHULHU, player) || (player.getMainHandItem() == itemStack || player.getOffhandItem() == itemStack)) {
                if (player.level().isClientSide) {
                    // todo: make work as an attack like in Terraria, dash into entities to damage them and knock them back
                    //Knockback 9 (Very strong), Defense 2, Critical chance 4%
                    // Players gain invulnerability from melee attacks when striking an enemy with the Shield of Cthulhu (they can still get hit if they miss the attack).
                    // The player's invulnerability timer resets after they hit something with the Shield, meaning an enemy can hit the player again right after they have dashed into something.
                    Utilities.playerDash(player, itemStack.getItem());
                }
            }
        }
    }
}
