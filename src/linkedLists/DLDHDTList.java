package linkedLists;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

import linkedLists.AbstractSLList.SNode;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		// ADD CODE HERE to generate empty linked list of this type 
		header = new DNode<>();
		trailer = new DNode<>();
		header.setNext(trailer);
		trailer.setPrev(header);
		length = 0;
		
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// ADD CODE HERE
		DNode<E> targetN = (DNode<E>) target;
		DNode<E> nuevoN = (DNode<E>) nuevo;
		
		nuevoN.setPrev(targetN.getPrev());
		nuevoN.setNext(targetN);
		targetN.setPrev(nuevoN);
		targetN.setNext(targetN.getNext());
		length++;

	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		if(length == 0)
			throw new NoSuchElementException("list is empty");
		
		DNode<E> targetN = (DNode<E>) target;
		if(targetN.getNext() == null)
			return null;
		else {
			return targetN.getNext();
		}
		
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if(length == 0)
			throw new NoSuchElementException("list is empty");
		
		
		DNode<E> targetN = (DNode<E>) target;
		
		if(targetN.getPrev() == null)
			return null;
		
		else {
			return targetN.getPrev();
		}
			
		 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		// ADD CODE HERE to disconnect target from the linked list, reduce lent, clean target...
		DNode<E> targetN = (DNode<E>) target;
		DNode<E> previousN = targetN.getPrev();
		DNode<E> nextN = targetN.getNext();
		
		previousN.setNext(nextN);
		nextN.setPrev(previousN);
		
		targetN.setNext(null);
		targetN.setPrev(null);
		
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
		DNode<E> dummyN = header;
		while(trailer.getNext() != null) {
			dummyN.setNext(null);
		}
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}
	
	public <T> T[] toArray(T[] array) { 
		if (array.length < this.length) { 
			array = (T[]) Array.newInstance(array.getClass().getComponentType(), this.length);
		} 
		else if (array.length > this.length){
			for (int j=this.length; j< array.length; j++){
				array[j] = null;
			}
		}
		SNode<T> fn = (SNode<T>)this.getFirstNode();
		for (int i=0; i < length; i++) {
			array[i] = fn.getElement();
			fn = fn.getNext();
		}
		return array;	
	}

	public Object[] toArray() { 
		Object[] array = new Object[this.length]; 
		SNode<E> fn = (SNode<E>)this.getFirstNode();
		for (int i=0; i < length; i++) {
			array[i] = fn.getElement();
			fn = fn.getNext();
		}
		return array;	
	}

}
