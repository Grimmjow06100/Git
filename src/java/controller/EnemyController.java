package controller;

import model.Enemy;


public class EnemyController {

    Enemy ennemy;
    public EnemyController(Enemy ennemy){
        this.ennemy = ennemy;
    }

    public double getX(){
        return ennemy.getX();
    }
    public double getY(){
        return ennemy.getY();
    }

    public Enemy getEnemy(){return ennemy;}


    public void updateEnnemy(){
        ennemy.update();
    }
    public boolean getMustRemove(){
        return ennemy.getMustRemove();
    }

}
