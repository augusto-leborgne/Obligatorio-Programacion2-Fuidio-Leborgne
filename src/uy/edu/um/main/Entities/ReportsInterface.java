package uy.edu.um.main.Entities;

import uy.edu.um.adt.hash.KeyNullException;
import uy.edu.um.main.Exceptions.DatosInvalidosException;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ReportsInterface {
    public ArrayList<String> top10(String p, String d) throws DatosInvalidosException, KeyNullException;

    public ArrayList<String> top5(String d) throws DatosInvalidosException, KeyNullException;

    public ArrayList<String> top7(String di, String df) throws DatosInvalidosException, KeyNullException;

    public int cantArtista(String a, String d, String p) throws DatosInvalidosException, KeyNullException;

    public int cantCanciones(BigDecimal ti, BigDecimal tf, String di, String df) throws DatosInvalidosException, KeyNullException;
}
