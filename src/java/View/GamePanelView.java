package View;

import Handler.MouseHandler;
import Handler.KeyHandler;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import controller.ItemController;
import controller.PlayerController;
import controller.EnemyController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Pane;
import model.*;
import model.GameObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanelView extends Pane {

    public final static int ScreenWidth = 960;
    public final static int ScreenHeight = 576;
    public final static int mapWidth = 3200;
    public final static int mapHeight = 3200;
    public final static int blockSize = 50;

    Image mapLoader = new Image(getClass().getResource("/Map/GameMap.png").toString());
    Image map = new Image(getClass().getResource("/Map/GameMap.png").toString(), mapWidth, mapHeight, false, false);
    Image floorTexture = new Image(getClass().getResource("/Map/floorTexture.png").toString(), blockSize, blockSize, false, false);
    Image wallTexture = new Image(getClass().getResource("/Map/wallTexture.png").toString(), blockSize, blockSize, false, false);

    KeyHandler key;
    MouseHandler mouse;
    GraphicsContext gc;
    PlayerController controller;
    Canvas canvas;


    public GamePanelView(PlayerController c, GraphicsContext gc, MouseHandler mouse, KeyHandler key){
        this.setPrefSize(ScreenWidth, ScreenHeight);
        this.gc = gc;
        this.mouse = mouse;
        this.key = key;
        this.controller = c;

        canvas = new Canvas(ScreenWidth,ScreenHeight);
        this.gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);

        setOnKeyPressed(key.getOnKeyPressedHandler());
        setOnKeyReleased(key.getOnKeyReleasedHandler());
        setOnMouseClicked(mouse.getOnMouseClicked());

        loadWall(mapLoader);
        placeEnemies(controller.getEnemyNumber());
        placeItems(10);

    }

    public void render() {
        gc.clearRect(0, 0, ScreenWidth, ScreenHeight);
        gc.drawImage(map, 0, 0, mapWidth, mapHeight);
        loadFloor(mapLoader);
    }

    private void loadWall(Image image) {
        int w = (int) image.getWidth();
        int h = (int) image.getHeight();
        PixelReader pixelReader = image.getPixelReader();
        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy ++) {
                   int pixel = pixelReader.getArgb(xx, yy);
                   int red = (pixel >> 16) & 0x000000FF;
                   int green = (pixel >> 8) & 0x000000FF;
                   int blue = (pixel) & 0x000000FF;
                     if (red == 255 && green == 0 && blue == 0)
                         new Block(xx*blockSize, yy*blockSize);

            }
        }
    }
    private void loadFloor(Image image) {
        int w = (int) image.getWidth();
        int h = (int) 40;
        PixelReader pixelReader = image.getPixelReader();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = pixelReader.getArgb(xx, yy);
                int red = (pixel >> 16) & 0x000000FF;
                int green = (pixel >> 8) & 0x000000FF;
                int blue = (pixel) & 0x000000FF;

                if (red == 0 && green == 0 && blue == 0) {
                    gc.drawImage(floorTexture, xx * blockSize, yy * blockSize, blockSize, blockSize);
                }
                else {
                    gc.drawImage(wallTexture, xx * blockSize, yy * blockSize, blockSize, blockSize);
                }

            }
        }
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

    public void placeEnemies(int numEnemies) {
        Random rand = new Random();
        int w = (int) mapWidth;
        int h = (int) mapHeight/2;
        PixelReader pixelReader = map.getPixelReader();

        for (int i = 0; i < numEnemies; i++) {
            int x, y;
            do {
                x = rand.nextInt(w);
                y = rand.nextInt(h);
            } while (isBlockRed(pixelReader, x, y) || isEnemyNearby(x, y));

            Enemy e = new Enemy(x, y, controller);
            EnemyController c = new EnemyController(e);
            new EnemyView(c);
        }
    }

    public void placeItems(int numItems) {
        Random rand = new Random();
        int w = (int) mapWidth;
        int h = (int) mapHeight/2 ;
        PixelReader pixelReader = map.getPixelReader();

        for (int i = 0; i < numItems; i++) {
            int x, y;
            do {
                x = rand.nextInt(w);
                y = rand.nextInt(h);
            } while (isBlockRed(pixelReader, x, y) || isItemNearby(x, y));
            Item.ItemID it = Item.ItemID.values()[rand.nextInt(Item.ItemID.values().length)];
            Item item = new Item(x, y, it);
            ItemController c = new ItemController(item);
            new ItemView(c, controller);
        }
    }

    private boolean isEnemyNearby(int x, int y) {
        for (GameObject obj : GameObject.gameObjects) {
            if (obj.getId() == GameObject.ID.ENEMY || obj.getId() == GameObject.ID.PLAYER){
                int deltaX = (int) Math.abs(obj.getX() - x);
                int deltaY = (int) Math.abs(obj.getY() - y);
                if (deltaX < blockSize * 6 && deltaY < blockSize * 6) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isItemNearby(int x, int y) {
        for (GameObject obj : GameObject.gameObjects) {
            if (obj.getId() == GameObject.ID.ITEM) {
                Item potion = (Item) obj;
                int deltaX = (int) Math.abs(potion.getX() - x);
                int deltaY = (int) Math.abs(potion.getY() - y);
                if (deltaX < blockSize * 4 && deltaY < blockSize * 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public Image getMap(){
        return map;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}
