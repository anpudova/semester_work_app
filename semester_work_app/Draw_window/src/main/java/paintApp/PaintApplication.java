package paintApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PaintApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/paint.fxml"))));
        primaryStage.setTitle("Draw");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
