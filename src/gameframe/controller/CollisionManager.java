package gameframe.controller;

import gameframe.Collider;
import gameframe.models.GameRect;

import java.util.ArrayList;

/**
 * Created by admin on 4/26/17.
 */
public class CollisionManager {
    private ArrayList<Collider> colliders;
    public  static  final CollisionManager instance = new CollisionManager();
    private   CollisionManager() {
        colliders =  new ArrayList<>();
    }

    public void  update() {
        for ( int i = 0; i < colliders.size(); i++) {
            for ( int j = i+1; j < colliders.size(); j++) {
                Collider ci = colliders.get(i);
                Collider cj = colliders.get(j);

                GameRect recti = ci.getGameRect();
                GameRect rectj = cj.getGameRect();

                if ( recti.intersects(rectj)) {
                    ci.onCollide(cj);
                    cj.onCollide(ci);
                }
            }
        }

    }

    public  void add(Collider collider) {
        colliders.add(collider);
    }
    public  void remove(Collider collider) {
        if (collider.getGameRect().isDead()) {
            colliders.remove(collider);
        }

    }
}
