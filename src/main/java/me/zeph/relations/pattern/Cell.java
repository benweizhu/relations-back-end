package me.zeph.relations.pattern;

public class Cell {
	private double value;
	private String pattern;

	public Cell(double value) {
		this.value = value;
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
