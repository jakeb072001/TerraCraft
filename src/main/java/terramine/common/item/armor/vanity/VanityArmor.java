package terramine.common.item.armor.vanity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import terramine.common.item.armor.TerrariaArmor;

import java.util.List;

public class VanityArmor extends TerrariaArmor {
    public VanityArmor(String armorType, Holder<ArmorMaterial> holder, Type type, Properties properties) {
        super(armorType, holder, type, properties);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
    }

    public String getCustomArmorLocation() {
        return "empty";
    }
}
