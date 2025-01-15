package de.suders.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Walkable {

    private TextureRegion currentFrame;

    public Player(float spawnX, float spawnY, World world) {
        super("Art/Characters/Character/Character_Walk.png", "Art/Characters/Character/Character_Idle.png", 4, 4, 2, 4, true, world, spawnX, spawnY);
    }

    @Override
    public void onCollision(DynamicEntity other) {

    }
}
