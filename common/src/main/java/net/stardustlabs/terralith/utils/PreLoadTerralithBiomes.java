package net.stardustlabs.terralith.utils;

import net.cristellib.CristelLibExpectPlatform;
import net.stardustlabs.terralith.Terralith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class PreLoadTerralithBiomes {

    public static String getBiomeName(Path path){
        String[] parts = path.toString().split("biome/");
        String biomeString = parts[parts.length - 1];
        return biomeString.split("\\.")[0];
    }

    public static Set<String> getBiomeFiles(){
        Set<String> paths = new HashSet<>();
        try {
            walk(CristelLibExpectPlatform.getResourceDirectory(Terralith.HIGHEST_MOD_ID, "data/terralith/worldgen/biome"), Files::exists, (path, file) -> {
                if (Files.isRegularFile(file) && file.getFileName().toString().endsWith(".json")) {
                    paths.add(getBiomeName(file));
                }
                return true;
            }, true, Integer.MAX_VALUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return paths;
    }

    public static void walk(Path root, Predicate<Path> rootFilter, BiFunction<Path, Path, Boolean> processor, boolean visitAllFiles, int maxDepth) throws IOException {
        if (root == null || !Files.exists(root) || !rootFilter.test(root)) {
            return;
        }
        if (processor != null) {
            try (var stream = Files.walk(root, maxDepth)) {
                Iterator<Path> itr = stream.iterator();

                while (itr.hasNext()) {
                    boolean keepGoing = processor.apply(root, itr.next());
                    if (!visitAllFiles && !keepGoing) {
                        return;
                    }
                }
            }
        }
    }
}