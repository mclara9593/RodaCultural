package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Show extends DigitalMedia {
    private static int seasons_number;
    private static List<Season> seasons ;

    public int getSeasons_number() {
        return seasons_number;
    }
    public void setSeasons_number(int seasons_number) {
        this.seasons_number = seasons_number;
    }

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

    public static void addSeason(Season season) {
        if (seasons == null) {
            seasons = new ArrayList<>();
        }
        seasons.add(season);
    }

    //Construtor
    public Show(String title, boolean status, int release_date,
                Pessoa author, Gender gender, Review review, List<Pessoa> cast,
                String onde,int Seasons_number) {

        super(title, status, release_date, author, gender, onde,review);
        this.seasons_number = Seasons_number;
        this.seasons = new ArrayList<>();
        this.setCast(cast);
        this.tipo="Show";
    }

}
