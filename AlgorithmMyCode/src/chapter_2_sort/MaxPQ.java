package chapter_2_sort;

public class MaxPQ <Key extends Comparable<Key>>{
/**
 * ������ȶ���
 * ��һ������ΪN+1������pq[]����ʾһ����СΪN�Ķѣ���ʹ��pq[0]��
 * λ��Ϊk�Ľڵ�ĸ��ڵ��λ��Ϊk/2������ȡ����
 * λ��Ϊk�Ľڵ���ӽڵ��λ��Ϊ2*k��2*k+1
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
		exch(pq,1, N);//�������Ǹ���������Ǹ�������
		N--;
		pq[N+1] = null;
		sink(pq, 1);
		return max;
	}
	
	public static Comparable getMax(Comparable[] pq) {
		return pq[1];
	}
	//�ж��±�Ϊi��Ԫ���Ƿ���±�Ϊj��Ԫ��С
	public static boolean less(Comparable[] pq, int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	//�����±�Ϊi��j����Ԫ��
	public static void exch(Comparable[] pq, int i, int j) {//����һ�ξ�һ�ν���
		Comparable t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	//�ϸ�
	public static void swim(Comparable[] pq,int k) {
		while(k > 1 && less(pq, k/2, k)){
			exch(pq, k/2, k);
			k = k/2;
		}
	}
	
	//�³�
	public static void sink(Comparable[] pq, int k) {
		int j = 2*k;
		while(j <= N ) {
			if(j < N && less(pq, j, j + 1))//ѡ�������ӽڵ��и������һ��
				j=j+1;
			if(!less(pq, k , j))//���±�Ϊk��Ԫ�ز�С�����ڵ��и�����Ǹ�����ֹͣ�³�
				break;
			exch(pq, k, j);
			k = j;
			j = 2*k;
		}
	}
	
	public static void sort(Comparable[] pq) {
		N = pq.length -1 ;
		for(int i = N/2; i >= 1; i--) {//�����ѵĹ��̣�ֻ��һ���ʱ����Ҫ���������Դӵ����ڶ��㿪ʼ���������³��������Ӷѣ�ֱ�����ڵ�Ϊֹ
			sink(pq,i);				   
		}
		while(N > 1) {//���ڵ㣨λ��Ϊ1��������Ԫ�أ����������һ��Ԫ�أ�λ��ΪN���Ի���NҪ��һ���ѵĴ�С��һ���������������ɾ������
					  //��ʱ��Ϊ���ڵ㣬�����Ա��ƻ���������sink��1���ָ�������
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
