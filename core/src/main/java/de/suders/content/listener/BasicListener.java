package de.suders.content.listener;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.suders.SudersMain;
import de.suders.content.container.WelcomeContainer;
import de.suders.content.logic.Container;
import de.suders.screen.GameRunningScreen;

public class BasicListener extends ClickListener {

    public boolean keyUp(InputEvent event, int keycode) {
        if(!(SudersMain.getInstance().getScreen() instanceof GameRunningScreen)) return true;
        if (keycode == Input.Keys.E) {
            SudersMain.screenManager.getContainerByClass(WelcomeContainer.class, SudersMain.getInstance().getMap().getStage()).toggleContainer();
            return true;
        }
        return false;
    }


    @Override
    public void clicked(InputEvent event, float x, float y) {
        if(!(SudersMain.getInstance().getScreen() instanceof GameRunningScreen)) return;
        Stage stage = event.getStage();
        Actor actor = event.getTarget();
        if (actor instanceof Button) {
            Container container = getContainerByActor(actor);
            if(container == null) return;

            Button button = (Button) actor;
            String name = button.getName();
            switch (name) {
                case "close_button":
                    container.toggleContainer();
                    break;
                default:
                    break;
            }

        }
    }

    private Container getContainerByActor(Actor actor) {
        Group group = actor.getParent();
        if (group != null) {
            Object userData = group.getUserObject();
            if (userData instanceof Container) {
                return (Container) userData;
            } else {
                group.clearChildren();
                return null;
            }
        }
        throw new IllegalStateException("Close button was clicked, but no group found!");
    }
}
