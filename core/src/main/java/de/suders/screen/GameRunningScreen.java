package de.suders.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.suders.SudersMain;
import de.suders.map.StartMap;
import de.suders.map.logic.Map;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class GameRunningScreen implements Screen {

    private Map map;
    private Stage stage;

    public GameRunningScreen(@NonNull Stage stage) {
        this.stage = stage;
    }

    @Override
    public void show() {
        this.map = new StartMap(true, stage);
        SudersMain.getInstance().setMap(map);
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
        SudersMain.getInstance().setMap(null);
        this.map.kill();
    }
}
