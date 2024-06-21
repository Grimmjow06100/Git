package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import main.Game;
import model.Player;

import java.io.IOException;


public class CharacterPickController {
    @FXML
    ImageView AMON;
    @FXML
    ImageView BRUNO;
    @FXML
    ImageView RAIJIN;
    @FXML
    ImageView selectedCharacterView;
    @FXML
    ImageView PlayButton;

    @FXML
    Button parameter;

    public Player.PlayerID id;

    @FXML
    public void initialize(){
        setupDragAndDrop(AMON, Player.PlayerID.AMON);
        setupDragAndDrop(BRUNO, Player.PlayerID.BRUNO);
        setupDragAndDrop(RAIJIN, Player.PlayerID.RAIJIN);


        parameter.setOnAction(event -> {
            try {
                // Create a new FXMLLoader
                FXMLLoader loader = new FXMLLoader();

                // Set the location of the FXML file you want to load
                loader.setLocation(getClass().getResource("/FXML/parametres.fxml"));

                // Load the FXML file
                Parent root = loader.load();

                RegleControlleur controller=loader.getController();

                controller.initialize();

                // Create a new Scene with the loaded FXML file
                Scene scene = new Scene(root);

                // Get the current Stage
                Stage stage = (Stage) parameter.getScene().getWindow();

                // Set the new Scene on the Stage
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        PlayButton.setOnMouseClicked(event -> {
            if(id!=null) {
                Game game = new Game(id);
                Stage stage= (Stage) PlayButton.getScene().getWindow();
                try {
                    game.startNewGame(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        selectedCharacterView.setOnDragOver(event -> {
            if (event.getGestureSource() != selectedCharacterView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        selectedCharacterView.setOnDragDropped(event -> {
            System.out.println("Drag dropped event fired"); // Check if the event is being fired
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                selectedCharacterView.setImage(db.getImage());
                success = true;

                // Get the identifier from the dragboard
                String idString = db.getString();

                if(idString.equals(Player.PlayerID.AMON.name())){
                    id=Player.PlayerID.AMON;
                    System.out.println("AMON");
                }
                else if(idString.equals(Player.PlayerID.BRUNO.name())){
                    id=Player.PlayerID.BRUNO;
                    System.out.println("BRUNO");
                }
                else if(idString.equals(Player.PlayerID.RAIJIN.name())){
                    id=Player.PlayerID.RAIJIN;
                    System.out.println("RAIJIN");
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });


    }

    private void setupDragAndDrop(ImageView imageView, Player.PlayerID id) {
        imageView.setOnDragDetected(event -> {
            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);

            // Put the image on the dragboard
            db.setDragView(imageView.getImage());

            // Create a ClipboardContent
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();

            // Put the image and the identifier on the clipboard
            content.putImage(imageView.getImage());
            content.putString(id.name());

            db.setContent(content);

            event.consume();
        });

        imageView.setOnDragDone(DragEvent::consume);
    }
}
