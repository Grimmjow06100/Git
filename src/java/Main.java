import View.GamePanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GamePanel game=new GamePanel();
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

