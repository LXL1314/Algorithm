package chapter_3_search;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeST<Key extends Comparable<Key>, Value> {

	private Node root;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		
		public Node(Key key, Value val, int N, Node left, Node right) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.left = left;
			this.right = right;
		}
	}
	
	private  int size(Node x) {
		if(x == null)
			return 0;
		return x.N;
	}
	
	public int size() {
		return size(root);
	}
	
	private Value get(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)
			return x.val;
		else if(cmp < 0)
			return get(x.left, key);
		else
			return get(x.right, key); 
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Node put(Node x, Key key, Value val) {
		if (x == null) 
			return new Node(key, val, 1, null, null);
		
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			x.left = put(x.left, key,val);
		else if(cmp > 0)
			x.right = put(x.right, key,val);
		else 
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node min(Node x) {
		if(x.left == null)
			return x;
		return min(x.left);
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node max(Node x) {
		if(x.right == null)
			return x;
		return min(x.right);
	}
	
	public Key max() {
		return max(root).key;
	}
	
	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp < 0)
			return floor(x.left, key);
		else {
			Node t = floor(x.right, key);
			if (t  == null)
				return x;
			else
				return t;
		}
	}
	
	public Key floor(Key key) {
		Node t = floor(root, key);
		if (t == null)
			return null;
		return t.key;
	}
	
	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp > 0)
			return ceiling(x.right, key);
		else {
			Node t = ceiling(x.left, key);
			if (t == null)
				return x;
			else
				return t;
		}
	}
	
	public Key ceiling(Key key) {
		Node t = ceiling(root, key);
		if (t == null)
			return null;
		else 
			return t.key;
	}
	
	private int rank(Node x, Key key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return size(x.left);
		else if (cmp < 0)
			return rank(x.left, key);
		else 
			return size(x.left) + 1 + rank(x.right, key);
	}
	
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private Node select(Node x, int k) {
		if (x == null)
			return null;
		if (k < 0 || k > size(x))
			return null;
		
		int n = size(x.left);
		if (k <= n)
			return select(x.left, k);
		else if (k == n + 1 )
			return x;
		else
			return select(x.right, k - n - 1);
	}
	
	public Key select(int k) {
		Node t = select(root, k);
		if (t == null)
			return null;
		else
			return t.key;
	}
	
	private Node deleteMin(Node x) {	
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) +1;
		return x;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMax(Node x){
		if (x.right == null)
			return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) +1;
		return x;
	}
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node delete(Node x, Key key){
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			 x.left = delete(x.left, key);
		else if (cmp > 0)
			 x.right = delete(x.right, key);
		else {
			if (x.left == null)
				return x.right;
			if (x.right == null)
				return x.left;
			
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}
	
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new LinkedList<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			queue.add(x.key);
		if (cmphi > 0)
			keys(x.right, queue, lo, hi);
	}
	
	public static void main(String[] args) { 
		BinarySearchTreeST<Integer, Integer> st = new BinarySearchTreeST<Integer, Integer>();
		st.put(8, 8);
		System.out.println(st.min()+"-------------------------" + st.max());
		st.put(2, 2);
		System.out.println(st.min()+"-------------------------" + st.max());
		st.put(9, 9);
		System.out.println(st.min()+"-------------------------" + st.max());
		st.put(6, 6);
		System.out.println(st.min()+"-------------------------" + st.max());
		System.out.println(st.ceiling(3)+"-------------------------" + st.floor(5));
		for (Integer i : st.keys()) {
			System.out.println(i + "  " + st.get(i));
		}
		st.delete(8);
		for (Integer i : st.keys()) {
			System.out.println(i + "  " + st.get(i));
		}
	}
	
}
