package de.suders.content.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import de.suders.SudersMain;
import de.suders.content.container.MainTitleContainer;
import de.suders.content.container.MainTitleSettingsContainer;
import de.suders.content.layout.SettingsLayout;
import de.suders.content.logic.Container;
import de.suders.screen.GameRunningScreen;
import de.suders.screen.MainTitleScreen;
import de.suders.screen.SudersGame;

public class MainScreenButtonListener extends ChangeListener {

    private final SudersGame game;

    public MainScreenButtonListener() {
        game = SudersMain.getInstance();
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        if (!(event == null || actor == null || !(game.getScreen() instanceof MainTitleScreen))) {
            String name = actor.getName();
            switch (name) {
                case "start_button":
                    synchronized (game) {
                        game.setScreen(new GameRunningScreen(event.getStage()));
                    }
                    break;
                case "settings_button":
                    getMainContainer(event.getStage()).dispose();
                    SudersMain.screenManager.getContainerByClass(MainTitleSettingsContainer.class, event.getStage()).showContainer();
                    break;
                case "title_button":
                    Actor parentActor = actor;
                    do {
                        parentActor = parentActor.hasParent() ? parentActor.getParent() : null;
                        if (parentActor == null) break;

                    } while (parentActor.getUserObject() == null);

                    assert parentActor != null;
                    if (parentActor.getUserObject() != null) {
                        if (parentActor.getUserObject() instanceof Container) {
                            Container container = (Container) parentActor.getUserObject();
                            container.dispose();
                        }
                    }
                    getMainContainer(event.getStage()).showContainer();
                    break;
                case "music_slider":
                    ((SettingsLayout) getSettingsContainer(event.getStage()).getLayout()).updateLabelOfValue(SettingsLayout.SettingsTypes.MUSIC_SLIDER);
                    break;
                case "sound_slider":
                    ((SettingsLayout) getSettingsContainer(event.getStage()).getLayout()).updateLabelOfValue(SettingsLayout.SettingsTypes.SOUND_SLIDER);
                    break;
                case "exit_button":
                    Gdx.app.exit();
                    break;
                case "quick_music_button":

                    break;
                case "quick_sound_button":

                    break;
                default:
                    break;
            }
        }
    }

    private Container getMainContainer(Stage stage) {
        return SudersMain.screenManager.getContainerByClass(MainTitleContainer.class, stage);
    }

    private Container getSettingsContainer(Stage stage) {
        return SudersMain.screenManager.getContainerByClass(MainTitleSettingsContainer.class, stage);
    }
}
