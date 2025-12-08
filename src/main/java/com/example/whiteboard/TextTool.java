package com.example.whiteboard;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;

public class TextTool {

    private final WhiteboardCanvas whiteboard;
    private boolean textMode = false;

    public TextTool(WhiteboardCanvas whiteboard) {
        this.whiteboard = whiteboard;
    }

    public void toggleTextMode(Button btn) {
        textMode = !textMode;
        if (textMode) {
            btn.setStyle("-fx-background-color: lightblue;");
            whiteboard.disableDrawing();

            var canvas = whiteboard.getCanvas();
            var manager = whiteboard.getManager();

            canvas.setOnMousePressed(e -> {
                if (manager.startDrag(e.getX(), e.getY())) return;

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Введення тексту");
                dialog.setHeaderText("Введи текст для вставки:");

                dialog.showAndWait().ifPresent(input -> {
                    manager.addText(input, e.getX(), e.getY(), Color.BLACK);
                });
            });

            canvas.setOnMouseDragged(e -> manager.moveDrag(e.getX(), e.getY()));
            canvas.setOnMouseReleased(e -> manager.endDrag());

        } else {
            btn.setStyle("");
            whiteboard.enableDrawing();
        }
    }
}