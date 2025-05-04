package Controller;

import Midias.Media;
import Others.Gender;
import Others.Pessoa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static MenuUtils.MenuUtilities.lerInteiro;
import static MenuUtils.SaveFile.load;
import static MenuUtils.SaveFile.save;

public class RodaCultural {
    public static void main(String[] args) throws IOException {

        List<Pessoa> atores = new ArrayList<>();
        List<Media> mídias = new ArrayList<>();

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\MenuUtils.json";
        Scanner scanlmenu = new Scanner(System.in);

        mídias = load(mídias, path);
        System.out.println("Mídias salvas localmente: " + mídias.size());


        while (true) {
            System.out.print("Bem vind@ à Roda Cultural!" + System.lineSeparator());
            System.out.print("O que deseja fazer? " + System.lineSeparator());
            System.out.print("1-CADASTRAR " + System.lineSeparator() +
                    "2-AVALIAR" + System.lineSeparator() +
                    "3-BUSCAR" + System.lineSeparator() +
                    "4-LISTAR" + System.lineSeparator() +
                    "5-SAIR" + System.lineSeparator());
            System.out.println();

            int opcaol = lerInteiro(scanlmenu, 1, 4);


            if (opcaol == 1) {
                Media midia = Media.register(mídias,atores);
                if (midia != null) {
                    mídias.add(midia);
                    if (mídias == null || mídias.isEmpty()) {
                        System.out.println("Nenhuma mídia cadastrada.");
                    }else{
                        System.out.println();
                        System.out.println("Título adiconado:");
                        System.out.println();
                        System.out.println(midia.toString());
                        System.out.println();
                    }
                    save(mídias,path);
                }
            }

            else if (opcaol == 2) {
                Media revisada = Media.doReview(mídias,atores);
                System.out.println();
                System.out.println(revisada.toString());
                System.out.println();

            }

            else if (opcaol == 3) {
                Media find  =Media.search(mídias,atores);
            }

            else if (opcaol == 4) {
                Media.order(mídias);
            }

            else if (opcaol == 5) {
                System.out.println("Saindo do sistema...");
                break;
            }
        }


    }

}