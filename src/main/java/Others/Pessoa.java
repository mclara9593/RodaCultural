package Others;
import Midias.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static MenuUtils.MenuUtilities.getStringInput;
import static Midias.Media.getTitle;


public class Pessoa {
    public static String nome;
    public static String função;
    public static String obra;

    public String getNome() {
        return nome;
    }
    public static String getFunção() {
        return função;
    }
    public static String getObra() {
        return obra;
    }


    public static void setNome(String nome) {
        Pessoa.nome = nome;
    }
    public static void setFunção(String função) {
        Pessoa.função = função;
    }
    public static void setObra(String obra) {
        Pessoa.obra = obra;
    }

    public String getNomeInput() {
        System.out.println("Nome:");
        Scanner sc = new Scanner(System.in);
        nome=sc.nextLine();
        return nome;
    }

    public static String getFunçãoInput() {
        System.out.println("Nome:");
        Scanner sc = new Scanner(System.in);
        função=sc.nextLine();
        return função;
    }

}
