package myMath;
import myMath.ComplexFunction;
//import com.google.gson.JsonElement;
//import com.google.gson.*;
import jdk.nashorn.api.scripting.JSObject;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import javax.swing.text.Style;

//+import com.google.gson.Gson;
//import jdk.nashorn.internal.parser.JSONParser;

import javax.xml.stream.events.DTD;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;
import java.util.function.Function;

/**
 * This class demonstrate a simple set of sin funxtions and draw them in a GUI window.
 * A trivial example of inner class is also presented.
 */
public class Functions_GUI implements functions {
	private ArrayList<function> f = new ArrayList<function>();
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
			Color.red, Color.GREEN, Color.PINK,Color.CYAN};

	public Functions_GUI() {
		f = new ArrayList<function>();
	}

	public void initFromFile(String file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String buffer;
		String fulltext = "";
		Functions_GUI init = new Functions_GUI();
		ComplexFunction q3 = new ComplexFunction();
		function f3 = new ComplexFunction();
		while ((buffer = br.readLine()) != null) {
			//System.out.println(buffer);
			f3 = q3.initFromString(buffer);
			this.add(f3);
		}
		br.close();
		fr.close();
	}

	@Override
	public void saveToFile(String file) throws IOException {
		PrintWriter pw = new PrintWriter(new File(file));
		StringBuilder sb = new StringBuilder();
		Iterator<function> p = f.iterator();
		while (p.hasNext()) {
			pw.write(p.next().toString()+ "\n");
		}
		pw.close();
	}

	@Override
	public  void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width, height);
		int size = f.size();
		double[] x = new double[resolution+1];
		double[] y = new double[resolution+1];
		double[][] yy = new double[size][resolution+1];
		double x_step = (rx.get_max()-rx.get_min())/resolution;
		double x0 = rx.get_min();

		for (int i = 0; i <=resolution ; i++) {
			x[i]=x0;
			for (int a = 0; a < this.size(); a++) {
				yy[a][i]=f.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		//numbers on axis

		StdDraw.setPenRadius(0.003);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.text(i, -0.30, Integer.toString(Math.toIntExact((long) i)));
		}

		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(-0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		int n=resolution;
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(Color.black);
		StdDraw.line(rx.get_min(),0,rx.get_max(),0);
		StdDraw.line(0,ry.get_min(),0,ry.get_max());
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(0, y[n/2], Math.PI, y[n/2]);
		for (int a = 0; a < this.size(); a++) {
			int c = a % Colors.length;
			StdDraw.setPenColor(Colors[c]);
			System.out.println(a + ") " + Colors[a] + "  f(x)= " + f.get(a));
			for (int i = 0; i < resolution; i++) {
				StdDraw.line(x[i], yy[a][i], x[i + 1], yy[a][i + 1]);
			}
		}
	}


	@Override
	public void drawFunctions(String json_file) throws IOException {
		long w = 1000, h = 600, res =200;
		Range rx = new Range(-10, 10);
		Range ry = new Range(-5, 15);
		double[] Range_X = {-10, 10};
		double[] Range_Y = {-5, 15};
		JSONParser parser = new JSONParser();
		JSONArray X = new JSONArray();
		JSONArray Y = new JSONArray();
		
		try {
			Object obj = parser.parse(new FileReader(json_file));
			JSONObject jsonObject = (JSONObject) obj;
	        w =(long) jsonObject.get("Width");
			h =(long) jsonObject.get("Height");
			res =(long) jsonObject.get("Resolution");
			JSONArray rangeX = (JSONArray)jsonObject.get("Range_X");
			JSONArray rangeY = (JSONArray)jsonObject.get("Range_Y");	
			String x_min= rangeX.get(0).toString();
			String x_max= rangeX.get(1).toString();
			String y_min= rangeY.get(0).toString();
			String y_max= rangeY.get(1).toString();
			Range_X[0]=Double.parseDouble(x_min);	
			Range_X[1]=Double.parseDouble(x_max);			
			Range_Y[0]=Double.parseDouble(y_min);
			Range_Y[1]=Double.parseDouble(y_max);
			rx = new Range(Range_X[0], Range_X[1]);
			ry = new Range(Range_Y[0], Range_Y[1]);
			this.drawFunctions((int)w, (int)h, rx, ry, (int) res);
            
		}
		catch(Exception e){
			this.drawFunctions((int)w, (int)h, rx, ry, (int) res);
			System.out.println("Default valus");

		}
	
	}

	@Override
	public int size() {
		return f.size();
	}

	@Override
	public boolean isEmpty() {
		return f.isEmpty();
	}


	@Override
	public boolean contains(Object o) {
		return f.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return f.iterator();
	}

	@Override
	public Object[] toArray() {
		return f.toArray();
	}

	@Override
	public <T> T[] toArray(T[] ts) {
		return f.toArray(ts);
	}

	@Override
	public boolean add(function function) {
		return f.add(function);
	}

	@Override
	public boolean remove(Object o) {
		return f.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		return f.containsAll(collection);
	}

	@Override
	public boolean addAll(Collection<? extends function> collection) {
		return f.addAll(collection);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		return f.retainAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		return f.retainAll(collection);
	}

	@Override
	public void clear() {
		f.clear();
	}

	@Override
	public String toString() {
		String ans = "";
		for (int i = 0; i < f.size(); i++) {
			ans += f.get(i).toString() + "\n";
		}
		return ans;
	}

//	public static void main(String[] args) throws IOException {
//		ComplexFunction cf1 = new ComplexFunction("plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0)");
//		ComplexFunction cf2 = new ComplexFunction("plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
//		ComplexFunction cf3 = new ComplexFunction("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
//		ComplexFunction cf4 = new ComplexFunction("-1.0x^4 +2.4x^2 +3.1");
//		ComplexFunction cf5 = new ComplexFunction("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
//		ComplexFunction cf6 = new ComplexFunction("min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
//		ComplexFunction cf7 = new ComplexFunction("0.1x^5 -1.2999999999999998x +5.0");
//		Functions_GUI a=new Functions_GUI();
//		a.add(cf1);
//		a.add(cf2);
//		a.add(cf3);
//		a.add(cf4);
//		a.add(cf5);
//		a.add(cf6);
//		a.add(cf7);
//		for (int i = 0; i < a.size(); i++) {
//			System.out.println(a.f.get(i).f(0));
//		}
//		int w=1000, h=600, res=200;
//		Range rx = new Range(-10,10);
//		Range ry = new Range(-5,15);
//		
//		//a.drawFunctions("/home/netanel/Downloads/GUI_params.json");
//
//	
//	}

}

