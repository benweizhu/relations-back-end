package me.zeph.relations.service.pi;


import me.zeph.relations.model.OneParentReqParam;

public class OneInQFormula extends Formula {
	@Override
	public float calculate(float af1Value, float af2Value, float c1Value, float c2Value,
	                       OneParentReqParam reqParam) {
		float pi = 0;
		if (af1EqualAf2(reqParam) && c1EqualC2(reqParam) && c1EqualAf1(reqParam)) {
			pi = 1 / c1Value;
		}
		return pi;
	}
}
