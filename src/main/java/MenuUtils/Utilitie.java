package MenuUtils;

import Model.Books;
import Model.Media;
import Model.Movie;
import Model.Show;
import View.Menu;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
/**
 * Classe utilitária com métodos auxiliares para navegação e manipulação de dados.
 */
public class Utilitie {

    /**
     * Painel para seleção de tipo de mídia.
     */
    public static class MediaTypeChooserPane extends VBox {
        private Consumer<String> onChoiceMade;
        private Button movieButton;
        private Button showButton;
        private Button bookButton;

        /**
         * Constrói o painel de seleção de tipo de mídia.
         */
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
        /**
         * Define o callback para quando uma escolha é feita.
         * @param onChoiceMade Consumer que recebe o tipo de mídia selecionado
         */
        public void setOnChoiceMade(Consumer<String> onChoiceMade) {
            this.onChoiceMade = onChoiceMade;
        }


        private void handleChoice(String mediaType, ActionEvent event) {
            if (onChoiceMade != null) {
                onChoiceMade.accept(mediaType);
            }
        }

    }
    /**
     * Cria um botão de voltar configurado.
     * @return Botão de voltar
     */
    public static Button createBackButton() {
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            // Volta para o menu principal
            Menu menu = new Menu();
            Utilitie.handleMainChoice(menu, "Menu Principal", btnVoltar);
        });
        return btnVoltar;
    }


    /**
     * Manipula a navegação para uma nova cena.
     * @param SceneType Tipo de cena a ser exibida
     * @param message Título da janela
     * @param btn Botão que acionou a navegação
     */
    public  static void handleMainChoice(Parent SceneType, String message, Button btn) {
        //cria uma cena
        Scene scene = new Scene(SceneType, 800, 600);
        scene.getStylesheets().add(Utilitie.class.getResource("/estilo.css").toExternalForm());

        // obtém o Stage (janela) atual do botão que foi clicado
        Stage currentStage = (Stage) btn.getScene().getWindow(); // Obtém o Stage a partir do botão

        // Define a nova Scene no Stage
        currentStage.setScene(scene);

        // mudar o título da janela para refletir a nova tela
        currentStage.setTitle(message);

    }

    /**
     * Exibe um alerta de informação.
     * @param message Mensagem a ser exibida
     */
    public static void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Filtra a lista de mídias por tipo.
     * @param midias Lista completa de mídias
     * @param tipo Tipo de mídia (B: Livro, M: Filme, S: Série)
     * @return Lista filtrada de mídias
     */
    public static List<Media> getLists(List<Media> midias, String tipo) {
        return midias.stream()
                .filter(m -> {
                    if (tipo.equals("B")) return m instanceof Books;
                    if (tipo.equals("M")) return m instanceof Movie;
                    if (tipo.equals("S")) return m instanceof Show;
                    return false;
                })
                .collect(Collectors.toList());
    }

}

