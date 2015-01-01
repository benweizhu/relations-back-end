package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;

public class PPlusQInFourPQFormula extends Formula {
	@Override
	public float calculate(float af1Value, float af2Value, float c1Value, float c2Value,
	                       OneParentReqParam reqParam) {
		float pi = 0;
		if (!c1EqualC2(reqParam) && !af1EqualAf2(reqParam) && (c1EqualAf1(reqParam) ||
				c1EqualAf2(reqParam)) && (c2EqualAf1(reqParam) || c2EqualAf2(reqParam))) {
			pi = (c1Value + c2Value) / (4 * c1Value) * c2Value;
		}
		return pi;
	}
}
