package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;

import java.util.Scanner;

public class Season extends Media {
    private int season_number;
    private int episodes_number;

    public String toString() {
        return "Temporada " + season_number + "\n" +
                "Número de episódios: " + episodes_number;
    }

    
    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    
    public void setEpisodes_number(int episodes_number) {
        this.episodes_number = episodes_number;
    }


    //Construtor
    public Season(String title, boolean status, int release_date, Gender gender, Pessoa author, int season_number, Review review ) {
        super(title, status, release_date,gender, author,review);
        this.episodes_number = episodes_number;

    }
}
