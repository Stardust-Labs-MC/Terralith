package net.stardustlabs.terralith.fabric.terrablender;

import net.stardustlabs.terralith.Terralith;
import net.stardustlabs.terralith.utils.TerrablenderUtil;
import terrablender.api.TerraBlenderApi;


public class TerrablenderInit implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        if(Terralith.MODE.equals(Terralith.Mode.TERRABLENDER)){
            TerrablenderUtil.registerRegions();
            TerrablenderUtil.readOverworldSurfaceRules();
        }
    }
}
