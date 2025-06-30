package View;

import Model.*;
import Model.Others.Pessoa;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;
import java.util.function.Function;

import static Model.Media.*;

public class Busca extends VBox {

    private VBox conteudoFormulario;
    private TextField campoBusca;

    public Busca(List<Media> midias, List<Pessoa> atores, Button btnVoltar) {
        this.setSpacing(10);
        this.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Título
        Text title = new Text("🔍 BUSCA");
        VBox titleBox = new VBox(title);
        titleBox.setId("mainTitle");
        titleBox.setStyle("-fx-alignment: center; -fx-background-color: #b98cdc;");
        titleBox.setPadding(new Insets(10));
        titleBox.setPrefSize(300, 60);

        // Seletor de tipo de mídia
        ViewUtilitie.MediaTypeChooserPane typeChooser = new ViewUtilitie.MediaTypeChooserPane();

        // Conteúdo que muda
        conteudoFormulario = new VBox(10);
        conteudoFormulario.setPadding(new Insets(10));
        conteudoFormulario.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 15;");

        // Campo de busca
        campoBusca = new TextField();
        campoBusca.setPromptText("Digite a informação aqui");
        campoBusca.setPrefWidth(300);

        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(conteudoFormulario);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("custom-scroll-pane");

        // Comportamento ao selecionar tipo de mídia
        typeChooser.setOnChoiceMade(mediaType -> {
            conteudoFormulario.getChildren().clear();

            switch (mediaType) {
                case "Movie":
                    mostrarFormularioFilme(midias, atores, btnVoltar,mediaType);
                    break;
                case "Book":
                    mostrarFormularioLivro(midias, btnVoltar,mediaType);
                    break;
                case "Show":
                    mostrarFormularioShow(midias, btnVoltar,mediaType);
                    break;
            }
        });

        // Estrutura base da tela
        this.getChildren().addAll(titleBox, typeChooser, scrollPane);
    }

