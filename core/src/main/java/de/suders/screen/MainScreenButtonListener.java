package de.suders.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainScreenButtonListener extends ChangeListener {

    private Game game;
    private BitmapFont font;

    public MainScreenButtonListener(Game game, BitmapFont font) {
        this.game = game;
        this.font = font;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if(event == null || actor == null || !(game.getScreen() instanceof MainTitleScreen)) return;
        String name = actor.getName();
        switch (name) {
            case "start_button":
                //game.setScreen(new GameRunningScreen(game, font));
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
