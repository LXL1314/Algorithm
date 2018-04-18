package chapter_2_sort;

public class SelectionSort {
	public static void sort(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			int min = i;
			for(int j = i+1; j < a.length; j++) 
				if(Ready.less(a[j], a[min]))
					min=j;
			Ready.exch(a, min, i);
		}
	}

	public static void main(String[] args){ 
		Integer[] a = {55, 33, 23, 1, 34, 121, 45, 24, 46};
		sort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
	}
	
}
