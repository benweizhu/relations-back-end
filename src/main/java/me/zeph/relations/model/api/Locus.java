package me.zeph.relations.model.api;

import com.wordnik.swagger.annotations.ApiModel;
import org.springframework.hateoas.ResourceSupport;

@ApiModel
public class Locus extends ResourceSupport {

	private long locusId;

	private String name;

	public long getLocusId() {
		return locusId;
	}

	public void setLocusId(long locusId) {
		this.locusId = locusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
