package View;



import Handler.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import controller.PlayerController;
import model.GameObject;


public class PlayerView {
    private PlayerController controller;
    private GamePanelView game;
    private KeyHandler key;

    public PlayerView(PlayerController controller, GamePanelView game, int CharacterId, KeyHandler key) {
        this.controller= controller;
        this.game = game;
        getPlayerImage(CharacterId);
        GameObject.objectList.add(controller.getPlayer());
        this.key = key;
    }


    public void update() {
        controller.update(key);
    }


    public void render(GraphicsContext gc) {
        Image image = null;
        controller.handleDraw();
        switch (controller.getDirection()) {
            case up:
                image = controller.getUpFrame().get(controller.getSpriteCounter());
                break;
            case down:
                image = controller.getDownFrame().get(controller.getSpriteCounter());
                break;
            case left:
                image = controller.getLeftFrame().get(controller.getSpriteCounter());
                break;
            case right:
                image = controller.getRightFrame().get(controller.getSpriteCounter());
                break;
        }

        gc.drawImage(image, controller.getX(), controller.getY(), 50, 50);
    }

    public void getPlayerImage(int CharacterId){
        try {
            for (int i = 1; i < 7; i++) {

                String path = String.format("/character%d/gun/up/character_sprite_%d-removebg-preview.png",CharacterId, i);
                controller.getUpFrame().add(new Image(getClass().getResource(path).toString()));
            }
            for (int i = 1; i < 7; i++) {
                String path = String.format("/character%d/gun/down/character_sprite_%d-removebg-preview.png", CharacterId,i);
                controller.getDownFrame().add(new Image(getClass().getResource(path).toString()));
            }
            for (int i = 1; i < 7; i++) {
                String path = String.format("/character%d/gun/left/character_sprite_%d-removebg-preview.png",CharacterId, i);
                controller.getLeftFrame().add(new Image(getClass().getResource(path).toString()));
            }
            for (int i = 1; i < 7; i++) {
                String path = String.format("/character%d/gun/right/character_sprite_%d-removebg-preview.png",CharacterId, i);
                controller.getRightFrame().add(new Image(getClass().getResource(path).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

