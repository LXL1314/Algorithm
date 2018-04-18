package chapter_2_sort;

import java.util.Comparator;

public class Transaction {
	public static class ValueComparator implements Comparator<Integer>{
		public int compare(Integer v1, Integer v2) {
			return v1.compareTo(v2);
		}
	}
	
	public static void main(String[] args){ 
		Integer v1 = 3, v2 = 5;
		System.out.println(v1.compareTo(v2));
		String a = "Abc", b = "abdf";
		System.out.println(a.compareTo(b));
		System.out.println(a.compareToIgnoreCase(b));
	}

}
