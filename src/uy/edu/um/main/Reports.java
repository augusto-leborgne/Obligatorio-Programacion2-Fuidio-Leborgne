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
        MyHeapImpl<String> heap = new MyHeapImpl<String>(false);
        MyHashImpl<String, String> hash = new MyHashImpl<String, String>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(new File("universal_top_spotify_songs.csv")));
            System.out.println("File open successful!");

            //Agregar lineas a hash y daily_ranks a heap
            int line = 0;
            for (String x = in.readLine(); x != null; x = in.readLine()) {
                line++;
                String[] valores = x.split(",");
                if(line != 1 && valores[6].equals(p) && valores[7].equals(d)){
                    hash.put(valores[3], x);
                    heap.insert(valores[3]);
                }
            }

            ArrayList<String> songs = new ArrayList<>();
            for(int i=0; i<heap.size();i++){
                if(Integer.parseInt(heap.get()) <= 10){
                    String k = heap.delete();
                    songs.add(hash.get(k));
                }
            }
            return songs;
        } catch (IOException e) {
            System.out.println("File I/O error!");
        } catch (KeyNullException e) {
            System.out.println("Key Null ERROR!");
        }
    }
}
