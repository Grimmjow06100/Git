package controller;

import model.Bullet;
import model.EnemyBullet;

public class EnemyBulletController {
    EnemyBullet bullet;

    public EnemyBulletController(EnemyBullet bullet ){
        this.bullet = bullet;
    }

    public double getX(){
        return bullet.getX();
    }


    public  double getY(){
        return bullet.getY();
    }

    public void update(){
        bullet.update();
    }

    public boolean getCollision(){
        return bullet.getCollision();
    }
}
