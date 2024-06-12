package View;



import event.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import controller.PlayerController;
import model.entity;


public class PlayerView {
    private PlayerController controller;
    private GamePanel game;

    public PlayerView(PlayerController controller, GamePanel game) {
        this.controller= controller;
        this.game = game;
    }


    public void update() {
        controller.update();
    }


    public void draw(GraphicsContext gc) {
        Image image = null;
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

        controller.handleDraw();

        gc.drawImage(image, controller.getX(), controller.getY(), game.getTileSize(), game.getTileSize());
    }
}

