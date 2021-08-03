package io.github.saksham4106.balanced_elytra;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {

    public static ForgeConfigSpec SERVER_CONFIG;

    public static ForgeConfigSpec.BooleanValue ELYTRAA_GO_DOWN;
    public static ForgeConfigSpec.BooleanValue WEAK_FIREWORKS;
    public static ForgeConfigSpec.IntValue DAMAGE_STRENGTH_RAIN;
    public static ForgeConfigSpec.IntValue DAMAGE_STRENGTH_THUNDER;
    public static ForgeConfigSpec.IntValue THUNDER_CHANCE;


    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        SERVER_BUILDER.comment("Elytra Settings").push("elytra");

        ELYTRAA_GO_DOWN = SERVER_BUILDER.comment("Should Elytra slowly go down during rain")
                .define("elytra_go_down", true);

        WEAK_FIREWORKS = SERVER_BUILDER.comment("Should fireworks decrease in strength during rain")
                .define("weak_fireworks", true);

        DAMAGE_STRENGTH_RAIN = SERVER_BUILDER.comment("Chance of rain damaging the elytra")
                .defineInRange("damage_strength_rain", 85, 1, 100);

        DAMAGE_STRENGTH_THUNDER = SERVER_BUILDER.comment("Chance of thunder damaging the elytra")
                .defineInRange("damage_strength_thunder", 93, 1, 100);

        THUNDER_CHANCE = SERVER_BUILDER.comment("Chance of thunder striking you while flying")
                .defineInRange("thunder_chance", 3000, 1, 10000);

        SERVER_CONFIG = SERVER_BUILDER.build();
    }
}
