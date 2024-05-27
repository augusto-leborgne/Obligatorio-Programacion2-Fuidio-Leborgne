/**
 * 
 */
package uy.edu.um.adt.hash;

import uy.edu.um.adt.linkedlist.MyList;

public interface MyHash<K,T> {
	void resize();

	void resize();

	/**
	 * Agrega la clave dentro del hash
	 * @param key
	 * @param value
	 */
	void put(K key, T value);

	/**
	 * @param key
	 * @return
	 */
	T get(K key);

	/**
	 * Retorna true la clave existe false en caso contrario
	 * @param key
	 * @return
	 */
	boolean contains(K key);

	/**
	 * Remueve un elemento con la key indicada
	 * @param key
	 */
	void remove(K key);
	
	/**
	 * Obtiene la lista de keys disponibles del hash
	 * @return
	 */
	MyList<K> keys();

	/**
	 * Obtiene la lista de values disponibles del hash
	 * @return
	 */
	MyList<T> values();

	/**
	 * Obtiene la cantidad de elementos dentro del hash
	 * @return
	 */
	int size();
	
}
