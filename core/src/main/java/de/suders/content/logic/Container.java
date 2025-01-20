package de.suders.content.logic;

import com.badlogic.gdx.scenes.scene2d.Stage;
import lombok.Getter;
import lombok.NonNull;

public abstract class Container {

    @Getter
    private Layout layout;
    private Stage stage;
    @Getter
    private boolean isVisible;

    public Container(@NonNull Layout layout, @NonNull Stage stage) {
        this.layout = layout;
        this.stage = stage;
        isVisible = false;
    }

    public void toggleContainer() {
        if (isVisible)
            dispose();
        else
            showContainer();
    }

    public void showContainer() {
        layout.showLayout(stage, this);
        isVisible = true;
    }

    public final void dispose() {
        layout.killLayout(stage);
        isVisible = false;
    }
}
