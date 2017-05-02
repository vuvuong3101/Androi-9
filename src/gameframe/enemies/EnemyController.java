package gameframe.enemies;

import gameframe.Collider;
import gameframe.PlayerController;
import gameframe.controller.CollisionManager;
import gameframe.controller.Controller;
import gameframe.models.GameRect;
import gameframe.utils.Utils;
import gameframe.views.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by admin on 4/13/17.
 */
public class EnemyController extends Controller implements Collider{
    public int getEnemyHP() {
        return enemyHP;
    }

    public void setEnemyHP(int enemyHP) {
        this.enemyHP = enemyHP;
    }

    private int enemyHP = 3;
    private int damage = 2;
    static boolean isStatusLeft;
    static boolean isStatusRight;
    private boolean shootEnable;
    private Movebehavior movebehavior;
    static  ArrayList<BulletEnemyController> bulletEnemyControllers;

    public void setMovebehavior(Movebehavior movebehavior) {
        this.movebehavior = movebehavior;
    }

    public EnemyController(int x, int y, Image image) {
        gameRect = new GameRect(x , y, 50, 50);
        imageRenderer = new ImageRenderer(image);
        isStatusRight = true;
        shootEnable = true;
        bulletEnemyControllers = new ArrayList<>();

        CollisionManager.instance.add(this);

    }

    public  void automove(){
        if (movebehavior != null) {
            movebehavior.move(gameRect);

        }
//        if (gameRect.getY() > 700) {
//            gameRect.setY(0);
//        }
//        if (gameRect.getX() > 600) {
//            gameRect.setY(100);
//        }
    }
    int coolDownTime;
    public  void autoshoot() {
        coolDownTime --;
        if (shootEnable) {
            BulletEnemyController enemy_bullet = new BulletEnemyController(
                    gameRect.getX() + gameRect.getWidth() / 2,
                    gameRect.getY(),
                    Utils.loadImage("res/enemy_bullet.png"));
            bulletEnemyControllers.add(enemy_bullet);
            shootEnable = false;
            coolDownTime = 70;
        }
        if (coolDownTime == 0){
            shootEnable = true;
        }
    }
    public void draw_Ebullet(Graphics graphics) {
        for ( BulletEnemyController bulletEnemyController : bulletEnemyControllers) {
            bulletEnemyController.draw(graphics);
        }
    }
    public  void updateEbullet() {
        for ( BulletEnemyController bulletEnemyController : bulletEnemyControllers) {
            bulletEnemyController.update();
        }
    }

    public ArrayList<BulletEnemyController> getEnemybullets() {
        return bulletEnemyControllers;
    }
    public  void getHit(int damage) {
        enemyHP = enemyHP - damage;
        System.out.println("ENEMY HP: "+ enemyHP);
        if (enemyHP <= 0)
            gameRect.setDead(true);
            CollisionManager.instance.remove(this);



    }
    @Override
    public void onCollide(Collider other) {
        if (other instanceof PlayerController) {
            ((PlayerController) other).getHit(damage);

        }
     }

}
