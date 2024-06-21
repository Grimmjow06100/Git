package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RegleControlleur {

    @FXML
    Button btnRetour;


    public void initialize() {
        btnRetour.setOnAction(event -> {
            try {
                // Create a new FXMLLoader
                FXMLLoader loader = new FXMLLoader();

                // Set the location of the FXML file you want to load
                loader.setLocation(getClass().getResource("/chooseperso.fxml"));

                // Load the FXML file
                Parent root = loader.load();

                CharacterPickController controller = loader.getController();
                controller.initialize();

                // Create a new Scene with the loaded FXML file
                Scene scene = new Scene(root);

                // Get the current Stage
                Stage stage = (Stage) btnRetour.getScene().getWindow();

                // Set the new Scene on the Stage
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

