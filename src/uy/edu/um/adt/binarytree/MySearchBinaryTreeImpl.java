/**
 * 
 */
package uy.edu.um.adt.binarytree;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

public class MySearchBinaryTreeImpl<K extends Comparable<K>, V> implements
        MySearchBinaryTree<K, V> {

	private TreeNode<K, V> root;

	@Override
	public void add(K key, V value) {
		TreeNode<K, V> elementToAdd = new TreeNode<>(key, value);

		if (root == null) {

			root = elementToAdd;

		} else {

			root.add(key, value);

		}
	}
	
	public TreeNode<K, V> find(K key) {

		return find(key, root);
	}

	private TreeNode<K, V> find(K keyToSearch, TreeNode<K, V> root) {
		TreeNode<K, V> node = null;
		
		if (root != null) {

			int nValue = keyToSearch.compareTo(root.getKey());

			if (nValue == 0) {
				
				node = root;
				
			} else if (nValue > 0) {
				
				node = find(keyToSearch, root.getRight());
				
			} else {

				node = find(keyToSearch, root.getLeft());

			}

		}

		return node;
	}

	public boolean contains(K key) {

		return contains(key, root);
	}

	private boolean contains(K keyToSearch, TreeNode<K, V> root) {
		boolean contains = false;
		
		if (root != null) {

			int nValue = keyToSearch.compareTo(root.getKey());

			if (nValue == 0) {

				contains = true;
				
			} else if (nValue > 0) {

				contains = contains(keyToSearch, root.getRight());
				
			} else {

				contains = contains(keyToSearch, root.getLeft());
				
			}

		}

		return contains;
	}


	@Override
	public void remove(K key) {

		if (root != null) {

			root = root.remove(key);

		}

	}

	@Override
	public MyList<TreeNode<K, V>> inOrder() {
		MyList<TreeNode<K, V>> inOrderTraverse = new MyLinkedListImpl<>();

		if (root != null) {


			root.inOrderTraverse(inOrderTraverse);

		}

		return inOrderTraverse;
	}

}
