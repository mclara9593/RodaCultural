package View;

import Model.Media;
import Model.Others.Pessoa;
import Model.Others.Review;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static MenuUtils.SaveFile.save;
import static Model.Media.*;
import static View.ViewUtilitie.showInfoAlert;

public class Avaliacao extends VBox {

    private VBox conteudoFormulario;
    private List<Media> midias;
    private List<Pessoa> atores;
    private String path;
    private Media midiaSelecionada;
    private List<Review> avaliacoesTemporadas = new ArrayList<>();

    public Avaliacao(List<Media> midias, List<Pessoa> atores, String path, Button btnVoltar) {
        this.midias = midias;
        this.atores = atores;
        this.path = path;

        this.setSpacing(10);
        this.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Título
        Text title = new Text("⭐ AVALIAR MÍDIA");
        VBox titleBox = new VBox(title);
        titleBox.setId("mainTitle");
        titleBox.setStyle("-fx-alignment: center; -fx-background-color: #b98cdc;");
        titleBox.setPadding(new Insets(10));
        titleBox.setPrefSize(300, 60);

        // Seletor de tipo de mídia para busca
        ViewUtilitie.MediaTypeChooserPane typeChooser = new ViewUtilitie.MediaTypeChooserPane();

        // Conteúdo que muda
        conteudoFormulario = new VBox(10);
        conteudoFormulario.setPadding(new Insets(10));

        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(conteudoFormulario);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("custom-scroll-pane");

        // Comportamento ao selecionar tipo de mídia
        typeChooser.setOnChoiceMade(mediaType -> {
            conteudoFormulario.getChildren().clear();
            midiaSelecionada = null; // Reseta a mídia selecionada
            avaliacoesTemporadas.clear(); // Limpa avaliações anteriores

            switch (mediaType) {
                case "Movie":
                    mostrarBuscaFilme(midias, atores, btnVoltar);
                    break;
                case "Book":
                    mostrarBuscaLivro(midias, btnVoltar);
                    break;
                case "Show":
                    mostrarBuscaShow(midias, btnVoltar);
                    break;
            }
        });

        // Estrutura base da tela
        this.getChildren().addAll(titleBox, typeChooser, scrollPane);
    }

