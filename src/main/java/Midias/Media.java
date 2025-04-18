package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static MenuUtils.MenuUtilities.getMediaType;
import static MenuUtils.MenuUtilities.lerInteiro;

public class Media {
    private static String title;
    private static boolean status;
    private static LocalDate release_date;
    private static Gender gender;
    private static Pessoa author;
    private static Review review;


    //Getters and Setters
    public static String getTitle() {
        Scanner sc = new Scanner(System.in);
        title=sc.nextLine();
        return title;}
    public void setTitle(String title) {
        this.title = title;
    }

    public static boolean isStatus() {
        Scanner sc = new Scanner(System.in);
        String sta=sc.nextLine();
        if(sta.equals("T"))
            status= Boolean.parseBoolean(String.valueOf(true));
        else if(sta.equals("F"))
            status= Boolean.parseBoolean(String.valueOf(false));
        return status;
    }
    public static void setStatus(boolean status) {
        Media.status = status;
    }

    public static LocalDate getRelease_date() {
        Scanner scan = new Scanner(System.in);
        String releaseDateStr = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate releaseDate = LocalDate.parse(releaseDateStr, formatter);
        return release_date;
    }
    public static void setRelease_date(LocalDate release_date) {
        Media.release_date = release_date;
    }


    public Gender getGender() {
        System.out.println("Gêneros disponíveis:");
        for (Gender gender : Gender.values()) {
            System.out.println(gender.name() + " - " + gender.getDescricao());
        }
        System.out.println("Qual gênero corresponde ao seu título?:");
        Scanner sc = new Scanner(System.in);
        String entrada=sc.nextLine();
        return Media.gender.valueOf(entrada.toUpperCase());
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }



    public Pessoa getAuthor() {
        Scanner sc = new Scanner(System.in);
        Pessoa author=new Pessoa();
        author.nome = sc.nextLine();
        author.função = sc.nextLine();
        author.função = sc.nextLine();
        return author;
    }
    public void setAuthor(Pessoa author) {
        this.author = author;
    }



    public static Review getReview() {
        return review;
    }

    public static void setReview(Review review) {
        Media.review = review;
    }

    //Métodos

    public static void register(List<Media> Mídias) {
        System.out.print("Quantos itens vai cadastrar? ");
        Scanner sc = new Scanner(System.in);
        int a = lerInteiro(sc, 1, 4);

        //Cria o objeto
        Media media = new Media();

        for (int i = 0; i < a; i++) {
            //Verifica se o título é livro série ou filme
            String type= getMediaType();

            System.out.println("Digite as informações a respeito do item " + (i + 1) + ":");
            System.out.println("Título:");
            String titulo=getTitle();



            if (type.equals("F")) {

            } else if (type.equals("L")) {

            }
            else if (type.equals("M")) {

            }
        }

        Mídias.add(media);
    }

    public static void remove(List<Media> Mídias){}

    public static void search(List<Media> Mídias){}

    public static void review(List<Media> Mídias){}

    public static void order(List<Media> Mídias){}

    public static void changeStatus(List<Media> Mídias){}



}




