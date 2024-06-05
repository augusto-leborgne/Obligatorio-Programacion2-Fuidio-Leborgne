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
            System.out.println("3. Option 3");
            System.out.println("4. Option 4");
            System.out.println("5. Exit");
            System.out.println("6. Salir");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.next();
                System.out.print("Enter your choice: ");
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
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void option1(Reports report) {
        System.out.println("Option 1 selected.");

        ArrayList<String> top10 = report.top10("ZA", "2024-01-14");
        for (int i = 0; i < top10.size(); i++) {
            System.out.println(top10.get(i));
        }
    }

    private static void option2(Reports report) {
        System.out.println("Option 2 selected.");
        ArrayList<String> top5 = report.top5("2024-01-14");
        for (int i = 0; i < top5.size(); i++) {
            System.out.println(top5.get(i));
        }
    }

    private static void option3(Reports report) {
        System.out.println("Option 3 selected.");
        // Add your code for option 3 here
    }

    private static void option4(Reports report) {
        System.out.println("Option 4 selected.");
        // Add your code for option 4 here
    }

    private static void option5(Reports report) {
        System.out.println("Option 4 selected.");
        // Add your code for option 4 here
    }


}
