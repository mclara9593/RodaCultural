package View;

import Model.Books;
import Model.Media;
import Model.Movie;
import Model.Others.Pessoa;
import Model.Show;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.text.Text;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static Model.Media.*;

public class Listar extends VBox {

    private VBox conteudoFormulario;
    private List<Media> midias;
    private Button btnVoltar;

    public Listar(List<Media> midias, List<Pessoa> atores, Button btnVoltar) {
        this.midias = midias;
        this.btnVoltar = btnVoltar;

        this.setSpacing(10);
        this.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Título
        Text title = new Text("📋 LISTAR MÍDIAS");
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

        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(conteudoFormulario);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("custom-scroll-pane");

        // Comportamento ao selecionar tipo de mídia
        typeChooser.setOnChoiceMade(mediaType -> {
            conteudoFormulario.getChildren().clear();

            switch (mediaType) {
                case "Movie":
                    showAllMovies();
                    break;
                case "Book":
                    showAllBooks();
                    break;
                case "Show":
                    showAllShows();
                    break;
            }
        });

        // Estrutura base da tela
        this.getChildren().addAll(titleBox, typeChooser, scrollPane);
    }

    private void showAllMovies() {
        List<Media> filmes = midias.stream()
                .filter(m -> m instanceof Movie)
                .collect(Collectors.toList());

        showMediaList(filmes, "Filmes");
    }

    private void showAllBooks() {
        List<Media> livros = midias.stream()
                .filter(m -> m instanceof Books)
                .collect(Collectors.toList());

        showMediaList(livros, "Livros");
    }

    private void showAllShows() {
        List<Media> series = midias.stream()
                .filter(m -> m instanceof Show)
                .collect(Collectors.toList());

        showMediaList(series, "Séries");
    }

    private void showMediaList(List<Media> mediaList, String tipo) {
        conteudoFormulario.getChildren().clear();

        if (mediaList.isEmpty()) {
            conteudoFormulario.getChildren().add(new Label("Sem " + tipo.toLowerCase() + " encontrado!"));
            return;
        }

        Label tituloSecao = new Label("Lista de " + tipo + ":");
        tituloSecao.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-padding: 0 0 10 0;");
        conteudoFormulario.getChildren().add(tituloSecao);

        for (Media media : mediaList) {
            Button btnMidia = new Button(media.getTitle());
            btnMidia.setMaxWidth(Double.MAX_VALUE);
            btnMidia.setStyle("-fx-alignment: center-left; -fx-content-display: left;");
            btnMidia.setOnAction(e -> exibirDetalhesMidia(media));

            conteudoFormulario.getChildren().add(btnMidia);
        }

        // Adiciona botão de voltar no final da lista
        conteudoFormulario.getChildren().add(btnVoltar);
    }

    private void exibirDetalhesMidia(Media midia) {
        conteudoFormulario.getChildren().clear();

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
        grid.addRow(row++, new Label("Título:"), new Label(midia.getTitle()));
        grid.addRow(row++, new Label("Status:"), new Label(midia.isStatus() ? "Concluído" : "Não concluído"));
        grid.addRow(row++, new Label("Gênero:"), new Label(midia.getGender().getDescricao()));
        grid.addRow(row++, new Label("Autor:"), new Label(safeValue.apply(midia.getAuthor())));
        grid.addRow(row++, new Label("Ano de Lançamento:"), new Label(String.valueOf(midia.getRelease_date())));

        if (midia.getReview() != null) {
            grid.addRow(row++, new Label("Avaliação:"), new Label(String.valueOf(midia.getReview().getStars())));
            grid.addRow(row++, new Label("Comentário:"), new Label(midia.getReview().getNote()));
        }

        // Campos específicos com tratamento de null
        if (midia instanceof Movie) {
            Movie filme = (Movie) midia;
            grid.addRow(row++, new Label("Duração (min):"), new Label(String.valueOf(filme.getDuration())));
            grid.addRow(row++, new Label("Diretor:"), new Label(safeValue.apply(filme.getDirector())));
            grid.addRow(row++, new Label("Roteiro:"), new Label(filme.getScript() != null ? filme.getScript() : "N/A"));
            grid.addRow(row++, new Label("Atores:"), new Label(formatarAtores(filme.getCast())));
            grid.addRow(row++, new Label("Onde encontrar:"), new Label(filme.getOnde() != null ? filme.getOnde() : "N/A"));
        }
        else if (midia instanceof Books) {
            Books livro = (Books) midia;
            grid.addRow(row++, new Label("ISBN:"), new Label(String.valueOf(livro.getISBN())));
            grid.addRow(row++, new Label("Cópia Física:"), new Label(livro.getCopy() ? "Sim" : "Não"));
            grid.addRow(row++, new Label("Editora:"), new Label(livro.getPublisher() != null ? livro.getPublisher() : "N/A"));
        }
        else if (midia instanceof Show) {
            Show serie = (Show) midia;
            grid.addRow(row++, new Label("Ano de Encerramento:"), new Label(String.valueOf(serie.getFinal_date())));
            grid.addRow(row++, new Label("Temporadas:"), new Label(String.valueOf(serie.getSeasons_number())));
            grid.addRow(row++, new Label("Onde encontrar:"), new Label(serie.getOnde() != null ? serie.getOnde() : "N/A"));
        }

        Button btnVoltarLista = new Button("Voltar para lista");
        btnVoltarLista.setOnAction(e -> {
            // Descobre qual tipo de mídia estava sendo exibido
            if (midia instanceof Movie) showAllMovies();
            else if (midia instanceof Books) showAllBooks();
            else if (midia instanceof Show) showAllShows();
        });

        conteudoFormulario.getChildren().addAll(grid, new HBox(10, btnVoltarLista, btnVoltar));
    }

    private String formatarAtores(List<Pessoa> atores) {
        if (atores == null || atores.isEmpty()) return "Nenhum ator cadastrado";
        return atores.stream()
                .map(Pessoa::getNome)
                .collect(Collectors.joining(", "));
    }
}