    private void mostrarFormularioFilme(List<Media> midias, List<Pessoa> atores, Button btnVoltar,String mediaType) {
        GridPane grid = criarGrid();
        ComboBox<String> combo = criarComboBox("TÍTULO", "GÊNERO", "DIRETOR", "ATOR", "ANO");
        grid.addRow(0, new Label("Buscar por:"), combo);

        Label tipoMidia = new Label("Buscando filmes: ");
        tipoMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            Media encontrada = searchMovie(midias, atores, combo.getValue(), campoBusca.getText());
            exibirDetalhesMidia(encontrada,mediaType);
        });

        conteudoFormulario.getChildren().addAll(tipoMidia,grid, campoBusca, new HBox(10, btnBuscar, btnVoltar));
    }

    private void mostrarFormularioLivro(List<Media> midias, Button btnVoltar,String mediaType) {
        GridPane grid = criarGrid();
        ComboBox<String> combo = criarComboBox("TÍTULO", "AUTOR", "GÊNERO", "ANO", "ISBN");
        grid.addRow(0, new Label("Buscar por:"), combo);

        Label tipoMidia = new Label("Buscando livros: ");
        tipoMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            Media encontrada = searchBook(midias, combo.getValue(), campoBusca.getText());
            exibirDetalhesMidia(encontrada, mediaType);
        });

        conteudoFormulario.getChildren().addAll(tipoMidia,grid, campoBusca, new HBox(10, btnBuscar, btnVoltar));
    }

    private void mostrarFormularioShow(List<Media> midias, Button btnVoltar,String mediaType) {
        GridPane grid = criarGrid();
        ComboBox<String> combo = criarComboBox("TÍTULO");
        grid.addRow(0, new Label("Buscar por:"), combo);

        Label tipoMidia = new Label("Buscando séries: ");
        tipoMidia.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> {
            Media encontrada = searchSeries(midias, campoBusca.getText().toUpperCase());
            exibirDetalhesMidia(encontrada,mediaType);
        });

        conteudoFormulario.getChildren().addAll(tipoMidia,combo, campoBusca, new HBox(10, btnBuscar, btnVoltar));
    }

    private void exibirDetalhesMidia(Media midia,String mediaType) {
        conteudoFormulario.getChildren().clear();

        if (midia == null) {
            conteudoFormulario.getChildren().add(new Label("Nenhuma mídia encontrada!"));
            return;
        }

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: #e8ddf7; -fx-background-radius: 10;");

        // Função auxiliar para lidar com valores nulos
        Function<Object, String> safeValue = obj -> {
            if (obj == null) return "N/A";
            if (obj instanceof Pessoa) return ((Pessoa) obj).getNome();
            return obj.toString();
        };

        // Campos comuns
        int row = 0;
        grid.addRow(row++, criarLabelConteudo("Título:"), criarLabelConteudo(midia.getTitle()));
        grid.addRow(row++, criarLabelConteudo("Status:"), criarLabelConteudo(midia.isStatus() ? "Concluído" : "Não concluído"));
        //grid.addRow(row++, criarLabelConteudo("Gênero:"), criarLabelConteudo(midia.getGender().getDescricao()));
        grid.addRow(row++, criarLabelConteudo("Autor:"), criarLabelConteudo(safeValue.apply(midia.getAuthor())));
        grid.addRow(row++, criarLabelConteudo("Ano de Lançamento:"), criarLabelConteudo(String.valueOf(midia.getRelease_date())));

        // Verificar se existe review antes de acessar
        if (midia.getReview() != null) {
            grid.addRow(row++, criarLabelConteudo("Avaliação:"), criarLabelConteudo(String.valueOf(midia.getReview().getStars())));
            grid.addRow(row++, criarLabelConteudo("Comentário:"), criarLabelConteudo(midia.getReview().getNote()));
        } else {
            grid.addRow(row++, criarLabelConteudo("Avaliação:"), criarLabelConteudo("N/A"));
            grid.addRow(row++, criarLabelConteudo("Comentário:"), criarLabelConteudo("Nenhum comentário"));
        }

        // Campos específicos com tratamento de null
        if ( mediaType.equals("Movie") && midia instanceof Movie ) {
            Movie filme = (Movie) midia;
            grid.addRow(row++, criarLabelConteudo("Duração (min):"), criarLabelConteudo(String.valueOf(filme.getDuration())));
            grid.addRow(row++, criarLabelConteudo("Diretor:"), criarLabelConteudo(safeValue.apply(filme.getDirector())));
            grid.addRow(row++, criarLabelConteudo("Roteiro:"), criarLabelConteudo(filme.getScript() != null ? filme.getScript() : "N/A"));
            grid.addRow(row++, criarLabelConteudo("Atores:"), criarLabelConteudo(formatarAtores(filme.getCast())));
            grid.addRow(row++, criarLabelConteudo("Onde encontrar:"), criarLabelConteudo(filme.getOnde() != null ? filme.getOnde() : "N/A"));
        }
        else if (mediaType.equals("Book") && midia instanceof Books) {
            Books livro = (Books) midia;
            grid.addRow(row++, criarLabelConteudo("ISBN:"), criarLabelConteudo(String.valueOf(livro.getISBN())));
            grid.addRow(row++, criarLabelConteudo("Cópia Física:"), criarLabelConteudo(livro.getCopy() ? "Sim" : "Não"));
            grid.addRow(row++, criarLabelConteudo("Editora:"), criarLabelConteudo(livro.getPublisher() != null ? livro.getPublisher() : "N/A"));
        }
        else if (mediaType.equals("Show") && midia instanceof Show) {
            Show serie = (Show) midia;
            grid.addRow(row++, criarLabelConteudo("Ano de Encerramento:"), criarLabelConteudo(String.valueOf(serie.getFinal_date())));
            grid.addRow(row++, criarLabelConteudo("Temporadas:"), criarLabelConteudo(String.valueOf(serie.getSeasons_number())));
            grid.addRow(row++, criarLabelConteudo("Onde encontrar:"), criarLabelConteudo(serie.getOnde() != null ? serie.getOnde() : "N/A"));
        }

        conteudoFormulario.getChildren().add(grid);
    }


    public static Label criarLabelConteudo(String texto) {
        Label label = new Label(texto);
        label.setStyle("-fx-text-fill: #000000;");
        label.setWrapText(true);
        return label;
    }

    private String formatarAtores(List<Pessoa> atores) {
        if (atores == null || atores.isEmpty()) return "Nenhum ator cadastrado";
        StringBuilder sb = new StringBuilder();
        for (Pessoa ator : atores) {
            sb.append(ator.getNome()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    // Métodos auxiliares
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
}