package Midias;

import Others.Gender;
import Others.Pessoa;

import java.time.LocalDate;
import java.util.List;

public class Season {
    private int season_number;
    private int episodes_number;

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }
    public void setEpisodes_number(int episodes_number) {
        this.episodes_number = episodes_number;
    }


    public Season(String title, boolean status, LocalDate release_date, Pessoa author, Gender gender, List cast, String onde, int season_number, int episodes_number) {

        //construtor de DigitalMedia
        super(title, status, release_date, author, gender,cast,onde);
    }
}
