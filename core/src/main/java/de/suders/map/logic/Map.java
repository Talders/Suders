package de.suders.map.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface Map {

    public abstract String getName();
    public abstract World getWorld();
    public abstract MapRenderer getMapRenderer();
    public abstract Stage getStage();
    public abstract void kill();
    public abstract void render(float delta);

}
