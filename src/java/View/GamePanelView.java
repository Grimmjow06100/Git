package View;

import controller.PlayerController;
import Handler.KeyHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import jdk.jfr.internal.tool.Main;
import model.Camera;
import model.GameObject;
import model.Player;
import model.Block;


public class GamePanelView extends Pane {

    public final static  int ScreenWidth = 960;
    public final  static int ScreenHeight = 576;
    public final static int mapWidth = 3200;
    public final static int mapHeight = 3200;
    public final static int blockSize = 50;

    Image map=new Image(getClass().getResource("/Map/GameMap.png").toString(),mapWidth,mapHeight,false,false);



    KeyHandler key=new KeyHandler();
    Canvas canva;
    GraphicsContext gc;
    Camera camera;


    Player model=new Player();
    PlayerController controller=new PlayerController(model, key);
    PlayerView view=new PlayerView(controller,this,1);

    public GamePanelView() {
        this.setPrefSize(ScreenWidth, ScreenHeight);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
        canva = new Canvas(ScreenWidth, ScreenHeight);
        gc = canva.getGraphicsContext2D();
        camera=new Camera(0,0);
        this.getChildren().add(canva);
        setOnKeyPressed(key.getOnKeyPressedHandler());
        setOnKeyReleased(key.getOnKeyReleasedHandler());
        loadMap(map);



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
        camera.update(GameObject.objectList.get(0));
    }

    public void loadMap(Image image) {
        int w = (int) image.getWidth();
        int h = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();

        for (int x = 0; x < w; x += blockSize) {
            for (int y = 0; y < h; y += blockSize) {
                if (isBlockRed(pixelReader, x, y)) {
                    Block wall = new Block(x, y, GameObject.ID.wall);
                    GameObject.objectList.add(wall);
                }
            }
        }
        System.out.println(GameObject.objectList.size());
    }

    private boolean isBlockRed(PixelReader pixelReader, int startX, int startY) {
        for (int x = startX; x < startX + blockSize && x < mapWidth; x++) {
            for (int y = startY; y < startY + blockSize && y < mapHeight; y++) {
                int pixel = pixelReader.getArgb(x, y);
                int red = (pixel >> 16) & 0x000000FF;
                int green = (pixel >> 8) & 0x000000FF;
                int blue = (pixel) & 0x000000FF;
                if (red == 255 && green == 0 && blue == 0) {
                    return true; // Si un des pixels du bloc est rouge, on considère le bloc comme rouge
                }
            }
        }
        return false;
    }

    // DRAW : THE SCREEN
    public void paint(GraphicsContext gc) {
        // Effacer le canvas
        gc.clearRect(0, 0, ScreenWidth, ScreenHeight);

        gc.translate(-camera.getX(),-camera.getY());

        // Dessiner la carte à sa taille d'origine mais elle sera mise à l'échelle par la transformation
        if (map != null) {
            gc.drawImage(map, 0, 0);
        }


        view.render(gc);

        gc.translate(camera.getX(),camera.getY());
    }




    public static void main(String[] args){
        Main.main(args);
    }
}
