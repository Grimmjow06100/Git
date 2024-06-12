package controller;

import event.KeyHandler;
import javafx.scene.image.Image;
import model.PlayerModel;
import model.entity;
import model.entity.*;

import java.util.ArrayList;

public class PlayerController {
    private PlayerModel model;
    private KeyHandler keyHandler;

    public PlayerController(PlayerModel model, KeyHandler keyHandler) {
        this.model = model;
        this.keyHandler = keyHandler;
    }


    public void update(){
        model.setMoving(false);// Reset the moving flag
        double x = model.getX();
        double y = model.getY();
        double speed = model.getSpeed();

        if (keyHandler.upPressed) {
            model.setDirection(entity.Direction.up);
            y -= speed;
            model.setY(y);
            model.setMoving(true);
        }
        if (keyHandler.downPressed) {
            model.setDirection(entity.Direction.down);
            y += speed;
            model.setY(y);
            model.setMoving(true);
        }
        if (keyHandler.leftPressed) {
            model.setDirection(entity.Direction.left);
            x -= speed;
            model.setX(x);
            model.setMoving(true);
        }
        if (keyHandler.rightPressed) {
            model.setDirection(entity.Direction.right);
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

    public Direction getDirection() {
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
