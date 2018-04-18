package chapter_2_sort;

import java.util.Comparator;
import java.util.Date;

public class TransactionMul implements Comparable<TransactionMul>{

	private final String  who;      // customer
    private final Date    when;     // date
    private final double  amount;   // amount

    public String who() {
        return who;
    }
    public Date when() {
        return when;
    }
 
    
    public double amount() {
        return amount;
    }
    
    public TransactionMul(String who, Date when, double amount) {
    	this.who = who;
    	this.when = when;
    	this.amount = amount;
    }
    
   
	public TransactionMul(String str) {
    	String[] a = str.split("\\s+");
    	who = a[0];
    	when = new Date(a[1]);
    	amount = Double.parseDouble(a[2]);
    }
    
    public int compareTo(TransactionMul that) {
        return Double.compare(this.amount, that.amount);
    }    
    
    public static class WhoOrder implements Comparator<TransactionMul>{
    	public int compare(TransactionMul o1, TransactionMul o2) {
    		return o1.who.compareTo(o2.who);
    	}
    }
    
    public static class WhenOrder implements Comparator<TransactionMul>{
    	public int compare(TransactionMul o1, TransactionMul o2) {
    		return o1.when.compareTo(o2.when);
    	}
    }
    
    public static class HowMuchOrder implements Comparator<TransactionMul>{
    	public int compare(TransactionMul o1, TransactionMul o2) {
    		return Double.compare(o1.amount, o2.amount);
    	}
    }
    
    public String toString() {
    	return String.format("%-10s %10s %8.2f", who, when, amount);
    }
    
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
	
	public static void show(Object[] a) {
		for(int i = 0; i < a.length; i++)
			System.out.println(a[i].toString());
	}
    
    public static void main(String[] args) {
    	TransactionMul[] a = new TransactionMul[4];
    	a[0] = new TransactionMul("Turing   6/17/1990  644.08");
    	a[1] = new TransactionMul("Tarjan   3/26/2002 4121.85");
    	a[2] = new TransactionMul("Knuth    6/14/1999  288.34");
    	a[3] = new TransactionMul("Dijkstra 8/22/2007 2678.40");
    	
    	System.out.println("Unsoted");
    	show(a);
    	
    	System.out.println("WhoOrder");
    	sort(a, new TransactionMul.WhoOrder());
    	show(a);
    	
    	System.out.println("WhenOrder");
    	sort(a, new TransactionMul.WhenOrder());
    	show(a);
    	
    	System.out.println("HowMuchOrder");
    	sort(a, new TransactionMul.HowMuchOrder());
    	show(a);
    }
    
}
