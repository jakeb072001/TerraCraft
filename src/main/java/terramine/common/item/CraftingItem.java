package terramine.common.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import terramine.TerraMine;

import java.util.List;

public class CraftingItem extends TerrariaItemConfigurable {
    private final boolean hasTooltip;

    public CraftingItem(Properties properties, boolean hasTooltip) {
        super(properties);
        this.hasTooltip = hasTooltip;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
        if (TerraMine.CONFIG.client.showTooltips && hasTooltip) {
            appendTooltipDescription(tooltip, this.getDescriptionId() + ".tooltip");
        }
    }
}
