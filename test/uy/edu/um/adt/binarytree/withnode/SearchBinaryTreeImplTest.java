package uy.edu.um.adt.binarytree.withnode;

import static org.junit.Assert.*;

import org.junit.Test;
import uy.edu.um.adt.binarytree.MySearchBinaryTree;
import uy.edu.um.adt.binarytree.MySearchBinaryTreeImpl;
import uy.edu.um.adt.binarytree.TreeNode;
import uy.edu.um.adt.linkedlist.MyList;

public class
SearchBinaryTreeImplTest {

	@Test
	public void testAddFlujoNormal() {
		MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();
		
		oTree.add(3, 3);
		oTree.add(21, 21);
		oTree.add(11, 11);
		oTree.add(-1, -1);
		oTree.add(4, 4);
		oTree.add(18, 18);
		
		MyList<TreeNode<Integer, Integer>> colValues = oTree.inOrder();
		
		assertEquals(Integer.valueOf(-1), colValues.get(0).getKey());
		assertEquals(Integer.valueOf(3), colValues.get(1).getKey());
		assertEquals(Integer.valueOf(4), colValues.get(2).getKey());
		assertEquals(Integer.valueOf(11), colValues.get(3).getKey());
		assertEquals(Integer.valueOf(18), colValues.get(4).getKey());
		assertEquals(Integer.valueOf(21), colValues.get(5).getKey());
	}

	@Test
	public void testRemoveFlujoNormal() {
		MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();
		
		oTree.add(3, 3);
		oTree.add(21, 21);
		oTree.add(11, 11);
		oTree.add(-1, -1);
		oTree.add(4, 4);
		oTree.add(18, 18);
		
		oTree.remove(3);
		oTree.remove(-1);
		oTree.remove(18);
		
		MyList<TreeNode<Integer, Integer>> colValues = oTree.inOrder();

		assertEquals(Integer.valueOf(4), colValues.get(0).getKey());
		assertEquals(Integer.valueOf(11), colValues.get(1).getKey());
		assertEquals(Integer.valueOf(21), colValues.get(2).getKey());
	}
	
	@Test
	public void testRemoveFlujoParticular() {
		MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();
		
		oTree.add(3, 3);
		oTree.add(-1, -1);
		oTree.add(4, 4);
		oTree.add(18, 18);
		
		oTree.remove(3);
		oTree.remove(-1);
		oTree.remove(18);
		oTree.remove(4);		
		
		MyList<TreeNode<Integer, Integer>> colValues = oTree.inOrder();

		assertEquals(0, colValues.size());
	}
	
	@Test
	public void testFindFlujoNormal() {
		MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();
		
		oTree.add(3, 3);
		oTree.add(21, 21);
		oTree.add(11, 11);
		oTree.add(-1, -1);
		oTree.add(4, 4);
		oTree.add(18, 18);
		
		oTree.remove(3);
		oTree.remove(-1);
		oTree.remove(18);
		
		TreeNode<Integer, Integer> oTemp = oTree.find(3);
		
		assertNull(oTemp);
		
		oTemp = oTree.find(21);
		
		assertEquals(Integer.valueOf(21), oTemp.getValue());

	}
	
	@Test
	public void testContainFlujoNormal() {
		MySearchBinaryTree<Integer, Integer> oTree = new MySearchBinaryTreeImpl<>();
		
		oTree.add(3, 3);
		oTree.add(21, 21);
		oTree.add(11, 11);
		oTree.add(-1, -1);
		oTree.add(4, 4);
		oTree.add(18, 18);
		
		assertFalse(oTree.contains(33));
		assertTrue(oTree.contains(-1));				
	}
	
}
