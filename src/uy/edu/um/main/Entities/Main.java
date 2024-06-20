package uy.edu.um.main.Entities;


import uy.edu.um.adt.hash.KeyNullException;
import uy.edu.um.main.Exceptions.DatosInvalidosException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DatosInvalidosException {
        Reports report = new Reports();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenu:");
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

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    oTop10(report);
                    break;
                case 2:
                    oTop5(report);
                    break;
                case 3:
                    oTop7(report);
                    break;
                case 4:
                    oCantArtista(report);
                    break;
                case 5:
                    oCantCanciones(report);
                    break;
                case 6:

                    break;
                default:
                    System.out.println("Numero inválido. Ingrese un número entre 1 y 6.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void oTop10(Reports report) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el país (código ISO): ");
        String pais = scanner.nextLine();
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        long startTime = System.currentTimeMillis();
        try {
            ArrayList<String> top10 = report.top10(fecha, pais);
            if (top10 != null) {
                System.out.println("\nTop 10 canciones del " + fecha + " en " + pais + ":");
                for (String song : top10) {
                    System.out.println(song);
                }
            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println("\nTiempo total de ejecución en milisegundos op1: " + totalTime + "\n");

        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage() + "\n");
        } catch (NullPointerException e) {
            System.out.println("\nNo se han encontrado resultados con los datos ingresados");
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.println("Memoria utilizada para operación: " + memoryUsed + " bytes");
    }

    private static void oTop5(Reports report) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        long startTime = System.currentTimeMillis();
        try {
            ArrayList<String> top5 = report.top5(fecha);
            if (top5 != null) {
                System.out.println("\nTop 5 canciones del " + fecha + ":");
                for (String song : top5) {
                    System.out.println(song);
                }

            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println("\nTiempo total de ejecución en milisegundos op2: " + totalTime + "\n");


        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage() + "\n");
        } catch (NullPointerException e) {
            System.out.println("\nNo se han encontrado resultados con los datos ingresados");
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.println("Memoria utilizada para operación: " + memoryUsed + " bytes");
    }

    private static void oTop7(Reports report) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String fechaI = scanner.nextLine();
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        String fechaF = scanner.nextLine();

        long startTime = System.currentTimeMillis();
        try {
            ArrayList<String> top7Artists = report.top7(fechaI, fechaF);

            if (!top7Artists.isEmpty()) {
                System.out.println("\nTop 7 artistas entre " + fechaI + " y " + fechaF + ":");
                for (String artist : top7Artists) {
                    System.out.println(artist);
                }
            }else{
                System.out.println("\nNo se han encontrado resultados con los datos ingresados");
            }
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println("\nTiempo total de ejecución en milisegundos op3: " + totalTime + "\n");

        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage() + "\n");
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.println("Memoria utilizada para operación: " + memoryUsed + " bytes");
    }

    private static void oCantArtista(Reports report) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del artista: ");
        String artista = scanner.nextLine();
        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el pais: ");
        String pais = scanner.nextLine();


        long startTime = System.currentTimeMillis();
        try {
            int count = report.cantArtista(artista, fecha, pais);
            System.out.println("\nEl artista " + artista + " aparece " + count + " veces en el top 50 del país " + pais + " el " + fecha + ".");

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println("\nTiempo total de ejecución en milisegundos op4: " + totalTime + "\n");

        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage() + "\n");
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.println("Memoria utilizada para operación: " + memoryUsed + " bytes");
    }

    private static void oCantCanciones(Reports report) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el rango de tempo mínimo (se redondea a 3 cifras decimales): ");
        BigDecimal minTempo = scanner.nextBigDecimal().setScale(3, RoundingMode.HALF_UP);
        System.out.print("Ingrese el rango de tempo máximo (se redondea a 3 cifras decimales): ");
        BigDecimal maxTempo = scanner.nextBigDecimal().setScale(3, RoundingMode.HALF_UP);
        scanner.nextLine();
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        long startTime = System.currentTimeMillis();
        try {
            int count = report.cantCanciones(minTempo, maxTempo, startDate, endDate);
            if (count != -1) {
                System.out.println("\nHay " + count + " canciones en el rango de tempo " + minTempo + "-" + maxTempo + " entre " + startDate + " y " + endDate + ".");
            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println("\nTiempo total de ejecución en milisegundos op5: " + totalTime + "\n");


        } catch (DatosInvalidosException | KeyNullException e) {
            System.err.println("Error: " + e.getMessage() + "\n");
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.println("Memoria utilizada para operación: " + memoryUsed + " bytes");
    }

    public void MamoriaUsada(int k){

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Llamar al método que queremos medir
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.println("Memoria utilizada: " + memoryUsed + " bytes");
    }
}
