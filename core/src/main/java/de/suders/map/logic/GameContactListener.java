package de.suders.map.logic;

import com.badlogic.gdx.physics.box2d.*;
import de.suders.entity.DynamicEntity;
import de.suders.map.MapManager;
import lombok.Setter;

public class GameContactListener implements ContactListener {

    @Setter
    private World world;
    /*@Setter
    private Player player;
    */@Setter
    private MapManager mapLoader;

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        Object userDataA = bodyA.getUserData();
        Object userDataB = bodyB.getUserData();

        if (userDataA instanceof DynamicEntity && userDataB instanceof DynamicEntity) {
            DynamicEntity entityA = (DynamicEntity) userDataA;
            DynamicEntity entityB = (DynamicEntity) userDataB;

            entityA.onCollision(entityB);
            entityB.onCollision(entityA);
        }
        /* Player
        boolean isPlayerInvolved = bodyA.equals(player.getBody()) || bodyB.equals(player.getBody());
        if (isPlayerInvolved) {
            EventType eventType = mapLoader.getEventBodies().get(bodyA);
            if (eventType == null) {
                eventType = mapLoader.getEventBodies().get(bodyB);
            }
            if(eventType == null) return;

            if (eventType.equals(EventType.TEST)) {
                if (!SudersMain.eventManager.uniqueEventExists(eventType)) {
                    SudersMain.eventManager.callEvent(new CallInventoryEvent("debugLayout", mapLoader.getStage()));
                } else {
                    SudersMain.eventManager.getUniqueEvent(eventType).callEvent();
                }
            }

         */
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
