package net.stardustlabs.terralith;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class TerralithExpectPlatform {

    @ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoadedWithVersion(String modid, String minVersion){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modid){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Platform getPlatform(){
        throw new AssertionError();
    }

    public enum Platform {
        FORGE,
        FABRIC
    }

}
