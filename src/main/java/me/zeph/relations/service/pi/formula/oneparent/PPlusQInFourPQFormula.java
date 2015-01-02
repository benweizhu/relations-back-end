package me.zeph.relations.service.pi.formula.oneparent;

import me.zeph.relations.model.OneParentReqParam;
import me.zeph.relations.service.pi.formula.Formula;

public class PPlusQInFourPQFormula extends Formula {
	@Override
	public double calculate(double c1Value, double c2Value, double af1Value, double af2Value,
	                        OneParentReqParam reqParam) {
		return isPQPQ(reqParam) ? ((c1Value + c2Value) / ((4 * c1Value) * c2Value)) : 0;
	}

	private boolean isPQPQ(OneParentReqParam reqParam) {
		return !c1EqualC2(reqParam) && c1EqualAf1(reqParam) && c2EqualAf2(reqParam);
	}
}
