package de.suders.map.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface Map {

    String getName();
    World getWorld();
    MapRenderer getMapRenderer();
    Stage getStage();
    void kill();
    void render(float delta);

}
