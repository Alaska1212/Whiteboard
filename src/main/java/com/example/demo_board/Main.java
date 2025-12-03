package com.example.demo_board;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        WhiteboardCanvas whiteboard = new WhiteboardCanvas(800, 600);
        FileManager fileManager = new FileManager();
        TextTool textTool = new TextTool(whiteboard);
        ImageTool imageTool = new ImageTool(whiteboard, stage);

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        Slider sizeSlider = new Slider(1, 20, 4);
        Button clearButton = new Button("Очистити");
        Button textButton = new Button("Текст");
        Button imageButton = new Button("Зображення");
        Button saveButton = new Button("Зберегти");

        HBox controls = new HBox(10,
                new Label("Колір:"), colorPicker,
                new Label("Розмір:"), sizeSlider,
                textButton, imageButton, clearButton, saveButton);
        controls.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(whiteboard.getCanvas());

        colorPicker.setOnAction(e -> whiteboard.setBrushColor(colorPicker.getValue()));
        sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> whiteboard.setBrushSize(newVal.doubleValue()));
        clearButton.setOnAction(e -> whiteboard.clear());
        textButton.setOnAction(e -> textTool.toggleTextMode(textButton));
        imageButton.setOnAction(e -> imageTool.toggleImageMode(imageButton));
        saveButton.setOnAction(e -> fileManager.saveAsPng(stage, whiteboard.getCanvas()));

        Scene scene = new Scene(root);
        stage.setTitle("Біла дошка — JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}