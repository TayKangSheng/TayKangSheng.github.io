import java.util.Comparator;

public class AVL<T extends Comparable<T>>{
	public static void main(String args[]){
		int[] a = {1,2,5,3,4};
		AVL<Integer> avl = new AVL<Integer>();
		for (int i : a){
			avl.insert(i);
		}
		avl.inOrderTraversal();
	}
	
	/************************************************
	 * 
	 * 		Variables
	 * 
	 ************************************************/
	private Node<T> root;
	Comparator comparator;
	
	/************************************************
	 * 
	 * 		Constructors
	 * 
	 ************************************************/
	public AVL(){
		root = null;
	}
	
	public AVL(Comparator comp){
		root = null;
		comparator = comp;
	}
	
	/************************************************
	 * 
	 * 		Helper Methods
	 * 
	 ************************************************/
	private int compare(T x, T y){
		if (comparator == null){
			return x.compareTo(y);
		} else {
			return comparator.compare(x, y);
		}
	}
	
	private void fix_height(Node<T> T){
		T.height = Math.max( height(T.left), height(T.right)) + 1 ;
	}
	
	private int height(Node<T> T){
		if (T == null){
			return 0;
		} else {
			return Math.max(height(T.left), height(T.right)) + 1 ;
		}
	}
	
	/************************************************
	 * 
	 * 		Rotation Methods
	 * 
	 ************************************************/
	private Node<T> rotate_left(Node<T> T){
		Node<T> Tr = T.right;
		T.right = Tr.left;
		Tr.left = T;
		fix_height(Tr.left);
		fix_height(Tr);
		return Tr;
	}
	
	private Node<T> rotate_right(Node<T> T){
		Node<T> Tl = T.left;
		T.left = Tl.right;
		Tl.right = T;
		fix_height(Tl.left);
		fix_height(Tl);
		return Tl;
	}
	
	/************************************************
	 * 
	 * 		Balancing Methods
	 * 
	 ************************************************/
	private Node<T> rebalance(Node<T> T){
		Node<T> Tr = T.right;
		Node<T> Tl = T.left;
		if ( height(Tr) - height(Tl) > 1 ){
			// right side heavy
			if ( height(Tr.right) > height(Tr.left) ){
				// right-right condition
				T = rotate_left(T);
			} else {
				// right-left condition
				T.right = rotate_right(Tr);
				T = rotate_left(T);
			}
		} else if ( height(Tr) - height(Tl) < -1){
			// left side heavy
			if ( height(Tl.left) > height(Tl.right) ){
				// left-left condition
				T = rotate_right(T);
			} else {
				// left-right condition
				T.left = rotate_left(Tl);
				T = rotate_right(T);
			}
		} else {
			// Tree is already balance
			fix_height(T);
		}
		return T;
	}
	
	/************************************************
	 * 
	 * 		Public Methods
	 * 
	 ************************************************/
	public void insert(T toInsert){
		root = insert(root, toInsert);
	}
	
	private Node<T> insert (Node<T> p, T toInsert){
		if (p == null){
			return new Node(toInsert);
		}
		if ( compare( toInsert, p.data ) > 0 ){
			p.right = insert(p.right, toInsert);
			p = rebalance( p );
		} else {
			p.left = insert(p.left, toInsert);
			p = rebalance( p );
		}
		return p;
	}
	
	/************************************************
	 * 
	 *		Traversal
	 *
	 ************************************************/
	public void inOrderTraversal(){
		inOrderTraversal(root);
		System.out.println();
	}
	private void inOrderTraversal(Node<T> p){
		if (p != null){
			inOrderTraversal(p.left);
			System.out.println(p);
			inOrderTraversal(p.right);
		}
	}
	
	/************************************************
	 * 
	 *		Private inner Node Class
	 *
	 ************************************************/
	
	private class Node<T>{
		private T data;
		private int height;
		private Node<T> left, right;
		
		public Node(T value){
			data = value;
			height = 1;
			left = right = null;
		}
		public String toString(){
			return data.toString();
		}
	}
}
