package chapter_3_search;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchST<Key extends Comparable<Key>,Value> {

	private Key[] keys;
	private Value[] vals;
	private int N = 0;//符号表中的填入的元素的个数
	
	public BinarySearchST() {
		keys = (Key[]) new Comparable[1];
		vals = (Value[]) new Object[1];
	}
	
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
	}
	
	public int size() {
		return N;
	}
	
	public int size(Key lo, Key hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to size() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null"); 
        if(lo.compareTo(hi) > 0) return 0;
        
        if(contains(hi))
        	return rank(hi) - rank(lo) +1;
        else
        	return rank(hi) - rank(lo);
        
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public Value get(Key key) {//log N
		if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 
		
		if(isEmpty()) return null;
		int i = rank(key);//键值小于key的数量
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		return null;
	}
	
	public int rank(Key key) {//log N
		if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 
		
		int lo = 0, hi = N-1, mid, cmp;
		while(lo <= hi ) {
			mid = lo + (hi - lo) / 2;
			cmp = key.compareTo(keys[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if (cmp > 0) 
				lo = mid + 1;
			else 
				return mid;
		}
		return lo;//当所有的键值比key都小，lo = N
	}
	
	public void put(Key key, Value val) {//平均：N  最坏情况：符号表已满且符号表无该键：2N
		if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 
		if(val == null) {
			delete(key);
			return;
		}
		
		int i = rank(key);
		//在符号表中
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		
		//若符号表已满,让符号表长度变成原来的两倍
		if(N == keys.length)
			resize(2*keys.length);
		
		//不在符号表中
		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public void delete(Key key) {//N
		if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 
		if(isEmpty()) 
			return;
		
		int i = rank(key);
		//key不在符号表中
		if(i > N || keys[i].compareTo(key) != 0)
			return;
		
		//key在符号表中
		for(int j = i; j < N-1; j++) {
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		N--;
	}
	
	public Key min() {
		 if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		 return keys[0];
	}
	
	public Key max() {
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return keys[N-1];
	}
	
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
		delete(min());
	}
	
	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
		delete(max());
	}
	
	public Key select(int k) {
		if(k < 0 || k >= size())
			throw new IllegalArgumentException("called select() with invalid argument: " + k);
		return keys[k];
	}
	
	public void resize(int capacity) {
		if(capacity <= size() ) return;
		
		Key[] tempKeys = (Key[]) new Comparable[capacity];
		Value[] tempVals = (Value[]) new Object[capacity];
		
		for(int j = 0; j < size(); j++) {
			tempKeys[j] = keys[j];
			tempVals[j] = vals[j];
		}
		keys = tempKeys;
		vals = tempVals;
	}
	
	public Key floor(Key key) {// log N
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
		if (key == null) throw new IllegalArgumentException("argument to floor() is null"); 
		int i = rank(key);
		if(i == 0)
			return null;
		if(i < N && key.compareTo(keys[i]) == 0)
			return keys[i];
		else 
			return keys[i-1];
	}
	
	public Key ceiling(Key key) {// log N
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
		if (key == null) throw new IllegalArgumentException("argument to floor() is null"); 
		int i = rank(key);
		if(i == N) return null;
		return keys[i];
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null"); 
        Queue<Key> queue = new LinkedList<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.add(keys[i]);
        if (contains(hi)) 
        	queue.add(keys[rank(hi)]);
		return queue; 
	}
	
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	
	public static void main(String[] args) { 
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        st.put("1",45);
        System.out.println(" size: " + st.size());
        st.put("2", 34);
        System.out.println(" size: " + st.size());
        st.put("5", 34);
        System.out.println(" size: " + st.size());
        st.put("0", 34);
        System.out.println(" size: " + st.size());
        for (String s : st.keys()) 
        	System.out.println(s + " " + st.get(s));
        st.put("1",34);
        System.out.println(" size: " + st.size());
        st.put("2", 56);
        System.out.println(" size: " + st.size());
        st.put("5", 67);
        System.out.println(" size: " + st.size());
        st.put("0", 145);
        System.out.println(" size: " + st.size());
        for (String s : st.keys()) 
        	System.out.println(s + " " + st.get(s));
	}
	
}
