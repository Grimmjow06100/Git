package main;

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


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/chooseperso.fxml"));
        Parent root= loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}

