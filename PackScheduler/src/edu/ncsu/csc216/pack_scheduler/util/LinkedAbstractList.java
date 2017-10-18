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
	 * 
	 */
	@Override
	public E get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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
