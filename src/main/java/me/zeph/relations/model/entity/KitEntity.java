package me.zeph.relations.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "RELT_KIT")
public class KitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RELT_KIT_GEN")
	@SequenceGenerator(name = "RELT_KIT_GEN")
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
