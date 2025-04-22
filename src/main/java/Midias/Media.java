package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;

import static Midias.Books.*;
import static Midias.DigitalMedia.*;
import static Midias.Movie.*;
import static Midias.Season.*;
import static Midias.Show.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Scanner;
import static MenuUtils.MenuUtilities.*;

public class Media {
    private static String title;
    private static boolean status;
    private static int release_date;
    private static Gender gender;
    private static Pessoa author;
    private static Review review;

    //Getters and Setters
    public static String getTitle() {
        return title;
    }

    public static boolean isStatus() {
        return status;
    }

    public static int getRelease_date() {
        return release_date;
    }

    public static Gender getGender() {
        return gender;
    }

    public static Pessoa getAuthor() {
        return author;
    }

    public static Review getReview() {
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

    //Métodos para pegar a entrada dos atributos

    //Título
    public static String getTitleInput() {
        Scanner sc = new Scanner(System.in);
        title=sc.nextLine();
        return title;
    }

    //Status
    public static boolean getStatusInput() {
        Scanner sc = new Scanner(System.in);
        String sta=sc.nextLine();
        if(sta.equals("S"))
            status= Boolean.parseBoolean(String.valueOf(true));
        else if(sta.equals("N"))
            status= Boolean.parseBoolean(String.valueOf(false));
        return status;
    }

    //Gênero
    public static Gender getGenderInput() {
        System.out.println("Gêneros disponíveis:");
        for (Gender gender : Gender.values()) {
            System.out.println(gender.name() + " - " + gender.getDescricao());
        }
        System.out.println("Qual gênero corresponde ao seu título?:");
        Scanner sc = new Scanner(System.in);
        String entrada=sc.nextLine();
        return Media.gender.valueOf(entrada.toUpperCase());
    }

    //Autor
    public static Pessoa getAuthorInput() {
        Scanner sc = new Scanner(System.in);
        Pessoa author=new Pessoa();
        author.getNomeInput();
        return author;
    }

    //ToString
    public String toString() {
        return "Título: " + title + "\n" +
                "Status: " + (status ? "Concluído" : "Não concluído") + "\n" +
                "Ano lançamento: " + release_date + "\n" +
                "Gênero: " + gender + "\n" +
                "Autor: " + (author.getNome())+"\n" +
                "Nota: " + (this.review.getStars())+"\n" +
                "Avaliação: " + (this.review.getNote())+"\n" ;
    }

    //Construtor
    public Media(String title, boolean status, int release_date, Gender gender, Pessoa author) {
        this.title = title;
        this.status = status;
        this.release_date = release_date;
        this.gender = gender;
        this.author = author;
        this.review = review;
    }

    //Métodos

    //Cadastrar
    public static Media register(List<Media> mídias,List <Pessoa> atores) {
        System.out.print("Quantos itens vai cadastrar? ");
        Scanner sc = new Scanner(System.in);
        int a = lerInteiro(sc, 1, 4);
        Media media = null;

        //Cria o objeto
        Media result = null;
        for (int i = 0; i < a; i++) {

            //Verifica se o título é livro série ou filme
            System.out.println("Que tipo de mídia deseja cadastrar?"+ System.lineSeparator() +"F(Filme), S(Série) ou L(Livro):");
            String type = getMediaType();

            System.out.println("Título:");
            String titulo = getTitleInput();

            System.out.println("Digite o status: o título já foi visto/lido?" + System.lineSeparator() + "[S] ou [N]");
            Boolean status = getStatusInput();

            System.out.println("Ano de lançamento:");
            int release_date = lerInteiro(sc,1000,2025);

            System.out.println("Gênero:");
            Gender gender = getGenderInput();

            System.out.println("Autor-");
            Pessoa author = getAuthorInput();

            System.out.println("Onde encontrar?(plataforma,livraria ou cinema):");
            String onde = getOndeInput();


            //Entradas específicas para filme
            if (type.equals("F")) {
                List<Pessoa> cast = new ArrayList<>();
                System.out.println("Elenco:");
                cast = getCastInput(cast, mídias,titulo,atores);

                System.out.println("Duração:");
                int duration = getDurationInput();

                System.out.println("Diretor:");
                Pessoa director=getDirectorInput(titulo);


                System.out.println("Script:");
                String script = getScriptInput();

                //cria objeto tipo filme
                media = new Movie(title, status, release_date, author, gender,review, cast, onde, duration, director, script);

            }


            //Entradas específicas para livro
            else if (type.equals("L")) {
                System.out.println("ISBN:");
                int ISBN = getISBNInput();

                System.out.println("Possui cópias?: [S] ou [N]");
                boolean copy = getCopyInput();

                System.out.println("Editora:");
                String publisher = getPublisherInput();


                //cria objeto tipo livro
                media = new Books(title, status, release_date, author,review, gender, ISBN, copy, publisher);

            }
            //Entradas específicas para série
            if (type.equals("S")) {
                List<Pessoa> cast = new ArrayList<>();
                cast = getCastInput(cast, mídias,titulo,atores);

                int seasons_number = getSeasons_numberInput();
                List<Season> seasons = new ArrayList<>(); // Inicializa a lista de temporadas

                for (int n = 0; n < seasons_number; n++) {
                    System.out.println("Informações a respeito da temporada " + (n + 1) + ":");

                    //cria temporada
                    Season temporada = new Season(title, status, release_date,  gender,author,n);
                    temporada.setSeason_number(n+1);

                    System.out.println("Quantidade de episódios:");
                    temporada.setEpisodes_number(getEpisodes_numberInput());

                    //cria temporada na serie
                    seasons.add(temporada);
                }
                //cria objeto tipo serie
                media = new Show(title, status, release_date, author, gender, cast, onde);
                //Seta as temporadas
                ((Show)media).setSeasons(seasons);
            }

            //retorna título final
            result = media;

            //Inicializando avaliação em branco
            Review r = new Review();
            r.setNote("");
            r.setStars(0);
            result.setReview(r);



        }
        return result;
    }

    //Remover
    public static void remove(List<Media> Mídias){}

    //Busca
    public static Media search(List<Media> Mídias,List <Pessoa> atores) {
        System.out.println("Que tipo de mídia deseja buscar?" + System.lineSeparator() + "F(Filme), S(Série) ou L(Livro):");
        String typeMedia = getMediaType();

        String entrada="";
        //Busca por elementos específicos conforme tipo de mídia
        if (typeMedia.equalsIgnoreCase("L")) {
            System.out.print("Como deseja realizar a busca?" + System.lineSeparator() + " Título" + System.lineSeparator() + " Autor" + System.lineSeparator() + " Gênero" + System.lineSeparator() + " Ano" + System.lineSeparator() + " ISBN" + System.lineSeparator());
            entrada = getStringInput().toUpperCase();

        } else if (typeMedia.equalsIgnoreCase("F")) {
            System.out.print("Como deseja realizar a busca?" + System.lineSeparator() + " Título" + System.lineSeparator() + " Gênero" + System.lineSeparator() + " Diretor" + System.lineSeparator() + " Ator" + System.lineSeparator() + " Ano" + System.lineSeparator());
            entrada = getStringInput().toUpperCase();

        } else if (typeMedia.equalsIgnoreCase("S")) {}

        //Filtro por título
        Media encontrada = null;
        if (entrada.equals("TITULO") || typeMedia.equalsIgnoreCase("S")) {
            System.out.print("Digite o título que deseja buscar: ");

            String tituloBuscado = getStringInput().toUpperCase();
            encontrada = Mídias.stream()
                    .filter(m -> m.getTitle() != null && m.getTitle().equalsIgnoreCase(tituloBuscado))
                    .findFirst()
                    .orElse(null);
        }
            //Filtro por ano
        else  if (entrada.equals("ANO")) {
            System.out.print("Digite o ano de lançamento que deseja buscar: ");
                Scanner sc = new Scanner(System.in);
                int anoBuscado = lerInteiro(sc,1000,2025);
                encontrada = Mídias.stream()
                        .filter(m -> m.getRelease_date()==(anoBuscado))
                        .findFirst()
                        .orElse(null);
            }

            //Filtro por autor
            else if (entrada.equals("AUTOR")) {
            System.out.print("Digite o autor do titulo que deseja buscar: ");
               String autorBuscado= getStringInput().toUpperCase();
                encontrada = Mídias.stream()
                        .filter(m -> m.getAuthor().getNome() != null && m.getAuthor().getNome().equals(autorBuscado))
                        .findFirst()
                        .orElse(null);
            }

            // Filtro por gênero
            else if (entrada.equals("GÊNERO")) {
                System.out.print("Digite o gênero do título que deseja buscar: ");
                String genero = getStringInput().toUpperCase();
                encontrada = Mídias.stream()
                        .filter(m -> m.getGender() != null &&
                                m.getGender().name().equalsIgnoreCase(genero)) // Comparar com o nome do enum
                        .findFirst()
                        .orElse(null);
            }



            //Filtro por ISBN
            else if (entrada.equals("ISBN")) {
            System.out.print("Digite o código ISBN do título que deseja buscar: ");
                Scanner scan = new Scanner(System.in);
                System.out.print("Código do ISBN: ");
                int ISBNbuscado= scan.nextInt();

                encontrada = Mídias.stream ()
                        .map(m -> (Books)m)     //Cast para subtipo de mídia
                        .filter(m ->  m.getISBN()==ISBNbuscado)
                        .findFirst()
                        .orElse(null);
            }

            //Filtro por diretor
            else if (entrada.equals("DIRETOR")) {
            System.out.print("Digite o diretor do título que deseja buscar: ");
            String diretorBuscado= getStringInput().toUpperCase();

                encontrada = Mídias.stream()
                        .map(m -> (Movie)m)
                        .filter(m -> m.getDirector().getNome() != null && m.getDirector().getNome().equals(diretorBuscado))
                        .findFirst()
                        .orElse(null);

            }

            //Filtro por ator
            else if (entrada.equals("ATOR NO ELENCO")) {
                System.out.print("Digite o nome do ator: ");
                String AtorBuscado= getStringInput();

                //encontrar objeto ator pelo nome
                Pessoa ator = atores.stream()
                        .filter(p -> p.getNome() != null && p.getNome().equals(AtorBuscado))
                        .findFirst()
                        .orElse(null);

                //encontrar midia pelo nome do filme/serie que o ator fez parte
                String titulo =ator.getObra();
                encontrada = Mídias.stream()
                        .filter(m -> m.getTitle() != null && m.getTitle().equalsIgnoreCase(titulo))
                        .findFirst()
                        .orElse(null);

            }

            else{
            System.out.print("Não foi possível realizar a busca: ");
            }

        Media definitive = encontrada;
        return definitive;
    }
    public static Media doReview(List<Media> Mídias,List<Pessoa> atores){
        Scanner scan = new Scanner(System.in);

        System.out.print("Quantos itens vai avaliar? ");
        int a = scan.nextInt();
        scan.nextLine();

        Media find_reviewd = null;

        for (int i = 0; i < a; i++) {

            //busca a  midia a ser avalaida
            System.out.print("Informações a respeito do título a ser avaliado ");
            Media find=search(Mídias,atores);

            //verifica se a midia ja foi assistida/lida
            if (!find.isStatus()) {

                System.out.println("O título '"+ find.getTitle()+"' foi cadastrado como não visto/lido.Deseja alterar? [S] ou [N]");
                String mudar=getStringInput().toUpperCase();

                if (mudar.equals("N")) {
                    System.out.println("Não foi possível avaliar.");
                }else{
                    find.setStatus(true);

                    //avaliar
                    Review r = new Review();

                    //preenche avaliação de nota
                    System.out.println("Digite a nota que você atribui a esse título:");
                    int stars=lerInteiro(scan,1,5);
                    r.setStars(stars);

                    //preenche avaliação de texto
                    System.out.println("O que você achou de "+ find.getTitle()+" :");
                    String note = getStringInput().toUpperCase();
                    r.setNote(note);

                    //atribui avaliação completa à mídia
                    find.setReview(r);

                    if (find == null) {
                        System.out.println("Mídia não encontrada!");
                        continue; // Ou retorne null
                    }

                }

            }
            find_reviewd=find;
        }
       return find_reviewd;
    }


    public static void changeStatus(List<Media> Mídias, List <Pessoa> atores) {
        Media change=search(Mídias,atores);
        System.out.println("Digite o status: o título já foi visto/lido?"+ "[S] ou [N]");
        if (change.isStatus()) {
            System.out.println("O título foi cadastrado como visto/lido.Não é possível alterar");
        }
        else{
            Boolean status = getStatusInput();
        }
    }


//    Listas: o sistema deve apresentar ao usuário as listas de livros, séries e filmes. Essas
//    listas podem ser ordenadas pela avaliação – bem ou mal avaliados. Além disso, os
//    usuários podem filtrar essas listas por gênero e ano de lançamento.

    public static void order(List<Media> Mídias) {

        //saber qual lista buscar
        System.out.println("Que tipo de mídia deseja buscar?" + System.lineSeparator() + "F(Filme), S(Série) ou L(Livro):");
        String type = getMediaType();

        List<? extends Media> filteredList = List.of();

        if (type.equals("L")) {
            filteredList = Mídias.stream()
                    .filter(m -> m instanceof Books)
                    .map(m -> (Books) m)
                    .collect(Collectors.toList());
        }
        else if (type.equals("F")) {
            filteredList = Mídias.stream()
                    .filter(m -> m instanceof Movie)
                    .map(m -> (Movie) m)
                    .collect(Collectors.toList());
        }
        else if (type.equals("S")) {
            filteredList = Mídias.stream()
                    .filter(m -> m instanceof Show)
                    .map(m -> (Show) m)
                    .collect(Collectors.toList());
        }

        System.out.println("Como deseja buscar? "+ System.lineSeparator() + " Gênero" + System.lineSeparator() + " Ano" + System.lineSeparator() );
        String como=getStringInput().toUpperCase();

        if (como.equals("GÊNERO")) {
            Gender gender = getGenderInput();

            filteredList.stream()
                    .filter(m -> m.getGender() != null && m.getGender().equals(gender))
                    .forEach(System.out::println);

        } else if (como.equals("ANO")) {
            System.out.println("Digite o ano de lançamento:");
            int year = lerInteiro(new Scanner(System.in), 1000, 2025);
             filteredList.stream()
                    .filter(m -> m.getRelease_date()==(year))
                    .forEach(System.out::println);
        }
    }
}






