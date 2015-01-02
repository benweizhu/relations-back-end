package me.zeph.relations.service.pi.formula;

import me.zeph.relations.model.OneParentReqParam;

public abstract class OneParentFormula extends Formula {

	public abstract double calculate(double c1Value, double c2Value, double af1Value, double af2Value,
	                                 OneParentReqParam reqParam);
}
