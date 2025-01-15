package de.suders.map.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import lombok.Getter;

public abstract class DynamicEntity {

    @Getter
    private Body body;

    public DynamicEntity(Body body) {
        this.body = body;
        this.body.setUserData(this);
    }

    /**
     * Update-Methode zur Aktualisierung der Entität.
     *
     * @param delta Zeit zwischen den Frames
     */
    public abstract void update(float delta);

    /**
     * Render-Methode zur Zeichnung der Entität.
     *
     * @param batch Der SpriteBatch zum Zeichnen
     */
    public abstract void render(SpriteBatch batch);

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
