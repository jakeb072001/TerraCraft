package terramine.client.integrations;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import terramine.common.item.TerrariaItem;
import terramine.common.item.armor.TerrariaArmor;
import terramine.common.item.armor.vanity.VanityArmor;
import terramine.common.item.dye.BasicDye;

public class REIPlugin implements REIClientPlugin {

	@Override
	public void registerDisplays(DisplayRegistry recipeHelper) {
		BuiltInRegistries.ITEM.stream()
				.filter(item -> (item instanceof TerrariaItem && !(item instanceof BasicDye)))
				.map(item -> {
					DefaultInformationDisplay display = DefaultInformationDisplay.createFromEntry(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(item)), Component.translatable(item.getDescriptionId()));
					for (String string : ((TerrariaItem) item).getREITooltip()) {
						display.line(Component.literal(string));
					}
					return display;
				}).forEach(recipeHelper::add);

		BuiltInRegistries.ITEM.stream()
				.filter(item -> item instanceof TerrariaArmor && !(item instanceof VanityArmor))
				.map(item -> {
					DefaultInformationDisplay display = DefaultInformationDisplay.createFromEntry(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(item)), Component.translatable(item.getDescriptionId()));
					for (String string : ((TerrariaArmor) item).getREITooltip()) {
						display.line(Component.literal(string));
					}
					if (((TerrariaArmor) item).getREISetBonusTooltip() != null) {
						for (String string : ((TerrariaArmor) item).getREISetBonusTooltip()) {
							display.line(Component.literal(string));
						}
					}
					return display;
				}).forEach(recipeHelper::add);
	}
}
