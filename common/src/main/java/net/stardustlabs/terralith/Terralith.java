package net.stardustlabs.terralith;

import net.cristellib.builtinpacks.BuiltInDataPacks;
import net.stardustlabs.terralith.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terralith {
    public static final String MOD_ID = "terralith";

    public static final String HIGHEST_MOD_ID = TerralithExpectPlatform.getPlatform().equals(TerralithExpectPlatform.Platform.FORGE) ? MOD_ID : MOD_ID + "_loader";

    public static final Logger LOGGER = LogManager.getLogger("terralith");

    public static Mode MODE = Util.getMode();
    public static Cursed CURSED = Util.getCursed();
    public static final String minTerraBlenderVersion = "1.2.0.126";

    public static void init() {
        BuiltInDataPacks.registerPack("Terralith", "resources/terralith_default", HIGHEST_MOD_ID, () -> true);
        LOGGER.info("Datapack registered with CristelLib");
    }

    public enum Mode {
        TERRABLENDER,
        DEFAULT
    }

    public enum Cursed {
        NONE,
        SOME,
        VERY
    }

    public static boolean isTerrablenderLoaded(){
        return TerralithExpectPlatform.isModLoadedWithVersion("terrablender", minTerraBlenderVersion);
    }

    public static boolean isBiomeModLoaded(){
        if (TerralithExpectPlatform.isModLoaded("byg")) return true;
        return TerralithExpectPlatform.isModLoaded("biomesoplenty");
    }
}
