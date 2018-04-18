package chapter_2_sort;

public class MinPQ<Key extends Comparable<Key>>{
/**
 * ��С���ȶ���
 * ��һ������ΪN+1������pq[]����ʾһ����СΪN�Ķѣ���ʹ��pq[0]��
 * λ��Ϊk�Ľڵ�ĸ��ڵ��λ��Ϊk/2������ȡ����
 * λ��Ϊk�Ľڵ���ӽڵ��λ��Ϊ2*k��2*k+1
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
	
	public void swim(int k) {//�ڵ���丸�ڵ�Сʱ�����ϸ�
		while(k > 1 && less(k, k/2)) {
			exch(k,k/2);
			k = k/2;
		}
	}
	
	public void sink(int k) {//���ڵ�������ӽڵ�����С���Ǹ���С�����³������ӽڵ��н�С���Ǹ�������
		int j =2*k;
		while(j <= N) {
			if(j < N && less(j+1, j))//�ҳ��ӽڵ��и�С���Ǹ�
				j++;
			if(!less(j, k))//���ӽڵ��и�С���Ǹ������±�Ϊk��Ԫ��С�����˳�
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
