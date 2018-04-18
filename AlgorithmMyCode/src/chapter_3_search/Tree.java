package chapter_3_search;

public class Tree<Value extends Comparable<Value>> {
	
	private Value[] vals;
	private int N = 0;//NΪ�����д��ȥ��Ԫ�صĸ���������������ĵ�0��Ԫ��
	
	public Tree() {
		vals = (Value[]) new Comparable[1];
	}
	
	public int size() {
		return N;
	}
	
	public boolean isFull() {
		return size() == vals.length - 1;
	}
	
	//���ռ���������
	public void resize() {
		Value[] temp = (Value[]) new Comparable[vals.length*2];
		for (int i = 1; i <= N; i++)
			temp[i] = vals[i];
		vals = temp;
	}
	
	//���ظ�Ԫ���±�
	public int index(Value val) {
		for (int i = 1; i <= N; i++)
			if (val.compareTo(vals[i]) == 0)
				return i;
		return -1;
	}
	
	public boolean contains(Value val) {
		if (index(val) >=1 )
			return true;
		return false;
	}
	
	public void insert(Value val) {
		//���������������ռ���������
		if(isFull())
			resize();
		
		if (contains(val))
			return;
		
		N++;
		vals[N] = val;
	}
			
	public void delete(Value val) {
		int x = index(val);
		if (x < 1)
			return; 
		for (int i = x; i <= N - 1; i++ )
			vals[i] = vals[i+1];
		vals[N] = null;
		N--;
	}
	
	//�������
	public void first(int i) {
		if (i <= N) {
			first(2*i);
			System.out.println(vals[i]);
			first(2*i+1);
		}
	}
	
	//�������
	public void mid(int i) {
		if (i <= N) {
			System.out.println(vals[i]);
			mid(2*i);
			mid(2*i+1);
		}
	}
		
	//�������
	public void last(int i) {
		if (i <= N) {
			last(2*i+1);
			System.out.println(vals[i]);
			last(2*i);
		}
	}
	
	
	public void show() {
		System.out.println("start---------------------------");
		for (int i = 1; i <= N; i++)
			System.out.println(vals[i]);
		System.out.println("end---------------------------");
	}
	
	
	public static void main(String[] args) { 
		Tree<Integer> tree = new Tree<Integer>();
		for(int i = 1; i <= 7; i++)
			tree.insert(i);
			
		//tree.getleft(1);
		tree.last(1);
			
	}

}
 