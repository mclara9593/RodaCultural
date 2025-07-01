//package Model;
//import Model.Others.Gender;
//import Model.Others.Pessoa;
//import Model.Others.Review;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class DigitalMedia extends Media {
//    private List<Pessoa> cast;
//    private String onde;
//
//    public List<Pessoa> getCast() {
//        return cast;
//    }
//
//    public void setCast(List<Pessoa> cast) {
//        this.cast = cast;
//    }
//
//
//    public String getOnde() {
//        return onde;
//    }
//
//    public void setOnde(String onde) {
//        this.onde = onde;
//    }
//
//    @Override
//    public  String toString() {
//        String elenco = cast.stream()
//                .map(pessoa -> pessoa.getNome())
//                .collect(Collectors.joining(", "));
//
//        return super.toString() + "\n" +
//                "Onde encontrar: " + onde + "\n" +
//                "Elenco: " + elenco;
//    }
//
//
//
//
//
//    //Construtor
//    public DigitalMedia(String title, boolean status, int release_date,
//                        Pessoa author, Gender gender, String onde, Review review) {
//        super(title, status, release_date, gender, author, review); // Ordem correta
//        this.onde = onde;
//        this.cast = cast != null ? cast : new ArrayList<>();
//    }
//
//    public DigitalMedia() {
//        super(); // certifique-se de que Media também tem um construtor vazio
//        this.tipo = "DigitalMedia";
//    }
//
//    public static List<Pessoa> castList(List<Pessoa> cast, String titulo, String nomes) {
//        // Verifica se a string de nomes está vazia
//        if (nomes == null || nomes.trim().isEmpty()) {
//            return cast;
//        }
//
//        // Divide a string usando vírgula como separador
//        String[] nomesArray = nomes.split(",");
//
//        // Itera sobre cada nome no array
//        for (String nome : nomesArray) {
//            // Remove espaços em branco no início e fim
//            String nomeLimpo = nome.trim();
//
//            // Ignora strings vazias após o trim
//            if (!nomeLimpo.isEmpty()) {
//                // Cria uma nova pessoa com o nome e título da obra
//                Pessoa p = new Pessoa(nomeLimpo, titulo);
//
//                // Adiciona a pessoa ao elenco
//                cast.add(p);
//            }
//        }
//
//        return cast;
//    }
//
//
//
//}
//

package Model;

import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe abstrata que representa uma mídia digital, estendendo Media.
 * Inclui características comuns a filmes e séries como elenco e localização.
 */
public abstract class DigitalMedia extends Media {
    private List<Pessoa> cast;
    private String onde;

    /**
     * Obtém o elenco da mídia digital.
     * @return Lista de pessoas no elenco
     */
    public List<Pessoa> getCast() {
        return cast;
    }

    /**
     * Define o elenco da mídia digital.
     * @param cast Lista de pessoas no elenco
     */
    public void setCast(List<Pessoa> cast) {
        this.cast = cast;
    }

    /**
     * Obtém a localização onde a mídia pode ser encontrada.
     * @return Localização da mídia
     */
    public String getOnde() {
        return onde;
    }

    /**
     * Define a localização onde a mídia pode ser encontrada.
     * @param onde Localização da mídia
     */
    public void setOnde(String onde) {
        this.onde = onde;
    }

    /**
     * Retorna uma representação em string da mídia digital.
     * @return String formatada com informações da mídia digital
     */
    @Override
    public String toString() {
        String elenco = cast.stream()
                .map(Pessoa::getNome)
                .collect(Collectors.joining(", "));

        return super.toString() + "\n" +
                "Onde encontrar: " + onde + "\n" +
                "Elenco: " + elenco;
    }

    /**
     * Construtor completo para mídia digital.
     * @param title Título da mídia
     * @param status Status de conclusão
     * @param release_date Ano de lançamento
     * @param author Autor/Responsável
     * @param gender Gênero
     * @param onde Localização
     * @param review Avaliação
     */
    public DigitalMedia(String title, boolean status, int release_date,
                        Pessoa author, Gender gender, String onde, Review review) {
        super(title, status, release_date, gender, author, review);
        this.onde = onde;
        this.cast = cast != null ? cast : new ArrayList<>();
    }

    /**
     * Construtor vazio para mídia digital.
     */
    public DigitalMedia() {
        super();
        this.tipo = "DigitalMedia";
    }

    /**
     * Converte uma string de nomes em uma lista de objetos Pessoa.
     * @param cast Lista atual de atores
     * @param titulo Título da obra associada
     * @param nomes Nomes dos atores separados por vírgula
     * @return Lista atualizada de objetos Pessoa
     */
    public static List<Pessoa> castList(List<Pessoa> cast, String titulo, String nomes,List<Pessoa> atores) {
        if (nomes == null || nomes.trim().isEmpty()) {
            return cast;
        }

        String[] nomesArray = nomes.split(",");

        for (String nome : nomesArray) {
            String nomeLimpo = nome.trim();
            if (!nomeLimpo.isEmpty()) {
                Pessoa p = new Pessoa(nomeLimpo, titulo);
                cast.add(p);
                atores.add(p);
            }
        }

        return cast;
    }
}
