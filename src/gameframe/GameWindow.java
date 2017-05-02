package gameframe;

import gameframe.controller.CollisionManager;
import gameframe.enemies.*;
import gameframe.items.ItemController;
import gameframe.models.GameRect;
import gameframe.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by admin on 4/13/17.
 */
public class GameWindow extends Frame{
    Image backgroundImage;

    private int screenW = 600;
    private int screenH = 700;
    boolean isUpPressed;
    boolean isDownPressed;
    boolean isLeftPressed;
    boolean isRightPressed;
    boolean isSpacePressed;
    boolean startEnemy =  true;
    private int cooldown_startEnemy;
    ItemController itemController;
    PlayerController playerController;
    EnemyController enemyController;
    EnemyController enemyController2;
    EnemyController enemyController3;
    BufferedImage backbufferedImage;
    Graphics backBufferedGapphics;
    ArrayList<EnemyController> enemyControllers;
    ArrayList<ItemController> itemControllers;

    public GameWindow() {
        // Set Game Window
        setVisible(true);
        setSize(screenW, screenH);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    isLeftPressed = true;
                }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isRightPressed = true;
                }else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    isUpPressed = true;
                }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    isDownPressed = true;
                }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacePressed = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    isLeftPressed = false;
                }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isRightPressed =false;
                }else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    isUpPressed = false;
                }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    isDownPressed = false;
                }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacePressed = false;
                }

            }
        });
        backbufferedImage = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_ARGB);
        backBufferedGapphics = backbufferedImage.getGraphics();
        // CR player
        playerController = new PlayerController(370, 530, Utils.loadImage("res/plane2.png") );
        /// CR item
        itemController = new ItemController(500, -1000 , Utils.loadImage("res/item.png"));
        //// CR enemy
        enemyControllers = new ArrayList<>();


        // load image
        backgroundImage = Utils.loadImage("res/background.png");

        // Game loop
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ///
                    if (!startEnemy) {
                        cooldown_startEnemy --;
                        if ( cooldown_startEnemy == 0) {
                            startEnemy = true;
                        }
                    }

                    if (startEnemy) {
                        enemyController3 = new EnemyController(0, 100, Utils.loadImage("res/enemy_plane_yellow_1.png"));
                        enemyControllers.add(enemyController3);
                        enemyController3.setMovebehavior(new Movebehavior());
                        enemyController2 = new EnemyController(0, 0, Utils.loadImage("res/island.png"));
                        enemyControllers.add(enemyController2);
                        enemyController2.setMovebehavior(new DiagonalMovebehavior());


                        for (int x = 0; x < 600; x += 150) {
                            enemyController = new EnemyController(x + 50, 0, Utils.loadImage("res/enemy_plane_white_3.png"));
                            if (x < 400) {
                                enemyController.setMovebehavior(new HorzMovebehavior());
                            } else {
                                enemyController.setMovebehavior(new HorzMovebehavior());

                            }
                            enemyControllers.add(enemyController);
                        }

                        startEnemy = false;
                        cooldown_startEnemy = 500;
                    }

                    //// LOGIC
                    playerController.updateBullet();
                    enemyController3.autoshoot();

                    for (EnemyController enemyController: enemyControllers) {
                        enemyController.automove();
                    }
                    CollisionManager.instance.update();
                    playerController.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed);
                    enemyController.updateEbullet();
                    playerController.update();
                    itemController.move();
                    repaint();
                }
            }
        });

        // repaint
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        backBufferedGapphics.drawImage(backgroundImage, 0, 0 , screenW, screenH, null);
        itemController.draw(backBufferedGapphics);
        playerController.draw(backBufferedGapphics);
        playerController.drawBullet(backBufferedGapphics);
        ArrayList<BulletEnemyController> bulletEnemyControllers = enemyController.getEnemybullets();
        //

        // xet va cham cua cac Enemy
        for ( EnemyController enemyController : enemyControllers) {
            enemyController.draw(backBufferedGapphics);
            enemyController.draw_Ebullet(backBufferedGapphics);
            Iterator<EnemyController> enemyIterator = enemyControllers.iterator();
            while (enemyIterator.hasNext()) {
                EnemyController enemyController1 = enemyIterator.next();
                if ( enemyController1.getGameRect().isDead()) {
                    enemyIterator.remove();
                }
            }
        }

        // xet va cham cua bullet Enemy
        Iterator<BulletEnemyController> bulletEnemyControllerIterator = bulletEnemyControllers.iterator();
         while (bulletEnemyControllerIterator.hasNext()) {
             BulletEnemyController bulletEnemyController = bulletEnemyControllerIterator.next();
             if ( bulletEnemyController.getGameRect().isDead()) {
                 bulletEnemyControllerIterator.remove();
             }
         }

         // xet va cham cua bullet
        Iterator<BulletPlayerController> bulletPlayerControllerIterator = PlayerController.bulletPlayerControllers.iterator();
         while (bulletPlayerControllerIterator.hasNext()) {
             BulletPlayerController bulletPlayerController = bulletPlayerControllerIterator.next();
             if (bulletPlayerController.getGameRect().isDead()) {
                 bulletPlayerControllerIterator.remove();
             }
         }
        // ve ra man hinh GW
        g.drawImage(backbufferedImage, 0, 0, null);

    }
}
