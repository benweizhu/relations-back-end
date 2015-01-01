package me.zeph.relations.service.pi;

import me.zeph.relations.model.OneParentReqParam;

public abstract class Formula {

	public abstract float calculate(float af1Value, float af2Value, float c1Value, float c2Value,
	                                OneParentReqParam reqParam);

	protected boolean af1EqualAf2(OneParentReqParam reqParam) {
		return reqParam.getAf1() == reqParam.getAf2();
	}

	protected boolean c1EqualC2(OneParentReqParam reqParam) {
		return reqParam.getC1() == reqParam.getC2();
	}

	protected boolean c1EqualAf1(OneParentReqParam reqParam) {
		return reqParam.getAf1() == reqParam.getC1();
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
