package de.suders.map.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MapCamera extends OrthographicCamera {

    public MapCamera(float spawnX, float spawnY) {
        this.setToOrtho(false, (Gdx.graphics.getWidth() / 8), Gdx.graphics.getHeight() / 8);
        this.position.x = spawnX; //14
        this.position.y = spawnY; //28
        this.zoom = 0.0955f;
        this.update();
    }
}
