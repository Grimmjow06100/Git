package model;


import Handler.KeyHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;


public class Player extends GameObject {

    public enum Direction{
        up,down,left,right
    }

    public enum PlayerID{
        AMON,RAIJIN,BRUNO
    }
    private PlayerID playerId;
    private KeyHandler keyHandler;
    private Direction direction;
    private ArrayList<Image> Frame = new ArrayList<>();

    private boolean isMoving = false;

    public int spriteCounter=0;
     public boolean isDead=false;


    private int animationSpeed = 5; // Speed of animation
    private int animationCounter = 0; // Counter to control the animation speed

    public int mana=200;
    public int HP=200;
    public int speed=8;
    public int fireSpeed=800;
    private Timeline manaRegenTimeline;

    public Player(PlayerID id,KeyHandler keyHandler){
        super(100,100);
        this.id=ID.PLAYER;
        this.playerId=id;
        direction = Direction.left;
        this.keyHandler = keyHandler;
        velX=speed;
        velY=speed;
        GameObject.gameObjects.add(this);
        System.out.println(fireSpeed);

    }

    public PlayerID getPlayerId() {
        return playerId;
    }

    public void update() {
        isMoving=false;// Reset the moving flag
        x+=velX;
        y+=velY;
        collision();
        if (keyHandler.upPressed) {
            direction= Direction.up;
            velY = -speed;
            isMoving=true;
        }
        else if(!keyHandler.downPressed){
            velY=0;
        }
        if (keyHandler.downPressed) {
            direction= Direction.down;
            velY= speed;
            isMoving=true;
        }
        else if (!keyHandler.upPressed){
            velY=0;
        }
        if (keyHandler.leftPressed) {
            direction= Direction.left;
            velX = -speed;
            isMoving=true;

        }
        else if (!keyHandler.rightPressed){
            velX=0;
        }
        if (keyHandler.rightPressed) {
            direction= Direction.right;
            velX = speed;
            isMoving=true;

        }
        else if (!keyHandler.leftPressed){
            velX=0;
        }
        if(HP<=0){
            isDead=true;
        }
    }

    private void collision(){
        for(GameObject b : GameObject.gameObjects){
            if(b.getId()==ID.BLOCK && this.getBounds().intersects(b.getBounds())) {
                x+=velX*-1;
                y+=velY*-1;
            }
            if(b.getId()==ID.ENEMY && this.getBounds().intersects(b.getBounds())) {
                HP-=5;

            }
        }

    }

    public void setFireSpeed(int fireSpeed){
        this.fireSpeed=fireSpeed;
    }

    public int getMana(){
        return mana;
    }


    public void incrementMana(int mana){
        this.mana+=mana;
        this.mana = Math.min(this.mana, 200);
    }
    public void incrementHP(int HP){
        this.HP+=HP;
        this.HP = Math.min(this.HP, 200);
    }




    public Direction getDirection() {
        return direction;
    }

    public ArrayList<Image> getFrame() {
        return Frame;
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

    @Override
    public Bounds getBounds() {
        return new Rectangle(x,y,70,70).getBoundsInLocal();
    }
}
