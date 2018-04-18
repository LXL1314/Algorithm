package chapter_2_sort;

public class MinPQ<Key extends Comparable<Key>>{
/**
 * 最小优先队列
 * 用一个长度为N+1的数组pq[]来表示一个大小为N的堆，不使用pq[0]；
 * 位置为k的节点的父节点的位置为k/2（向下取整）
 * 位置为k的节点的子节点的位置为2*k和2*k+1
 */
	
	private Key[] pq;
	private int N = 0;
	
	public MinPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN+1];
	}
	
	public void insert(Key v) {
		N++;
		pq[N] = v;
		swim(N);
	}
	
	public void delMin() {
		exch(1, N);
		N--;
		pq[N+1] = null;
		sink(1);
	}
	
	public boolean isEmpty() {
		return N ==0;
	}
	
	public int size() {
		return N;
	}
	
	public Key getMin() {
		return pq[1];
	}
	
	public boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	public void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
 	}
	
	public void swim(int k) {//节点比其父节点小时，向上浮
		while(k > 1 && less(k, k/2)) {
			exch(k,k/2);
			k = k/2;
		}
	}
	
	public void sink(int k) {//若节点比它的子节点中最小的那个还小，则下沉，用子节点中较小的那个代替它
		int j =2*k;
		while(j <= N) {
			if(j < N && less(j+1, j))//找出子节点中更小的那个
				j++;
			if(!less(j, k))//若子节点中更小的那个不比下标为k的元素小，则退出
				break;
			exch(j,k);
			k=j;
			j=2*k;
		}
	}
	
	public void show() {
		if(N >= 1)
			for(int i = 1; i <= N; i++) {
				System.out.println(pq[i]);
			}
	}
	
	public static void main(String[] args){ 
		MinPQ<Integer> pq = new MinPQ<Integer>(4);
		pq.insert(3);
		System.out.println("SIZE:"+pq.size());
		pq.insert(4);
		System.out.println("SIZE:"+pq.size());
		pq.insert(1);
		System.out.println("SIZE:"+pq.size());
		pq.insert(2);
		System.out.println("SIZE:"+pq.size());
		System.out.println("MIN:"+pq.getMin());
		pq.delMin();
		System.out.println("SIZE:"+pq.size());
		pq.show();
		System.out.println("MIN:"+pq.getMin());
		
		pq.delMin();
		System.out.println("SIZE:"+pq.size());
		pq.show();
		System.out.println("MIN:"+pq.getMin());
	}
	
}
