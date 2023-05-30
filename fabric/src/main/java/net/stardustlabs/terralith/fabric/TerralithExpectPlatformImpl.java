package net.stardustlabs.terralith.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.stardustlabs.terralith.Terralith;
import net.stardustlabs.terralith.TerralithExpectPlatform;

import java.nio.file.Path;

public class TerralithExpectPlatformImpl {

    public static boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    public static boolean isModLoadedWithVersion(String modid, String minVersion) {
        if(isModLoaded(modid)){
            Version version = FabricLoader.getInstance().getModContainer(modid).get().getMetadata().getVersion();
            Version min;
            try {
                min = Version.parse(minVersion);
            } catch (VersionParsingException e) {
                Terralith.LOGGER.error("Couldn't parse version: " + minVersion);
                return false;
            }
            return version.compareTo(min) >= 0;
        }
        return false;
    }

    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static TerralithExpectPlatform.Platform getPlatform() {
        return TerralithExpectPlatform.Platform.FABRIC;
    }
}
