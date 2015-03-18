package me.zeph.relations.model.entity;

import javax.persistence.*;

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
}
