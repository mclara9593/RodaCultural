// In a new file, e.g., src/main/java/Controller/MainController.java
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class ControllerView {

    @FXML
    private void handleRegister(ActionEvent event) {
        try {
            String path = System.getProperty("user.dir") + "\\src\\main\\java\\View\\Menu.fxml";
            Parent registerRoot = FXMLLoader.load(getClass().getResource(path));
            Scene registerScene = new Scene(registerRoot);
            Stage currentStage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
            currentStage.setScene(registerScene);
            currentStage.setTitle("Cadastrar Mídia");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleReview(ActionEvent event) {
        // Logic for reviewing media
        System.out.println("Review button clicked!");
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        // Logic for reviewing media
        System.out.println("Review button clicked!");
    }

    @FXML
    private void handleList(ActionEvent event) {
        // Logic for reviewing media
        System.out.println("Review button clicked!");
    }

    @FXML
    private void handleMarkAsViewed(ActionEvent event) {
        // Logic for reviewing media
        System.out.println("Review button clicked!");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.out.println("Saindo do sistema...");
        System.exit(0);
    }
}