package uy.edu.um.main;

import uy.edu.um.adt.hash.KeyNullException;
import uy.edu.um.adt.hash.MyHashImpl;
import uy.edu.um.adt.heap.MyHeapImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reports {

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
}