package me.zeph.relations.model;

import org.springframework.util.StringUtils;

public class OneParentLocusRecord {

	private static final String DOT = ".";
	private static final String P = "p";
	private static final String Q = "q";
	private static final String R = "r";

	private Unit c1;
	private Unit c2;
	private Unit af1;
	private Unit af2;

	public OneParentLocusRecord(Unit c1, Unit c2, Unit af1, Unit af2) {
		this.c1 = c1;
		this.c2 = c2;
		this.af1 = af1;
		this.af2 = af2;
	}

	public String getPattern() {
		generatePattern();
		return getC1C2() + DOT + getAF1AF2();
	}

	private String getAF1AF2() {
		return af1.getPattern() + af2.getPattern();
	}

	private String getC1C2() {
		return c1.getPattern() + c2.getPattern();
	}

	private void generatePattern() {
		if (c1.getLocus() != c2.getLocus()) {
			c1.setPattern(P);
			c2.setPattern(Q);
		}
		if (c1.getLocus() == c2.getLocus()) {
			c1.setPattern(Q);
			c2.setPattern(Q);
		}
		generateAfPattern();
	}

	private void generateAfPattern() {
		generatePattern(af1);
		generatePattern(af2);
	}

	private void generatePattern(Unit af) {
		if (af.getLocus() == c1.getLocus()) {
			af.setPattern(c1.getPattern());
		}
		if (af.getLocus() == c2.getLocus()) {
			af.setPattern(c2.getPattern());
		}
		if (StringUtils.isEmpty(af.getPattern())) {
			af.setPattern(R);
		}
	}

	public double getP() {
		return get(P);
	}

	public double getQ() {
		return get(Q);
	}

	private double get(String pattern) {
		if (af1.getPattern().equals(pattern)) {
			return af1.getValue();
		}
		if (af2.getPattern().equals(pattern)) {
			return af2.getValue();
		}
		if (c1.getPattern().equals(pattern)) {
			return c1.getValue();
		}
		if (c2.getPattern().equals(pattern)) {
			return c2.getValue();
		}
		return 0;
	}

}
