package uy.edu.um.main.Entities;


import uy.edu.um.adt.hash.KeyNullException;
import uy.edu.um.main.Exceptions.DatosInvalidosException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DatosInvalidosException {
        Reports report = new Reports();
        Scanner scanner = new Scanner(System.in);
        int choice;
        // Opciones

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el país (código ISO): ");
        String country = scanner.nextLine();
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        ArrayList<String> top10 = null;
        long startTime = System.currentTimeMillis();
        try {
            top10 = report.top10(country, date);
        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        if (top10 != null) {
            for (String song : top10) {
                System.out.println(song);
            }
        }
        System.out.println();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Tiempo total de ejecución en milisegundos op1: " + totalTime);
        System.out.println();

    }

    private static void option2(Reports report) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        ArrayList<String> top5 = null;
        long startTime = System.currentTimeMillis();
        try {
            top5 = report.top5(date);
        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        for (String song : top5) {
            System.out.println(song);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println();
        System.out.println("Tiempo total de ejecución en milisegundos op2: " + totalTime);
        System.out.println();
    }

    private static void option3(Reports report) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        ArrayList<String> top7Artists = null;
        long startTime = System.currentTimeMillis();
        try {
            top7Artists = report.top7(startDate, endDate);
        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        for (String artist : top7Artists) {
            System.out.println(artist);
        }
        System.out.println();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Tiempo total de ejecución en milisegundos op3: " + totalTime);
        System.out.println();
    }

    private static void option4(Reports report) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del artista: ");
        String artist = scanner.nextLine();
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Ingrese el pais: ");
        String pais = scanner.nextLine();


        int count = 0;
        long startTime = System.currentTimeMillis();
        try {
            count = report.cantArtista(artist, date, pais);
        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("El artista " + artist + " aparece " + count + " veces en el top 50 el " + date + ".");
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println();
        System.out.println("Tiempo total de ejecución en milisegundos op4: " + totalTime);
        System.out.println();
    }

    private static void option5(Reports report) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el rango de tempo mínimo: ");
        double minTempo = scanner.nextInt();
        System.out.print("Ingrese el rango de tempo máximo: ");
        double maxTempo = scanner.nextInt();
        scanner.nextLine();  // consume the newline
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        int count = 0;
        long startTime = System.currentTimeMillis();
        try {
            count = report.cantCanciones(minTempo, maxTempo, startDate, endDate);
        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Hay " + count + " canciones en el rango de tempo " + minTempo + "-" + maxTempo + " entre " + startDate + " y " + endDate + ".");
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println();
        System.out.println("Tiempo total de ejecución en milisegundos op5: " + totalTime);
        System.out.println();
    }
}
