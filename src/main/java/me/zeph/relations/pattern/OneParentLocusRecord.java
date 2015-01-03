package me.zeph.relations.pattern;

import org.springframework.util.StringUtils;

public class OneParentLocusRecord {

	public static final String DOT = ".";
	public static final String P = "p";
	public static final String Q = "q";
	public static final String R = "r";
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
		if (af1.getLocus() == c1.getLocus()) {
			af1.setPattern(c1.getPattern());
		}
		if (af1.getLocus() == c2.getLocus()) {
			af1.setPattern(c2.getPattern());
		}
		if (StringUtils.isEmpty(af1.getPattern())) {
			af1.setPattern(R);
		}
		if (af2.getLocus() == c1.getLocus()) {
			af2.setPattern(c1.getPattern());
		}
		if (af2.getLocus() == c2.getLocus()) {
			af2.setPattern(c2.getPattern());
		}
		if (StringUtils.isEmpty(af2.getPattern())) {
			af2.setPattern(R);
		}
	}

	public double getP() {
		if (af1.getPattern().equals(P)) {
			return af1.getValue();
		}
		if (af2.getPattern().equals(P)) {
			return af2.getValue();
		}
		if (c1.getPattern().equals(P)) {
			return c1.getValue();
		}
		if (c2.getPattern().equals(P)) {
			return c2.getValue();
		}
		return 0;
	}

	public double getQ() {
		if (af1.getPattern().equals(Q)) {
			return af1.getValue();
		}
		if (af2.getPattern().equals(Q)) {
			return af2.getValue();
		}
		if (c1.getPattern().equals(Q)) {
			return c1.getValue();
		}
		if (c2.getPattern().equals(Q)) {
			return c2.getValue();
		}
		return 0;
	}

}
