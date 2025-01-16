package de.suders.content.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ray3k.stripe.scenecomposer.SceneComposerStageBuilder;
import de.suders.SudersMain;
import lombok.Getter;
import lombok.NonNull;

public abstract class Layout {

    @Getter
    private String layoutName, groupName;
    private Skin skin;
    private Actor actor;

    public Layout(@NonNull String layoutName, @NonNull String groupName, Skin skin) {
        this.layoutName = layoutName;
        this.groupName = groupName;
        this.skin = skin == null ? SudersMain.screenManager.getUiSkin() : skin;
    }

    public final void showLayout(@NonNull Stage stage, Container container) {
        if (actor == null) {
            SceneComposerStageBuilder sceneComposerStageBuilder = new SceneComposerStageBuilder();
            sceneComposerStageBuilder.build(stage, skin, Gdx.files.internal("ui/layout/" + layoutName + ".json"));
            actor = stage.getRoot().findActor(groupName);
            actor.setUserObject(container);
        }
    }

    public final void killLayout(@NonNull Stage stage) {
        if (actor != null) {
            stage.getActors().removeValue(actor, true);
            actor = null;
        }
    }

    public final boolean isCreated(@NonNull Stage stage) {
        return actor != null;
    }
}
