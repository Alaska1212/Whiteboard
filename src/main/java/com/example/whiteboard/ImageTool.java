package com.example.whiteboard;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class ImageTool {

    private final WhiteboardCanvas whiteboard;
    private final Stage stage;
    private boolean imageMode = false;

    public ImageTool(WhiteboardCanvas whiteboard, Stage stage) {
        this.whiteboard = whiteboard;
        this.stage = stage;
    }

    public void toggleImageMode(Button btn) {
        imageMode = !imageMode;
        if (imageMode) {
            btn.setStyle("-fx-background-color: lightgreen;");
            whiteboard.disableDrawing();

            var canvas = whiteboard.getCanvas();
            var manager = whiteboard.getManager();

            canvas.setOnMousePressed(e -> {
                if (manager.startDrag(e.getX(), e.getY())) return;

                FileChooser chooser = new FileChooser();
                chooser.setTitle("Вибери зображення");
                chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

                File file = chooser.showOpenDialog(stage);
                if (file != null) {
                    Image img = new Image(file.toURI().toString());
                    manager.addImage(img, e.getX(), e.getY());
                }
            });

            canvas.setOnMouseDragged(e -> manager.moveDrag(e.getX(), e.getY()));
            canvas.setOnMouseReleased(e -> manager.endDrag());

        } else {
            btn.setStyle("");
            whiteboard.enableDrawing();
        }
    }
}