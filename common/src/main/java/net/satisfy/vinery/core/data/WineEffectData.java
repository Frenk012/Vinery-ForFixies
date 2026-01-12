package net.satisfy.vinery.core.data;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

public record WineEffectData(
        ResourceLocation wineId,
        ResourceLocation effectId,
        int baseDuration,
        int baseAmplifier,
        boolean scaleDurationWithAge
) {
    public static WineEffectData fromJson(ResourceLocation wineId, JsonObject json) {
        ResourceLocation effectId = ResourceLocation.parse(json.get("effect").getAsString());
        int baseDuration = json.has("duration") ? json.get("duration").getAsInt() : 1600;
        int baseAmplifier = json.has("amplifier") ? json.get("amplifier").getAsInt() : 0;
        boolean scaleDurationWithAge = !json.has("scale_with_age") || json.get("scale_with_age").getAsBoolean();

        return new WineEffectData(wineId, effectId, baseDuration, baseAmplifier, scaleDurationWithAge);
    }
}
