package net.stardustlabs.terralith.utils;

import net.stardustlabs.terralith.Terralith;
import net.stardustlabs.terralith.config.ConfigUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Util {


	public static Terralith.Mode getMode(){
		ConfigUtil.createConfig();
		if(!Terralith.isTerrablenderLoaded()){
			Terralith.LOGGER.info("Overriding config for '{}': {} (Terrablender not installed)", ConfigUtil.MODE_NAME, "FALSE");
			return Terralith.Mode.DEFAULT;
		}
		boolean mode = ConfigUtil.readConfigMode();
		try {
			Terralith.LOGGER.info("Loading config for '{}': {}", ConfigUtil.MODE_NAME, Boolean.toString(mode).toUpperCase());
			if (mode) {
				return Terralith.Mode.TERRABLENDER;
			} else {
				return Terralith.Mode.DEFAULT;
			}
		}
		catch (IllegalArgumentException e){
			Terralith.LOGGER.warn("Invalid Mode '{}' for option '{}'", mode, "mode");
			return Terralith.Mode.TERRABLENDER;
		}
	}

	public static Terralith.Cursed getCursed(){
		ConfigUtil.createConfig();
		if(!Terralith.isTerrablenderLoaded()){
			Terralith.LOGGER.info("Overriding config for '{}': {} (Terrablender not installed)", ConfigUtil.CURSED_NAME, "NONE");
			return Terralith.Cursed.NONE;
		}
		String cursed = ConfigUtil.readConfigCursed();
		try {
			Terralith.LOGGER.info("Loading config for '{}': {}", ConfigUtil.CURSED_NAME, cursed.toUpperCase());
			if (cursed.equals("some")) {
				return Terralith.Cursed.SOME;
			} else if (cursed.equals("very")) {
				return Terralith.Cursed.VERY;
			} else {
				return Terralith.Cursed.NONE;
			}
		}
		catch (IllegalArgumentException e){
			Terralith.LOGGER.warn("Invalid Mode '{}' for option '{}'", cursed, "cursed");
			return Terralith.Cursed.NONE;
		}
	}

	public static void createDirectoriesSafe(Path path) throws IOException {
		Files.createDirectories(Files.exists(path) ? path.toRealPath() : path);
	}
}