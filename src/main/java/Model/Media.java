package Model;
import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;
import java.util.List;


public abstract class Media {
    private  String title;
    private  boolean status;
    private  int release_date;
    private Gender gender;
    private  Pessoa author;
    private  Review review;




    protected String tipo;


    //Getters and Setters

    public  String getTitle() {
        return title;
    }

    public boolean isStatus() {
        return status;
    }

    public int getRelease_date() {
        return release_date;
    }

    public Gender getGender() {
        return gender;
    }

    public Pessoa getAuthor() {
        return author;
    }

    public Review getReview() {
        return review;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(boolean status) {
        this.status=status;
    }

    public void setRelease_date(int release_date) {
        this.release_date=release_date;
    }    
    
    public void setGender(Gender gender) {
        this.gender=gender;
    }
    
    public void setAuthor(Pessoa author) {
        this.author=author;
    }

    public void setReview(Review review) {
        this.review=review;
    }



    //ToString
    public String toString() {
        return  "\n"+
                "Título: " + title + "\n" +
                "Status: " + (status ? "Concluído" : "Não concluído") + "\n" +
                "Ano lançamento: " + release_date + "\n" +
                "Gênero: " + gender + "\n" +
                "Autor: " + (author != null ? author.getNome() : "Desconhecido") + "\n" +
                "Avaliação: " + (review != null ? review.getStars() : "Nenhuma") + " estrelas\n" +
                "Nota: " + (review != null ? review.getNote() : "Nenhuma");

    }

    //Construtores
    public Media(String title, boolean status, int release_date, Gender gender, Pessoa author,Review review) {
        this.title = title;
        this.status = status;
        this.release_date = release_date;
        this.gender = gender;
        this.author = author;
        this.review = review;
    }

    public Media() {
        super(); // certifique-se de que Media também tem um construtor vazio
    }


    //Métodos de seleção do tipo de busca conforme tipo de obj
    public static Media searchBook(List<Media> midias,String entrada,String campo) {
        switch (entrada) {
            case "TÍTULO": return searchByTitle(midias,campo);
            case "AUTOR": return searchByAuthor(midias,campo);
            case "GÊNERO": return searchByGender(midias,campo);
            case "ANO": return searchByYear(midias,campo);
            case "ISBN": return searchByISBN(midias,campo);
            default:
                System.out.println("Opção inválida para livros!");
                return null;
        }
    }

    public static Media searchMovie(List<Media> midias, List<Pessoa> atores,String entrada,String campo) {

        switch (entrada) {
            case "TÍTULO": return searchByTitle(midias,campo);
            case "GÊNERO": return searchByGender(midias,campo);
            case "DIRETOR": return searchByDirector(midias,campo);
            case "ATOR": return searchByActor(midias, atores,campo);
            case "ANO": return searchByYear(midias,campo);
            default:
                System.out.println("Opção inválida para filmes!");
                return null;
        }
    }

    public static Media searchSeries(List<Media> midias, String title) {
        return midias.stream()
                .filter(m -> m instanceof Show)  // Filtra apenas séries
                .filter(m -> m.getTitle() != null && m.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    // Métodos de busca específicos
    private static Media searchByTitle(List<Media> midias,String title) {
        return midias.stream()
                .filter(m -> m.getTitle() != null && m.getTitle().equalsIgnoreCase(title.toUpperCase()))
                .findFirst()
                .orElse(null);

    }

    private static Media searchByYear(List<Media> midias,String ano) {
        int anoBuscado = Integer.parseInt(ano);
        return midias.stream()
                .filter(m -> m.getRelease_date() == anoBuscado)
                .findFirst()
                .orElse(null);
    }

    private static Media searchByAuthor(List<Media> midias,String autor) {
        return midias.stream()
                .filter(m -> m.getAuthor() != null &&
                        m.getAuthor().getNome().equalsIgnoreCase(autor.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    private static Media searchByGender(List<Media> midias,String gender) {
        return midias.stream()
                .filter(m -> m.getGender() != null &&
                        m.getGender().name().equalsIgnoreCase(gender))
                .findFirst()
                .orElse(null);
    }

    private static Media searchByISBN(List<Media> midias,String isbn) {
        int ISBN = Integer.parseInt(isbn);
        return midias.stream()
                .filter(Books.class::isInstance)
                .map(Books.class::cast)
                .filter(m -> m.getISBN() == ISBN)
                .findFirst()
                .orElse(null);
    }

    private static Media searchByDirector(List<Media> midias,String diretor) {
        return midias.stream()
                .filter(Movie.class::isInstance)
                .map(Movie.class::cast)
                .filter(m -> m.getDirector() != null &&
                        m.getDirector().getNome().equalsIgnoreCase(diretor.toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    private static Media searchByActor(List<Media> midias, List<Pessoa> atores,String nomeAtor) {

        Pessoa ator = atores.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nomeAtor.toUpperCase()))
                .findFirst()
                .orElse(null);

        if (ator == null || ator.getObra() == null) {
            System.out.println("Ator não encontrado ou sem obra associada!");
            return null;
        }

        String tituloObra = ator.getObra();
        return midias.stream()
                .filter(m -> m.getTitle() != null &&
                        m.getTitle().equalsIgnoreCase(tituloObra.toUpperCase()))
                .findFirst()
                .orElse(null);
    }


}