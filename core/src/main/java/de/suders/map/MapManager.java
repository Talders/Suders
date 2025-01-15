package de.suders.map;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import de.suders.assets.Assets;
import lombok.NonNull;

public class MapManager {

    public MapManager(Map map) {

    }

    public static void createPhysicsFromMap(@NonNull MapLayer layer, @NonNull World world) {
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
