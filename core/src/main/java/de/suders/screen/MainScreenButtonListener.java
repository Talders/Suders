package de.suders.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import de.suders.SudersMain;
import jdk.tools.jmod.Main;

public class MainScreenButtonListener extends ChangeListener {

    private SudersMain game;
    private BitmapFont font;

    public MainScreenButtonListener(SudersMain game, BitmapFont font) {
        this.game = game;
        this.font = font;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if(event == null || actor == null || !(game.getScreen() instanceof MainTitleScreen)) return;
        String name = actor.getName();
        switch (name) {
            case "start_button":
                game.setScreen(new GameRunningScreen(game));
                break;
            case "settings_button":

                break;
            case "exit_button":
                Gdx.app.exit();
                break;
            case "quick_music_button":

                break;
            case "quick_sound_button":

                break;
            default:
                break;
        }
    }
}
