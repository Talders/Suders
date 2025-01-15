package de.suders.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import de.suders.SudersMain;
import de.suders.map.StartMap;
import de.suders.map.logic.Map;
import lombok.Getter;

public class GameRunningScreen implements Screen {

    private SudersMain game;
    @Getter
    private Map map;

    public GameRunningScreen(SudersMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.map = new StartMap(true);
    }

    @Override
    public void render(float delta) {
        this.map.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.map.kill();
    }
}
