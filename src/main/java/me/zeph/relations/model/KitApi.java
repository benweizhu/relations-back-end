package me.zeph.relations.model;

import org.springframework.hateoas.ResourceSupport;

public class KitApi extends ResourceSupport {
	private long id;
	private String name;

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
