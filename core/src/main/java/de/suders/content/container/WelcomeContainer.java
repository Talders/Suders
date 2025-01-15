package de.suders.content.container;

import de.suders.SudersMain;
import de.suders.content.layout.WelcomeLayout;
import de.suders.content.logic.Container;

public class WelcomeContainer extends Container {

    public WelcomeContainer() {
        super(SudersMain.screenManager.getLayoutByClass(WelcomeLayout.class),
            SudersMain.getInstance().getMap().getStage());
    }
}
