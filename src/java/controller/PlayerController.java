package controller;

import Handler.KeyHandler;
import javafx.scene.image.Image;
import model.GameObject;
import model.Player;

import java.util.ArrayList;

public class PlayerController {
    private Player model;
    private KeyHandler keyHandler;

    public PlayerController(Player model, KeyHandler keyHandler) {
        this.model = model;
        this.keyHandler = keyHandler;
    }

    public Player getModel() {
        return model;
    }

    public void update(){
        model.setMoving(false);// Reset the moving flag
        double x = model.getX();
        double y = model.getY();
        double speed = model.getSpeed();
        collision();

        if (keyHandler.upPressed) {
            model.setDirection(Player.Direction.up);
            y -= speed;
            model.setY(y);
            model.setMoving(true);
        }
        if (keyHandler.downPressed) {
            model.setDirection(Player.Direction.down);
            y += speed;
            model.setY(y);
            model.setMoving(true);
        }
        if (keyHandler.leftPressed) {
            model.setDirection(Player.Direction.left);
            x -= speed;
            model.setX(x);
            model.setMoving(true);
        }
        if (keyHandler.rightPressed) {
            model.setDirection(Player.Direction.right);
            x += speed;
            model.setX(x);
            model.setMoving(true);
        }
    }


    public void handleDraw() {
        if (model.isMoving()) {
            model.incrementAnimationCounter();
            if (model.shouldUpdateSprite()) {
                model.incrementSpriteCounter();
                if (model.getSpriteCounter() >= model.getUpFrame().size()) {
                    model.resetSpriteCounter();
                }
                model.resetAnimationCounter();
            }
        } else {
            model.resetSpriteCounter();  // Reset to the first frame when not moving
        }
    }

    private void collision() {
        for(GameObject object : GameObject.objectList) {
            if(model.getBounds().intersects(object.getBounds())) {
                System.out.println("Collision");
                if (object.getId() == GameObject.ID.wall) {
                    model.setX(model.getX());
                    model.setY(model.getY());
                }
            }
        }
    }



    public Player.Direction getDirection() {
        return model.getDirection();
    }

    public ArrayList<Image> getUpFrame() {
        return model.getUpFrame();
    }

    public ArrayList<Image> getDownFrame() {
        return model.getDownFrame();
    }

    public ArrayList<Image> getLeftFrame() {
        return model.getLeftFrame();
    }

    public ArrayList<Image> getRightFrame() {
        return model.getRightFrame();
    }
     public boolean isMoving() {
         return model.isMoving();
     }
     public int getSpriteCounter() {
            return model.getSpriteCounter();
    }
    public void incrementSpriteCounter() {
            model.incrementSpriteCounter();
    }

    public double getX(){
        return model.getX();
    }

    public double getY(){
        return model.getY();
    }
}
