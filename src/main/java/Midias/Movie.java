package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;
import static MenuUtils.MenuUtilities.*;

import java.util.List;
import java.util.Scanner;

public class Movie extends DigitalMedia {
    private static int duration;
    private static Pessoa director;
    private static String script;

    public static int getDuration() {
        return duration;
    }
    public static Pessoa getDirector() {
        return director;
    }
    public static String getScript() {
        return script;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDirector(Pessoa director) {
        this.director = director;
    }
    public void setScript(String script) {
        this.script = script;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Duração: " + duration + " minutos\n" +
                "Diretor: " + this.director.getNome() + "\n" +
                "Roteiro/Script: " + this.script;
    }

    public static Pessoa getDirectorInput(String titulo) {
        Pessoa diretor = new Pessoa();

        System.out.println("Diretor do filme:");
        String nome = getStringInput();
        diretor.setNome(nome);

        diretor.setFunção("Diretor");
        diretor.setObra(titulo);

        return diretor;
    }

    public static String getScriptInput() {
        Scanner sc = new Scanner(System.in);
        script=sc.nextLine();
        return script;
    }

    public static int getDurationInput() {
            Scanner sc = new Scanner(System.in);
            duration= lerInteiro(sc,1,300);
            return duration;
        }

    public Movie(String title, boolean status, int release_date,
                 Pessoa author, Gender gender, Review review,
                 List<Pessoa> cast, String onde,
                 int duration, Pessoa director, String script) {
        super(title, status, release_date, author, gender, cast, onde);
        this.duration = duration;
        this.director = director;
        this.script = script;
        setReview(review);
    }
}
