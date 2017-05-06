package gameframe;
import gameframe.scenes.GameScene;
import gameframe.scenes.MenuScene;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;


/**
 * Created by admin on 4/13/17.
 */
public class GameWindow extends Frame {


    private int screenW = 600;
    private int screenH = 700;
    BufferedImage backbufferedImage;
    Graphics backBufferedGapphics;

    public void setCurrentScene(GameScene currentScene) {
        this.currentScene = currentScene;
    }

    public  static  GameWindow instance;

    GameScene currentScene;
    public GameWindow() {

        // Set Game Window
        setVisible(true);
        setSize(screenW, screenH);
        currentScene = new MenuScene();
        instance = this;

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
                currentScene.keyPressed(e);
                }

            @Override
            public void keyReleased (KeyEvent e){
                currentScene.keyReleased(e);
            }
            });

        backbufferedImage = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_ARGB);
        backBufferedGapphics = backbufferedImage.getGraphics();

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
                    //// LOGIC
                    currentScene.update();
                    repaint();
                }
            }
        });

        // repaint
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        currentScene.draw(backBufferedGapphics);
        //backBufferedGapphics.drawImage(backgroundImage, 0, 0, screenW, screenH, null);

    // ve ra man hinh GW
        g.drawImage(backbufferedImage,0,0,null);

    }
}
