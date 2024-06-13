import View.GamePanelView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GamePanelView game=new GamePanelView();
        double width=game.getPrefWidth();
        double height=game.getPrefHeight();
        Scene scene = new Scene(game,width,height);
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

