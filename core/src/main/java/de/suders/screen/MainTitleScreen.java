package de.suders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.suders.SudersMain;
import de.suders.assets.Assets;
import de.suders.content.container.MainTitleContainer;
import de.suders.content.logic.Container;
import lombok.Getter;

public class MainTitleScreen implements Screen {

    @Getter
    private Stage stage;
    private Viewport viewport;
    private Skin skin;
    private SudersMain game;

    public MainTitleScreen(SudersMain game) {
        this.game = game;
        stage = new Stage();
    }

    @Override
    public void show() {
        viewport = new FitViewport(Assets.SCREEN_WIDTH, Assets.SCREEN_HEIGHT);
        stage.setViewport(viewport);
        Gdx.input.setInputProcessor(stage);
        this.skin = SudersMain.screenManager.getUiSkin();
        MainTitleContainer mainTitleContainer = (MainTitleContainer) SudersMain.screenManager.getContainerByClass(MainTitleContainer.class, stage);
        mainTitleContainer.showContainer();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Container container = SudersMain.screenManager.getContainerByClass(MainTitleContainer.class, stage);
        if(container != null)
            container.dispose();
        stage.getRoot().clearChildren();
        viewport = null;
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
