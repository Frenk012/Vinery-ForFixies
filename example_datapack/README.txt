Vinery Wine Effects Datapack Example
=====================================

This datapack shows how to customize wine effects in Vinery.

Directory Structure:
    data/vinery/vinery/wine_effects/<wine_name>.json

JSON Format:
{
    "effect": "minecraft:speed",      // Effect ID (required)
    "duration": 1600,                 // Duration in ticks (optional, default: 1600)
    "amplifier": 0,                   // Effect level 0-5 (optional, default: 0)
    "scale_with_age": true            // Scale with wine age (optional, default: true)
}

Available Wine Names:
- apple_cider
- apple_wine
- mead
- glowing_wine
- solaris_wine
- kelp_cider
- eiswein
- aegis_wine
- villagers_fright
- clark_wine
- jellie_wine
- noir_wine
- red_wine
- strad_wine
- cherry_wine
- cristel_wine
- lilitu_wine
- jo_special_mixture
- bolvar_wine
- magnetic_wine
- stal_wine
- chenet_wine
- bottle_mojang_noir
- chorus_wine
- creepers_crush
- mellohi_wine

Common Effect IDs:
- minecraft:speed
- minecraft:slowness
- minecraft:haste
- minecraft:mining_fatigue
- minecraft:strength
- minecraft:instant_health
- minecraft:instant_damage
- minecraft:jump_boost
- minecraft:nausea
- minecraft:regeneration
- minecraft:resistance
- minecraft:fire_resistance
- minecraft:water_breathing
- minecraft:invisibility
- minecraft:blindness
- minecraft:night_vision
- minecraft:hunger
- minecraft:weakness
- minecraft:poison
- minecraft:wither
- minecraft:health_boost
- minecraft:absorption
- minecraft:saturation
- minecraft:glowing
- minecraft:levitation
- minecraft:luck
- minecraft:unluck
- minecraft:slow_falling
- minecraft:conduit_power
- minecraft:dolphins_grace
- minecraft:bad_omen
- minecraft:hero_of_the_village

Vinery Custom Effects:
- vinery:water_walker
- vinery:frosty_armor
- vinery:armor_effect
- vinery:double_jump
- vinery:jellie
- vinery:party_effect
- vinery:climbing_effect
- vinery:lava_walker
- vinery:magnet
- vinery:health_effect
- vinery:experience_effect

Installation:
1. Copy this folder to your world's "datapacks" folder
2. Run /reload in-game or restart the server
3. Changes will apply immediately to all wine items
