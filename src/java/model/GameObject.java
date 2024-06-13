package model;


import java.util.ArrayList;

import javafx.geometry.Bounds;


public abstract class GameObject {
    protected double  x,y;
    protected ID id;
    public static ArrayList<GameObject> objectList = new ArrayList<GameObject>();



    public GameObject(double x, double y,ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }



    public enum ID{
        player,enemy,wall,item,bullet;
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

    public ID getId() {
        return id;
    }

    public abstract Bounds getBounds();
}
