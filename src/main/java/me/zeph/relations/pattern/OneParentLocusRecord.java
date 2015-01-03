package me.zeph.relations.pattern;

import org.springframework.util.StringUtils;

public class OneParentLocusRecord {

	public static final String SPACE = " ";
	public static final String P = "P";
	public static final String Q = "Q";
	public static final String R = "R";
	private Unit c1;
	private Unit c2;
	private Unit af1;
	private Unit af2;

	public OneParentLocusRecord(double c1, double c2, double af1, double af2) {
		this.c1 = new Unit(c1);
		this.c2 = new Unit(c2);
		this.af1 = new Unit(af1);
		this.af2 = new Unit(af2);
	}

	public String getPattern() {
		generatePattern();
		return getC1C2() + SPACE + getAF1AF2();
	}

	private void generatePattern() {
		if (c1.getValue() != c2.getValue()) {
			c1.setPattern(P);
			c2.setPattern(Q);
		}
		if (c1.getValue() == c2.getValue()) {
			c1.setPattern(Q);
			c2.setPattern(Q);
		}
		generateAfPattern();
	}

	private void generateAfPattern() {
		if (af1.getValue() == c1.getValue()) {
			af1.setPattern(c1.getPattern());
		}
		if (af1.getValue() == c2.getValue()) {
			af1.setPattern(c2.getPattern());
		}
		if (StringUtils.isEmpty(af1.getPattern())) {
			af1.setPattern(R);
		}
		if (af2.getValue() == c1.getValue()) {
			af2.setPattern(c1.getPattern());
		}
		if (af2.getValue() == c2.getValue()) {
			af2.setPattern(c2.getPattern());
		}
		if (StringUtils.isEmpty(af2.getPattern())) {
			af2.setPattern(R);
		}
	}

	private String getAF1AF2() {
		return af1.getPattern() + af2.getPattern();
	}

	private String getC1C2() {
		return c1.getPattern() + c2.getPattern();
	}

}
