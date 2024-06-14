package model;


import Handler.KeyHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Player extends GameObject {

    public enum Direction{
        up,down,left,right
    }

    private double speedX,speedY;
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
        speedY=5;
        speedX=5;

    }
    public void update(KeyHandler keyHandler){
        isMoving=false;// Reset the moving flag
        x+=speedX;
        y+=speedY;
        collision();
        if (keyHandler.upPressed) {
            direction= Direction.up;
            speedY = -5;
            isMoving=true;
        }
        else if(!keyHandler.downPressed){
            speedY=0;
        }
        if (keyHandler.downPressed) {
            direction= Direction.down;
            speedY= 5;
            isMoving=true;
        }
        else if (!keyHandler.upPressed){
            speedY=0;
        }
        if (keyHandler.leftPressed) {
            direction= Direction.left;
            speedX = -5;
            isMoving=true;

        }
        else if (!keyHandler.rightPressed){
            speedX=0;
        }
        if (keyHandler.rightPressed) {
            direction= Direction.right;
            speedX = 5;
            isMoving=true;

        }
        else if (!keyHandler.leftPressed){
            speedX=0;
        }
    }

    private void collision() {
        for(GameObject object : GameObject.objectList) {
            if(object!= this && this.getBounds().intersects(object.getBounds())) {
                if (object.getId() == GameObject.ID.wall) {
                    x+=speedX*-1;
                    y+=speedY*-1;

                }
            }
        }

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

    public double getSpeedY() {
        return speedY;
    }
    public double getSpeedX() {
        return speedX;
    }
    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }
    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    @Override
    public Bounds getBounds() {
        return new Rectangle(x,y,50,50).getBoundsInLocal();
    }
}
