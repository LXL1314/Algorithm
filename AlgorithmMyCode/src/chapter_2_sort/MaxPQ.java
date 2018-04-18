package chapter_2_sort;

public class MaxPQ <Key extends Comparable<Key>>{
/**
 * 最大优先队列
 * 用一个长度为N+1的数组pq[]来表示一个大小为N的堆，不使用pq[0]；
 * 位置为k的节点的父节点的位置为k/2（向下取整）
 * 位置为k的节点的子节点的位置为2*k和2*k+1
 */
	
	//private static Comparable[] pq;
	private static int N ;
	
	/*public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN+1];
	}*/
	
	public static boolean isEmpty() {
		return N ==0;
	}
	
	public static int size() {
		return N;
	}
	
	public static void insert(Comparable[] pq, Comparable v) {
		N++;
		pq[N] = v;
		swim(pq, N);
	}
	
	public static Comparable delMax(Comparable[] pq) {
		Comparable max = pq[1];
		exch(pq,1, N);//把最大的那个数与最后那个数交换
		N--;
		pq[N+1] = null;
		sink(pq, 1);
		return max;
	}
	
	public static Comparable getMax(Comparable[] pq) {
		return pq[1];
	}
	//判断下标为i的元素是否比下标为j的元素小
	public static boolean less(Comparable[] pq, int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	//交换下标为i和j的两元素
	public static void exch(Comparable[] pq, int i, int j) {//调用一次就一次交换
		Comparable t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	//上浮
	public static void swim(Comparable[] pq,int k) {
		while(k > 1 && less(pq, k/2, k)){
			exch(pq, k/2, k);
			k = k/2;
		}
	}
	
	//下沉
	public static void sink(Comparable[] pq, int k) {
		int j = 2*k;
		while(j <= N ) {
			if(j < N && less(pq, j, j + 1))//选出两个子节点中更大的那一个
				j=j+1;
			if(!less(pq, k , j))//若下标为k的元素不小于两节点中更大的那个，则停止下沉
				break;
			exch(pq, k, j);
			k = j;
			j = 2*k;
		}
	}
	
	public static void sort(Comparable[] pq) {
		N = pq.length -1 ;
		for(int i = N/2; i >= 1; i--) {//构建堆的过程，只有一层的时候不需要操作，所以从倒数第二层开始构建，用下沉法构建子堆，直到根节点为止
			sink(pq,i);				   
		}
		while(N > 1) {//根节点（位置为1）是最大的元素，将其与最后一个元素（位置为N）对换，N要减一（堆的大小减一，但是这个数并不删除），
					  //此时因为根节点，堆序性被破坏，所以用sink（1）恢复堆序性
			exch(pq, N, 1);
			N--;
			sink(pq, 1);
		}
	}
	
	
	public static void show(Comparable[] pq) {
		if(pq.length >= 2)
			for(int i = 1; i < pq.length; i++) {
				System.out.println(pq[i]);
			}
	}
	
	public static void main(String[] args){ 
		
		Comparable[] a = {0,23,45,67,23,45,87,34,76,12,34,41};
		sort(a);
		show(a);
		
	}
}
