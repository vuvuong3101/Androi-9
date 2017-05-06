package gameframe.scenes;

import gameframe.GameWindow;
import gameframe.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by admin on 5/3/17.
 */
public class MenuScene implements GameScene {
    Image menuBG;

    public MenuScene() {
        menuBG = Utils.loadImage("res/menu.png");
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameWindow.instance.setCurrentScene( new Level1scene());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBG, 0, 0, null);
    }

    @Override
    public void update() {

    }


}
