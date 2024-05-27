package terramine.client.integrations;

// todo: uncomment once dynamic lights is updated, also uncomment in build.gradle
/**
import dev.lambdaurora.lambdynlights.api.DynamicLightHandlers;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import terramine.common.init.ModEntities;

public class DynamicLightPlugin implements DynamicLightsInitializer {

    @Override
    public void onInitializeDynamicLights() {
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
**/