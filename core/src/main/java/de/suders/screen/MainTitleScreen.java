package de.suders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ray3k.stripe.scenecomposer.SceneComposerStageBuilder;
import de.suders.SudersMain;
import de.suders.assets.Assets;

public class MainTitleScreen implements Screen {

    private Stage stage;
    private Viewport viewport;
    private Skin skin;
    private SudersMain game;
    private MainScreenButtonListener mainScreenButtonListener;

    public MainTitleScreen(SudersMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(Assets.SCREEN_WIDTH, Assets.SCREEN_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        this.skin = SudersMain.screenManager.getUiSkin();;
        SceneComposerStageBuilder builder = new SceneComposerStageBuilder();
        builder.build(stage, skin, Gdx.files.internal("ui/layout/mainLayout.json"));
        TextButton button = stage.getRoot().findActor("start_button");
        Label label = button.getLabel();
        BitmapFont font = button.getStyle().font;

        stage.addListener(mainScreenButtonListener = new MainScreenButtonListener(game, font));
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
        stage.dispose();
        viewport = null;
        mainScreenButtonListener = null;
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
