package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ultime extends GameObject{
    boolean MustRemove;
    private long creationTime;
    public Ultime(double x, double y,double mx, double my) {
        super(x,y);
        velX= (mx - x) / 20;
        velY= (my - y) / 20;
        this.id= ID.UlTIME;
        GameObject.gameObjects.add(this);
        this.creationTime = System.currentTimeMillis();
    }

    public void  update() {
        MustRemove=false;
        x+=velX;
        y+=velY;
        long currentTime = System.currentTimeMillis();
        if (currentTime - creationTime > 3000) {
            MustRemove = true;
            GameObject.gameObjects.remove(this);
        }
    }

    public boolean getMustRemove(){
        return MustRemove;
    }

    @Override
    public Bounds getBounds(){
        return new Rectangle(x,y,200,200).getBoundsInLocal();
    }
}
