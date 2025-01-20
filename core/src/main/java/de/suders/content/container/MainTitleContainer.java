package de.suders.content.container;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.suders.SudersMain;
import de.suders.content.layout.MainTitleLayout;
import de.suders.content.logic.Container;
import lombok.NonNull;

public class MainTitleContainer extends Container {

    public MainTitleContainer(@NonNull Stage stage) {
        super(SudersMain.screenManager.getLayoutByClass(MainTitleLayout.class),
            stage);
    }
}
