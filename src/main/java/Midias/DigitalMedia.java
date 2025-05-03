package Midias;
import Others.Gender;
import Others.Pessoa;
import Others.Review;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.*;
import static MenuUtils.MenuUtilities.getStringInput;
import static MenuUtils.MenuUtilities.lerInteiro;
import static Others.Pessoa.lerPessoa;


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

    @Override
    public  String toString() {
        String elenco = cast.stream()
                .map(pessoa -> pessoa.getNome())
                .collect(Collectors.joining(", "));

        return super.toString() + "\n" +
                "Onde encontrar: " + onde + "\n" +
                "Elenco: " + elenco;
    }

    public static List<Pessoa> getCastInput(List<Pessoa> cast, List<Media> midias,String titulo,List<Pessoa> atores) {
        System.out.println("Quantas pessoas no elenco?");
        Scanner sc = new Scanner(System.in);
        int numPessoas = lerInteiro(sc,1,10000);

        for (int i = 0; i < numPessoas; i++) {

            Pessoa p = lerPessoa(titulo);

            //Adiciona pessoa á lista de elenco e atores no geral
            cast.add(p);
            atores.add(p);

        }

        return cast;
    }

    //Construtor
    public DigitalMedia(String title, boolean status, int release_date,
                        Pessoa author, Gender gender, String onde, Review review) {
        super(title, status, release_date, gender, author, review); // Ordem correta
        this.onde = onde;
        this.cast = cast != null ? cast : new ArrayList<>();
    }
}

