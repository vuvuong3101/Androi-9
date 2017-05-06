package gameframe.controller;

import gameframe.models.GameRect;
import gameframe.utils.Utils;
import gameframe.views.ImageRenderer;

import java.awt.*;

/**
 * Created by admin on 5/5/17.
 */
public class IslandController {
    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    public IslandController(int x, int y, Image image) {
        gameRect =  new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        imageRenderer =  new ImageRenderer(image);

    }
    public  void update() {
        gameRect.move(0, 1);
    }

}
