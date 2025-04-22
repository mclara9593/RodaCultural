package Midias;

import Others.Gender;
import Others.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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


    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Temporadas: " + (seasons != null ? seasons.stream()
                .map(Season::toString)
                .collect(Collectors.joining("\n---\n")) : "Nenhuma");
    }




    public static List<Season> getSeasonsInput(Season temporada) {
        System.out.println("Digite a quantidade de episódios temporada " + (seasons_number) + ":");
        temporada.getEpisodes_numberInput();
        temporada.setSeason_number(seasons_number);

        return seasons;
    }

    public static int getSeasons_numberInput() {
        System.out.println("Quantidade de temporadas:");
        Scanner sc = new Scanner(System.in);
        int seasons_number= Integer.parseInt(sc.nextLine());
        return seasons_number;
    }

    public static void addSeason(Season season) {
        if (seasons == null) {
            seasons = new ArrayList<>();
        }
        seasons.add(season);
    }


    //Construtor
    public Show(String title, boolean status, int release_date,
                Pessoa author, Gender gender, List<Pessoa> cast, String onde) {
        super(title, status, release_date, author, gender, cast, onde);
        this.seasons = new ArrayList<>();

    }



}
