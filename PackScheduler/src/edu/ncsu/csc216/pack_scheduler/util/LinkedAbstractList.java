package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @param <E> Indicates that the list can store any object type
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** Reference that points to the node at the front of the list */
	private ListNode front;
	/** The number of elements in the list */
	private int size;
	/** The number of elements the list can store */
	private int capacity;
	
	/**
	 * Constructs a LinkedAbstractList object of a specified capacity.
	 * @throws IllegalArgumentException if the capacity is less than zero or less than the current list's size
	 */
	public LinkedAbstractList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity cannot be less than zero.");
		} else if (capacity < this.size) {
			throw new IllegalArgumentException("Capacity cannot be less than current list's size.");
		}
		this.front = null;
		this.size = 0;
		this.capacity = capacity;
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
			this.size++;
			return;
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
		this.size++;
		return;
	}
	
	/**
	 * Removes an element at a specified index
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the specified index is out of bounds
	 */
	public E remove(int idx) {
		//Check for out of bounds index
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the acceptable range.");
		}
		//Removing from the front of the list
		if (idx == 0) {
			E oldData = front.data;
			front = front.next;
			this.size--;
			return oldData;
		}
		//Removing from the middle or end of the list
		ListNode current = this.front;
		ListNode previous = null;
		for (int i = 0; i < idx; i++) { //Traverse the list to get the reference to the element to remove
			previous = current;
			current = current.next;
		}
		E oldData = current.data; //Store the data at the element to be removed
		previous.next = current.next; //Makes the node before the node to remove point to the node after the node to be removed
		this.size--;
		return oldData;
	}
	
	/**
	 * Overrides the data value at a given index in the list
	 * @return the overridden element
	 * @throws NullPointerException if the element to set is null
	 * @throws IllegalArgumentException if a duplicate element is added
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public E set(int idx, E element) {
		//Check for null elements
		if (element == null) {
			throw new NullPointerException("Cannot set null elements.");
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
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException("Index is outside the acceptable range.");
		}
		current = this.front;
		for (int i = 0; i < idx; i++) { //Traverse the list to get the reference to the element to change the data
			current = current.next;
		}
		E oldData = current.data; //Get the old data from the element
		current.data = element; //Overwrite the data in the node with the new data
		return oldData; //Return the old data value
	}
	
	/**
	 * Gets the element in the list at a specified index
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the specified index is less than zero or greater than
	 * or equal to the list's size
	 */
	@Override
	public E get(int idx) {
		//Check for out of bounds index
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
		/** The data element being stored */
		private E data;
		/** The reference to the next node in the list */
		private ListNode next;
		
		
//		/**
//		 * Constructs a ListNode with just a data field passed
//		 * @param data the data element to store
//		 */
//		public ListNode(E data) {
//			this(data, null);
//		}
		
		/**
		 * Constructs a ListNode with a data field and a next node reference passed
		 * @param data the data element to store
		 * @param next the reference to the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
