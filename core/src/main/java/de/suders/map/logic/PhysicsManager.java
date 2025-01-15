package de.suders.map.logic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsManager {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private ContactListener contactListener;

    /**
     * Konstruktor zur Initialisierung der Box2D-Welt und des Debug-Renderers.
     *
     * @param contactListener Der Kontaktlistener für Kollisionen.
     */
    public PhysicsManager(GameContactListener contactListener, Box2DDebugRenderer debugRenderer, World world) {
        this.world = world;
        this.debugRenderer = debugRenderer;
        this.contactListener = contactListener;
        this.world.setContactListener(contactListener);
        contactListener.setWorld(world);
    }

    /**
     * Führt einen Simulationsschritt der Physik-Welt durch.
     *
     * @param delta Zeit zwischen den Schritten.
     */
    public void step(float delta) {
        world.step(delta, 6, 2);
    }

    /**
     * Zeichnet die Box2D-Körper zur Debugging-Zwecken.
     *
     * @param camera Die Kameraansicht.
     */
    public void renderDebug(OrthographicCamera camera) {
        debugRenderer.render(world, camera.combined);
    }

    /**
     * Erstellt einen statischen Box2D-Körper basierend auf einem Rechteck.
     *
     * @param centerX   X-Koordinate des Zentrums.
     * @param centerY   Y-Koordinate des Zentrums.
     * @param halfWidth  Halbe Breite des Rechtecks.
     * @param halfHeight Halbe Höhe des Rechtecks.
     * @return Der erstellte statische Körper.
     */
    public Body createStaticBody(float centerX, float centerY, float halfWidth, float halfHeight) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(centerX, centerY);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

    /**
     * Erstellt einen dynamischen Box2D-Körper basierend auf einem Rechteck.
     *
     * @param centerX   X-Koordinate des Zentrums.
     * @param centerY   Y-Koordinate des Zentrums.
     * @param halfWidth  Halbe Breite des Rechtecks.
     * @param halfHeight Halbe Höhe des Rechtecks.
     * @return Der erstellte dynamische Körper.
     */
    public Body createDynamicBody(float centerX, float centerY, float halfWidth, float halfHeight) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(centerX, centerY);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

    /**
     * Dispose der Ressourcen.
     */
    public void dispose() {
        debugRenderer.dispose();
        world.dispose();
    }
}
