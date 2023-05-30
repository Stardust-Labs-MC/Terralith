package net.stardustlabs.terralith.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.stardustlabs.terralith.TerralithExpectPlatform;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.nio.file.Path;

public class TerralithExpectPlatformImpl {

    public static boolean isModLoaded(String modid) {
        ModList modList = ModList.get();
        if(modList != null){
            return modList.isLoaded(modid);
        }
        return isModPreLoaded(modid);
    }

    public static boolean isModPreLoaded(String modid) {
        for(ModInfo info : LoadingModList.get().getMods()){
            if(info.getModId().equals(modid)) return true;
        }
        return false;
    }

    public static ArtifactVersion getPreLoadedModVersion(String modid) {
        for(ModInfo info : LoadingModList.get().getMods()){
            if(info.getModId().equals(modid)) {
                return info.getVersion();
            }
        }
        throw new RuntimeException("Couldn't find mod");
    }

    public static boolean isModLoadedWithVersion(String modid, String minVersion) {
        if(isModLoaded(modid)){
            ModList modList = ModList.get();
            ArtifactVersion version;
            if(modList != null) version = modList.getModContainerById(modid).get().getModInfo().getVersion();
            else version = getPreLoadedModVersion(modid);

            ArtifactVersion min;
            min = new DefaultArtifactVersion(minVersion);
            return version.compareTo(min) >= 0;
        }
        return false;
    }

    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static TerralithExpectPlatform.Platform getPlatform() {
        return TerralithExpectPlatform.Platform.FORGE;
    }
}
