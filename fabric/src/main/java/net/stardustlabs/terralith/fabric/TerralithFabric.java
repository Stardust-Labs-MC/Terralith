package net.stardustlabs.terralith.fabric;

import net.fabricmc.api.ModInitializer;
import net.stardustlabs.terralith.Terralith;
import net.stardustlabs.terralith.utils.TerrablenderUtil;

public class TerralithFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        if (Terralith.isTerrablenderLoaded()) {
            Terralith.LOGGER.info("[terralith] Terrablender detected");
            if (Terralith.MODE.equals(Terralith.Mode.TERRABLENDER)) {
                Terralith.LOGGER.info("[terralith] Terrablender compatibility enabled");
                TerrablenderUtil.registerRegions();
                TerrablenderUtil.readOverworldSurfaceRules();
            } else if (Terralith.isBiomeModLoaded()) {
                Terralith.LOGGER.info("[terralith] Terrablender compatibility disabled, but BYG detected: compatibility is forced");
                TerrablenderUtil.registerRegions();
                TerrablenderUtil.readOverworldSurfaceRules();
            } else {
                Terralith.LOGGER.info("[terralith] Terrablender compatibility disabled");
                Terralith.init();
            }
        } else {
            Terralith.LOGGER.info("[terralith] Terrablender not detected");
            Terralith.init();
        }
    }



}
