/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;

/**
 * Unit tests for ArrayList
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 */
public class ArrayListTest {

	/**
	 * Test method for the ArrayList constructor.
	 */
	@Test
	public void testArrayList() {
		// Test creating a valid ArrayList
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		// Test that the list grows by adding at least 11 elements
		// Remember the list's initial capacity is 10
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		list.add(3, "d");
		list.add(4, "e");
		list.add(5, "f");
		list.add(6, "g");
		list.add(7, "h");
		list.add(8, "i");
		list.add(9, "j");
		list.add(10, "k");
		assertEquals(11, list.size());
	}

	/**
	 * Test for the size() method
	 */
	@Test
	public void testSize() {
		// Create the initial array
		ArrayList<String> list = new ArrayList<String>();
		// Check that initial size is zero
		assertEquals(0, list.size());
		// Add 3 elements
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		// Check that the size updated
		assertEquals(3, list.size());
		// Add more than the initial capacity and check that the size adjusts
		list.add(3, "d");
		list.add(4, "e");
		list.add(5, "f");
		list.add(6, "g");
		list.add(7, "h");
		list.add(8, "i");
		list.add(9, "j");
		list.add(10, "k");
		assertEquals(11, list.size());
		// Remove an element and check that the size adjusts
		list.remove(5);
		assertEquals(10, list.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		// Create an ArrayList
		ArrayList<String> list = new ArrayList<String>();
		// Check that initial size is zero
		assertEquals(0, list.size());
		// Test adding to an empty array list
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		assertEquals(3, list.size());
		// Test adding to the front of the list
		list.add(0, "d");
		assertEquals(4, list.size());
		assertEquals("d", list.get(0));
		assertEquals("a", list.get(1));
		assertEquals("b", list.get(2));
		assertEquals("c", list.get(3));
		// Test adding to the middle
		list.add(2, "e");
		assertEquals(5, list.size());
		assertEquals("d", list.get(0));
		assertEquals("a", list.get(1));
		assertEquals("e", list.get(2));
		assertEquals("b", list.get(3));
		assertEquals("c", list.get(4));
		// Test adding to the end
		list.add(5, "f");
		assertEquals(6, list.size());
		assertEquals("d", list.get(0));
		assertEquals("a", list.get(1));
		assertEquals("e", list.get(2));
		assertEquals("b", list.get(3));
		assertEquals("c", list.get(4));
		assertEquals("f", list.get(5));
		// Test adding a null element
		try {
			list.add(3, null);
			fail();
		} catch(NullPointerException e) {
			assertEquals("Cannot add null elements", e.getMessage());
		}
		// Test adding a repeat element
		try {
			list.add(3, "a");
		} catch(IllegalArgumentException e) {
			assertEquals("Cannot add repeat elements", e.getMessage());
		}
		// Test adding to an index below the boundary
		try {
			list.add(-1, "z");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals("Index is outside the accepatble range", e.getMessage());
		}
		// Test adding to an index above the boundary
		
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#get(int)}.
	 */
	@Test
	public void testGetInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSetIntE() {
		fail("Not yet implemented");
	}

}
