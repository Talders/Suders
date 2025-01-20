package de.suders.content.layout;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.suders.content.logic.Layout;
import lombok.NonNull;

public class WelcomeLayout extends Layout {

    public WelcomeLayout(@NonNull Skin skin) {
        super("debugLayout", "debug_table", skin);
    }
}
