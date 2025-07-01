package Model;
import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;
import java.util.List;


/**
 * Classe abstrata que representa uma mídia genérica.
 * Define atributos e comportamentos comuns a todos os tipos de mídia.
 */
public abstract class Media {
    private  String title;
    private  boolean status;
    private  int release_date;
    private Gender gender;
    private  Pessoa author;
    private  Review review;




    protected String tipo;

    // Getters e Setters com JavaDoc

    /**
     * Obtém o título da mídia.
     * @return Título da mídia
     */

    public  String getTitle() {
        return title;
    }
    /**
     * Verifica o status de conclusão da mídia.
     * @return true se concluída, false caso contrário
     */
    public boolean isStatus() {
        return status;
    }
    /**
     * Obtém o gênero da mídia.
     * @return Gênero da mídia
     */
    public int getRelease_date() {
        return release_date;
    }

    /**
     * Obtém o gênero da mídia.
     * @return Gênero da mídia
     */

    public Gender getGender() {
        return gender;
    }

    /**
     * Obtém o autor da mídia.
     * @return Autor da mídia
     */
    public Pessoa getAuthor() {
        return author;
    }


    /**
     * Obtém a avaliação da mídia.
     * @return Avaliação da mídia
     */
    public Review getReview() {
        return review;
    }


    /**
     * Define o título da mídia.
     * @param title Título da mídia
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Define o status da mídia.
     * @param status Título da mídia
     */
    public void setStatus(boolean status) {
        this.status=status;
    }

    /**
     * Define a data de lançamento da mídia.
     * @param release_date data de lançamento da mídia
     */
    public void setRelease_date(int release_date) {
        this.release_date=release_date;
    }
    /**
     * Define o gênero da mídia.
     * @param gender data de lançamento da mídia
     */
    public void setGender(Gender gender) {
        this.gender=gender;
    }
    /**
     * Define o autor da mídia.
     * @param author
     */
    public void setAuthor(Pessoa author) {
        this.author=author;
    }
    /**
     * Define a avaliação da mídia.
     * @param review
     */
    public void setReview(Review review) {
        this.review=review;
    }



    /**
     * Retorna uma representação em string da mídia.
     * @return String formatada com informações da mídia
     */
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

    /**
     * Construtor completo para uma mídia.
     * @param title Título
     * @param status Status de conclusão
     * @param release_date Ano de lançamento
     * @param gender Gênero
     * @param author Autor
     * @param review Avaliação
     */
    public Media(String title, boolean status, int release_date, Gender gender, Pessoa author,Review review) {
        this.title = title;
        this.status = status;
        this.release_date = release_date;
        this.gender = gender;
        this.author = author;
        this.review = review;
    }
    /**
     * Construtor vazio
     */
    public Media() {
        super(); // certifique-se de que Media também tem um construtor vazio
    }


    /**
     * Busca um livro com base no critério especificado.
     * @param midias Lista de mídias
     * @param entrada Critério de busca
     * @param campo Valor do campo de busca
     * @return Mídia encontrada ou null
     */
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

    /**
     * Busca um filme com base no critério especificado.
     * @param midias Lista de mídias
     * @param entrada Critério de busca
     * @param campo Valor do campo de busca
     * @return Mídia encontrada ou null
     */
    public static Media searchMovie(List<Media> midias, List<Pessoa> atores,String entrada,String campo) {

        switch (entrada) {
            case "TÍTULO": return searchByTitle(midias,campo);
            case "GÊNERO": return searchByGender(midias,campo);
            case "DIRETOR": return searchByDirector(midias,campo);
            case "ATOR": return searchByActor(midias,campo);
            case "ANO": return searchByYear(midias,campo);
            default:
                System.out.println("Opção inválida para filmes!");
                return null;
        }
    }

    /**
     * Busca uma serie com base no critério especificado.
     * @param midias Lista de mídias
     * @param title
     * @return Mídia encontrada ou null
     */
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

//    private static Media searchByActor(List<Media> midias, List<Pessoa> atores, String nomeAtor) {
//        System.out.println("\n=== INÍCIO DA BUSCA POR ATOR ===");
//        System.out.println("Nome do ator buscado: " + nomeAtor.toUpperCase());
//
//        // Print da lista de atores
//        System.out.println("\nLista completa de atores:");
//        atores.forEach(ator -> System.out.println(
//                " - Nome: " + ator.getNome() +
//                        " | Obra: " + ator.getObra() +
//                        " | Função: " + ator.getFunção()
//        ));
//
//        Pessoa ator = atores.stream()
//                .filter(p -> {
//                    System.out.println("\nComparando ator: " + p.getNome() +
//                            " com: " + nomeAtor.toUpperCase());
//                    return p.getNome().equalsIgnoreCase(nomeAtor.toUpperCase());
//                })
//                .findFirst()
//                .orElse(null);
//
//        if (ator == null) {
//            System.out.println("\n❌ Ator não encontrado na lista de atores!");
//            return null;
//        }
//
//        System.out.println("\n✅ Ator encontrado: " + ator.getNome());
//        System.out.println("Obra associada: " + ator.getObra());
//
//        if (ator.getObra() == null) {
//            System.out.println("⚠️ Ator encontrado mas sem obra associada!");
//            return null;
//        }
//
//        String tituloObra = ator.getObra();
//
//        // Print da lista de mídias
//        System.out.println("\nLista completa de mídias:");
//        midias.forEach(m -> System.out.println(
//                " - Título: " + m.getTitle() +
//                        " | Tipo: " + m.getClass().getSimpleName()
//        ));
//
//        return midias.stream()
//                .peek(m -> System.out.println("\nVerificando mídia: " + m.getTitle()))
//                .filter(m -> {
//                    System.out.println("Comparando título: " + m.getTitle() +
//                            " com obra do ator: " + tituloObra.toUpperCase());
//                    return m.getTitle() != null &&
//                            m.getTitle().equalsIgnoreCase(tituloObra.toUpperCase());
//                })
//                .peek(m -> System.out.println("🌟 Mídia encontrada: " + m.getTitle()))
//                .findFirst()
//                .orElse(null);
//    }
private static Media searchByActor(List<Media> midias, String nomeAtor) {
    String nomeBusca = nomeAtor.trim().toUpperCase();
    for (Media m : midias) {
        if (m instanceof Movie) {
            Movie filme = (Movie) m;
            if (filme.getCast() != null) {
                for (Pessoa ator : filme.getCast()) {
                    if (ator.getNome().toUpperCase().contains(nomeBusca)) {
                        return filme;
                    }
                }
            }
        }
    }
    return null;
}


}