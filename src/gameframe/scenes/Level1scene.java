package gameframe.scenes;

import gameframe.GameWindow;
import gameframe.PlayerController;
import gameframe.controller.BackgroundController;
import gameframe.controller.CollisionManager;
import gameframe.controller.ControllerManager;
import gameframe.enemies.*;
import gameframe.items.Islands;
import gameframe.items.ItemController;
import gameframe.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admin on 5/3/17.
 */
public class Level1scene implements GameScene {
    boolean isUpPressed;
    boolean isDownPressed;
    boolean isLeftPressed;
    boolean isRightPressed;
    boolean isSpacePressed;
    private int startEnemy = 1;
    boolean startItem = true;
    private  int cdTime;
    private int cooldown_startEnemy;
    private boolean gameover;
    private int yplayer = 720;
    private  boolean intro = true;
    static int countLV;
    ItemController itemController;
    PlayerController playerController;
    EnemyController enemyController;
    EnemyController enemyController2;
    EnemyController enemyController3;
    ArrayList<ItemController> itemControllers;
    BackgroundController backgroundController;
    BackgroundController backgroundController2;
    Islands islands;
    Random random =  new Random();

    public Level1scene() {
        // island
        int ilX = random.nextInt(500) + 50;
        int ilY= random.nextInt(100) + -1;
        enemyController2 = new EnemyController(ilX, ilY, Utils.loadImage("res/island.png"));
        countLV = 0;
        
        // CR player
        playerController = new PlayerController(270, yplayer, Utils.loadImage("res/plane2.png"));
        backgroundController = new BackgroundController(0, -700, Utils.loadImage("res/stars.png"));
        backgroundController2 = new BackgroundController(0, 0, Utils.loadImage("res/stars.png"));
    }

    public void setStartEnemy(int startEnemy) {
        this.startEnemy = startEnemy;
    }

    public  void update() {

            if (!startItem) {
                cdTime--;
                if (cdTime == 0) {
                    startItem = true;
                }
            }

            if (startItem) {
                int xIT = random.nextInt(500) + 100;
                itemController = new ItemController(xIT, -2300, Utils.loadImage("res/power-up.png"));
                startItem = false;
                cdTime = 2000;


            }
            //// Enemy

            if (startEnemy == 2) {
                cooldown_startEnemy--;
                if (cooldown_startEnemy == 0) {
                    startEnemy = 1;
                }
            }

            if (startEnemy == 1) {
                enemyController3 = new EnemyController(0, -400, Utils.loadImage("res/enemy_plane_yellow_1.png"));
                ControllerManager.instance.add(enemyController3);
                enemyController3.setMovebehavior(new Movebehavior());
                int Low = 150;
                int High = 300;
                int xr = random.nextInt(High - Low) + Low;
                for (int x = 0; x < 600; x += xr) {
                    if (x + 50 < 550) {
                        enemyController = new EnemyController(x + 50, -300, Utils.loadImage("res/enemy_plane_white_3.png"));
                        enemyController.setMovebehavior(new HorzMovebehavior());
                        ControllerManager.instance.add(enemyController);
                    }
                }
                startEnemy = 2;
                cooldown_startEnemy = 300;

        }

         if (playerController.getPlayerHP() <= 0 ) {
             GameWindow.instance.setCurrentScene(new GameOver());
         }

         // move background
         backgroundController.update();
         backgroundController2.update();
         enemyController2.setMovebehavior(new HorzMovebehavior());

         // move object
         playerController.updateBullet();
         CollisionManager.instance.update();
         ControllerManager.instance.update();
         playerController.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed);
         enemyController.updateEbullet();
         playerController.update();
         itemController.move();
    }

    public  void draw(Graphics graphics) {
        backgroundController.draw(graphics);
        backgroundController2.draw(graphics);
//        enemyController2.draw(graphics);

        playerController.drawBullet(graphics);
        ControllerManager.instance.draw(graphics);
        enemyController.draw_Ebullet(graphics);
        itemController.draw(graphics);
        playerController.draw(graphics);
//        islands.draw(graphics);
    }



    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            isLeftPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            isRightPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            isUpPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            isDownPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacePressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            isLeftPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            isRightPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            isUpPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            isDownPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isSpacePressed = false;
        }
    }
}

