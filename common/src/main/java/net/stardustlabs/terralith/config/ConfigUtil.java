package net.stardustlabs.terralith.config;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import net.stardustlabs.terralith.Terralith;
import net.stardustlabs.terralith.TerralithExpectPlatform;
import net.stardustlabs.terralith.utils.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigUtil {

    public static final Path TERRALITH_CONFIG_DIR = TerralithExpectPlatform.getConfigDirectory().resolve(Terralith.MOD_ID);

    public static final Path FILE_PATH = TERRALITH_CONFIG_DIR.resolve("config.json");
    public static final Path README_PATH = TERRALITH_CONFIG_DIR.resolve("README.txt");
    public static final String MODE_NAME = "terrablender-compatibility";
    public static final String CURSED_NAME = "cursed-skylands";

    public static void createConfig(){
        try {
            Util.createDirectoriesSafe(TERRALITH_CONFIG_DIR);
        } catch (IOException e) {
            Terralith.LOGGER.error("Couldn't create directories", e);
        }

        if (!README_PATH.toFile().exists()) createReadme();
        if (FILE_PATH.toFile().exists()) return;

        JsonObject file = new JsonObject();
        file.addProperty(MODE_NAME, true);
        file.addProperty(CURSED_NAME, "none");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter(FILE_PATH.toFile()); JsonWriter jsonWriter = gson.newJsonWriter(fileWriter)) {
            jsonWriter.jsonValue(gson.toJson(file));
        } catch (IOException ex) {
            Terralith.LOGGER.error("Couldn't create config file", ex);
        }
    }

    public static void createReadme() {
        try (FileWriter fileWriter = new FileWriter(README_PATH.toFile())) {
            fileWriter.write("--- Terralith Config File Description ---" + System.getProperty("line.separator"));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write("terrablender-compatibility: true by default" + System.getProperty("line.separator"));
            fileWriter.write("   This option determines whether Terralith should create a Terrablender region." + System.getProperty("line.separator"));
            fileWriter.write("   Enabling will allow for better biome rarity and compatibility with other mods." + System.getProperty("line.separator"));
            fileWriter.write("   Disabling will allow Terralith to function as it would a datapack." + System.getProperty("line.separator"));
            fileWriter.write("   Note: If compatibility is disabled, but BYG or BoP are installed, compatibility will be forced." + System.getProperty("line.separator"));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write("cursed-skylands: \"none\" by default" + System.getProperty("line.separator"));
            fileWriter.write("   This option determines whether Skylands will be cursed - ONLY takes effect when Terrablender compatibility is enabled!" + System.getProperty("line.separator"));
            fileWriter.write("   The options consist of \"none\", \"some\", and \"very\". DO NOT use \"some\" or \"very\" in a real world, unless you know what you're doing!" + System.getProperty("line.separator"));
            fileWriter.write("   None - Skylands generate as normally as they can" + System.getProperty("line.separator"));
            fileWriter.write("   Some - Skylands can generate on top of each other, be very large, and sometimes \"cancel\" out ocean-based biomes such as Mirage Isles." + System.getProperty("line.separator"));
            fileWriter.write("   Very - Skylands can generate anywhere and everywhere. This includes on each other, above land, and *in* land!" + System.getProperty("line.separator"));
            fileWriter.write("   It should be noted that if Terrablender compatibility is disabled, the cursed generation will not occur, no matter what." + System.getProperty("line.separator"));
            fileWriter.write("      *Cursed option provided in loving memory of catter's pain and suffering*");
        } catch (IOException ex) {
            Terralith.LOGGER.error("Couldn't create README file", ex);
        }
    }

    public static boolean readConfigMode(){
        try (InputStream in = Files.newInputStream(FILE_PATH);
             InputStreamReader reader = new InputStreamReader(in)) {
            if(JsonParser.parseReader(reader) instanceof JsonObject o){
                JsonElement element = o.get(MODE_NAME);
                if (element instanceof JsonPrimitive primitive && primitive.isBoolean()) {
                    return element.getAsBoolean();
                }
            }
        } catch (IOException e) {
            Terralith.LOGGER.error("Couldn't read " + FILE_PATH, e);
        }
        return false;
    }

    public static String readConfigCursed(){
        try (InputStream in = Files.newInputStream(FILE_PATH);
             InputStreamReader reader = new InputStreamReader(in)) {
            if(JsonParser.parseReader(reader) instanceof JsonObject o){
                JsonElement element = o.get(CURSED_NAME);
                if (element instanceof JsonPrimitive primitive && primitive.isString()) {
                    return element.getAsString();
                }
            }
        } catch (IOException e) {
            Terralith.LOGGER.error("Couldn't read " + FILE_PATH, e);
        }
        return "none";
    }

}
