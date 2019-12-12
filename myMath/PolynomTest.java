package myMath;

import java.awt.geom.Area;

public class PolynomTest {
	public static void main(String[] args) {
		test0();
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		erorTesting();
	}
	public static void test0(){
		System.out.println("******test0********");
		Polynom y=new Polynom();
		Polynom temp=new Polynom();
		String [] bad_Polynom ={"10x^6-2x+2" , "8@^2" , "xx^2"};
		for (int i = 0; i <bad_Polynom.length ; i++) {
			y = new Polynom(bad_Polynom[i]);
			temp.add(y);
		}
		System.out.println("temp: "+temp);
	}
	public static void test1() {
		System.out.println("******test1********");
		Polynom u = new Polynom("5x^6+2x^3+x+8");
		Polynom t= new Polynom();
		t=(Polynom)u.derivative();// t=u' && this=u'
		System.out.println(t.equals(u)); // TRUE
		Monom a =new Monom("1");
		u.multiply(a);// u*1
		System.out.println(u);// U same
		Monom b =new Monom("0");
		u.multiply(b);
		System.out.println(u);
		System.out.println(u.isZero());//TRUE
		Polynom q = new Polynom("x^2-2x+1");
		System.out.println(q.root(1,0,0.00001));
		System.out.println(q.area(-2,0,0.00001));

	}

	public static void test2() {
		System.out.println("******test2********");
		double a;
		Polynom t = new Polynom("x^2");
		//	Polynom n = new Polynom("-x^3+x");
		a=t.area(2, 5, 0.01);
		System.out.println(a);


	}
	public static void test3() {
		System.out.println("******test3********");
		Polynom p1 = new Polynom("x^2+8");
		Polynom p12 = new Polynom("2x");

		String[] monoms = {"1","x","3x", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[2]);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p12);
		System.out.println(p1);
	}
	public static void test4() {
		System.out.println("******test4********");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);

		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		//Polynom_able pp1 = Polynom1.parse(s1);
		//System.out.println("from string: "+pp1);
	}
	public static void test5() {
		System.out.println("******test5********");
		String[] monoms = {"x^2+5x", "x^3-x+5","-0.5x^2+3x^5","0"};
		for(int i=0;i<monoms.length;i++) {
			Polynom m = new Polynom(monoms[i]);
			String s = m.toString();
			m = new Polynom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}

	public static void test6() {
		System.out.println("******test6********");
		String[] monoms = {"x^2", "4x^2+2x-5","-1.3x^3","0"};
		for(int i=0;i<monoms.length;i++) {
			Polynom m = new Polynom(monoms[i]);
			String s = m.toString();


			Polynom m1 = new Polynom(s);

			boolean e = m.equals(m1);

			Polynom_able x = m.derivative();

			System.out.println(i+") "+m +"    \tDer: "+x+"  \teq: "+e);
		}
	}

	public static void test7() {
		System.out.println("******test7********");
		String[] monoms = {"x^2-4", "x^2-2x+1","x^4-16","0"};
		for(int i=0;i<monoms.length;i++) {
			Polynom m = new Polynom(monoms[i]);
			String s = m.toString();

			Polynom m1 = new Polynom(s);
			double a = m1.area(1, 5, 0.001);
			double r = m1.root(1, 4, 0.001);
			System.out.println(i+") "+s +"    \tarea: "+a+"  \troot: "+r);
		}
	}
	/**
	 * eror tester.
	 * enable manually
	 */
	private static void erorTesting() {
		System.out.println("************ erorTesting:************");
		Polynom x = new Polynom("3a");
			Polynom y = new Polynom("-x");
		Polynom z = new Polynom("-x^2");

		System.out.println(x.toString());
		System.out.println(y.root(1, 4, 0.001));
		System.out.println(z.area(2, 4, 0.001));
	}
}

