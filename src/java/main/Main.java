package main;

import controller.CharacterPickController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Mage vs Monsters");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chooseperso.fxml"));
        Parent root= loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/characterPick.fxml"));
        Parent root= loader.load();
        CharacterPickController controller=loader.getController();
        controller.initialize();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}

