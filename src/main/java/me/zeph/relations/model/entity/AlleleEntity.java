package me.zeph.relations.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RELT_ALLELE")
public class AlleleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RELT_ALLELE_GEN")
	@SequenceGenerator(name = "RELT_ALLELE_GEN")
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(mappedBy = "alleles")
	private List<KitEntity> kits;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
