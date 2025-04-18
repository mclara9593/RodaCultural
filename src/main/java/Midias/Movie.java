package Midias;

import Others.Gender;
import java.time.LocalDate;
import java.util.List;

public class Movie {
    private int duration;
    private String director;
    private String script;

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setScript(String script) {
        this.script = script;
    }

    public Movies(String title, boolean status, LocalDate release_date, Pessoa author, Gender gender, List cast, String onde, int duration, String director, String scripts) {

        //construtor de DigitalMedia
        super(title, status, release_date, author, gender, cast, onde);
    }

}
