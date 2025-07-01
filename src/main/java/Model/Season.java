package Model;

import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;

/**
 * Classe que representa uma temporada de uma série.
 * Contém informações específicas como número da temporada e episódios.
 */
public class Season extends Media {
    private int season_number;
    private int episodes_number;
    private int release_date_season;

    /**
     * Retorna uma representação em string da temporada.
     * @return String formatada com informações da temporada
     */
    public String toString() {
        return "Temporada " + season_number + "\n" +
                "Número de episódios: " + episodes_number;
    }

    /**
     * Obtém o número da temporada.
     * @return Número da temporada
     */
    public int getSeason_number() {
        return season_number;
    }

    /**
     * Obtém o número de episódios da temporada.
     * @return Número de episódios
     */
    public int getEpisodes_number() {
        return episodes_number;
    }

    /**
     * Obtém o ano de lançamento da temporada.
     * @return Ano de lançamento
     */
    public int getRelease_date_season() {
        return release_date_season;
    }

    /**
     * Define o número da temporada.
     * @param season_number Número da temporada
     */
    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    /**
     * Define o número de episódios da temporada.
     * @param episodes_number Número de episódios
     */
    public void setEpisodes_number(int episodes_number) {
        this.episodes_number = episodes_number;
    }

    /**
     * Define o ano de lançamento da temporada.
     * @param release_date_season Ano de lançamento
     */
    public void setRelease_date_season(int release_date_season) {
        this.release_date_season = release_date_season;
    }

    /**
     * Construtor completo para uma temporada.
     * @param title Título
     * @param status Status de conclusão
     * @param release_date_season Ano de lançamento
     * @param gender Gênero
     * @param author Autor/Responsável
     * @param season_number Número da temporada
     * @param review Avaliação
     */
    public Season(String title, boolean status, int release_date_season, Gender gender, Pessoa author, int season_number, Review review) {
        super(title, status, release_date_season, gender, author, review);
        this.episodes_number = episodes_number;
        this.release_date_season = release_date_season;
    }

    /**
     * Construtor vazio para uma temporada.
     */
    public Season() {
        super();
        this.tipo = "Season";
    }
}