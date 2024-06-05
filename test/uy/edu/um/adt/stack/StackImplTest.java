package uy.edu.um.adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import org.junit.Test;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;

public class StackImplTest {

	@Test
	public void testFlujoCompleto() {
		MyStack<Integer> colStack = new MyLinkedListImpl<>();
		
		colStack.push(2);
		colStack.push(4);
		colStack.push(7);

		assertEquals(Integer.valueOf(7), colStack.peek());

		try {

			assertEquals(Integer.valueOf(7), colStack.pop());

		} catch (EmptyStackException e) {

			fail(e.getMessage());

		}

		assertEquals(Integer.valueOf(4), colStack.peek());

		try {

			assertEquals(Integer.valueOf(4), colStack.pop());

		} catch (EmptyStackException e) {

			fail(e.getMessage());

		}

		try {

			assertEquals(Integer.valueOf(2), colStack.pop());

		} catch (EmptyStackException e) {

			fail(e.getMessage());

		}
		try {
			
			colStack.pop();
			
			fail("El stack deberia estar vacio");

		} catch (EmptyStackException e) {
			
			assertTrue(true);

		}
	}

}
