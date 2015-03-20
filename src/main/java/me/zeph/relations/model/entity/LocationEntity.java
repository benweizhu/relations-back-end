package me.zeph.relations.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "RELT_LOCATION")
public class LocationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RELT_LOCATION_GEN")
	@SequenceGenerator(name = "RELT_LOCATION_GEN")
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
