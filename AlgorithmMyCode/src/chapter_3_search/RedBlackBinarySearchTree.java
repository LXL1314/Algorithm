package chapter_3_search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ��ڶ�����
 */
public class RedBlackBinarySearchTree <Value extends Comparable<Value>>{
	private Node root;
	
	private class Node{
		private int N;
		private Value val;
		private boolean color;//ָ��ý������ӵ���ɫ����ɫΪtrue����ɫΪfalse
		private Node left, right;
		
		public Node(Value val, int N, boolean color) {
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	//�ж�ָ��ý������ӵ���ɫ�Ƿ�Ϊ��ɫ����ɫΪtrue����ɫΪfalse
	public boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color;
	}
	
	public int size(Node h) {
		if (h == null)
			return 0;
		return h.N;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	//xΪ���ڵ�������У�x��������Ϊ��ɫ����������ת����������ֵΪ��ת��������ĸ��ڵ�
	public Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = true;
		x.N = h.N;
		h.N = size(h.left) + 1 + size(h.right);
		return x;
	}
	
	//xΪ���ڵ�������У�x��������Ϊ��ɫ����������ת����������ֵΪ��ת��������ĸ��ڵ�
	public Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right  = h;
		x.color = h.color;
		h.color = true;
		x.N = h.N;
		h.N = size(h.left) + 1 + size(h.right);
		return x;
	}
	
	//��ɫת��,h���������Ӷ�Ϊ��ɫ�Ľ�㣬��h������������Ϊ��ɫ��ͬʱ��ָ��h��������Ϊ��ɫ
	//�п��ܸ��ڵ��color�Ǻ�ɫ������ÿ�β���ʱ������root����ɫ��Ϊ��ɫ
	//���ڵ���ɫ�Ӻ�ɫ��Ϊ��ɫ�������Ӹ߶Ȼ�����һ
	public void flipColors(Node h) {
		h.color = true;
		h.left.color = false;
		h.right.color = false;
	}
	
	private Node put(Node h, Value val) {
		if (h == null)
			return new Node(val, 1, true);
		
		int cmp = val.compareTo(h.val);
		if (cmp < 0)
			h.left = put(h.left, val);
		else if (cmp > 0)
			h.right = put(h.left, val);
		else
			h.val = val;
		
		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) 
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.N = size(h.left) + 1 + size(h.right);
		return h;
	}
	
	public void put(Value val) {
		root = put(root, val);
		root.color = false;
	}
	
	
 /**
 * ���µĴ��붼δ�㶮��˼
 * 
 */
	private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
    	if (h == null)
    		return null;
    	if (isRed(h.right))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) 
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.N = size(h.left) + 1 + size(h.right);
		return h;
    }
    
	//ɾ����xΪ�����������е���С��ֵ
    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }
    
    
    public void deleteMin() {
        if (isEmpty()) 
        	return ;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = true;

        root = deleteMin(root);
        if (!isEmpty()) root.color = false;
    }

    private Node deleteMax(Node h) { 
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }
    
    public void deleteMax() {
        if (isEmpty()) 
        	return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = true;

        root = deleteMax(root);
        if (!isEmpty()) root.color = false;
    }

    private Node delete(Node h, Value val) { 
        // assert get(h, key) != null;

        if (val.compareTo(h.val) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, val);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (val.compareTo(h.val) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (val.compareTo(h.val) == 0) {
                Node x = getMin(h.right);
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, val);
        }
        return balance(h);
    }
    
    public void delete(Value val) { 
        if (val == null) 
        	return;
        if (!contains(val)) 
        	return;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = true;

        root = delete(root, val);
        if (!isEmpty()) 
        	root.color = false;
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
  		RedBlackBinarySearchTree<Integer> st = new  RedBlackBinarySearchTree<Integer>();
  		for (int i = 1; i <= 30; i++)
  			st.put(i);
  		System.out.println(st.rank(9) + "---" + st.getMax());
  		for (int i : st.vals())
  			System.out.println(i);
  	}
	
	
}
