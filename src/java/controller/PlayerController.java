package controller;

import Handler.KeyHandler;
import javafx.scene.image.Image;
import model.Player;


import java.util.ArrayList;

public class PlayerController {
    private Player player;


    public PlayerController(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(KeyHandler key){
        player.update(key);
    }


    public void handleDraw() {
        if (player.isMoving()) {
            player.incrementAnimationCounter();
            if (player.shouldUpdateSprite()) {
                player.incrementSpriteCounter();
                if (player.getSpriteCounter() >= player.getUpFrame().size()) {
                    player.resetSpriteCounter();
                }
                player.resetAnimationCounter();
            }
        } else {
            player.resetSpriteCounter();  // Reset to the first frame when not moving
        }
    }





    public Player.Direction getDirection() {
        return player.getDirection();
    }

    public ArrayList<Image> getUpFrame() {
        return player.getUpFrame();
    }

    public ArrayList<Image> getDownFrame() {
        return player.getDownFrame();
    }

    public ArrayList<Image> getLeftFrame() {
        return player.getLeftFrame();
    }

    public ArrayList<Image> getRightFrame() {
        return player.getRightFrame();
    }
     public boolean isMoving() {
         return player.isMoving();
     }
     public int getSpriteCounter() {
            return player.getSpriteCounter();
    }
    public void incrementSpriteCounter() {
            player.incrementSpriteCounter();
    }

    public double getX(){
        return player.getX();
    }

    public double getY(){
        return player.getY();
    }
}
