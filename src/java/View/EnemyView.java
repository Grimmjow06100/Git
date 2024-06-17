package View;

import Handler.MouseHandler;
import controller.EnemyController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class EnemyView extends View{

    Image image;
    EnemyController controller;


    public EnemyView(EnemyController c) {
        this.controller = c;
        View.viewList.add(this);

    }

    @Override
    public void update(GraphicsContext gc) {
        controller.updateEnnemy();
        render(gc);
        if(controller.getMustRemove())
            View.viewList.remove(this);
    }

    public void render(GraphicsContext gc){
        controller.getEnemy().render(gc);
    }


}
