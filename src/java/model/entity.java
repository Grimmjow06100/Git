package model;


import java.util.ArrayList;

import javafx.scene.image.Image;



public class entity {
    protected double  x,y;
    protected double speed;

    public enum Direction{
        up,down,left,right
    }

    public  ArrayList<Image> upFrame=new ArrayList<>();
    public  ArrayList<Image> downFrame=new ArrayList<>();
    public  ArrayList<Image> leftFrame=new ArrayList<>();
    public  ArrayList<Image> rightFrame=new ArrayList<>();



    public int spriteCounter=0;
    public int spriteNumber=1;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
     public double getSpeed() {
         return speed;
     }
}
