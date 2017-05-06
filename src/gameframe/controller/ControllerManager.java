package gameframe.controller;

import gameframe.enemies.EnemyController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by admin on 5/3/17.
 */
public class ControllerManager {
    public ArrayList<Controller> getControllers() {
        return controllers;
    }
    private ArrayList<Controller> controllers;
    public  static final ControllerManager instance =  new ControllerManager();
    private ControllerManager() {
        controllers =  new ArrayList<>();
    }

    public  void add (Controller controller) {
        controllers.add(controller);
    }

    public  void draw(Graphics graphics) {
        for ( Controller controller : controllers){
            controller.draw(graphics);
        }
    }

    public  void update() {
        // remove object
        Iterator<Controller> iterator = controllers.iterator();
        while (iterator.hasNext()) {
            Controller controller =  iterator.next();
            if (controller.getGameRect().isDead()) {
                iterator.remove();
            }
        }
        for ( Controller controller : controllers) {
            controller.update();
        }

    }

    public  void automove() {

    }

    public  void run () {

    }
}
