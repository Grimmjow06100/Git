package View;

import controller.ItemController;
import controller.PlayerController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Item;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ItemView extends View{
    ItemController controllerItem;
    PlayerController playerController;
    Image itemImage;

    public ItemView(ItemController controller, PlayerController playerController) {
        this.controllerItem = controller;
        this.playerController = playerController;
        View.viewList.add(this);
        LoadImage(controllerItem.getItemId());
    }

    @Override
    public void update(GraphicsContext gc) {
        controllerItem.update();
        renderItemImage(gc);
        if (controllerItem.getMustRemove()) {
            View.viewList.remove(this);
        }
    }

    public void renderItemImage(GraphicsContext gc){
        gc.drawImage(itemImage, controllerItem.getX(), controllerItem.getY(), 30, 30);
    }

    public void LoadImage(Item.ItemID id){
        String name=id.name();
        String path = String.format("/item/"+name+".png");
        itemImage = new Image(getClass().getResource(path).toString());
    }

}
