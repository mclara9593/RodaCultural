package Others;

public enum Gender {
    // Gêneros para filmes, livros e séries
    ACAO("Ação"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    FANTASIA("Fantasia"),
    TERROR("Terror"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense"),
    THRILLER("Thriller"),
    DOCUMENTARIO("Documentário"),
    ANIMACAO("Animação"),
    MUSICAL("Musical"),
    FAROESTE("Faroeste"),
    CRIME("Crime"),
    MISTERIO("Mistério"),
    HISTORICO("Histórico"),
    GUERRA("Guerra"),
    BIOGRAFIA("Biografia"),
    INFANTIL("Infantil"),
    FAMILIA("Família"),
    ESPORTE("Esporte"),
    SUPER_HEROIS("Super Heróis"),
    DISTOPICO("Distópico"),
    CYBERPUNK("Cyberpunk"),
    ESPACIAL("Espacial"),
    APOCALIPTICO("Apocalíptico");

    public String descricao;

    Gender(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}