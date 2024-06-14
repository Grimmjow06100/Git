import View.GamePanelView;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GamePanelView game=new GamePanelView();
        double width=game.getPrefWidth();
        double height=game.getPrefHeight();

        // Charger l'image de la cible
        Image targetImage = new Image(getClass().getResource("/cible.png").toString(),50, 50, false, false);

        // Créer un ImageCursor avec l'image de la cible
        ImageCursor targetCursor = new ImageCursor(targetImage);

        Scene scene = new Scene(game,width,height);
        scene.setCursor(targetCursor);


        stage.setTitle("ZombieMania");
        stage.setScene(scene);
        stage.show();
        game.requestFocus();
        game.startGameThread();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

