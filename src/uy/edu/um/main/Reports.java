package uy.edu.um.main;

import uy.edu.um.adt.binarytree.MySearchBinaryTreeImpl;
import uy.edu.um.adt.binarytree.TreeNode;
import uy.edu.um.adt.hash.KeyNullException;
import uy.edu.um.adt.hash.MyHashImpl;
import uy.edu.um.adt.heap.MyHeapImpl;
import uy.edu.um.adt.linkedlist.MyList;
import uy.edu.um.adt.linkedlist.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Reports {

    public Reports(){

    }

    public ArrayList<String> top10(String p, String d) {
        MyHeapImpl<Integer> heap = new MyHeapImpl<>(true);
        MyHashImpl<Integer, String> hash = new MyHashImpl<>();

        try (BufferedReader in = new BufferedReader(new FileReader(new File("universal_top_spotify_songs.csv")))) {

            int line = 0;
            for (String x = in.readLine(); x != null; x = in.readLine()) {
                line++;
                String[] valores = x.split("\",\"");

                for (int i=0; i<valores.length; i++){
                    valores[i] = valores[i].replace("\"","");
                }

                if (line != 1 && valores[6].equals(p) && valores[7].equals(d)) {
                    int k = Integer.parseInt(valores[3]);
                    hash.put(k, x);
                    heap.insert(k);
                }
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


        } catch (IOException e) {
            System.out.println("File I/O error!");
            e.printStackTrace();
            return null;
        } catch (KeyNullException e) {
            System.out.println("Key Null ERROR!");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> top5(String d) {
        MyHeapImpl<Integer> heap = new MyHeapImpl<>(false);
        MyHashImpl<Integer, String> hash = new MyHashImpl<>();
        MySearchBinaryTreeImpl<String, String> bst = new MySearchBinaryTreeImpl<>();

        try (BufferedReader in = new BufferedReader(new FileReader(new File("universal_top_spotify_songs.csv")))) {

            int line = 0;
            for (String x = in.readLine(); x != null; x = in.readLine()) {
                line++;
                String[] valores = x.split("\",\"");

                for (int i=0; i<valores.length; i++){
                    valores[i] = valores[i].replace("\"","");
                }

                if (line != 1 && valores[7].equals(d)) {
                    String k = valores[0];
                    bst.add(k, x);
                }
            }

            MyList<TreeNode<String, String>> listKeys = bst.inOrder();
            for(Node<TreeNode<String, String>> temp = listKeys.getFirst(); temp != listKeys.getLast(); temp = temp.getNext()){
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


        } catch (IOException e) {
            System.out.println("File I/O error!");
            e.printStackTrace();
            return null;
        } catch (KeyNullException e) {
            System.out.println("Key Null ERROR!");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> top7(String di, String df){
        MyHeapImpl<Integer> heap = new MyHeapImpl<>(false);
        MyHashImpl<Integer, String> hash = new MyHashImpl<>();
        MySearchBinaryTreeImpl<String, String> bst = new MySearchBinaryTreeImpl<>();

        try (BufferedReader in = new BufferedReader(new FileReader(new File("universal_top_spotify_songs.csv")))) {

            int line = 0;
            for (String x = in.readLine(); x != null; x = in.readLine()) {
                line++;
                String[] valores = x.split("\",\"");

                for (int i=0; i<valores.length; i++){
                    valores[i] = valores[i].replace("\"","");
                }

                if (line != 1 && valores[7].compareToIgnoreCase(di) >= 0 && valores[7].compareToIgnoreCase(df) <= 0) {
                    String[] artistas = valores[2].split(", ");
                    for (int i=0; i<artistas.length; i++){
                        String k = artistas[i];
                        bst.add(k, x);
                    }
                }
            }

            MyList<TreeNode<String, String>> nodosArtistas = bst.inOrder();
            for(Node<TreeNode<String, String>> temp = nodosArtistas.getFirst(); temp != nodosArtistas.getLast(); temp = temp.getNext()){
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


        } catch (IOException e) {
            System.out.println("File I/O error!");
            e.printStackTrace();
            return null;
        } catch (KeyNullException e) {
            System.out.println("Key Null ERROR!");
            e.printStackTrace();
            return null;
        }
    }

    public int cantArtista (String a, String d, String p){
        int count = 0;

        try (BufferedReader in = new BufferedReader(new FileReader(new File("universal_top_spotify_songs.csv")))) {

            int line = 0;
            for (String x = in.readLine(); x != null; x = in.readLine()) {
                line++;
                String[] valores = x.split("\",\"");

                for (int i=0; i<valores.length; i++){
                    valores[i] = valores[i].replace("\"","");
                }

                if (line != 1 && valores[7].equals(d) && valores[6].equals(p)) {
                    String[] artistas = valores[2].split(", ");
                    for (int i=0; i<artistas.length; i++){
                        artistas[i] = artistas[i].replace("\"","");
                        if (artistas[i].equals(a)){
                            count++;
                        }
                    }
                }
            }

            return count;

        } catch (IOException e) {
            System.out.println("File I/O error!");
            e.printStackTrace();
            return 0;
        }
    }

    public int cantCanciones (Double ti, Double tf, String di, String df) {
        int count = 0;

        try (BufferedReader in = new BufferedReader(new FileReader(new File("universal_top_spotify_songs.csv")))) {

            int line = 0;
            for (String x = in.readLine(); x != null; x = in.readLine()) {
                line++;

                if (line != 1){
                    String[] valores = x.split("\",\"");
                    for (int i = 0; i < valores.length; i++) {
                        valores[i] = valores[i].replace("\"", "");
                    }
                    double td = Double.parseDouble(valores[23]);

                    if (valores[7].compareTo(di) >= 0 && valores[7].compareTo(df) <= 0
                            && td >= ti && td <= tf) {
                        count++;
                    }
                }
            }

            return count;

        } catch (IOException e) {
            System.out.println("File I/O error!");
            e.printStackTrace();
            return 0;
        }
    }
}