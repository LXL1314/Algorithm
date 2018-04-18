package chapter_3_search;

public interface Stack<Value extends Comparable<Value>>{
	
	public int size();
	public boolean isEmpty();
	public void deleteAll();
	public Iterable<Value> vals();
	public void show();
	
	public boolean push(Value val);
	public Value pop();
	
}