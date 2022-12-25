package org.example.gui.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.exceptions.ClientException;

import java.io.IOException;

public class SceneHelper {
    private static final Scene[] scenes = new Scene[7];

    public static Scene getScene(int scene) throws ClientException {
        FXMLLoader loader = new FXMLLoader();
        if (scenes[scene] == null) {
            String resource;
            switch (scene) {
                case 0:
                    resource = "/layouts/WelcomeWindow.fxml";
                    break;
                case 1:
                    resource = "/layouts/AllRoomsWindow.fxml";
                    break;
                case 2:
                    resource = "/layouts/WaitRoomWindow.fxml";
                    break;
                case 3:
                    resource = "/layouts/PhraseWriteWindow.fxml";
                    break;
                case 4:
                    resource = "/layouts/PaintWindow.fxml";
                    break;
                case 5:
                    resource = "/layouts/WriteWindow.fxml";
                    break;
                case 6:
                    resource = "/layouts/ResultWindow.fxml";
                    break;
                default:
                    throw new ClientException("Scene not found.");
            }
            loader.setLocation(SceneHelper.class.getResource(resource));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new ClientException("Failed to load resource.");
            }
            scenes[scene] = new Scene(root);
        }
        return scenes[scene];
    }
}
