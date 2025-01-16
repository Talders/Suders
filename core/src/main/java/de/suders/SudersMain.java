package de.suders;

import de.suders.resource.ResourceManager;
import de.suders.screen.MainTitleScreen;
import de.suders.screen.ScreenManager;
import de.suders.screen.SudersGame;
import lombok.Getter;

import java.io.IOException;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class SudersMain extends SudersGame {

    public static ScreenManager screenManager;
    public static ResourceManager resourceManager;

    @Getter
    private static SudersMain instance;

    public SudersMain() {
        instance = this;
    }

    @Override
    public void create() {
        try {
            resourceManager = new ResourceManager();
            System.out.println(resourceManager.getSettings().toString() + " TO STRING");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screenManager = new ScreenManager();
        setScreen(new MainTitleScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        resourceManager.getSettings().setSoundVolume(44);
        resourceManager.getSettings().setMusicVolume(30);

        resourceManager.saveSerializableResource(resourceManager.getSettings());
    }
}
