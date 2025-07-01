package Controller;

import Model.Media;
import View.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class RodaCulturalApp extends Application {

    public static List<Media> midias = new ArrayList<>();


    public static List<Media> getMidias() {
        return midias;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Menu root = new Menu();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("/estilo.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }


}
