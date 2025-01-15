package de.suders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import lombok.Getter;

public class ScreenManager {

    @Getter
    private Skin uiSkin;

    public ScreenManager() {
        uiSkin = new Skin(Gdx.files.internal("ui/mainAtlas.json"));
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/mainAtlas.atlas"));
        uiSkin.addRegions(atlas);
    }
}
