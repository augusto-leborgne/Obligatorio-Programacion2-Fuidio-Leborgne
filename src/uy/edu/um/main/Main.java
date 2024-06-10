package uy.edu.um.main;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Reports report = new Reports();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Top 10 canciones en un país y un día dado.");
            System.out.println("2. Top 5 canciones que aparecen en más top 50 en un día dado.");
            System.out.println("3. Top 7 artistas en un rango de fechas");
            System.out.println("4. Cantidad de veces que aparece un artista en un top 50 con fecha dada");
            System.out.println("5. Cantidad de canciones en un rango de tempo y fechas");
            System.out.println("6. Salir");
            System.out.print("Ingrese la opción a elegir: ");

            while (!scanner.hasNextInt()) {
                System.out.println("ERROR. Ingrese un número entre 1 y 6.");
                scanner.next();
                System.out.print("Ingrese la opción a elegir: ");
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    option1(report);
                    break;
                case 2:
                    option2(report);
                    break;
                case 3:
                    option3(report);
                    break;
                case 4:
                    option4(report);
                    break;
                case 5:
                    option5(report);
                    break;
                case 6:

                    break;
                default:
                    System.out.println("Numero inválido. Ingrese un número entre 1 y 6.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void option1(Reports report) {
        ArrayList<String> top10 = report.top10("ZA", "2024-01-14");
        for (int i = 0; i < top10.size(); i++) {
            System.out.println(top10.get(i));
        }
    }

    private static void option2(Reports report) {
        ArrayList<String> top5 = report.top5("2024-01-14");
        for (int i = 0; i < top5.size(); i++) {
            System.out.println(top5.get(i));
        }
    }

    private static void option3(Reports report) {
        ArrayList<String> top7 = report.top7("2024-01-09", "2024-01-13");
        for (int i = 0; i < top7.size(); i++) {
            System.out.println(top7.get(i));
        }
    }

    private static void option4(Reports report) {
        int c = report.cantArtista("Travis Scott","2024-01-05", "AE");
        System.out.println("Travis Scott aparece " + c + " veces en la fecha y país dados.");
    }

    private static void option5(Reports report) {
        int c = report.cantCanciones(120.043, 121.100,"2024-01-09", "2024-01-13");
        System.out.println("Hay " + c + " canciones en el rango de tempo y fechas ingresados");
    }


}
