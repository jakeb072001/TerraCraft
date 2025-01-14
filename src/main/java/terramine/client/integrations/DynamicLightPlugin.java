package terramine.client.integrations;

import dev.lambdaurora.lambdynlights.api.DynamicLightHandlers;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSourceManager;
import terramine.common.init.ModEntities;

public class DynamicLightPlugin implements DynamicLightsInitializer {

    // todo: find a way to make accessories emit light when equipped
    @Override
    public void onInitializeDynamicLights(ItemLightSourceManager itemLightSourceManager) {
        //DynamicLightHandlers.registerDynamicLightHandler(ModEntities.ENTITY, DynamicLightHandler.makeHandler(entity -> 10, entity -> false));
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.FALLING_STAR, star -> 10);
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.METEORITE, meteorite -> 10);
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.MAGIC_MISSILE, missile -> 8);
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.FLAMELASH_MISSILE, missile -> 10);
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.RAINBOW_MISSILE, missile -> 12);
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.LASER, laser -> 5);
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.FLAMING_ARROW, arrow -> 7);
        DynamicLightHandlers.registerDynamicLightHandler(ModEntities.JESTER_ARROW, arrow -> 6);
    }
}