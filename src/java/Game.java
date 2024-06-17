import Handler.DragAndDropHandler;
import Handler.KeyHandler;
import Handler.MouseHandler;
import View.*;
import controller.PlayerController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Handler.CameraHandler;
import model.*;
import java.io.IOException;

public class Game extends Application {

    GraphicsContext gc ;
    CameraHandler camera;
    KeyHandler key;
    MouseHandler mouse;
    Image targetImage;
    PlayerController controller;
    GamePanelView game;
    PlayerView playerView;
    DragAndDropHandler dragAndDropHandler;

    @Override
    public void start(Stage stage) throws IOException {
        InitGame();

        //Creation model
        Player player=new Player(Player.PlayerID.RAIJIN,key);

        //Creation du controller
        controller=new PlayerController(player);

        //liaison du model avec le mouseHandler
        mouse.setController(controller,key);





        //Creation des vues
        game = new GamePanelView(controller,gc,mouse,key);
        playerView=new PlayerView(controller,key);




        //setup la scene de jeu
        String path1="/cible1.png";
        targetImage = new Image(getClass().getResource(path1).toString(), 50, 50, false, false);
        ImageCursor targetCursor = new ImageCursor(targetImage);

        double width = game.getPrefWidth();
        double height = game.getPrefHeight();
        Scene scene = new Scene(game, width, height);
        scene.setCursor(targetCursor);
        // Créer et ajouter les gestionnaires de drag and drop
        dragAndDropHandler = new DragAndDropHandler(controller, camera);
        dragAndDropHandler.addDragAndDropHandlers(scene);


        stage.setTitle("Mage vs Monsters");
        stage.setScene(scene);
        stage.show();
        game.requestFocus();



        gc=game.getGraphicsContext();
        //Boucle de jeu
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Mettre à jour la map
                update(gc);
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

        game.render();

        for(View view:View.viewList){
            view.update(gc);
        }
        gc.translate(camera.getX(),camera.getY());

        gc.setFill(Color.GREEN);
        gc.fillRect(5, 5, controller.getHP(),20);
        gc.setFill(Color.WHITE);
        gc.fillText("HP: " + controller.getHP() , 5,40 );
        gc.setFill(Color.BLUE);
        gc.fillRect(5, 55, controller.getMana(),20);
        gc.setFill(Color.WHITE);
        gc.fillText("Mana: " + controller.getMana() , 5,90 );
    }


    public static void main(String[] args) {
        launch(args);
    }
}
