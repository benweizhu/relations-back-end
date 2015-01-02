package me.zeph.relations.service.pi.formula.oneparent;


import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.pi.formula.OneParentFormula;

public class OneInQOneParentFormula extends OneParentFormula {
	@Override
	public double calculate(double c1Value, double c2Value, double af1Value, double af2Value,
	                        OneParentReqParam reqParam) {
		return isAllEquals(reqParam) ? (1f / c1Value) : 0;
	}

	private boolean isAllEquals(OneParentReqParam reqParam) {
		return af1EqualAf2(reqParam) && c1EqualC2(reqParam) && c1EqualAf1(reqParam);
	}
}
