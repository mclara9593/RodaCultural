package Model;
import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DigitalMedia extends Media {
    private List<Pessoa> cast;
    private String onde;

    public List<Pessoa> getCast() {
        return cast;
    }

    public void setCast(List<Pessoa> cast) {
        this.cast = cast;
    }


    public String getOnde() {
        return onde;
    }

    public void setOnde(String onde) {
        this.onde = onde;
    }

    @Override
    public  String toString() {
        String elenco = cast.stream()
                .map(pessoa -> pessoa.getNome())
                .collect(Collectors.joining(", "));

        return super.toString() + "\n" +
                "Onde encontrar: " + onde + "\n" +
                "Elenco: " + elenco;
    }





    //Construtor
    public DigitalMedia(String title, boolean status, int release_date,
                        Pessoa author, Gender gender, String onde, Review review) {
        super(title, status, release_date, gender, author, review); // Ordem correta
        this.onde = onde;
        this.cast = cast != null ? cast : new ArrayList<>();
    }

    public DigitalMedia() {
        super(); // certifique-se de que Media também tem um construtor vazio
        this.tipo = "DigitalMedia";
    }

    public static List<Pessoa> castList(List<Pessoa> cast, String titulo, String nomes) {
        // Verifica se a string de nomes está vazia
        if (nomes == null || nomes.trim().isEmpty()) {
            return cast;
        }

        // Divide a string usando vírgula como separador
        String[] nomesArray = nomes.split(",");

        // Itera sobre cada nome no array
        for (String nome : nomesArray) {
            // Remove espaços em branco no início e fim
            String nomeLimpo = nome.trim();

            // Ignora strings vazias após o trim
            if (!nomeLimpo.isEmpty()) {
                // Cria uma nova pessoa com o nome e título da obra
                Pessoa p = new Pessoa(nomeLimpo, titulo);

                // Adiciona a pessoa ao elenco
                cast.add(p);
            }
        }

        return cast;
    }



}

