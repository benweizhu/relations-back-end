package me.zeph.relations.model.api;

public class ParentsLocusRecord extends OneParentLocusRecord {

	private Unit m1;
	private Unit m2;

	public ParentsLocusRecord(Unit c1, Unit c2, Unit m1, Unit m2, Unit af1, Unit af2) {
		super(c1, c2, af1, af2);
		this.m1 = m1;
		this.m2 = m2;
	}

	@Override
	public String getPattern() {
		generatePattern();
		return getC1C2() + DOT + getM1M2() + DOT + getAF1AF2();
	}

	private String getM1M2() {
		return m1.getPattern() + m2.getPattern();
	}

	@Override
	protected void generatePattern() {
		generateCPattern();
		generateMAfPattern();
	}

	private void generateMAfPattern() {
		generatePattern(m1);
		generatePattern(m2);
		generatePattern(af1);
		generatePattern(af2);
	}

	@Override
	protected double get(String pattern) {
		if (c1.getPattern().equals(pattern)) {
			return c1.getValue();
		}
		if (c2.getPattern().equals(pattern)) {
			return c2.getValue();
		}
		if (m1.getPattern().equals(pattern)) {
			return m1.getValue();
		}
		if (m2.getPattern().equals(pattern)) {
			return m2.getValue();
		}
		if (af1.getPattern().equals(pattern)) {
			return af1.getValue();
		}
		if (af2.getPattern().equals(pattern)) {
			return af2.getValue();
		}
		return 0;
	}

}
