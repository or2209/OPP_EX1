package Ex1Testing;
import myMath.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class PolynomTest_Junit {

    @Test
    public void f() {
        Polynom x = new Polynom("2x^3-8x^2+3x-2");
        Polynom y = new Polynom("-2");
        assertEquals(x.f(0.0), y.f(0.0), 0.000001);
       
    }

    @Test
    public void add() {
        Polynom x = new Polynom("2x^3-8x^2+3x-2");
        Polynom y = new Polynom("-2");
        x.add(y);
        Polynom temp = new Polynom("2x^3-8x^2+3x-4");
        String temp_s = temp.toString();
        String x_s = x.toString();
        assertEquals(x_s, temp_s);
    }


    @Test
    public void substract() {
        Polynom x = new Polynom("2x^3-8x^2+3x-2");
        Polynom y = new Polynom("-2");
        x.substract(y);
        Polynom temp = new Polynom("2x^3-8x^2+3x");
        String temp_s = temp.toString();
        String x_s = x.toString();
        assertEquals(x_s, temp_s);
    }

    @Test
    public void multiply() {
        Polynom x = new Polynom("2x^3-8x^2");
        Polynom y = new Polynom("-2x+2");
        Polynom s = new Polynom("-4x^4+20x^3-16x^2");
        x.multiply(y);
        String temp_s = s.toString();
        String x_s = x.toString();

        assertEquals(x_s, temp_s);


    }


    @Test
    public void isZero() {
        Polynom s = new Polynom("-4x^4+20x^3-16x^2");
        s.substract(s);
        System.out.println(s);
        Polynom u = new Polynom("0");
        String p_s = s.toString();
        String u_s = u.toString();
       assertEquals(u_s, p_s);
    }

    @Test
    public void root() {
        Polynom_able x= new Polynom("x^2+2x+2");
        Polynom y= new Polynom("2x+2");
        x=(Polynom_able)x.derivative();
        double root_x= x.root(-8,1,Polynom.EPSILON);
        double root_y= y.root(-8,1,Polynom.EPSILON);
        assertEquals(root_x,root_y,0.0000001);

    }

    @Test
    public void copy() {
        Polynom p=new Polynom("6x");
        Polynom_able t=new Polynom();
        t= p.copy();
       String temp_s = t.toString();
        assertEquals("6.0x", temp_s);

    }

    @Test
    public void derivative() {
        Polynom p=new Polynom("5x^2+8+6x");
        Polynom t= new Polynom();
        t=(Polynom)p.derivative();
        Polynom s=new Polynom("5x^2+2+4x+2x");
        Polynom t1= new Polynom();
        t1=(Polynom)s.derivative();
        String temp_t = t.toString();
        String temp_t1 = t1.toString();
        assertEquals(temp_t, temp_t1);


    }

    @Test
    public void area() {
        double sum=0;
        Polynom p=new Polynom("x^2");
        sum=p.area(2,3,0.000001);
        assertEquals(sum, 6.3333333333333333333, 0.0001);

    }


}