package uy.edu.um.adt.hash;

import uy.edu.um.adt.linkedlist.MyList;


/**
 * Implementacion de MyHash Cerrado, que se autodimensiona
 * si el factor de carga supera 0.75
 */
public class MyHashImpl<K, T> implements MyHash<K, T> {

	int size = 1;
	int count = 0;
	HashNode<K, T>[] hashMap = new HashNode[size];

	@Override
	public void resize() {
		size = size * 2;
		HashNode<K, T>[] oldHashMap = hashMap;
		HashNode<K, T>[] hashMap = new HashNode[size];

		for (int i = 0; i < oldHashMap.length; i++) {
			if (oldHashMap[i] != null) {
				if (!oldHashMap[i].isBorrado())
					put(oldHashMap[i].getKey(), oldHashMap[i].getData());
			}
		}
	}


	@Override
	public void put(K key, T value) {
		HashNode<K,T> nodo = new HashNode<K,T>(key,value);
		int pos = key.hashCode() % size;
		if (hashMap[pos] == null) {
			hashMap[pos] = nodo;
			count++;

		} else {
			for (int i=pos+1; i!=pos; i++){
				if (i != size){
					if (hashMap[i] == null){
						hashMap[i] = nodo;
						count++;
					}
				} else {
					i = 0;
				}
			}
		}

		if (count > (size * 0.75)){
			resize();
		}
	}

	@Override
	public T get(K key) {
		int pos = key.hashCode() % size;
		for (int i=pos; i!=pos-1; i++){
			if (i != size){
				if (hashMap[i] != null && hashMap[i].getKey().equals(key)){
					return hashMap[i].getData();
				}
			} else {
				i = 0;
			}
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		if (get(key) == null){
			return false;
		} else{
			return true;
		}
	}

	@Override
	public T remove(K key) {
		int pos = key.hashCode() % size;
		for (int i=pos; i!=pos-1; i++){
			if (i != size){
				if (hashMap[i] != null && hashMap[i].getKey().equals(key)){
					hashMap[i].setBorrado(true);
				}
			} else {
				i = 0;
			}
		}
		return null;
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
		int k = 0;

		return 0;
	}
}



