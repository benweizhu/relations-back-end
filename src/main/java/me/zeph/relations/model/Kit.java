package me.zeph.relations.model;

import org.springframework.hateoas.ResourceSupport;

public class Kit extends ResourceSupport {
	private long kitId;
	private String name;

	public void setKitId(long kitId) {
		this.kitId = kitId;
	}

	public void setName(String name) {
		this.name = name;
	}
}
