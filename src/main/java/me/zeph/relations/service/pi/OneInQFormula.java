package me.zeph.relations.service.pi;


import me.zeph.relations.model.OneParentReqParam;

public class OneInQFormula extends Formula {
	@Override
	public double calculate(double c1Value, double c2Value, double af1Value, double af2Value,
	                        OneParentReqParam reqParam) {
		return isAllEquals(reqParam) ? (1 / c1Value) : 0;
	}

	private boolean isAllEquals(OneParentReqParam reqParam) {
		return af1EqualAf2(reqParam) && c1EqualC2(reqParam) && c1EqualAf1(reqParam);
	}
}
