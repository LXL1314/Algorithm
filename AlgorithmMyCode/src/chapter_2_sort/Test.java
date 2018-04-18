package chapter_2_sort;

import chapter_2_sort.OuterClass.InnerClass;

public class Test {

	public static void main(String[] args){ 
		OuterClass outer = new OuterClass();
		OuterClass.InnerClass inner = outer.new InnerClass();
		OuterClass.StaticInnerClass sinner = new OuterClass.StaticInnerClass();
		outer.tt();
	}
	
}
