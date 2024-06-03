package uy.edu.um.main;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Reports report = new Reports();
        for (int i = 0; i < report.top10("BG","2024-05-13").size(); i++) {
            System.out.println(report.top10("BG", "2024-05-13").get(i));
        }
    }
}
