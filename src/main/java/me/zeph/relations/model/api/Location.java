package me.zeph.relations.model.api;

import org.springframework.hateoas.ResourceSupport;

public class Location extends ResourceSupport {

	private long locationId;
	private String name;

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
