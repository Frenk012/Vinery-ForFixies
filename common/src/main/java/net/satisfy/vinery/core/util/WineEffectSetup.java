package net.satisfy.vinery.core.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.satisfy.vinery.core.Vinery;
import net.satisfy.vinery.core.data.WineEffectData;
import net.satisfy.vinery.core.data.WineEffectManager;
import net.satisfy.vinery.core.item.DrinkBlockItem;
import net.satisfy.vinery.core.registry.MobEffectRegistry;
import net.satisfy.vinery.core.registry.ObjectRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class WineEffectSetup {
    // Map wine item IDs to their DrinkBlockItem instances
    private static final Map<ResourceLocation, Supplier<DrinkBlockItem>> WINE_ITEMS = new HashMap<>();

    public static void setupWineEffects() {
        // Register wine items with their IDs
        registerWineItem("apple_cider", () -> (DrinkBlockItem) ObjectRegistry.APPLE_CIDER_ITEM.get());
        registerWineItem("apple_wine", () -> (DrinkBlockItem) ObjectRegistry.APPLE_WINE_ITEM.get());
        registerWineItem("mead", () -> (DrinkBlockItem) ObjectRegistry.MEAD_ITEM.get());
        registerWineItem("glowing_wine", () -> (DrinkBlockItem) ObjectRegistry.GLOWING_WINE_ITEM.get());
        registerWineItem("solaris_wine", () -> (DrinkBlockItem) ObjectRegistry.SOLARIS_WINE_ITEM.get());
        registerWineItem("kelp_cider", () -> (DrinkBlockItem) ObjectRegistry.KELP_CIDER_ITEM.get());
        registerWineItem("eiswein", () -> (DrinkBlockItem) ObjectRegistry.EISWEIN_ITEM.get());
        registerWineItem("aegis_wine", () -> (DrinkBlockItem) ObjectRegistry.AEGIS_WINE_ITEM.get());
        registerWineItem("villagers_fright", () -> (DrinkBlockItem) ObjectRegistry.VILLAGERS_FRIGHT_ITEM.get());
        registerWineItem("clark_wine", () -> (DrinkBlockItem) ObjectRegistry.CLARK_WINE_ITEM.get());
        registerWineItem("jellie_wine", () -> (DrinkBlockItem) ObjectRegistry.JELLIE_WINE_ITEM.get());
        registerWineItem("noir_wine", () -> (DrinkBlockItem) ObjectRegistry.NOIR_WINE_ITEM.get());
        registerWineItem("red_wine", () -> (DrinkBlockItem) ObjectRegistry.RED_WINE_ITEM.get());
        registerWineItem("strad_wine", () -> (DrinkBlockItem) ObjectRegistry.STRAD_WINE_ITEM.get());
        registerWineItem("cherry_wine", () -> (DrinkBlockItem) ObjectRegistry.CHERRY_WINE_ITEM.get());
        registerWineItem("cristel_wine", () -> (DrinkBlockItem) ObjectRegistry.CRISTEL_WINE_ITEM.get());
        registerWineItem("lilitu_wine", () -> (DrinkBlockItem) ObjectRegistry.LILITU_WINE_ITEM.get());
        registerWineItem("jo_special_mixture", () -> (DrinkBlockItem) ObjectRegistry.JO_SPECIAL_MIXTURE_ITEM.get());
        registerWineItem("bolvar_wine", () -> (DrinkBlockItem) ObjectRegistry.BOLVAR_WINE_ITEM.get());
        registerWineItem("magnetic_wine", () -> (DrinkBlockItem) ObjectRegistry.MAGNETIC_WINE_ITEM.get());
        registerWineItem("stal_wine", () -> (DrinkBlockItem) ObjectRegistry.STAL_WINE_ITEM.get());
        registerWineItem("chenet_wine", () -> (DrinkBlockItem) ObjectRegistry.CHENET_WINE_ITEM.get());
        registerWineItem("bottle_mojang_noir", () -> (DrinkBlockItem) ObjectRegistry.BOTTLE_MOJANG_NOIR_ITEM.get());
        registerWineItem("chorus_wine", () -> (DrinkBlockItem) ObjectRegistry.CHORUS_WINE_ITEM.get());
        registerWineItem("creepers_crush", () -> (DrinkBlockItem) ObjectRegistry.CREEPERS_CRUSH_ITEM.get());
        registerWineItem("mellohi_wine", () -> (DrinkBlockItem) ObjectRegistry.MELLOHI_WINE_ITEM.get());

        // Register default effects
        registerDefaultEffect("apple_cider", "minecraft:strength", 1600, 0);
        registerDefaultEffect("apple_wine", "minecraft:resistance", 1600, 0);
        registerDefaultEffect("mead", "minecraft:haste", 1600, 0);
        registerDefaultEffect("glowing_wine", "minecraft:glowing", 1600, 0);
        registerDefaultEffect("solaris_wine", "minecraft:health_boost", 1600, 0);
        registerDefaultEffect("kelp_cider", "vinery:water_walker", 1600, 0);
        registerDefaultEffect("eiswein", "vinery:frosty_armor", 1600, 0);
        registerDefaultEffect("aegis_wine", "vinery:armor_effect", 1600, 0);
        registerDefaultEffect("villagers_fright", "minecraft:bad_omen", 1600, 0);
        registerDefaultEffect("clark_wine", "vinery:double_jump", 1600, 0);
        registerDefaultEffect("jellie_wine", "vinery:jellie", 1600, 0);
        registerDefaultEffect("noir_wine", "minecraft:jump_boost", 1600, 0);
        registerDefaultEffect("red_wine", "minecraft:slow_falling", 1600, 0);
        registerDefaultEffect("strad_wine", "minecraft:night_vision", 1600, 0);
        registerDefaultEffect("cherry_wine", "minecraft:invisibility", 1600, 0);
        registerDefaultEffect("cristel_wine", "minecraft:water_breathing", 1600, 0);
        registerDefaultEffect("lilitu_wine", "vinery:party_effect", 1600, 0);
        registerDefaultEffect("jo_special_mixture", "vinery:climbing_effect", 1600, 0);
        registerDefaultEffect("bolvar_wine", "vinery:lava_walker", 1600, 0);
        registerDefaultEffect("magnetic_wine", "vinery:magnet", 1600, 0);
        registerDefaultEffect("stal_wine", "vinery:health_effect", 1600, 0);
        registerDefaultEffect("chenet_wine", "vinery:climbing_effect", 1600, 0);
        registerDefaultEffect("bottle_mojang_noir", "vinery:experience_effect", 1600, 0);
        // chorus_wine: no effect (was removed)
        registerDefaultEffect("creepers_crush", "minecraft:resistance", 1600, 0);
        registerDefaultEffect("mellohi_wine", "minecraft:regeneration", 1600, 0);

        // Apply default effects
        applyAllEffects();
    }

    private static void registerWineItem(String name, Supplier<DrinkBlockItem> itemSupplier) {
        WINE_ITEMS.put(Vinery.identifier(name), itemSupplier);
    }

    private static void registerDefaultEffect(String wineName, String effectId, int duration, int amplifier) {
        ResourceLocation wineId = Vinery.identifier(wineName);
        ResourceLocation effect = ResourceLocation.parse(effectId);
        WineEffectManager.registerDefault(wineId, effect, duration, amplifier);
    }

    /**
     * Apply effects from WineEffectManager to all wine items.
     * This is called at startup and can be called again after datapack reload.
     */
    public static void applyAllEffects() {
        for (Map.Entry<ResourceLocation, Supplier<DrinkBlockItem>> entry : WINE_ITEMS.entrySet()) {
            ResourceLocation wineId = entry.getKey();
            DrinkBlockItem item = entry.getValue().get();

            Optional<WineEffectData> effectData = WineEffectManager.getEffect(wineId);
            if (effectData.isPresent()) {
                WineEffectData data = effectData.get();
                Optional<Holder<MobEffect>> effectHolder = WineEffectManager.getEffectHolder(data.effectId());

                if (effectHolder.isPresent()) {
                    item.setEffectSupplier(effectHolder::get, data.baseDuration(), data.baseAmplifier());
                    Vinery.LOGGER.debug("Applied effect {} to wine {}", data.effectId(), wineId);
                } else {
                    Vinery.LOGGER.warn("Effect {} not found for wine {}", data.effectId(), wineId);
                }
            }
            // If no effect data, the wine has no effect (like chorus_wine)
        }
    }

    /**
     * Get all registered wine item IDs for documentation/datapack generation.
     */
    public static Iterable<ResourceLocation> getWineIds() {
        return WINE_ITEMS.keySet();
    }
}
