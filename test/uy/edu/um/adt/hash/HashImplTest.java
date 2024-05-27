package uy.edu.um.adt.hash;

import org.junit.Test;
import uy.edu.um.adt.linkedlist.MyList;

import static org.junit.Assert.*;

public class HashImplTest {

    @Test
    public void testFlujoNormal() {
        MyHash<Integer, String> hash = new MyHashImpl<>();

        hash.put(32, "Cancion1");
        hash.put(44, "Cancion2");
        hash.put(54, "Cancion3");


        assertEquals(3, hash.size());

        assertTrue(hash.contains(44));
        assertEquals("Cancion1", hash.get(32));
        hash.remove(54);

        assertEquals(2, hash.size());

        assertFalse(hash.contains(54));
        assertNull(hash.get(54));

        MyList<Integer> keys = hash.keys();

        assertEquals(2, keys.size());
        assertTrue(keys.contains(32));
        assertTrue(keys.contains(44));

        MyList<String> values = hash.values();

        assertEquals(2, values.size());
        assertTrue(values.contains("Cancion1"));
        assertTrue(values.contains("Cancion2"));
    }
}

