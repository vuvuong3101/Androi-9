package gameframe;

import gameframe.BulletPlayerController;
import gameframe.controller.Collider;
import gameframe.controller.CollisionManager;
import gameframe.controller.Controller;
import gameframe.controller.ControllerManager;
import gameframe.enemies.EnemyController;
import gameframe.enemies.Movebehavior;
import gameframe.items.ItemController;
import gameframe.models.GameRect;
import gameframe.scenes.Level1scene;
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

    public void setIsBullet(int isBullet) {
        this.isBullet = isBullet;
    }

    public int getIsBullet() {
        return isBullet;
    }

    private  int isBullet = 1 ;
    private  int cdTime = 0;
    String bulletdefaut = null;
    String onebullet = "res/bullet-single.png";
    String towbullet = "res/bullet-double.png";
    private  int active = 1;


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
    Movebehavior movebehavior;
    private  Level1scene level1scene;

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
            isBullet += 1;
            System.out.println(isBullet);
            if (isBullet == 2) {
                for (BulletPlayerController bulletPlayerController : bulletPlayerControllers) {
                    bulletPlayerController.setDamage(10);
//                    bulletPlayerController.getDamage();
                    System.out.println(bulletPlayerController.getDamage());

                }
            }

        }

    }
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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
                              boolean isSpacePressed) {
        dx = 0;
        dy = 0;

        if (active == 2) {
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

                if (isBullet == 1) {
                    bulletdefaut = onebullet;
                }
                if (isBullet == 2) {
                    bulletdefaut = towbullet;
                }
                if (shootEnable) {
                    BulletPlayerController bullet = new BulletPlayerController(gameRect.getX() + gameRect.getWidth() / 2,
                            gameRect.getY(), Utils.loadImage(bulletdefaut));
                    bulletPlayerControllers.add(bullet);
                    shootEnable = false;
                    coolDownTime = 15;
                    if (isBullet == 3) {
                        BulletPlayerController bullet2 = new BulletPlayerController(gameRect.getX() + gameRect.getWidth() / 2,
                                gameRect.getY(), Utils.loadImage(bulletdefaut));
                    }
                }
                if (playerHP <= 0) {
                    shootEnable = false;
                }

            }

        }
        if (active == 1) {
            gameRect.move(0, -3);
            if (gameRect.getY() < 570) {
                gameRect.move(0, 0);
                active = 2;
            }
        }
    }
    int coolDownTime;
    public  void update() {
         gameRect.move(dx,dy);
        if  (!shootEnable) {
            if (playerHP > 0)
            coolDownTime --;
            if (coolDownTime <= 0) {
                shootEnable = true;
            }
        }

        if (isBullet == 2) {
            cdTime ++;
            System.out.println(cdTime);

            if (cdTime >= 700) {
                isBullet -= 1;
                cdTime = 0;
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
