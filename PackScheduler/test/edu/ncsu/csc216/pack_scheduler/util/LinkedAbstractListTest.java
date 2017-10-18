package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests our implementation of a linked list
 * 
 * @author Kevin Hildner
 * @author Noah Benveniste
 */
public class LinkedAbstractListTest {

	/**
	 * Tests the constructor
	 */
	@Test
	public void testLinkedAbstractList() {
		// Test creating an abstract linked list with a negative capacity
		try {
			new LinkedAbstractList<String>(-1);
		} catch (IllegalArgumentException e) {
			assertEquals("Capacity cannot be less than zero.", e.getMessage());
		}

		// Test creating an abstract linked list with a capacity of zero
		LinkedAbstractList<String> s1 = new LinkedAbstractList<String>(0);
		assertEquals(0, s1.size());

		// Test creating an abstract linked list with a positive capacity
		LinkedAbstractList<String> s2 = new LinkedAbstractList<String>(3);
		assertEquals(0, s2.size());
		s2.add(0, "Apple");
		s2.add(1, "Banana");
		s2.add(2, "Orange");
		assertEquals(3, s2.size());

	}

	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		// Create a new abstract linked list and add elements
		LinkedAbstractList<String> s1 = new LinkedAbstractList<String>(4);
		s1.add(0, "Apple");
		s1.add(1, "Banana");
		s1.add(2, "Orange");

		// Test getting an element from the front of the list
		assertEquals("Apple", s1.get(0));
		// Test getting an element from the middle of the list
		assertEquals("Banana", s1.get(1));
		// Test getting an element fromt the end of the list
		assertEquals("Orange", s1.get(2));
	}

	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		// Create a new abstract linked list and add elements
		LinkedAbstractList<String> s1 = new LinkedAbstractList<String>(4);
		s1.add(0, "Apple");
		s1.add(1, "Banana");
		s1.add(2, "Orange");

		// Test setting at the front of the list
		s1.set(0, "Kiwi");
		assertEquals("Kiwi", s1.get(0));

		// Test setting at the middle of the list
		s1.set(1, "Watermelon");
		assertEquals("Watermelon", s1.get(1));

		// Test setting at the end of the list
		s1.set(2, "Pear");
		assertEquals("Pear", s1.get(2));
	}

	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		// Create a new abstract linked list and add elements in order
		LinkedAbstractList<String> s1 = new LinkedAbstractList<String>(6);
		s1.add(0, "Apple");
		s1.add(1, "Banana");
		s1.add(2, "Orange");
		assertEquals(3, s1.size());
		assertEquals("Apple", s1.get(0));
		assertEquals("Banana", s1.get(1));
		assertEquals("Orange", s1.get(2));

		// Test adding to the front
		s1.add(0, "Kiwi");
		assertEquals(4, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Banana", s1.get(2));
		assertEquals("Orange", s1.get(3));

		// Test adding to the middle

		// Test adding to the end

	}

	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the size method
	 */
	@Test
	public void testSize() {
		fail("Not yet implemented");
	}
}
