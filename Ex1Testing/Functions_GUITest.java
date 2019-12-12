package Ex1Testing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import myMath.*;



public class Functions_GUITest {




	/**
	 * Note: minor changes (thanks to Amichai!!)
	 * The use of "get" was replaced by iterator!
	 * 
	 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
	 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
	1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
	2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
	3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
	4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
	5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
	6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

	 * @author boaz_benmoshe
	 *
	 */
	
		public static void main(String[] a) throws IOException {
			functions data = FunctionsFactory();
		//	int w=1000, h=600, res=200;
		//	Range rx = new Range(-10,10);
		//	Range ry = new Range(-5,15);
//			data.drawFunctions(w,h,rx,ry,res);
			String file = "function_file.txt";
			String file2 = "function_file2.txt";
			try {
				data.saveToFile(file);
				Functions_GUI data2 = new Functions_GUI();
				data2.initFromFile(file);
				data.saveToFile(file2);
			}
			catch(Exception e) {e.printStackTrace();}
			
			String JSON_param_file = "GUI_params.txt";
			data.drawFunctions(JSON_param_file);
		}
		private functions _data=null;
//		@BeforeAll
//		static void setUpBeforeClass() throws Exception {
//		}

		//@BeforeEach
		public void setUp() throws Exception {
			_data = FunctionsFactory();
		}

		@Test
		public void testInitFromFile() {
			String u = "divid(plus(plus(8x^8,80),8x^9),x)";
	      String p2 = "min(min(min(min(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),plus(div(+1.0x+1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x-4.0)),2.0)),div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4 +2.4x^2+3.1),+0.1x^5-1.2999999999999998x+5.0)";
	      ComplexFunction q2 = new ComplexFunction();
	      function f2 = new ComplexFunction();
	      f2 = q2.initFromString(p2);
	      ComplexFunction q3 = new ComplexFunction();
	      function f3 = new ComplexFunction();
	      f3=q3.initFromString(u);
	      String t = f3.toString();
	      String t1 = f2.toString();
	      assertNotEquals(t, t1);
//	       u = "plus(x,x)";
//	       p2 = "x^2";
//	       q2 = new ComplexFunction();
//	       f2 = new ComplexFunction();
//	      f2 = q2.initFromString(p2);
//	      q3 = new ComplexFunction();
//	       f3 = new ComplexFunction();
//	      f3=q3.initFromString(u);
//	      assertEquals(f2, f3);
		//	fail("Not yet implemented");
		}

		@Test
		public void testSaveToFile() throws Exception {
			functions data = FunctionsFactory();
			String file = "function_file.txt";
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
		//	assertEquals(data, data2);
		}

		@Test
		public void testDrawFunctions() {
			Functions_GUI z = new Functions_GUI();
			Polynom z1 = new Polynom("-x^2");
			Polynom z2 = new Polynom("3x^2+5");
			Polynom z3 = new Polynom("4x^2+5x-3");
			z.add(z1);
			z.add(z2);
			z.add(z3);
			Range rx = new Range(-10,10);
			Range ry = new Range(-10,10);
			z.drawFunctions(700,700,rx,ry,500);
		}

		@Test
		public void testDrawFunctionsIntIntRangeRangeInt() throws IOException, InterruptedException {
			Functions_GUI z = new Functions_GUI();
			Polynom x = new Polynom("2x^3");
			Polynom y = new Polynom("8x+2x+2+x+x+x+X^8");
			z.add(x);
			z.add(y);
			try {
				z.drawFunctions("GUI_params.txt");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			//fail("Not yet implemented");
		}
		}
		public static functions FunctionsFactory() {
			functions ans = new Functions_GUI();
			String s1 = "3.1 +2.4x^2 -x^4";
			String s2 = "5 +2x -3.3x +0.1x^5";
			String[] s3 = {"x +3","x -2", "x -4"};
			Polynom p1 = new Polynom(s1);
			Polynom p2 = new Polynom(s2);
			Polynom p3 = new Polynom(s3[0]);
			ComplexFunction cf3 = new ComplexFunction(p3);
			for(int i=1;i<s3.length;i++) {
				cf3.mul(new Polynom(s3[i]));
			}
			
			ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
			ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
			cf4.plus(new Monom("2"));
			ans.add(cf.copy());
			ans.add(cf4.copy());
			cf.div(p1);
			ans.add(cf.copy());
			String s = cf.toString();
			function cf5 = cf4.initFromString(s1);
			function cf6 = cf4.initFromString(s2);
			ans.add(cf5.copy());
			ans.add(cf6.copy());
			Iterator<function> iter = ans.iterator();
			function f = iter.next();
			ComplexFunction max = new ComplexFunction(f);
			ComplexFunction min = new ComplexFunction(f);
			while(iter.hasNext()) {
				f = iter.next();
				max.max(f);
				min.min(f);
			}
			ans.add(max);
			ans.add(min);		
			return ans;
		}
	}
