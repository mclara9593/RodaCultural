package View;

import Model.*;
import Model.Others.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

import static MenuUtils.SaveFile.save;
import static View.ViewUtilitie.showInfoAlert;

public class Cadastro extends VBox {

    private VBox conteudoFormulario;

    public Cadastro(List<Media> midias, String path, Button btnVoltar) {
        this.setSpacing(10);
        this.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Text title = new Text("➕ CADASTRO");
        VBox titleBox = new VBox(title);
        titleBox.setId("mainTitle");
        titleBox.setStyle("-fx-alignment: center; -fx-background-color: #b98cdc;");
        titleBox.setPadding(new Insets(10));
        titleBox.setPrefSize(300, 60);

        ViewUtilitie.MediaTypeChooserPane typeChooser = new ViewUtilitie.MediaTypeChooserPane();
        conteudoFormulario = new VBox(10);
        conteudoFormulario.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(conteudoFormulario);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("custom-scroll-pane");

        typeChooser.setOnChoiceMade(mediaType -> {
            conteudoFormulario.getChildren().clear();
            switch (mediaType) {
                case "Movie":
                    criarFormularioFilme(new Movie(), midias, path);
                    break;
                case "Show":
                    criarFormularioSerie(new Show(), midias, path);
                    break;
                case "Book":
                    criarFormularioLivro(new Books(), midias, path);
                    break;
            }
        });

        this.getChildren().addAll(titleBox, typeChooser, scrollPane);
    }

