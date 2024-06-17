package controller;

import model.Enemy;


public class EnemyController {

    Enemy ennemy;
    public EnemyController(Enemy ennemy){
        this.ennemy = ennemy;
    }

    public double getEnemyX(){
        return ennemy.getX();
    }
    public double getEnemyY(){
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
