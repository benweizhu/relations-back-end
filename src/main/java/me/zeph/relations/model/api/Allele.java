package me.zeph.relations.model.api;

import com.wordnik.swagger.annotations.ApiModel;
import org.springframework.hateoas.ResourceSupport;

@ApiModel
public class Allele extends ResourceSupport {

	private double alleleValue;
	private double probability;

	public double getAlleleValue() {
		return alleleValue;
	}

	public void setAlleleValue(double alleleValue) {
		this.alleleValue = alleleValue;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}
}
