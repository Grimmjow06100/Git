package model;


import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Player extends GameObject {

    public enum Direction{
        up,down,left,right
    }

    private double speed;
    private Direction direction;
    private ArrayList<Image> upFrame = new ArrayList<>();
    private ArrayList<Image> downFrame = new ArrayList<>();
    private ArrayList<Image> leftFrame = new ArrayList<>();
    private ArrayList<Image> rightFrame = new ArrayList<>();
    private boolean isMoving = false;

    public int spriteCounter=0;
    public int spriteNumber=1;


    private int animationSpeed = 5; // Speed of animation
    private int animationCounter = 0; // Counter to control the animation speed

    public Player() {
        super(100,100,ID.player);
        direction = Direction.left;
        speed=4;

    }




    public Direction getDirection() {
        return direction;
    }

    public ArrayList<Image> getUpFrame() {
        return upFrame;
    }

    public ArrayList<Image> getDownFrame() {
        return downFrame;
    }

    public ArrayList<Image> getLeftFrame() {
        return leftFrame;
    }

    public ArrayList<Image> getRightFrame() {
        return rightFrame;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void incrementAnimationCounter() {
        animationCounter++;
    }

    public boolean shouldUpdateSprite() {
        return animationCounter > animationSpeed;
    }

    public void resetAnimationCounter() {
        animationCounter = 0;
    }

    public void resetSpriteCounter() {
        spriteCounter = 0;
    }

    public void incrementSpriteCounter() {
        spriteCounter++;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }


    public void setMoving(boolean b) {
        isMoving=b;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ID getId() {
        return id;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public Bounds getBounds() {
        return new Rectangle(x,y,50,50).getBoundsInLocal();
    }
}
