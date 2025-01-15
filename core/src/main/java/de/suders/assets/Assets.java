package de.suders.assets;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.util.ArrayList;
import java.util.List;

public class Assets {

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    public static final int VIEWPORT_WIDTH = 1792;
    public static final int VIEWPORT_HEIGHT = 1024;
    public static final int PPM = 16;
    public static boolean DEBUG_MODE = true;
    public static final float UNIT_SCALE = 1 / 16f;

    public static final String CLOSE_BUTTON = "close_button";


    public static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try (ScanResult scanResult = new ClassGraph()
            .acceptPackages(packageName) // Nur das gewünschte Package scannen
            .scan()) {

            scanResult.getAllClasses().forEach(classInfo -> {
                try {
                    classes.add(Class.forName(classInfo.getName()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
        return classes;
    }
}
