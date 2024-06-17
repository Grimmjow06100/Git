package model;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;


public abstract class GameObject {
    public enum ID{
        PLAYER,ENEMY,BULLET, BLOCK,UlTIME,ITEM
    }

    public static CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();

    protected double  x,y;
    protected double velX,velY;
    protected ID id;

    GameObject(double x,double y){
        this.x=x;
        this.y=y;
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }


    public abstract Bounds getBounds();

    public ID getId() {
        return id;
    }

}
