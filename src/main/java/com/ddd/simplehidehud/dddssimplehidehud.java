package com.ddd.simplehidehud;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(dddssimplehidehud.MODID)
public class dddssimplehidehud {
    public static final String MODID = "dddssimplehidehud";
    public static final Logger LOGGER = LogUtils.getLogger();

    public dddssimplehidehud(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        modEventBus.addListener(Config::onLoad);
        LOGGER.debug("Loaded {}", MODID);
    }
}
