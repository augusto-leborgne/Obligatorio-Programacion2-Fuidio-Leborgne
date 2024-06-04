package uy.edu.um.main;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Reports report = new Reports();
        ArrayList<String> top10 = report.top10("ZA","2024-01-14");
        for (int i = 0; i < top10.size(); i++) {
            System.out.println(top10.get(i));
        }
    }
}
