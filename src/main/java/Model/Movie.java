package Model;

import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;
import java.util.List;

/**
 * Classe que representa um filme, uma subclasse de DigitalMedia.
 * Inclui atributos específicos como duração, diretor e roteiro.
 */
public class Movie extends DigitalMedia {
    private int duration;
    private Pessoa director;
    private String script;

    /**
     * Obtém a duração do filme em minutos.
     * @return Duração do filme
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Obtém o roteiro do filme.
     * @return Roteiro do filme
     */
    public String getScript() {
        return script;
    }

    /**
     * Obtém o diretor do filme.
     * @return Diretor do filme
     */
    public Pessoa getDirector() {
        return director;
    }

    /**
     * Define o diretor do filme.
     * @param director Diretor do filme
     */
    public void setDirector(Pessoa director) {
        this.director = director;
    }

    /**
     * Define a duração do filme.
     * @param duration Duração em minutos
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Define o roteiro do filme.
     * @param script Roteiro do filme
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * Retorna uma representação em string do filme.
     * @return String formatada com informações do filme
     */
    public String toString() {
        return super.toString() + "\n" +
                "Duração: " + duration + " minutos\n" +
                "Diretor: " + (director != null ? director.getNome() : "Desconhecido") + "\n" +
                "Roteiro/Script: " + script;
    }

    /**
     * Construtor completo para um filme.
     * @param title Título
     * @param status Status de conclusão
     * @param release_date Ano de lançamento
     * @param author Autor/Responsável
     * @param gender Gênero
     * @param review Avaliação
     * @param cast Elenco
     * @param onde Localização
     * @param duration Duração em minutos
     * @param director Diretor
     * @param script Roteiro
     */
    public Movie(String title, boolean status, int release_date,
                 Pessoa author, Gender gender, Review review,
                 List<Pessoa> cast, String onde,
                 int duration, Pessoa director, String script) {
        super(title, status, release_date, author, gender, onde, review);
        this.duration = duration;
        this.script = script;
        this.director = director;
        this.setCast(cast);
        setReview(review);
        this.tipo = "Movie";
    }

    /**
     * Construtor vazio para um filme.
     */
    public Movie() {
        super();
        this.tipo = "Movie";
    }
}