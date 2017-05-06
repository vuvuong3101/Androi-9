package gameframe.controller;

import gameframe.models.GameRect;

/**
 * Created by admin on 4/26/17.
 */
public interface Collider {
     GameRect getGameRect();

     void onCollide(Collider other);

}
