package uy.edu.um.main;

import java.util.ArrayList;

public interface ReportsInterface {
    public ArrayList<String> top10(String p, String d);
    public ArrayList<String> top5(String d);
    public ArrayList<String> top7(String di, String df);

}
