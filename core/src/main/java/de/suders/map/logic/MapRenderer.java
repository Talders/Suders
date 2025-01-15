package de.suders.map.logic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Null;
import de.suders.assets.Assets;
import de.suders.entity.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;

public class MapRenderer {

    @Getter
    private MapCamera camera;
    private World world;
    private OrthogonalTiledMapRenderer renderer;
    private SpriteBatch batch;
    @Getter
    private Box2DDebugRenderer debugRenderer;
    private DebugUIScreen debugUIScreen;
    private TiledMap tiledMap;
    private MapLayer objectMapLayer;
    @Setter
    private Player player;

    public MapRenderer(World targetWorld, String filePath, @Null String objectLayer, float spawnX, float spawnY, boolean debug) {
        String mapPath = "Tiled/Tilemaps/Beginning Fields.tmx";
        world = targetWorld;
        camera = new MapCamera(spawnX, spawnY);
        batch = new SpriteBatch();

        if(debug) {
            debugRenderer = new Box2DDebugRenderer();
            debugUIScreen = new DebugUIScreen(camera, new BitmapFont());
        }

        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load(mapPath, TiledMap.class);
        assetManager.finishLoading();
        tiledMap = assetManager.get(mapPath);

        renderer = new OrthogonalTiledMapRenderer(tiledMap, Assets.UNIT_SCALE);
        renderer.setView(camera);

        objectMapLayer = objectLayer != null ? tiledMap.getLayers().get(objectLayer) : null;
    }

    public void render(float delta) {
        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if(objectMapLayer != null) {
            ArrayList<SortableEntity> entities = new ArrayList<>();
            objectMapLayer.getObjects().forEach(object -> {
                if (object instanceof TextureMapObject) {
                    entities.add(new SortableEntity((TextureMapObject) object));
                }
            });
            if(player != null) {
                entities.add(new SortableEntity(this.player));
            }
            entities.sort(Comparator.comparing(SortableEntity::getY).reversed());
            for(SortableEntity sortableEntity : entities) {
                sortableEntity.render(batch);
            }
        }
        batch.end();

        if(debugRenderer != null) {
            debugRenderer.render(world, camera.combined);
            debugUIScreen.render(delta);
        }
    }

    public void dispose() {
        world.dispose();
        renderer.dispose();
        batch.dispose();
        tiledMap.dispose();
        if(debugRenderer != null) debugRenderer.dispose();
    }

    private void createPhysicsFromMap(MapLayer layer) {
        for (MapObject object : layer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(
                    (rect.x + rect.width / 2) / Assets.PPM,
                    (rect.y + rect.height / 2) / Assets.PPM
                );

                Body body = world.createBody(bodyDef);

                PolygonShape shape = new PolygonShape();
                shape.setAsBox(
                    rect.width / 2 / Assets.PPM,
                    rect.height / 2 / Assets.PPM
                );

                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);

                //TODO: EVENT DETECTIONS
                //if(object.getProperties().get("event") != null) {
                //    eventBodies.put(body, EventType.getFromString((String) object.getProperties().get("event")));
                //}
                shape.dispose();
            }
        }
    }

}