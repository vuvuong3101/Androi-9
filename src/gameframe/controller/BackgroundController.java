package gameframe.controller;

import gameframe.models.GameRect;
import gameframe.views.ImageRenderer;

import java.awt.*;

/**
 * Created by admin on 5/5/17.
 */
public class BackgroundController {
    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    public BackgroundController(int x, int y, Image image) {
        gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        imageRenderer = new ImageRenderer(image);
    }

    public void update() {
        gameRect.move(0, 1);
        if (gameRect.getY() >700) {
            gameRect.setY(-700);
        }
    }

    public  void draw(Graphics graphics){
        imageRenderer.render(graphics, gameRect);
    }
}
