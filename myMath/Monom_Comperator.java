package myMath;

import java.util.Comparator;
import myMath.*;
public class Monom_Comperator implements Comparator<Monom> {

	public Monom_Comperator() {
		
		}
	public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		return dp;
	}

	// ******** add your code below *********

}
