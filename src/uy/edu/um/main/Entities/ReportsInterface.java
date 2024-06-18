package uy.edu.um.main.Entities;

import uy.edu.um.adt.hash.KeyNullException;
import uy.edu.um.main.Exceptions.DatosInvalidosException;

import java.util.ArrayList;

public interface ReportsInterface {
    public ArrayList<String> top10(String p, String d) throws DatosInvalidosException, KeyNullException;

    public ArrayList<String> top5(String d) throws DatosInvalidosException, KeyNullException;

    public ArrayList<String> top7(String di, String df) throws DatosInvalidosException, KeyNullException;

    public int cantArtista(String a, String d, String p) throws DatosInvalidosException, KeyNullException;

    public int cantCanciones(Double ti, Double tf, String di, String df) throws DatosInvalidosException, KeyNullException;
}
