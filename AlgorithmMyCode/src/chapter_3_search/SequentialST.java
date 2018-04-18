package chapter_3_search;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SequentialST <Value extends Comparable<Value>> implements 	Stack, MyQueue{
	private Node first;//ͷ���
	
	private class Node{
		Node next;
		Value val;
		
		public Node(Value val, Node next) {
			this.val = val;
			this.next = next;
		}
	}
	
	//���ظ�ֵ
	public boolean insert(Value val) {
		//�����������ҵ��������򷵻�false
		if (contains(val))
			return false;
		//δ�ҵ������½�һ�����Ϊfirst��㣬�ý���next���Ϊ�ɵ�first��㣬������true
		first = new Node(val, first);
		return true;
	}
	
	public boolean delete(Value val) {
		first = delete(first, val);
		if (first == null)
			return false;
		return true;
		
	}

	private Node delete(Node x, Value val){
		if (x == null)
			return null;
		//�ý����Ҫ��ɾ���Ľڵ㣬�򷵻ظý�����һ���ڵ�
		if (val.compareTo(x.val) == 0)
			return x.next;
		//�ý��x�ǲ���Ҫ��ɾ���Ľڵ㣬��ô�ý�����һ���ڵ�Ϊdelete(x.next, val)
		x.next = delete(x.next, val);
		return x;
	}
	
	public boolean update(Value oldVal, Value newVal) {
		//�������в�����oldVal,�޷����¸�ֵ������false
		for (Node x = first; x != null; x = x.next)
			if(oldVal.compareTo(x.val) == 0) {
				x.val = newVal;
				return true;	
			}
		return false;
	}
	
	public void sort() {
		Node min;
		Value t;
		//ѡ������
		for (Node i = first; i != null; i = i.next) {
			min = i;
			for (Node j = i; j != null; j = j.next) 
				if (j.val.compareTo(min.val) < 0) 
					min = j;
			t = i.val;
			i.val = min.val;
			min.val = t;
		}
	}
	
	public boolean contains(Value val) {
		for (Node x = first; x != null; x = x.next)
			if(val.compareTo(x.val) == 0)
				return true;	
		return false;
	}
	
	public Node get(int i) {
		int n = 0;
		for (Node x = first; x != null ; x = x.next) {
			n++;
			if ( n == i)
				return x;
		}
		return null;
	}
	
	
	public void reverse() {
		
		Node follow = first.next;
		
		first.next = null;
		while (follow != null) {
			//insert(follow.val);
			first = new Node(follow.val, first);
			follow = follow.next;
		}
	}
	
	
	@Override
	public int size() {
		int n=0;
		for (Node i = first; i != null ; i = i.next) 
			n++;
		return n;
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public void deleteAll() {
		for (Node x = first; x != null; x = x.next)
			delete(x.val);
	}
	
	@Override
	public Iterable<Value> vals(){
		Queue<Value> queue = new LinkedList<Value>();
		for (Node x = first; x != null; x = x.next)
			queue.add(x.val);
		return queue;
	}
	
	@Override
	public void show() {
		System.out.println("start-----------------------------");
		for (Value x : vals())
			System.out.println(x);
		System.out.println("end-----------------------------" + "\n");
	}
	
	@Override
	public boolean push(Comparable val) {
		insert((Value) val);
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Comparable pop() {
		// TODO Auto-generated method stub
		Node x = get(1);
		delete(x.val);
		return x.val;
	}

	@Override
	public boolean enqueue(Comparable val) {
		// TODO Auto-generated method stub
		insert((Value) val);
		return false;
	}
	
	@Override
	public Comparable delqueue() {
		// TODO Auto-generated method stub
		int n = size();
		Node x = get(n);
		delete(x.val);
		return x.val;
	}
	
	public static void main(String[] args) { 
		SequentialST<Integer> stack = new SequentialST<Integer>();
		for (int i = 1; i <= 8; i++)
			stack.push(i);
		stack.show();
		
		stack.reverse();
		stack.show();
	}
	
}
