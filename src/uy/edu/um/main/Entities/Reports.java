package uy.edu.um.main.Entities;

import uy.edu.um.adt.binarytree.MySearchBinaryTree;
import uy.edu.um.adt.binarytree.MySearchBinaryTreeImpl;
import uy.edu.um.adt.binarytree.TreeNode;
import uy.edu.um.adt.hash.KeyNullException;
import uy.edu.um.adt.hash.MyHash;
import uy.edu.um.adt.hash.MyHashImpl;
import uy.edu.um.adt.heap.MyHeap;
import uy.edu.um.adt.heap.MyHeapImpl;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import uy.edu.um.adt.linkedlist.Node;
import uy.edu.um.main.Exceptions.DatosInvalidosException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Reports implements ReportsInterface {
    private MyHash<String, MyHash<String, MyList<String>>> hashCancionesFechaPais;
    private MyHash<String, MyList<String>> hashCancionesFecha;
    private MyHash<String, MyHash<BigDecimal, MyList<String>>> hashCancionesFechaTempo;

    public Reports() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();

        this.hashCancionesFechaPais = new MyHashImpl<>();
        this.hashCancionesFecha = new MyHashImpl<>();
        this.hashCancionesFechaTempo = new MyHashImpl<>();

        try (BufferedReader in = new BufferedReader(new FileReader("universal_top_spotify_songs.csv"))) {

            int line = 0;
            for (String x = in.readLine(); x != null; x = in.readLine()) {
                line++;
                String[] valores = x.split("\",\"");

                for (int i = 0; i < valores.length; i++) {
                    valores[i] = valores[i].replace("\"", "");
                }

                if (line != 1) {
                    BigDecimal tempo = new BigDecimal(valores[23]).setScale(3, RoundingMode.HALF_UP);

                    MyHash<String, MyList<String>> hashPais = this.hashCancionesFechaPais.get(valores[7]);
                    MyList<String> listaCancionesFP = new MyLinkedListImpl<>();
                    MyList<String> listaCancionesF = this.hashCancionesFecha.get(valores[7]);
                    MyHash<BigDecimal, MyList<String>> hashTempo = this.hashCancionesFechaTempo.get(valores[7]);
                    MyList<String> listaCancionesFT = new MyLinkedListImpl<>();

                    if (hashPais == null) {
                        hashPais = new MyHashImpl<>();
                        listaCancionesFP.add(x);
                        hashPais.put(valores[6], listaCancionesFP);
                        this.hashCancionesFechaPais.put(valores[7], hashPais);

                    } else {
                        listaCancionesFP = hashPais.get(valores[6]);

                        if (listaCancionesFP == null) {
                            listaCancionesFP = new MyLinkedListImpl<>();
                            listaCancionesFP.add(x);
                            hashPais.put(valores[6], listaCancionesFP);

                        } else {
                            listaCancionesFP.add(x);
                        }
                    }

                    if (listaCancionesF == null) {
                        listaCancionesF = new MyLinkedListImpl<>();
                        listaCancionesF.add(x);
                        this.hashCancionesFecha.put(valores[7], listaCancionesF);
                    } else {
                        listaCancionesF.add(x);
                    }

                    if (hashTempo == null) {
                        hashTempo = new MyHashImpl<>();
                        listaCancionesFT.add(valores[0]);
                        hashTempo.put(tempo, listaCancionesFT);
                        this.hashCancionesFechaTempo.put(valores[7], hashTempo);

                    } else {
                        listaCancionesFT = hashTempo.get(tempo);

                        if (listaCancionesFT == null) {
                            listaCancionesFT = new MyLinkedListImpl<>();
                            listaCancionesFT.add(valores[0]);
                            hashTempo.put(tempo, listaCancionesFT);

                        } else {
                            listaCancionesFT.add(valores[0]);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("File I/O error!");
            e.printStackTrace();
        } catch (KeyNullException e) {
            System.out.println("Key Null ERROR!");
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("\nTiempo total de ejecución (en milisegundos) para carga de datos: " + totalTime + "\n");

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.println("Memoria utilizada para carga de datos: " + memoryUsed + " bytes");
    }

    public ArrayList<String> top10(String d, String p) throws DatosInvalidosException, KeyNullException {
        if (p == null || d == null || !esFormatoValido(d)) {
            throw new DatosInvalidosException("Datos Inválidos");
        }

        MyHeap<Integer> heap = new MyHeapImpl<>(true);
        MyHash<Integer, String> hash = new MyHashImpl<>();

        MyList<String> listaCanciones = this.hashCancionesFechaPais.get(d).get(p);

        for (Node<String> nodeTemp = listaCanciones.getFirst(); nodeTemp != null; nodeTemp = nodeTemp.getNext()) {
            String[] valores = nodeTemp.getValue().split("\",\"");

            for (int i = 0; i < valores.length; i++) {
                valores[i] = valores[i].replace("\"", "");
            }

            int k = Integer.parseInt(valores[3]);
            hash.put(k, valores[1]);
            heap.insert(k);
        }


        ArrayList<String> songs = new ArrayList<>();
        int count = 0;
        while (count < 10 && heap.size() > 0) {
            int k = heap.delete();
            String songData = hash.get(k);
            if (songData != null) {
                songs.add(songData);
                count++;
            }
        }

        return songs;
    }


    public ArrayList<String> top5 (String d) throws DatosInvalidosException, KeyNullException {
        if (d == null || !esFormatoValido(d)) {
            throw new DatosInvalidosException("Datos Inválidos");
        }

        MyHeapImpl<Integer> heap = new MyHeapImpl<>(false);
        MyHashImpl<Integer, String> hash = new MyHashImpl<>();
        MySearchBinaryTree<String, String> bst = new MySearchBinaryTreeImpl<>();

        MyList<String> listaCanciones = this.hashCancionesFecha.get(d);

            for (Node<String> nodeTemp = listaCanciones.getFirst(); nodeTemp != null; nodeTemp = nodeTemp.getNext()) {
                String x = nodeTemp.getValue();
                String[] valores = x.split("\",\"");

                for (int i = 0; i < valores.length; i++) {
                    valores[i] = valores[i].replace("\"", "");
                }

                bst.add(valores[0], valores[1]);
            }

            MyList<TreeNode<String, String>> listKeys = bst.inOrder();
            for (Node<TreeNode<String, String>> temp = listKeys.getFirst(); temp != listKeys.getLast(); temp = temp.getNext()) {
                int c = temp.getValue().getCount();
                String v = temp.getValue().getValue();
                hash.put(c, v);
                heap.insert(c);
            }

            ArrayList<String> songs = new ArrayList<>();
            int count = 0;
            while (count < 5 && heap.size() > 0) {
                int c = heap.delete();
                String songData = hash.get(c);
                if (songData != null) {
                    songs.add(songData);
                    count++;
                }
                hash.remove(c);
            }

            return songs;
    }


    public ArrayList<String> top7 (String di, String df) throws DatosInvalidosException, KeyNullException {
        if (di == null || df == null || !esFormatoValido(di) || !esFormatoValido(df)) {
            throw new DatosInvalidosException("Datos Inválidos");
        }

        MyHeapImpl<Integer> heap = new MyHeapImpl<>(false);
        MyHashImpl<Integer, String> hash = new MyHashImpl<>();
        MySearchBinaryTreeImpl<String, String> bst = new MySearchBinaryTreeImpl<>();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (LocalDate fecha = LocalDate.parse(di, formato); !fecha.isAfter(LocalDate.parse(df, formato)); fecha = fecha.plusDays(1)) {
            String fechaString = fecha.toString();
            MyList<String> listaCanciones = this.hashCancionesFecha.get(fechaString);

            if (listaCanciones != null) {
                for (Node<String> nodo = listaCanciones.getFirst(); nodo != null; nodo = nodo.getNext()) {
                    String x = nodo.getValue();
                    String[] valores = x.split("\",\"");

                    for (int i = 0; i < valores.length; i++) {
                        valores[i] = valores[i].replace("\"", "");
                    }

                    String[] artistas = valores[2].split(", ");
                    for (String k : artistas) {
                        bst.add(k, x);
                    }
                }
            }
        }

        MyList<TreeNode<String, String>> nodosArtistas = bst.inOrder();
        for (Node<TreeNode<String, String>> temp = nodosArtistas.getFirst(); temp != nodosArtistas.getLast(); temp = temp.getNext()) {
            int c = temp.getValue().getCount();
            String a = temp.getValue().getKey();
            hash.put(c, a);
            heap.insert(c);
        }

        ArrayList<String> songs = new ArrayList<>();
        int count = 0;
        while (count < 7 && heap.size() > 0) {
            int c = heap.delete();
            String songData = hash.get(c);
            if (songData != null) {
                songs.add(songData);
                count++;
            }
            hash.remove(c);
        }

        return songs;
    }


    public int cantArtista(String a, String d, String p) throws DatosInvalidosException, KeyNullException {
        if (a == null || d == null || p == null || !esFormatoValido(d)) {
            throw new DatosInvalidosException("Datos Inválidos");
        }
        int count = 0;

        try {
            MyList<String> listaCanciones = this.hashCancionesFechaPais.get(d).get(p);

            if (listaCanciones != null) {
                for (Node<String> nodo = listaCanciones.getFirst(); nodo != null; nodo = nodo.getNext()) {
                    String x = nodo.getValue();
                    String[] valores = x.split("\",\"");

                    for (int i = 0; i < valores.length; i++) {
                        valores[i] = valores[i].replace("\"", "");
                    }

                    String[] artistas = valores[2].split(", ");
                    for (int i = 0; i < artistas.length; i++) {
                        artistas[i] = artistas[i].replace("\"", "");
                        if (artistas[i].equals(a)) {
                            count++;
                        }
                    }
                }
            }

            return count;

        } catch (NullPointerException e) {
            return 0;
        }
    }


    public int cantCanciones(BigDecimal ti, BigDecimal tf, String di, String df) throws DatosInvalidosException, KeyNullException {
        if (ti == null || tf == null || di == null || df == null || ti.compareTo(new BigDecimal("0.000")) <= 0 || tf.compareTo(new BigDecimal("0.000")) <= 0 || ti.compareTo(tf) > 0 || !esFormatoValido(di) || !esFormatoValido(df)) {
            throw new DatosInvalidosException("Datos Inválidos");
        }

        MySearchBinaryTree<String, String> bst = new MySearchBinaryTreeImpl<>();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (LocalDate fecha = LocalDate.parse(di, formato); !fecha.isAfter(LocalDate.parse(df, formato)); fecha = fecha.plusDays(1)) {
            String fechaString = fecha.toString();

            if(this.hashCancionesFecha.get(fechaString) != null) {

                for (BigDecimal t = ti; t.compareTo(tf) <= 0; t = t.add(BigDecimal.valueOf(0.001)).setScale(3, RoundingMode.HALF_UP)) {
                    MyList<String> listaCanciones = this.hashCancionesFechaTempo.get(fechaString).get(t);

                    if (listaCanciones != null) {
                        for (Node<String> node = listaCanciones.getFirst(); node != null; node = node.getNext()) {
                            bst.add(node.getValue(), node.getValue());
                        }
                    }
                }
            }
        }

        return bst.inOrder().size();
    }



    public boolean esFormatoValido(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(fecha, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}