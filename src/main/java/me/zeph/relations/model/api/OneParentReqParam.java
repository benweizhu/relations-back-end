package me.zeph.relations.model.api;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel
public class OneParentReqParam extends ReqParam{

	private double af1;
	private double af2;
	private double c1;
	private double c2;

	public double getAf1() {
		return af1;
	}

	public void setAf1(double af1) {
		this.af1 = af1;
	}

	public double getAf2() {
		return af2;
	}

	public void setAf2(double af2) {
		this.af2 = af2;
	}

	public double getC1() {
		return c1;
	}

	public void setC1(double c1) {
		this.c1 = c1;
	}

	public double getC2() {
		return c2;
	}

	public void setC2(double c2) {
		this.c2 = c2;
	}
}
