package me.zeph.relations.model;

public class Unit {
	private double locus;
	private double value;
	private String pattern;

	public Unit(double locus, double value) {
		this.locus = locus;
		this.value = value;
	}

	public double getLocus() {
		return locus;
	}

	public double getValue() {
		return value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
