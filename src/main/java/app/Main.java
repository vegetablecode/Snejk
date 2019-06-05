
package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
        Pane pane = loader.load();

        Scene scene = new Scene(pane, 800, 600);

        primaryStage.setResizable(false);
        scene.getStylesheets().add("/styles/dark.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snejk");
        primaryStage.show();
    }
}