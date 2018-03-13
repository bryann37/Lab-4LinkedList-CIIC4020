package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		// TODO Auto-generated method stub
		
		((SNode<E>) nuevo).setNext(first); 
		first = (SNode<E>) nuevo; 
		length++;
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
		((SNode<E>) target).setNext((SNode<E>) nuevo); 
		
		SNode<E> targetN = (SNode<E>) target;
		SNode<E> nuevoN = (SNode<E>) nuevo;

		nuevoN.setNext(targetN.getNext());
		targetN.setNext(nuevoN);
		
		length++;
		
		
	}

	private Node<E> findNodePrevTo(Node<E> target) {
		// Pre: target is a node in the list
		if (target == first) 
			return null; 
		else { 
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev; 
		}
	}
	
	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		
		Node<E> previousNode = null;
		
		if (target == first)
			this.addFirstNode(nuevo); 
		else { 
			Node<E> prevNode = findNodePrevTo(target);  
			this.addNodeAfter(prevNode, nuevo); 
		}
		
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (first == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty..."); 
		
		// the linked list is not empty....
		return first;
		
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		 
			return last; 
		}
	

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		// TODO Auto-generated method stub
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if (aNode == null)  
			throw new NoSuchElementException("getNextNode(...) : target is the last node."); 
		else 
			return aNode;
		
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (target == last)  
			throw new NoSuchElementException("getPrevNode(...) : target is the first node."); 
		else 
			return findNodePrevTo(target);
	}

	public int length() {

		return this.length;
	}

	public void removeNode(Node<E> target) {
		// TODO Auto-generated method stub
		
		if (target == first) 
			first = first.getNext(); 
		if(target == last) {
			Node<E> prevNode = findNodePrevTo(target);  
			this.addNodeAfter(prevNode, last);
		}
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		((SNode<E>) target).clean();   // clear all references from target
		length--; 
		
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
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
