package uy.edu.um.adt.hash;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import java.util.Arrays;


/**
 * Implementacion de MyHash Cerrado, que se autodimensiona
 * si el factor de carga supera 0.75
 */
public class MyHashImpl<K, T> implements MyHash<K, T> {

	int size = 17;
	int count = 0;
	HashNode<K, T>[] HashMap = new HashNode[size];

	@Override
	public void put(K key, T value) {
		HashNode<K,T> Nodo = new HashNode<K,T>(key,value);
		int code = (key.hashCode())%size;
		if (HashMap[code] == null) {
			HashMap[code] = Nodo;
			count++;

		}
		else {
			for (int i=code+1; i!=code; i++){
				if (i != size){
					if (HashMap[i] == null){
						HashMap[i] = Nodo;
						count++;
					}
				}
				else {
					i = 0;
				}
			}
		}

		if (count >= size*(0.75)){
			HashNode<K, T>[] HashMapn = new HashNode[(int) (size*1.1)];
			System.arraycopy(HashMap,0,HashMapn,0,size);
			HashMap = HashMapn;
			size = (int) (size*1.1);
		}
	}

	@Override
	public T get(K key) {

		return null;
	}

	@Override
	public boolean contains(K key) {
		return false;
	}

	@Override
	public void remove(K key) {

	}

	@Override
	public MyList<K> keys() {
		return null;
	}

	@Override
	public MyList<T> values() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
}




