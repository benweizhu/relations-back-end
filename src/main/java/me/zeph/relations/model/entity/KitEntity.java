package me.zeph.relations.model.entity;

import javax.persistence.*;
import java.util.List;

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

	@ManyToMany
	@JoinTable(name = "RELR_KIT_ALLELE", joinColumns = {@JoinColumn(name = "kit_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "allele_id", referencedColumnName = "id")})
	private List<AlleleEntity> alleles;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<AlleleEntity> getAlleles() {
		return alleles;
	}

	public void addAllele(AlleleEntity allele) {
		if (!alleles.contains(allele)) {
			alleles.add(allele);
		}
		if (!allele.getKits().contains(this)) {
			allele.getKits().add(this);
		}
	}
}
