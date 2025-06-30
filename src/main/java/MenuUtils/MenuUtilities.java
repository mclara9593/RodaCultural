package MenuUtils;
import Model.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MenuUtilities {
    public static int lerInteiro(Scanner scanner, int min, int max) {
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido!");
                scanner.next();
            }
            int numero = scanner.nextInt();
            scanner.nextLine();

            if (numero >= min && numero <= max) {
                return numero;
            }
            System.out.printf("Opção inválida! Digite um número entre %d e %d.%n", min, max);
        }
    }

    public static String getMediaType() {
        String type;
        do {
            Scanner scan = new Scanner(System.in);
            type = scan.nextLine().toUpperCase();
            if (!type.equals("L") && !type.equals("S") && !type.equals("F")) {
                System.out.println("Entrada inválida! Digite F, S ou L:");
            }
        } while (!type.equals("L") && !type.equals("S") && !type.equals("F"));

        return type;
    }

    public static String getStringInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().toUpperCase();
    }

//    public static List<? extends Media> getLists(List<Media> Mídias, String type) {
//        List<? extends Media> specify = new ArrayList<>();
//        //List<? extends Media> result = new ArrayList<>();
//        switch (type) {
//            case "B":
//                specify = Mídias.stream()
//                        .filter(m -> m instanceof Books)
//                        .map(m -> (Books) m)
//                        .collect(Collectors.toList());
//            case "M":
//                specify = Mídias.stream()
//                        .filter(m -> m instanceof Movie)
//                        .map(m -> (Movie) m)
//                        .collect(Collectors.toList());
//            case "S":
//                specify = Mídias.stream()
//                        .filter(m -> m instanceof Show)
//                        .map(m -> (Show) m)
//                        .collect(Collectors.toList());
//
//        }
//        return specify;
//    }

    public static List<Media> getLists(List<Media> midias, String tipo) {
        return midias.stream()
                .filter(m -> {
                    if (tipo.equals("B")) return m instanceof Books;
                    if (tipo.equals("M")) return m instanceof Movie;
                    if (tipo.equals("S")) return m instanceof Show;
                    return false;
                })
                .collect(Collectors.toList());
    }


}