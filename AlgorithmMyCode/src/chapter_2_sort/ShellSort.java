package chapter_2_sort;

public class ShellSort {
	public static void sort(Comparable[] a) {
		for(int gap = a.length; gap >= 1; gap = gap / 2)
			for(int i = gap; i < a.length; i = i + gap)
				for(int j = i; j >= gap && Ready.less(a[j], a[j-gap]); j = j-gap)
					Ready.exch(a, j, j-gap);
	}

	public static void main(String[] args){ 
		Integer[] a = {3, 78, 55, 33, 23, 1, 34, 121, 45, 24, 46};
		sort(a);
		assert Ready.isSorted(a);
		Ready.show(a);
	}
	
}
