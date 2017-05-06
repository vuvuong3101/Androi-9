package gameframe.enemies;

import gameframe.models.GameRect;

/**
 * Created by admin on 5/5/17.
 */
public class IslandMove extends Movebehavior {
    @Override
    public void move(GameRect gameRect) {
        gameRect.move(0, 1);

    }
}
