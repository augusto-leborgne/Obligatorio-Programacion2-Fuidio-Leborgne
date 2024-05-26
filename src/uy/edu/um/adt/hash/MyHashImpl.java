package uy.edu.um.adt.hash;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;


/**
 * Implementacion de MyHash Cerrado, que se autodimensiona
 * si el factor de carga supera 0.75
 */
public class MyHashImpl<K, T> implements MyHash<K, T> {

	private int size;
	private int count;
	private HashNode<K, T>[] hashMap;

	public MyHashImpl(){
		this.size = 1;
		this.count = 0;
		this.hashMap = new HashNode[this.size];
	}

	@Override
	public void resize() {
		this.size = this.size * 2;
		HashNode<K, T>[] oldHashMap = this.hashMap;
		this.hashMap = new HashNode[this.size];

		for (int i = 0; i < oldHashMap.length; i++) {
			if (oldHashMap[i] != null && !oldHashMap[i].isBorrado()) {
				put(oldHashMap[i].getKey(), oldHashMap[i].getData());
			}
		}
	}

	@Override
	public void put(K key, T value) {
		HashNode<K, T> nodo = new HashNode<K, T>(key, value);
		int pos = key.hashCode() % size;
		if (this.hashMap[pos] == null) {
			this.hashMap[pos] = nodo;
			count++;

		} else {
			for (int i = pos + 1; i != pos; i++) {
				if (i != this.size) {
					if (this.hashMap[i] == null) {
						this.hashMap[i] = nodo;
						count++;
					}
				} else {
					i = 0;
				}
			}
		}

		if (count > (this.size * 0.75)) {
			resize();
		}
	}

	@Override
	public T get(K key) {
		int pos = key.hashCode() % this.size;
		for (int i = pos; i != pos - 1; i++) {
			if (i != this.size) {
				if (this.hashMap[i] != null && this.hashMap[i].getKey().equals(key)) {
					return this.hashMap[i].getData();
				}
			} else {
				i = 0;
			}
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void remove(K key) {
		int pos = key.hashCode() % size;
		for (int i = pos; i != pos - 1; i++) {
			if (i != this.size) {
				if (this.hashMap[i] != null && this.hashMap[i].getKey().equals(key)) {
					this.hashMap[i].setBorrado(true);
				}
			} else {
				i = 0;
			}
		}
	}

	@Override
	public MyList<K> keys() {
		MyLinkedListImpl<K> keys = new MyLinkedListImpl<>();
		for (int i=0; i< this.hashMap.length; i++){
			keys.add(this.hashMap[i].getKey());
		}
		return keys;
	}

	@Override
	public MyList<T> values() {
		MyLinkedListImpl<T> values = new MyLinkedListImpl<>();
		for (int i=0; i< this.hashMap.length; i++){
			values.add(this.hashMap[i].getData());
		}
		return values;
	}

	@Override
	public int size() {
		return this.size;
	}
}



