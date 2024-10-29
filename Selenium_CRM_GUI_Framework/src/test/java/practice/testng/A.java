package practice.testng;

import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;



public class A extends BaseClass {

	@Test
	public void m1() {
		System.out.println("m1 method of class A");
	}
	
	@Test
	public void m2() {
		System.out.println("m2 method of class A");
	}
	
	@Test
	public void m3() {
		System.out.println("m3 method of class A");
	}
}
