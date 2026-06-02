package com.ddd.simplehidehud.mixin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class CreateWorldScreenMixinPlugin implements IMixinConfigPlugin {
    private static final String CREATE_WORLD_SCREEN_MIXIN = "com.ddd.simplehidehud.mixin.CreateWorldScreenMixin";
    private static final Path CLIENT_CONFIG = Path.of("config", "dddssimplehidehud-client.toml");

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!CREATE_WORLD_SCREEN_MIXIN.equals(mixinClassName)) {
            return true;
        }

        return isCreateScreenOverrideEnabled();
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean isCreateScreenOverrideEnabled() {
        if (!Files.exists(CLIENT_CONFIG)) {
            return true;
        }

        try {
            for (String line : Files.readAllLines(CLIENT_CONFIG)) {
                String normalized = line.split("#", 2)[0].trim();
                if (normalized.startsWith("createScreenOverride")) {
                    return !normalized.toLowerCase().contains("false");
                }
            }
        } catch (IOException ignored) {
            return true;
        }

        return true;
    }
}