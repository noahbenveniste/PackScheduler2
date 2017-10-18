package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @param <E> Indicates that the list can store any object type
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** */
	private ListNode front;
	/** */
	private int size;
	/** */
	private int capacity;
	
	/**
	 * 
	 */
	public LinkedAbstractList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity cannot be less than zero.");
		} else if (capacity < this.size) {
			throw new IllegalArgumentException("Capacity cannot be less than current list's size.");
		}
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Adds an element to a specified index in the list
	 * @throws IllegalArgumentException if the list is full or if a duplicate element is added
	 * @throws NullPointerException if the added element is null
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater than the list's size
	 */
	public void add(int idx, E element) {
		//Check if the list is full
		if (this.size() == this.capacity) {
			throw new IllegalArgumentException("List is full.");
		}
		//Check for null input data
		if (element == null) {
			throw new NullPointerException("Cannot add null elements.");
		}
		//Check for duplicates
		ListNode current = this.front;
		for (int i = 0; i < this.size(); i++) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException("Cannot add duplicate elements.");
			}
			current = current.next;
		}
		//Check for out of bounds index
		if (idx < 0 || idx > this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the acceptable range.");
		}
		//Adding to the front of the list
		if (idx == 0) {
			ListNode newFront = new ListNode(element, this.front); //Make the new node point to the old front
			this.front = newFront; //Make the front field point to the new front;
		}
		//Adding to the middle or end of the list
		current = this.front;
		for (int i = 0; i < idx; i++) { //Traverse the list to get the reference to the old element at the index to add to
			current = current.next;
		}
		ListNode newNode = new ListNode(element, current); //Create a new node that points to the old element at the index to add to
		current = this.front;
		for (int i = 0; i < idx - 1; i++) { //Traverse the list to get to the index just before the index to add to
			current = current.next;
		}
		current.next = newNode; //Make the element at the index just before the index being added to point to the newly created node
	}
	
	/**
	 * 
	 */
	public E remove(int idx) {
		return null;
	}
	
	/**
	 * @return 
	 */
	public E set(int idx, E element) {
		return null;
	}
	
	/**
	 * Gets the element in the list at a specified index
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the specified index is less than zero or greater than
	 * or equal to the list's size
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the acceptable range.");
		}
		E data = null;
		ListNode current = this.front;
		for (int i = 0; i < this.size(); i++) {
			if (i == idx) {
				data =  current.data;
				break;
			}
			current = current.next;
		}
		return data;
	}

	/**
	 * Gets the list's current size i.e. the number of elements in contains
	 * @return the list's size
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 */
	private class ListNode {
		/** */
		private E data;
		/** */
		private ListNode next;
		
		/**
		 * 
		 * @param data
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * 
		 * @param data
		 * @param next
		 */
		public ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
	}
}
