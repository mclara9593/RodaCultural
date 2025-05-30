package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;

import java.util.Scanner;

public class Season extends Media {
    private int season_number;
    private int episodes_number;
    private int release_date_season;

    public String toString() {
        return "Temporada " + season_number + "\n" +
                "Número de episódios: " + episodes_number;
    }

    public int getSeason_number() {return season_number;}
    public int getEpisodes_number() {return episodes_number;}
    public int getRelease_date_season() {return release_date_season;}

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }
    public void setEpisodes_number(int episodes_number) {
        this.episodes_number = episodes_number;
    }
    public void setRelease_date_season(int release_date_season) {this.release_date_season = release_date_season;}



    //Construtor
    public Season(String title, boolean status, int release_date_season, Gender gender, Pessoa author, int season_number, Review review ) {
        super(title, status, release_date_season,gender, author,review);
        this.episodes_number = episodes_number;
        this.release_date_season = release_date_season;

    }
}
