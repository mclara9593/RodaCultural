package Midias;
import Others.Gender;
import Others.Pessoa;

import java.time.LocalDate;
import java.util.List;

public class DigitalMedia extends Media {
    private List<Pessoa> cast;
    private String onde;

    public void setCast(List<Pessoa> cast) {
        this.cast = cast;
    }
    public void setOnde(String onde) {
        this.onde = onde;
    }

    public DigitalMedia(String title, boolean status, LocalDate release_date, Pessoa author, Gender gender, List<Pessoa> cast, String onde){
        super(title, status, release_date, gender, author);}
}

