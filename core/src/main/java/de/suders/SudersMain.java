package de.suders;

import de.suders.screen.MainTitleScreen;
import de.suders.screen.ScreenManager;
import de.suders.screen.SudersGame;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SudersMain extends SudersGame {

   public static ScreenManager screenManager;

   @Getter
   private static SudersMain instance;

   public SudersMain() {
        instance = this;
   }

    @Override
    public void create() {
        screenManager = new ScreenManager();
        setScreen(new MainTitleScreen(this));
    }
}
