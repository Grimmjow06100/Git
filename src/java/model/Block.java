package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends GameObject{
    public Block(double x, double y, ID id ) {
        super(x, y,ID.wall);
    }

    public void render(GraphicsContext gc){
        gc.setFill(Color.BLUE);
        gc.fillRect(x,y,50,50);
    }


    @Override
    public Bounds getBounds(){
        return new Rectangle(x,y,50,50).getBoundsInLocal();
    }


}