    // Método auxiliar para criar campos numéricos com validação
    private TextField createNumericField() {
        TextField field = new TextField();
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(oldValue);
                showErrorAlert("Por favor, preencha esse campo apenas com números.");
            }
        });
        return field;
    }

    private void criarFormularioFilme(Movie movie, List<Media> midias, String path) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Campos comuns
        TextField campoTitulo = new TextField();
        ComboBox<Boolean> campoStatus = comboBoolean("Concluído", "Não concluído");
        ComboBox<Gender> campoGenero = comboGenero();
        TextField campoAutor = new TextField();
        TextField campoAnoLancamento = createNumericField();

        // Campos específicos de filme
        TextField campoDuracao = createNumericField();
        TextField campoDiretor = new TextField();
        TextField campoRoteiro = new TextField();
        TextField campoAtores = new TextField();
        TextField campoOnde = new TextField();

        int row = 0;
        grid.addRow(row++, new Label("Título:"), campoTitulo);
        grid.addRow(row++, new Label("Status:"), campoStatus);
        grid.addRow(row++, new Label("Gênero:"), campoGenero);
        grid.addRow(row++, new Label("Autor:"), campoAutor);
        grid.addRow(row++, new Label("Ano de Lançamento:"), campoAnoLancamento);
        grid.addRow(row++, new Label("Duração (min):"), campoDuracao);
        grid.addRow(row++, new Label("Diretor:"), campoDiretor);
        grid.addRow(row++, new Label("Roteiro:"), campoRoteiro);
        grid.addRow(row++, new Label("Elenco:"), campoAtores);
        grid.addRow(row++, new Label("Onde encontrar:"), campoOnde);

        Button btnSalvar = new Button("Salvar Filme");
        btnSalvar.setOnAction(e -> {
            try {
                // Verificar campos obrigatórios
                if (campoTitulo.getText().isEmpty() || campoAutor.getText().isEmpty() ||
                        campoAnoLancamento.getText().isEmpty() || campoDuracao.getText().isEmpty()) {
                    showErrorAlert("Por favor, preencha todos os campos obrigatórios.");
                    return;
                }

                // Definir atributos comuns
                movie.setTitle(campoTitulo.getText().toUpperCase());
                movie.setStatus(campoStatus.getValue());
                movie.setGender(campoGenero.getValue());
                movie.setAuthor(new Pessoa(campoAutor.getText().toUpperCase(), null));
                movie.setRelease_date(Integer.parseInt(campoAnoLancamento.getText()));

                // Definir atributos específicos
                movie.setDirector(new Pessoa(campoDiretor.getText().toUpperCase(), campoTitulo.getText().toUpperCase()));
                movie.setDuration(Integer.parseInt(campoDuracao.getText()));
                movie.setScript(campoRoteiro.getText().toUpperCase());
                movie.setOnde(campoOnde.getText().toUpperCase());

                if(movie.getCast()==null){
                    List<Pessoa> cast = new ArrayList<>();
                    DigitalMedia.castList(cast,campoTitulo.getText(),campoAtores.getText().toUpperCase());
                    movie.setCast(cast);
                }else {
                    List<Pessoa> cast=movie.getCast();
                    DigitalMedia.castList(cast,campoTitulo.getText(),campoAtores.getText().toUpperCase());
                    movie.setCast(cast);
                }



                midias.add(movie);
                save(midias, path);
                showInfoAlert("Filme salvo com sucesso!");

            } catch (NumberFormatException ex) {
                showErrorAlert("Erro ao converter número: Por favor, preencha todos os campos numéricos corretamente.");
            }
        });

        conteudoFormulario.getChildren().addAll(grid, new HBox(10, btnSalvar));
    }

    private void criarFormularioLivro(Books book, List<Media> midias, String path) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Campos comuns
        TextField campoTitulo = new TextField();
        ComboBox<Boolean> campoStatus = comboBoolean("Concluído", "Não concluído");
        ComboBox<Gender> campoGenero = comboGenero();
        TextField campoAutor = new TextField();
        TextField campoAnoLancamento = createNumericField();

        // Campos específicos de livro
        TextField campoISBN = createNumericField();
        ComboBox<Boolean> campoCopia = comboBoolean("Sim", "Não");
        TextField campoEditora = new TextField();

        int row = 0;
        grid.addRow(row++, new Label("Título:"), campoTitulo);
        grid.addRow(row++, new Label("Status:"), campoStatus);
        grid.addRow(row++, new Label("Gênero:"), campoGenero);
        grid.addRow(row++, new Label("Autor:"), campoAutor);
        grid.addRow(row++, new Label("Ano de Lançamento:"), campoAnoLancamento);
        grid.addRow(row++, new Label("ISBN:"), campoISBN);
        grid.addRow(row++, new Label("Cópia Física:"), campoCopia);
        grid.addRow(row++, new Label("Editora:"), campoEditora);

        Button btnSalvar = new Button("Salvar Livro");
        btnSalvar.setOnAction(e -> {
            try {
                // Verificar campos obrigatórios
                if (campoTitulo.getText().isEmpty() || campoAutor.getText().isEmpty() ||
                        campoAnoLancamento.getText().isEmpty() || campoISBN.getText().isEmpty()) {
                    showErrorAlert("Por favor, preencha todos os campos obrigatórios.");
                    return;
                }

                // Definir atributos comuns
                book.setTitle(campoTitulo.getText().toUpperCase());
                book.setStatus(campoStatus.getValue());
                book.setGender(campoGenero.getValue());
                book.setAuthor(new Pessoa(campoAutor.getText().toUpperCase(), campoTitulo.getText().toUpperCase()));
                book.setRelease_date(Integer.parseInt(campoAnoLancamento.getText()));

                // Definir atributos específicos
                book.setISBN(Integer.parseInt(campoISBN.getText()));
                book.setCopy(campoCopia.getValue());
                book.setPublisher(campoEditora.getText().toUpperCase());

                midias.add(book);
                save(midias, path);
                showInfoAlert("Livro salvo com sucesso!");
            } catch (NumberFormatException ex) {
                showErrorAlert("Erro ao converter número: Por favor, preencha todos os campos numéricos corretamente.");
            }
        });

        conteudoFormulario.getChildren().addAll(grid, new HBox(10, btnSalvar));
    }

    private void criarFormularioSerie(Show show, List<Media> midias, String path) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Campos comuns
        TextField campoTitulo = new TextField();
        ComboBox<Boolean> campoStatus = comboBoolean("Concluído", "Não concluído");
        ComboBox<Gender> campoGenero = comboGenero();
        TextField campoAutor = new TextField();
        TextField campoAnoLancamento = createNumericField();
        TextField campoAtores = new TextField();

        // Campos específicos de série
        TextField campoAnoEncerramento = createNumericField();
        TextField campoTemporadas = createNumericField();
        TextField campoOnde = new TextField();

        int row = 0;
        grid.addRow(row++, new Label("Título:"), campoTitulo);
        grid.addRow(row++, new Label("Status:"), campoStatus);
        grid.addRow(row++, new Label("Gênero:"), campoGenero);
        grid.addRow(row++, new Label("Autor:"), campoAutor);
        grid.addRow(row++, new Label("Ano de Lançamento:"), campoAnoLancamento);
        grid.addRow(row++, new Label("Ano de Encerramento:"), campoAnoEncerramento);
        grid.addRow(row++, new Label("Elenco:"), campoAtores);
        grid.addRow(row++, new Label("Qtd Temporadas:"), campoTemporadas);
        grid.addRow(row++, new Label("Onde encontrar:"), campoOnde);

        Button btnSalvar = new Button("Salvar Série");
        btnSalvar.setOnAction(e -> {
            try {
                // Verificar campos obrigatórios
                if (campoTitulo.getText().isEmpty() || campoAutor.getText().isEmpty() ||
                        campoAnoLancamento.getText().isEmpty() || campoAnoEncerramento.getText().isEmpty() ||
                        campoTemporadas.getText().isEmpty()) {
                    showErrorAlert("Por favor, preencha todos os campos obrigatórios.");
                    return;
                }

                int anoLancamento = Integer.parseInt(campoAnoLancamento.getText());
                int anoEncerramento = Integer.parseInt(campoAnoEncerramento.getText());

                if (anoEncerramento < anoLancamento) {
                    showErrorAlert("O ano de encerramento não pode ser anterior ao ano de lançamento.");
                    return;
                }

                // Definir atributos comuns
                show.setTitle(campoTitulo.getText().toUpperCase());
                show.setStatus(campoStatus.getValue());
                show.setGender(campoGenero.getValue());
                show.setAuthor(new Pessoa(campoAutor.getText().toUpperCase(), campoTitulo.getText().toUpperCase()));
                show.setRelease_date(anoLancamento);

                // Definir atributos específicos
                show.setFinal_date(anoEncerramento);
                show.setOnde(campoOnde.getText());
                show.setSeasons_number(Integer.parseInt(campoTemporadas.getText()));

                if(show.getCast()==null){
                    List<Pessoa> cast = new ArrayList<>();
                    DigitalMedia.castList(cast,campoTitulo.getText(),campoAtores.getText().toUpperCase());
                    show.setCast(cast);
                }else {
                    List<Pessoa> cast=show.getCast();
                    DigitalMedia.castList(cast,campoTitulo.getText(),campoAtores.getText().toUpperCase());
                    show.setCast(cast);
                }

                midias.add(show);
                save(midias, path);
                showInfoAlert("Série salva com sucesso!");
            } catch (NumberFormatException ex) {
                showErrorAlert("Erro ao converter número: Por favor, preencha todos os campos numéricos corretamente.");
            }
        });

        Button btnTemporada = new Button("Cadastrar temporada");
        List<Season> seasons = show.getSeasons();

        btnTemporada.setOnAction(e -> {
            conteudoFormulario.getChildren().clear();
            criarFormularioSeason(seasons, midias, path, show, Integer.parseInt(campoTemporadas.getText()));
        });

        conteudoFormulario.getChildren().addAll(grid, new HBox(10, btnTemporada, btnSalvar));
    }

    private void criarFormularioSeason(List<Season> seasons, List<Media> midias, String path, Show show,int seasons_number) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Campos específicos de temporada
        //TextField number = createNumericField();
        TextField episodios = createNumericField();
        TextField releasedate = createNumericField();

        ComboBox<Integer> comboNumber = new ComboBox<>();
        for (int i = 0; i <= seasons_number; i++) {
            comboNumber.getItems().add(i);
        }

        int row = 0;
        grid.addRow(row++, new Label("Qual temporada?:"), comboNumber);
        grid.addRow(row++, new Label("Quantidade de episódios:"), episodios);
        grid.addRow(row++, new Label("Data de lançamento:"), releasedate);

        Season season = new Season();

        Button btnSalvar = new Button("Salvar Temporada");
        btnSalvar.setOnAction(e -> {
            try {
                // Verificar campos obrigatórios
                if (episodios.getText().isEmpty() || releasedate.getText().isEmpty()) {
                    showErrorAlert("Por favor, preencha todos os campos.");
                    return;
                }

                // Definir atributos específicos
                season.setSeason_number(comboNumber.getValue());
                season.setEpisodes_number(Integer.parseInt(episodios.getText()));
                season.setRelease_date(Integer.parseInt(releasedate.getText()));

                // Adicionar temporada à série
                Show.addSeason(season, seasons);

                save(midias, path);
                showInfoAlert("Temporada cadastrada com sucesso!");

                // Voltar para o formulário da série
                conteudoFormulario.getChildren().clear();
                criarFormularioSerie(show, midias, path);
            } catch (NumberFormatException ex) {
                showErrorAlert("Erro ao converter número. Preencha corretamente.");
            }
        });

        conteudoFormulario.getChildren().addAll(grid, new HBox(10, btnSalvar));
    }

    // mostrar erros
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Formato");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // combobox
    private ComboBox<Gender> comboGenero() {
        ComboBox<Gender> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(Gender.values());
        comboBox.setConverter(new StringConverter<Gender>() {
            @Override
            public String toString(Gender gender) {
                return gender == null ? "" : gender.getDescricao();
            }

            @Override
            public Gender fromString(String string) {
                return null;
            }
        });
        comboBox.setPromptText("Selecione o gênero");
        return comboBox;
    }

    private ComboBox<Boolean> comboBoolean(String trueLabel, String falseLabel) {
        ComboBox<Boolean> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(true, false);
        comboBox.setConverter(new StringConverter<Boolean>() {
            @Override
            public String toString(Boolean value) {
                return value == null ? "" : value ? trueLabel : falseLabel;
            }

            @Override
            public Boolean fromString(String string) {
                return string.equals(trueLabel);
            }
        });
        comboBox.getSelectionModel().selectFirst();
        return comboBox;
    }
}
