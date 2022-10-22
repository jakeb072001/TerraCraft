package terramine.common.world;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import terramine.TerraMine;

public class FloatingIslandFeatureType extends JigsawFeature {
    public FloatingIslandFeatureType(Codec<JigsawConfiguration> codec) {
        super(codec, TerraMine.CONFIG.worldgen.structures.floatingIslandHeight, false, false, context -> true);
    }
}
