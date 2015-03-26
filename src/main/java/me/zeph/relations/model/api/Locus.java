package me.zeph.relations.model.api;

import com.wordnik.swagger.annotations.ApiModel;
import org.springframework.hateoas.ResourceSupport;

@ApiModel
public class Locus extends ResourceSupport {

	private long alleleId;

	private String name;

	public long getAlleleId() {
		return alleleId;
	}

	public void setAlleleId(long alleleId) {
		this.alleleId = alleleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
