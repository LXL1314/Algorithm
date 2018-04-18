package chapter_2_sort;

//升序排序
public class Sort {
	
	public static void selectionSort(Comparable[] a) {
		//选择排序思想：找到数组中最小的元素，然后和第一个元素交换位置；找到剩下的数中最小的元素和第二个元素交换位置；...；直至整个数组排序完。
		//复杂度：对于长度为 N 的数组， 选择排序需要大约 N2/2 次比较和 N 次交换。复杂度O(N2)
		//特点：与该数组是否有序无关
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
		//插入排序思想：到第j个元素时，其前面的数组是已经排好序的，如a[j]比它前面的那个元素小，则要把a[j]插入到前面已排好的数组中去。
		//所以在j>0时，一直交换a[j]和a[j-1]的顺序，直到a[j]不比a[j-1]小。
		//复杂度：平均情况下插入排序需要～ N2/4 次比较以及～ N2/4 次交换,
		// 最坏情况（降序，从大到小排序，则每次都需要比较和交换）下需要～ N2/2 次比较和～ N2/2 次交换， 
		// 最好情况（升序）下需要 N-1次比较和 0 次交换。
		int N = a.length;
		for(int i = 1; i < N; i++) 
			for(int j = i; j>=1 && Ready.less(a[j], a[j-1]); j--) 
				Ready.exch(a,j,j-1);
	}
	
	public static void shellSort(Comparable[] a) {
		//希尔排序思想：大致同插入排序，只是间隔变为h，且间隔逐渐变小
		int N = a.length;
		for(int h = N/2; h >= 1; h=h/2) 
			for( int i = h; i < N; i++) 
				for( int j = i; j >= h && Ready.less(a[j],a[j-h]); j=j-h) 
					Ready.exch(a, j, j-h);
	}
	
	//合并排序的实现
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
	
	//快速排序的实现
	public static int partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[hi];
		int i=lo-1;
		for(int j= lo; j < hi; j++)
			if(Ready.less(a[j], v)) {//若a[j]<v,则a[j]为第i=i+1个小于v的数，将现在在该位置的数与a[j]交换
				i=i+1;
				Ready.exch(a, i ,j);
			}
		Ready.exch(a, i+1, hi);//有i个小于v的数，则a[hi]应在i+1的位置上
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

		/*Integer[] a = {55, 33, 23, 1, 34, 121, 45, 24, 46};//预先给定一个数组a
		
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
		Integer[] a = {55, 33, 23, 1, 34, 121, 45, 24, 46};//预先给定一个数组a
		quickSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
	}

}

