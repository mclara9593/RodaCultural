package Midias;
import Others.Gender;
import Others.Pessoa;
import Others.Review;
import java.util.Comparator;
import java.util.stream.Collectors;
import static Midias.DigitalMedia.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static MenuUtils.MenuUtilities.*;
import static Others.Pessoa.lerPessoa;

public class Media {
    private  String title;
    private  boolean status;
    private  int release_date;
    private Gender gender;
    private  Pessoa author;
    private  Review review;
    protected String tipo;


    //Getters and Setters

    public  String getTitle() {
        return title;
    }

    public boolean isStatus() {
        return status;
    }

    public int getRelease_date() {
        return release_date;
    }

    public Gender getGender() {
        return gender;
    }

    public Pessoa getAuthor() {
        return author;
    }

    public Review getReview() {
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

    //Status
    public static boolean getBooleanInput() {
        boolean bool = false;
        String sta=getStringInput();
        if(sta.equals("S"))
            bool= Boolean.parseBoolean(String.valueOf(true));
        else if(sta.equals("N"))
            bool= Boolean.parseBoolean(String.valueOf(false));
        return bool;
    }

    //Gênero
    public static Gender getGenderInput() {
        System.out.println("Gêneros disponíveis:");
        for (Gender gender : Gender.values()) {
            System.out.println(gender.name() + " - " + gender.getDescricao());
        }
        System.out.println("Qual gênero corresponde ao seu título?:");
        String entrada=getStringInput();
        return Gender.valueOf(entrada);
    }


    //ToString
    public String toString() {
        return "Título: " + title + "\n" +
                "Status: " + (status ? "Concluído" : "Não concluído") + "\n" +
                "Ano lançamento: " + release_date + "\n" +
                "Gênero: " + gender + "\n" +
                "Autor: " + (author != null ? author.getNome() : "Desconhecido") + "\n" +
                "Avaliação: " + (review != null ? review.getStars() : "Nenhuma") + " estrelas\n" +
                "Nota: " + (review != null ? review.getNote() : "Nenhuma");
    }

    //Construtor
    public Media(String title, boolean status, int release_date, Gender gender, Pessoa author,Review review) {
        this.title = title;
        this.status = status;
        this.release_date = release_date;
        this.gender = gender;
        this.author = author;
        this.review = review;
    }

    //Métodos principais

    //Cadastrar
    public static Media register(List<Media> mídias,List <Pessoa> atores) {
        System.out.print("Quantos itens vai cadastrar? ");
        Scanner sc = new Scanner(System.in);
        int a = lerInteiro(sc, 1, 50);
        Media media = null;

        //Cria o objeto
        Media result = null;
        for (int i = 0; i < a; i++) {

            //Verifica se o título é livro série ou filme
            System.out.println("Que tipo de mídia deseja cadastrar?"+ System.lineSeparator() +"F(Filme), S(Série) ou L(Livro):");
            String type = getMediaType();

            //Pega a entrada de atributos

            System.out.println("Título:");
            String titulo = getStringInput();

            System.out.println("Digite o status: o título já foi visto/lido?" + System.lineSeparator() + "[S] ou [N]");
            Boolean status = getBooleanInput();

            System.out.println("Ano de lançamento:");
            int release_date = lerInteiro(sc,1000,2025);

            System.out.println("Gênero:");
            Gender gender = getGenderInput();

            System.out.println("Autor");
            Pessoa author=lerPessoa(titulo);
            author.setFunção("Autor");

            System.out.println("Onde encontrar?(plataforma,livraria ou cinema):");
            String onde = getStringInput();

            //Inicializando avaliação em branco
            Review review = new Review();
            review.setNote("");
            review.setStars(0);
            //media.setReview(r);

            //Entradas específicas para filme
            if (type.equals("F")) {

                //inicializa elenco
                List<Pessoa> cast = new ArrayList<>();
                System.out.println("Elenco:");
                cast = getCastInput(cast, mídias,titulo,atores);

                System.out.println("Duração (em minutos):");
                int duration = lerInteiro(sc, 0, 99999);

                //cria objeto diretor para tipo filme
                System.out.println("Diretor:");
                Pessoa director = lerPessoa(titulo);
                director.setFunção("Diretor");

                System.out.println("Script:");
                String script = getStringInput();

                //cria objeto tipo filme
                media =new Movie(titulo,status, release_date, author,gender,review, cast,onde,duration, director, script);
            }

                //Entradas específicas para livro
                else if (type.equals("L")) {
                System.out.println("ISBN:");
                int ISBN = lerInteiro(sc, 0, 99999);

                System.out.println("Possui cópias?: [S] ou [N]");
                boolean copy =getBooleanInput();

                System.out.println("Editora:");
                String publisher = getStringInput();

                //cria objeto tipo livro
                media = new Books(titulo, status, release_date, author,review, gender, ISBN, copy, publisher);

            }
                //Entradas específicas para série
                if (type.equals("S")) {

                System.out.println("Ano de encerramento:");
                int final_date = lerInteiro(sc,1000,2025);

                //inicializa elenco
                List<Pessoa> cast = new ArrayList<>();
                cast = getCastInput(cast, mídias,titulo,atores);

                //inicializa lista de temporadas
                    System.out.println("Quantidade de temporadas:");
                int seasons_number = lerInteiro(sc, 0, 99999);
                List<Season> seasons = new ArrayList<>(); // Inicializa a lista de temporadas

                for (int n = 0; n < seasons_number; n++) {
                    System.out.println("Informações a respeito da temporada " + (n + 1) + ":");

                    //cria temporada
                    Season temporada = new Season(titulo, status, release_date, gender,author,n,review);
                    temporada.setSeason_number(n+1);

                    System.out.println("Quantidade de episódios:");
                    temporada.setEpisodes_number(lerInteiro(sc, 0, 999999999));

                    //cria temporada na serie
                    seasons.add(temporada);
                }

                //cria objeto tipo serie
                media = new Show(titulo, status, release_date, author, gender,review, cast, onde,seasons_number);
                //Seta as temporadas
                ((Show)media).setSeasons(seasons);
            }

            result = media;

        }
        return result;
    }

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

        }


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
                int anoBuscado = lerInteiro(new Scanner(System.in),1000,2025);
                encontrada = Mídias.stream()
                        .filter(m -> m.getRelease_date()==(anoBuscado))
                        .findFirst()
                        .orElse(null);
            }

        //Filtro por autor
        else if (entrada.equals("AUTOR")) {
            System.out.print("Digite o autor do título que deseja buscar: ");
            String autor = getStringInput().toUpperCase();

            encontrada = Mídias.stream()
                    .filter(m ->
                    m.getAuthor().getNome().toUpperCase().contains(autor))
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
            int ISBN = lerInteiro(new Scanner(System.in), 0, 99999);

            encontrada = Mídias.stream()
                    .filter(m -> m instanceof Books)
                    .map(m -> (Books) m)
                    .filter(m -> m.getISBN() == ISBN)
                    .findFirst()
                    .orElse(null);
        }

        //Filtro por diretor
        else if (entrada.equals("DIRETOR")) {
            System.out.print("Digite o diretor do título que deseja buscar: ");
            String diretor= getStringInput().trim();

            encontrada = Mídias.stream()
                    .filter(m -> m instanceof Movie)
                    .map(m -> (Movie) m)
                    .filter(m ->
                    m.getDirector().getNome().equalsIgnoreCase(diretor))
                    .findFirst()
                    .orElse(null);
        }

        //Filtro por ator
        else if (entrada.equals("ATOR")) {
            System.out.print("Digite o nome do ator: ");
            String Ator = getStringInput();

            Pessoa ator = atores.stream()
                    .filter(p ->
                    p.getNome().equalsIgnoreCase(Ator))
                    .findFirst()
                    .orElse(null);

            if (ator != null && ator.getObra() != null) {
                String tituloObra = ator.getObra();

                encontrada = Mídias.stream()
                        .filter(m -> m.getTitle() != null &&
                                m.getTitle().equalsIgnoreCase(tituloObra))
                        .findFirst()
                        .orElse(null);
            } else {
                System.out.println("Ator não encontrado ou sem obra associada!");
            }
        }

        else{
            System.out.print("Não foi possível realizar a busca: ");
        }
        System.out.println("Resultado: " + encontrada);
        Media definitive = encontrada;
        return definitive;
    }

    //Fazer avalição
    public static Media doReview(List<Media> Mídias,List<Pessoa> atores){
        Scanner scan = new Scanner(System.in);
            //busca a  midia a ser avalaida
            System.out.print("Informações a respeito do título a ser avaliado "+ System.lineSeparator());
            Media find=search(Mídias,atores);

            //verifica se a midia ja foi assistida/lida
            if (!find.status) {
                System.out.println("O título '"+ find.getTitle()+"' foi cadastrado como não visto/lido.Deseja alterar? [S] ou [N]");
                String mudar=getStringInput().toUpperCase();

                if (mudar.equals("N")) {
                    System.out.println("Não foi possível avaliar.");
                }else{
                    find.setStatus(true);
                }
            }
                //avaliar
                Review r = new Review();

                //preenche avaliação de nota
                System.out.println();
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

                }
       return find;
    }


    public static Media changeStatus(Media change) {
        System.out.println("Digite o status: o título já foi visto/lido?"+ "[S] ou [N]");
        if (change.isStatus()) {
            System.out.println("O título foi cadastrado como visto/lido.Não é possível alterar");
        }
        else{
            Boolean status = getBooleanInput();
            change.setStatus(status);
        }
        return change;
    }

    public static void order(List<Media> Mídias) {

        //saber qual lista buscar
        System.out.println("Que tipo de lista deseja vizualizar?" + System.lineSeparator() + "F(Filme), S(Série),L(Livro) ou T(Todos os títulos):");
        String type = getMediaType();

        List<? extends Media> filteredList = List.of();
        System.out.println("Lista de títulos:"+ type);
        System.out.println();

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
        else if (type.equals("T")) {
            filteredList=Mídias;

        }

        System.out.println("Lista de"+ type);
        filteredList.stream().forEach(System.out::println);

        Scanner sc = new Scanner(System.in);
        System.out.println("Quantas buscas deseja realizar?");
        int num=lerInteiro(sc,1,50);

        System.out.println();
        for (int i = 0; i < num; i++) {

            System.out.println("Como deseja buscar? "+ System.lineSeparator() + " Gênero" + System.lineSeparator() + " Ano" + System.lineSeparator() +"Nota" );
            String como=getStringInput();

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
            else if (como.equals("NOTA")) {
            filteredList.stream()
                    .sorted(Comparator.comparing(m -> m.getReview().getStars()))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);

            }

        }
    }
}