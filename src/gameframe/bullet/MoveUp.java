package gameframe.bullet;

import gameframe.models.GameRect;

/**
 * Created by admin on 5/5/17.
 */
public class MoveUp  {
    public void moveUp(GameRect gameRect) {
        gameRect.move(0, -15);
    }
}
