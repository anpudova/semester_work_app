package org.example.gui.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PaintWindowController {

    public TextField brushSize;
    public ColorPicker colorPicker;
    public CheckBox eraser;
    public Canvas canvas;
    public Label time;

    public void initialize() {
        GraphicsContext gContext = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(event -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;

            if (eraser.isSelected()) {
                gContext.clearRect(x, y, size, size);
            } else {
                gContext.setFill(colorPicker.getValue());
                gContext.fillOval(x, y, size, size);
            }
        });
    }

    public void onSave(ActionEvent actionEvent) {
        try {
            WritableImage snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("C:\\Users\\Raijin\\Desktop\\DrawImages\\image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
