package View;

import controller.EnemyController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class EnemyView extends View{

    Image EnemyImage=new Image(getClass().getResource("/Enemy/mimic.png").toString());
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
        gc.drawImage(EnemyImage, controller.getX(), controller.getY(), 50, 50);
    }


}
