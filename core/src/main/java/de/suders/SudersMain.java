package de.suders;

import com.badlogic.gdx.Game;
import de.suders.screen.MainTitleScreen;
import de.suders.screen.ScreenManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SudersMain extends Game {

   public static ScreenManager screenManager;

    @Override
    public void create() {
        screenManager = new ScreenManager();
        setScreen(new MainTitleScreen(this));
    }
}
