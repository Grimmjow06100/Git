package model;

import View.GamePanelView;

public class Camera {
    private double x,y;
    private double speed;

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public void update(GameObject object){
        x+=((object.getX()-x)-1000/2)*0.05f;
        y+=((object.getY()-y)-563/2)*0.05f;

        double maxX = GamePanelView.mapWidth - GamePanelView.ScreenWidth;
        double maxY = GamePanelView.mapHeight - GamePanelView.ScreenHeight;

        if(x<=0) x=0;
        if(x>= maxX){
            x=maxX;
        }
        if(y<=0) y=0;
        if(y>=maxY){
            y = maxY;
        }
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }
}
