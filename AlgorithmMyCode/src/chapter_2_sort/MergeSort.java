package chapter_2_sort;

public class MergeSort {
	public static Comparable[] tem ;
	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		
		for(int i = lo; i <= hi; i++)
			tem[i] = a[i];
		int j = mid + 1, i = lo;
		for(int k = lo; k <= hi; k++)
			if(i > mid) {
				a[k] = tem[j];
				j++;	
			}
			else if(j > hi) {
				a[k] = tem[i];
				i++;
			}
			else if( Ready.less(tem[j], tem[i])) {
				a[k] = tem[j];
				j++;
			}
			else {
				a[k] = tem[i];
				i++;
			}
	}
	
	public static void sort(Comparable[] a, int lo, int hi) {
		int mid = (lo + hi)/2;
		if(lo >= hi) return;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}
	
	public static void mergeSort(Comparable[] a) {
		tem = new  Comparable[a.length];
		sort(a, 0, a.length-1);
	}
	
	public static void main(String[] args){ 
		Integer[] a = {3, 78, 55, 33, 23, 1, 34, 121, 45, 24, 46};
		mergeSort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
	}

}
