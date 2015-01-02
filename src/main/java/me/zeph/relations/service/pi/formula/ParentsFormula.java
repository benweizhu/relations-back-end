package me.zeph.relations.service.pi.formula;

import me.zeph.relations.model.ParentsReqParam;

public abstract class ParentsFormula extends Formula {
	public abstract double calculate(double c1Value, double c2Value, double m1Value,
	                                 double m2Value, double af1Value, double af2Value,
	                                 ParentsReqParam reqParam);
}
