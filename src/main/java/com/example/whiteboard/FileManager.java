package com.example.whiteboard;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FileManager {

    public void saveAsPng(Stage stage, Canvas canvas) {
        WritableImage image = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());

        SnapshotParameters params = new SnapshotParameters();
        canvas.snapshot(params, image);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Зберегти як PNG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}