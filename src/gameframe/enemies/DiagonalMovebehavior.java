package gameframe.enemies;

import gameframe.models.GameRect;

/**
 * Created by admin on 4/30/17.
 */
public class DiagonalMovebehavior extends Movebehavior {
    @Override
    public void move(GameRect gameRect) {
        gameRect.move(2, 4);
    }
}
