package Model.Others;

/**
 * Classe que representa uma pessoa no sistema.
 *
 * @nome Seu Nome
 * @obra titulo da midia que participou
 */
public class Pessoa {
    public  String nome;
    public static String função;
    public static String obra;

    public Pessoa(String nome, String titulo) {
        this.nome = nome;
        this.obra = titulo;

    }

    public String getNome() {
        return nome;
    }
    public static String getFunção() {
        return função;
    }
    public static String getObra() {
        return obra;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFunção(String função) {
        this.função = função;
    }
    public void setObra(String obra) {
        this.obra = obra;
    }

}
