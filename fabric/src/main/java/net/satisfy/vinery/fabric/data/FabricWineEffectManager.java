package net.satisfy.vinery.fabric.data;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.vinery.core.Vinery;
import net.satisfy.vinery.core.data.WineEffectManager;

public class FabricWineEffectManager extends WineEffectManager implements IdentifiableResourceReloadListener {
    private static final ResourceLocation ID = Vinery.identifier("wine_effects");

    @Override
    public ResourceLocation getFabricId() {
        return ID;
    }
}