    private void mostrarBuscaFilme(List<Media> midias, List<Pessoa> atores, Button btnVoltar) {
        Label tipoMidia = new Label("Buscando filmes: ");
        tipoMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

        GridPane grid = criarGrid();
        ComboBox<String> combo = criarComboBox("TÍTULO", "GÊNERO", "DIRETOR", "ATOR", "ANO");
        grid.addRow(0, new Label("Buscar por:"), combo);

        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Digite a informação a respeito do filme");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            Media encontrada = searchMovie(midias, atores, combo.getValue(), campoBusca.getText());
            if (encontrada != null) {
                midiaSelecionada = encontrada;
                exibirFormularioAvaliacao(btnVoltar);
            } else {
                conteudoFormulario.getChildren().add(new Label("Nenhuma mídia encontrada!"));
            }
        });

        conteudoFormulario.getChildren().addAll(tipoMidia,grid, campoBusca, new HBox(10, btnBuscar, btnVoltar));
    }

    private void mostrarBuscaLivro(List<Media> midias, Button btnVoltar) {
        Label tipoMidia = new Label("Buscando livros: ");
        tipoMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

        GridPane grid = criarGrid();
        ComboBox<String> combo = criarComboBox("TÍTULO", "AUTOR", "GÊNERO", "ANO", "ISBN");
        grid.addRow(0, new Label("Buscar por:"), combo);

        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Digite a informação a respeito do livro");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            Media encontrada = searchBook(midias, combo.getValue(), campoBusca.getText());
            if (encontrada != null) {
                midiaSelecionada = encontrada;
                exibirFormularioAvaliacao(btnVoltar);
            } else {
                conteudoFormulario.getChildren().add(new  Label("Nenhuma mídia encontrada!"));
            }
        });

        conteudoFormulario.getChildren().addAll(tipoMidia,grid, campoBusca, new HBox(10, btnBuscar, btnVoltar));
    }

    private void mostrarBuscaShow(List<Media> midias, Button btnVoltar) {
        Label tipoMidia = new Label("Buscando séries: ");
        tipoMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

        GridPane grid = criarGrid();
        ComboBox<String> combo = criarComboBox("TÍTULO");
        grid.addRow(0, new Label("Buscar por:"), combo);

        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Digite o título da série.");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            Media encontrada = searchSeries(midias, campoBusca.getText());
            if (encontrada != null) {
                midiaSelecionada = encontrada;
                exibirFormularioAvaliacaoSerie(btnVoltar);
            } else {
                conteudoFormulario.getChildren().add(new Label("Nenhuma mídia encontrada!"));
            }
        });

        conteudoFormulario.getChildren().addAll(tipoMidia,grid, campoBusca, new HBox(10, btnBuscar, btnVoltar));
    }

    private void exibirFormularioAvaliacao(Button btnVoltar) {
        conteudoFormulario.getChildren().clear();

        if (midiaSelecionada == null) {
            conteudoFormulario.getChildren().add(new Label("Nenhuma mídia selecionada!"));
            return;
        }

        // Exibir título da mídia
        Label tituloMidia = new Label("Avaliando: " + midiaSelecionada.getTitle());
        tituloMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        // Formulário de avaliação
        GridPane gridAvaliacao = new GridPane();
        gridAvaliacao.setHgap(10);
        gridAvaliacao.setVgap(10);
        gridAvaliacao.setPadding(new Insets(20));

        // Campos de avaliação
        ComboBox<Integer> comboNota = new ComboBox<>();
        for (int i = 0; i <= 5; i++) {
            comboNota.getItems().add(i);
        }
        comboNota.setPromptText("Selecione a nota");

        TextArea campoComentario = new TextArea();
        campoComentario.setPromptText("Digite seu comentário...");
        campoComentario.setWrapText(true);
        campoComentario.setPrefRowCount(3);

        // Se já existir avaliação, preenche os campos
        if (midiaSelecionada.getReview() != null) {
            comboNota.setValue(midiaSelecionada.getReview().getStars());
            campoComentario.setText(midiaSelecionada.getReview().getNote());
        }

        gridAvaliacao.addRow(0, new Label("Nota (0-5):"), comboNota);
        gridAvaliacao.addRow(1, new Label("Comentário:"), campoComentario);

        Button btnSalvar = new Button("Salvar Avaliação");
        btnSalvar.setOnAction(e -> {
            if (comboNota.getValue() == null) {
                showErrorAlert("Selecione uma nota!");
                return;
            }

            // Atualiza a review
            Review review = new Review();
            review.setNote(campoComentario.getText());
            review.setStars(comboNota.getValue());
            midiaSelecionada.setReview(review);

            // Atualiza a lista de mídias (substitui a mídia antiga pela atualizada)
            int index = midias.indexOf(midiaSelecionada);
            if (index != -1) {
                midias.set(index, midiaSelecionada);
            }

            // Salva
            save(midias, path);

            showInfoAlert("Avaliação salva com sucesso!");
        });

        conteudoFormulario.getChildren().addAll(tituloMidia, gridAvaliacao, new HBox(10, btnSalvar, btnVoltar));
    }

    // NOVO MÉTODO PARA AVALIAÇÃO DE SÉRIES
    private void exibirFormularioAvaliacaoSerie(Button btnVoltar) {
        conteudoFormulario.getChildren().clear();

        if (midiaSelecionada == null) {
            conteudoFormulario.getChildren().add(new Label("Nenhuma mídia selecionada!"));
            return;
        }

        // Exibir título da mídia
        Label tituloMidia = new Label("Avaliando Série: " + midiaSelecionada.getTitle());
        tituloMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

        VBox formularioTemporadas = new VBox(10);
        formularioTemporadas.setPadding(new Insets(20));

        // Obter número de temporadas (supondo que a série tenha este método)
        int numTemporadas = 1;
        try {
            numTemporadas = ((Model.Show) midiaSelecionada).getSeasons_number();
        } catch (Exception e) {
            // Valor padrão se não conseguir obter
        }

        // Criar campos para cada temporada
        List<ComboBox<Integer>> combosNotas = new ArrayList<>();
        List<TextArea> camposComentarios = new ArrayList<>();

        for (int i = 1; i <= numTemporadas; i++) {
            Label lblTemporada = new Label("Temporada " + i);
            lblTemporada.setStyle("-fx-font-weight: bold; -fx-text-fill: #5a4a9a;");

            ComboBox<Integer> comboNota = new ComboBox<>();
            for (int j = 0; j <= 5; j++) {
                comboNota.getItems().add(j);
            }
            comboNota.setPromptText("Nota para temporada " + i);
            combosNotas.add(comboNota);

            TextArea campoComentario = new TextArea();
            campoComentario.setPromptText("Comentário para temporada " + i + "...");
            campoComentario.setWrapText(true);
            campoComentario.setPrefRowCount(2);
            camposComentarios.add(campoComentario);

            formularioTemporadas.getChildren().addAll(lblTemporada, comboNota, campoComentario);
        }

        Button btnSalvar = new Button("Salvar Avaliação da Série");
        int finalNumTemporadas = numTemporadas;
        btnSalvar.setOnAction(e -> {
            // Verificar se todas as temporadas foram avaliadas
            for (int i = 0; i < combosNotas.size(); i++) {
                if (combosNotas.get(i).getValue() == null) {
                    showErrorAlert("Selecione uma nota para a temporada " + (i + 1) + "!");
                    return;
                }
            }

            // Criar avaliações individuais para cada temporada
            avaliacoesTemporadas.clear();
            double somaNotas = 0;

            for (int i = 0; i < combosNotas.size(); i++) {
                Review reviewTemporada = new Review();
                reviewTemporada.setNote(camposComentarios.get(i).getText());
                reviewTemporada.setStars(combosNotas.get(i).getValue());
                avaliacoesTemporadas.add(reviewTemporada);

                somaNotas += reviewTemporada.getStars();
            }

            // Calcular média das temporadas
            int mediaNotas = (int) Math.round(somaNotas / finalNumTemporadas);

            // Criar avaliação geral da série (média)
            Review reviewGeral = new Review();
            reviewGeral.setStars(mediaNotas);
            reviewGeral.setNote("Avaliação calculada pela média das " + finalNumTemporadas + " temporadas");
            midiaSelecionada.setReview(reviewGeral);

            // Atualizar a lista de mídias
            int index = midias.indexOf(midiaSelecionada);
            if (index != -1) {
                midias.set(index, midiaSelecionada);
            }

            // Salvar
            save(midias, path);

            showInfoAlert("Avaliação da série salva com sucesso!\n" +
                    "Média geral: " + mediaNotas + "/5");
        });

        conteudoFormulario.getChildren().addAll(
                tituloMidia,
                formularioTemporadas,
                new HBox(10, btnSalvar, btnVoltar)
        );
    }

    // Métodos auxiliares (mantidos iguais)
    private GridPane criarGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        return grid;
    }

    private ComboBox<String> criarComboBox(String... opcoes) {
        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll(opcoes);
        combo.setPromptText("Selecione uma opção");
        combo.setPrefWidth(200);
        return combo;
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}