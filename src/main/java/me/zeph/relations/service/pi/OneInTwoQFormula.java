package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;

public class OneInTwoQFormula extends Formula {
	@Override
	public float calculate(float af1Value, float af2Value, float c1Value, float c2Value,
	                       OneParentReqParam reqParam) {
		float pi = 0;
		if (!c1EqualC2(reqParam) && af1EqualAf2(reqParam) &&
				(c1EqualAf1(reqParam) || c2EqualAf1(reqParam))) {
			pi = 1 / af1Value * 2;
		} else if (!af1EqualAf2(reqParam) && c1EqualC2(reqParam) &&
				(c1EqualAf1(reqParam) || c1EqualAf2(reqParam))) {
			pi = 1 / c1Value * 2;
		}
		return pi;
	}
}
