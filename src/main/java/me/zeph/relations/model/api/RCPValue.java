package me.zeph.relations.model.api;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel
public class RCPValue {

	private double value;

	public RCPValue() {
	}

	public RCPValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
