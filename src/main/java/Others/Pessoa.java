package Others;
import Midias.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static MenuUtils.MenuUtilities.getStringInput;


public class Pessoa {
    public  String nome;
    public static String função;
    public static String obra;

    public static Pessoa lerPessoa(String titulo){
        System.out.println("Nome:");
        String nome = getStringInput();

        Pessoa pessoa = new Pessoa(nome,titulo);
        return pessoa;
    }

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
