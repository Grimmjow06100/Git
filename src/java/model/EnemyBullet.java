package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyBullet extends GameObject {


    public EnemyBullet(double x, double y, double mx, double my) {
        super(x,y);
        velX= (mx - x) / 30;
        velY= (my - y) / 30;
        this.id= GameObject.ID.BULLET;
        GameObject.gameObjects.add(this);
    }

    public void  update() {
        x+=velX;
        y+=velY;
        if(getCollision()){
            GameObject.gameObjects.remove(this);
        }
    }

    public boolean getCollision(){
        for(GameObject go: GameObject.gameObjects){
            if(go.getId()== GameObject.ID.BLOCK){
                if(this.getBounds().intersects(go.getBounds())){
                    return true;
                }
            }
            if(go.getId()== GameObject.ID.PLAYER){
                if(this.getBounds().intersects(go.getBounds())){
                    ((Player) go).HP-=30;
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Bounds getBounds(){
        return new Rectangle(x,y,20,20).getBoundsInLocal();
    }

    public void render(GraphicsContext gc){
        gc.setFill(Color.RED);
        gc.fillOval(x, y, 10, 10);
    }
}
