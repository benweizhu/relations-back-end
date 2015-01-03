package me.zeph.relations.model;

import org.springframework.util.StringUtils;

public class OneParentLocusRecord {

	protected static final String DOT = ".";
	protected static final String P = "p";
	protected static final String Q = "q";
	protected static final String R = "r";

	protected Unit c1;
	protected Unit c2;
	protected Unit af1;
	protected Unit af2;

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

	protected String getAF1AF2() {
		return af1.getPattern() + af2.getPattern();
	}

	protected String getC1C2() {
		return c1.getPattern() + c2.getPattern();
	}

	protected void generatePattern() {
		generateCPattern();
		generateAfPattern();
	}

	protected void generateCPattern() {
		if (c1.getLocus() != c2.getLocus()) {
			c1.setPattern(P);
			c2.setPattern(Q);
		}
		if (c1.getLocus() == c2.getLocus()) {
			c1.setPattern(Q);
			c2.setPattern(Q);
		}
	}

	private void generateAfPattern() {
		generatePattern(af1);
		generatePattern(af2);
	}

	protected void generatePattern(Unit af) {
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

	protected double get(String pattern) {
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
