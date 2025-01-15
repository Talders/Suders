package de.suders.map.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import jdk.javadoc.internal.doclets.formats.html.taglets.UserTaglet;

public class DebugUIScreen {

    private Camera camera;
    private Label label;
    private Stage stage;

    public DebugUIScreen(Camera camera, BitmapFont font) {
        this.camera = camera;
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        label = new Label("", style);
        label.setPosition(50, 60);
        stage = new Stage();
        stage.addActor(label);
        render(Gdx.graphics.getDeltaTime());
    }

    public void render(float delta) {
        label.setText("FPS: " + Gdx.graphics.getFramesPerSecond() + "\nX: " + camera.position.x + " Y: " + camera.position.y);
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
