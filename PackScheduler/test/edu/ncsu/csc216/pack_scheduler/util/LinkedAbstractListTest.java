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
		// Test creating an abstract list with a negative capacity
		try {
			new LinkedAbstractList<String>(-1);
		} catch (IllegalArgumentException e) {
			assertEquals("message for calling the constructor with a negative integer", e.getMessage());
		}
	}
	
	/**
	 * Tests the set capacity method
	 */
	@Test
	public void testSetCapacity() {
		fail("Not yet implemented");
	}
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}
	
	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		fail("Not yet implemented");
	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
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
