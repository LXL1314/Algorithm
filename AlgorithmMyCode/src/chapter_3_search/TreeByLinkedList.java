package chapter_3_search;

import java.util.LinkedList;
import java.util.Queue;

//ɾ������������

public class TreeByLinkedList <Value extends Comparable<Value>>{

	private Node root;
	
	private class Node{
		private int N = 0;
		private Value val;
		private Node left, right;
		
		public Node(Value val, int N) {
			this.val = val;
			this.N = N;
		}
	}
		
	//������xΪ���ڵ�������Ĵ�С
	public int size(Node x) {
		if (x == null)
			return 0;
		return x.N;
	}
		
	//����xΪ���ڵ�������ϵõ�ֵΪval���ڵĽڵ�
	private Node get(Node x, Value val) {
		if (x == null)
			return null;
		
		int cmp = val.compareTo(x.val);
		
		if (cmp < 0 )
			return get(x.left, val);
		else if (cmp > 0)
			return get(x.right, val);
		else
			return x;
	}
		
	public Node get(Value val) {
		return get(root, val);
	}
		
	private Node getMin(Node x) {
		if (x == null)
			return null;
		if (x.left == null)
			return x;
		else
			return getMin(x.left);
	}
		
	public Value getMin() {
		Node x = getMin(root);
		if (x == null)
			return null;
		return x.val;
	}
		
	private Node getMax(Node x) {
		if (x == null)
			return null;
		if (x.right == null)
			return x;
		else
			return getMax(x.right);
	}
		
	public Value getMax() {
		Node x = getMax(root);
		if (x == null)
			return null;
		return x.val;
	}
		
	public boolean contains(Value val) {
		return get(val) != null;
	}
		
	//����xΪ���ڵ�������У�����val
	private Node put(Node x, Value val) {
		if (x == null)
			return new Node(val, 1);
		//�����ִ��ڸ�ֵ���򲻲���
		
		int cmp = val.compareTo(x.val);
		if (cmp < 0)
			x.left = put (x.left, val);
		else if (cmp > 0)
			x.right = put(x.right, val);
		x.N = size(x.left) + 1 + size(x.right);
		return x;
	}
		
	public void put(Value val) {
		root = put(root, val);
	}
	
	// ���ýڵ�Ҫ��ɾ������ɾ���󣬷��ر����ֵ����˵ĸýڵ�
	private Node deleteMin(Node x) {	
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + 1 + size(x.right);
		return x;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMax(Node x) {
		if (x.right == null)
			return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + 1 + size(x.right);
		return x;
	}
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	//���ص��Ǳ�������x�ڵ㣨���ڵ�xû�б�ɾ������xû�б仯��x�ڵ㱻ɾ�������صĻ��Ǹýڵ㣬���Ǹýڵ��Ǳ����ֵ��˵ģ�
	private Node delete(Node x, Value val) {
		if (x == null)
			return null;
		int cmp = val.compareTo(x.val);
		if (cmp < 0)
			x.left = delete(x.left, val);
		else if (cmp > 0)
			x.right = delete(x.right, val);
		else {
			//ɾ���ýڵ�ʱ��ֻ�Ƿ����˽�Ҫ���x�Ľڵ㣬���ǻ�û�аѷ��صĽڵ㸳��x�� ����Ҫ����x = delete(x, val),�����÷��صĽڵ����ԭ���Ľڵ�
			if (x.left == null)
				return x.right;
			if (x.right == null)
				return x.left;
			
			Node t = x;
			x = getMin(t.right);
			x.left = t.left;
			x.right = deleteMin (t.right);//x.right = delete(t.right, getMin(t.right).val);
		}
		x.N = size(x.left) + 1 + size(x.right);
		return x;
	}
	
	public void delete(Value val) {
		root = delete(root, val);
	}
	
	//����xΪ�ڵ���������ҳ�<= val ��������
	private Value floor(Node x, Value val) {
		if (x == null)
			return null;
		int cmp = val.compareTo(x.val);
		if (cmp < 0)
			return floor(x.left,val);
		else if (cmp == 0)
			return x.val;
		else {//��val �ȸýڵ��ֵ��
			Value t = floor(x.right, val);
			if (t == null)
				return x.val;
			else 
				return t;
		}
	}
	
	public Value floor(Value val) {
		return floor(root, val);
	}
	
	//����xΪ���ڵ���������ҵ�>=val����С����
	private Value ceiling(Node x, Value val) {
		if (x == null)
			return null;
		int cmp = val.compareTo(x.val);
		if (cmp > 0)
			return ceiling(x.right, val);
		else if(cmp == 0)
			return x.val;
		else {
			Value t = ceiling(x.left, val);
			if (t == null)
				return x.val;
			else 
				return t;
		}
	}
	
	public Value ceiling(Value val) {
		return ceiling(root, val);
	}
	
	//����xΪ���ڵ���������ҵ�����Ϊk����
	private Value select(Node x, int k) {
		if (x == null)
			return null;
		if (k < 1 || k > size(x))
			return null;
		if (k <= size(x.left))
			return select(x.left, k);
		else if ( k == size(x.left) + 1)
			return x.val;
		else 
			return select(x.right, k - 1 - size(x.left));
	}
	
	public Value select(int k) {
		return select(root, k);
	}
	
	//����xΪ���ڵ�������У�С��val��ֵ�ĸ���
	private int rank(Node x, Value val) {
		if (x == null)
			return 0;
		int cmp = val.compareTo(x.val);
		if (cmp < 0)
			return rank(x.left, val);
		else if (cmp > 0)
			return size(x.left) + 1 + rank(x.right, val);
		else 
			return size(x.left);
	}
	
	public int rank(Value val) {
		return rank(root, val);
	}
	
	//������xΪ���ڵ���������������Ľ���ǰ���С�����ţ�
	private void vals(Queue<Value> queue,Node x){
		if (x == null)
			return ;
		if (x.left != null)
			vals(queue, x.left);
		queue.add(x.val);
		if (x.right != null)
			vals(queue,x.right);
	}
	
	private Iterable<Value> vals(Node x){
		Queue<Value> queue = new LinkedList<Value>();
		vals(queue,x);
		return queue;
	}
	
	
	public Iterable<Value> vals(){
		return vals(root);
	}
	
	public static void main(String[] args) {
		TreeByLinkedList<Integer> tree = new TreeByLinkedList<Integer>();
		tree.put(34);
		tree.put(67);
		tree.put(23);
		tree.put(65);
		tree.put(26);
		tree.put(11);
		tree.put(69);
	
		System.out.println(tree.getMin());
		System.out.println(tree.getMax());
		tree.delete(69);
		System.out.println(tree.getMax()+"---------"+ tree.select(5) +"-------------------");
		Iterable<Integer> vals = tree.vals();
		for (Integer x : vals)
			System.out.println(x);
		
	}
	
	
	
}
