package chapter_2_sort;

public class QuickSort {

	public static int partition(Comparable[] a, int lo, int hi) {
		int i = (int)(Math.random()*(hi-lo+1))+lo;
		Ready.exch(a, i, hi);
		
		Comparable key = a[hi];
		i = lo - 1;
		for( int k = lo; k <= hi - 1; k++)
			if(Ready.less(a[k], key)) {
				i = i + 1;
				Ready.exch(a, i, k);
			}
		Ready.exch(a, hi, i+1);
		return i+1;
	}
	
	public static void sort(Comparable[] a, int lo, int hi) {
		if(lo >= hi) return;
		int k = partition(a, lo, hi);
		sort(a, lo, k -1);
		sort(a, k + 1, hi);
	}
	
	public static void quickSort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
	public static void main(String[] args){ 
		Integer[] a = {1, 76, 34, 78, 55, 33, 23, 1, 121, 45, 24};
		quickSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
	}
	
}
