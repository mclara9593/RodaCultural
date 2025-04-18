package Midias;

import Others.Gender;
import Others.Pessoa;

import java.time.LocalDate;
import java.util.List;

public class Show {
    private int seasons_number;

    public Show(String title, boolean status, LocalDate release_date, Pessoa author, Gender gender, List cast, String onde, int seasons_number){
        //construtor de DigitalMedia
        super(title,status,release_date,author,gender,cast,onde);
    }

    public void setSeasons_number(int seasons_number) {
        this.seasons_number = seasons_number;
    }
}
