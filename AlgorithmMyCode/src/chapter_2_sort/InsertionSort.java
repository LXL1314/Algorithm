package chapter_2_sort;

public class InsertionSort {
	public static void sort(Comparable[] a) {
		for(int i = 1; i < a.length; i++) 
			for(int j = i; j >= 1 && Ready.less(a[j], a[j-1]); j--) 
				Ready.exch(a, j-1, j);
	}

	public static void main(String[] args){ 
		Integer[] a = {55, 33, 23, 1, 34, 121, 45, 24, 46};
		sort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
	}
	
}
