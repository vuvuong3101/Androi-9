package gameframe.controller;

import gameframe.models.GameRect;
import gameframe.views.ImageRenderer;

import java.awt.*;

/**
 * Created by admin on 4/26/17.
 */
public class Controller {
    protected GameRect gameRect;
    protected ImageRenderer imageRenderer;

    public Controller() {
    }

    public Controller(GameRect gameRect, ImageRenderer imageRenderer) {
        this.gameRect = gameRect;
        this.imageRenderer = imageRenderer;
    }

    public  void draw(Graphics graphics) {
        if (gameRect.isInvisible()) return; ;
        imageRenderer.render(graphics, gameRect);
    }


    public GameRect getGameRect() {

        return gameRect;
    }

    public  void update() {

    }
}
