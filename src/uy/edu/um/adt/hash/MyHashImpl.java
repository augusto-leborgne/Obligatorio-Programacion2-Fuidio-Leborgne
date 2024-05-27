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

	public MyHashImpl() {
		this.size = 1;
		this.count = 0;
		this.hashMap = new HashNode[this.size];
	}

	@Override
	public void resize() throws KeyNullException {
		this.size = this.size * 2;
		HashNode<K, T>[] oldHashMap = this.hashMap;
		this.hashMap = new HashNode[this.size];
		this.count = 0; // Reset count

		for (int i = 0; i < oldHashMap.length; i++) {
			HashNode<K, T> node = oldHashMap[i];
			if (node != null && !node.isBorrado()) {
				put(node.getKey(), node.getData());
			}
		}
	}

	@Override
	public void put(K key, T value) throws KeyNullException {
		if (key == null)
			throw new KeyNullException();

		if (count > (this.size * 0.75)) {
			resize();
		}

		int pos = Math.abs(key.hashCode()) % size;
		int originalPos = pos;

		while (hashMap[pos] != null && !hashMap[pos].isBorrado() && !hashMap[pos].getKey().equals(key)) {
			pos = (pos + 1) % size;
			if (pos == originalPos) return;
		}

		if (hashMap[pos] == null || hashMap[pos].isBorrado()) {
			count++;
		}

		hashMap[pos] = new HashNode<>(key, value);
	}

	@Override
	public T get(K key) {
		if (key == null) return null;

		int pos = Math.abs(key.hashCode()) % size;
		int originalPos = pos;

		while (hashMap[pos] != null) {
			if (!hashMap[pos].isBorrado() && hashMap[pos].getKey().equals(key)) {
				return hashMap[pos].getData();
			}
			pos = (pos + 1) % size;
			if (pos == originalPos) return null;
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		return get(key) != null;
	}

	@Override
	public void remove(K key) {
		if (key == null) return;

		int pos = Math.abs(key.hashCode()) % size;
		int originalPos = pos;

		while (hashMap[pos] != null) {
			if (!hashMap[pos].isBorrado() && hashMap[pos].getKey().equals(key)) {
				hashMap[pos].setBorrado(true);
				count--;
				return;
			}
			pos = (pos + 1) % size;
			if (pos == originalPos) return;
		}
	}

	@Override
	public MyList<K> keys() {
		MyLinkedListImpl<K> keys = new MyLinkedListImpl<>();
		for (HashNode<K, T> node : hashMap) {
			if (node != null && !node.isBorrado()) {
				keys.add(node.getKey());
			}
		}
		return keys;
	}

	@Override
	public MyList<T> values() {
		MyLinkedListImpl<T> values = new MyLinkedListImpl<>();
		for (HashNode<K, T> node : hashMap) {
			if (node != null && !node.isBorrado()) {
				values.add(node.getData());
			}
		}
		return values;
	}

	@Override
	public int size() {
		return count;
	}
}

