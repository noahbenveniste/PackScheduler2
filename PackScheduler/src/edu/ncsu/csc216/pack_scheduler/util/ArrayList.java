package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 */
public class ArrayList<E> extends AbstractList<E> {
	/** */
	private static final int INIT_SIZE = 10;
	/** */
	private E[] list;
	/** */
	private int size;
	/** */
	private int capacity;
	
	/**
	 * Constructs an empty ArrayList of size zero with an initial capacity of ten
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		Object[] o = new Object[INIT_SIZE];
		this.size = 0;
		this.list = (E[]) o;
		this.capacity = list.length;
	}
	
	/**
	 * Adds a desired element to the ArrayList at a specified index, right-shifting necessary values
	 * @param idx the zero-based index to add the new element at
	 * @param element the element to add to the ArrayList
	 * @throws NullPointerException if the added element is null
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater than the ArrayList's size
	 * @throws IllegalArgumentException if the added element is a duplicate of an element already in the list
	 */
	@Override
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (idx < 0 || idx > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < this.size(); i++) {
			if (this.list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		if (this.size() == this.capacity) { //Grow the array if list is full
			this.growArray();
		}
		if (idx == this.size()) { //Adding an element to the end of the list
			list[idx] = element;
		} else { //Add an element to the front or middle of the list
			//Right shift all values before the index to insert the new element
			for (int i = this.size; i > idx; i--) {
				list[i] = list[i - 1];
			}
			//Add the element to the desired index
			list[idx] = element;
		}
		//Increment the size of the ArrayList
		this.size++;
	}
	
	/**
	 * Used to grow the array if size == capacity; Doubles the capacity by default
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		//Update capacity
		this.capacity *= 2;
		//Create a new object array of double the capacity of the current array
		Object[] o = new Object[this.capacity];
		//Cast to generic type
		E[] temp = (E[]) o;
		//Assign the elements from the old array to the same index in the new array
		for (int i = 0; i < this.capacity; i++) {
			temp[i] = this.list[i];
		}
		//Assign the new array to the list field
		this.list = temp;
	}
	
	/**
	 * Removes an element from the ArrayList at a specified index, left-shifting all remaining elements
	 * and returning the removed element
	 * @param idx indicates the index of the element to remove from the ArrayList
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the specified index is out of bounds
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		//Get the element at the specified index
		E temp = list[idx];
		for (int i = idx; i < this.size() - 2; i++) {
			list[i] = list[i + 1];
		}
		//Set the repeated element at the end of the list to null
		list[this.size() - 1] = null;
		//Decrement the size
		this.size--;
		//Return the removed element
		return temp;
	}
	
	/**
	 * Overwrites the element at a specified index with a new element
	 * @return the old element at the specified index
	 * @throws NullPointerException if the added element is null
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater than the ArrayList's size
	 * @throws IllegalArgumentException if the added element is a duplicate of an element already in the list
	 */
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < this.size(); i++) {
			if (this.list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		E temp = list[idx];
		list[idx] = element;
		return temp;
	}
	
	/**
	 * Gets an element at a specified index
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the specified index is out of bounds
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		return this.list[idx];
	}

	/**
	 * Getter for the size field
	 * @return the size of the ArrayList i.e. how many elements it contains
	 */
	@Override
	public int size() {
		return this.size;
	}
}
