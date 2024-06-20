import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EndGameWindow {
    public EndGameWindow(Game game, Stage stage, String message) {
        Label label = new Label(message);
        label.setStyle("-fx-font-size: 70px;");
        Button rejouer= new Button("Rejouer");
        Button quitter= new Button("Quitter");
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        HBox h1=new HBox(label);
        h1.setAlignment(Pos.CENTER);
        HBox h2=new HBox(20,rejouer,quitter);
        h2.setPadding(new Insets(20,20,20,20));
        h2.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(h1,h2);

        rejouer.setOnAction(e->{
            try {
                game.startNewGame(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        quitter.setOnAction(e->{
            stage.close();
        });


        Scene scene = new Scene(vbox, 960, 576);
        stage.setScene(scene);
        stage.show();
    }
}
