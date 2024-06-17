package model;

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






    public Enemy(double x, double y) {
        super(x,y);
        velX=5;
        velY=5;
        this.id=ID.ENEMY;
        GameObject.gameObjects.add(this);
    }


    public void render(GraphicsContext gc){
        gc.setFill(Color.RED);
        gc.fillRect(x, y, 50, 50);
    }


    public void update() {
        x+=velX;
        y+=velY;
        MustRemove=false;

        choose=r.nextInt(10);

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
            }
            if(b.getId()==ID.UlTIME && this.getBounds().intersects(b.getBounds())) {
                hp=-100;
            }
        }
        if (hp<=0) {
            GameObject.gameObjects.remove(this);
            MustRemove=true;
        }

    }

    public boolean getMustRemove(){
        return MustRemove;
    }




    @Override
    public Bounds getBounds() {
        return new Rectangle(x,y,30,30).getBoundsInLocal();
    }



}
