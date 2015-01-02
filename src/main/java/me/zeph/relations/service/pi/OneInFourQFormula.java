package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;

public class OneInFourQFormula extends Formula {
	@Override
	public double calculate(double c1Value, double c2Value, double af1Value, double af2Value,
	                        OneParentReqParam reqParam) {
		return isPQQR(reqParam) ? (1 / (4 * c2Value)) : 0;
	}

	private boolean isPQQR(OneParentReqParam reqParam) {
		return !c1EqualC2(reqParam) && !af1EqualAf2(reqParam) && c2EqualAf1(reqParam) && !c1EqualAf2(reqParam);
	}

}
