package controller;

import model.Bullet;

public class BulletController {
    Bullet bullet;

    public BulletController(Bullet bullet ){
        this.bullet = bullet;
    }

    public double getBulletX(){
        return bullet.getX();
    }

    public double getBulletY(){
        return bullet.getY();
    }

    public void update(){
        bullet.update();
    }

    public boolean getCollision(){
        return bullet.getCollision();
    }

}
