package gameframe.items;

import gameframe.controller.Controller;
import gameframe.models.GameRect;
import gameframe.views.ImageRenderer;

import java.awt.*;

/**
 * Created by admin on 5/5/17.
 */
public class Islands extends Controller{
    private GameRect gameRect;
    private ImageRenderer imageRenderer;
    public Islands(int x, int y, Image image) {
        gameRect =  new GameRect( x, y, image.getWidth(null), image.getHeight(null));
        imageRenderer =  new ImageRenderer(image);

    }

    public  void draw(Graphics graphics) {
        imageRenderer.render(graphics, gameRect);
    }

    public  void update() {
        gameRect.move(0, 1);
    }
}
