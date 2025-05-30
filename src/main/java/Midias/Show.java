package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Show extends DigitalMedia {
    private  int seasons_number;
    private  List<Season> seasons ;
    private int final_date;

    public int getSeasons_number() {
        return seasons_number;
    }
    public void setSeasons_number(int seasons_number) {
        this.seasons_number = seasons_number;
    }

    public int getFinal_date() {return final_date;}
    public void setFinal_date(int final_date) {this.final_date = final_date;}

    public List<Season> getSeasons() {
        return seasons;
    }
    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Temporadas: " + (seasons != null ? seasons.stream()
                .map(Season::toString)
                .collect(Collectors.joining("\n---\n")) : "Nenhuma");
    }

    public static void addSeason(Season season,List<Season> seasons) {
        if (seasons == null) {
            seasons = new ArrayList<>();
        }
        seasons.add(season);
    }

    public static int getShowNote(List<Season> seasons){
        int count = seasons.stream()
                .mapToInt(season -> season.getReview().getStars())
                .sum();

        return count / seasons.size();

    }

    //Construtor
    public Show(String title, boolean status, int release_date,
                int final_date, Pessoa author, Gender gender, Review review, List<Pessoa> cast,
                String onde, int Seasons_number) {

        super(title, status, release_date, author, gender, onde,review);
        this.final_date=final_date;
        this.seasons_number = Seasons_number;
        this.seasons = new ArrayList<>();
        this.setCast(cast);
        this.tipo="Show";
    }

}
