package com.ddd.simplehidehud;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = dddssimplehidehud.MODID, dist = Dist.CLIENT)
public class dddssimplehidehudClient {
    public dddssimplehidehudClient(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        modEventBus.addListener(dddssimplehidehudClient::onClientSetup);
        NeoForge.EVENT_BUS.addListener(dddssimplehidehudClient::onRenderGuiLayer);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        dddssimplehidehud.LOGGER.debug("register hud");
    }

    private static void onRenderGuiLayer(RenderGuiLayerEvent.Pre event) {
        if (shouldHide(event.getName())) {
            event.setCanceled(true);
        }
    }

    private static boolean shouldHide(ResourceLocation layer) {
        if (layer.equals(VanillaGuiLayers.CAMERA_OVERLAYS)) return Config.hideCameraOverlays;
        if (layer.equals(VanillaGuiLayers.CROSSHAIR)) return Config.hideCrosshair;
        if (layer.equals(VanillaGuiLayers.HOTBAR)) return Config.hideHotbar;
        if (layer.equals(VanillaGuiLayers.JUMP_METER)) return Config.hideJumpMeter;
        if (layer.equals(VanillaGuiLayers.EXPERIENCE_BAR)) return Config.hideExperienceBar;
        if (layer.equals(VanillaGuiLayers.PLAYER_HEALTH)) return Config.hidePlayerHealth;
        if (layer.equals(VanillaGuiLayers.ARMOR_LEVEL)) return Config.hideArmorLevel;
        if (layer.equals(VanillaGuiLayers.FOOD_LEVEL)) return Config.hideFoodLevel;
        if (layer.equals(VanillaGuiLayers.VEHICLE_HEALTH)) return Config.hideVehicleHealth;
        if (layer.equals(VanillaGuiLayers.AIR_LEVEL)) return Config.hideAirLevel;
        if (layer.equals(VanillaGuiLayers.SELECTED_ITEM_NAME)) return Config.hideSelectedItemName;
        if (layer.equals(VanillaGuiLayers.SPECTATOR_TOOLTIP)) return Config.hideSpectatorTooltip;
        if (layer.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) return Config.hideExperienceLevel;
        if (layer.equals(VanillaGuiLayers.EFFECTS)) return Config.hideEffects;
        if (layer.equals(VanillaGuiLayers.BOSS_OVERLAY)) return Config.hideBossOverlay;
        if (layer.equals(VanillaGuiLayers.SLEEP_OVERLAY)) return Config.hideSleepOverlay;
        if (layer.equals(VanillaGuiLayers.DEMO_OVERLAY)) return Config.hideDemoOverlay;
        if (layer.equals(VanillaGuiLayers.DEBUG_OVERLAY)) return Config.hideDebugOverlay;
        if (layer.equals(VanillaGuiLayers.SCOREBOARD_SIDEBAR)) return Config.hideScoreboardSidebar;
        if (layer.equals(VanillaGuiLayers.OVERLAY_MESSAGE)) return Config.hideOverlayMessage;
        if (layer.equals(VanillaGuiLayers.TITLE)) return Config.hideTitle;
        if (layer.equals(VanillaGuiLayers.CHAT)) return Config.hideChat;
        if (layer.equals(VanillaGuiLayers.TAB_LIST)) return Config.hideTabList;
        if (layer.equals(VanillaGuiLayers.SUBTITLE_OVERLAY)) return Config.hideSubtitleOverlay;
        if (layer.equals(VanillaGuiLayers.SAVING_INDICATOR)) return Config.hideSavingIndicator;
        return false;
    }
}
