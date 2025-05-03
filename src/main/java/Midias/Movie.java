package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;
import static MenuUtils.MenuUtilities.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                "Diretor: " + (director != null ? director.getNome() : "Desconhecido")
                + "\n" +
                "Roteiro/Script: " + this.script;
    }


    public Movie(String title, boolean status, int release_date,
                 Pessoa author, Gender gender, Review review,
                 List<Pessoa> cast, String onde,
                 int duration,Pessoa director,String script)
    {
        super(title, status, release_date, author, gender, onde,review);
        this.director = director;
        this.setCast(cast);
        setReview(review);
    }
}

