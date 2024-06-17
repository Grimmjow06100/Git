package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends GameObject{

    boolean MustRemove;
    public Bullet(double x, double y,double mx, double my) {
        super(x,y);
        velX= (mx - x) / 10;
        velY= (my - y) / 10;
        this.id=ID.BULLET;
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
            if(go.getId()==ID.BLOCK){
                if(this.getBounds().intersects(go.getBounds())){
                    return true;
                }
            }
        }
        return false;
    }




    @Override
    public Bounds getBounds(){
        return new Rectangle(x,y,50,50).getBoundsInLocal();
    }

    public void render(GraphicsContext gc){
        gc.setFill(Color.RED);
        gc.fillOval(x, y, 10, 10);
    }
}
