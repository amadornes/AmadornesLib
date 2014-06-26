package com.amadornes.lib.color;

public class Color {

	private double r, g, b, a = 1D;

	public Color(double red, double green, double blue) {
		this(red, green, blue, 1);
	}

	public Color(double red, double green, double blue, double alpha) {
		this.r = red;
		this.g = green;
		this.b = blue;
		this.a = alpha;
	}

	public double getR() {
		return r;
	}

	public double getG() {
		return g;
	}

	public double getB() {
		return b;
	}

	public double getA() {
		return a;
	}

	public void setR(double r) {
		this.r = r;
	}

	public void setG(double g) {
		this.g = g;
	}

	public void setB(double b) {
		this.b = b;
	}

	public void setA(double a) {
		this.a = a;
	}

}
