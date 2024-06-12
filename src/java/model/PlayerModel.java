package model;


import event.KeyHandler;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel extends entity {
    private Direction direction;
    private ArrayList<Image> upFrame = new ArrayList<>();
    private ArrayList<Image> downFrame = new ArrayList<>();
    private ArrayList<Image> leftFrame = new ArrayList<>();
    private ArrayList<Image> rightFrame = new ArrayList<>();
    private boolean isMoving = false;
    private int CharacterId;

    private int animationSpeed = 5; // Speed of animation
    private int animationCounter = 0; // Counter to control the animation speed

    public PlayerModel(int CharacterId) {
        this.CharacterId=CharacterId;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.left;
    }

    public void getPlayerImage() {
        try {
            for (int i = 1; i < 7; i++) {

                String path = String.format("/character%d/gun/up/character_sprite_%d-removebg-preview.png",CharacterId, i);
                upFrame.add(new Image(getClass().getResource(path).toString(), 50, 50, false, false));
            }
            for (int i = 1; i < 7; i++) {
                String path = String.format("/character%d/gun/down/character_sprite_%d-removebg-preview.png", CharacterId,i);
                downFrame.add(new Image(getClass().getResource(path).toString(), 50, 50, false, false));
            }
            for (int i = 1; i < 7; i++) {
                String path = String.format("/character%d/gun/left/character_sprite_%d-removebg-preview.png",CharacterId, i);
                leftFrame.add(new Image(getClass().getResource(path).toString(), 50, 50, false, false));
            }
            for (int i = 1; i < 7; i++) {
                String path = String.format("/character%d/gun/right/character_sprite_%d-removebg-preview.png",CharacterId, i);
                rightFrame.add(new Image(getClass().getResource(path).toString(), 50, 50, false, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setMoving(boolean b) {
        isMoving=b;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
