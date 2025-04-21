package Midias;

import Others.Gender;
import Others.Pessoa;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static MenuUtils.MenuUtilities.lerInteiro;

public class RodaCultural {
    public static void main(String[] args) {
        List<Pessoa> cast = new ArrayList<>();  // Nome de variável em minúsculo
        List<Media> mídias = new ArrayList<>(); // Nome de variável em minúsculo
        Scanner scanlmenu = new Scanner(System.in);

        while (true) {
            System.out.print("Bem vind@ à Roda Cultural!" + System.lineSeparator());
            System.out.print("O que deseja fazer? " + System.lineSeparator());
            System.out.print("1-CADASTRAR " + System.lineSeparator() +
                    "2-AVALIAR" + System.lineSeparator() +
                    "3-BUSCAR" + System.lineSeparator() +
                    "4-SAIR" + System.lineSeparator());

            int opcaol = lerInteiro(scanlmenu, 1, 4);

            if (opcaol == 1) {
                Media midia = Media.register(cast,mídias);
                if (midia != null) {
                    mídias.add(midia);
                }
            }
            else if (opcaol == 2) {
                Media revisada = Media.review(mídias);
            } else if (opcaol == 3) {
                Media encontrada  =Media.search(mídias);
            } else if (opcaol == 4) {
                System.out.println("Saindo do sistema...");
                break;
            }
        }


    }

}