package Handler;

import View.AttackView;
import controller.BulletController;
import controller.PlayerController;
import controller.UltimeController;
import model.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;



public class MouseHandler{
    CameraHandler camera;
    PlayerController controller;
    KeyHandler key;

    public double x,y;
    private long lastShotTime = 0;


    public MouseHandler(CameraHandler camera){
        this.camera=camera;
    }


    public EventHandler<MouseEvent> getOnMouseClicked() {
        return event -> {
            long currentTime = System.currentTimeMillis();

            long coolTime=controller.getFireSpeed();


            // Vérifiez si le cooldown est écoulé
            if (currentTime - lastShotTime <coolTime){
                return; // Si le cooldown n'est pas écoulé, ne faites rien
            }

            // Mettre à jour le temps du dernier tir
            lastShotTime = currentTime;

            x = event.getX() + camera.getX();
            y = event.getY() + camera.getY();
            Player p = controller.getPlayer();

            if (key.APressed) {
                if (controller.getMana() >= 100) {
                    Ultime u = new Ultime(p.getX(), p.getY(), x, y);
                    UltimeController uController = new UltimeController(u);
                    new AttackView(uController, controller.getPlayerID());
                    controller.incrementMana(-100);
                } else {
                    System.out.println("Not enough mana");
                }
            } else {
                Bullet b = new Bullet(p.getX(), p.getY(), x, y);
                BulletController bController = new BulletController(b);
                new AttackView(bController, controller.getPlayerID());
            }
        };
    }


    public void setController(PlayerController controller,KeyHandler keyHandler) {
        this.controller = controller;
        this.key= keyHandler;
    }





}
