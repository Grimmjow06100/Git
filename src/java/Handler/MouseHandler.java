package Handler;

import View.AttackView;
import View.View;
import controller.BulletController;
import controller.PlayerController;
import controller.UltimeController;
import model.*;


import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.util.LinkedList;

public class MouseHandler{
    CameraHandler camera;
    PlayerController controller;
    KeyHandler key;

    public double x,y;

    public MouseHandler(CameraHandler camera){
        this.camera=camera;
    }


    public EventHandler<MouseEvent> getOnMouseClicked() {
        return event -> {
            x = event.getX() + camera.getX();
            y = event.getY() + camera.getY();
            Player p= controller.getPlayer();
            if(key.APressed){
                if(controller.getMana() >= 100){
                    Ultime u= new Ultime(p.getX(), p.getY(), x, y);
                    UltimeController uController = new UltimeController(u);
                    new AttackView(uController,controller.getPlayerID());
                    controller.incrementMana(-100);

                }
                else {
                    System.out.println("Not enough mana");
                }
            }
            else {
                if (controller.getMana() >= 10) {
                    Bullet b = new Bullet(p.getX(), p.getY(), x, y);
                    BulletController bController = new BulletController(b);
                    new AttackView(bController,controller.getPlayerID());
                    controller.incrementMana(-10);

                }
                else System.out.println("Not enough mana");
            }

        };
    }


    public void setController(PlayerController controller,KeyHandler keyHandler) {
        this.controller = controller;
        this.key= keyHandler;
    }





}
