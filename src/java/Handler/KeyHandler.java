package Handler;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
    public boolean upPressed, downPressed, rightPressed, leftPressed;
    public boolean APressed;

    public EventHandler<KeyEvent> getOnKeyPressedHandler() {
        return event -> {
            if (event.getCode() == KeyCode.Q) {
                leftPressed = true;

            }
            if (event.getCode() == KeyCode.D) {
                rightPressed = true;

            }
            if (event.getCode() == KeyCode.Z) {
                upPressed = true;

            }
            if (event.getCode() == KeyCode.S) {
                downPressed = true;

            }
            if (event.getCode() == KeyCode.A) {
                APressed = true;
            }
        };
    }

    public EventHandler<KeyEvent> getOnKeyReleasedHandler() {
        return event -> {
            if (event.getCode() == KeyCode.Q) {
                leftPressed = false;
            }
            if (event.getCode() == KeyCode.D) {
                rightPressed = false;
            }
            if (event.getCode() == KeyCode.Z) {
                upPressed = false;
            }
            if (event.getCode() == KeyCode.S) {
                downPressed = false;
            }
            if (event.getCode() == KeyCode.A) {
                APressed = false;
            }
        };
    }
}

