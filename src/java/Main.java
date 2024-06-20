
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Mage vs Monsters");
        Game game = new Game();
        game.startNewGame(stage);
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}

