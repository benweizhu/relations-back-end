package me.zeph.relations.service.pi.formula;

import me.zeph.relations.model.OneParentReqParam;

public abstract class Formula {

	public abstract double calculate(double c1Value, double c2Value, double af1Value, double af2Value,
	                                 OneParentReqParam reqParam);

	protected boolean af1EqualAf2(OneParentReqParam reqParam) {
		return reqParam.getAf1() == reqParam.getAf2();
	}

	protected boolean c1EqualC2(OneParentReqParam reqParam) {
		return reqParam.getC1() == reqParam.getC2();
	}

	protected boolean c1EqualAf1(OneParentReqParam reqParam) {
		return reqParam.getC1() == reqParam.getAf1();
	}

	protected boolean c1EqualAf2(OneParentReqParam reqParam) {
		return reqParam.getC1() == reqParam.getAf2();
	}

	protected boolean c2EqualAf1(OneParentReqParam reqParam) {
		return reqParam.getC2() == reqParam.getAf1();
	}

	protected boolean c2EqualAf2(OneParentReqParam reqParam) {
		return reqParam.getC2() == reqParam.getAf2();
	}
}
