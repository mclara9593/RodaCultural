package MenuUtils;

import Midias.Books;
import Midias.Media;
import Midias.Movie;
import Midias.Show;

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
        return scan.nextLine();
    }





}
