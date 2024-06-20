package View;

import controller.BulletController;
import controller.EnemyBulletController;
import controller.UltimeController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.Player;

public class AttackView extends View {
    BulletController controllerBullet=null;
    UltimeController controllerUltime=null;
    EnemyBulletController controllerEnnemyBullet=null;
    Image normaleBullet ;
    Image Ultime;
    Image enemyBullet;



    public AttackView(BulletController b, Player.PlayerID id) {
        this.controllerBullet =b;
        View.viewList.add(this);
        LoadImage(id);
    }

    public  AttackView(UltimeController c, Player.PlayerID id) {
        this.controllerUltime = c;
        View.viewList.add(this);
        LoadImage(id);
    }

    public AttackView(EnemyBulletController c) {
        this.controllerEnnemyBullet = c;
        View.viewList.add(this);
        LoadImage(Player.PlayerID.AMON);
    }


    @Override
    public void update(GraphicsContext gc) {
        if (controllerBullet != null){
            controllerBullet.update();
            renderNormaleBullet(gc);
            if (controllerBullet.getCollision()) {
                View.viewList.remove(this);

            }
        }
        else if(controllerUltime!=null) {
            controllerUltime.update();
            renderUltime(gc);
            if(controllerUltime.getMustRemove()) {
                View.viewList.remove(this);

            }
        }
        else if(controllerEnnemyBullet!=null){
            controllerEnnemyBullet.update();
            renderEnnemyBullet(gc);
            if(controllerEnnemyBullet.getCollision()){
                View.viewList.remove(this);
            }
        }
        else{
            System.out.println("Error");
        }
    }


    public void LoadImage(Player.PlayerID id){
        String name=id.name();
        String pathBullet = String.format("/"+name+"/normaleAttack/Attack.png");
        String pathUltime = String.format("/"+name+"/SuperAttack/Attack.png");
        String pathEnnemyBullet = String.format("/Enemy/Attack/EnemyAttack.png");
        normaleBullet = new Image(getClass().getResource(pathBullet).toString());
        Ultime = new Image(getClass().getResource(pathUltime).toString());
        enemyBullet=new Image(getClass().getResource(pathEnnemyBullet).toString());

    }



    public void renderNormaleBullet(GraphicsContext gc){
        gc.drawImage(normaleBullet, controllerBullet.getBulletX(), controllerBullet.getBulletY(), 50, 50);
    }
    public void renderUltime(GraphicsContext gc){
        gc.drawImage(Ultime, controllerUltime.getUltimeX(), controllerUltime.getUltimeY(), 200, 200);
    }
    public void renderEnnemyBullet(GraphicsContext gc){
        gc.drawImage(enemyBullet, controllerEnnemyBullet.getX(), controllerEnnemyBullet.getY(), 30, 30);
    }
}
