package myMath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;



/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {
	private LinkedList<Monom> r = new LinkedList<Monom>();
	private LinkedList<Character> c = new LinkedList<Character>();
	public static final double EPSILON = 0.0000001;


	/**
	 * Zero (empty polynom)
	 */
	/**
	 * init a new polynom-zero polynom
	 */
	public Polynom() {
		r.add(new Monom());
	}

	/**
	 * init a Polynom from a String such as:
	 * {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 *
	 * @param s: is a string represents a Polynom
	 */
	private Comparator<Monom> y = new Monom_Comperator();

	public Polynom(String s) {
		s = s.toLowerCase();
		s=s.toLowerCase();
		String temp1 =s;
		s="";
		for (int i = 0; i <temp1.length(); i++) {
			if (temp1.charAt(i)!=' '){
			s+=temp1.charAt(i);
			}
		}
		char q = 0;
		if(s.charAt(s.length()-1)==' ') {
			s=s.substring(0,s.length()-1);
		}
		if (s.charAt(0)=='+'){
			s=s.substring(1,s.length());
		}
		for (int i = 0; i <s.length(); i++) {
			if (s.charAt(i)==' '){
			s=s.replace(' ',q);
			}
		}



		String help = "";
		int i = 0;
		Monom t;
		boolean flag = true;
		try {
			if (s.charAt(0) == '-') {
				c.add(s.charAt(0));
				i++;
			}
			while (flag == true) {
				while ((i < s.length()) && (s.charAt(i) != '+' && s.charAt(i) != '-')) {
					help += s.charAt(i);
					i++;

				}
				for (int j = 0; j <s.length(); j++) {
					if (s.charAt(j)==' '){
					s=s.replace(' ',q);
					}
				}
				t = new Monom(help);
				help = "";
				r.add(t);
				if (i < s.length()) {
					c.add(s.charAt(i));
					i++;
				}
				if (i == s.length()) {
					flag = false;
				}
			}
			int j = 0, o = 0;

			if (r.size() == c.size() && c.get(0) == '-') {
				Monom temp = new Monom(-1 * (r.get(0).get_coefficient()), r.get(0).get_power());
				r.set(0, temp);
				o = 1;
				j = 1;
			} else {
				o = 1;
			}
			for (int k = o; k < r.size(); k++) {
				if (c.get(j) == '+' && j < c.size()) {
					j++;
					Monom temp = new Monom(r.get(k).get_coefficient(), r.get(k).get_power());
					r.set(k, temp);
				} else {
					Monom temp = new Monom(-(r.get(k).get_coefficient()), r.get(k).get_power());
					r.set(k, temp);
					j++;
				}
			}
		} catch (Exception e) {
			/*throw new ArithmeticException("ILLEGAL NUM-MUST BE: Ax^b+Bx+C");*/
			System.err.println("ILLEGAL NUM-MUST BE: Ax^b+Bx+C");
		}
		sort(r);
		r.sort(y);

	}

	@Override
	public double f(double x) {
		double sum = 0;
		Iterator<Monom> runer = this.iteretor();
		while (runer.hasNext()) {
			Monom temp = runer.next();
			sum += temp.f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator w = p1.iteretor();
		while (w.hasNext()) {
			Monom temp = ((Monom) w.next());
			r.add(temp);
		}
		sort(r);
		r.sort(y);

	}


	public void add(Monom m1) {
		int sum = 0;
		boolean t = true;
		for (int i = 0; i < r.size(); i++) {
			if (m1.get_power() == r.get(i).get_power()) {
				r.get(i).set_coefficient(m1.get_coefficient() + r.get(i).get_coefficient());
				t = false;
			}

		}
		if (t == true) {
			r.add(m1);
		}
		sort(r);
		r.sort(y);
		for (int i = 0; i < r.size(); i++) {
			if (r.get(i).get_coefficient() == 0 && r.size() > 1) {
				r.remove(i);
			}
		}
	}


	public void sort(LinkedList<Monom> x) {
		for (int i = 0; i < r.size(); i++) {
			for (int j = 0; j < r.size(); j++) {
				if (i != j) {
					if (x.get(i).get_power() == x.get(j).get_power()) {
						x.get(i).set_coefficient((x.get(i).get_coefficient() + x.get(j).get_coefficient()));
						x.remove(j);
					}
				}
			}

		}
		for (int i = 0; i < r.size(); i++) {
			if (r.get(i).get_coefficient() == 0) {
				r.remove(i);
			}
		}


		if (r == null) {
			r.set(0, new Monom(0, 0));
		}
	}

	public String toString() {
		sort(r);
		r.sort(y);
		String ans="";
		if (r.isEmpty()){
			return "0";
		}
		if (r.get(0).get_coefficient()>0){//insert first monom
			ans+=r.get(0).toString();
		}else{
			ans+=r.get(0).toString();
		}
		for (int i = 1; i <r.size() ; i++) {
			if (r.get(i).get_coefficient()>0){
				ans+="+";
			}
			ans+=r.get(i).toString();
		}
		return ans;
	}

	@Override
	public function initFromString(String s) {


		function p = new Polynom(s);
		return p;
	}


	@Override
	public void substract(Polynom_able p1)  {
	    if (r.equals(p1)){
	        r.remove();
	        Monom t= new Monom("0");
	        r.add(t);
	        return;
        }
		LinkedList<Monom> x = new LinkedList<Monom>();
		Iterator w =p1.iteretor();
		while(w.hasNext()) {	
			Monom temp=new Monom((Monom)w.next());
			Monom temp1=new Monom(-1*(temp.get_coefficient()),temp.get_power());
			x.add(temp1);
		}

			r.addAll(x);

        sort(r);
        r.sort(y);
	}
	@Override
	public void multiply(Polynom_able p1) {
		LinkedList<Monom> b = new LinkedList<Monom>();
		LinkedList<Monom> ans = new LinkedList<Monom>();
		Iterator w =p1.iteretor();
		int h= r.size();
		while(w.hasNext()) {			
			Monom temp=((Monom)w.next());
			b.add(temp);
		}
		for (int i = 0; i < h; i++) {
			ans.add(i, r.get(i));
		}
		r.removeAll(r);

		if(ans.size()<=b.size())
		{
			for (int j = 0; j < b.size(); j++) {
				for (int j2 = 0; j2 < ans.size(); j2++) {
					Monom temp1= new Monom(ans.get(j2)) ;
					ans.get(j2).multipy(b.get(j));
					r.add(ans.get(j2));
					ans.set(j2, temp1);
				}
			}
		}
		else {
			for (int j = 0; j < b.size(); j++) {
				for (int j2 = 0; j2 < ans.size(); j2++) {
					Monom temp1= new Monom(ans.get(j2)) ;
					ans.get(j2).multipy(b.get(j));
					r.add(ans.get(j2));
					ans.set(j2, temp1);
				}
			}	
		}
		sort(r);
		r.sort(y);
	}

	@Override
	public boolean equals(Object p1) {
		if(p1==null || !(p1 instanceof Polynom)) return false;
		p1=(Polynom)p1;
		Iterator i = ((Polynom) p1).iteretor();
			int count = 0, u = 0;
			while (i.hasNext()) {
				count++;
				i.next();
			}
			i = ((Polynom) p1).iteretor();
			if (r.size() != count) {
				return false;
			}
			while (i.hasNext()) {
				Monom temp = ((Monom) i.next());
				Monom o = r.get(u);
				u++;
				if (Math.abs(temp.get_coefficient() - o.get_coefficient()) > EPSILON || temp.get_power() != o.get_power()) {
					return false;
				}
			}


			return true;

	}

	@Override
	public boolean isZero() {
		for (int i = 0; i <r.size() ; i++) {
			if (r.get(i).get_coefficient()!=0){
				return false;
			}
		}
		//if(r.size()==1 && r.get(0).get_coefficient()==0)
			return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if(x0>x1){
			System.err.println("X1 bigger then X0 ****** SWAP X1 , X0");
		}
		if(this.f(x0)*this.f(x1)>0) {
		System.out.print("NO ROOT");
		return -1;
		}
		if(this.f(x0)==0){
			return x0;
		}
		if(this.f(x1)==0){
			return x1;
		}
		double y=x0;
		while((x1-x0)>=eps) {
			y=(x1+x0)/2;
			if(this.f(y)==0) {
				return y;
			}
			else if (this.f(y)*this.f(x0)<0) {
				x1=y;
			}
				else {
					x0=y;
				}
			}
			
		
		
		
		return y;
	}

	@Override
	public Polynom_able copy() {
		String p="";
		LinkedList<Monom> v = new LinkedList<Monom>();
		for (int i = 0; i < r.size(); i++) {
			v.add(r.get(i));
		}
		for (int i = 0; i < v.size(); i++) {
			if(v.get(i).get_coefficient()>0) {
				p+="+";
			}
			p+=v.get(i).toString();
		}
		if(p.charAt(0)=='+') {
			p=p.substring(1, p.length());
		}
		Polynom s= new Polynom(p);

		return s;
	}

	@Override
	public Polynom_able derivative() {
		Polynom_able p = new Polynom();
		Monom f = new Monom();
		String e="";
		int u=0;
		Iterator i =this.iteretor();
		while(i.hasNext()) {
			Monom temp = (Monom) i.next();
			f=temp.derivative();
			p.add(f);
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		double sum=0;
		if (x0>=x1){
			return 0;
		}
		while(x0<x1) {
			if(this.f(x0)>=0) {
				sum+=eps*this.f(x0);
				x0+=eps;
			}else {
				x0+=eps;

			}}
		return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return r.iterator();
	}
	@Override
	public void multiply(Monom m1) {

		for (int i = 0; i < r.size(); i++) {
			r.get(i).multipy(m1);
			if(r.get(i).get_coefficient()==0)
			r.remove();
		}

		sort(r);
		r.sort(y);


	}

}
