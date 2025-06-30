package View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ViewUtilitie {
    public static class MediaTypeChooserPane extends VBox {

        private Consumer<String> onChoiceMade;
        private Button movieButton;
        private Button showButton;
        private Button bookButton;
        private Button backButton;

        public MediaTypeChooserPane() {
            this.setSpacing(10);
            this.setPadding(new Insets(20));
            this.setStyle("-fx-alignment: center;");

            Label title = new Label("Escolha o tipo de mídia:");
            title.setStyle("-fx-font-size: 18px;"); // Estilo direto ou via CSS

            movieButton = new Button("Filme");
            showButton = new Button("Série");
            bookButton = new Button("Livro");

            movieButton.setPrefWidth(180);
            showButton.setPrefWidth(180);
            bookButton.setPrefWidth(180);


            this.getChildren().addAll(title, movieButton, showButton, bookButton, createBackButton());

            movieButton.setOnAction(e -> handleChoice("Movie", e));
            showButton.setOnAction(e -> handleChoice("Show", e));
            bookButton.setOnAction(e -> handleChoice("Book", e));
        }

        public void setOnChoiceMade(Consumer<String> onChoiceMade) {
            this.onChoiceMade = onChoiceMade;
        }

        private void handleChoice(String mediaType, ActionEvent event) {
            if (onChoiceMade != null) {
                onChoiceMade.accept(mediaType);
            }
            // Lógica de navegação ou outra ação pode ser adicionada aqui
            // Similar ao que MediaTypeChooserController faz no FXML
        }

        private void handleBack(ActionEvent event) {
            // Lógica para voltar para a cena anterior
            // Isso precisaria ser genérico ou configurável.
            // Você poderia passar uma referência à cena principal, ou usar um NavigationService
            System.out.println("Voltar clicado.");
            // Exemplo:
            // Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            // currentStage.setScene(new Scene(new MainMenuPane())); // Se MainMenuPane for sua cena principal
        }
    }

    static Button createBackButton() {
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            // Volta para o menu principal
            Menu menu = new Menu();
            ViewUtilitie.handleMainChoice(menu, "Menu Principal", btnVoltar);
        });
        return btnVoltar;
    }


    //tipo de cena p cada botão
    public  static void handleMainChoice(Parent SceneType, String message, Button btn) {
        //cria uma cena
        Scene scene = new Scene(SceneType, 800, 600);
        scene.getStylesheets().add(ViewUtilitie.class.getResource("/estilo.css").toExternalForm());

        // obtém o Stage (janela) atual do botão que foi clicado
        Stage currentStage = (Stage) btn.getScene().getWindow(); // Obtém o Stage a partir do botão

        // Define a nova Scene no Stage
        currentStage.setScene(scene);

        // mudar o título da janela para refletir a nova tela
        currentStage.setTitle(message);

    }

    public static void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

