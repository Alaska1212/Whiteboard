package com.example.demo_board;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class WhiteboardCanvas {

    private final WhiteboardManager manager;
    private final Canvas canvas;

    private double brushSize = 4;
    private Color brushColor = Color.BLACK;

    public WhiteboardCanvas(double width, double height) {
        this.canvas = new Canvas(width, height);
        this.manager = new WhiteboardManager(canvas);
        enableDrawing();
    }

    public void enableDrawing() {
        canvas.setOnMousePressed(e -> draw(e.getX(), e.getY()));
        canvas.setOnMouseDragged(e -> draw(e.getX(), e.getY()));
    }

    public void disableDrawing() {
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
    }

    private void draw(double x, double y) {
        manager.addPoint(x, y, brushSize, brushColor);
    }

    public void setBrushColor(Color color) {
        this.brushColor = color;
    }

    public void setBrushSize(double size) {
        this.brushSize = size;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void clear() {
        manager.clearAll();
    }

    public WhiteboardManager getManager() {
        return manager;
    }
}