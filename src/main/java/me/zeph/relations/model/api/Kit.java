package me.zeph.relations.model.api;

import com.wordnik.swagger.annotations.ApiModel;
import org.springframework.hateoas.ResourceSupport;

@ApiModel
public class Kit extends ResourceSupport {

	private long kitId;
	private String name;

	public void setKitId(long kitId) {
		this.kitId = kitId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getKitId() {
		return kitId;
	}

	public String getName() {
		return name;
	}
}
