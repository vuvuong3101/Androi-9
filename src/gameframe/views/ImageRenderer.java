package gameframe.views;

import gameframe.models.GameRect;
import gameframe.utils.Utils;

import java.awt.*;

/**
 * Created by admin on 4/15/17.
 */
public class ImageRenderer {
    private Image image;

    public ImageRenderer(Image image) {
        this.image = image;
    }
    public ImageRenderer (String path) {
        this(Utils.loadImage(path));
    }
    public  void render(Graphics graphics, GameRect gameRect) {
        graphics.drawImage(image,
                gameRect.getX(),
                gameRect.getY(),
                gameRect.getWidth(),
                gameRect.getHeight(),
                null);
    }
}
