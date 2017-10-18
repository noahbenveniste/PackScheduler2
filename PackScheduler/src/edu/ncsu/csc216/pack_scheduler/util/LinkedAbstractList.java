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
	 * 
	 */
	public void add(int idx, E element) {
		
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
