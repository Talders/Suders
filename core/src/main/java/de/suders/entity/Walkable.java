package de.suders.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Walkable {

    private Texture walkSheet;
    private Texture idleSheet;
    private Map<Direction, Animation<TextureRegion>> walkAnimations;
    private Map<Direction, Animation<TextureRegion>> idleAnimations;

    @Getter
    private Body body; // Box2D-Body

    @Getter
    protected Vector2 bounds;
    protected float stateTime = 0f, idleTime = 0f;
    @Getter
    protected float speed = 700f;

    @Getter
    private boolean controlable;

    protected Direction currentDirection = Direction.DOWN;

    public Walkable(String walkFilePath, String idleWalkPath, int walkRows, int walkColms, int idleRows, int idleColms, boolean controlable, World world, float spawnX, float spawnY) {
        List<Direction> directions = new ArrayList<Direction>();
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        if(walkFilePath != null) {
            walkAnimations = new HashMap<Direction, Animation<TextureRegion>>();
            walkSheet = new Texture(Gdx.files.internal(walkFilePath));
            TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / walkRows, walkSheet.getHeight() / walkColms);
            addAnimation(tmp, walkAnimations, directions);

        }
        if(idleWalkPath != null) {
            idleAnimations = new HashMap<Direction, Animation<TextureRegion>>();
            idleSheet = new Texture(Gdx.files.internal(idleWalkPath));
            TextureRegion[][] tmp2 = TextureRegion.split(idleSheet, idleSheet.getWidth() / idleRows, idleSheet.getHeight() / idleColms);
            addAnimation(tmp2, idleAnimations, directions);
        }

        this.controlable = controlable;

        // Größe des Spielers
        bounds = new Vector2(1.1f, 1.3f);
        Vector2 boxBounds = new Vector2(1, 0.9f);
        // Box2D-Body erstellen
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawnX, spawnY);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f / 2, 0.5f / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.0f; // Keine Abprallwirkung
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    private void addAnimation(TextureRegion[][] tmpRegion, Map<Direction, Animation<TextureRegion>> animations, List<Direction> directions) {
        int i = 0;
        for(Direction direction : directions) {
            TextureRegion[] frame = tmpRegion[i];
            Animation<TextureRegion> anim = new Animation<>(0.1f, frame);
            anim.setPlayMode(Animation.PlayMode.LOOP);
            animations.put(direction, anim);
            i++;
        }
    }

    public void dispose() {
        idleSheet.dispose();
        walkSheet.dispose();
        walkAnimations = null;
        idleAnimations = null;
    }

    public void updateMovement(float delta, Camera camera) {
        if(!controlable) return;
        Vector2 velocity = new Vector2(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speed = 900f;
        } else {
            speed = 700f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x -= 1;
            currentDirection = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x += 1;
            currentDirection = Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.y += 1;
            currentDirection = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocity.y -= 1;
            currentDirection = Direction.DOWN;
        }

        if (velocity.len() > 0) {
            velocity.nor().scl(speed * delta);
        }

        body.setLinearVelocity(velocity);

        if (body.getLinearVelocity().len() < 0.1f) {
            body.setLinearVelocity(0, 0);
        }

        camera.position.set(body.getPosition(), 0);

        if (velocity.len() > 0) {
            stateTime += delta;
        } else {
            stateTime = 0f;
        }
    }

    public TextureRegion getCurrentFrame() {
        if (stateTime == 0 && idleAnimations != null) {
            idleTime += Gdx.graphics.getDeltaTime();
            Animation<TextureRegion> idleAnimation = getIdleAnimations().get(currentDirection);

            int frameIndex = (int) (idleTime / 0.4f) % idleAnimation.getKeyFrames().length;

            return idleAnimation.getKeyFrames()[frameIndex];
        }
        idleTime = 0f;
        return walkAnimations != null ? this.getWalkAnimations().get(currentDirection).getKeyFrame(stateTime, true) : this.getIdleAnimations().get(currentDirection).getKeyFrame(stateTime, true);
    }

    public enum Direction {

        UP, DOWN, LEFT, RIGHT, NONE,
        UP_RIGHT, DOWN_RIGHT, UP_LEFT, DOWN_LEFT;

    }
}
