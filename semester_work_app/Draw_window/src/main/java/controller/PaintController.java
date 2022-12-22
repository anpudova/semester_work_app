package controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.StrokeLineCap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PaintController {

    @FXML
    private Canvas canvas;

    @FXML
    private TextField brushSize;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox eraser;

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

//        canvas.setOnMousePressed(event -> {
//            double size = Double.parseDouble(brushSize.getText());
//            gContext.setStroke(colorPicker.getValue());
//            gContext.setLineWidth(size);
//            gContext.setLineCap(StrokeLineCap.ROUND);
//            gContext.beginPath();
//            gContext.lineTo(event.getX(), event.getY());
//            gContext.stroke();
//            if (eraser.isSelected()) {
//                gContext.clearRect(event.getX(), event.getY(), size, size);
//            } else {
//                gContext.lineTo(event.getX(), event.getY());
//                gContext.stroke();
//            }
//        });
//
//        canvas.setOnMouseDragged(event -> {
//            double size = Double.parseDouble(brushSize.getText());
//            if (eraser.isSelected()) {
//                gContext.clearRect(event.getX(), event.getY(), size, size);
//            } else {
//                gContext.lineTo(event.getX(), event.getY());
//                gContext.stroke();
//            }
//        });

    }

    public void onSave() {
        try {
            WritableImage snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("C:\\Users\\Raijin\\Desktop\\DrawImages\\image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onExit() {
        Platform.exit();
    }

}
