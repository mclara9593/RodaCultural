package Model;

import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que representa uma série, uma subclasse de DigitalMedia.
 * Inclui informações sobre temporadas e data de encerramento.
 */
public class Show extends DigitalMedia {
    private int seasons_number;
    private List<Season> seasons;
    private int final_date;

    /**
     * Obtém o número de temporadas da série.
     * @return Número de temporadas
     */
    public int getSeasons_number() {
        return seasons_number;
    }

    /**
     * Define o número de temporadas da série.
     * @param seasons_number Número de temporadas
     */
    public void setSeasons_number(int seasons_number) {
        this.seasons_number = seasons_number;
    }

    /**
     * Obtém o ano de encerramento da série.
     * @return Ano de encerramento
     */
    public int getFinal_date() {
        return final_date;
    }

    /**
     * Define o ano de encerramento da série.
     * @param final_date Ano de encerramento
     */
    public void setFinal_date(int final_date) {
        this.final_date = final_date;
    }

    /**
     * Obtém a lista de temporadas da série.
     * @return Lista de temporadas
     */
    public List<Season> getSeasons() {
        if (seasons == null) {
            seasons = new ArrayList<>();
        }
        return seasons;
    }

    /**
     * Define a lista de temporadas da série.
     * @param seasons Lista de temporadas
     */
    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    /**
     * Retorna uma representação em string da série.
     * @return String formatada com informações da série
     */
    public String toString() {
        return super.toString() + "\n" +
                "Temporadas: " + (seasons != null ? seasons.stream()
                .map(Season::toString)
                .collect(Collectors.joining("\n---\n")) : "Nenhuma");
    }

    /**
     * Adiciona uma temporada à série.
     * @param season Temporada a ser adicionada
     * @param seasons Lista de temporadas
     */
    public static void addSeason(Season season, List<Season> seasons) {
        if (seasons == null) {
            seasons = new ArrayList<>();
        }
        seasons.add(season);
    }

    /**
     * Calcula a nota média da série com base nas avaliações das temporadas.
     * @param seasons Lista de temporadas
     * @return Nota média da série
     */
    public static int getShowNote(List<Season> seasons) {
        int count = seasons.stream()
                .mapToInt(season -> season.getReview().getStars())
                .sum();
        return count / seasons.size();
    }

    /**
     * Construtor completo para uma série.
     * @param title Título
     * @param status Status de conclusão
     * @param release_date Ano de lançamento
     * @param final_date Ano de encerramento
     * @param author Autor/Responsável
     * @param gender Gênero
     * @param review Avaliação
     * @param cast Elenco
     * @param onde Localização
     * @param Seasons_number Número de temporadas
     */
    public Show(String title, boolean status, int release_date,
                int final_date, Pessoa author, Gender gender, Review review, List<Pessoa> cast,
                String onde, int Seasons_number) {
        super(title, status, release_date, author, gender, onde, review);
        this.final_date = final_date;
        this.seasons_number = Seasons_number;
        this.seasons = new ArrayList<>();
        this.setCast(cast);
        this.tipo = "Show";
    }

    /**
     * Construtor vazio para uma série.
     */
    public Show() {
        super();
        this.tipo = "Show";
    }
}