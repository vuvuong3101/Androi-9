package gameframe;

import gameframe.controller.Collider;
import gameframe.controller.CollisionManager;
import gameframe.controller.Controller;
import gameframe.controller.ControllerManager;
import gameframe.enemies.EnemyController;
import gameframe.models.GameRect;
import gameframe.scenes.Level1scene;
import gameframe.views.ImageRenderer;

import java.awt.*;

public class BulletPlayerController extends Controller implements Collider {
    private   int damage;
    PlayerController playerController;

    public BulletPlayerController(int x, int y, Image image) {
        int rectx = x - image.getWidth(null) / 2;
        int recty = y - image.getHeight(null);
        int rectWidth = image.getWidth(null);
        int rectHeight = image.getHeight(null);
        this.imageRenderer = new ImageRenderer(image);
        this.gameRect = new GameRect(rectx, recty, rectWidth, rectHeight);
        damage = 2;

        CollisionManager.instance.add(this);
        System.out.println(damage);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void update() {
//        if (playerController.getIsBullet() == 2) {
            this.damage = 2;
        

        gameRect.move(0, -15);
    }


    @Override
    public void onCollide(Collider other) {
        if ( other instanceof EnemyController) {
            ((EnemyController) other).getHit(damage);
            System.out.println("damage: " + damage);
            gameRect.setDead(true);
            CollisionManager.instance.remove(this);
        }
    }
}
