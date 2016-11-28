import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T> {
	
	/*************************************************
	 * 
	 * 			Variables
	 * 
	 *************************************************/
	
	private Node<T> root;
	Comparator<T> comparator;
	
	/*************************************************
	 * 
	 * 			Contructors
	 * 
	 *************************************************/
	
	public BST() {
		root = null;
		comparator = null;
	}

	public BST(Comparator<T> comp1) {
		root = null;
		comparator = comp1;
	}
	
	/*************************************************
	 * 
	 * 			Helper Methods
	 * 
	 *************************************************/

	private int compare(T x, T y) {
		if (comparator == null) {
			return x.compareTo(y);
		} else {
			return comparator.compare(x, y);
		}
	}
	
	/*************************************************
	 * 
	 * 			Methods
	 * 
	 *************************************************/

	// A public and a private method is to provide
	// encapsulation of the recursive insert method.
	public void insert(T toInsert) {
		root = insert(root, toInsert);
	}

	private Node<T> insert(Node<T> parent, T toInsert) {
		if (parent == null) {
			// We have reached the end of the tree and found
			// the correct position to insert the new key.
			parent = new Node<T>(toInsert);
		} else {
			// First check whether the toInsert key is the
			// same as the key of the current Node.
			if (compare(toInsert, parent.data) == 0) {
				return parent;
			}
			// Check whether the toInsert key is bigger or equal
			// to the current key. If smaller, insert in the left
			// subtree. Else, insert in the right subtree.
			if (compare(toInsert, parent.data) < 0) {
				parent.left = insert(parent.left, toInsert);
			} else {
				parent.right = insert(parent.right, toInsert);
			}
		}
		return parent;
	}
	
	// Again we first start with Encapsulation
	public boolean search(T toSearch){
	  return search(root, toSearch);
	}
	private boolean search(Node<T> parent, T toSearch){
	  if ( parent == null ){
	    // We have come to the end of the tree. 
	    // Key has not been found.
	    return false;
	  }
	  if ( compare( toSearch, parent.data ) == 0 ){
	    // We have found a match with the
	    // key we are looking for!
	    return true;
	  }
	  // If key is smaller than parent.data, 
	  // search in left subtree. Else search in
	  // right subtree.
	  if ( compare( toSearch, parent.data ) < 0 ){
	    return search(parent.left, toSearch);
	  } else {
	    return search(parent.right, toSearch);
	  }
	}
	
	// Again, encapsulation first.
	public Node<T> findMax(){
	  return findMax(root);
	}
	
	private Node<T> findMax(Node<T> parent){
	  while (parent != null){
	    // Keep going until the furthest right of the tree
	    parent = parent.right;
	  }
	  return parent;
	}
	
	// Again, encapsulation first
	public Node<T> findMin(){
	  return findMin(root);
	}
	private Node<T> findMin(Node<T> parent){
	  while (parent != null){
	    // Keep going until the furthest left of the tree
	    parent = parent.left;
	  }
	  return parent;
	}
	
	// Again always remember to encapsulate functions.
	public void delete(T toDelete){
	  root = delete(root, toDelete);
	}
	private Node<T> delete(Node<T> parent, T toDelete){
	  if ( parent == null ) {
	    throw new RuntimeException("Cannot Delete.");
	  }
	  if ( compare( toDelete, parent.data ) < 0 ){
	    parent.left = delete( parent.left, toDelete );
	  } else if ( compare( toDelete, parent.data) > 0 ){
	    parent.right = delete( parent.right, toDelete );
	  } else {
	    if ( parent.left == null ){
	      parent = parent.right;
	    } else if ( parent.right == null ){
	      parent = parent.left;
	    } else {
	      parent = findMax(parent.left);
	      parent.left = delete(parent.left, parent.data);
	    }
	  }
	  return parent;
	}
	
	/*************************************************
	 * 
	 * 			Traversal
	 * 
	 *************************************************/
	
	//Again, Encapsulation First!!
	public void preOrderTraversal(){
		preOrderTraversal(root);
		System.out.println();
	}
	private void preOrderTraversal(Node<T> parent){
		if (parent != null){
			System.out.print(parent);
			preOrderTraversal(parent.left);
			preOrderTraversal(parent.right);
		}
	}
	
	//Again, Encapsulation First!!
	public void InOrderTraversal(){
		InOrderTraversal(root);
		System.out.println();
	}
	private void InOrderTraversal(Node<T> parent){
		if (parent != null){
			InOrderTraversal(parent.left);
			System.out.print(parent);
			InOrderTraversal(parent.right);
		}
	}
	
	//Again, Encapsulation First!!
	public void PostOrderTraversal(){
		PostOrderTraversal(root);
		System.out.println();
	}
	private void PostOrderTraversal(Node<T> parent){
		if (parent != null){
			PostOrderTraversal(parent.left);
			PostOrderTraversal(parent.right);
			System.out.print(parent);
		}
	}
	
	/*************************************************
	 * 
	 * 			Node class
	 * 
	 *************************************************/

	private class Node<T> {
		private T data;
		private Node<T> left, right;
		public Node(T value) {
			data = value;
			left = right = null;
		}
		public String toString() {
			return data.toString();
		}
	}
	
	/*****************************************
	 * 
	 * 		Iterator
	 * 
	 *****************************************/
	
	@Override
	public Iterator<T> iterator() {
		return new BSTiterator();
	}
	
	private class BSTiterator implements Iterator<T>{
		Stack<Node<T>> stk = new Stack<Node<T>>();
		
		public BSTiterator(){
			if (root != null){ stk.push(root); }
		}
		
		@Override
		public boolean hasNext() {
			return !stk.isEmpty();
		}

		@Override
		public T next() {
			if (stk.isEmpty()) throw new java.util.NoSuchElementException();
			Node<T> cur = stk.pop();
			if (cur.right != null) stk.push(cur.right);
			if (cur.left != null) stk.push(cur.left);
			return cur.data;
		}
	}
}
