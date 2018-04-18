package chapter_2_sort;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class InsertionSortMulKey {
	public static void sort(Object[] a, Comparator c) {
		int N = a.length;
		for(int i = 1; i < N; i++)
			for(int j = i; j >= 1 && less(c, a[j], a[j-1]); j--)
				exch(a, j, j-1);
	}
	public static boolean less(Comparator c, Object v, Object w) {
		return c.compare(v, w) < 0;
	}

	public static void exch(Object[] a, int i, int j) {
		Object t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static void main(String[] args){ 
		Integer[] a= {1,4,6,3,7,45,67,12,31,40};
		sort(a, new Transaction.ValueComparator());
		assert Ready.isSorted(a);
		Ready.show(a);
	}
	
}
