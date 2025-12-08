package com.example.whiteboard;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class WhiteboardManager {

    private final Canvas canvas;
    private final GraphicsContext gc;

    private final List<DrawPoint> points = new ArrayList<>();
    private final List<TextItem> texts = new ArrayList<>();
    private final List<ImageItem> images = new ArrayList<>();

    private Object selected = null;
    private double offsetX;
    private double offsetY;

    public WhiteboardManager(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void addPoint(double x, double y, double size, Color color) {
        points.add(new DrawPoint(x, y, size, color));
        redrawAll();
    }

    public void addText(String text, double x, double y, Color color) {
        texts.add(new TextItem(text, x, y, color));
        redrawAll();
    }

    public void addImage(Image img, double x, double y) {
        images.add(new ImageItem(img, x, y, img.getWidth() / 2, img.getHeight() / 2));
        redrawAll();
    }

    public void clearAll() {
        points.clear();
        texts.clear();
        images.clear();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void redrawAll() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (DrawPoint p : points) {
            gc.setFill(p.color);
            gc.fillOval(p.x, p.y, p.size, p.size);
        }

        for (ImageItem img : images) {
            gc.drawImage(img.image, img.x, img.y, img.width, img.height);
        }

        for (TextItem t : texts) {
            gc.setFill(t.color);
            gc.fillText(t.text, t.x, t.y);
        }
    }

    public boolean startDrag(double x, double y) {
        for (ImageItem img : images) {
            if (img.contains(x, y)) {
                selected = img;
                offsetX = x - img.x;
                offsetY = y - img.y;
                return true;
            }
        }

        for (TextItem t : texts) {
            if (t.contains(x, y)) {
                selected = t;
                offsetX = x - t.x;
                offsetY = y - t.y;
                return true;
            }
        }
        return false;
    }

    public void moveDrag(double x, double y) {
        if (selected == null) return;

        if (selected instanceof ImageItem img) {
            img.x = x - offsetX;
            img.y = y - offsetY;
        } else if (selected instanceof TextItem t) {
            t.x = x - offsetX;
            t.y = y - offsetY;
        }
        redrawAll();
    }

    public void endDrag() {
        selected = null;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private static class DrawPoint {
        double x, y, size;
        Color color;
        DrawPoint(double x, double y, double size, Color color) {
            this.x = x; this.y = y; this.size = size; this.color = color;
        }
    }

    public static class TextItem {
        String text;
        double x, y;
        Color color;

        TextItem(String text, double x, double y, Color color) {
            this.text = text; this.x = x; this.y = y; this.color = color;
        }

        boolean contains(double mx, double my) {
            double width = text.length() * 7;
            double height = 15;
            return mx >= x && mx <= x + width && my <= y && my >= y - height;
        }
    }

    public static class ImageItem {
        Image image;
        double x, y, width, height;

        ImageItem(Image image, double x, double y, double width, double height) {
            this.image = image; this.x = x; this.y = y; this.width = width; this.height = height;
        }

        boolean contains(double mx, double my) {
            return mx >= x && mx <= x + width && my >= y && my <= y + height;
        }
    }
}
