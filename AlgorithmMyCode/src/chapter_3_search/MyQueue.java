package chapter_3_search;

public interface MyQueue <Value extends Comparable<Value>>{
	public int size();
	public boolean isEmpty();
	public void deleteAll();
	public Iterable<Value> vals();
	public void show();
	
	public boolean enqueue(Value val);
	public Value delqueue();
	
}
