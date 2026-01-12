package net.satisfy.vinery.core.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.effect.MobEffect;
import net.satisfy.vinery.core.Vinery;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WineEffectManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String DIRECTORY = "vinery/wine_effects";

    private static final Map<ResourceLocation, WineEffectData> WINE_EFFECTS = new HashMap<>();
    private static final Map<ResourceLocation, WineEffectData> DEFAULT_EFFECTS = new HashMap<>();

    public WineEffectManager() {
        super(GSON, DIRECTORY);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profiler) {
        WINE_EFFECTS.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : map.entrySet()) {
            ResourceLocation id = entry.getKey();
            try {
                JsonObject json = entry.getValue().getAsJsonObject();
                WineEffectData data = WineEffectData.fromJson(id, json);
                WINE_EFFECTS.put(id, data);
                Vinery.LOGGER.debug("Loaded wine effect override for: {}", id);
            } catch (Exception e) {
                Vinery.LOGGER.error("Failed to load wine effect {}: {}", id, e.getMessage());
            }
        }

        Vinery.LOGGER.info("Loaded {} wine effect overrides from datapacks", WINE_EFFECTS.size());

        // Re-apply effects to wine items after reload
        net.satisfy.vinery.core.util.WineEffectSetup.applyAllEffects();
    }

    public static void registerDefault(ResourceLocation wineId, ResourceLocation effectId, int duration, int amplifier) {
        DEFAULT_EFFECTS.put(wineId, new WineEffectData(wineId, effectId, duration, amplifier, true));
    }

    public static Optional<WineEffectData> getEffect(ResourceLocation wineId) {
        // Datapack overrides take priority
        if (WINE_EFFECTS.containsKey(wineId)) {
            return Optional.of(WINE_EFFECTS.get(wineId));
        }
        // Fall back to defaults
        if (DEFAULT_EFFECTS.containsKey(wineId)) {
            return Optional.of(DEFAULT_EFFECTS.get(wineId));
        }
        return Optional.empty();
    }

    public static Optional<Holder<MobEffect>> getEffectHolder(ResourceLocation effectId) {
        MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(effectId);
        if (effect != null) {
            return Optional.of(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect));
        }
        return Optional.empty();
    }

    public static boolean hasOverride(ResourceLocation wineId) {
        return WINE_EFFECTS.containsKey(wineId);
    }
}
