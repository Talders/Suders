package de.suders.content.container;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.suders.SudersMain;
import de.suders.content.layout.WelcomeLayout;
import de.suders.content.logic.Container;
import lombok.NonNull;

public class WelcomeContainer extends Container {

    public WelcomeContainer(@NonNull Stage stage) {
        super(SudersMain.screenManager.getLayoutByClass(WelcomeLayout.class),
            stage);
    }
}
