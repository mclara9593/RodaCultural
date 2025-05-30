
package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class RodaCulturalApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\View\\Menu.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        primaryStage.setTitle("Roda Cultural");
        primaryStage.setScene(new Scene(root, 800, 600)); // Set desired width and height
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}