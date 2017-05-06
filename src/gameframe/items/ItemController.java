package gameframe.items;

import gameframe.controller.Collider;
import gameframe.controller.CollisionManager;
import gameframe.controller.Controller;
import gameframe.controller.ControllerManager;
import gameframe.models.GameRect;
import gameframe.views.ImageRenderer;

import java.awt.*;

/**
 * Created by admin on 5/2/17.
 */
public class ItemController extends Controller implements Collider {
    public  ItemController (int x, int y , Image image) {
       gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
       imageRenderer = new ImageRenderer(image);
        CollisionManager.instance.add(this);


    }
    public  void draw(Graphics graphics) {
        if (gameRect.isDead()) return;
        imageRenderer.render(graphics, gameRect);
    }
    public  void move() {
        gameRect.move(0, 3);
    }
    public void getHit() {
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);
    }
    @Override
    public void onCollide(Collider other) {

    }
}
