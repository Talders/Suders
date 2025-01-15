package de.suders.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import lombok.Getter;

public abstract class DynamicEntity {

    @Getter
    private Body body;

    public DynamicEntity(World world, float spawnX, float spawnY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawnX, spawnY);
        this.body = world.createBody(bodyDef);
        this.body.setUserData(this);
    }

    /**
     * Methode, die bei einer Kollision aufgerufen wird.
     *
     * @param other Die andere kollidierende Entität
     */
    public abstract void onCollision(DynamicEntity other);

    /**
     * Getter für die Position der Entität.
     *
     * @return Die Position als Vector2
     */
    public Vector2 getPosition() {
        return body.getPosition();
    }
}
