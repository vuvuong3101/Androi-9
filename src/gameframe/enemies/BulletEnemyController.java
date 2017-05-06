package gameframe.enemies;

import gameframe.controller.Collider;

import gameframe.PlayerController;
import gameframe.controller.CollisionManager;
import gameframe.controller.Controller;
import gameframe.controller.ControllerManager;
import gameframe.models.GameRect;
import gameframe.views.ImageRenderer;

import java.awt.*;

/**
 * Created by admin on 4/13/17.
 */
public class BulletEnemyController extends Controller implements Collider{
    private int x;
    private int y;
    private Image image;
    private int damage = 1;

    public BulletEnemyController(int x, int y, Image image) {
        int rectX = x - image.getWidth(null)/2;
        int rectY = y + image.getHeight(null);
        int rectW = image.getWidth(null);
        int rectH = image.getHeight(null);
        this.gameRect = new GameRect(rectX, rectY, rectW, rectH);
        this.imageRenderer = new ImageRenderer(image);
        CollisionManager.instance.add(this);
    }
//    public void draw(Graphics graphics) {
//        graphics.drawImage(image, x, y, null);
//    }
    public  void update(){
       gameRect.move(0,5);
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof PlayerController) {
            ((PlayerController)other).getHit(damage);
            gameRect.setDead(true);
            CollisionManager.instance.remove(this);
        }
    }
}
