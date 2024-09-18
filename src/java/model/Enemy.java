package model;

import View.AttackView;
import controller.BulletController;
import controller.EnemyBulletController;
import controller.PlayerController;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends GameObject{



    Random r=new Random();
    int choose= 0;
    int hp=100;
    boolean MustRemove;
    PlayerController player;
    private long lastShotTime = 0;
    private final long shootCooldown = 5000; // Cooldown de 10 secondes entre les tirs
    private final double shootingDistance = 300;






    public Enemy(double x, double y,PlayerController player){
        super(x,y);
        velX=5;
        velY=5;
        this.id=ID.ENEMY;
        this.player=player;
        GameObject.gameObjects.add(this);
    }


    public void update() {
        x+=velX;
        y+=velY;
        MustRemove=false;

        choose=r.nextInt(10);
        shootAtPlayer();

        for(GameObject b : GameObject.gameObjects) {
            if(b.getId()==ID.BLOCK && this.getBounds().intersects(b.getBounds())) {
                x+=(velX*5) * -1;
                y+=(velY*5) * -1;
                velX*=-1;
                velY*=-1;
            }else if(choose==0){
                velX=(r.nextInt(4 - -4) + -4);

                velY=(r.nextInt(4 - -4) + -4);
            }
            if(b.getId()==ID.BULLET && this.getBounds().intersects(b.getBounds())) {
                GameObject.gameObjects.remove(b);
                hp-=25;
                player.incrementMana(5);
            }
            if(b.getId()==ID.UlTIME && this.getBounds().intersects(b.getBounds())) {
                hp=-100;
            }
        }
        if (hp<=0) {
            GameObject.gameObjects.remove(this);
            MustRemove=true;
            player.EnemyKilled();
        }

    }

    public double getHp(){
        return hp;
    }

    private void shootAtPlayer() {
        long currentTime = System.currentTimeMillis();

        // Vérifiez si le cooldown est écoulé
        if (currentTime - lastShotTime < shootCooldown) {
            return; // Si le cooldown n'est pas écoulé, ne faites rien
        }

        Player playerObject = player.getPlayer();
        double playerX = playerObject.getX();
        double playerY = playerObject.getY();

        double deltaX = playerX - x;
        double deltaY = playerY - y;

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Vérifiez si le joueur est à portée de tir
        if (distance > shootingDistance) {
            return; // Si le joueur est hors de portée, ne faites rien
        }

        // Mettre à jour le temps du dernier tir
        lastShotTime = currentTime;


        EnemyBullet bullet = new EnemyBullet(x, y, playerX, playerY);
        EnemyBulletController bulletController = new EnemyBulletController(bullet);
        new AttackView(bulletController);
    }

    public void render(GraphicsContext gc){
        gc.setFill(Color.PURPLE);
        gc.fillOval(x, y, 30, 30);
    }

    public boolean getMustRemove(){
        return MustRemove;
    }




    @Override
    public Bounds getBounds() {
        return new Rectangle(x,y,30,30).getBoundsInLocal();
    }



}
