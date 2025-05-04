package Others;

import Midias.Media;

import java.util.Scanner;

import static MenuUtils.MenuUtilities.getStringInput;
import static MenuUtils.MenuUtilities.lerInteiro;

public class Review {
    public static String note;
    public static int stars;

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static Review review(Scanner scan,Media find) {
        //avaliar
        Review r = new Review();

        //preenche avaliação de nota
        System.out.println("Digite a nota que você atribui a esse título:");
        int stars = lerInteiro(scan, 1, 5);
        r.setStars(stars);

        //preenche avaliação de texto
        System.out.println("O que você achou de " + find.getTitle() + " :");
        String note = getStringInput().toUpperCase();
        r.setNote(note);

        return r;
    }
}
