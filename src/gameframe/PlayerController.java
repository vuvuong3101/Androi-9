package gameframe;

import gameframe.controller.CollisionManager;
import gameframe.controller.Controller;
import gameframe.enemies.EnemyController;
import gameframe.items.ItemController;
import gameframe.models.GameRect;
import gameframe.utils.Utils;
import gameframe.views.ImageRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by admin on 4/13/17.
 */
public class PlayerController extends Controller implements Collider {
    private GameRect gameRect;
    private Image image;
    private int dx;
    private  int dy;
    private int screenW = 600;
    private int screenH = 700;
    private int playerHP = 10;
    private int damage = 20;
    private  boolean isOneBullet =  true;


    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    private boolean shootEnable;
    private ImageRenderer imageRenderer;
    static ArrayList<BulletPlayerController> bulletPlayerControllers;
    BulletPlayerController bulletPlayerController;


    public PlayerController(int x, int y, Image image) {
        gameRect =  new GameRect(x, y, 70, 56);
        imageRenderer = new ImageRenderer(image);
        this.image = image;
        shootEnable = true;
        bulletPlayerControllers = new ArrayList<>();
        CollisionManager.instance.add(this);

    }
    public  GameRect getGameRect() {
        return gameRect;
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof EnemyController) {
            ((EnemyController)other).getHit(damage);
        }
        if (other instanceof ItemController) {
            ((ItemController)other).getHit();
            isOneBullet = false;
            if (!isOneBullet) {
                for (BulletPlayerController bulletPlayerController : bulletPlayerControllers) {
                    bulletPlayerController.setDamage(10);
                    bulletPlayerController.getDamage();
                }
            }

        }

    }



    public  void updateBullet() {
        for (BulletPlayerController bullet: bulletPlayerControllers) {
            bullet.update();
        }
    }
    public  void drawBullet(Graphics graphics){
        if (!gameRect.isDead())
        for (BulletPlayerController bullet: bulletPlayerControllers) {
            bullet.draw(graphics);
        }
    }

    public void draw(Graphics graphics) {
        if (gameRect.isDead()) return;
        imageRenderer.render(graphics, gameRect);

    }

    public  void processInput(boolean isUpPressed,
                              boolean isDownPressed,
                              boolean isLeftPressed,
                              boolean isRightPressed,
                              boolean isSpacePressed){
        dx = 0;
        dy = 0;
        if (isUpPressed) {
            if (gameRect.getY() > 20) {
                dy -= 10;
            }
        }
        if (isDownPressed) {
            if (gameRect.getY() <= screenH - gameRect.getHeight()) {
                dy += 10;
            }
        }
        if (isLeftPressed) {
            if (gameRect.getX() >= 0) {
                dx -= 10;
            }
        }
        if (isRightPressed) {
            if (gameRect.getX() <= screenW - gameRect.getWidth()) {
                dx += 10;
            }
        }

        if (isSpacePressed) {
            String bulletdefaut = null;
            String onebullet = "res/bullet.png";
            String towbullet = "res/bullet-double.png";
            if (isOneBullet) {
                bulletdefaut = onebullet;
            }
            if (!isOneBullet) {
                bulletdefaut = towbullet;
            }
            if (shootEnable) {
                BulletPlayerController bullet = new BulletPlayerController(gameRect.getX() + gameRect.getWidth() / 2,
                        gameRect.getY(),Utils.loadImage(bulletdefaut));
                bulletPlayerControllers.add(bullet);
                shootEnable = false;
                coolDownTime = 15;
            }
            if (playerHP <=0) {
                shootEnable = false;
            }

        }

    }
    int coolDownTime;
    public  void update() {
         gameRect.move(dx,dy);
        if  (!shootEnable) {
            if (playerHP > 0)
            coolDownTime --;
            if (coolDownTime == 0) {
                shootEnable = true;
            }
        }
    }
    public ArrayList<BulletPlayerController> getPlayerbullets() {
        return bulletPlayerControllers;
    }
    public void getHit(int damage) {
        playerHP = playerHP - damage;
        System.out.println("playerHP: " + playerHP);
        if (playerHP <= 0) {

            System.out.println("Game OVER");
            gameRect.setDead(true);

            CollisionManager.instance.remove(this);


        }
    }

}
