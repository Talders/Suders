package de.suders.screen;

import com.badlogic.gdx.Game;
import de.suders.map.logic.Map;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class SudersGame extends Game {

    private Map map;

    public abstract void create();

}
