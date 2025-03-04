package myMath;

import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3_ERR();
		test4();
	}


	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2x^2","8.3","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(2,5));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			double r= monoms.get(i).get_coefficient();
			
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}
	private static void test3_ERR() {
		System.out.println("*****  Test3_ERR:  *****");
		Monom x = new Monom();
 		Monom sum = new Monom();
		String[] Bad_Monom = { "5x^&", "2!","2@"};
		for (int i = 0; i < Bad_Monom.length; i++) {
			x = new Monom(Bad_Monom[i]);
		}
	}

	private static void test4() {
		System.out.println("*****  Test4:  *****");
		Monom x = new Monom();
		Monom sum = new Monom();
		String[] GOOD_Monom = { "8X^6", "4X^6","2X^2"};
		for (int i = 0; i < GOOD_Monom.length; i++) {
			x = new Monom(GOOD_Monom[i]);
			sum.add(x);
		}
		System.out.println(sum);

	}}