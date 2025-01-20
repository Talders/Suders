package de.suders.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import de.suders.entity.Player;
import de.suders.map.logic.GameContactListener;
import de.suders.map.logic.Map;
import de.suders.map.logic.MapRenderer;
import de.suders.map.logic.PhysicsManager;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class StartMap implements Map {

    private String name;
    private World world;
    private Stage stage;
    private MapRenderer mapRenderer;
    private PhysicsManager physicsManager;
    private Player player;

    public StartMap(boolean debug, @NonNull Stage stage) {
        name = "Start Map";
        world = new World(new Vector2(0, 0), true);
        this.stage = stage;

        mapRenderer = new MapRenderer(world, "Beginning Fields", "Object Layer 1", 14f, 24f, true);
        mapRenderer.setPlayer(player = new Player(14f, 24f, world));
        physicsManager = new PhysicsManager(new GameContactListener(), mapRenderer.getDebugRenderer(), world);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        MapLayer mapLayer = mapRenderer.getTiledMap().getLayers().get("collision");
        if (mapLayer != null) MapManager.createPhysicsFromMap(mapLayer, world);
    }

    @Override
    public void kill() {
        mapRenderer.dispose();
        stage.dispose();
        world.dispose();
        name = null;
        physicsManager.dispose();
        physicsManager = null;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        physicsManager.step(delta);
        mapRenderer.render(delta);
        if (player != null) player.updateMovement(delta, mapRenderer.getCamera());
        stage.act(delta);
        stage.draw();
    }
}
