package View;

import MenuUtils.Utilitie;
import Model.Media;
import Model.Others.Pessoa;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.util.ArrayList;
import java.util.List;

import static MenuUtils.SaveFile.load;
import static MenuUtils.SaveFile.loadActors;
import static MenuUtils.Utilitie.handleMainChoice;

/**
 * Classe que representa o menu principal da aplicação.
 * Permite navegar para diferentes funcionalidades do sistema.
 */
public class Menu extends VBox {

    /**
     * Constrói o menu principal com opções de navegação.
     */
    public Menu() {
        // Configurações básicas do layout
        this.setSpacing(10);
        this.setStyle("-fx-alignment: center; -fx-padding: 20;");


        Text title = new Text("DIÁRIO CULTURAL");

        VBox titleBox = new VBox();
        titleBox.setId("mainTitle");
        titleBox.setSpacing(10);
        titleBox.setStyle("-fx-alignment: center; -fx-background-color: #b98cdc;");
        titleBox.setPadding(new Insets(10, 10, 10, 10));
        titleBox.getChildren().add(title);
        titleBox.setPrefSize(300, 60);
        titleBox.setAlignment(javafx.geometry.Pos.CENTER);


        Button btnCadastrar = new Button("➕ Cadastrar");
        Button btnAvaliar = new Button("⭐  Avaliar");
        Button btnBuscar = new Button("\uD83D\uDD0D Buscar");
        Button btnListar = new Button("\uD83D\uDCCB Listar");
        Button btnSair = new Button("\uD83D\uDEAA Sair");

        // Adicionando os botões ao layout
        this.getChildren().addAll(titleBox ,btnCadastrar, btnBuscar, btnListar,   btnAvaliar ,btnSair);
        Button btnVoltar = Utilitie.createBackButton();

        List<Pessoa> atores = new ArrayList<>();
        String pathAtores = System.getProperty("user.dir") + "\\src\\main\\java\\Atores.json";
        loadActors(atores,pathAtores);

        List<Media> midias = new ArrayList<>();
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\All.json";
        load(midias,path);
        if (midias==null){
            System.out.println("VAZIO");
        }

        btnCadastrar.setOnAction(e -> {
            Cadastro register = new Cadastro( midias,path,atores,pathAtores);
            handleMainChoice(register,"Cadastro",btnCadastrar);

        });

        btnAvaliar.setOnAction(e -> {
            Avaliacao review = new Avaliacao(midias,path,btnVoltar);
            handleMainChoice(review,"Avaliação",btnCadastrar);

        });

        btnBuscar.setOnAction(e -> {
            load(midias,path);
            Busca busca= new Busca(midias, atores,btnVoltar );
            handleMainChoice(busca,"Busca",btnCadastrar);

        });

        btnListar.setOnAction(e -> {
            Listar listar= new Listar(midias, atores,btnVoltar);
            handleMainChoice(listar,"Listar",btnCadastrar);

        });

        btnSair.setOnAction(e -> System.exit(0));
    }

}
