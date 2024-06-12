package View;

import controller.PlayerController;
import event.KeyHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.PlayerModel;


public class GamePanel extends Pane {
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int ScreenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler key=new KeyHandler();
    Canvas canva=new Canvas(ScreenWidth,screenHeight);
    GraphicsContext gc=canva.getGraphicsContext2D();
    double playerSpeed=4;
    double playerY=100;
    double playerX=100;

    PlayerModel model=new PlayerModel(4);
    PlayerController controller=new PlayerController(model, key);
    PlayerView view=new PlayerView(controller,this);

    public GamePanel() {
        this.setPrefSize(ScreenWidth, screenHeight);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        canva = new Canvas(ScreenWidth, screenHeight);
        gc = canva.getGraphicsContext2D();
        this.getChildren().add(canva);
        setOnKeyPressed(key.getOnKeyPressedHandler());
        setOnKeyReleased(key.getOnKeyReleasedHandler());


    }

    public void startGameThread() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Mettre à jour les informations du jeu
                update();

                // Dessiner l'écran
                paint(gc);
            }
        };
        gameLoop.start();
    }


    public void update() {
        view.update();
    }

    // DRAW : THE SCREEN
    public void paint(GraphicsContext gc) {
        // Effacer le canvas
        gc.clearRect(0, 0, ScreenWidth, screenHeight);
        view.draw(gc);
    }


    public int getTileSize() {
        return tileSize;
    }
}
