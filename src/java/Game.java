import Handler.KeyHandler;
import Handler.MouseHandler;
import View.*;
import controller.PlayerController;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Handler.CameraHandler;
import model.*;
import java.io.IOException;

public class Game {

    GraphicsContext gc ;
    CameraHandler camera;
    KeyHandler key;
    MouseHandler mouse;
    Image targetImage;
    PlayerController controller;
    GamePanelView GamePanel;
    PlayerView playerView;
    AnimationTimer gameLoop;


    public void startNewGame(Stage stage) throws IOException {


        InitGame();

        //Creation model
        Player player = new Player(Player.PlayerID.BRUNO, key);
        //Creation du controller
        controller = new PlayerController(player);

        //liaison du model avec le mouseHandler
        mouse.setController(controller, key);


        //Creation des vues
        GamePanel = new GamePanelView(controller, gc, mouse, key);
        playerView = new PlayerView(controller, key);


        //setup la scene de jeu
        String path1 = "/cible1.png";
        targetImage = new Image(getClass().getResource(path1).toString(), 50, 50, false, false);
        ImageCursor targetCursor = new ImageCursor(targetImage);

        double width = GamePanel.getPrefWidth();
        double height = GamePanel.getPrefHeight();
        Scene scene = new Scene(GamePanel, width, height);
        scene.setCursor(targetCursor);


        stage.setTitle("Mage vs Monsters");
        stage.setScene(scene);
        stage.show();
        GamePanel.requestFocus();


        gc = GamePanel.getGraphicsContext();
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(0.5));
        //Boucle de jeu
         gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Mettre à jour la map
                update(gc);
                if (player.isDead == true) {
                    pause.play();
                    pause.setOnFinished(e -> {
                        stop();
                        new EndGameWindow(Game.this, stage, "Game Over");

                    });
                } else if (player.EnemyNumber == 0) {

                    pause.play();
                    pause.setOnFinished(e -> {
                        stop();
                        new EndGameWindow(Game.this, stage, "You Win");

                    });
                }
            }
        };
        gameLoop.start();
    }

    public void InitGame() {
         camera=new CameraHandler(0,0);
         key=new KeyHandler();
         mouse=new MouseHandler(camera);
    }
    public void update(GraphicsContext gc) {
        camera.update(controller.getPlayer());

        gc.translate(-camera.getX(),-camera.getY());

        GamePanel.render();

        for(View view:View.viewList){
            view.update(gc);
        }
        gc.translate(camera.getX(),camera.getY());

        gc.setFill(Color.GREEN);
        gc.fillRect(5, 5, controller.getHP(),20);
        gc.setFill(Color.WHITE);
        gc.fillText("HP     : " + controller.getHP(),5,40);
        gc.setFill(Color.BLUE);
        gc.fillRect(210, 5, controller.getMana(),20);
        gc.setFill(Color.WHITE);
        gc.fillText("Mana : " + controller.getMana() , 210,40);
        gc.setFill(Color.RED);
        gc.fillRect(415, 5, controller.getEnemyNumber()*10,20);
        gc.setFill(Color.WHITE);
        gc.fillText("Enemy : " + controller.getEnemyNumber() , 415,40);

    }
}
