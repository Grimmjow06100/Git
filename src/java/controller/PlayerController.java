package controller;

import Handler.KeyHandler;
import javafx.scene.image.Image;
import model.Block;
import model.GameObject;
import model.Player;


import java.util.ArrayList;

public class PlayerController {
    private Player player;


    public PlayerController(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void updatePlayer(KeyHandler key){
        player.update();

    }






    public void handleDraw() {
        if (player.isMoving()) {
            player.incrementAnimationCounter();
            if (player.shouldUpdateSprite()) {
                player.incrementSpriteCounter();
                if (player.getSpriteCounter() >= player.getFrame().size()) {
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

    public ArrayList<Image> getFrame() {
        return player.getFrame();
    }

    public int getMana() {
        return player.getMana();
    }

    public void incrementMana(int mana) {
        player.incrementMana(mana);
    }

    public int getHP() {
        return player.HP;
    }

    public Player.PlayerID getPlayerID() {
        return player.getPlayerId();
    }



     public int getSpriteCounter() {
            return player.getSpriteCounter();
    }



    public double getX(){
        return player.getX();
    }

    public double getY(){
        return player.getY();
    }

    public void teleportPlayer(double x, double y){
        player.setY(y);
        player.setX(x);
    }

}
