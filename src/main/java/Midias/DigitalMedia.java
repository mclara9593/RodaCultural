package Midias;
import Others.Gender;
import Others.Pessoa;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.*;
import static MenuUtils.MenuUtilities.getStringInput;


public class DigitalMedia extends Media {
    private List<Pessoa> cast;
    private String onde;

    public List<Pessoa> getCast() {
        return cast;
    }
    public String getOnde() {
        return onde;
    }

    public void setCast(List<Pessoa> cast) {
        this.cast = cast;
    }
    public void setOnde(String onde) {
        this.onde = onde;
    }

    //toString
    @Override
    public String toString() {
        String elencoFormatado = cast.stream()
                .map(pessoa -> pessoa.getNome())
                .collect(Collectors.joining(", "));

        return super.toString() + "\n" +
                "Onde encontrar: " + onde + "\n" +
                "Elenco: " + elencoFormatado;
    }

    public static List<Pessoa> getCastInput(List<Pessoa> cast, List<Media> midias,String titulo,List<Pessoa> atores) {
        System.out.println("Quantas pessoas no elenco?");
        Scanner sc = new Scanner(System.in);
        int numPessoas = sc.nextInt();

        for (int i = 0; i < numPessoas; i++) {
            Pessoa p = new Pessoa();

            System.out.println("Digite o nome da pessoa " + (i+1) + ":");
            String nome = getStringInput();
            p.setNome(nome);

            System.out.println("Digite a função/cargo da pessoa " + (i+1) + ":");
            String funcao = getStringInput();
            p.setFunção(funcao);

            p.setObra(titulo);

            //Adiciona pessoa á lista de elenco
            cast.add(p);
            atores.add(p);
        }

        return cast;
    }

    public static String getOndeInput() {
        Scanner sc = new Scanner(System.in);
        String onde=sc.nextLine();
        return onde;
    }


    //Construtor
    public DigitalMedia(String title, boolean status, int release_date,
                        Pessoa author, Gender gender, List<Pessoa> cast, String onde) {
        super(title, status, release_date, gender, author);
        this.onde = onde;
        this.cast = cast != null ? cast : new ArrayList<>();
    }
}

