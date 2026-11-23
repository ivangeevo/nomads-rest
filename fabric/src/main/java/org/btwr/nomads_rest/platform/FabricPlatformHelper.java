package org.btwr.nomads_rest.platform;

import net.fabricmc.loader.api.FabricLoader;
import org.btwr.nomads_rest.platform.services.IPlatformHelper;

import java.nio.file.Path;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override public String getPlatformName() {
        return "Fabric";
    }
    @Override public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
    @Override public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
    @Override public Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}