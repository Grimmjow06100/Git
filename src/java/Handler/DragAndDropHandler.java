package Handler;


import controller.PlayerController;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.input.DragEvent;

public class DragAndDropHandler {
    private PlayerController controller;
    private CameraHandler camera;

    public DragAndDropHandler(PlayerController controller, CameraHandler camera) {
        this.controller = controller;
        this.camera = camera;
    }

    public void addDragAndDropHandlers(Scene scene) {
        scene.setOnDragDetected(event -> {
            Dragboard db = scene.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("Dragging");
            db.setContent(content);
            event.consume();
        });

        scene.setOnDragOver(event -> {
            if (event.getGestureSource() != scene && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        scene.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                double x = event.getX() + camera.getX();
                double y = event.getY() + camera.getY();
                controller.teleportPlayer(x, y);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }
}

