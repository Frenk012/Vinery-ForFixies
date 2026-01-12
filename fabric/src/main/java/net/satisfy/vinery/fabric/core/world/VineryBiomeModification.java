package net.satisfy.vinery.fabric.core.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.satisfy.vinery.core.Vinery;
import net.satisfy.vinery.core.world.placed.VineryPlacedFeatures;

import java.util.function.Predicate;


public class VineryBiomeModification {

    public static void init() {
        // Natural world generation disabled - trees and grape bushes no longer spawn naturally
    }

    private static Predicate<BiomeSelectionContext> getVinerySelector(String path) {
        return BiomeSelectors.tag(TagKey.create(Registries.BIOME, Vinery.identifier(path)));
    }



}
