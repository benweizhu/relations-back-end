package me.zeph.relations.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "RELT_ALLELE_PROBABILITY")
public class AlleleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RELT_ALLELE_PROBABILITY_GEN")
	@SequenceGenerator(name = "RELT_ALLELE_PROBABILITY_GEN")
	@Column(name = "ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "LOCUS_ID")
	private LocusEntity locus;

	@Column(name = "ALLELE")
	private double allele;

	@Column(name = "PROBABILITY")
	private double probability;

	public double getProbability() {
		return probability;
	}
}
