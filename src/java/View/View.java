package View;

import javafx.scene.canvas.GraphicsContext;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class View {

    public static CopyOnWriteArrayList<View> viewList = new CopyOnWriteArrayList<>();

    public abstract void update(GraphicsContext gc );


}
