package chapter_2_sort;

//��������
public class Sort {
	
	public static void selectionSort(Comparable[] a) {
		//ѡ������˼�룺�ҵ���������С��Ԫ�أ�Ȼ��͵�һ��Ԫ�ؽ���λ�ã��ҵ�ʣ�µ�������С��Ԫ�غ͵ڶ���Ԫ�ؽ���λ�ã�...��ֱ���������������ꡣ
		//���Ӷȣ����ڳ���Ϊ N �����飬 ѡ��������Ҫ��Լ N2/2 �αȽϺ� N �ν��������Ӷ�O(N2)
		//�ص㣺��������Ƿ������޹�
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++) 
				if(Ready.less(a[j], a[min]))
					min=j;
			Ready.exch(a, i, min);
		}
	}
	
	public static void insertionSort(Comparable[] a) {
		//��������˼�룺����j��Ԫ��ʱ����ǰ����������Ѿ��ź���ģ���a[j]����ǰ����Ǹ�Ԫ��С����Ҫ��a[j]���뵽ǰ�����źõ�������ȥ��
		//������j>0ʱ��һֱ����a[j]��a[j-1]��˳��ֱ��a[j]����a[j-1]С��
		//���Ӷȣ�ƽ������²���������Ҫ�� N2/4 �αȽ��Լ��� N2/4 �ν���,
		// ���������򣬴Ӵ�С������ÿ�ζ���Ҫ�ȽϺͽ���������Ҫ�� N2/2 �αȽϺ͡� N2/2 �ν����� 
		// ����������������Ҫ N-1�αȽϺ� 0 �ν�����
		int N = a.length;
		for(int i = 1; i < N; i++) 
			for(int j = i; j>=1 && Ready.less(a[j], a[j-1]); j--) 
				Ready.exch(a,j,j-1);
	}
	
	public static void shellSort(Comparable[] a) {
		//ϣ������˼�룺����ͬ��������ֻ�Ǽ����Ϊh���Ҽ���𽥱�С
		int N = a.length;
		for(int h = N/2; h >= 1; h=h/2) 
			for( int i = h; i < N; i++) 
				for( int j = i; j >= h && Ready.less(a[j],a[j-h]); j=j-h) 
					Ready.exch(a, j, j-h);
	}
	
	//�ϲ������ʵ��
	private static Comparable[] aux;
	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		for(int k = lo; k <= hi; k++) 
			if(i > mid) {
				a[k] = aux[j];
				j = j+1;
			}
			else if (j > hi) {
				a[k] = aux[i];
				i = i+1;
			}
			else if(Ready.less(aux[j],aux[i])) {
				a[k] = aux[j];
				j = j+1;
			}
			else {
				a[k] = aux[i];
				i = i+1;
			}
	}
	public static void msort(Comparable[] a, int lo, int hi ) {
		int mid = (lo + hi)/2;
		if(lo >= hi) return;
		msort(a, lo, mid);
		msort(a, mid+1, hi);
		merge(a,lo,mid,hi);
	}
	public static void mergeSort(Comparable[] a) {
		aux = new Comparable[a.length];
		msort(a, 0, a.length-1);
	}
	
	//���������ʵ��
	public static int partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[hi];
		int i=lo-1;
		for(int j= lo; j < hi; j++)
			if(Ready.less(a[j], v)) {//��a[j]<v,��a[j]Ϊ��i=i+1��С��v�������������ڸ�λ�õ�����a[j]����
				i=i+1;
				Ready.exch(a, i ,j);
			}
		Ready.exch(a, i+1, hi);//��i��С��v��������a[hi]Ӧ��i+1��λ����
		return i+1;
	}
	public static void qsort(Comparable[] a, int lo, int hi) {
		if(lo >= hi) return;
		int i = partition(a, lo, hi);
		qsort(a, lo, i - 1);
		qsort(a, i + 1, hi);
	}
	public static void quickSort(Comparable[] a) {
		qsort(a, 0, a.length-1);
	}
	
	public static void main(String[] args){ 

		/*Integer[] a = {55, 33, 23, 1, 34, 121, 45, 24, 46};//Ԥ�ȸ���һ������a
		
		selectionSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
		
		insertionSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
		
		shellSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
		
		mergeSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
		*/
		Integer[] a = {55, 33, 23, 1, 34, 121, 45, 24, 46};//Ԥ�ȸ���һ������a
		quickSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
	}

}

