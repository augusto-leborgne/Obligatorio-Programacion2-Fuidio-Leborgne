/**
 *
 */
package uy.edu.um.adt.binarytree;

import uy.edu.um.adt.linkedlist.MyList;

public interface MySearchBinaryTree<K extends Comparable<K>, V> {

    void add(K key, V value);

    void remove(K key);

    boolean contains(K key);

    TreeNode<K, V> find(K key);

    MyList<TreeNode<K, V>> inOrder();

}
