package gameframe.enemies;

import gameframe.models.GameRect;

/**
 * Created by admin on 4/25/17.
 */
public class HorzMovebehavior extends Movebehavior {
    @Override
    public void move(GameRect gameRect) {
        gameRect.move(0, 3);
    }
}
