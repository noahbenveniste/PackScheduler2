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
		// Test getting an element front the end of the list
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
		s1.add(2, "Pineapple");
		assertEquals(5, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		
		// Test adding a duplicate to a non-full list
		try {
			s1.add(2, "Pineapple");
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add duplicate elements.", e.getMessage());
		}
		assertEquals(5, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		
		// Test adding a null element to a non-full list
		try {
			s1.add(2, null);
		} catch (NullPointerException e) {
			assertEquals("Cannot add null elements.", e.getMessage());
		}
		assertEquals(5, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		
		// Test adding an element to index >= capacity
		try {
			s1.add(6, "Mango");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside the acceptable range.", e.getMessage());
		}
		assertEquals(5, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));

		try {
			s1.add(7, "Mango");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside the acceptable range.", e.getMessage());
		}
		assertEquals(5, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		
		// Test adding an element to index < 0
		try {
			s1.add(-1, "Mango");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside the acceptable range.", e.getMessage());
		}
		assertEquals(5, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		
		// Test adding an element to the end of the list
		s1.add(5, "Mango");
		assertEquals(6, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		assertEquals("Mango", s1.get(5));
		
		// Test adding an element to the front of the list when the list is full
		try {
			s1.add(0, "Grapefruit");
		} catch (IllegalArgumentException e) {
			assertEquals("List is full.", e.getMessage());
		}
		assertEquals(6, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		assertEquals("Mango", s1.get(5));
		
		// Test adding an element to the middle of the list when the list is full
		try {
			s1.add(2, "Grapefruit");
		} catch (IllegalArgumentException e) {
			assertEquals("List is full.", e.getMessage());
		}
		assertEquals(6, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		assertEquals("Mango", s1.get(5));
		
		//Test adding an element to the end of the list when the list is full
		try {
			s1.add(5, "Grapefruit");
		} catch (IllegalArgumentException e) {
			assertEquals("List is full.", e.getMessage());
		}
		assertEquals(6, s1.size());
		assertEquals("Kiwi", s1.get(0));
		assertEquals("Apple", s1.get(1));
		assertEquals("Pineapple", s1.get(2));
		assertEquals("Banana", s1.get(3));
		assertEquals("Orange", s1.get(4));
		assertEquals("Mango", s1.get(5));
	}

	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> s = new LinkedAbstractList<String>(5);
		
		// Try removing from an empty list
		try {
			s.remove(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside the acceptable range.", e.getMessage());
		}
		
		// Populate the list
		s.add(0, "a");
		s.add(1, "b");
		s.add(2, "c");
		s.add(3, "d");
		
		assertEquals("a", s.get(0));
		assertEquals("b", s.get(1));
		assertEquals("c", s.get(2));
		assertEquals("d", s.get(3));
		
		assertEquals(4, s.size());
		
		// Try removing from an index < 0
		try {
			s.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside the acceptable range.", e.getMessage());
		}
		
		// Try removing from an index > size
		try {
			s.remove(4);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is outside the acceptable range.", e.getMessage());
		}
	}

	/**
	 * Tests the size method
	 */
	@Test
	public void testSize() {
		LinkedAbstractList<String> s = new LinkedAbstractList<String>(5);
		s.add(0, "a");
		s.add(1, "b");
		s.add(2, "c");
		s.add(3, "d");
		s.add(4, "e");
		
		assertEquals("a", s.get(0));
		assertEquals("b", s.get(1));
		assertEquals("c", s.get(2));
		assertEquals("d", s.get(3));
		assertEquals("e", s.get(4));
		
		assertEquals(5, s.size());
	}
}
