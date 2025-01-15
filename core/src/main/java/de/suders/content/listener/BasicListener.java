package de.suders.content.listener;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.suders.SudersMain;
import de.suders.assets.Assets;
import de.suders.content.container.WelcomeContainer;
import de.suders.content.logic.Container;

public class BasicListener extends ClickListener {

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        if(keycode == Input.Keys.E) {
            Container container = SudersMain.screenManager.getContainerByClass(WelcomeContainer.class);
            if (container.isVisible())
                container.dispose();
            else container.showContainer();
            return true;
        }
        return false;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Stage stage = event.getStage();
        Actor actor = event.getTarget();
        if(actor instanceof Button) {
            Button button = (Button)actor;
            if(button.getName().equals(Assets.CLOSE_BUTTON)) {
                Group group = actor.getParent();
                if(group != null) {
                    Object userData = group.getUserObject();
                    if(userData instanceof Container) {
                        Container container = (Container)userData;
                        if(!SudersMain.screenManager.disposeContainer(container))
                            throw new RuntimeException(container.getLayout().getLayoutName() + " Layout of a Container could´nt be closed!");
                        else
                            return;

                    } else {
                        group.clearChildren();
                        return;
                    }
                }
                throw new IllegalStateException("Close button was clicked, but no group found!");
            }
        }
    }
}
