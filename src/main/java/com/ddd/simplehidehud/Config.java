package com.ddd.simplehidehud;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue HIDE_CAMERA_OVERLAYS = BUILDER
            .comment("Hide camera overlays such as pumpkin blur or powder snow overlay.")
            .define("hideCameraOverlays", false);
    private static final ModConfigSpec.BooleanValue HIDE_CROSSHAIR = BUILDER
            .comment("Hide the crosshair.")
            .define("hideCrosshair", false);
    private static final ModConfigSpec.BooleanValue HIDE_HOTBAR = BUILDER
            .comment("Hide the hotbar.")
            .define("hideHotbar", true);
    private static final ModConfigSpec.BooleanValue HIDE_JUMP_METER = BUILDER
            .comment("Hide the mount jump meter.")
            .define("hideJumpMeter", true);
    private static final ModConfigSpec.BooleanValue HIDE_EXPERIENCE_BAR = BUILDER
            .comment("Hide the experience progress bar.")
            .define("hideExperienceBar", true);
    private static final ModConfigSpec.BooleanValue HIDE_PLAYER_HEALTH = BUILDER
            .comment("Hide the player health hearts.")
            .define("hidePlayerHealth", true);
    private static final ModConfigSpec.BooleanValue HIDE_ARMOR_LEVEL = BUILDER
            .comment("Hide the armor bar.")
            .define("hideArmorLevel", true);
    private static final ModConfigSpec.BooleanValue HIDE_FOOD_LEVEL = BUILDER
            .comment("Hide the hunger bar.")
            .define("hideFoodLevel", true);
    private static final ModConfigSpec.BooleanValue HIDE_VEHICLE_HEALTH = BUILDER
            .comment("Hide vehicle health.")
            .define("hideVehicleHealth", true);
    private static final ModConfigSpec.BooleanValue HIDE_AIR_LEVEL = BUILDER
            .comment("Hide the air bubbles bar.")
            .define("hideAirLevel", true);
    private static final ModConfigSpec.BooleanValue HIDE_SELECTED_ITEM_NAME = BUILDER
            .comment("Hide the selected item name tooltip.")
            .define("hideSelectedItemName", true);
    private static final ModConfigSpec.BooleanValue HIDE_SPECTATOR_TOOLTIP = BUILDER
            .comment("Hide spectator tooltips.")
            .define("hideSpectatorTooltip", false);
    private static final ModConfigSpec.BooleanValue HIDE_EXPERIENCE_LEVEL = BUILDER
            .comment("Hide the numeric experience level.")
            .define("hideExperienceLevel", true);
    private static final ModConfigSpec.BooleanValue HIDE_EFFECTS = BUILDER
            .comment("Hide status effect icons.")
            .define("hideEffects", false);
    private static final ModConfigSpec.BooleanValue HIDE_BOSS_OVERLAY = BUILDER
            .comment("Hide boss bars.")
            .define("hideBossOverlay", false);
    private static final ModConfigSpec.BooleanValue HIDE_SLEEP_OVERLAY = BUILDER
            .comment("Hide the sleep fade overlay.")
            .define("hideSleepOverlay", false);
    private static final ModConfigSpec.BooleanValue HIDE_DEMO_OVERLAY = BUILDER
            .comment("Hide the demo overlay.")
            .define("hideDemoOverlay", false);
    private static final ModConfigSpec.BooleanValue HIDE_DEBUG_OVERLAY = BUILDER
            .comment("Hide the debug overlay.")
            .define("hideDebugOverlay", false);
    private static final ModConfigSpec.BooleanValue HIDE_SCOREBOARD_SIDEBAR = BUILDER
            .comment("Hide the scoreboard sidebar.")
            .define("hideScoreboardSidebar", false);
    private static final ModConfigSpec.BooleanValue HIDE_OVERLAY_MESSAGE = BUILDER
            .comment("Hide overlay messages such as action bar text.")
            .define("hideOverlayMessage", false);
    private static final ModConfigSpec.BooleanValue HIDE_TITLE = BUILDER
            .comment("Hide title and subtitle screen messages.")
            .define("hideTitle", false);
    private static final ModConfigSpec.BooleanValue HIDE_CHAT = BUILDER
            .comment("Hide the chat HUD.")
            .define("hideChat", false);
    private static final ModConfigSpec.BooleanValue HIDE_TAB_LIST = BUILDER
            .comment("Hide the player tab list.")
            .define("hideTabList", false);
    private static final ModConfigSpec.BooleanValue HIDE_SUBTITLE_OVERLAY = BUILDER
            .comment("Hide sound subtitles.")
            .define("hideSubtitleOverlay", false);
    private static final ModConfigSpec.BooleanValue HIDE_SAVING_INDICATOR = BUILDER
            .comment("Hide the saving indicator.")
            .define("hideSavingIndicator", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean hideCameraOverlays;
    public static boolean hideCrosshair;
    public static boolean hideHotbar;
    public static boolean hideJumpMeter;
    public static boolean hideExperienceBar;
    public static boolean hidePlayerHealth;
    public static boolean hideArmorLevel;
    public static boolean hideFoodLevel;
    public static boolean hideVehicleHealth;
    public static boolean hideAirLevel;
    public static boolean hideSelectedItemName;
    public static boolean hideSpectatorTooltip;
    public static boolean hideExperienceLevel;
    public static boolean hideEffects;
    public static boolean hideBossOverlay;
    public static boolean hideSleepOverlay;
    public static boolean hideDemoOverlay;
    public static boolean hideDebugOverlay;
    public static boolean hideScoreboardSidebar;
    public static boolean hideOverlayMessage;
    public static boolean hideTitle;
    public static boolean hideChat;
    public static boolean hideTabList;
    public static boolean hideSubtitleOverlay;
    public static boolean hideSavingIndicator;

    private Config() {}

    static void onLoad(ModConfigEvent event) {
        hideCameraOverlays = HIDE_CAMERA_OVERLAYS.get();
        hideCrosshair = HIDE_CROSSHAIR.get();
        hideHotbar = HIDE_HOTBAR.get();
        hideJumpMeter = HIDE_JUMP_METER.get();
        hideExperienceBar = HIDE_EXPERIENCE_BAR.get();
        hidePlayerHealth = HIDE_PLAYER_HEALTH.get();
        hideArmorLevel = HIDE_ARMOR_LEVEL.get();
        hideFoodLevel = HIDE_FOOD_LEVEL.get();
        hideVehicleHealth = HIDE_VEHICLE_HEALTH.get();
        hideAirLevel = HIDE_AIR_LEVEL.get();
        hideSelectedItemName = HIDE_SELECTED_ITEM_NAME.get();
        hideSpectatorTooltip = HIDE_SPECTATOR_TOOLTIP.get();
        hideExperienceLevel = HIDE_EXPERIENCE_LEVEL.get();
        hideEffects = HIDE_EFFECTS.get();
        hideBossOverlay = HIDE_BOSS_OVERLAY.get();
        hideSleepOverlay = HIDE_SLEEP_OVERLAY.get();
        hideDemoOverlay = HIDE_DEMO_OVERLAY.get();
        hideDebugOverlay = HIDE_DEBUG_OVERLAY.get();
        hideScoreboardSidebar = HIDE_SCOREBOARD_SIDEBAR.get();
        hideOverlayMessage = HIDE_OVERLAY_MESSAGE.get();
        hideTitle = HIDE_TITLE.get();
        hideChat = HIDE_CHAT.get();
        hideTabList = HIDE_TAB_LIST.get();
        hideSubtitleOverlay = HIDE_SUBTITLE_OVERLAY.get();
        hideSavingIndicator = HIDE_SAVING_INDICATOR.get();
    }
}
