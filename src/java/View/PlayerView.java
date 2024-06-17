package View;



import Handler.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import controller.PlayerController;
import javafx.scene.image.ImageView;
import model.Player;


public class PlayerView extends View {
    private PlayerController controller;
    private KeyHandler key;
    private Image image;

    public PlayerView(PlayerController controller, KeyHandler key) {
        this.controller = controller;
        this.key = key;
        View.viewList.add(this);
        LoadImage(controller.getPlayerID());
    }

    @Override
    public void update(GraphicsContext gc) {
        controller.updatePlayer(key);
        render(gc);
    }


    public void render(GraphicsContext gc) {
        controller.handleDraw();
        image = controller.getFrame().get(controller.getSpriteCounter());
        gc.drawImage(image, controller.getX(), controller.getY(), 70, 70);
    }


    public void LoadImage(Player.PlayerID id) {
        String name=id.name();
        for (int i = 1; i < 6; i++) {
            String path = String.format("/"+name+"/mage%d.png", i);

            controller.getFrame().add(new Image(getClass().getResource(path).toString()));
        }
    }
}


