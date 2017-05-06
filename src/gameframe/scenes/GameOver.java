package gameframe.scenes;

import gameframe.GameWindow;
import gameframe.models.GameRect;
import gameframe.utils.Utils;
import gameframe.views.ImageRenderer;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by admin on 5/5/17.
 */
public class GameOver  implements  GameScene{
   Image gameover;
    public GameOver (){
        gameover = Utils.loadImage("res/gameover.png");
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameWindow.instance.setCurrentScene( new Level1scene());

        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.exit(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(gameover, 0, 0, null);
    }

    @Override
    public void update() {

    }
}
