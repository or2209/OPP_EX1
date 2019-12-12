package myMath;
import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		String strI = "" + a;
		if(!strI.contains(".")) {
			Double c = Double.parseDouble(strI);
			a=c;
		}

		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom() {
		this._coefficient=0;
		this._power=0;
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public void set_poewr(int x) {
		this._power=x;
	}

	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) {
		if(s.charAt(s.length()-1)==' ') {
			s=s.substring(0,s.length()-1);
		}
		char q=0;
		s=s.toLowerCase();
		String temp =s;
		s="";
		for (int i = 0; i <temp.length(); i++) {
			if (temp.charAt(i)!=' '){
			s+=temp.charAt(i);
			}
		}
		if(s.charAt(s.length()-1)==' '){
			s=s.substring(0,s.length()-1);

		}
		s=s.toLowerCase();
		int t = 0,i=0;
		int p=0;
		int count=0;
		double c=0;
		String e="";
		String left="";
		try {
			if (((int) s.charAt(0) <= 57) && ((int) s.charAt(0) >= 48) && s.contains("x")) {
				while (s.charAt(t) != 'x') {
					left += s.charAt(t);
					t++;
					count++;
				}
				c = Double.parseDouble(left);
				set_coefficient(c);
				if (s.contains("^")) {
					e += s.substring(left.length() + 2, s.length());
					p = Integer.parseInt(e);
					set_power(p);
				}
				if (!s.contains("^")) {
					set_power(1);
				}
			}

			if (!s.contains("x")) {// num only
				c = Double.parseDouble(s);
				set_coefficient(c);
				set_power(0);
				this._coefficient = c;
				this._power = 0;
			}

			if ((s.contains("x")) && (s.charAt(0) == '-')) {
				while (s.charAt(i) != 'x') {
					left += s.charAt(i);
					i++;
				}
				if ((left.length() == 1)) {
					left += "1";
				}
				c = Double.parseDouble(left);
				set_coefficient(c);
				if ('x' == s.charAt(s.length() - 1))
					set_power(1);
				else {
					e = s.substring(left.length() + 2, s.length());
					p = Integer.parseInt(e);
					set_power(p);
				}
			}
			if (s.charAt(0) == 'x') {
				set_coefficient(1);
				if ('x' == s.charAt(s.length() - 1))
					set_power(1);
				else {
					e = s.substring(left.length() + 2, s.length());
					p = Integer.parseInt(e);
					set_power(p);
				}
			}
		}
	
		catch (Exception E){
	System.out.println("INPUT NOT LEGAL:"+s + " INPUT LEGAL FROM SHAPE:(-/+)Ax^b or (-/+)Ax or (-/+)A");
		}
	}


	public  void set_coefficient(double a){
		this._coefficient = a;
	}

	public void add(Monom m) {

		if(m._power==this._power)
		{
			this._coefficient+=m._coefficient;
		}
		if (this._coefficient==0)
		{
			this._coefficient+=m._coefficient;
			this._power=m._power;

		}
		if(m._coefficient==0) {
			this._coefficient += m._coefficient;
			this._power += m._coefficient;
		}
	}
	public  boolean equals (Object Temp) {
		if(Temp==null || !(Temp instanceof Monom)) return false;
		Temp=(Monom)Temp;
		if(((Monom) Temp)._coefficient-this._coefficient<=EPSILON && ((Monom) Temp)._power==this._power)
			return true;
		return false;

	}

	public void multipy(Monom d) {
		this._coefficient*=d._coefficient;
		this._power+=d._power;

	}

	public String toString() {
		String ans="";
		if(this._power==0) {
			ans+=this._coefficient;
		}
		if(this._power==1) {
			ans+=this._coefficient+"x";
		}
		if(this._power>1) {
			ans+=this._coefficient+"x^"+this._power;
		}
		return ans;
	}

	@Override
	public function initFromString(String s) {
		function p= new Monom(s);

		 return p;
	}

	@Override
	public function copy() {
		/*String s =this.toString();
		function copy = new Monom(s);*/
		return new Monom(this.toString());


	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;


}
