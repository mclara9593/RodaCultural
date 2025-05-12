package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;
import static MenuUtils.MenuUtilities.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Movie extends DigitalMedia {
    private  int duration;
    private static Pessoa director;
    private  String script;

    public int getDuration() {return duration;}
    public String getScript() {return script;}
    public Pessoa getDirector() {return director;}

    public void setDirector(Pessoa director) {this.director = director;}
    public void setDuration(int duration) {this.duration = duration;}
    public void setScript(String script) {this.script = script;}

    public String toString() {
        return super.toString() + "\n" +
                "Duração: " + duration + " minutos\n" +
                "Diretor: " + (director != null ? director.getNome() : "Desconhecido")
                + "\n" +
                "Roteiro/Script: " + script;
    }


    public Movie(String title, boolean status, int release_date,
                 Pessoa author, Gender gender, Review review,
                 List<Pessoa> cast, String onde,
                 int duration,Pessoa director,String script)
    {
        super(title, status, release_date, author, gender, onde,review);
        this.duration = duration;
        this.script = script;
        this.director = director;
        this.setCast(cast);
        setReview(review);
        this.tipo="Movie";
    }
}

