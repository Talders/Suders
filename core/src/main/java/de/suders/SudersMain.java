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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screenManager = new ScreenManager();
        MainTitleScreen mainTitleScreen = new MainTitleScreen(this);
        try {
            setScreen(mainTitleScreen);

            screenManager.loadListenersToStage(mainTitleScreen.getStage());
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        resourceManager.saveSerializableResource(resourceManager.getSettings());
    }
}
