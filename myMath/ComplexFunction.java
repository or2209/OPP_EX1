package myMath;

//import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
//import com.sun.xml.internal.ws.api.model.ExceptionType;

import java.util.LinkedList;

public class ComplexFunction implements complex_function {
	private function left, right;
	private Operation operation;

	public ComplexFunction() {
		this.operation = null;
		this.left = null;
		this.right = null;
	}
	public boolean equals(Object obj)
	{
		if((this!=null && obj==null) || (this==null && obj!=null)) {
			return false;
		}
		if(this.operation==Operation.None) {
			if( obj instanceof Monom) {
				return obj.equals(this.left);
			}
			if(obj instanceof Polynom) {
				return obj.equals(this.left);
			}

		}
		if(obj instanceof ComplexFunction) {
			for (double i = -100000; i <1000000 ; i=i+0.5) {
				if(((ComplexFunction) obj).f(i)!= this.f(i)) {
					return false;
				}

			}
			return true;
		}
		return false;


	}


	public ComplexFunction(Operation ope, function f1, function f2) {
		this.left = f1;
		this.right = f2;
		this.operation = ope;
		if ((f1 == null)) {
			this.left = null;
		}
		if (f2 == null) {
			this.right = null;
		}
	}

	public ComplexFunction(Polynom p3) {
		this.setLeft(p3);
       this.right=null;
	    this.operation=Operation.None;
	}

	public ComplexFunction(function f) {
		if(f instanceof ComplexFunction) {
			this.left = ((ComplexFunction) f).left();
			this.right=((ComplexFunction) f).right();
			this.operation=((ComplexFunction) f).operation;

		}

	}


	public void plus(function f1) {
		function v1 = this.copy();
		this.operation = Operation.Plus;
		this.right = f1;
		this.left = v1;
	}

	@Override
	public void mul(function f1) {
		function v1 = this.copy();
		this.operation = Operation.Times;
		this.right = f1;
		this.left = v1;

	}

	@Override
	public void div(function f1) {
		function v1 = this.copy();
		this.operation = Operation.Divid;
		this.right = f1;
		this.left = v1;
	}

	@Override
	public void max(function f1) {
		function v1 = this.copy();
		this.operation = Operation.Max;
		this.right = f1;
		this.left = v1;
	}

	@Override
	public void min(function f1) {
		function v1 = this.copy();
		this.operation = Operation.Min;
		this.right = f1;
		this.left = v1;
	}

	@Override
	public void comp(function f1) {
		function v1 = this.copy();
		this.operation = Operation.Comp;
		this.right = f1;
		this.left = v1;
	}


	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.operation;
	}

	public double f(double x) {

		double sum = 0;
		switch (this.operation) {
		case Plus:
			return sum + this.left.f(x) + this.right.f(x);
		case Divid:
			return sum + this.left.f(x) / this.right.f(x);
		case Times:
			return sum + this.left.f(x) * this.right.f(x);
		case Max:
			return sum + Math.max(this.left.f(x), this.right.f(x));
		case Min:
			return sum + Math.min(this.left.f(x), this.right.f(x));
		case Comp:
			return sum + this.left.f(this.right.f(x));
		case None:
			if (this.right == null) {
				return sum + left().f(x);
			} else {
				return sum + right().f(x);
			}
		case Error:
			return sum + 0;
		default:
			System.out.println("error");
		}
		return sum;
	}


	@Override
	public String toString() {
		String ans="";
		if(this.operation==Operation.None) {
			ans+=this.left;
		}else {
			ans+=this.operation + "(" + this.left + "," + this.right + ")";

		}

		return ans;

	}

	public ComplexFunction(String s, function f1, function f2) {
		this.operation = help_op(s);
		this.left = f1;
		this.right = f2;
	}

	@Override
	public function initFromString(String s) {
		function ans = new ComplexFunction(s);
		return ans;
	}

	public Operation help_op(String s) {
		s = s.toLowerCase();
		//        if (s != "max" && s != "min" && s != "mult" && s != "plus" && s != "comp" && s != "none" && s != "divid")
		//            throw new ArithmeticException();
		if (s.charAt(0) == 'p') {
			return this.operation = Operation.Plus;
		}
		if (s.charAt(0) == 'd') {
			return this.operation = Operation.Divid;
		}
		if (s.charAt(0) == 'm' && s.charAt(1) == 'u') {
			return this.operation = Operation.Times;
		}
		if (s.charAt(0) == 't') {
			return this.operation = Operation.Times;
		}
		if (s.charAt(0) == 'c') {
			return this.operation = Operation.Comp;
		}
		if (s.charAt(0) == 'm' && s.charAt(1) == 'i') {
			return this.operation = Operation.Min;
		}
		if (s.charAt(0) == 'm' && s.charAt(1) == 'a') {
			return this.operation = Operation.Max;
		}
		return Operation.None;
	}

	public function getLeft() {
		return left;
	}

	public void setLeft(function left) {
		this.left = left;
	}

	public function getRight() {
		return right;
	}

	public void setRight(function right) {
		this.right = right;
	}

	public ComplexFunction(String s) {

		if (s.startsWith("+")) {
			s = s.substring(1, s.length());
		}

		s = s.toLowerCase();
		if (s.charAt(0) != 't' && s.charAt(0) != 'd' && s.charAt(0) != 'm' && s.charAt(0) != 'c' && s.charAt(0) != 'n' && s.charAt(0) != 'p') {
			Polynom temp = new Polynom(s);
			this.operation = Operation.None;
			this.left = temp;
			return;
		}
		int i = 0;
		int count = 0;
		String op = "";
		String left = "";
		String right = "";
		for (; i < s.length() && s.charAt(i) != '('; i++) {
			op += s.charAt(i);
		}
		this.operation = help_op(op);
		i++;
		count++;
		s = s.substring(i, s.length());
		if (s.endsWith(")")) {
			s = s.substring(0, s.length() - 1);
		}
		for (int j = 0; j < s.length(); j++) {

			if (s.charAt(j) == '(') {
				count++;
			}
			if (s.charAt(j) == ')')
				count--;

			if (s.charAt(j) == ',') {
				if (count == 1) {
					left = s.substring(0, j);
					right = s.substring(j + 1, s.length());
					break;
				}
			}
		}
		if (right.startsWith("+")) {
			right = right.substring(1, right.length());
		}
		if (left.startsWith("+")) {
			left = left.substring(1, left.length());
		}
		if ((left.charAt(0) <= '9' && left.charAt(0) >= '0') || left.charAt(0) == '+' || left.charAt(0) == '-' || left.charAt(0) == 'x')
			this.left = new Polynom(left);
		else this.left = initFromString(left);
		if ((right.charAt(0) <= '9' && right.charAt(0) >= '0') || right.charAt(0) == '+' || right.charAt(0) == '-' || right.charAt(0) == 'x')
			this.right = new Polynom(right);
		else this.right = initFromString(right);

	}


	public function copy() {
		function cop = new ComplexFunction(this.operation, this.left, this.right);
		return cop;

	}
}
