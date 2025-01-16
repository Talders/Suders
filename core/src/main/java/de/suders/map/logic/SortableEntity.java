package de.suders.map.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.math.Vector2;
import de.suders.assets.Assets;
import de.suders.entity.Player;
import lombok.Getter;

public class SortableEntity {

    private Player player;
    private TextureRegion region;
    @Getter
    private Vector2 position, bounds;
    private boolean halfRender = false;

    public SortableEntity(Player player) {
        this.position = player.getPosition();
        this.bounds = player.getBounds();
        this.player = player;
        this.region = player.getCurrentFrame();
    }

    public SortableEntity(TextureMapObject mapObject) {
        this.position = new Vector2(mapObject.getX() * Assets.UNIT_SCALE, mapObject.getY() * Assets.UNIT_SCALE);
        this.bounds = new Vector2(mapObject.getTextureRegion().getRegionWidth() * Assets.UNIT_SCALE, mapObject.getTextureRegion().getRegionHeight() * Assets.UNIT_SCALE);
        this.region = mapObject.getTextureRegion();
        this.halfRender = mapObject.getProperties().get("half_render") != null && (boolean) mapObject.getProperties().get("half_render");
    }

    public void render(SpriteBatch batch) {
        if (player != null) {
            batch.draw(region,
                player.getBody().getPosition().x - (bounds.x / 2), // Zentriere Sprite horizontal
                player.getBody().getPosition().y - (bounds.y / 2) + 0.25f, // Zentriere Sprite vertikal
                bounds.x,
                bounds.y);
        } else {
            batch.draw(region, position.x, position.y, bounds.x, bounds.y);
        }
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return halfRender ? this.position.y + (this.bounds.y / 2) : this.position.y + (this.bounds.y / 4);
    }
}
