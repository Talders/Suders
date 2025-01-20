package de.suders.content.container;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.suders.SudersMain;
import de.suders.content.layout.SettingsLayout;
import de.suders.content.logic.Container;
import lombok.NonNull;

public class MainTitleSettingsContainer extends Container {

    public MainTitleSettingsContainer(@NonNull Stage stage) {
        super(SudersMain.screenManager.getLayoutByClass(SettingsLayout.class), stage);
    }
}
